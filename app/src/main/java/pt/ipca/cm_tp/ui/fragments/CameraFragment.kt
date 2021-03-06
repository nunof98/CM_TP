package pt.ipca.cm_tp.ui.fragments

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.MediaRecorder
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import pt.ipca.cm_tp.MyApplication
import pt.ipca.cm_tp.R
import pt.ipca.cm_tp.databases.Leaderboard
import pt.ipca.cm_tp.databases.LeaderboardRepository
import pt.ipca.cm_tp.databases.Subject
import pt.ipca.cm_tp.ui.LoginActivity
import pt.ipca.cm_tp.ui.MainActivity
import pt.ipca.cm_tp.ui.RegisterActivity
import java.io.File
import java.io.IOException
import kotlin.math.acos
import kotlin.math.cos
import kotlin.math.round
import kotlin.math.sin


private const val LOG_TAG = "AudioRecordTest"
private const val FILE_NAME = "photo"
private const val REQUEST_CAMERA = 0
private const val REQUEST_RECORD_AUDIO = 1
private const val REQUEST_FINE_LOCATION = 2
private const val REQUEST_IMAGE_CAPTURE = 100

class CameraFragment : Fragment(){



    private var mediaRecorder: MediaRecorder? = null
    private var isRecording: Boolean = false
    private lateinit var imageButtonMic: ImageButton
    private lateinit var imageButtonContinue: ImageButton
    private lateinit var recordPath: String
    private lateinit var photoFile: File
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    lateinit var studentID: String
    private val db = Firebase.firestore

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_camera, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Get student id number
        studentID = (activity as RegisterActivity).studentID

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(requireContext())

        // Initialize image buttons
        imageButtonMic = requireView().findViewById(R.id.button_audio_capture)
        imageButtonMic.visibility = View.INVISIBLE
        imageButtonContinue = requireView().findViewById(R.id.button_continue)
        imageButtonContinue.visibility = View.INVISIBLE



        requireView().findViewById<ImageButton>(R.id.button_audio_capture).setOnClickListener {
            // Check audio record permission
            if (checkRecordPermission()) {
                // Start audio recording process
                recordAudio()
            } else {
                // No audio record permission
                Toast.makeText(
                    requireContext(),
                    "You must give audio record permission to use this feature",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        requireView().findViewById<ImageButton>(R.id.button_continue).setOnClickListener {
            // Send data for analysis
            sendData(studentID.toInt())
        }

        // Check location permission
        if (checkLocationPermission()) {
            // Get last location
            fusedLocationProviderClient.lastLocation.addOnSuccessListener { location ->
                // Fail
                if (location == null) {
                    Toast.makeText(
                        requireContext(),
                        "Sorry can't get Location",
                        Toast.LENGTH_SHORT
                    ).show()
                // Success
                } else location.apply {
                    //IPCAs' location
                    val latitudeIPCA = 41.53704048478451
                    val longitudeIPCA = -8.627900848553278

                    // Calculate phones distance from IPCA campus
                    val distance = round(
                        6378137 * acos(cos(location.latitude * Math.PI / 180) * cos(latitudeIPCA * Math.PI / 180)
                                        * cos((longitudeIPCA * Math.PI / 180) - (location.longitude * Math.PI / 180))
                                        + sin(location.latitude * Math.PI / 180) * sin(latitudeIPCA * Math.PI / 180))
                    )

                    // If phone is within radius of IPCA campus
                    if (distance <= 500) {
                        // Check camera permission
                        if (checkCameraPermission()) {
                            // Open native camera
                            openNativeCamera()
                            // Make record audio button visible
                            imageButtonMic.visibility = View.VISIBLE
                        } else {
                            // No camera permission
                            Toast.makeText(
                                requireContext(),
                                "You must give camera permission to use this feature",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    } else {
                        // Not on campus grounds
                        Toast.makeText(
                            requireContext(),
                            "You must be in the IPCA campus to use this feature",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        } else {
            // No location permission
            Toast.makeText(
                requireContext(),
                "You must give location permission to use this feature",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    /**
     * This method will take an image taken by the camera and put it in the ui
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == RESULT_OK && requestCode == REQUEST_IMAGE_CAPTURE) {
            val takenImage = BitmapFactory.decodeFile(photoFile.absolutePath)
            requireView()
                .findViewById<ImageView>(R.id.imageView_photo)
                .setImageBitmap(takenImage)
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    /**
     * Calling this method will open the default camera application.
     */
    private fun openNativeCamera() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        photoFile = getPhotoFile(FILE_NAME)
        val fileProvider = FileProvider.getUriForFile(requireContext(), "pt.ipca.cm_tp.fileprovider", photoFile)
        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, fileProvider)

        startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
    }

    private fun getPhotoFile(fileName: String): File {
        // Use 'getExternalFilesDir' on Context to access package-specific directories
        val storageDirectory = requireContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(fileName, ".jpg", storageDirectory)
    }

    /**
     * Calling this method will start/stop the recording process
     */
    private fun recordAudio() {
        isRecording = !isRecording

        if(isRecording) {
            imageButtonMic.setImageResource(R.drawable.ic_media_stop)
            startRecording()
        } else {
            imageButtonMic.setImageResource(R.drawable.ic_mic)
            stopRecording()
        }
    }

    /**
     * Calling this method will start audio recording
     */
    private fun startRecording() {
        // Set path
        recordPath = activity?.getExternalFilesDir("/")?.absolutePath ?: ""
        recordPath = "$recordPath/password.3gp"

        // Configure media recorder
        mediaRecorder = MediaRecorder().apply {
            setAudioSource(MediaRecorder.AudioSource.MIC)
            setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
            setOutputFile(recordPath)
            setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)

            try {
                prepare()
            } catch (e: IOException) {
                Log.e(LOG_TAG, "prepare() failed")
            }

            // Start recording
            start()
        }

        // Inform user recording as started
        Toast.makeText(requireContext(), "Recording as started", Toast.LENGTH_SHORT).show()
    }

    /**
     * Calling this method will stop audio recording
     */
    private fun stopRecording() {
        // Stop recording
        mediaRecorder?.apply {
            stop()
            release()
        }
        mediaRecorder = null

        // Inform user recording as stopped
        Toast.makeText(requireContext(), "Recording as stoped", Toast.LENGTH_SHORT).show()
        imageButtonContinue.visibility = View.VISIBLE
    }

    /**
     * Calling this method will send the image and audio recording to be analyse
     */
    private fun sendData(id: Int) {

        db.collection("leaderboard")
            .whereEqualTo("studentNumber", id)
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    Log.d("FIRESTORE_SUBJECTS:", "${document.id} => ${document.data}")

                    db.collection("leaderboard")
                        .document("${document.id}")
                        .update(mapOf(
                            "points" to (document.get("points").toString().toInt() + 10)
                        ))
                }
            }
            .addOnFailureListener { exception ->
                Log.w("FIRESTORE_SUBJECTS:", "Error getting documents: ", exception)
            }

        Toast.makeText(requireContext(), "Data sent", Toast.LENGTH_SHORT).show()

        // Change to HomeFragment
        val intent = Intent(requireContext(), MainActivity::class.java)
        intent.putExtra("studentID", studentID)
        startActivity(intent)
    }

    /**
     * Checks and if necessary requests permission to access camera
     * @return    Boolean   if permission is granted or not
     */
    private fun checkCameraPermission(): Boolean{
        if(ContextCompat
                .checkSelfPermission(requireContext(), Manifest.permission.CAMERA)
            != PackageManager.PERMISSION_GRANTED){
                // Ask permission for the camera
                requestPermissions(arrayOf(Manifest.permission.CAMERA), REQUEST_CAMERA)

            return false
        }

        return true
    }

    /**
     * Checks and if necessary requests permission to record audio
     * @return    Boolean   if permission is granted or not
     */
    private fun checkRecordPermission(): Boolean{
        if(ContextCompat
                .checkSelfPermission(requireContext(), Manifest.permission.RECORD_AUDIO)
            != PackageManager.PERMISSION_GRANTED){
            // Ask permission for the micro
            requestPermissions(arrayOf(Manifest.permission.RECORD_AUDIO), REQUEST_RECORD_AUDIO)

            return false
        }

        return true
    }

    /**
     * Checks and if necessary requests permission to access location
     * @return    Boolean   if permission is granted or not
     */
    private fun checkLocationPermission(): Boolean{
        if(ContextCompat
                .checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED){
            // Ask permission for the location
            requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), REQUEST_FINE_LOCATION)

            return false
        }

        return true
    }
}
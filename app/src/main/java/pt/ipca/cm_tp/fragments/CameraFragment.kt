package pt.ipca.cm_tp.fragments

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.media.MediaRecorder
import android.os.Bundle
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
import androidx.fragment.app.Fragment
import pt.ipca.cm_tp.R
import java.io.IOException

private const val LOG_TAG = "AudioRecordTest"
const val REQUEST_CAMERA = 0
const val REQUEST_RECORD_AUDIO = 1
private const val REQUEST_IMAGE_CAPTURE = 100

class CameraFragment : Fragment(){

    private var mediaRecorder: MediaRecorder? = null
    private var isRecording: Boolean = false
    private lateinit var imageButtonMic: ImageButton
    private lateinit var imageButtonContinue: ImageButton
    private lateinit var imageBitmap: Bitmap
    private lateinit var recordPath: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_camera, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize image buttons
        imageButtonMic = requireView().findViewById(R.id.button_audio_capture)
        imageButtonContinue = requireView().findViewById(R.id.button_continue)

        //openCamera()
        if(checkCameraPermission()) {
            openNativeCamera()
        }

        requireView().findViewById<ImageButton>(R.id.button_audio_capture).setOnClickListener {
            imageButtonContinue.visibility = View.INVISIBLE

            if(checkRecordPermission()) {
                recordAudio()
            }
        }

        requireView().findViewById<ImageButton>(R.id.button_continue).setOnClickListener {
            sendData()
        }
    }

    /**
     * Calling this method will send the image and audio recording to be analyse
     */
    private fun sendData() {

    }

    /**
     * This method will take an image taken by the camera and put it in the ui
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == AppCompatActivity.RESULT_OK) {
            imageBitmap = data?.extras?.get("data") as Bitmap
            requireView().findViewById<ImageView>(R.id.imageView_photo).setImageBitmap(imageBitmap)
        }

        super.onActivityResult(requestCode, resultCode, data)
    }

    /**
     * Calling this method will open the default camera application.
     */
    private fun openNativeCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, REQUEST_IMAGE_CAPTURE)
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
}
package pt.ipca.cm_tp.ui.fragments

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import pt.ipca.cm_tp.MyApplication
import pt.ipca.cm_tp.R
import pt.ipca.cm_tp.databases.Student
import pt.ipca.cm_tp.databases.StudentRepository
import pt.ipca.cm_tp.ui.LoginActivity
import pt.ipca.cm_tp.ui.RegisterActivity
import pt.ipca.cm_tp.ui.recyclerViews.HistoryAdapter
import pt.ipca.cm_tp.utils.TripleString



class HomeFragment : Fragment() {

    private lateinit var studentRepository: StudentRepository
    //private val repository = (requireActivity().application as MyApplication).studentRepository
    //private val db = AppDatabase.getDatabase(requireContext())
    //private val db = AppDatabase.getDatabase(requireContext().applicationContext as MyApplication)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize repositories
        studentRepository = (requireActivity().application as MyApplication).studentRepository

        displayStudentInfo(15464)

        // Bind button press to login function
        requireView().findViewById<Button>(R.id.button_register).setOnClickListener {
            changeToRegisterActivity()
        }

        // Get recyclerView from layout
        val recyclerView = requireView().findViewById<RecyclerView>(R.id.recyclerView_history)

        // Get data
        val values = listOf<TripleString>(
            TripleString("Corporate Finance", "Wed, Dec 1", "14:00","Wed, Dec 1","16:00","School of Management * Room 5 * Attendance on Time"),
            TripleString("Derivatives", "Thu, Dec 2", "16:00","Thu, Dec 2","18:00","School of Management * Room 3 * Attendance on Time"),
            TripleString("Investments", "Fri, Dec 3", "16:00","Fri, Dec 3","18:00", "School of Management * Room 1 * Attendance on Time"),
            TripleString("Crypto Currency", "Mon, Dec 6", "14:00","Mon, Dec 6","16:00","School of Management * Room 3 * Attendance on Time"),
            TripleString("Law of Investment", "Mon, Dec 6", "16:00","Mon, Dec 6","18:00", "School of Management * Room 2 * Attendance on Time")
        )

        // Initialize adapter
        val adapter = HistoryAdapter(values)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    /**
     * This method fetches student data from ROOM database and
     * displays it on the textViews in home fragment
     *
     */
    private fun displayStudentInfo(id: Int) {
        val student: Student = studentRepository.findStudentById(id)

        // Get student name
        requireView().
            findViewById<TextView>(R.id.textView_student_name).
                text = "${student.firstName} ${student.lastName} (${student.id})"

        // Get student course
        requireView().
            findViewById<TextView>(R.id.textView_student_course).
                text = "${student.course} — ${student.year}º year"
    }

    /**
     * This method will take an image taken by the camera and put it in the ui
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == AppCompatActivity.RESULT_OK) {
            val imageBitmap = data?.extras?.get("data") as Bitmap
            requireView().findViewById<ImageView>(R.id.imageView_photo).setImageBitmap(imageBitmap)
        }

        super.onActivityResult(requestCode, resultCode, data)
    }

    /**
     * Calling this method will open the default camera application.
     */
    private fun openNativeCamera() {
        val intent = Intent(requireContext(), )

    }

    /**
     * Changes activity to Register activity
     */
    private fun changeToRegisterActivity() {
        val intent = Intent(requireContext(), RegisterActivity::class.java)
        startActivity(intent)
    }
}
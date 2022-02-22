package pt.ipca.cm_tp.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import pt.ipca.cm_tp.MyApplication
import pt.ipca.cm_tp.R
import pt.ipca.cm_tp.databases.Attendance
import pt.ipca.cm_tp.databases.StudentRepository
import pt.ipca.cm_tp.ui.MainActivity
import pt.ipca.cm_tp.ui.RegisterActivity
import pt.ipca.cm_tp.ui.recyclerViews.HistoryAdapter

class HomeFragment : Fragment() {

    private val profileFragment by lazy { ProfileFragment() }
    private lateinit var studentRepository: StudentRepository

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

        // Get student id number
        val studentID = (activity as MainActivity).studentID.toInt()

        // Display students' info on Home view
        displayStudentInfo(studentID)

        // Bind button press to login function
        requireView().findViewById<Button>(R.id.button_register).setOnClickListener {
            changeToRegisterActivity()
        }

        // Bind imageView press to profile fragment
        requireView().findViewById<ImageView>(R.id.imageView_profile_picture).setOnClickListener {
            // Change to profile fragment
            (activity as MainActivity).loadFragment(profileFragment)
        }

        // Get recyclerView from layout
        val recyclerView = requireView().findViewById<RecyclerView>(R.id.recyclerView_history)

        // Get data
        val attendanceList = listOf<Attendance> (
            Attendance(subject="Mobile Computing", date="Wed, Dec 1", start="14:00", end="16:00", summaryInfo="School of Technology | Lab. Automation | Attendance on Time"),
            Attendance(subject="Integrated Laboratories I", date="Thu, Dec 2", start="16:00", end="18:00", summaryInfo="School of Technology | Lab. Electronic | Attendance on Time"),
            Attendance(subject="Image Processing and Computer Vision", date="Fri, Dec 3", start="16:00", end="18:00", summaryInfo="School of Technology | Lab. Electronic | Attendance on Time"),
            Attendance(subject="Digital Signal Processing", date="Mon, Dec 6", start="14:00", end="16:00", summaryInfo="School of Technology | Lab. Automation | Attendance on Time"),
            Attendance(subject="Embedded and Real-Time Systems", date="Mon, Dec 6", start="16:00", end="18:00", summaryInfo="School of Technology | Lab. Electronic | Attendance on Time")
        )

        // Initialize adapter
        val adapter = HistoryAdapter(attendanceList)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    /**
     * This method fetches student data from ROOM database and
     * displays it on the textViews in home fragment
     *
     */
    private fun displayStudentInfo(id: Int) {

        studentRepository.findStudentById(id) { student ->
            // Set student name
            requireView()
                .findViewById<TextView>(R.id.textView_student_name)
                    .text = "${student?.firstName} ${student?.lastName} nº${student?.id}"

            // Set student course
            requireView()
                .findViewById<TextView>(R.id.textView_student_course)
                    .text = "${student?.course} — ${student?.year}º year"
        }
    }

    /**
     * Changes activity to Register activity
     */
    private fun changeToRegisterActivity() {
        val intent = Intent(requireContext(), RegisterActivity::class.java)
        startActivity(intent)
    }
}
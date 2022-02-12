package pt.ipca.cm_tp.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import pt.ipca.cm_tp.MyApplication
import pt.ipca.cm_tp.R
import pt.ipca.cm_tp.databases.StudentRepository
import pt.ipca.cm_tp.ui.MainActivity
import pt.ipca.cm_tp.ui.RegisterActivity
import pt.ipca.cm_tp.ui.recyclerViews.HistoryAdapter
import pt.ipca.cm_tp.utils.MultiString



class HomeFragment : Fragment() {

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
        val studentID = (activity as MainActivity).studentID

        // Get student info from firestore
        getStudentInfo(studentID.toInt())
        // Display students' info on Home view
        displayStudentInfo(studentID.toInt())

        // Bind button press to login function
        requireView().findViewById<Button>(R.id.button_register).setOnClickListener {
            changeToRegisterActivity()
        }

        // Get recyclerView from layout
        val recyclerView = requireView().findViewById<RecyclerView>(R.id.recyclerView_history)

        // Get data
        val values = listOf<MultiString>(
            MultiString("Corporate Finance", "Wed, Dec 1", "14:00","Wed, Dec 1","16:00","School of Management * Room 5 * Attendance on Time"),
            MultiString("Derivatives", "Thu, Dec 2", "16:00","Thu, Dec 2","18:00","School of Management * Room 3 * Attendance on Time"),
            MultiString("Investments", "Fri, Dec 3", "16:00","Fri, Dec 3","18:00", "School of Management * Room 1 * Attendance on Time"),
            MultiString("Crypto Currency", "Mon, Dec 6", "14:00","Mon, Dec 6","16:00","School of Management * Room 3 * Attendance on Time"),
            MultiString("Law of Investment", "Mon, Dec 6", "16:00","Mon, Dec 6","18:00", "School of Management * Room 2 * Attendance on Time")
        )

        // Initialize adapter
        val adapter = HistoryAdapter(values)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun getStudentInfo(id: Int) {
        val db = Firebase.firestore

        db.collection("students")
            .whereEqualTo("id", id)
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    //Log.d("FIRESTORE:", "${document.id} => ${document.data}")
                    val data = document.data
                }
            }
            .addOnFailureListener { exception ->
                Log.w("FIRESTORE:", "Error getting documents: ", exception)
            }
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
                    .text = "${student?.firstName} ${student?.lastName} (${student?.id})"

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
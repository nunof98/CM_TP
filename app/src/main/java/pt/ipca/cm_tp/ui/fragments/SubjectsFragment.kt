package pt.ipca.cm_tp.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import pt.ipca.cm_tp.MyApplication
import pt.ipca.cm_tp.R
import pt.ipca.cm_tp.databases.StudentRepository
import pt.ipca.cm_tp.databases.Subject
import pt.ipca.cm_tp.databases.SubjectRepository
import pt.ipca.cm_tp.ui.MainActivity
import pt.ipca.cm_tp.ui.recyclerViews.SubjectsAdapter

class SubjectsFragment : Fragment(){

    private lateinit var subjectRepository: SubjectRepository
    private lateinit var studentRepository: StudentRepository
    private val db = Firebase.firestore

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_subjects, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize repository
        subjectRepository = (requireActivity().application as MyApplication).subjectRepository
        studentRepository = (requireActivity().application as MyApplication).studentRepository

        // Get student id number
        val studentID = (activity as MainActivity).studentID.toInt()
        studentRepository.findStudentById(studentID) { student ->
            // Get subjects from firestore
            student?.let { setup(student.course, student.year) }

            // Set label
            requireView()
                .findViewById<TextView>(R.id.textView_student_course)
                .text = student?.course

            // Get recyclerView from layout
            val recyclerView = requireView().findViewById<RecyclerView>(R.id.recyclerView_subjects)

            // Get data for ROOM database
            student?.let {
                subjectRepository
                    .getSubjectsByCourseAndYear(student.course, student.year) { subjectList ->
                        // Initialize adapter
                        val adapter = subjectList?.let { SubjectsAdapter(it) }
                        recyclerView.adapter = adapter
                        recyclerView.layoutManager = LinearLayoutManager(requireContext())
                    }
            }
        }
    }

    private fun setup(course: String, year: Int) {
        db.collection("subjects")
            .whereEqualTo("course", course)
            .whereEqualTo("year", year)
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    Log.d("FIRESTORE_SUBJECTS:", "${document.id} => ${document.data}")

                    subjectRepository.insertSubject(
                        Subject(
                            id = document.id,
                            course = document.get("course") as String,
                            name = document.get("name") as String,
                            officeHours = document.get("officeHours") as String,
                            professorEmail = document.get("professorEmail") as String,
                            professorName = document.get("professorName") as String,
                            schedule1 = document.get("schedule1") as String,
                            schedule2 = document.get("schedule2") as String,
                            semester = document.get("semester").toString().toInt(),
                            year = document.get("year").toString().toInt()
                        ))
                }
            }
            .addOnFailureListener { exception ->
                Log.w("FIRESTORE_SUBJECTS:", "Error getting documents: ", exception)
            }
    }
}
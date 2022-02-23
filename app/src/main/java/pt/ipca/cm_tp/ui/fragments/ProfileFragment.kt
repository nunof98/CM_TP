package pt.ipca.cm_tp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import pt.ipca.cm_tp.MyApplication
import pt.ipca.cm_tp.R
import pt.ipca.cm_tp.ui.MainActivity


class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Force imageView to show image
        val imageView = requireView().findViewById<ImageView>(R.id.imageView_background_pic)
        imageView.setImageResource(R.drawable.ipca_background)

        // Initialize repositories
        val studentRepository = (requireActivity().application as MyApplication).studentRepository

        // Get student id number
        val studentID = (activity as MainActivity).studentID.toInt()
        studentRepository.findStudentById(studentID) { student ->
            // Set student name on textView
            //requireView()
            //    .findViewById<ImageView>(R.id.ImageView_profile_picture)
            //    .setImageResource(student?.profilePicture)

            // Set student name on textView
            requireView()
                .findViewById<TextView>(R.id.textView_student_name)
                .text = "${student?.firstName} ${student?.lastName} ● ${student?.id}"

            // Set student course on textView
            requireView()
                .findViewById<TextView>(R.id.textView_student_course)
                .text = "${student?.course} — ${student?.year}º year"

            // Set student email on textView
            requireView()
                .findViewById<TextView>(R.id.textView_student_mail)
                .text = student?.email

            // Set student phone number on textView
            requireView()
                .findViewById<TextView>(R.id.textView_phone)
                .text = student?.phoneNumber

            // Set student address on textView
            requireView()
                .findViewById<TextView>(R.id.textView_address)
                .text = student?.address

            // Set student status on textView
            requireView()
                .findViewById<TextView>(R.id.textView_status)
                .text = student?.status

            // Set student average grade on textView
            requireView()
                .findViewById<TextView>(R.id.textView_overall_avg)
                .text = student?.averageGrade.toString()

        }
    }
}
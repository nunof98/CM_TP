package pt.ipca.cm_tp.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import pt.ipca.cm_room.MyDatabase
import pt.ipca.cm_tp.R
import pt.ipca.cm_tp.RegisterActivity
import pt.ipca.cm_tp.databases.StudentDao
import pt.ipca.cm_tp.recyclerViews.HistoryAdapter
import pt.ipca.cm_tp.utils.TripleString


class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Bind button press to login function
        requireView().findViewById<Button>(R.id.button_register).setOnClickListener {
            changeToRegisterActivity()
        }

        getStudentNameByID(15464)

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


    fun getStudentNameByID(id: Int){
        requireView().
            findViewById<TextView>(R.id.textView_student_name).
                text = StudentDao.getName(id) + "($id.toString())"
    }

    /**
     * Changes activity to Register activity
     */
    private fun changeToRegisterActivity() {
        val intent = Intent(requireContext(), RegisterActivity::class.java)
        startActivity(intent)
    }
}
package pt.ipca.cm_tp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import pt.ipca.cm_tp.MyApplication
import pt.ipca.cm_tp.R
import pt.ipca.cm_tp.databases.DeadlineRepository
import pt.ipca.cm_tp.databases.SubjectRepository
import pt.ipca.cm_tp.ui.MainActivity
import pt.ipca.cm_tp.ui.recyclerViews.DeadlineAdapter

class DeadlinesFragment : Fragment() {

    private val addDeadlineFragment by lazy { AddDeadlinesFragment() }
    private lateinit var deadlineRepository: DeadlineRepository

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_deadlines, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize repository
        deadlineRepository = (requireActivity().application as MyApplication).deadlineRepository

        // Bind imageView press to profile fragment
        requireView().findViewById<ImageButton>(R.id.imageButton_add).setOnClickListener {
            // Change to profile fragment
            (activity as MainActivity).loadFragment(addDeadlineFragment)
        }

        // Get recyclerView from layout
        val recyclerView = requireView().findViewById<RecyclerView>(R.id.recyclerView_deadline)

        deadlineRepository.getAll { deadlineList ->
            // Initialize adapter
            val adapter = deadlineList?.let { DeadlineAdapter(it) }
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun OpenEdit() {

    }


}
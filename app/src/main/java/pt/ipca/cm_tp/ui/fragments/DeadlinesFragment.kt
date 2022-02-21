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
import pt.ipca.cm_tp.R
import pt.ipca.cm_tp.ui.MainActivity
import pt.ipca.cm_tp.ui.recyclerViews.DeadlineAdapter

class DeadlinesFragment : Fragment() {

    private val addDeadlineFragment by lazy { AddDeadlinesFragment() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_deadlines, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Bind imageView press to profile fragment
        requireView().findViewById<ImageButton>(R.id.imageButton_add).setOnClickListener {
            // Change to profile fragment
            (activity as MainActivity).loadFragment(addDeadlineFragment)
        }

        // Get recyclerView from layout
        val recyclerView = requireView().findViewById<RecyclerView>(R.id.recyclerView_deadline)

        // Get data
        val deadlineList = listOf<pt.ipca.cm_tp.databases.Deadline>(
            pt.ipca.cm_tp.databases.Deadline(title = "Computação Móvel", dueDate =  "12 Days", description = "TODO"),
            pt.ipca.cm_tp.databases.Deadline(title = "Laboratórios Integrados I", dueDate =  "15 Days", description = "TODO"),
            pt.ipca.cm_tp.databases.Deadline(title = "Procesamento Digital de Sinal", dueDate =  "20 Days", description = "TODO"),
            pt.ipca.cm_tp.databases.Deadline(title = "Procesamento de Imagem e Visão por Computador", dueDate =  "18 Days", description = "TODO"),
            pt.ipca.cm_tp.databases.Deadline(title = "Sistemas Embebidos e Tempo Real", dueDate =  "35 Days", description = "TODO"))

        // Initialize adapter
        val adapter = DeadlineAdapter(deadlineList)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

    }

    private fun OpenEdit() {

    }


}
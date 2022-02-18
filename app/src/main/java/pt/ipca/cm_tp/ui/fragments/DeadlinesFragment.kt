package pt.ipca.cm_tp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import pt.ipca.cm_tp.R
import pt.ipca.cm_tp.ui.recyclerViews.DeadlineAdapter

class DeadlinesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_deadlines, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Get recyclerView from layout
        val recyclerView = requireView().findViewById<RecyclerView>(R.id.recyclerView_deadlineList)

        // Get data
        val values = listOf<pt.ipca.cm_tp.databases.Deadline>(
            pt.ipca.cm_tp.databases.Deadline(title = "Computação Móvel", dueDate =  "12 Days"),
            pt.ipca.cm_tp.databases.Deadline(title = "Laboratórios Integrados I", dueDate =  "15 Days"),
            pt.ipca.cm_tp.databases.Deadline(title = "Procesamento Digital de Sinal", dueDate =  "20 Days"),
            pt.ipca.cm_tp.databases.Deadline(title = "Procesamento de Imagem e Visão por Computador", dueDate =  "18 Days"),
            pt.ipca.cm_tp.databases.Deadline(title = "Sistemas Embebidos e Tempo Real", dueDate =  "35 Days"))

        // Initialize adapter
        val adapter = DeadlineAdapter(values)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

    }

    private fun OpenEdit() {

    }


}
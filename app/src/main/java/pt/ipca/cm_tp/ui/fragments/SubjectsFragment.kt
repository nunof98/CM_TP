package pt.ipca.cm_tp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import pt.ipca.cm_tp.R
import pt.ipca.cm_tp.ui.recyclerViews.SubjectsAdapter

class SubjectsFragment : Fragment(){

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_subjects, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*
        val listView = requireView().findViewById<ListView>(R.id.listView_subjects)

        val values = listOf<String>(
            "Computação Móvel",
            "Laboratórios Integrados I",
            "Procesamento Digial de Sinal",
            "Procesamento de Imagem e Visão por Computador",
            "Sistemas Embebidos e Tempo Real")

        val adapter = ArrayAdapter<String>(requireContext(), android.R.layout.simple_list_item_1, values)
        listView.adapter = adapter

         */

        // Get recyclerView from layout
        val recyclerView = requireView().findViewById<RecyclerView>(R.id.recyclerView_subjects)

        // Get data
        val values = listOf<String>(
            "Computação Móvel",
            "Laboratórios Integrados I",
            "Procesamento Digial de Sinal",
            "Procesamento de Imagem e Visão por Computador",
            "Sistemas Embebidos e Tempo Real")

        // Initialize adapter
        val adapter = SubjectsAdapter(values)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

    }

}
package pt.ipca.cm_tp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import pt.ipca.cm_tp.R
import pt.ipca.cm_tp.recyclerViews.DeadlineAdapter
import pt.ipca.cm_tp.recyclerViews.SubjectsAdapter
import pt.ipca.cm_tp.utils.DoubleString
import pt.ipca.cm_tp.utils.TripleString

class DeadlinesFragment : Fragment(){

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_deadlines, container, false)
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
        val recyclerView = requireView().findViewById<RecyclerView>(R.id.recyclerView_deadlineList)

        // Get data
        val values = listOf<DoubleString>(
            DoubleString("Computação Móvel", "10 Days"),
            DoubleString("Laboratórios Integrados I","15 Days"),
            DoubleString("Procesamento Digital de Sinal","20 Days"),
            DoubleString("Procesamento de Imagem e Visão por Computador","18 Days"),
            DoubleString("Sistemas Embebidos e Tempo Real","35 Days"))

        // Initialize adapter
        val adapter = DeadlineAdapter(values)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

    }

    private fun OpenEdit() {

    }


}
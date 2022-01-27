package pt.ipca.cm_tp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import pt.ipca.cm_tp.utils.DoubleString
import pt.ipca.cm_tp.R
import pt.ipca.cm_tp.recyclerViews.SubjectsAdapter

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

        val recyclerView = requireView().findViewById<RecyclerView>(R.id.recyclerView_subjects)

        val values = listOf<DoubleString>(
            DoubleString("CM", "Computação Móvel"),
            DoubleString("LII", "Laboratórios Integrados I"),
            DoubleString("PDS", "Procesamento Digial de Sinal"),
            DoubleString("PIVC", "Procesamento de Imagem e Visão por Computador"),
            DoubleString("SETR", "Sistemas Embebidos e Tempo Real"),
        )

        val adapter = SubjectsAdapter(values)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

}
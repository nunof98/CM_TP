package pt.ipca.cm_tp.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import pt.ipca.cm_tp.utils.DoubleString
import pt.ipca.cm_tp.recyclerViews.DeadlineAdapter
import pt.ipca.cm_tp.R
import pt.ipca.cm_tp.RegisterActivity
import pt.ipca.cm_tp.SignUpActivity
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

        requireView().findViewById<Button>(R.id.button_register).setOnClickListener {
            changeToRegisterActivity()
        }

        val recyclerView = requireView().findViewById<RecyclerView>(R.id.recyclerView_deadline)

        val values = listOf<TripleString>(
            TripleString("8", "Computação Móvel", "Make app"),
            TripleString("10", "Laboratórios Integrados I", "Make PCB"),
            TripleString("25", "Procesamento Digial de Sinal", "Make voice recognition algorithm"),
            TripleString("55", "Procesamento de Imagem e Visão por Computador", "Make OCR"),
            TripleString("100", "Sistemas Embebidos e Tempo Real", "Make code")
        )

        val adapter = DeadlineAdapter(values)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun changeToRegisterActivity() {
        val intent = Intent(requireContext(), RegisterActivity::class.java)
        startActivity(intent)
    }
}
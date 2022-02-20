package pt.ipca.cm_tp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import pt.ipca.cm_tp.R
import pt.ipca.cm_tp.databases.Leaderboard
import pt.ipca.cm_tp.ui.recyclerViews.LeaderboardAdapter


class LeaderboardFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_leaderboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Get recyclerView from layout
        val recyclerView = requireView().findViewById<RecyclerView>(R.id.recyclerView_leatherBoard)

        // Get data
        val leaderboardList = listOf<Leaderboard>(
            Leaderboard(position = 1, studentName =  "Nuno Fernandes", points = 100),
            Leaderboard(position = 2, studentName =  "Joaquin Dillen", points = 95),
            Leaderboard(position = 3, studentName =  "André Fernandes", points = 90))

        // Initialize adapter
        val adapter = LeaderboardAdapter(leaderboardList)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }
}
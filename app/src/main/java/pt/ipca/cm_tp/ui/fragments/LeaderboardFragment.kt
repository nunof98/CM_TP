package pt.ipca.cm_tp.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import pt.ipca.cm_tp.MyApplication
import pt.ipca.cm_tp.R
import pt.ipca.cm_tp.databases.Leaderboard
import pt.ipca.cm_tp.databases.LeaderboardRepository
import pt.ipca.cm_tp.ui.recyclerViews.LeaderboardAdapter


class LeaderboardFragment : Fragment() {

    private lateinit var leaderboardRepository: LeaderboardRepository
    private val db = Firebase.firestore

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_leaderboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize repository
        leaderboardRepository = (requireActivity().application as MyApplication).leaderboardRepository

        // Get leaderboard from firestore
        setup()

        // Get recyclerView from layout
        val recyclerView = requireView().findViewById<RecyclerView>(R.id.recyclerView_leatherboard)

        leaderboardRepository.getAll { leaderboardList ->
            // Initialize adapter
            val adapter = leaderboardList?.let { LeaderboardAdapter(it) }
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun setup() {
        db.collection("leaderboard")
            .get()
            .addOnSuccessListener { documents ->
                for ((index, document) in documents.withIndex()) {
                    Log.d("FIRESTORE_LEADERBOARD:", "${document.id} => ${document.data}")

                    leaderboardRepository.insert(
                        Leaderboard(
                            studentNumber = document.get("studentNumber").toString().toInt(),
                            studentName = document.get("studentName") as String,
                            points = document.get("points").toString().toInt(),
                        )
                    )
                }
            }
            .addOnFailureListener { exception ->
                Log.w("FIRESTORE_SUBJECTS:", "Error getting documents: ", exception)
            }
    }
}
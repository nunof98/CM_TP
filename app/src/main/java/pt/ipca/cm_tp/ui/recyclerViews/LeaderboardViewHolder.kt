package pt.ipca.cm_tp.ui.recyclerViews

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import pt.ipca.cm_tp.R
import pt.ipca.cm_tp.databases.Leaderboard

class LeaderboardViewHolder(inflater: LayoutInflater, val  parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.item_leaderboard, parent, false)) {

    private var positionTV = itemView.findViewById<TextView>(R.id.textView_position)
    private var nameTV = itemView.findViewById<TextView>(R.id.textView_student_name)
    private var pointsTV = itemView.findViewById<TextView>(R.id.textView_points)

    fun bindData(leaderboard: Leaderboard, position: Int){
        positionTV.text = (position + 1).toString()
        nameTV.text = "${leaderboard.studentName}\nnº${leaderboard.studentNumber}"
        pointsTV.text = leaderboard.points.toString()
    }
}
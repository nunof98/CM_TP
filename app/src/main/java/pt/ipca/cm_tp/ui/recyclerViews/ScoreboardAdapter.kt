package pt.ipca.cm_tp.ui.recyclerViews

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ScoreboardAdapter(val leatherBoardList: List<String>) :
    RecyclerView.Adapter<ScoreboardViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScoreboardViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ScoreboardViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: ScoreboardViewHolder, position: Int) {
        holder.bindData(leatherBoardList.get(position))
    }

    override fun getItemCount(): Int {
        return leatherBoardList.size
    }

}
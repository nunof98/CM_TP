package pt.ipca.cm_tp.recyclerViews

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class LeatherBoardAdapter(val leatherBoardList: List<String>) :
    RecyclerView.Adapter<LeatherBoardViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeatherBoardViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return LeatherBoardViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: LeatherBoardViewHolder, position: Int) {
        holder.bindData(leatherBoardList.get(position))
    }

    override fun getItemCount(): Int {
        return leatherBoardList.size
    }

}
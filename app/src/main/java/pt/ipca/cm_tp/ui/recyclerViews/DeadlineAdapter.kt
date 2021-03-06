package pt.ipca.cm_tp.ui.recyclerViews

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import pt.ipca.cm_tp.databases.Deadline

class DeadlineAdapter(val deadlineList: List<Deadline>):
    RecyclerView.Adapter<DeadlineViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeadlineViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return DeadlineViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: DeadlineViewHolder, position: Int) {
        holder.bindData(deadlineList.get(position))
    }

    override fun getItemCount(): Int {
        return deadlineList.size
    }
}
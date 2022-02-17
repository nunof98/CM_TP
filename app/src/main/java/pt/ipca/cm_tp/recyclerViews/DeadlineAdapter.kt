package pt.ipca.cm_tp.recyclerViews

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import pt.ipca.cm_tp.utils.DoubleString

class DeadlineAdapter(val deadlineList: List<DoubleString>):
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
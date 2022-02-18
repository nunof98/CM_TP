package pt.ipca.cm_tp.ui.recyclerViews

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import pt.ipca.cm_tp.databases.Subject

class SubjectsAdapter(val subjectList: List<Subject>) :
    RecyclerView.Adapter<SubjectsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return SubjectsViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: SubjectsViewHolder, position: Int) {
        holder.bindData(subjectList.get(position))
    }

    override fun getItemCount(): Int {
        return subjectList.size
    }
}
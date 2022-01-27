package pt.ipca.cm_tp.recyclerViews

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import pt.ipca.cm_tp.utils.DoubleString
import pt.ipca.cm_tp.R

class SubjectsViewHolder(inflater: LayoutInflater, val  parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.item_subjects, parent, false)) {

    private var tvAbbreviation = itemView.findViewById<TextView>(R.id.textView_subject_abbreviation)
    private var tvName = itemView.findViewById<TextView>(R.id.textView_subject_name)

    fun bindData(doubleString: DoubleString){
        tvAbbreviation.text = doubleString.s1
        tvName.text = doubleString.s2
    }
}
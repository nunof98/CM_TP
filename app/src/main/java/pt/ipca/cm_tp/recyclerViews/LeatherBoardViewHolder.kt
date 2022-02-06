package pt.ipca.cm_tp.recyclerViews

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import pt.ipca.cm_tp.utils.DoubleString
import pt.ipca.cm_tp.R

class LeatherBoardViewHolder(inflater: LayoutInflater, val  parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.item_leatherBoard, parent, false)) {

    private var tvName = itemView.findViewById<TextView>(R.id.textView_subject_name)

    fun bindData(string: String){
        tvName.text = string
    }
}
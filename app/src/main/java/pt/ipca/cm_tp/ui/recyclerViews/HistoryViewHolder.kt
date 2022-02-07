package pt.ipca.cm_tp.ui.recyclerViews

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import pt.ipca.cm_tp.R
import pt.ipca.cm_tp.utils.TripleString

class HistoryViewHolder(inflater: LayoutInflater, val  parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.item_history, parent, false)) {

    private var subject_name = itemView.findViewById<TextView>(R.id.textView_subject_name)
    private var date_entry = itemView.findViewById<TextView>(R.id.textView_date_entry)
    private var entry_hour = itemView.findViewById<TextView>(R.id.textView_entry_hour)
    private var date_end = itemView.findViewById<TextView>(R.id.textView_date_end)
    private var end_hour = itemView.findViewById<TextView>(R.id.textView_end_hour)
    private var resume_info = itemView.findViewById<TextView>(R.id.textView_resume_info)


    fun bindData(tripleString: TripleString){
        subject_name.text = tripleString.s1
        date_entry.text = tripleString.s2
        entry_hour.text = tripleString.s3
        date_end.text = tripleString.s4
        end_hour.text = tripleString.s5
        resume_info.text = tripleString.s6
    }
}
package pt.ipca.cm_tp.ui.recyclerViews

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import pt.ipca.cm_tp.R
import pt.ipca.cm_tp.databases.Attendance

class HistoryViewHolder(inflater: LayoutInflater, val  parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.item_history, parent, false)) {

    private var subject_name = itemView.findViewById<TextView>(R.id.textView_subject_name)
    private var date_entry = itemView.findViewById<TextView>(R.id.textView_date_entry)
    private var entry_hour = itemView.findViewById<TextView>(R.id.textView_entry_hour)
    private var date_end = itemView.findViewById<TextView>(R.id.textView_date_end)
    private var end_hour = itemView.findViewById<TextView>(R.id.textView_end_hour)
    private var resume_info = itemView.findViewById<TextView>(R.id.textView_resume_info)


    fun bindData(attendance: Attendance){
        subject_name.text = attendance.subject
        date_entry.text = attendance.date
        entry_hour.text = attendance.start
        date_end.text = attendance.date
        end_hour.text = attendance.end
        resume_info.text = attendance.summaryInfo
    }
}
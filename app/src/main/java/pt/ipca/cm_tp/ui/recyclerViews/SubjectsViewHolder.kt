package pt.ipca.cm_tp.ui.recyclerViews

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import pt.ipca.cm_tp.R
import pt.ipca.cm_tp.databases.Subject

class SubjectsViewHolder(inflater: LayoutInflater, val  parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.item_subjects, parent, false)) {

    private var subjectNameTV = itemView.findViewById<TextView>(R.id.textView_subject_name)
    private var professorNameTV = itemView.findViewById<TextView>(R.id.textView_prof_name)
    private var professorEmailTV = itemView.findViewById<TextView>(R.id.textView_prof_email)
    private var scheduleTV = itemView.findViewById<TextView>(R.id.textView_schedule)


    fun bindData(subject: Subject){
        subjectNameTV.text = subject.name
        professorNameTV.text = subject.professorName
        professorEmailTV.text = subject.professorEmail
        scheduleTV.text = "${subject.schedule1}\n${subject.schedule2}"
    }
}
package pt.ipca.cm_tp.recyclerViews

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import pt.ipca.cm_tp.R
import pt.ipca.cm_tp.utils.TripleString

class DeadlineViewHolder(inflater: LayoutInflater, val  parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.item_deadline, parent, false)) {

    private var tvDays = itemView.findViewById<TextView>(R.id.textView_days_left)
    private var tvSubject = itemView.findViewById<TextView>(R.id.textView_Event_title)
    //private var tvDescription = itemView.findViewById<TextView>(R.id.textView_description) -> We need to pass this to an image.


    fun bindData(tripleString: TripleString){
        tvDays.text = tripleString.s1
        tvSubject.text = tripleString.s2
        //tvDescription.text = tripleString.s3
    }
}
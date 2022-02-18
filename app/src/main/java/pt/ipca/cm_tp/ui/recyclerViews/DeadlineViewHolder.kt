package pt.ipca.cm_tp.ui.recyclerViews

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import pt.ipca.cm_tp.R
import pt.ipca.cm_tp.databases.Deadline

class DeadlineViewHolder(inflater: LayoutInflater, val  parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.item_deadline, parent, false)) {

    private var daysTV = itemView.findViewById<TextView>(R.id.textView_days_left)
    private var evenTitleTV = itemView.findViewById<TextView>(R.id.textView_event_title)
    private var photoIV = itemView.findViewById<ImageView>(R.id.imageView_deadline_pic)
    //private var tvDescription = itemView.findViewById<TextView>(R.id.textView_description) -> We need to pass this to an image.


    fun bindData(deadline: Deadline){
        daysTV.text = deadline.dueDate.toString()
        evenTitleTV.text = deadline.title
        //photoIV.setImageResource(R.drawable.scenic1)
    }
}


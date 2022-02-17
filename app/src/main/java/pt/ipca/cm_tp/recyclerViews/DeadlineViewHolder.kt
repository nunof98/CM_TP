package pt.ipca.cm_tp.recyclerViews

import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import pt.ipca.cm_tp.R
import pt.ipca.cm_tp.utils.DoubleString


class DeadlineViewHolder(inflater: LayoutInflater, val  parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.item_deadline, parent, false)) {

    private var evDays = itemView.findViewById<TextView>(R.id.textView_days_left)
    private var evEventitle = itemView.findViewById<TextView>(R.id.textView_event_title)
    private var evImage = itemView.findViewById<ImageView>(R.id.imageView_deadline_pic)
    //private var tvDescription = itemView.findViewById<TextView>(R.id.textView_description) -> We need to pass this to an image.


    fun bindData(doubleString: DoubleString){
        evEventitle.text = doubleString.s1
        evDays.text = doubleString.s2
        evImage.setImageResource(R.drawable.scenic1)
    }



}


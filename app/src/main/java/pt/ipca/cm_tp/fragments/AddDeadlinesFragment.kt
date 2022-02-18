package pt.ipca.cm_tp.fragments

import android.R.attr
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import pt.ipca.cm_tp.R
import pt.ipca.cm_tp.utils.TripleString


val PICK_IMAGE = 1


class AddDeadlinesFragment : Fragment(){

    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_add_deadline, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Bind button press to Change Background Picture
        requireView().findViewById<Button>(R.id.button_select_background).setOnClickListener {
            select_background()
        }

        // Bind button press to Add Event
        requireView().findViewById<Button>(R.id.button_add_event).setOnClickListener {
            add_event()
        }

        // Bind button press to Delete Event
        requireView().findViewById<Button>(R.id.button_delete_event).setOnClickListener {
            delete_event()
        }

        // Get data
        val values = listOf<TripleString>(
            TripleString("Corporate Finance", "Wed, Dec 1", "14:00","Wed, Dec 1","16:00","School of Management * Room 5 * Attendance on Time"),
            TripleString("Derivatives", "Thu, Dec 2", "16:00","Thu, Dec 2","18:00","School of Management * Room 3 * Attendance on Time"),
            TripleString("Investments", "Fri, Dec 3", "16:00","Fri, Dec 3","18:00", "School of Management * Room 1 * Attendance on Time"),
            TripleString("Crypto Currency", "Mon, Dec 6", "14:00","Mon, Dec 6","16:00","School of Management * Room 3 * Attendance on Time"),
            TripleString("Law of Investment", "Mon, Dec 6", "16:00","Mon, Dec 6","18:00", "School of Management * Room 2 * Attendance on Time")
        )


    }

    /**
     * Open Background selection to change background image
     */
    private fun select_background() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE)
    }



    /**
     * Saves the event information
     */
    private fun add_event() {

    }

    /**
     * Deletes the event Information
     */
    private fun delete_event() {

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == PICK_IMAGE) {
            //TODO: action
            val selectedImageUri: Uri? = data?.getData()
        }
    }

}
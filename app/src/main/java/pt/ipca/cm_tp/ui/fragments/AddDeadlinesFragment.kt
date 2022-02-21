package pt.ipca.cm_tp.ui.fragments

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputEditText
import pt.ipca.cm_tp.MyApplication
import pt.ipca.cm_tp.R
import pt.ipca.cm_tp.databases.Deadline
import pt.ipca.cm_tp.databases.DeadlineRepository
import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter


val PICK_IMAGE = 1

class AddDeadlinesFragment : Fragment(){

    private lateinit var deadlineRepository: DeadlineRepository
    private lateinit var period: Period

    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_add_deadline, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize repositories
        deadlineRepository = (requireActivity().application as MyApplication).deadlineRepository

        // Bind button press to Change Background Picture
        requireView().findViewById<ImageButton>(R.id.imageButton_select_background).setOnClickListener {
            selectBackground()
        }
        // Bind button press to Add Event
        requireView().findViewById<Button>(R.id.button_add_event).setOnClickListener {
            addEvent()
        }
        // Bind button press to Delete Event
        requireView().findViewById<Button>(R.id.button_delete_event).setOnClickListener {
            deleteEvent()
        }

        // Set up text watcher for event title
        val eventTitleTextWatcher: TextWatcher = object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                requireView().findViewById<TextView>(R.id.textView_event_title).text = s.toString()
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
        }
        requireView().findViewById<TextInputEditText>(R.id.tet_event_title).addTextChangedListener(eventTitleTextWatcher)

        // Set focus listener
        requireView().findViewById<TextInputEditText>(R.id.tet_date).setOnFocusChangeListener { _, b ->
            // Lost focus
            if (!b) {
                // Get current time
                val current = LocalDate.now()
                // Get user input
                val stringDate = requireView().findViewById<TextView>(R.id.tet_date).text.toString()
                // Convert to LocalDate
                val finalDate = LocalDate.parse(stringDate.replace("/", "-"), DateTimeFormatter.ISO_DATE)
                // Calculate difference between dates in days
                period = Period.between(current, finalDate)
                // Set text
                requireView().findViewById<TextView>(R.id.textView_days_left).text = "${period.days} Days"
            }
        }
    }

    /**
     * Calling this method will open gallery to change background image
     */
    private fun selectBackground() {
         val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
         startActivityForResult(intent, PICK_IMAGE)
    }

    /**
     * Saves the event information
     */
    @RequiresApi(Build.VERSION_CODES.O)
    private fun addEvent() {
        val eventTitle = requireView().findViewById<TextInputEditText>(R.id.tet_event_title).text.toString()
        val eventDescription = requireView().findViewById<TextInputEditText>(R.id.tet_event_description).text.toString()
      //val image = requireView().findViewById<ImageView>(R.id.imageView_background_pic).drawable

        deadlineRepository.insertDeadline(
            Deadline(
                title = eventTitle,
                dueDate = "${period.days}",
                description = eventDescription,)
                //picture = image)
        )
    }

    /**
     * Deletes the event Information
     */
    private fun deleteEvent() {

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE) {
            val imageUri = data?.data
            requireView()
                .findViewById<ImageView>(R.id.imageView_deadline_pic)
                .setImageURI(imageUri)
        }
    }
}
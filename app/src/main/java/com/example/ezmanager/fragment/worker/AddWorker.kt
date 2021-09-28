package com.example.ezmanager.fragment.worker

import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.ezmanager.R
import com.example.ezmanager.database.DatabaseHandler
import java.text.SimpleDateFormat
import java.util.*

class AddWorker : Fragment() {

    private lateinit var addbtn: Button
    val cal= Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_worker, container, false)

        val db = DatabaseHandler(activity as Context)
        val newDate: TextView = view.findViewById(R.id.WorkerJoinDate)
        val openDatePicker: Button = view.findViewById(R.id.btnOpenDatePicker)

        val dateSetListener =
            DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                val myFormat = "dd-MM-yyyy"
                val sdf = SimpleDateFormat(myFormat, Locale.US)
                newDate.text = sdf.format(cal.time)
            }

        openDatePicker.setOnClickListener {
            DatePickerDialog(
                activity as Context,
                dateSetListener,
                // set DatePickerDialog to point to today's date when it loads up
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)
            ).show()

        }
        return view
    }
}
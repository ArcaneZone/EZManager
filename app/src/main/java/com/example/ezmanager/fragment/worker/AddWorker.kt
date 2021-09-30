package com.example.ezmanager.fragment.worker

import android.app.DatePickerDialog
import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.ezmanager.R
import com.example.ezmanager.database.DatabaseHandler
import com.example.ezmanager.model.Transaction
import com.example.ezmanager.model.Worker
import com.google.android.material.slider.Slider
import com.google.android.material.textfield.TextInputLayout
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.*

class AddWorker(context: Context) : Fragment() {
    private lateinit var toolBar: Toolbar

    private lateinit var addbtn: Button
    val db=DatabaseHandler(context)
    val cal= Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_worker, container, false)

        toolBar = view.findViewById(R.id.WorkerAddUserToolBar)
        setToolBar()

        val newWorkerName: TextInputLayout =view.findViewById(R.id.WorkerAddName)
        val newWorkerPhone: TextInputLayout =view.findViewById(R.id.WorkerAddPhone)
        val newWorkerAddress: TextInputLayout =view.findViewById(R.id.WorkerAddAddress)
        val newDate: TextView = view.findViewById(R.id.WorkerJoinDate)
        val openDatePicker: Button = view.findViewById(R.id.btnOpenDatePicker)
        val switchUserType: Slider = view.findViewById(R.id.switchUserType)
        var userType: String = "none"
        addbtn=view.findViewById(R.id.btnAddWorker)

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

        addbtn.setOnClickListener {
            if(switchUserType.value<=50) {
                userType = "Temporary"
            } else if(switchUserType.value>50) {
                userType = "Permanent"
            }
            if (newWorkerName.editText?.text.toString().trim().isNotEmpty() && newWorkerPhone.editText?.text.toString().trim().isNotEmpty()){
                val worker: Worker = Worker( cal.time.toString(),newWorkerName.editText?.text.toString().trim(),0,newWorkerPhone.editText?.text.toString().trim(),newDate.text.toString().trim(),userType)
                db.addWorker(worker)
            }
        }
        return view
    }
    private fun setToolBar() {

        (activity as AppCompatActivity).setSupportActionBar(toolBar)
        (activity as AppCompatActivity).supportActionBar?.title = "Add Your New Worker"
        (activity as AppCompatActivity).supportActionBar?.setHomeButtonEnabled(true)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (activity as AppCompatActivity).supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_back_arrow)
        toolBar.setNavigationOnClickListener {
            (activity as AppCompatActivity).onBackPressed()
        }
    }
}
package com.example.ezmanager.fragment.customer

import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.ezmanager.R
import com.example.ezmanager.database.DatabaseHandler
import com.example.ezmanager.model.Customer
import com.google.android.material.textfield.TextInputLayout
import java.text.SimpleDateFormat
import java.util.*


class AddCustomerFragment(context: Context) : Fragment() {
    private lateinit var toolBar: Toolbar
    private val cal: Calendar = Calendar.getInstance()

    val db= DatabaseHandler(context)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.fragment_add_user, container, false)

        toolBar = view.findViewById(R.id.CustomerAddUserToolBar)
        setToolBar()

        val addNewCustomer:Button=view.findViewById(R.id.BtnCustomerAddCustomer)

       val newCustomerName: TextInputLayout =view.findViewById(R.id.CustomerAddUserName)
        val newPhone: TextInputLayout =view.findViewById(R.id.CustomerAddUserPhone)
        val newAddress: TextInputLayout =view.findViewById(R.id.CustomerAddUserAddress)
        val newArea:TextInputLayout=view.findViewById(R.id.CustomerAddUserArea)
        val newDate: TextView =view.findViewById(R.id.txtDate)
        val openDatePicker: Button =view.findViewById(R.id.btnCustomerOpenDatePicker)
        val dateSetListener =
            DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                val myFormat = "dd-MM-yyyy"
                val sdf = SimpleDateFormat(myFormat, Locale.US)
                newDate.text=sdf.format(cal.time)
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
        addNewCustomer.setOnClickListener {
        val customer=Customer(cal.time.toString(),newCustomerName.editText?.text.toString(),newPhone.editText?.text.toString(),newAddress.editText?.text.toString(),newArea.editText?.text.toString(),newDate.text.toString())
        db.addCustomer(customer)
        }

        return view
    }


private fun setToolBar() {

    (activity as AppCompatActivity).setSupportActionBar(toolBar)
    (activity as AppCompatActivity).supportActionBar?.title = "Add Your New Customer"
    (activity as AppCompatActivity).supportActionBar?.setHomeButtonEnabled(true)
    (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
    (activity as AppCompatActivity).supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_back_arrow)
    toolBar.setNavigationOnClickListener {
        (activity as AppCompatActivity).onBackPressed()
    }
}
}
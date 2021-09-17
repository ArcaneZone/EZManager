package com.example.ezmanager.fragment

import android.app.DatePickerDialog
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.ezmanager.R
import com.example.ezmanager.database.DatabaseHandler
import com.example.ezmanager.model.Transaction
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.textfield.TextInputLayout
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.*


class AddNewTransactionFragment : BottomSheetDialogFragment() {
    val cal= Calendar.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        this.dialog?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)


        val view=inflater.inflate(R.layout.fragment_add_new_transaction, container, false)
        val newTitle:TextInputLayout=view.findViewById(R.id.textNewTransactionTitle)
        val newAmount: TextInputLayout =view.findViewById(R.id.textNewTransactionAmount)
        val newDate: TextView =view.findViewById(R.id.textNewTransactionDate)

        var transactionType:String="F"

        val openDatePicker: Button =view.findViewById(R.id.btnOpenDatePicker)
        val transactionActivity=FinanceFragment()
        val db = DatabaseHandler(activity as Context)
        val radioGroup:RadioGroup=view.findViewById(R.id.radioGroup)

        val addNewTransaction:Button=view.findViewById(R.id.btnAddNewTransaction)
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
        val checkedRadioButtonId = radioGroup.checkedRadioButtonId // Returns View.NO_ID if nothing is checked.
        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            transactionType=when(checkedId){
                R.id.radio_debit->"D"
                R.id.radio_credit->"C"
                else -> "N"
            }
        }



        addNewTransaction.setOnClickListener {
            if (newTitle.editText?.text.toString().trim().isNotEmpty() && newAmount.editText?.text.toString().trim().isNotEmpty() && transactionType!="F"){
                val transaction:Transaction= Transaction( LocalDateTime.now().toString(),newTitle.editText?.text.toString().trim(),newAmount.editText?.text.toString().trim().toInt(),newDate.text.toString().trim(),transactionType)
                db.addTransaction(transaction)
                dismiss()
                requireActivity().supportFragmentManager.beginTransaction().apply {
                    replace(R.id.content,transactionActivity)
                }.commit()
            }
            else{
                if (newTitle.editText?.text.toString().trim().isEmpty())
                    Toast.makeText(requireActivity(),"Title is missing",Toast.LENGTH_SHORT).show()
                else if (newAmount.editText?.text.toString().trim().isEmpty())
                    Toast.makeText(requireActivity(),"Amount is missing",Toast.LENGTH_SHORT).show()
                else if (transactionType=="F")
                    Toast.makeText(requireActivity(),"Credit or Debit is missing",Toast.LENGTH_SHORT).show()
            }

        }
        return view
    }
}


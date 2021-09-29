package com.example.ezmanager.fragment.customer

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.example.ezmanager.R
import com.example.ezmanager.database.DatabaseHandler
import com.example.ezmanager.model.Customer
import com.example.ezmanager.model.Transaction
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class DeleteCustomerFragment(private val deletedCustomer:Customer,context: Context) : BottomSheetDialogFragment(){


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.fragment_delete_transaction, container, false)
        this.dialog?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        val btnOk:Button=view.findViewById(R.id.btnPositive)

        val db = DatabaseHandler(activity as Context)



        btnOk.setOnClickListener {
            db.deleteCustomer(deletedCustomer)
            dismiss()
            val transactionActivity= ExistingCustomerFragment(activity as Context)
            requireActivity().supportFragmentManager.beginTransaction().apply {
                replace(R.id.content,transactionActivity).commit()
            }
            Toast.makeText(context, "removed ${deletedCustomer.name}", Toast.LENGTH_SHORT).show()
        }

        return view
    }


}
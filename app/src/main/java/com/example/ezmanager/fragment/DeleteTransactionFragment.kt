package com.example.ezmanager.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import android.widget.Toast
import com.example.ezmanager.R
import com.example.ezmanager.database.DatabaseHandler
import com.example.ezmanager.model.Transaction
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class DeleteTransactionFragment(private val deletedTransaction:Transaction) : BottomSheetDialogFragment(){


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

        val transactionActivity=FinanceFragment()

        btnOk.setOnClickListener {
            db.deleteTransaction(deletedTransaction)
            dismiss()
            requireActivity().supportFragmentManager.beginTransaction().apply {
                replace(R.id.content,transactionActivity)
            }.commit()
            Toast.makeText(context, "removed ${deletedTransaction.title}", Toast.LENGTH_SHORT).show()
        }

        return view
    }


}
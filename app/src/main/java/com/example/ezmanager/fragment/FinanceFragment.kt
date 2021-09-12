package com.example.ezmanager.fragment

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent

import android.os.Bundle

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ajts.androidmads.library.SQLiteToExcel
import com.example.ezmanager.R
import com.example.ezmanager.activity.SplashActivity
import com.example.ezmanager.adapter.TransactionDashboardAdapter
import com.example.ezmanager.database.DatabaseHandler
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.text.SimpleDateFormat
import java.util.*


class FinanceFragment(context: Context): Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var layoutManager: RecyclerView.LayoutManager
    private lateinit var dashboardAdapter: TransactionDashboardAdapter
    private lateinit var btnOpenBottomSheet:FloatingActionButton
    lateinit var txtTotalAmount:TextView
    private lateinit var btnExport:FloatingActionButton




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view= inflater.inflate(R.layout.fragment_finance, container, false)
        val db = DatabaseHandler(activity as Context)
        var transactionList = db.viewTransactions(activity as Context)
         btnExport=view.findViewById(R.id.btnExportToExcel)
        btnOpenBottomSheet=view.findViewById(R.id.btnOpenBottomSheet)
        layoutManager = LinearLayoutManager(activity)
        recyclerView = view.findViewById(R.id.recyclerView)
        txtTotalAmount=view.findViewById(R.id.txtTotalAmount)
        txtTotalAmount.text="Total Amount Spend: ₹"+db.sumTransaction(context as Context)
        dashboardAdapter = TransactionDashboardAdapter(
            activity as Context,
            transactionList,
        )
        recyclerView.adapter = dashboardAdapter
        recyclerView.layoutManager = layoutManager


        btnOpenBottomSheet.setOnClickListener {
            val bottomsheet=AddNewTransactionFragment()
            bottomsheet.show(requireActivity().supportFragmentManager,"TAG")
        }
        btnExport.setOnClickListener {
                //requireActivity().startActivityFromFragment(this,Intent(requireContext(),SplashActivity::class.java),1)
        }



        return view
    }



    private fun getCurrentDateTime(date:Date): String {
        val myFormat = "MM/dd/yyyy"
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        return sdf.format(date)
    }
}
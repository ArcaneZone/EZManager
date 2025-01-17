package com.example.ezmanager.fragment.finance

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build

import android.os.Bundle
import android.os.Environment

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ajts.androidmads.library.SQLiteToExcel
import com.example.ezmanager.R
import com.example.ezmanager.adapter.TransactionDashboardAdapter
import com.example.ezmanager.database.DatabaseHandler
import java.io.File
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*
import com.ajts.androidmads.library.SQLiteToExcel.ExportListener as ExportListener


class FinanceFragment: Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var layoutManager: RecyclerView.LayoutManager
    private lateinit var dashboardAdapter: TransactionDashboardAdapter
    private lateinit var btnOpenBottomSheet:Button
    private lateinit var txtTotalAmount:TextView
    private lateinit var btnExport:Button
    private lateinit var btnStats:Button
    private lateinit var txtTodayAmount:TextView
    private lateinit var btnSwitch:Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    @RequiresApi(Build.VERSION_CODES.R)
    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view= inflater.inflate(R.layout.fragment_finance, container, false)
        val db = DatabaseHandler(requireActivity())
        val transactionList = db.viewTransactions()

        btnSwitch=view.findViewById(R.id.btnSwitchView)
        btnStats=view.findViewById(R.id.btnOpenFinanceStats)
         btnExport=view.findViewById(R.id.btnExportToExcel)
        btnOpenBottomSheet=view.findViewById(R.id.btnOpenBottomSheet)


        txtTotalAmount=view.findViewById(R.id.txtTotalAmount)
        txtTotalAmount.text= "₹"+db.sumTransaction()
        txtTodayAmount=view.findViewById(R.id.txtTodayAmount)
        txtTodayAmount.text="₹"+db.sumTransactionToday()


        layoutManager = LinearLayoutManager(activity)
        recyclerView = view.findViewById(R.id.recyclerView)
        dashboardAdapter = TransactionDashboardAdapter(
            activity as Context,
            transactionList,
        )
        recyclerView.adapter = dashboardAdapter
        recyclerView.layoutManager = layoutManager



        btnOpenBottomSheet.setOnClickListener {
            val bottomsheet= AddNewTransactionFragment()
            bottomsheet.show(requireActivity().supportFragmentManager,"TAG")
        }
        btnExport.setOnClickListener {
            val dirPath:String=Environment.getStorageDirectory().path+"/ezManager/"
                val file=File(dirPath)
            if (!file.exists())
            {
                file.mkdir()
                Toast.makeText(context,"File Directory Creating",Toast.LENGTH_SHORT).show()
            }

            val sqLiteToExcel= SQLiteToExcel(context,db.databaseName,dirPath)

            sqLiteToExcel.exportAllTables(
                "users.xls",
                object : ExportListener{
                    override fun onStart() {
                        Toast.makeText(context,"Excel File Creating",Toast.LENGTH_SHORT).show()
                    }

                    override fun onCompleted(filePath: String?) {
                        Toast.makeText(context,"Excel File Created",Toast.LENGTH_SHORT).show()
                    }

                    override fun onError(e: Exception?) {
                        Toast.makeText(context,"Error in Excel File Created $e",Toast.LENGTH_SHORT).show()
                    }

                }

            )
        }

        btnStats.setOnClickListener {
            val statsFragment= StatsFragment(requireContext())
            requireActivity().supportFragmentManager.beginTransaction().addToBackStack("$statsFragment").apply {
                replace(R.id.content,statsFragment).commit()
            }
        }

        btnSwitch.setOnClickListener {
            val transactionTableFragment= TransactionTableFragment()
                requireActivity().supportFragmentManager.beginTransaction().addToBackStack("$transactionList").apply {
                replace(R.id.content,transactionTableFragment)
                commit()
            }
        }



        return view
    }



    private fun getCurrentDateTime(date:Date): String {
        val myFormat = "MM/dd/yyyy"
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        return sdf.format(date)
    }
}
package com.example.ezmanager.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TableLayout
import android.widget.TextView
import android.graphics.Color
import android.graphics.Color.*
import android.provider.CalendarContract
import android.view.Gravity
import android.widget.TableRow
import androidx.annotation.ColorInt
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.ezmanager.R
import com.example.ezmanager.database.DatabaseHandler
import com.example.ezmanager.model.Transaction


class TransactionTableFragment : Fragment() {
     lateinit var tableLayout: TableLayout
     lateinit var tableLayoutFinal: TableLayout
    private lateinit var toolBar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(com.example.ezmanager.R.layout.fragment_transaction_table, container, false)
        val db=DatabaseHandler(requireContext())
        val trasactionList=db.viewTransactions()
        toolBar=view.findViewById(R.id.toolBar)
        setToolBar()

        tableLayout=view.findViewById(com.example.ezmanager.R.id.table_main)
        tableLayoutFinal=view.findViewById(R.id.table_result)
        val itr=trasactionList.listIterator()
        var i=1
        while (itr.hasNext()) {
            val tbrow = TableRow(context)

            val transaction:Transaction=itr.next()

            val t1v = TextView(context)
            t1v.text = (i++).toString()
            t1v.gravity = Gravity.CENTER
            tbrow.addView(t1v)
            val t2v = TextView(context)
            t2v.text = transaction.title
            t2v.gravity = Gravity.CENTER
            tbrow.addView(t2v)
            val t3v = TextView(context)
            t3v.text = transaction.amount.toString()
            t3v.gravity = Gravity.CENTER
            tbrow.addView(t3v)
            val t4v = TextView(context)
            t4v.text=transaction.date
            t4v.gravity=Gravity.CENTER
            tbrow.addView(t4v)
            val t5v = TextView(context)
            if (transaction.type=="C")
                t5v.text = "Credited"
            else
                t5v.text="Debited"
            t5v.gravity = Gravity.CENTER
            tbrow.addView(t5v)
            tableLayout.addView(tbrow)

        }
        val tbRowFinal = TableRow(context)
        val fT1v=TextView(context)
        fT1v.text="Total Transaction Amount:"
        fT1v.gravity=Gravity.CENTER
        tbRowFinal.addView(fT1v)

        val fT2v=TextView(context)
        fT2v.text=db.sumTransaction().toString()
        fT2v.gravity=Gravity.LEFT
        tbRowFinal.addView(fT2v)
        tableLayoutFinal.addView(tbRowFinal)



        return view
    }
    private fun setToolBar() {

        (activity as AppCompatActivity).setSupportActionBar(toolBar)
        (activity as AppCompatActivity).supportActionBar?.title = "Switched to Table View"
        (activity as AppCompatActivity).supportActionBar?.setHomeButtonEnabled(true)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (activity as AppCompatActivity).supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_back_arrow)
        toolBar.setNavigationOnClickListener {
            (activity as AppCompatActivity).onBackPressed()
        }
    }
}
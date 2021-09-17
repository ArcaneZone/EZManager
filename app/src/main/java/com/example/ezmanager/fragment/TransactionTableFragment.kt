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
import android.widget.TableRow


class TransactionTableFragment : Fragment() {
     lateinit var tableLayout: TableLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(com.example.ezmanager.R.layout.fragment_transaction_table, container, false)

        tableLayout=view.findViewById(com.example.ezmanager.R.id.table_main)
        val tbrow0 = TableRow(context)
        val tv0 = TextView(context)
        tv0.text = "Sl.No"
        tv0.setTextColor(Color.WHITE)
        tbrow0.addView(tv0)
        val tv1 = TextView(context)
        tv1.text = " Title "
        tv1.setTextColor(Color.WHITE)
        tbrow0.addView(tv1)
        val tv2 = TextView(context)
        tv2.text = "Amount"
        tv2.setTextColor(Color.WHITE)
        tbrow0.addView(tv2)
        val tv3 = TextView(context)
        tableLayout.addView(tbrow0)


        

        return view
    }
    /*fun init() {
        val stk = findViewById(R.id.table_main) as TableLayout
        val tbrow0 = TableRow(this)
        val tv0 = TextView(this)
        tv0.text = " Sl.No "
        tv0.setTextColor(Color.WHITE)
        tbrow0.addView(tv0)
        val tv1 = TextView(this)
        tv1.text = " Product "
        tv1.setTextColor(Color.WHITE)
        tbrow0.addView(tv1)
        val tv2 = TextView(this)
        tv2.text = " Unit Price "
        tv2.setTextColor(Color.WHITE)
        tbrow0.addView(tv2)
        val tv3 = TextView(this)
        tv3.text = " Stock Remaining "
        tv3.setTextColor(Color.WHITE)
        tbrow0.addView(tv3)
        stk.addView(tbrow0)
        for (i in 0..24) {
            val tbrow = TableRow(this)
            val t1v = TextView(this)
            t1v.text = "" + i
            t1v.setTextColor(Color.WHITE)
            t1v.gravity = Gravity.CENTER
            tbrow.addView(t1v)
            val t2v = TextView(this)
            t2v.text = "Product $i"
            t2v.setTextColor(Color.WHITE)
            t2v.gravity = Gravity.CENTER
            tbrow.addView(t2v)
            val t3v = TextView(this)
            t3v.text = "Rs.$i"
            t3v.setTextColor(Color.WHITE)
            t3v.gravity = Gravity.CENTER
            tbrow.addView(t3v)
            val t4v = TextView(this)
            t4v.text = "" + i * 15 / 32 * 10
            t4v.setTextColor(Color.WHITE)
            t4v.gravity = Gravity.CENTER
            tbrow.addView(t4v)
            stk.addView(tbrow)
        }
    }*/


}
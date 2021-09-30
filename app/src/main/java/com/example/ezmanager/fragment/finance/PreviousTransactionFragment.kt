package com.example.ezmanager.fragment.finance

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RelativeLayout
import android.widget.ScrollView
import android.widget.Spinner
import androidx.recyclerview.widget.RecyclerView
import com.example.ezmanager.R
import com.google.android.material.button.MaterialButton

class PreviousTransactionFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_previous_transaction, container, false)

        // Main 3 Buttons
        val btnDefault: MaterialButton = view.findViewById(R.id.btnDefaultTransactions)
        val btnStats: MaterialButton = view.findViewById(R.id.btnStats)
        val btnExport: MaterialButton = view.findViewById(R.id.btnExport)
        // Others
        val defaultScrollView: ScrollView = view.findViewById(R.id.defaultScrollView)
        val statsSpinner: Spinner = view.findViewById(R.id.statsSpinner)
        val btnShowStats: Button = view.findViewById(R.id.btnShowStats)
        val recyclerViewStats: RecyclerView = view.findViewById(R.id.recyclerViewStats)
        val noOptionSelectedView: RelativeLayout =view.findViewById(R.id.noOptionSelectedView)

        btnDefault.setOnClickListener{
            statsSpinner.visibility=View.INVISIBLE
            btnShowStats.visibility=View.INVISIBLE
            recyclerViewStats.visibility=View.INVISIBLE
            noOptionSelectedView.visibility=View.INVISIBLE
        }

        btnStats.setOnClickListener{
            statsSpinner.visibility=View.VISIBLE
            btnShowStats.visibility=View.VISIBLE
            recyclerViewStats.visibility=View.VISIBLE
            defaultScrollView.visibility=View.INVISIBLE
            noOptionSelectedView.visibility=View.INVISIBLE
        }

        btnExport.setOnClickListener{
            defaultScrollView.visibility=View.INVISIBLE
            statsSpinner.visibility=View.INVISIBLE
            btnShowStats.visibility=View.INVISIBLE
            recyclerViewStats.visibility=View.INVISIBLE
            noOptionSelectedView.visibility=View.VISIBLE
        }

        return view
    }
}
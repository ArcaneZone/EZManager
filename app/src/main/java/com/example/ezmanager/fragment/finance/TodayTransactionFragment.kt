package com.example.ezmanager.fragment.finance

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ezmanager.R
import com.example.ezmanager.adapter.TransactionDashboardAdapter
import com.example.ezmanager.database.DatabaseHandler
import com.example.ezmanager.model.Transaction
import java.util.*


class TodayTransactionFragment(context: Context) : Fragment() {
    private lateinit var toolBar: Toolbar
    private lateinit var recyclerView: RecyclerView
    private lateinit var layoutManager: RecyclerView.LayoutManager
    private lateinit var dashboardAdapter: TransactionDashboardAdapter
    val db = DatabaseHandler(context)
    private val transactionList = db.viewTransactions()
    lateinit var searchView: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.fragment_today_transaction, container, false)
        toolBar = view.findViewById(R.id.CustomerAddUserToolBar)
        searchView = view.findViewById(R.id.searchView)
        setToolBar()
        layoutManager = LinearLayoutManager(activity)
        recyclerView = view.findViewById(R.id.recyclerView)
        dashboardAdapter = TransactionDashboardAdapter(
            context as Context,
            transactionList,
        )
        recyclerView.adapter = dashboardAdapter
        recyclerView.layoutManager = layoutManager
        fun filterFun(strTyped: String) {
            val filteredList = arrayListOf<Transaction>()

            for (item in transactionList) {
                if (item.title.toLowerCase(Locale.ROOT)
                        .contains(strTyped.toLowerCase(Locale.ROOT))
                ) {
                    filteredList.add(item)
                }
            }

            if (filteredList.size == 0) {
                //
            } else {
                //
            }

            dashboardAdapter.filterList(filteredList)

        }

        searchView.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(strTyped: Editable?) {
                filterFun(strTyped.toString())
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }
        })


        return view
    }
    private fun setToolBar() {

        (activity as AppCompatActivity).setSupportActionBar(toolBar)
        (activity as AppCompatActivity).supportActionBar?.title = "Transactions"
        (activity as AppCompatActivity).supportActionBar?.setHomeButtonEnabled(true)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (activity as AppCompatActivity).supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_back_arrow)
        toolBar.setNavigationOnClickListener {
            (activity as AppCompatActivity).onBackPressed()
        }
    }
}
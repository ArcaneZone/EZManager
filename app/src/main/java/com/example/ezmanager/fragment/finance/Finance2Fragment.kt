package com.example.ezmanager.fragment.finance

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import com.example.ezmanager.R
import com.example.ezmanager.fragment.worker.AddWorker
import com.example.ezmanager.fragment.worker.PermanentWorker
import com.example.ezmanager.fragment.worker.TemporaryWorker


class Finance2Fragment : Fragment() {
    private lateinit var showHistory: CardView
    private lateinit var todaytransaction: CardView
    private lateinit var addMultipleCard: CardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.fragment_finance2, container, false)
        showHistory = view.findViewById(R.id.transactionHistory)
        todaytransaction = view.findViewById(R.id.transactionToday)
        addMultipleCard = view.findViewById(R.id.transactionAddMultiple)

        showHistory.setOnClickListener {
            val historyFragment = PreviousTransactionFragment()
            requireActivity().supportFragmentManager.beginTransaction()
                .addToBackStack("$historyFragment").apply {
                    replace(R.id.content, historyFragment).commit()
                }
        }

        todaytransaction.setOnClickListener {
            val todayTransaction = TodayTransactionFragment(requireActivity())
            requireActivity().supportFragmentManager.beginTransaction()
                .addToBackStack("$todayTransaction").apply {
                    replace(R.id.content, todayTransaction).commit()
                }
        }

        addMultipleCard.setOnClickListener {
            val addMultipleTransactionFragment= AddMultipleTransactionFragment()
            requireActivity().supportFragmentManager.beginTransaction()
                .addToBackStack("$addMultipleTransactionFragment").apply {
                    replace(R.id.content,addMultipleTransactionFragment).commit()
                }
        }
        return view
    }

}
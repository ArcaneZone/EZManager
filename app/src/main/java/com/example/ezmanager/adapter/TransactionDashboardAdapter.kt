package com.example.ezmanager.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ezmanager.R
import com.example.ezmanager.database.DatabaseHandler
import com.example.ezmanager.model.Transaction
import android.app.Activity
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.ezmanager.fragment.DeleteTransactionFragment
import com.example.ezmanager.fragment.FinanceFragment


class TransactionDashboardAdapter(val context: Context, private var itemList: List<Transaction>):
RecyclerView.Adapter<TransactionDashboardAdapter.ItemViewHolder>(){
    private val db = DatabaseHandler(context)
    class ItemViewHolder (view: View) : RecyclerView.ViewHolder(view){
        val transactionTitle:TextView=view.findViewById(R.id.transactionTitle)
        val transactionDate:TextView=view.findViewById(R.id.transactionDate)
        val transactionAmount:TextView=view.findViewById(R.id.transactionAmt)
        val deleteTransaction:ImageView=view.findViewById(R.id.btnDeleteTransaction)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.single_transaction_layout, parent, false)

        return ItemViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val transaction=itemList[position]
        holder.transactionTitle.text=transaction.title
        holder.transactionDate.text= transaction.date
        holder.transactionAmount.text="₹${transaction.amount}"
        holder.deleteTransaction.setOnClickListener {

                val deleteTransactionFragment=DeleteTransactionFragment(transaction)
                deleteTransactionFragment.show((context as FragmentActivity).supportFragmentManager,"TAG")
            }


        }


    override fun getItemCount(): Int {
        return itemList.size
    }
}

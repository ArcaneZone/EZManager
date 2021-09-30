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
import android.widget.*
import androidx.fragment.app.FragmentActivity
import com.example.ezmanager.fragment.finance.DeleteTransactionFragment
import java.util.*
import kotlin.collections.ArrayList


class TransactionDashboardAdapter(val context: Context, private var itemList: List<Transaction>):
RecyclerView.Adapter<TransactionDashboardAdapter.ItemViewHolder>(){
    var countryFilterList = listOf<Transaction>()
    init {
        countryFilterList = itemList
    }
    private val db = DatabaseHandler(context)
    class ItemViewHolder (view: View) : RecyclerView.ViewHolder(view){
        val transactionTitle:TextView=view.findViewById(R.id.transactionTitle)
        val transactionDate:TextView=view.findViewById(R.id.transactionDate)
        val transactionAmount:TextView=view.findViewById(R.id.transactionAmt)
        val deleteTransaction:ImageView=view.findViewById(R.id.btnDeleteTransaction)
        val transactionType:ImageView=view.findViewById(R.id.imgTransactionType)
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

                val deleteTransactionFragment= DeleteTransactionFragment(transaction)
                deleteTransactionFragment.show((context as FragmentActivity).supportFragmentManager,"TAG")
            }
        if(transaction.type == "D")
        holder.transactionType.setImageResource(R.drawable.img)
        else if(transaction.type == "C")
            holder.transactionType.setImageResource(R.drawable.img_1)
        }

    fun filterList(filteredList: ArrayList<Transaction>) {
        itemList = filteredList
        notifyDataSetChanged()
    }


    override fun getItemCount(): Int {
        return itemList.size
    }
}

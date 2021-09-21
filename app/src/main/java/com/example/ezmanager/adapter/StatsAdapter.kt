package com.example.ezmanager.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ezmanager.R
import com.example.ezmanager.model.stats

class StatsAdapter(val context: Context, private var itemList: List<stats>):
    RecyclerView.Adapter<StatsAdapter.ItemViewHolder>(){

    class ItemViewHolder (view: View) : RecyclerView.ViewHolder(view){
        val titleDate:TextView=view.findViewById(R.id.txtDateTitle)
        val totalTransaction:TextView=view.findViewById(R.id.txtStatsTotal)
        val transactionCredit:TextView=view.findViewById(R.id.txtStatsCredit)
        val trasactionDebit:TextView=view.findViewById(R.id.txtStatsDebit)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.single_stat_list_item, parent, false)

        return ItemViewHolder(view)
    }


    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item=itemList[position]
        holder.titleDate.text=item.dateTitle
        holder.totalTransaction.text= item.Total.toString()
        holder.transactionCredit.text=item.Credit.toString()
        holder.trasactionDebit.text=item.Debit.toString()
    }

}
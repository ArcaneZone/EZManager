package com.example.ezmanager.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ezmanager.R
import com.example.ezmanager.fragment.customer.CustomerFragment
import com.example.ezmanager.model.Customer
import com.example.ezmanager.model.Transaction

class CustomerAdapter(val context: Context, private var itemList: List<Customer>):
    RecyclerView.Adapter<CustomerAdapter.ItemViewHolder>() {
    class ItemViewHolder (view: View) : RecyclerView.ViewHolder(view){
        val customerName: TextView =view.findViewById(R.id.txtCustomerName)
        val customerPhone: TextView =view.findViewById(R.id.txtCustomerAddress)
        /*val transactionAmount: TextView =view.findViewById(R.id.transactionAmt)
        val deleteTransaction: ImageView =view.findViewById(R.id.btnDeleteTransaction)
        val transactionType: ImageView =view.findViewById(R.id.imgTransactionType)*/
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CustomerAdapter.ItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.single_item_customer_list, parent, false)

        return CustomerAdapter.ItemViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: CustomerAdapter.ItemViewHolder,
        position: Int
    ) {
        val customer=itemList[position]
        holder.customerName.text=customer.name
        holder.customerPhone.text=customer.phone
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}
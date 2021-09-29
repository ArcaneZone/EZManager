package com.example.ezmanager.adapter

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ezmanager.R
import com.example.ezmanager.fragment.customer.CustomerFragment
import com.example.ezmanager.fragment.customer.DeleteCustomerFragment
import com.example.ezmanager.fragment.customer.DetailedCustomerFragment
import com.example.ezmanager.fragment.finance.DeleteTransactionFragment
import com.example.ezmanager.model.Customer
import com.example.ezmanager.model.Transaction

class CustomerAdapter(val context: Context, private var itemList: List<Customer>):
    RecyclerView.Adapter<CustomerAdapter.ItemViewHolder>() {
    class ItemViewHolder (view: View) : RecyclerView.ViewHolder(view){
        val customerName: TextView =view.findViewById(R.id.txtCustomerName)
        val customerAddress: TextView =view.findViewById(R.id.txtCustomerAddress)
        val customerPhone: TextView =view.findViewById(R.id.txtCustomerPhone)
        val customerDelete:ImageView=view.findViewById(R.id.customerDelete)
        val singleCardView:CardView=view.findViewById(R.id.CustomerLlContent)
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
        holder.customerAddress.text=customer.address
        holder.customerDelete.setOnClickListener {
            val deleteCustomerFragment= DeleteCustomerFragment(customer,context)
            deleteCustomerFragment.show((context as FragmentActivity).supportFragmentManager,"TAG")
        }
        holder.singleCardView.setOnClickListener {
            val nextfrag=DetailedCustomerFragment(customer)
            val activity=context as AppCompatActivity
            activity.supportFragmentManager.beginTransaction().apply {
                replace(R.id.content,nextfrag).commit()
            }
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}
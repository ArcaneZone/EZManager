package com.example.ezmanager.fragment.customer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ezmanager.R
import com.google.android.material.card.MaterialCardView


class CustomerFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    lateinit var  addNewCustomerCard:MaterialCardView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.fragment_customer, container, false)
        addNewCustomerCard=view.findViewById(R.id.CustomerAddUserCard)

        addNewCustomerCard.setOnClickListener {
        val addUserFragment= AddUserFragment()
            requireActivity().supportFragmentManager.beginTransaction().addToBackStack("$addUserFragment").apply {
                replace(R.id.content,addUserFragment).commit()
            }
        }


        return view
    }

}
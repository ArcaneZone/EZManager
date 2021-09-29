package com.example.ezmanager.fragment.customer

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ezmanager.HomeActivity
import com.example.ezmanager.R


class NewCustomerAddedFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.fragment_new_customer_added, container, false)
        Handler(Looper.getMainLooper()).postDelayed({
            val fragment=CustomerFragment()
            val intent = Intent (requireActivity(), HomeActivity::class.java)
            startActivity(intent)   // start the home_activity
            requireActivity().finish()
        }, 3000)

        return view
    }

}
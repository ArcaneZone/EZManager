package com.example.ezmanager.fragment.customer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.ezmanager.R


class AddUserFragment : Fragment() {
    private lateinit var toolBar: Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.fragment_add_user, container, false)

        toolBar = view.findViewById(R.id.CustomerAddUserToolBar)

        setToolBar()
        return view
    }


private fun setToolBar() {

    (activity as AppCompatActivity).setSupportActionBar(toolBar)
    (activity as AppCompatActivity).supportActionBar?.title = "Add Your New Customer"
    (activity as AppCompatActivity).supportActionBar?.setHomeButtonEnabled(true)
    (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
    (activity as AppCompatActivity).supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_back_arrow)
    toolBar.setNavigationOnClickListener {
        (activity as AppCompatActivity).onBackPressed()
    }
}
}
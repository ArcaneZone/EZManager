package com.example.ezmanager.fragment

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RelativeLayout
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.ezmanager.R


class StatsFragment : Fragment() {

    private lateinit var toolBar: Toolbar
    private lateinit var spinner: Spinner
    private lateinit var btnStatsGO:Button

    private lateinit var NoOptionSelectedView:RelativeLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.fragment_stats, container, false)
        toolBar = view.findViewById(R.id.toolBar)
        spinner=view.findViewById(R.id.statsSpinner)
        btnStatsGO=view.findViewById(R.id.btnShowStats)

        NoOptionSelectedView=view.findViewById(R.id.noOptionSelectedView)
        NoOptionSelectedView.visibility=View.VISIBLE

        val spinnerList=resources.getStringArray(R.array.stats_menu)
        btnStatsGO.setOnClickListener {
            when(spinner.selectedItem){
                spinnerList[0]->{
                    Toast.makeText(requireContext(),"No Category Selected",Toast.LENGTH_SHORT).show()
                    NoOptionSelectedView.visibility=View.VISIBLE
                }
                spinnerList[1]->{
                    NoOptionSelectedView.visibility=View.INVISIBLE

                }
                spinnerList[2]->{
                    NoOptionSelectedView.visibility=View.INVISIBLE
                }


            }
        }


        setToolBar()


        return view
    }
    private fun setToolBar() {

        (activity as AppCompatActivity).setSupportActionBar(toolBar)
        (activity as AppCompatActivity).supportActionBar?.title = "Statistics"
        (activity as AppCompatActivity).supportActionBar?.setHomeButtonEnabled(true)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (activity as AppCompatActivity).supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_back_arrow)
        toolBar.setNavigationOnClickListener {
            (activity as AppCompatActivity).onBackPressed()
        }
    }



}
package com.example.ezmanager.fragment.finance

import android.content.Context
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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ezmanager.R
import com.example.ezmanager.adapter.StatsAdapter
import com.example.ezmanager.database.DatabaseHandler
import com.example.ezmanager.model.CustomDate
import com.example.ezmanager.model.stats
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class StatsFragment(context: Context) : Fragment() {

    private lateinit var toolBar: Toolbar
    private lateinit var spinner: Spinner
    private lateinit var btnStatsGO:Button

    private lateinit var NoOptionSelectedView:RelativeLayout
    private lateinit var recyclerView: RecyclerView
    private lateinit var layoutManager: RecyclerView.LayoutManager
    private lateinit var dashboardAdapter: StatsAdapter
    val cal= Calendar.getInstance()
    private val Weekday= arrayOf("Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday")





    private val db=DatabaseHandler(context)
    private val allTransaction =db.viewTransactions()

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
        layoutManager = LinearLayoutManager(activity)
        recyclerView = view.findViewById(R.id.recyclerViewStats)
        recyclerView.setNestedScrollingEnabled(false);




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

                    dashboardAdapter = StatsAdapter(requireContext(),statsWeek())
                    println("stats are ${statsWeek()}")
                    recyclerView.adapter = dashboardAdapter
                    recyclerView.layoutManager = layoutManager

                }
                spinnerList[2]->{
                    NoOptionSelectedView.visibility=View.INVISIBLE
                    dashboardAdapter = StatsAdapter(requireContext(),statsMonth())
                    println("stats are ${statsWeek()}")
                    recyclerView.adapter = dashboardAdapter
                    recyclerView.layoutManager = layoutManager

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
    private fun statsWeek():List<stats>{
        val statList=ArrayList<stats>()

            val currentDate:String=getCurrentDateTime(cal.time)
        println("current date is $currentDate")
            val currentDateObject=dateToNum(currentDate)
        println("current date object is $currentDateObject")
            for (i in 6 downTo 0)
            {
                val day=String.format("%02d",currentDateObject.dd-i)
                val month=String.format("%02d",currentDateObject.mm)
                val year=String.format("%02d",currentDateObject.yy)
                val dateC=Calendar.getInstance()
                dateC.set(currentDateObject.yy,currentDateObject.mm,currentDateObject.dd-i)

                val date= "$day-$month-$year"
                val allTransactionDate=db.sumTransactionDate(date)
                println("Date : $date")

                statList.add(stats(date,allTransactionDate.total,allTransactionDate.credit,allTransactionDate.debit))
            }
        return statList
    }
    private fun statsMonth():List<stats>{
       val statList=ArrayList<stats>()

        val firstDate=Calendar.getInstance()
        firstDate.set(Calendar.DAY_OF_MONTH,1)
        val firstDateDay=firstDate.get(Calendar.DAY_OF_WEEK)
        val lastDate=Calendar.getInstance()
        lastDate.set(Calendar.DAY_OF_MONTH,8-firstDateDay)
        for (i in 0..4)
        {
            val weekday1String=getCurrentDateTime(firstDate.time)
            val weekDayLastString=getCurrentDateTime(lastDate.time)
            val all=db.sumTransactionDateRange(weekday1String,weekDayLastString)
            statList.add(stats("$weekday1String-$weekDayLastString",all.total,all.credit,all.debit))
            firstDate.set(Calendar.DAY_OF_MONTH,lastDate.get(Calendar.DAY_OF_MONTH)+1)
            lastDate.set(Calendar.DAY_OF_MONTH,lastDate.get(Calendar.DAY_OF_MONTH)+7)
        }


        return statList
    }
    private fun dateToNum(date: String): CustomDate {

        val d1 = date[0].toString().toInt()
        val d2 = date[1].toString().toInt()
        val day = d1 * 10 + d2
        val m1 = date[3].toString().toInt()
        val m2 = date[4].toString().toInt()
        val month = m1 * 10 + m2
        val y1 = date[6].toString().toInt()
        val y2 = date[7].toString().toInt()
        val y3=  date[8].toString().toInt()
        val y4=  date[9].toString().toInt()
        val year = y1*1000+y2*100+y3*10+y4


        return CustomDate(day, month, year)
    }
    private fun numToDate(customDate: CustomDate):String{
        val day=String.format("%02d",customDate.dd)
        val month=String.format("%02d",customDate.mm)
        val year=String.format("%02d",customDate.yy)
        return "$day-$month-$year"
    }
    private fun getCurrentDateTime(date: Date): String {
        val myFormat = "dd-MM-yyyy"
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        return sdf.format(date)
    }




}

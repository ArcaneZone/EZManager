package com.example.ezmanager.fragment.finance

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ezmanager.R
import com.example.ezmanager.adapter.StatsAdapter
import com.example.ezmanager.database.DatabaseHandler
import com.example.ezmanager.model.CustomDate
import com.example.ezmanager.model.stats
import com.google.android.material.button.MaterialButton
import org.apache.poi.ss.formula.functions.Finance
import java.text.SimpleDateFormat
import java.util.*
import android.widget.ArrayAdapter




class PreviousTransactionFragment(context: Context) : Fragment() {
    private lateinit var spinner: Spinner
    private lateinit var btnStatsGO:Button

    private lateinit var NoOptionSelectedView:RelativeLayout
    private lateinit var recyclerView: RecyclerView
    private lateinit var layoutManager: RecyclerView.LayoutManager
    private lateinit var dashboardAdapter: StatsAdapter
    val cal= Calendar.getInstance()
    private val Weekday= arrayOf("Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday")

    private val db= DatabaseHandler(context)
    private val allTransaction =db.viewTransactions()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_previous_transaction, container, false)

        spinner=view.findViewById(R.id.statsSpinner)
        btnStatsGO=view.findViewById(R.id.btnShowStats)
        layoutManager = LinearLayoutManager(activity)
        recyclerView = view.findViewById(R.id.recyclerViewStats)
        recyclerView.setNestedScrollingEnabled(false);

        NoOptionSelectedView=view.findViewById(R.id.noOptionSelectedView)
        NoOptionSelectedView.visibility=View.VISIBLE

        val spinnerList=resources.getStringArray(R.array.stats_menu)
        val adapter: ArrayAdapter<*> =
            ArrayAdapter.createFromResource(requireContext(), R.array.stats_menu, R.layout.spinner_item)
        spinner.adapter = adapter


        btnStatsGO.setOnClickListener {
            when(spinner.selectedItem){
                spinnerList[0]->{
                    Toast.makeText(requireContext(),"No Category Selected", Toast.LENGTH_SHORT).show()
                    NoOptionSelectedView.visibility=View.VISIBLE
                    recyclerView.visibility=View.INVISIBLE
                }
                spinnerList[1]->{
                    NoOptionSelectedView.visibility=View.INVISIBLE
                    recyclerView.visibility=View.VISIBLE
                    dashboardAdapter = StatsAdapter(requireContext(),statsWeek())
                    println("stats are ${statsWeek()}")
                    recyclerView.adapter = dashboardAdapter
                    recyclerView.layoutManager = layoutManager

                }
                spinnerList[2]->{
                    NoOptionSelectedView.visibility=View.INVISIBLE
                    recyclerView.visibility=View.VISIBLE
                    dashboardAdapter = StatsAdapter(requireContext(),statsMonth())
                    println("stats are ${statsWeek()}")
                    recyclerView.adapter = dashboardAdapter
                    recyclerView.layoutManager = layoutManager

                }


            }
        }



        return view
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
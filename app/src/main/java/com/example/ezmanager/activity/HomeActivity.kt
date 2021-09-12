package com.example.ezmanager

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.ezmanager.fragment.CustomerFragment
import com.example.ezmanager.fragment.DashboardFragment
import com.example.ezmanager.fragment.FinanceFragment
import com.example.ezmanager.fragment.WorkerFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {
    private val dashboardFragment=DashboardFragment()
    private val customerFragment=CustomerFragment()
    private val workerFragment= WorkerFragment()
    private val financeFragment= FinanceFragment(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        openDashBoard()
        var bottomNavigationView:BottomNavigationView=findViewById(R.id.bottom_navigation)
        bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.customerDashboard->setCurrentFragment(customerFragment)
                R.id.workerDashboard->setCurrentFragment(workerFragment)
                R.id.financeDashboard->setCurrentFragment(financeFragment)
            }
            true
        }



    }
    private fun openDashBoard()
    {
        setCurrentFragment(dashboardFragment)
    }
    public fun setCurrentFragment(fragment: Fragment)=
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.content,fragment)
            commit()
        }

}
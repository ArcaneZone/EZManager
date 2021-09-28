package com.example.ezmanager

import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.ezmanager.fragment.finance.CustomerFragment
import com.example.ezmanager.fragment.finance.DashboardFragment
import com.example.ezmanager.fragment.finance.FinanceFragment
import com.example.ezmanager.fragment.worker.WorkerFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.core.app.ActivityCompat

import android.content.pm.PackageManager

import android.app.Activity




class HomeActivity : AppCompatActivity() {
    private val REQUEST_EXTERNAL_STORAGE = 1
    private val PERMISSIONS_STORAGE = arrayOf(
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    )
    private val dashboardFragment= DashboardFragment()
    private val customerFragment= CustomerFragment()
    private val workerFragment= WorkerFragment()
    private val financeFragment= FinanceFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        verifyStoragePermissions(this)

        openDashBoard()
        val bottomNavigationView:BottomNavigationView=findViewById(R.id.bottom_navigation)
        bottomNavigationView.setOnItemSelectedListener {
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
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.content,dashboardFragment)
            commit()
    }
    }

    private fun setCurrentFragment(fragment: Fragment)=
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.content,fragment)
            commit()
        }
    private fun verifyStoragePermissions(activity: Activity?) {
        // Check if we have write permission
        val permission = ActivityCompat.checkSelfPermission(
            activity!!,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                activity,
                PERMISSIONS_STORAGE,
                REQUEST_EXTERNAL_STORAGE
            )
        }
    }
    override fun onBackPressed() {
        val count = supportFragmentManager.backStackEntryCount
        if (count == 0) {
            super.onBackPressed()

        } else {
            supportFragmentManager.popBackStack()
        }
    }


}
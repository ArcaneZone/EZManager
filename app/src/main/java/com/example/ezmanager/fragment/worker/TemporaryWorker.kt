package com.example.ezmanager.fragment.worker

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ezmanager.R
import com.example.ezmanager.adapter.TransactionDashboardAdapter
import com.example.ezmanager.adapter.WorkerAdapter
import com.example.ezmanager.database.DatabaseHandler
import com.example.ezmanager.model.Worker

class TemporaryWorker(context: Context) : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var layoutManager: RecyclerView.LayoutManager
    private lateinit var dashboardAdapter: WorkerAdapter
    val db=DatabaseHandler(context)
    val twlist=db.viewWorker()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_temporary_worker, container, false)

        layoutManager = LinearLayoutManager(activity)
        recyclerView = view.findViewById(R.id.tWorkerRecyclerView)
        dashboardAdapter = WorkerAdapter(
            activity as Context,
            twlist,
        )
        recyclerView.adapter = dashboardAdapter
        recyclerView.layoutManager = layoutManager

        return view
    }

}
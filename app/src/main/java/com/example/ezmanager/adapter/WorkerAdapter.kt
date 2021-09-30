package com.example.ezmanager.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ezmanager.R
import com.example.ezmanager.database.DatabaseHandler
import com.example.ezmanager.model.Transaction
import com.example.ezmanager.model.Worker
import org.w3c.dom.Text

class WorkerAdapter(val context: Context, private var itemList: List<Worker>):
    RecyclerView.Adapter<WorkerAdapter.ItemViewHolder>(){
    class ItemViewHolder (view: View) : RecyclerView.ViewHolder(view){
        val worker: TextView =view.findViewById(R.id.DailyWorkerName)
        val tworkerwage: TextView = view.findViewById(R.id.dailyWorkerWage)
        val pworker: TextView = view.findViewById(R.id.PermanentWorkerName)
        val pworkerdate: TextView = view.findViewById(R.id.PermanentWorkerDate)
        val btnDelete: ImageView = view.findViewById(R.id.deleteWorker)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): WorkerAdapter.ItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.single_temporaryworker_layout, parent, false)

        return WorkerAdapter.ItemViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: WorkerAdapter.ItemViewHolder,
        position: Int
    ) {
        val worker=itemList[position]
        holder.worker.text=worker.name
        holder.btnDelete.setOnClickListener{

        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}
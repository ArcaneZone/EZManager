package com.example.ezmanager.fragment.worker

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.cardview.widget.CardView
import com.example.ezmanager.R

class WorkerFragment : Fragment() {

    private lateinit var button1: CardView
    private lateinit var button2: CardView
    private lateinit var button3: CardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_worker, container, false)

        button1 = view.findViewById(R.id.worker_card1)
        button2 = view.findViewById(R.id.worker_card2)
        button3 = view.findViewById(R.id.worker_card3)

        button1.setOnClickListener {
            val temporaryWorker = TemporaryWorker()
            requireActivity().supportFragmentManager.beginTransaction()
                .addToBackStack("$temporaryWorker").apply {
                    replace(R.id.content, temporaryWorker).commit()
                }
        }

        button2.setOnClickListener {
            val permanentWorker = PermanentWorker()
            requireActivity().supportFragmentManager.beginTransaction()
                .addToBackStack("$permanentWorker").apply {
                    replace(R.id.content, permanentWorker).commit()
                }
        }

        button3.setOnClickListener {
            val addWorker = AddWorker()
            requireActivity().supportFragmentManager.beginTransaction()
                .addToBackStack("$addWorker").apply {
                    replace(R.id.content,addWorker).commit()
                }
        }

            return view
        }
}
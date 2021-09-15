package com.example.ezmanager.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Spinner
import androidx.fragment.app.DialogFragment
import com.example.ezmanager.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class HistoryDialogueFragment : BottomSheetDialogFragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.fragment_history_dialogue, container, false)
        val spinner:Spinner=view.findViewById(R.id.spinner)
        val btnGO:Button=view.findViewById(R.id.btnGo)
        btnGO.setOnClickListener {
            println("spinner selected : ${spinner.selectedItem}")
        }

        return view
    }

}
package com.example.ezmanager.fragment.finance

import android.os.Bundle
import android.os.Environment
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.ajts.androidmads.library.SQLiteToExcel
import com.example.ezmanager.R
import com.example.ezmanager.database.DatabaseHandler
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.io.File
import java.lang.Exception

class ExportToExcelLatest : BottomSheetDialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_export_to_excel_latest, container, false)

        val btnExportDownload: Button = view.findViewById(R.id.btnExportDownload)
        val db = DatabaseHandler(requireActivity())

        btnExportDownload.setOnClickListener{
            val dirPath:String= Environment.getStorageDirectory().path+"/ezManager/"
            val file= File(dirPath)
            if (!file.exists())
            {
                file.mkdir()
                Toast.makeText(context,"File Directory Creating", Toast.LENGTH_SHORT).show()
            }

            val sqLiteToExcel= SQLiteToExcel(context,db.databaseName,dirPath)

            sqLiteToExcel.exportAllTables(
                "users.xls",
                object : SQLiteToExcel.ExportListener {
                    override fun onStart() {
                        Toast.makeText(context,"Excel File Creating", Toast.LENGTH_SHORT).show()
                    }

                    override fun onCompleted(filePath: String?) {
                        Toast.makeText(context,"Excel File Created", Toast.LENGTH_SHORT).show()
                    }

                    override fun onError(e: Exception?) {
                        Toast.makeText(context,"Error in Excel File Created $e", Toast.LENGTH_SHORT).show()
                    }

                }

            )
        }


        return view
    }
}
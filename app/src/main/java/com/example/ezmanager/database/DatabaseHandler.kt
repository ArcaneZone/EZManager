package com.example.ezmanager.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import com.example.ezmanager.model.Transaction


class DatabaseHandler(context: Context): SQLiteOpenHelper(context,DATABASE_NAME,null,DATABASE_VERSION) {

    companion object {
        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "TransactionDatabase"
        private val TABLE_TRANSACTION = "TransactionTable"
        private val KEY_ID = "id"
        private val KEY_TITLE = "title"
        private val KEY_DATE = "date"
        private val KEY_AMOUNT="amount"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_TRANSACTION_TABLE = ("CREATE TABLE $TABLE_TRANSACTION($KEY_ID TEXT PRIMARY KEY,$KEY_TITLE TEXT,$KEY_AMOUNT INTEGER,$KEY_DATE TEXT)")
        db.execSQL(CREATE_TRANSACTION_TABLE);
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_TRANSACTION")
        onCreate(db)
    }

    //method to insert data
    fun addTransaction(t:Transaction):Long{
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_ID, t.id)
        contentValues.put(KEY_TITLE, t.title) // EmpModelClass Name
        contentValues.put(KEY_AMOUNT,t.amount )
        contentValues.put(KEY_DATE,t.date )
        // EmpModelClass Phone
        // Inserting Row
        val success = db.insert(TABLE_TRANSACTION, null, contentValues)
        //2nd argument is String containing nullColumnHack
        db.close() // Closing database connection
        return success
    }

    //method to read data
    fun viewTransactions(context: Context):List<Transaction>{
        val tList:ArrayList<Transaction> = ArrayList<Transaction>()
        val selectQuery = "SELECT  * FROM $TABLE_TRANSACTION "
        val db = this.readableDatabase
        var cursor: Cursor? = null
        try{

            cursor = db.rawQuery(selectQuery, null)
        }catch (e: SQLiteException) {
            db.execSQL(selectQuery)
            return ArrayList()
        }
        var tId: String
        var tTitle: String
        var tDate: String
        var tAmount:Int
        if (cursor.moveToFirst()) {
            do {
                tId = cursor.getString(cursor.getColumnIndexOrThrow("id"))
                tTitle= cursor.getString(cursor.getColumnIndexOrThrow("title"))
                tAmount=cursor.getInt(cursor.getColumnIndexOrThrow("amount"))
                tDate = cursor.getString(cursor.getColumnIndexOrThrow("date"))
                val emp= Transaction(tId,tTitle,tAmount,tDate)
                tList.add(emp)
            } while (cursor.moveToNext())
        }
        return tList
    }
    fun deleteTransaction(t: Transaction){
        val db = this.writableDatabase
        db.execSQL("DELETE FROM " + TABLE_TRANSACTION+ " WHERE "+ KEY_ID+"='"+t.id+"'")
    }

    fun sumTransaction(context: Context):Int{
        var totalSum=0;
        val selectQuery = "SELECT  * FROM $TABLE_TRANSACTION "
        val db = this.readableDatabase
        var cursor: Cursor? = null
        try{

            cursor = db.rawQuery(selectQuery, null)
        }catch (e: SQLiteException) {
            db.execSQL(selectQuery)
            return 0;
        }
        var tAmount:Int
        if (cursor.moveToFirst()) {
            do {
                tAmount=cursor.getInt(cursor.getColumnIndexOrThrow("amount"))
                totalSum+=tAmount
            } while (cursor.moveToNext())
        }
        return totalSum
    }
   /*
    private fun ExportData(context: Context) {

        //CHECK IF YOU HAVE WRITE PERMISSIONS OR RETURN
        val permission = ActivityCompat.checkSelfPermission(
            context,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
        if (permission != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(context, "Storage permissions not granted", Toast.LENGTH_SHORT)
                .show()
            return
        }

//get database object
        val database = this.writableDatabase

//delete all entries in the second table
        database.delete("Table2", null, null)

//Create a cursor of the main database with your filters and sort order applied
        val cursor: Cursor? = context.getContentResolver().query(
            uri,
            projections,
            selection,
            args,
            sortOrder
        )

//loop through cursor and add entries from first table to second table
        try {
            while (cursor.moveToNext()) {
                val ColumnOneIndex = cursor.getString(cursor.getColumnIndexOrThrow("COLUMN_ONE"))
                val ColumnTwoIndex = cursor.getString(cursor.getColumnIndexOrThrow("COLUMN_TWO"))
                val ColumnThreeIndex =
                    cursor.getString(cursor.getColumnIndexOrThrow("COLUMN_THREE"))

                //add entries from table one into the table two
                val values = ContentValues()
                values.put("TABLE2_COLUMN_1", ColumnOneIndex)
                values.put("TABLE2_COLUMN_2", ColumnTwoIndex)
                values.put("TABLE2_COLUMN_3", ColumnThreeIndex)
                database.insert("table2", null, values)
            }
        } finally {
            //close cursor after looping is complete
            cursor.close()
        }

        //create a string for where you want to save the excel file
        val savePath = Environment.getExternalStorageDirectory().toString() + "/excelfileTemp"
        val file = File(savePath)
        if (!file.exists()) {
            file.mkdirs()
        }
        //create the sqLiteToExcel object
        val sqLiteToExcel = SQLiteToExcel(getContext(), "databasefile.db", savePath)

//use sqLiteToExcel object to create the excel file
        sqLiteToExcel.exportSingleTable("table2", "excelfilename.xls", object : ExportListener {
            override fun onStart() {}
            override fun onCompleted(filePath: String) {
                //now attach the excel file created and be directed to email activity
                val newPath: Uri = Uri.parse("file://$savePath/excelfilename.xls")
                val builder = VmPolicy.Builder()
                StrictMode.setVmPolicy(builder.build())
                val emailintent = Intent(Intent.ACTION_SEND)
                emailintent.type = "application/vnd.ms-excel"
                emailintent.putExtra(Intent.EXTRA_SUBJECT, "Subject")
                emailintent.putExtra(Intent.EXTRA_TEXT, "I'm email body.")
                emailintent.putExtra(Intent.EXTRA_STREAM, newPath)
                startActivity(Intent.createChooser(emailintent, "Send Email"))
            }

            override fun onError(e: Exception) {
                println("Error msg: $e")
                Toast.makeText(, "Failed to Export data", Toast.LENGTH_SHORT).show()
            }
        })
    }*/
}

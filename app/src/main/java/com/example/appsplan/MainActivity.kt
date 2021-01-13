package com.example.appsplan

import android.content.Intent
import android.os.Bundle
import android.provider.BaseColumns
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import database.DbContract
import database.ReaderDbHelper
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),DataAdapter.Callback {

    lateinit var dbHelper: ReaderDbHelper


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        dbHelper = ReaderDbHelper(this)

//       getDataDummy()
        initListener()

    }

    private fun getData() {

        val db = dbHelper.readableDatabase

        val projection = arrayOf(BaseColumns._ID,DbContract.dataEntry.COLUMN_NAME_TITLE
        ,DbContract.dataEntry.COLUMN_NAME_DESC  )


        val sortOrder = "${DbContract.dataEntry.COLUMN_NAME_TITLE} DESC"

        var curso = db.query(
                DbContract.dataEntry.TABLE_NAME,
                projection,

                null,
                null,
                null,
                null,
                sortOrder
        )

        val dataList = mutableListOf<DataModel>()
        with(curso){
            while (moveToNext()){
                val id = getLong(getColumnIndex(BaseColumns._ID))
                val title = getString(getColumnIndexOrThrow(DbContract.dataEntry.COLUMN_NAME_TITLE))
                val desc = getString(getColumnIndexOrThrow(DbContract.dataEntry.COLUMN_NAME_DESC))
                dataList.add(DataModel(id,title,desc))
            }
        }
        var dataAdapter = DataAdapter(this)
        rv_data.apply {

            var linearLayoutManager = LinearLayoutManager(context)
            linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
            layoutManager = linearLayoutManager
            adapter = dataAdapter
        }

        dataAdapter.setData(dataList)
        dataAdapter.notifyDataSetChanged()
    }

    private fun initListener() {
        fab_add.setOnClickListener{
            startActivity(Intent(this,Detail_Activity::class.java).putExtra("status", true))
        }
    }

    private fun getDataDummy() {
        var dataList = ArrayList<DataModel>()
        dataList.add(DataModel(1, "Belajar java", "Minggu ini baru bagian variable"))
        dataList.add(DataModel(1, "Belajar java", "Minggu ini baru bagian variable"))
        dataList.add(DataModel(1, "Belajar java", "Minggu ini baru bagian variable"))
        dataList.add(DataModel(1, "Belajar java", "Minggu ini baru bagian variable"))
        dataList.add(DataModel(1, "Belajar java", "Minggu ini baru bagian variable"))
        dataList.add(DataModel(1, "Belajar java", "Minggu ini baru bagian variable"))
        dataList.add(DataModel(1, "Belajar java", "Minggu ini baru bagian variable"))
        dataList.add(DataModel(1, "Belajar java", "Minggu ini baru bagian variable"))
        dataList.add(DataModel(1, "Belajar java", "Minggu ini baru bagian variable"))
        dataList.add(DataModel(1, "Belajar java", "Minggu ini baru bagian variable"))


        var dataAdapter = DataAdapter(this)
        rv_data.apply {

            var linearLayoutManager = LinearLayoutManager(context)
            linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
            layoutManager = linearLayoutManager
            adapter = dataAdapter
        }

        dataAdapter.setData(dataList)
        dataAdapter.notifyDataSetChanged()
    }

    override fun onClick(data: DataModel) {
        startActivity(Intent(this,Detail_Activity::class.java)
                .putExtra("status",false)
                .putExtra("data",data)
        )

    }

    override fun onResume() {
        super.onResume()
        getData()
    }
}
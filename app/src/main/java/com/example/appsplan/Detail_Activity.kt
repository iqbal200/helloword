package com.example.appsplan

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.provider.BaseColumns
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import database.DbContract
import database.ReaderDbHelper
import kotlinx.android.synthetic.main.activity_detail_.*

class Detail_Activity : AppCompatActivity() {

    lateinit var dbHelper: ReaderDbHelper
    lateinit var db : SQLiteDatabase

    lateinit var data : DataModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_)

        dbHelper = ReaderDbHelper(this)
        db = dbHelper.writableDatabase

        var status = intent.getBooleanExtra("status", false)
        if (status){

            intilistener()

        }


        else{

            data = intent.getParcelableExtra("data")!!

            et_title.setText(data.title)
            et_desc.setText(data.desc)


            btn_delete.visibility = View.VISIBLE
            btn_save.text = "Edit Plan"
            initlistenerEdit()
        }


    }

    private fun intilistener() {
        btn_save.setOnClickListener{

            val sTitle = et_title.text.toString()
            val sDesc  = et_desc.text.toString()

            if (sTitle.isNullOrEmpty()){
                et_title.error = "Silahkan Masukkan Title"
                et_title.requestFocus()
            }else if(sDesc.isNullOrEmpty()){
                et_desc.error = "Silahkan Masukkan Descripsi"
                et_desc.requestFocus()
            }else{
              val values = ContentValues().apply {
                  put(DbContract.dataEntry.COLUMN_NAME_TITLE,sTitle)
                  put(DbContract.dataEntry.COLUMN_NAME_DESC,sDesc)
              }

            val newrowId = db.insert(DbContract.dataEntry.TABLE_NAME, null,values)
            if(newrowId == -1L){

                Toast.makeText(this,"Data Gagal Disimpan", Toast.LENGTH_SHORT).show()
            }else {
                Toast.makeText(this,"Data Berhasil Disimpan",Toast.LENGTH_SHORT).show()
                finish()
            }

             }
        }
    }
    private fun initlistenerEdit(){
        btn_delete.setOnClickListener(){
            val selection = "${BaseColumns._ID} LIKE ?"
            val selectionArg = arrayOf(data.id.toString())

            val deleteRows = db.delete(DbContract.dataEntry.TABLE_NAME,selection,selectionArg)

            if(deleteRows == -1){

                Toast.makeText(this,"Data Gagal di delete", Toast.LENGTH_SHORT).show()
            }else {
                Toast.makeText(this,"Data Berhasil di delete",Toast.LENGTH_SHORT).show()
                finish()
            }

        }

        btn_save.setOnClickListener{

            val sTitle = et_title.text.toString()
            val sDesc  = et_desc.text.toString()

            if (sTitle.isNullOrEmpty()){
                et_title.error = "Silahkan Masukkan Title"
                et_title.requestFocus()
            }else if(sDesc.isNullOrEmpty()){
                et_desc.error = "Silahkan Masukkan Descripsi"
                et_desc.requestFocus()
            }else{
                val values = ContentValues().apply {
                    put(DbContract.dataEntry.COLUMN_NAME_TITLE,sTitle)
                    put(DbContract.dataEntry.COLUMN_NAME_DESC,sDesc)
                }

                val newrowId = db.insert(DbContract.dataEntry.TABLE_NAME, null,values)
                if(newrowId == -1L){

                    Toast.makeText(this,"Data Gagal Disimpan", Toast.LENGTH_SHORT).show()
                }else {
                    Toast.makeText(this,"Data Berhasil Disimpan",Toast.LENGTH_SHORT).show()
                    finish()
                }

            }
        }

        btn_save.setOnClickListener{

            val sTitle = et_title.text.toString()
            val sDesc  = et_desc.text.toString()

            if (sTitle.isNullOrEmpty()){
                et_title.error = "Silahkan Masukkan Title"
                et_title.requestFocus()
            }else if(sDesc.isNullOrEmpty()){
                et_desc.error = "Silahkan Masukkan Descripsi"
                et_desc.requestFocus()


            }else{

            val selection ="${BaseColumns._ID}LIKE ?"
                val selectionArg = arrayOf(data.id.toString())

                val values = ContentValues().apply {
                    put(DbContract.dataEntry.COLUMN_NAME_TITLE,sTitle)
                    put(DbContract.dataEntry.COLUMN_NAME_DESC,sDesc)
                }


                val editRows = db.update(DbContract.dataEntry.TABLE_NAME, null,selection,selectionArg)
                if(editRows == -1){

                    Toast.makeText(this,"Data Gagal Di edit", Toast.LENGTH_SHORT).show()
                }else {
                    Toast.makeText(this,"Data Berhasil di edit",Toast.LENGTH_SHORT).show()
                    finish()
                }

            }
        }
    }
}
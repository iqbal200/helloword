package database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns

class ReaderDbHelper(context: Context): SQLiteOpenHelper(context,DATABASE_NAME,null,DATABASE_VERSION) {

    private val SQL_CREATE_ENTRIES = "CREATE TABLE  ${DbContract.dataEntry.TABLE_NAME} ("+
            "${BaseColumns._ID} INTEGER PRYMARI KEY,"+
            "${DbContract.dataEntry.COLUMN_NAME_TITLE} TEXT," +
            "${DbContract.dataEntry.COLUMN_NAME_DESC} TEXT)"


    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_ENTRIES)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    companion object{
        const val DATABASE_NAME ="entry.db"
        const val DATABASE_VERSION = 1
    }
}
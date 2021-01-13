package database

import android.provider.BaseColumns

object DbContract {

    object dataEntry : BaseColumns{
        const val TABLE_NAME = "Entry"
        const val COLUMN_NAME_TITLE = "title"
        const val COLUMN_NAME_DESC = "desc"

    }
}
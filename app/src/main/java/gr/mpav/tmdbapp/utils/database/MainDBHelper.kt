package gr.mpav.tmdbapp.utils.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.io.Closeable

class MainDBHelper @JvmOverloads constructor(context: Context, dbName: String = DATABASE_NAME) : SQLiteOpenHelper(context, dbName, null, DATABASE_VERSION),
    Closeable
{
    companion object {
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "tmdbapp.db"
    }

    override fun onCreate(db: SQLiteDatabase) {
        DBSchema.createWatchlistTable(db)
    }


    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("drop table if exists "+DBSchema.WATCHLIST_TABLE)
        onCreate(db)
    }
}
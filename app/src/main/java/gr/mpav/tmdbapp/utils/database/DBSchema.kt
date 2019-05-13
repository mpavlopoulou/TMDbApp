package gr.mpav.tmdbapp.utils.database

import android.database.sqlite.SQLiteDatabase

class DBSchema {
    companion object {
        const val WATCHLIST_TABLE = "WATCHLIST"

        fun createWatchlistTable(db: SQLiteDatabase)
        {
            val createTableBooklet = "CREATE TABLE "+WATCHLIST_TABLE+" ( \n" +
                    "Id int PRIMARY KEY NOT NULL, \n" +
                    "ShowType varchar(50),\n" +
                    "PosterPath varchar(250),\n" +
                    "Title varchar(250),\n" +
                    "VoteAverage decimal(5, 2),\n" +
                    "ReleaseDate varchar(50),\n" +
                    "Overview varchar(500))"
            db.execSQL(createTableBooklet)
        }

    }
}
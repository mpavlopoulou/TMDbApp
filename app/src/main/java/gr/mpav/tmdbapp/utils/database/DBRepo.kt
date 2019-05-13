package gr.mpav.tmdbapp.utils.database

import android.content.ContentValues
import android.content.Context

class DbRepo(val context: Context) {

    fun isShowInWatchlist(showId: Int): Boolean {
        var showID: Int = -1

        MainDBHelper(this.context).use { dbHelper ->
            dbHelper.readableDatabase.use {

                val query = "SELECT Id FROM " + DBSchema.WATCHLIST_TABLE + " WHERE Id=$showId"
                val cursor = it.rawQuery(query, null)
                if (cursor != null && cursor.moveToFirst()) {
                    try {
                        showID = cursor.getInt(cursor.getColumnIndex("Id"))
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
                cursor?.close()
            }
        }
        return showID != -1
    }

    fun addShowInWatchlist(dbShow: DBShow){
        MainDBHelper(this.context).use { dbHelper ->
            dbHelper.writableDatabase.use {

                val showValues = ContentValues()
                showValues.put("Id", dbShow.Id)
                showValues.put("ShowType", dbShow.ShowType)
                showValues.put("PosterPath", dbShow.PosterPath)
                showValues.put("Title", dbShow.Title)
                showValues.put("VoteAverage", dbShow.VoteAverage)
                showValues.put("ReleaseDate", dbShow.ReleaseDate)
                showValues.put("Overview", dbShow.Overview)

                try {
                    it.insert(DBSchema.WATCHLIST_TABLE, null, showValues)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    fun deleteShowFromWatchlist(dbShowId: Int){
        MainDBHelper(this.context).use { dbHelper ->
            dbHelper.writableDatabase.use {
                try {
                    it.delete(DBSchema.WATCHLIST_TABLE,  "Id=?", arrayOf("$dbShowId"))
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }


}
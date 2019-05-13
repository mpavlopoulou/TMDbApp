package gr.mpav.tmdbapp.utils.database

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



}
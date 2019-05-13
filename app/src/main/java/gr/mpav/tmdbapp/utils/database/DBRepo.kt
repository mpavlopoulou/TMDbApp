package gr.mpav.tmdbapp.utils.database

import android.content.ContentValues
import android.content.Context
import gr.mpav.tmdbapp.utils.adapters.ShowAdapterItem

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

    fun isWatchlistEmpty(): Boolean {
        var result = true
        MainDBHelper(context).use { dbHelper ->
            dbHelper.readableDatabase.use {

                val query = "select * from " + DBSchema.WATCHLIST_TABLE
                val cursor = it.rawQuery(query, null)
                if (cursor != null && cursor.moveToFirst()) {
                    try {
                        result = false
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
                cursor?.close()
            }
        }
        return result
    }

    fun getWatchlistItems(titleFilter:String = ""):ArrayList<ShowAdapterItem>{
        val items: ArrayList<ShowAdapterItem> = ArrayList()

        MainDBHelper(context).use { dbHelper ->
            dbHelper.readableDatabase.use {

                val query = "select * from " + DBSchema.WATCHLIST_TABLE +
                        " where Title like \"%$titleFilter%\" "

                val cursor = it.rawQuery(query, null)
                if (cursor != null && cursor.moveToFirst()) {
                    try {
                        do {
                            items.add(ShowAdapterItem(cursor.getInt(cursor.getColumnIndex("Id")),
                                cursor.getString(cursor.getColumnIndex("PosterPath")),
                                cursor.getString(cursor.getColumnIndex("ReleaseDate")),
                                cursor.getString(cursor.getColumnIndex("ShowType")),
                                cursor.getString(cursor.getColumnIndex("Title")),
                                cursor.getDouble(cursor.getColumnIndex("VoteAverage")).toFloat(),
                                cursor.getString(cursor.getColumnIndex("Overview"))))
                        } while (cursor.moveToNext())
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
                cursor?.close()
            }
        }
        return items


    }

}
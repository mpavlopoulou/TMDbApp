package gr.mpav.tmdbapp.utils.database

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import android.util.Log

data class DBShow(
    var Id: Int = -1,
    var ShowType: String = "",
    var BackdropPath: String = "",
    var PosterPath: String = "",
    var Title: String = "",
    var VoteAverage: Float = 0.0f,
    var ReleaseDate:String = "",
    var Overview:String = "",
    var Genre:String = "",
    var TrailerKey:String = ""
)


fun insertShowToWatchList(db: SQLiteDatabase, dbShow: DBShow) {

    val showValues = ContentValues()
    showValues.put("Id", dbShow.Id)
    showValues.put("ShowType", dbShow.ShowType)
    showValues.put("BackdropPath", dbShow.BackdropPath)
    showValues.put("PosterPath", dbShow.PosterPath)
    showValues.put("Title", dbShow.Title)
    showValues.put("VoteAverage", dbShow.VoteAverage)
    showValues.put("ReleaseDate", dbShow.ReleaseDate)
    showValues.put("Overview", dbShow.Overview)
    showValues.put("Genre", dbShow.Genre)
    showValues.put("TrailerKey", dbShow.TrailerKey)

    try
    {
        db.insert(DBSchema.WATCHLIST_TABLE, null, showValues)
    }
    catch (e: Exception)
    {
        Log.d("Insert db show record", "error: ${e.message}")
    }
}

fun removeShowFromWatchList(db: SQLiteDatabase, dbShowId: Int){
    try
    {
        db.delete(DBSchema.WATCHLIST_TABLE,  "Id=?", arrayOf("$dbShowId"))
    }
    catch (e: Exception)
    {
        Log.d("Delete db show record", "error: ${e.message}")
    }
}
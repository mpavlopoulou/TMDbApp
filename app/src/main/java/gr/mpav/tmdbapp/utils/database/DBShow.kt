package gr.mpav.tmdbapp.utils.database

import java.io.Serializable

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
    var TrailerKey:String? = ""
): Serializable
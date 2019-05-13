package gr.mpav.tmdbapp.utils.api_calls

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class Show {
    @SerializedName("id")
    @Expose
    var id: Int = 0

    @SerializedName("title")
    @Expose
    var movieTitle: String = ""

    @SerializedName("overview")
    @Expose
    var overview: String = ""

    @SerializedName("name")
    @Expose
    var tvShowName: String = ""

    @SerializedName("poster_path")
    @Expose
    var posterPath: String = ""

    @SerializedName("release_date")
    @Expose
    var movieReleaseDate: String = ""

    @SerializedName("first_air_date")
    @Expose
    var tvShowFirstAirDate: String = ""

    @SerializedName("vote_average")
    @Expose
    var rating: Float = 0.toFloat()

    @SerializedName("media_type")
    @Expose
    var mediaType: String = ""
}

class ShowsResponse {

    @SerializedName("page")
    @Expose
    var page: Int = 0

    @SerializedName("results")
    @Expose
    var shows: ArrayList<Show> = ArrayList()

    @SerializedName("total_pages")
    @Expose
    var totalPages: Int = 0
}

class Genre {
    @SerializedName("name")
    @Expose
    var name: String = ""
}

class ShowDetails{
    @SerializedName("backdrop_path")
    @Expose
    var backdropPath: String = ""

    @SerializedName("poster_path")
    @Expose
    var posterPath: String = ""

    @SerializedName("title")
    @Expose
    var movieTitle: String = ""

    @SerializedName("name")
    @Expose
    var tvShowTitle: String = ""

    @SerializedName("vote_average")
    @Expose
    var rating: Float = 0.toFloat()

    @SerializedName("release_date")
    @Expose
    var movieReleaseDate: String = ""

    @SerializedName("first_air_date")
    @Expose
    var tvShowReleaseDate: String = ""

    @SerializedName("overview")
    @Expose
    var overview: String = ""

    @SerializedName("genres")
    @Expose
    var genres: ArrayList<Genre> = ArrayList()
}

class ShowVideo{
    @SerializedName("key")
    @Expose
    var key: String = ""
}

class VideosResponse{
    @SerializedName("results")
    @Expose
    var videos: ArrayList<ShowVideo> = ArrayList()
}
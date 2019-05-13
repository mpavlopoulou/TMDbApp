package gr.mpav.tmdbapp.utils.api_calls

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import gr.mpav.tmdbapp.utils.adapters.ShowAdapterItem
import gr.mpav.tmdbapp.utils.general.Constants

// Utility function which converts api response Show list to ShowAdapterItem list
fun getShowItems(shows: ArrayList<Show>): ArrayList<ShowAdapterItem> {
    val result: ArrayList<ShowAdapterItem> = ArrayList()
    for (show in shows) {
        var releaseDate: String
        var title: String
        if (show.mediaType == Constants.MOVIE_TYPE) {
            releaseDate = show.movieReleaseDate?:""
            title = show.movieTitle?:""
        } else {
            releaseDate = show.tvShowFirstAirDate?:""
            title = show.tvShowName?:""
        }

        result.add(
            ShowAdapterItem(
                show.id?:0
                , show.posterPath?:"", releaseDate, show.mediaType?:"", title, show.rating?:0f, show.overview?:""
            )
        )
    }
    return result
}

class Show {
    @SerializedName("id")
    @Expose
    var id: Int? = 0

    @SerializedName("title")
    @Expose
    var movieTitle: String? = ""

    @SerializedName("overview")
    @Expose
    var overview: String? = ""

    @SerializedName("name")
    @Expose
    var tvShowName: String? = ""

    @SerializedName("poster_path")
    @Expose
    var posterPath: String? = ""

    @SerializedName("release_date")
    @Expose
    var movieReleaseDate: String? = ""

    @SerializedName("first_air_date")
    @Expose
    var tvShowFirstAirDate: String? = ""

    @SerializedName("vote_average")
    @Expose
    var rating: Float? = 0.toFloat()

    @SerializedName("media_type")
    @Expose
    var mediaType: String? = ""
}

// Data class used to retrieve response from search api call
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

// Data class used to retrieve response from details api call
class ShowDetails{
    @SerializedName("backdrop_path")
    @Expose
    var backdropPath: String? = ""

    @SerializedName("poster_path")
    @Expose
    var posterPath: String? = ""

    @SerializedName("title")
    @Expose
    var movieTitle: String? = ""

    @SerializedName("name")
    @Expose
    var tvShowTitle: String? = ""

    @SerializedName("vote_average")
    @Expose
    var rating: Float? = 0.toFloat()

    @SerializedName("release_date")
    @Expose
    var movieReleaseDate: String? = ""

    @SerializedName("first_air_date")
    @Expose
    var tvShowReleaseDate: String? = ""

    @SerializedName("overview")
    @Expose
    var overview: String? = ""

    @SerializedName("genres")
    @Expose
    var genres: ArrayList<Genre>? = ArrayList()
}

class ShowVideo{
    @SerializedName("key")
    @Expose
    var key: String = ""
}

// Data class used to retrieve response from videos api call
class VideosResponse{
    @SerializedName("results")
    @Expose
    var videos: ArrayList<ShowVideo> = ArrayList()
}
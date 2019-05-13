package gr.mpav.tmdbapp.utils.api_calls

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface TMDbApi {

    @GET("search/multi")
    fun getSearchResults(
        @Query("api_key") apiKey: String,
        @Query("query") searchQuery: String,
        @Query("page") page: Int = 1
    ): Call<ShowsResponse>

    @GET("{show_type}/{show_id}")
    fun getShowDetails(
        @Path("show_type") show_type: String,
        @Path("show_id") id: Int,
        @Query("api_key") apiKey: String
    ): Call<ShowDetails>

    @GET("{show_type}/{show_id}/videos")
    fun getShowVideos(
        @Path("show_type") show_type: String,
        @Path("show_id") id: Int,
        @Query("api_key") apiKey: String
    ): Call<VideosResponse>

}
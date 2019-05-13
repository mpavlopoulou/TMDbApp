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

    @GET("movie/{movie_id}")
    fun getMovieDetails(
        @Path("movie_id") id: Int,
        @Query("api_key") apiKey: String
    ): Call<MovieDetails>

    @GET("tv/{tv_show_id}")
    fun getTVShowDetails(
        @Path("tv_show_id") id: Int,
        @Query("api_key") apiKey: String
    ): Call<TVShowDetails>

    @GET("movie/{movie_id}/videos")
    fun getMovieVideos(
        @Path("movie_id") id: Int,
        @Query("api_key") apiKey: String
    ): Call<VideosResponse>

    @GET("tv/{tv_show_id}/videos")
    fun getTVShowVideos(
        @Path("tv_show_id") id: Int,
        @Query("api_key") apiKey: String
    ): Call<VideosResponse>
}
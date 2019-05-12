package gr.mpav.tmdbapp.utils.api_calls

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface TMDbApi {

    @GET("search/multi")
    fun getSearchResults(
        @Query("api_key") apiKey: String,
        @Query("query") searchQuery: String,
        @Query("page") page: Int = 1
    ): Call<ShowsResponse>


}
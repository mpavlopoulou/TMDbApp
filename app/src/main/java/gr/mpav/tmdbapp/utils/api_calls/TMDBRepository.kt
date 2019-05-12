package gr.mpav.tmdbapp.utils.api_calls

import gr.mpav.tmdbapp.utils.general.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit

class TMDBRepository private constructor(private val api: TMDbApi) {

    fun getSearchResults(callback: OnGetSearchResultsCallback, searchQuery:String, pageNumber:Int = 1) {
        api.getSearchResults(Constants.MDB_API_KEY, searchQuery, pageNumber)
            .enqueue(object : Callback<ShowsResponse> {
                override fun onResponse(call: Call<ShowsResponse>, response: Response<ShowsResponse>) {
                    if (response.isSuccessful) {
                        val showsResponse = response.body()
                        if (showsResponse?.shows != null) {
                            callback.onSuccess(showsResponse.page,showsResponse.totalPages, showsResponse.shows)
                        } else {
                            callback.onError()
                        }
                    } else {
                        callback.onError()
                    }
                }

                override fun onFailure(call: Call<ShowsResponse>, t: Throwable) {
                    callback.onError()
                }
            })
    }

    companion object {

        private var repository: TMDBRepository? = null

        val instance: TMDBRepository
            get() {
                if (repository == null) {
                    val retrofit = Retrofit.Builder()
                        .baseUrl(Constants.MDB_API_ENDPOINT)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()

                    repository = TMDBRepository(retrofit.create(TMDbApi::class.java))
                }
                return repository as TMDBRepository
            }
    }
}
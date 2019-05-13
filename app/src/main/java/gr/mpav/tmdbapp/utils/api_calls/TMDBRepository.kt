package gr.mpav.tmdbapp.utils.api_calls

import gr.mpav.tmdbapp.utils.general.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

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

    fun getMovieDetails(callback: OnGetMovieDetailsCallback, movieId: Int) {
        api.getMovieDetails(movieId, Constants.MDB_API_KEY)
            .enqueue(object : Callback<MovieDetails> {
                override fun onResponse(call: Call<MovieDetails>, response: Response<MovieDetails>) {
                    if (response.isSuccessful) {
                        val movieDetails = response.body()
                        if (movieDetails != null) {
                            callback.onSuccess(movieDetails)
                        } else {
                            callback.onError()
                        }
                    } else {
                        callback.onError()
                    }
                }

                override fun onFailure(call: Call<MovieDetails>, t: Throwable) {
                    callback.onError()
                }
            })
    }

    fun getTVShowDetails(callback: OnGetTVShowDetailsCallback, tvShowId: Int) {
        api.getTVShowDetails(tvShowId, Constants.MDB_API_KEY)
            .enqueue(object : Callback<TVShowDetails> {
                override fun onResponse(call: Call<TVShowDetails>, response: Response<TVShowDetails>) {
                    if (response.isSuccessful) {
                        val tvShowDetails = response.body()
                        if (tvShowDetails != null) {
                            callback.onSuccess(tvShowDetails)
                        } else {
                            callback.onError()
                        }
                    } else {
                        callback.onError()
                    }
                }

                override fun onFailure(call: Call<TVShowDetails>, t: Throwable) {
                    callback.onError()
                }
            })
    }

    fun getMovieVideos(callback: OnGetShowVideosCallback, movieId: Int) {
        api.getMovieVideos(movieId, Constants.MDB_API_KEY)
            .enqueue(object : Callback<VideosResponse> {
                override fun onResponse(call: Call<VideosResponse>, response: Response<VideosResponse>) {
                    if (response.isSuccessful) {
                        val videos = response.body()
                        if (videos?.videos != null) {
                            callback.onSuccess(videos.videos)
                        } else {
                            callback.onError()
                        }
                    } else {
                        callback.onError()
                    }
                }

                override fun onFailure(call: Call<VideosResponse>, t: Throwable) {
                    callback.onError()
                }
            })
    }

    fun getTVShowVideos(callback: OnGetShowVideosCallback, tvShowId: Int) {
        api.getTVShowVideos(tvShowId, Constants.MDB_API_KEY)
            .enqueue(object : Callback<VideosResponse> {
                override fun onResponse(call: Call<VideosResponse>, response: Response<VideosResponse>) {
                    if (response.isSuccessful) {
                        val videos = response.body()
                        if (videos?.videos != null) {
                            callback.onSuccess(videos.videos)
                        } else {
                            callback.onError()
                        }
                    } else {
                        callback.onError()
                    }
                }

                override fun onFailure(call: Call<VideosResponse>, t: Throwable) {
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
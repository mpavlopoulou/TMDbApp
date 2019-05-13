package gr.mpav.tmdbapp.utils.api_calls


interface OnGetSearchResultsCallback {
    fun onSuccess(pageNumber:Int , totalPages:Int, shows: ArrayList<Show>)
    fun onError()
}

interface OnGetMovieDetailsCallback{
    fun onSuccess(movieDetails:MovieDetails)
    fun onError()
}

interface OnGetTVShowDetailsCallback{
    fun onSuccess(tvShowDetails:TVShowDetails)
    fun onError()
}

interface OnGetShowVideosCallback{
    fun onSuccess(videos: ArrayList<ShowVideo>)
    fun onError()
}
package gr.mpav.tmdbapp.utils.api_calls


interface OnGetSearchResultsCallback {
    fun onSuccess(pageNumber:Int , totalPages:Int, shows: ArrayList<Show>)
    fun onError()
}

interface OnGetShowDetailsCallback{
    fun onSuccess(showDetails:ShowDetails)
    fun onError()
}

interface OnGetShowVideosCallback{
    fun onSuccess(videos: ArrayList<ShowVideo>)
    fun onError()
}
package gr.mpav.tmdbapp.utils.api_calls


interface OnGetSearchResultsCallback {
    fun onSuccess(shows: ArrayList<Show>)
    fun onError()
}
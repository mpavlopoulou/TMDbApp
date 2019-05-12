package gr.mpav.tmdbapp.utils.api_calls


interface OnGetSearchResultsCallback {
    fun onSuccess(pageNumber:Int , totalPages:Int, shows: ArrayList<Show>)
    fun onError()
}
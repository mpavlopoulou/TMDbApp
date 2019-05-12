package gr.mpav.tmdbapp.activities

import android.os.Bundle
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatEditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import gr.mpav.tmdbapp.R
import gr.mpav.tmdbapp.utils.adapters.ShowsAdapter
import gr.mpav.tmdbapp.utils.api_calls.Show
import gr.mpav.tmdbapp.utils.api_calls.TMDBRepository
import gr.mpav.tmdbapp.utils.hideKeyboard
import gr.mpav.tmdbapp.utils.api_calls.OnGetSearchResultsCallback


class SearchActivity : BaseActivity(), ShowsAdapter.ItemClickedListener {

    private lateinit var searchEditText: AppCompatEditText
    private lateinit var showWatchList: ImageView
    private lateinit var showsRecycler: RecyclerView

    private lateinit var mShowsAdapter: ShowsAdapter
    private val mRepo:TMDBRepository  = TMDBRepository.instance

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        setUpViews()
    }

    private fun setUpViews() {
        searchEditText = findViewById(R.id.search_edit_text)
        searchEditText.setOnEditorActionListener { _, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_NEXT || event != null && event.keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_DOWN) {
                hideKeyboard(this, currentFocus)
                if (searchEditText.text!!.isNotEmpty())
                    performSearchApiCall()
                true
            } else {
                false
            }
        }

        showWatchList = findViewById(R.id.watchlist_button)
        showWatchList.setOnClickListener {
            moveToWatchListScreen()
        }

        setUpShowsRecycler()
    }

    private fun setUpShowsRecycler(){
        showsRecycler = findViewById(R.id.shows_recycler)
        showsRecycler.layoutManager = LinearLayoutManager(this)
        mShowsAdapter = ShowsAdapter(this)
        mShowsAdapter.setItemClickedListener(this)
        showsRecycler.adapter = mShowsAdapter
    }

    override fun itemClicked(item: Show) {
        TODO("not implemented")
    }

    private fun performSearchApiCall() {
        // todo progree view
        mRepo.getSearchResults(object : OnGetSearchResultsCallback {
            override fun onSuccess(shows: ArrayList<Show>) {
                mShowsAdapter.setData(shows)
            }

            override fun onError() {
                // todo
            }
        },searchEditText.text.toString().trim())
    }

    private fun moveToWatchListScreen(){
        TODO("not implemented")
    }
}
package gr.mpav.tmdbapp.activities

import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatEditText
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import gr.mpav.tmdbapp.R
import gr.mpav.tmdbapp.utils.adapters.ShowsAdapter
import gr.mpav.tmdbapp.utils.adapters.ShowsAdapterListener
import gr.mpav.tmdbapp.utils.api_calls.OnGetSearchResultsCallback
import gr.mpav.tmdbapp.utils.api_calls.Show
import gr.mpav.tmdbapp.utils.api_calls.TMDBRepository
import gr.mpav.tmdbapp.utils.api_calls.getShowItems
import gr.mpav.tmdbapp.utils.database.DBShow
import gr.mpav.tmdbapp.utils.database.DbRepo
import gr.mpav.tmdbapp.utils.general.Constants
import gr.mpav.tmdbapp.utils.general.Constants.Companion.SEARCH_TERM
import gr.mpav.tmdbapp.utils.general.hideKeyboard


class SearchActivity : BaseActivity(), ShowsAdapterListener {

    private lateinit var searchEditText: AppCompatEditText
    private lateinit var showWatchList: ImageView

    private lateinit var showsRecycler: RecyclerView
    private lateinit var mShowsAdapter: ShowsAdapter

    private lateinit var mNoResultsView: ConstraintLayout
    private lateinit var mInitialView: ConstraintLayout

    private val mRepo: TMDBRepository = TMDBRepository.instance

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        setUpViews(savedInstanceState)
    }

    private fun setUpViews(savedInstanceState: Bundle?) {
        super.setUpViews()

        mNoResultsView = findViewById(R.id.no_results_view)
        mNoResultsView.visibility = View.GONE
        mInitialView = findViewById(R.id.initial_view)
        mInitialView.visibility = View.VISIBLE

        // Search for shows when enter is pressed
        searchEditText = findViewById(R.id.search_edit_text)
        searchEditText.setOnEditorActionListener { _, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_NEXT || event != null && event.keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_DOWN) {
                hideKeyboard(this, currentFocus)
                if (searchEditText.text.toString().isNotEmpty())
                {
                    mShowsAdapter.clearData()
                    getSearchResults()
                }
                true
            } else
                false

        }

        showWatchList = findViewById(R.id.watchlist_button)
        showWatchList.setOnClickListener {
            moveToWatchListScreen()
        }
        setWatchlistButtonVisibility()

        setUpShowsRecycler()

        setSaveInstanceStateData(savedInstanceState)
    }

    private fun setWatchlistButtonVisibility() {
        val dbRepo = DbRepo(this)
        if (dbRepo.isWatchlistEmpty())
            showWatchList.visibility = View.GONE
        else
            showWatchList.visibility = View.VISIBLE
    }

    // Restore search term upon activity recreation on orientation change
    private fun setSaveInstanceStateData(savedInstanceState: Bundle?) {
        searchEditText.setText(savedInstanceState?.getString(SEARCH_TERM))
        if (searchEditText.text.toString().isNotEmpty()) {
            mShowsAdapter.clearData()
            getSearchResults()
        }
    }


    private fun setUpShowsRecycler(){
        showsRecycler = findViewById(R.id.shows_recycler)
        showsRecycler.visibility = View.GONE
        showsRecycler.layoutManager = LinearLayoutManager(this)
        mShowsAdapter = ShowsAdapter(this)
        mShowsAdapter.setListener(this)
        showsRecycler.adapter = mShowsAdapter
    }

    override fun onShowSelected(showId: Int,showType:String) {
        val detailsIntent = Intent(this, DetailsActivity::class.java)
        detailsIntent.putExtra(Constants.SHOW_SELECTED, DBShow(showId,showType))
        startActivity(detailsIntent)
    }

    override fun loadNextPageShows(pageNumber: Int) {
        getSearchResults(pageNumber)
    }

    private fun getSearchResults(pageNumber: Int = 1) {
        mInitialView.visibility = View.GONE
        showProgressView()
        mRepo.getSearchResults(object : OnGetSearchResultsCallback {
            override fun onSuccess(pageNumber: Int, totalPages:Int ,shows: ArrayList<Show>) {
                hideProgressView()
                if (shows.isEmpty()) {
                    showsRecycler.visibility = View.GONE
                    mNoResultsView.visibility = View.VISIBLE
                } else {
                    mNoResultsView.visibility = View.GONE
                    showsRecycler.visibility = View.VISIBLE
                    mShowsAdapter.setData(getShowItems(shows), pageNumber, totalPages)
                }
            }
            override fun onError() {
                hideProgressView()
                handleServiceExternalFailure()
            }
        }, searchEditText.text.toString().trim(), pageNumber)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString(SEARCH_TERM, searchEditText.text.toString())
        super.onSaveInstanceState(outState)
    }

    override fun onResume() {
        super.onResume()
        setWatchlistButtonVisibility()
    }

    private fun moveToWatchListScreen(){
        val watchlistIntent = Intent(this, WatchlistActivity::class.java)
        startActivity(watchlistIntent)
    }
}
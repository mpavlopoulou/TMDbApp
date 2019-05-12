package gr.mpav.tmdbapp.activities

import android.os.Bundle
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatEditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import gr.mpav.tmdbapp.R
import gr.mpav.tmdbapp.utils.adapters.ShowsAdapter
import gr.mpav.tmdbapp.utils.adapters.ShowsAdapterListener
import gr.mpav.tmdbapp.utils.api_calls.OnGetSearchResultsCallback
import gr.mpav.tmdbapp.utils.api_calls.Show
import gr.mpav.tmdbapp.utils.api_calls.TMDBRepository
import gr.mpav.tmdbapp.utils.general.hideKeyboard


class SearchActivity : BaseActivity(), ShowsAdapterListener {

    private lateinit var searchEditText: AppCompatEditText
    private lateinit var showWatchList: ImageView

    private lateinit var showsRecycler: RecyclerView
    private lateinit var mShowsAdapter: ShowsAdapter

    private val mRepo: TMDBRepository = TMDBRepository.instance

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        setUpViews()
    }

    override fun setUpViews() {
        super.setUpViews()
        searchEditText = findViewById(R.id.search_edit_text)
        searchEditText.setOnEditorActionListener { _, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_NEXT || event != null && event.keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_DOWN) {
                hideKeyboard(this, currentFocus)
                if (searchEditText.text!!.isNotEmpty())
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

        setUpShowsRecycler()
    }

    private fun setUpShowsRecycler(){
        showsRecycler = findViewById(R.id.shows_recycler)
        showsRecycler.layoutManager = LinearLayoutManager(this)
        mShowsAdapter = ShowsAdapter(this)
        mShowsAdapter.setListener(this)
        showsRecycler.adapter = mShowsAdapter
    }

    override fun onShowSelected(showId: Int,showType:String) {
        Toast.makeText(this,"Show details",Toast.LENGTH_LONG).show()
    }

    override fun loadNextPageShows(pageNumber: Int) {
        getSearchResults(pageNumber)
    }

    private fun getSearchResults(pageNumber: Int = 1) {
        showProgressView()
        mRepo.getSearchResults(object : OnGetSearchResultsCallback {
            override fun onSuccess(pageNumber: Int, totalPages:Int ,shows: ArrayList<Show>) {
                hideProgressView()
                mShowsAdapter.setData(shows,pageNumber,totalPages)
            }
            override fun onError() {
                hideProgressView()
                handleServiceExternalFailure()
            }
        }, searchEditText.text.toString().trim(), pageNumber)
    }

    private fun moveToWatchListScreen(){
        TODO("not implemented")
    }
}
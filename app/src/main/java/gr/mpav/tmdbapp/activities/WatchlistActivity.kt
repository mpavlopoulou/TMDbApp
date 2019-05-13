package gr.mpav.tmdbapp.activities

import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.appcompat.widget.AppCompatEditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import gr.mpav.tmdbapp.R
import gr.mpav.tmdbapp.utils.adapters.ShowsAdapter
import gr.mpav.tmdbapp.utils.adapters.ShowsAdapterListener
import gr.mpav.tmdbapp.utils.database.DBShow
import gr.mpav.tmdbapp.utils.database.DbRepo
import gr.mpav.tmdbapp.utils.general.Constants
import gr.mpav.tmdbapp.utils.general.hideKeyboard

class WatchlistActivity : BaseActivity(), ShowsAdapterListener {

    private lateinit var searchEditText: AppCompatEditText
    private lateinit var showsRecycler: RecyclerView
    private lateinit var mShowsAdapter: ShowsAdapter

    private lateinit var dbRepo: DbRepo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_watchlist)
        dbRepo = DbRepo(this)
        setUpViews(savedInstanceState)
    }

    private fun setUpViews(savedInstanceState: Bundle?) {
        super.setUpViews()

        searchEditText = findViewById(R.id.search_edit_text)
        searchEditText.setOnEditorActionListener { _, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_NEXT || event != null && event.keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_DOWN) {
                hideKeyboard(this, currentFocus)
                mShowsAdapter.clearData()
                mShowsAdapter.setData(dbRepo.getWatchlistItems(searchEditText.text.toString()),1,1)
                true
            } else
                false
        }

        setUpShowsRecycler()
        setSaveInstanceStateData(savedInstanceState)
    }

    private fun setUpShowsRecycler(){
        showsRecycler = findViewById(R.id.shows_recycler)
        showsRecycler.layoutManager = LinearLayoutManager(this)
        mShowsAdapter = ShowsAdapter(this)
        mShowsAdapter.setListener(this)
        mShowsAdapter.setData(dbRepo.getWatchlistItems(),1,1)
        showsRecycler.adapter = mShowsAdapter
    }

    override fun onShowSelected(showId: Int, showType: String) {
        val detailsIntent = Intent(this, DetailsActivity::class.java)
        detailsIntent.putExtra(Constants.SHOW_SELECTED, DBShow(showId,showType))
        startActivity(detailsIntent)
    }

    override fun loadNextPageShows(pageNumber: Int) {}

    private fun setSaveInstanceStateData(savedInstanceState: Bundle?) {
        searchEditText.setText(savedInstanceState?.getString(Constants.SEARCH_TERM))
        mShowsAdapter.clearData()
        mShowsAdapter.setData(dbRepo.getWatchlistItems(searchEditText.text.toString()),1,1)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString(Constants.SEARCH_TERM, searchEditText.text.toString())
        super.onSaveInstanceState(outState)
    }
}
package gr.mpav.tmdbapp.activities

import android.os.Bundle
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatEditText
import androidx.recyclerview.widget.RecyclerView
import gr.mpav.tmdbapp.R
import gr.mpav.tmdbapp.utils.hideKeyboard

class SearchActivity : BaseActivity() {
    private lateinit var searchEditText: AppCompatEditText
    private lateinit var showWatchList: ImageView
    private lateinit var showsRecycler: RecyclerView

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

        showsRecycler = findViewById(R.id.shows_recycler)
    }

    private fun performSearchApiCall() {
        TODO("not implemented")
    }

    private fun moveToWatchListScreen(){
        TODO("not implemented")
    }
}
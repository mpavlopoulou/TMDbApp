package gr.mpav.tmdbapp.activities

import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import gr.mpav.tmdbapp.R
import gr.mpav.tmdbapp.utils.general.Constants

class DetailsActivity : BaseActivity() {

    private var mShowId:Int = -1
    private var mShowType:String = ""

    private lateinit var mToolbar:Toolbar
    private lateinit var mDetailsRecycler:RecyclerView

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        getIntentData()
        setUpViews(savedInstanceState)
    }

    private fun setUpViews(savedInstanceState: Bundle?) {
        super.setUpViews()
        mToolbar = findViewById(R.id.mainToolbar)
        mDetailsRecycler = findViewById(R.id.show_details_recycler)
        mDetailsRecycler.layoutManager = LinearLayoutManager(this)

    }

    private fun getIntentData(){
        mShowId = intent.getIntExtra(Constants.SHOW_ID,-1)
        mShowType = intent.getStringExtra(Constants.SHOW_TYPE)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(Constants.SHOW_ID, mShowId)
        outState.putString(Constants.SHOW_TYPE, mShowType)
        super.onSaveInstanceState(outState)
    }
}
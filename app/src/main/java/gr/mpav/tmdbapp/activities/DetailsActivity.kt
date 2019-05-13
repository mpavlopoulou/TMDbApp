package gr.mpav.tmdbapp.activities

import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import gr.mpav.tmdbapp.R
import gr.mpav.tmdbapp.utils.adapters.DetailsAdapter
import gr.mpav.tmdbapp.utils.api_calls.*
import gr.mpav.tmdbapp.utils.general.Constants

class DetailsActivity : BaseActivity() {

    private var mShowId:Int = -1
    private var mShowType:String = ""

    private lateinit var mToolbar:Toolbar
    private lateinit var mDetailsRecycler:RecyclerView
    private lateinit var mAdapter:DetailsAdapter

    private val mRepo: TMDBRepository = TMDBRepository.instance

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        getIntentData()
        setUpViews(savedInstanceState)
        getShowDetails()
    }

    private fun setUpViews(savedInstanceState: Bundle?) {
        super.setUpViews()
        mToolbar = findViewById(R.id.mainToolbar)
        mDetailsRecycler = findViewById(R.id.show_details_recycler)
        mDetailsRecycler.layoutManager = LinearLayoutManager(this)
        mAdapter = DetailsAdapter(this)
        mDetailsRecycler.adapter = mAdapter
    }

    private fun getIntentData(){
        mShowId = intent.getIntExtra(Constants.SHOW_ID,-1)
        mShowType = intent.getStringExtra(Constants.SHOW_TYPE)
    }

    private fun getShowDetails(){
        showProgressView()
        mRepo.getShowDetails(object : OnGetShowDetailsCallback {
            override fun onSuccess(showDetails: ShowDetails) {
                //todo get movie trailer
            }
            override fun onError() {
                hideProgressView()
                handleServiceExternalFailure()
            }
        },mShowType,mShowId)
    }

    private fun getShowVideos(){
        mRepo.getShowVideos(object : OnGetShowVideosCallback {
            override fun onSuccess(videos:ArrayList<ShowVideo>) {
                //todo get movie trailer
            }
            override fun onError() {
                hideProgressView()
                handleServiceExternalFailure()
            }
        },mShowType,mShowId)
    }


//    override fun onSaveInstanceState(outState: Bundle) {
//        outState.putInt(Constants.SHOW_ID, mShowId)
//        outState.putString(Constants.SHOW_TYPE, mShowType)
//        super.onSaveInstanceState(outState)
//    }
//
//    private fun setSaveInstanceStateData(savedInstanceState: Bundle) {
//        mShowId = savedInstanceState.getInt(Constants.SHOW_ID)
//        mShowType = savedInstanceState.getString(Constants.SHOW_TYPE) ?: ""
//    }
}
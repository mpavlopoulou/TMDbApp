package gr.mpav.tmdbapp.activities

import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import gr.mpav.tmdbapp.R
import gr.mpav.tmdbapp.utils.adapters.*
import gr.mpav.tmdbapp.utils.api_calls.*
import gr.mpav.tmdbapp.utils.database.DBShow
import gr.mpav.tmdbapp.utils.general.Constants

class DetailsActivity : BaseActivity() {

    private lateinit var mToolbar:Toolbar
    private lateinit var mDetailsRecycler:RecyclerView
    private lateinit var mAdapter:DetailsAdapter

    private val mRepo: TMDBRepository = TMDBRepository.instance

    private var mDBShow:DBShow = DBShow()

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        mDBShow = intent.getSerializableExtra(Constants.SHOW_SELECTED) as DBShow
        setUpViews()
        getShowDetails()
    }

    override fun setUpViews() {
        super.setUpViews()
        mToolbar = findViewById(R.id.mainToolbar)
        mDetailsRecycler = findViewById(R.id.show_details_recycler)
        mDetailsRecycler.layoutManager = LinearLayoutManager(this)
        mAdapter = DetailsAdapter()
        mDetailsRecycler.adapter = mAdapter
    }

    private fun getShowDetails(){
        showProgressView()
        mRepo.getShowDetails(object : OnGetShowDetailsCallback {
            override fun onSuccess(showDetails: ShowDetails) {
                setShowDetails(showDetails)
                getShowVideos()
            }
            override fun onError() {
                hideProgressView()
                handleServiceExternalFailure()
            }
        },mDBShow.ShowType,mDBShow.Id)
    }

    private fun getShowVideos(){
        mRepo.getShowVideos(object : OnGetShowVideosCallback {
            override fun onSuccess(videos:ArrayList<ShowVideo>) {
                hideProgressView()
                if(videos.isNotEmpty())
                    setShowVideo(videos[0].key)
                setAdapterData()
            }
            override fun onError() {
                hideProgressView()
                handleServiceExternalFailure()
            }
        },mDBShow.ShowType,mDBShow.Id)
    }

    private fun setShowDetails(showDetails: ShowDetails){
        mDBShow.BackdropPath = showDetails.backdropPath?: ""
        mDBShow.PosterPath = showDetails.posterPath?: ""
        mDBShow.Title = if(mDBShow.ShowType == Constants.MOVIE_TYPE) showDetails.movieTitle?: "" else showDetails.tvShowTitle?: ""
        mDBShow.VoteAverage = showDetails.rating?: 0.0f
        mDBShow.ReleaseDate = if(mDBShow.ShowType == Constants.MOVIE_TYPE) showDetails.movieReleaseDate?:"" else showDetails.tvShowReleaseDate?:""
        mDBShow.Overview = showDetails.overview?: ""
        if(showDetails.genres != null && showDetails.genres!!.isNotEmpty())
        {
            mDBShow.Genre = showDetails.genres!![0].name
        }
        else
            mDBShow.Genre = ""

        mToolbar.title = mDBShow.Title
    }

    private fun setShowVideo(trailerKey:String)
    {
        mDBShow.TrailerKey = trailerKey
    }

    private fun setAdapterData(){
        // Show details are divided in sections , as different adapter items
        val adapterData:ArrayList<DetailAdapterItem> = ArrayList()
        adapterData.add(HeaderItem(mDBShow.BackdropPath,mDBShow.PosterPath,mDBShow.Title,mDBShow.ReleaseDate,mDBShow.VoteAverage,mDBShow.Genre))
        adapterData.add(WatchListItem(mDBShow))
        adapterData.add(DescriptionItem(mDBShow.Overview))
        if(mDBShow.TrailerKey != null && mDBShow.TrailerKey!!.isNotEmpty())
            adapterData.add(TrailerItem(mDBShow.TrailerKey!!))
        mAdapter.setData(adapterData)
    }

    override fun onDestroy() {
        // release the YouTubePlayerView when we are not using it, by calling YouTubePlayerView.release()
        super.onDestroy()
        mAdapter.releaseTrailerListeners()
    }

}
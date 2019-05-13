package gr.mpav.tmdbapp.activities

import android.os.Bundle
import gr.mpav.tmdbapp.R

class DetailsActivity : BaseActivity() {

    private var mShowId:Int = -1
    private var mShowType:String = ""

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        mShowId = intent.getIntExtra("showId",-1)
        mShowType = intent.getStringExtra("showType")

    }
}
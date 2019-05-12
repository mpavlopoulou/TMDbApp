package gr.mpav.tmdbapp.utils.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import gr.mpav.tmdbapp.R
import gr.mpav.tmdbapp.utils.Constants
import gr.mpav.tmdbapp.utils.api_calls.Show
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class ShowsAdapter(private val context: Context) : RecyclerView.Adapter<ShowsAdapter.ViewHolder>(), View.OnClickListener
{
    private var mShows = ArrayList<Show>()
    private var mRecyclerView: RecyclerView? = null
    private var mListener: ItemClickedListener? = null


    class ViewHolder internal constructor(v: View) : RecyclerView.ViewHolder(v) {
        internal var mPosterImageView: ImageView = v.findViewById(R.id.item_show_poster)
        internal var mReleaseDate: TextView = v.findViewById(R.id.item_show_release_date)
        internal var mTitle: TextView = v.findViewById(R.id.item_show_title)
        internal var mOverView: TextView = v.findViewById(R.id.overview_text)
        internal var mRatings: TextView = v.findViewById(R.id.item_show_rating)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
    {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.show_item_layout, parent, false)
        v.setOnClickListener(this)
        return ViewHolder(v)
    }

    fun setData(shows: ArrayList<Show>)
    {
        mShows = shows
        notifyDataSetChanged()
    }

    @SuppressLint("CheckResult")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val show = mShows[position]

        // Image
        val requestOptions = RequestOptions()
        requestOptions.placeholder(R.drawable.ic_show_icon)
        requestOptions.error(R.drawable.ic_show_icon)
        requestOptions.centerCrop()
        Glide.with(context).setDefaultRequestOptions(requestOptions).load(Constants.IMAGE_BASE_URL+"/"+show.posterPath).into(holder.mPosterImageView)

        // Release date
        val apiFormat = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
        val showFormat = SimpleDateFormat("MMM dd , yyyy", Locale.ENGLISH)
        val apiDateValue = if (show.mediaType == Constants.MOVIE_TYPE)
            show.movieReleaseDate
        else
            show.tvShowFirstAirDate
        try {
            val date = apiFormat.parse(apiDateValue)
            holder.mReleaseDate.text = showFormat.format(date)
        } catch (e: ParseException) {
            e.printStackTrace()
        }


        if(show.mediaType == Constants.MOVIE_TYPE){
            holder.mTitle.text = show.movieTitle
            holder.mTitle.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_movie_type, 0, 0, 0)
        } else {
            holder.mTitle.text = show.tvShowName
            holder.mTitle.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_tv_show_type, 0, 0, 0)
        }
        holder.mTitle.compoundDrawablePadding = 8

        holder.mRatings.text = show.rating.toString()
        holder.mOverView.text = show.overview
    }

    override fun getItemCount(): Int {
        return mShows.size
    }

    override fun onClick(v: View) {
        val position = mRecyclerView?.getChildAdapterPosition(v)
        mListener!!.itemClicked(mShows[position ?: 0])
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        mRecyclerView = recyclerView
    }

    fun setItemClickedListener(listener: ItemClickedListener) {
        mListener = listener
    }

    interface ItemClickedListener {
        fun itemClicked(item: Show)
    }
}
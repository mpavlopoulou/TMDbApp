package gr.mpav.tmdbapp.utils.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import gr.mpav.tmdbapp.R
import gr.mpav.tmdbapp.utils.general.Constants
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

interface ShowsAdapterListener{
    fun onShowSelected(showId:Int , showType:String)
    fun loadNextPageShows(pageNumber: Int)
}

data class ShowAdapterItem(val id:Int, val posterPath:String, val releaseDate:String, val mediaType:String, val title:String, val rating:Float,
                           val overview:String)

class ShowsAdapter(private val context: Context) : RecyclerView.Adapter<ShowsAdapter.ViewHolder>()
{
    private var mShows = ArrayList<ShowAdapterItem>()
    private var mCurrentPage:Int = -1
    private var mTotalPages:Int = -1
    private var mListener: ShowsAdapterListener? = null

    fun setListener(listener:ShowsAdapterListener){
        mListener = listener
    }

    class ViewHolder internal constructor(v: View) : RecyclerView.ViewHolder(v) {
        internal var mPosterImageView: ImageView = v.findViewById(R.id.item_show_poster)
        internal var mReleaseDate: TextView = v.findViewById(R.id.item_show_release_date)
        internal var mTitle: TextView = v.findViewById(R.id.item_show_title)
        internal var mOverView: TextView = v.findViewById(R.id.overview_text)
        internal var mRatings: TextView = v.findViewById(R.id.item_show_rating)
        internal var mCard:CardView = v.findViewById(R.id.card_view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
    {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.show_item_layout, parent, false)
        return ViewHolder(v)
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

        try {
            val date = apiFormat.parse(show.releaseDate)
            holder.mReleaseDate.text = showFormat.format(date)
        } catch (e: ParseException) {
            e.printStackTrace()
        }


        holder.mTitle.text = show.title
        if(show.mediaType == Constants.MOVIE_TYPE)
            holder.mTitle.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_movie_type, 0, 0, 0)
        else
            holder.mTitle.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_tv_show_type, 0, 0, 0)
        holder.mTitle.compoundDrawablePadding = 8

        holder.mRatings.text = show.rating.toString()
        holder.mOverView.text = show.overview

        // Check if we need to load next page shows
        if(position == mShows.size - Constants.LOAD_MORE_ITEMS_THRESHOLD && mCurrentPage!= mTotalPages){
            mListener?.loadNextPageShows(mCurrentPage+1)
        }

        holder.mCard.setOnClickListener {
            mListener?.onShowSelected(mShows[holder.adapterPosition].id,mShows[holder.adapterPosition].mediaType)
        }

    }

    override fun getItemCount(): Int {
        return mShows.size
    }

    fun setData(shows: ArrayList<ShowAdapterItem>,pageNumber:Int, totalPages:Int)
    {
        if(mCurrentPage ==-1){
            mShows = shows
            mCurrentPage = pageNumber
            mTotalPages = totalPages
            notifyDataSetChanged()
        }
        else
            appendShows(pageNumber,shows)
    }

    fun clearData(){
        mShows.clear()
        mCurrentPage = -1
        mTotalPages = -1
        notifyDataSetChanged()
    }

    fun appendShows(pageNumber: Int, showsToAppend: ArrayList<ShowAdapterItem>) {
        mCurrentPage = pageNumber
        mShows.addAll(showsToAppend)
        notifyDataSetChanged()
    }
}
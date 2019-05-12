package gr.mpav.tmdbapp.utils.adapters

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

class ShowsAdapter (val context: Context) : RecyclerView.Adapter<ShowsAdapter.ViewHolder>(), View.OnClickListener
{
    private var mShows = ArrayList<Show>()
    private var mRecyclerView: RecyclerView? = null
    private var mListener: ItemClickedListener? = null


    class ViewHolder internal constructor(v: View) : RecyclerView.ViewHolder(v) {
        internal var mPosterImageView: ImageView = v.findViewById(R.id.item_show_poster)
        internal var mReleaseDate: TextView = v.findViewById(R.id.item_show_release_date)
        internal var mTitle: TextView = v.findViewById(R.id.item_show_title)
        internal var mMediaType: TextView = v.findViewById(R.id.item_media_type)
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

    override fun onBindViewHolder(holder: ViewHolder, position: Int)
    {
        val show = mShows[position]

        val requestOptions = RequestOptions()
        requestOptions.placeholder(R.drawable.ic_show_icon)
        requestOptions.error(R.drawable.ic_show_icon)
        requestOptions.centerCrop()
        Glide.with(context).setDefaultRequestOptions(requestOptions).load(Constants.IMAGE_BASE_URL+"/"+show.posterPath).into(holder.mPosterImageView)

        if(show.mediaType == Constants.MOVIE_TYPE){
            holder.mReleaseDate.text = show.movieReleaseDate.split("-")[0]
            holder.mTitle.text = show.movieTitle
        }
        else
        {
            holder.mReleaseDate.text = show.tvShowFirstAirDate.split("-")[0]
            holder.mTitle.text = show.tvShowName
        }

        holder.mRatings.text = show.rating.toString()
        holder.mMediaType.text = show.mediaType
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
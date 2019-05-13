package gr.mpav.tmdbapp.utils.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import gr.mpav.tmdbapp.R
import gr.mpav.tmdbapp.utils.adapters.viewholders.*

class DetailsAdapter :RecyclerView.Adapter<RecyclerView.ViewHolder>(), TrailerInterface {

    override fun onVideoViewInitialized(youtubePlayerView: YouTubePlayerView) {
        this.youtubePlayerView = youtubePlayerView
    }

    companion object {
        private const val TYPE_HEADER = 0
        private const val TYPE_WATCHLIST = 1
        private const val TYPE_DESCRIPTION = 2
        private const val TYPE_TRAILER = 3
    }

    private var mDetailItems = ArrayList<DetailAdapterItem>()
    private var youtubePlayerView: YouTubePlayerView? = null

    fun setData(data:ArrayList<DetailAdapterItem>){
        mDetailItems = data
        notifyDataSetChanged()
    }

    fun releaseTrailerListeners(){
        youtubePlayerView?.release()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(viewGroup.context)
        val view: View

        when (viewType) {
            TYPE_HEADER -> {
                view = inflater.inflate(R.layout.show_posters_layout, viewGroup, false)
                return HeaderViewHolder(view)
            }
            TYPE_WATCHLIST -> {
                view = inflater.inflate(R.layout.show_watchlist_layout, viewGroup, false)
                return WatchListViewHolder(view)
            }
            TYPE_DESCRIPTION -> {
                view = inflater.inflate(R.layout.show_overview_layout, viewGroup, false)
                return DescriptionViewHolder(view)
            }
            else -> {
                view = inflater.inflate(R.layout.show_trailer_layout, viewGroup, false)
                val trailerViewHolder = TrailerViewHolder(view)
                trailerViewHolder.setListener(this)
                return trailerViewHolder
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewType = getItemViewType(position)

        if (viewType == TYPE_HEADER && holder is HeaderViewHolder)
            holder.initView(mDetailItems[position] as HeaderItem)
        else if (viewType == TYPE_WATCHLIST && holder is WatchListViewHolder)
            holder.initView(mDetailItems[position] as WatchListItem)
        else if (viewType == TYPE_DESCRIPTION && holder is DescriptionViewHolder)
            holder.initView(mDetailItems[position] as DescriptionItem)
        else if (viewType == TYPE_TRAILER && holder is TrailerViewHolder)
            holder.initView(mDetailItems[position] as TrailerItem)
    }

    override fun getItemCount(): Int {
        return mDetailItems.size
    }

    override fun getItemViewType(position: Int): Int {
        return when {
            mDetailItems[position] is HeaderItem -> TYPE_HEADER
            mDetailItems[position] is WatchListItem -> TYPE_WATCHLIST
            mDetailItems[position] is DescriptionItem -> TYPE_DESCRIPTION
            else -> TYPE_TRAILER
        }
    }

}
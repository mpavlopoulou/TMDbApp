package gr.mpav.tmdbapp.utils.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import gr.mpav.tmdbapp.R
import gr.mpav.tmdbapp.utils.adapters.viewholders.DescriptionViewHolder
import gr.mpav.tmdbapp.utils.adapters.viewholders.HeaderViewHolder
import gr.mpav.tmdbapp.utils.adapters.viewholders.TrailerViewHolder
import gr.mpav.tmdbapp.utils.adapters.viewholders.WatchListViewHolder

class DetailsAdapter (val context: Context) :RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val TYPE_HEADER = 0
        private const val TYPE_WATCHLIST = 1
        private const val TYPE_DESCRIPTION = 2
        private const val TYPE_TRAILER = 3
    }

    private var mDetailItems = ArrayList<DetailAdapterItem>()

    fun setData(data:ArrayList<DetailAdapterItem>){
        mDetailItems = data
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
                return TrailerViewHolder(view)
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
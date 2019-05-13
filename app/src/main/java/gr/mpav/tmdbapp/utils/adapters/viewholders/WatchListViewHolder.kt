package gr.mpav.tmdbapp.utils.adapters.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import gr.mpav.tmdbapp.R
import gr.mpav.tmdbapp.utils.adapters.WatchListItem

class WatchListViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val mWatchListButton: MaterialButton = itemView.findViewById(R.id.watchlist_button)

    fun initView(watchListItem: WatchListItem)
    {
        mWatchListButton.setOnClickListener {
            // todo
        }
    }

}
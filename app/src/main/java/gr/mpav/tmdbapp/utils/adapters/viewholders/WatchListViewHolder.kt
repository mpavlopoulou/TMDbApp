package gr.mpav.tmdbapp.utils.adapters.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import gr.mpav.tmdbapp.R
import gr.mpav.tmdbapp.utils.adapters.WatchListItem
import gr.mpav.tmdbapp.utils.database.DbRepo

class WatchListViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val mWatchListButton: MaterialButton = itemView.findViewById(R.id.add_to_watchlist_button)

    fun initView(watchListItem: WatchListItem)
    {
        val dbRepo = DbRepo(itemView.context)

        if(dbRepo.isShowInWatchlist(watchListItem.mShow.Id))
            mWatchListButton.text = itemView.context.resources.getString(R.string.remove_from_watchlist)
        else
            mWatchListButton.text = itemView.context.resources.getString(R.string.add_to_watchlist)

        mWatchListButton.setOnClickListener {
            if(dbRepo.isShowInWatchlist(watchListItem.mShow.Id))
                dbRepo.deleteShowFromWatchlist(watchListItem.mShow.Id)
            else
                dbRepo.addShowInWatchlist(watchListItem.mShow)

            if(dbRepo.isShowInWatchlist(watchListItem.mShow.Id))
                mWatchListButton.text = itemView.context.resources.getString(R.string.remove_from_watchlist)
            else
                mWatchListButton.text = itemView.context.resources.getString(R.string.add_to_watchlist)

        }
    }

}
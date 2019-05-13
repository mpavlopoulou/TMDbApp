package gr.mpav.tmdbapp.utils.adapters.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import gr.mpav.tmdbapp.R
import gr.mpav.tmdbapp.utils.adapters.TrailerItem
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener



class TrailerViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val youtubePlayerView: YouTubePlayerView = itemView.findViewById(R.id.trailer_view)

    fun initView(trailerItem: TrailerItem)
    {
        youtubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                val videoId = trailerItem.key
                youTubePlayer.loadVideo(videoId, 0f)
            }
        })
    }
}
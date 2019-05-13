package gr.mpav.tmdbapp.utils.adapters.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import gr.mpav.tmdbapp.R
import gr.mpav.tmdbapp.utils.adapters.TrailerItem
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerCallback

interface TrailerInterface{
    fun onVideoViewInitialized(youtubePlayerView:YouTubePlayerView)
}

class TrailerViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun setListener(listener:TrailerInterface){
        mListner = listener
    }
    private val youtubePlayerView: YouTubePlayerView = itemView.findViewById(R.id.trailer_view)
    private var mListner:TrailerInterface? = null

    fun initView(trailerItem: TrailerItem)
    {
        youtubePlayerView.enableAutomaticInitialization
        youtubePlayerView.getYouTubePlayerWhenReady(object :YouTubePlayerCallback{
            override fun onYouTubePlayer(youTubePlayer: YouTubePlayer) {
                val videoId = trailerItem.key
                youTubePlayer.cueVideo(videoId, 0f)
                mListner?.onVideoViewInitialized(youtubePlayerView)
            }
        })

    }
}
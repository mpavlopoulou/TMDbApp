package gr.mpav.tmdbapp.utils.adapters.viewholders

import android.annotation.SuppressLint
import android.opengl.Visibility
import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.chip.Chip
import gr.mpav.tmdbapp.R
import gr.mpav.tmdbapp.utils.adapters.HeaderItem
import gr.mpav.tmdbapp.utils.general.Constants
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class HeaderViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView)
{
    private val backDropImage:AppCompatImageView = itemView.findViewById(R.id.backdrop_image)
    private val posterImage:AppCompatImageView = itemView.findViewById(R.id.poster_image)
    private val titleText:AppCompatTextView = itemView.findViewById(R.id.title_text)

    private val dateChip:Chip = itemView.findViewById(R.id.chip_release_date)
    private val genreChip:Chip = itemView.findViewById(R.id.chip_first_genre)
    private val ratingChip:Chip = itemView.findViewById(R.id.chip_vote_average)

    @SuppressLint("CheckResult")
    fun initView(headerItem: HeaderItem)
    {
        val backDropRequestOptions = RequestOptions()
        backDropRequestOptions.placeholder(android.R.color.transparent)
        backDropRequestOptions.error(android.R.color.transparent)
        backDropRequestOptions.centerCrop()
        Glide.with(itemView.context).setDefaultRequestOptions(backDropRequestOptions).load(Constants.IMAGE_BASE_URL+"/"+headerItem.backDropPath).into(backDropImage)

        val posterRequestOptions = RequestOptions()
        posterRequestOptions.placeholder(R.drawable.ic_show_icon)
        posterRequestOptions.error(R.drawable.ic_show_icon)
        posterRequestOptions.centerCrop()
        Glide.with(itemView.context).setDefaultRequestOptions(posterRequestOptions).load(Constants.IMAGE_BASE_URL+"/"+headerItem.posterPath).into(posterImage)

        titleText.text = headerItem.title

        // Release date
        val apiFormat = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
        val showFormat = SimpleDateFormat("MMM dd , yyyy", Locale.ENGLISH)
        try {
            val date = apiFormat.parse(headerItem.releaseDate)
            dateChip.text = showFormat.format(date)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        genreChip.text = headerItem.genre
        ratingChip.text = headerItem.rating.toString()

        dateChip.visibility = if(dateChip.text.isEmpty()) View.GONE else View.VISIBLE
        genreChip.visibility = if(genreChip.text.isEmpty()) View.GONE else View.VISIBLE
        ratingChip.visibility = if(ratingChip.text.isEmpty()) View.GONE else View.VISIBLE
    }
}
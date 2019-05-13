package gr.mpav.tmdbapp.utils.adapters.viewholders

import android.annotation.SuppressLint
import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import gr.mpav.tmdbapp.R
import gr.mpav.tmdbapp.utils.adapters.DescriptionItem

class DescriptionViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val descriptionText: AppCompatTextView = itemView.findViewById(R.id.overview_text)

    @SuppressLint("CheckResult")
    fun initView(descriptionItem: DescriptionItem)
    {
        descriptionText.text = descriptionItem.overview
    }
}
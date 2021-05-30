package com.jaeyoung.tapasassignment.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import androidx.core.view.updateLayoutParams
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.jaeyoung.tapasassignment.SeriesDetailActivity
import com.jaeyoung.tapasassignment.data.SeriesModel
import com.jaeyoung.tapasassignment.databinding.ListItemSeriesBinding
import com.jaeyoung.tapasassignment.utilities.convertCount
import com.jaeyoung.tapasassignment.utilities.onThrottleClick

class SeriesListAdapter(
    private val items: MutableList<SeriesModel> = mutableListOf()
) : RecyclerView.Adapter<SeriesListAdapter.SeriesListViewHolder>() {

    fun setItems(items: List<SeriesModel>, isClear: Boolean = false) {
        if (isClear) this.items.clear()
        this.items.addAll(items)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeriesListViewHolder {
        return SeriesListViewHolder(ListItemSeriesBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))
    }

    override fun onBindViewHolder(holder: SeriesListViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    inner class SeriesListViewHolder(
        private val binding: ListItemSeriesBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: SeriesModel) {
            val isNullBookCoverUrl = item.book_cover_url.isNullOrEmpty()

            binding.cardBookCover.updateLayoutParams<ConstraintLayout.LayoutParams> {
                dimensionRatio = if (isNullBookCoverUrl) "1:1" else "1:1.5"
            }

            Glide.with(binding.root)
                .load(item.book_cover_url ?: item.thumb.file_url)
                .thumbnail(.1f)
                .into(binding.imageBookCover)

            binding.textTitle.isVisible = isNullBookCoverUrl
            binding.textTitle.text = item.title
            binding.textGenre.text = item.genre.name
            binding.textLike.text = convertCount(item.like_cnt)

            binding.root.onThrottleClick {
                it.context.run {
                    startActivity(Intent(this, SeriesDetailActivity::class.java).apply {
                        putExtra("series", item)
                    })
                }
            }
        }
    }
}
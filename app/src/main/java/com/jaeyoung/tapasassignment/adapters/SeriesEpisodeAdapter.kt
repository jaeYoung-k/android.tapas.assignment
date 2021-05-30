package com.jaeyoung.tapasassignment.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jaeyoung.tapasassignment.R
import com.jaeyoung.tapasassignment.data.EpisodeModel
import com.jaeyoung.tapasassignment.databinding.ListItemEpisodeBinding
import com.jaeyoung.tapasassignment.utilities.convertCount
import com.jaeyoung.tapasassignment.utilities.convertDate

class SeriesEpisodeAdapter(
    private val items: MutableList<EpisodeModel> = mutableListOf()
) : RecyclerView.Adapter<SeriesEpisodeAdapter.SeriesEpisodeViewHolder>() {

    fun setItems(items: List<EpisodeModel>, isClear: Boolean = false) {
        if (isClear) this.items.clear()
        this.items.addAll(items)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeriesEpisodeViewHolder {
        return SeriesEpisodeViewHolder(ListItemEpisodeBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))
    }

    override fun onBindViewHolder(holder: SeriesEpisodeViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    inner class SeriesEpisodeViewHolder(
        private val binding: ListItemEpisodeBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: EpisodeModel) {
            Glide.with(binding.imageEpisodeThumb)
                .load(item.thumb.file_url)
                .thumbnail(.1f)
                .into(binding.imageEpisodeThumb)

            binding.textEpisodeScene.text = String.format(
                binding.root.context.getString(R.string.scene_episode),
                item.scene
            )

            binding.textEpisodeTitle.text = item.title
            binding.textViewCount.text = convertCount(item.view_cnt)
            binding.textEpisodeSchedule.text = convertDate(item.created_date, "dd MMM yyyy")
        }
    }
}
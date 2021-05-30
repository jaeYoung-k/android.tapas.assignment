package com.jaeyoung.tapasassignment

import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import androidx.core.view.updateLayoutParams
import androidx.core.view.updatePadding
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.jaeyoung.tapasassignment.adapters.SeriesEpisodeAdapter
import com.jaeyoung.tapasassignment.data.SeriesModel
import com.jaeyoung.tapasassignment.databinding.ActivitySeriesDetailBinding
import com.jaeyoung.tapasassignment.utilities.*
import com.jaeyoung.tapasassignment.viewmodels.SeriesDetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.abs

@AndroidEntryPoint
class SeriesDetailActivity: AppCompatActivity() {
    private val adapter = SeriesEpisodeAdapter()
    private val viewModel: SeriesDetailViewModel by viewModels()
    private val binding: ActivitySeriesDetailBinding by lazy {
        ActivitySeriesDetailBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val series = intent.getParcelableExtra<SeriesModel>("series")

        if (series == null) {
            Toast.makeText(this, getString(R.string.invalid_access_message), Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        setSupportActionBar(binding.toolbarTop)
        supportActionBar?.title = ""

        binding.imageBack.onThrottleClick { finish() }
        binding.textToolbarTitle.text = series.title

        binding.appbarTop.addOnOffsetChangedListener(
            AppBarLayout.OnOffsetChangedListener { appBarLayout, offset ->
                val percentage = 1 - abs(offset).toFloat() / appBarLayout.totalScrollRange
                binding.layoutSeries.alpha = percentage
                binding.textToolbarTitle.isVisible = percentage == 0f
            }
        )

        val statusHeight = getStatusBarHeight()
        binding.toolbarTop.updateLayoutParams<CollapsingToolbarLayout.LayoutParams> {
            setMargins(0, statusHeight, 0, 0)
        }
        binding.layoutSeriesContainer.updatePadding(top = statusHeight)
        binding.layoutSeriesContainer.setBackgroundColor(Color.parseColor(series.rgb_hex))

        val isNullBookCoverUrl = series.book_cover_url.isNullOrEmpty()
        binding.cardBookCover.updateLayoutParams<ConstraintLayout.LayoutParams> {
            dimensionRatio = if (isNullBookCoverUrl) "1:1" else "1:1.5"
        }
        Glide.with(binding.root)
            .load(series.book_cover_url ?: series.thumb.file_url)
            .thumbnail(.1f)
            .into(binding.imageBookCover)

        binding.textGenre.text = series.genre.name
        binding.textTitle.text = series.title
        binding.textCreator.text = convertCreatorListToString(series.creators)
        binding.textViewCount.text = convertCount(series.view_cnt)
        binding.textLikeCount.text = convertCount(series.like_cnt)
        binding.textSubscribeCount.text = convertCount(series.subscribe_cnt)

        binding.recyclerEpisode.layoutManager = LinearLayoutManager(this).apply {
            orientation = LinearLayoutManager.VERTICAL
        }
        binding.recyclerEpisode.adapter = adapter

        viewModel.episodes.observe(this) {
            binding.textLoading.isVisible = it.status == Status.LOADING
            if (it.status == Status.SUCCESS) {
                it.data?.run {
                    adapter.setItems(this)
                    adapter.notifyDataSetChanged()

                    binding.textEpisodeCount.text = String.format(getString(R.string.total_episodes), this.size)
                }
            } else if (it.status == Status.ERROR) {
                Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.loadSeriesAndEpisodes(series.id)
    }
}
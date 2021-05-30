package com.jaeyoung.tapasassignment

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.core.view.updatePadding
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.jaeyoung.tapasassignment.adapters.SeriesListAdapter
import com.jaeyoung.tapasassignment.databinding.ActivitySeriesListBinding
import com.jaeyoung.tapasassignment.utilities.Status
import com.jaeyoung.tapasassignment.utilities.getStatusBarHeight
import com.jaeyoung.tapasassignment.viewmodels.SeriesListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SeriesListActivity : AppCompatActivity() {
    private val adapter = SeriesListAdapter()
    private val viewModel: SeriesListViewModel by viewModels()
    private val binding: ActivitySeriesListBinding by lazy {
        ActivitySeriesListBinding.inflate(layoutInflater)
    }

    private var page = 1
    private var hasNext = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.recyclerSeries.updatePadding(top = binding.recyclerSeries.paddingTop + getStatusBarHeight())
        binding.recyclerSeries.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        binding.recyclerSeries.adapter = adapter

        binding.recyclerSeries.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                if (!recyclerView.canScrollVertically(1)
                    && !binding.progressLoading.isVisible
                    && hasNext) {
                    viewModel.loadSeriesList("COMICS", page)
                }
            }
        })

        viewModel.series.observe(this) {
            binding.textLoading.isVisible = false
            binding.progressLoading.isVisible = it.status == Status.LOADING

            if (it.status == Status.SUCCESS) {
                it.data?.run {
                    adapter.setItems(this.series)

                    val start = (page - 1) * 10
                    val end = if (series.size == 10) page * 10 else start + series.size
                    adapter.notifyItemRangeChanged(start, end)

                    page = pagination.page
                    hasNext = pagination.has_next
                }
            } else if(it.status == Status.ERROR) {
                val message = it.message
                    ?: if (it.message_id != null) getString(it.message_id) else ""
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.loadSeriesList("COMICS", page)
    }
}
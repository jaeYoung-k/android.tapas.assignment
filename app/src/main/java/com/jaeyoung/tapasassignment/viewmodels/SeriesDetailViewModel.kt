package com.jaeyoung.tapasassignment.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jaeyoung.tapasassignment.R
import com.jaeyoung.tapasassignment.api.SeriesService
import com.jaeyoung.tapasassignment.data.DetailModel
import com.jaeyoung.tapasassignment.data.EpisodeModel
import com.jaeyoung.tapasassignment.data.SeriesModel
import com.jaeyoung.tapasassignment.utilities.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.functions.BiFunction
import io.reactivex.rxjava3.functions.Function
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class SeriesDetailViewModel @Inject constructor(
    private val service: SeriesService
) : BaseViewModel() {

    private val _episodes = MutableLiveData<Resource<List<EpisodeModel>>>()
    val episodes: LiveData<Resource<List<EpisodeModel>>>
        get() = _episodes

    fun loadSeriesAndEpisodes(id: Int) {
        _episodes.postValue(Resource.loading(null))

        addDisposable(
            service.getEpisodes(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (it.isSuccessful) {
                        _episodes.postValue(Resource.success(it.body()))
                    } else {
                        _episodes.postValue(Resource.error(it.errorBody().toString(), null))
                    }
                }) {
                    _episodes.postValue(Resource.error(R.string.common_error_message, null))
                }
        )
    }
}
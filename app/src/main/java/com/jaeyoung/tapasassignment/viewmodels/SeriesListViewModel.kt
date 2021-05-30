package com.jaeyoung.tapasassignment.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jaeyoung.tapasassignment.api.SeriesService
import com.jaeyoung.tapasassignment.data.BrowseModel
import com.jaeyoung.tapasassignment.utilities.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class SeriesListViewModel @Inject constructor(
    private val service: SeriesService
) : BaseViewModel() {

    private val _series = MutableLiveData<Resource<BrowseModel>>()
    val series: LiveData<Resource<BrowseModel>>
        get() = _series

    fun loadSeriesList(seriesType: String, page: Int) {
        if (page > 1) _series.postValue(Resource.loading(null))

        addDisposable(
            service.browseFresh(seriesType, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (it.isSuccessful) {
                        _series.postValue(Resource.success(it.body()))
                    } else {
                        _series.postValue(Resource.error(it.errorBody().toString(), null))
                    }
                }) {
                    _series.postValue(Resource.error("다시 시도 해주세요.", null))
                }
        )
    }
}
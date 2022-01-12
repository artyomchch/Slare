package tennisi.borzot.strada.fragments.news.presentation.newsFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import tennisi.borzot.strada.fragments.news.domain.entity.NewsItem
import tennisi.borzot.strada.fragments.news.domain.usecases.GetNewsListUseCase
import tennisi.borzot.strada.fragments.news.domain.usecases.UpdateNewsListUseCase
import javax.inject.Inject

class NewsFragmentViewModel @Inject constructor(
    private val getNewsListUseCase: GetNewsListUseCase,
    private val updateNewsListUseCase: UpdateNewsListUseCase
) : ViewModel() {


    private val _newsItems = MutableLiveData<List<NewsItem>>()
    val newsItems: LiveData<List<NewsItem>>
        get() = _newsItems

    private val _stateLoading = MutableLiveData<Boolean>()
    val stateLoading: LiveData<Boolean>
        get() = _stateLoading

    init {
        viewModelScope.launch {
            _stateLoading.value = true
            _newsItems.value = getNewsListUseCase.invoke()
            _stateLoading.value = false
        }
    }


    fun updateNewsList() {
        viewModelScope.launch {
            _newsItems.value = updateNewsListUseCase.invoke()
        }
    }


}
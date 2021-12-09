package tennisi.borzot.strada.fragments.news.presentation.newsFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import tennisi.borzot.strada.fragments.news.data.repository.NewsListRepositoryImpl
import tennisi.borzot.strada.fragments.news.domain.usecases.GetNewsListUseCase
import tennisi.borzot.strada.fragments.news.domain.usecases.UpdateNewsListUseCase
import tennisi.borzot.strada.fragments.news.data.network.pojo.Article

class NewsFragmentViewModel : ViewModel() {

    private val repository = NewsListRepositoryImpl
    private val getNewsListUseCase = GetNewsListUseCase(repository)
    private val updateNewsListUseCase = UpdateNewsListUseCase(repository)

    private val _newsItems = MutableLiveData<List<Article>>()
    val newsItems: LiveData<List<Article>>
        get() = _newsItems

    init {
        viewModelScope.launch {
            _newsItems.value = getNewsListUseCase.invoke()
        }
    }


    fun updateNewsList(){
        viewModelScope.launch {
            _newsItems.value = updateNewsListUseCase.invoke()
        }
    }


}
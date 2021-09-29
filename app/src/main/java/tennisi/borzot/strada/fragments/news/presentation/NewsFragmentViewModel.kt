package tennisi.borzot.strada.fragments.news.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import tennisi.borzot.strada.fragments.news.data.NewsListRepositoryImpl
import tennisi.borzot.strada.network.pojo.Article
import tennisi.borzot.strada.network.pojo.NewsItem

class NewsFragmentViewModel: ViewModel() {

    private val repository = NewsListRepositoryImpl
    val newsResponse: MutableLiveData<List<Article>> = MutableLiveData()

    init {
        getPostNews()
    }

    private fun getPostNews(){
        viewModelScope.launch {
             val response = repository.getPost()
               newsResponse.value = response
        }
    }

}
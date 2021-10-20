package tennisi.borzot.strada.fragments.news.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

import tennisi.borzot.strada.fragments.news.domain.repository.NewsListRepository
import tennisi.borzot.strada.network.RetrofitInstance
import tennisi.borzot.strada.network.pojo.Article

object NewsListRepositoryImpl : NewsListRepository {

    private val newsArticlesLD = MutableLiveData<List<Article>>()
    private var newsList = listOf<Article>()

    init {
        getPostList()
    }

    private suspend fun getPost(): List<Article> {
        return RetrofitInstance.api.getPost().articles
    }

    override fun getNewsList(): LiveData<List<Article>> = newsArticlesLD

    override fun updateNewsList(): LiveData<List<Article>> {
        getPostList()
        return newsArticlesLD
    }



    override fun addNewsList(article: Article) {
        TODO("Not yet implemented")
    }


    private fun updateList() {
        newsArticlesLD.postValue(newsList.toList())
    }

    private fun getPostList(){
        CoroutineScope(context = Dispatchers.IO).launch {
            newsList = emptyList()
            newsList = getPost()
            updateList()
        }
    }

}
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
    private var updateNewsList = hashSetOf<Article>()
    private var newsList1 = listOf<Article>()
    private const val DEFAULT_NUMBER_NEWS = 1
    private var pageSizeNumber = 1
    private val scope = CoroutineScope(Dispatchers.IO)

    init {
        getPostList()
    }

    private suspend fun getPost(pageNumber: Int): List<Article> {
        return RetrofitInstance.api.getPost(page = pageNumber).articles
    }

    override fun getNewsList(): LiveData<List<Article>> = newsArticlesLD

    override fun updateNewsList() {
        updateNewElementNewsItem()
    }


    override fun addNewsList(article: Article) {
        TODO("Not yet implemented")
    }


    private fun updateList() {
        newsArticlesLD.postValue(newsList.toList())
    }

    private fun getPostList() {
       scope.launch {
            newsList = getPost(DEFAULT_NUMBER_NEWS)
            updateList()
        }
    }

    private fun updateNewElementNewsItem() {
        //updateNewsList = newsList.toHashSet()
        scope.launch {
            newsList = getPost(DEFAULT_NUMBER_NEWS)
            updateList()

        }


    }


}
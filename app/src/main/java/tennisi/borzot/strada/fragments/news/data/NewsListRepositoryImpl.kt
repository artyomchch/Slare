package tennisi.borzot.strada.fragments.news.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import tennisi.borzot.strada.fragments.news.domain.NewsListRepository
import tennisi.borzot.strada.network.RetrofitInstance
import tennisi.borzot.strada.network.pojo.Article
import tennisi.borzot.strada.network.pojo.NewsItem

object NewsListRepositoryImpl: NewsListRepository {

    private val newsArticlesLD = MutableLiveData<NewsItem>()

    init {

    }


    suspend fun getPost(): List<Article> {
        return RetrofitInstance.api.getPost().articles
    }

    override fun getNewsList(): LiveData<List<Article>> {
        TODO("Not yet implemented")
    }

}
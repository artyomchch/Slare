package tennisi.borzot.strada.fragments.news.data

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

}
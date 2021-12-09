package tennisi.borzot.strada.fragments.news.data.repository

import org.joda.time.Instant
import tennisi.borzot.strada.fragments.news.domain.repository.NewsListRepository
import tennisi.borzot.strada.fragments.news.data.network.RetrofitInstance
import tennisi.borzot.strada.fragments.news.data.network.pojo.Article


object NewsListRepositoryImpl : NewsListRepository {

    private var newsList = listOf<Article>()
    private var olderTime = String()
    private var newestTime = String()
    private var updateNewsList = listOf<Article>()
    private const val ADD_SECOND = 5

    private val retrofit = RetrofitInstance.api


    override suspend fun getNewsList(): List<Article> {
        newsList = retrofit.getPost().articles
        olderTime = newsList[newsList.size - 1].publishedAt
        newestTime = newsList[0].publishedAt
        return newsList
    }


    override suspend fun updateNewsList(): List<Article> {
        updateNewsList = retrofit.getPost(from = newestNews(newestTime)).articles + newsList
        newsList = updateNewsList
        newestTime = newsList[0].publishedAt
        return newsList
    }


    override suspend fun addNewsList(): List<Article> {
        newsList = newsList + retrofit.getPost(to = oldestNews(olderTime)).articles
        olderTime = newsList[newsList.size - 1].publishedAt
        return newsList
    }

    private fun newestNews(newestTime: String): String {
        val newTime = Instant.parse(newestTime).toDateTime()
        return newTime.plusSeconds(ADD_SECOND).toString()
    }

    private fun oldestNews(oldestTime: String):String{
        val oldTime = Instant.parse(oldestTime).toDateTime()
        return oldTime.minusSeconds(ADD_SECOND).toString()
    }


}
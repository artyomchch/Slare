package tennisi.borzot.strada.fragments.news.data.repository

import org.joda.time.Instant
import tennisi.borzot.strada.fragments.news.data.mapper.NewsItemMapper
import tennisi.borzot.strada.fragments.news.data.network.RetrofitInstance
import tennisi.borzot.strada.fragments.news.data.network.pojo.ArticleDto
import tennisi.borzot.strada.fragments.news.domain.entity.NewsItem
import tennisi.borzot.strada.fragments.news.domain.repository.NewsListRepository


object NewsListRepositoryImpl : NewsListRepository {

    private var newsList = listOf<ArticleDto>()
    private var olderTime = String()
    private var newestTime = String()
    private var updateNewsList = listOf<ArticleDto>()
    private val mapper = NewsItemMapper()
    private const val ADD_SECOND = 5

    private val retrofit = RetrofitInstance.api


    override suspend fun getNewsList(): List<NewsItem> {
        newsList = retrofit.getPost().article
        olderTime = newsList[newsList.size - 1].publishedAt
        newestTime = newsList[0].publishedAt
        return mapper.mapListNetworkModelToListEntityNews(newsList)
    }


    override suspend fun updateNewsList(): List<NewsItem> {
        updateNewsList = retrofit.getPost(from = newestNews(newestTime)).article + newsList
        newsList = updateNewsList
        newestTime = newsList[0].publishedAt
        return mapper.mapListNetworkModelToListEntityNews(updateNewsList)
    }


    override suspend fun addNewsList(): List<NewsItem> {
        newsList = newsList + retrofit.getPost(to = oldestNews(olderTime)).article
        olderTime = newsList[newsList.size - 1].publishedAt
        return mapper.mapListNetworkModelToListEntityNews(newsList)
    }

    private fun newestNews(newestTime: String): String {
        val newTime = Instant.parse(newestTime).toDateTime()
        return newTime.plusSeconds(ADD_SECOND).toString()
    }

    private fun oldestNews(oldestTime: String): String {
        val oldTime = Instant.parse(oldestTime).toDateTime()
        return oldTime.minusSeconds(ADD_SECOND).toString()
    }


}
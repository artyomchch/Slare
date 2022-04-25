package tennisi.borzot.strada.fragments.news.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import org.joda.time.Instant
import tennisi.borzot.strada.fragments.news.data.mapper.NewsItemMapper
import tennisi.borzot.strada.fragments.news.data.network.NewsApi
import tennisi.borzot.strada.fragments.news.data.network.pojo.ArticleDto
import tennisi.borzot.strada.fragments.news.data.pagination.NewsPagingSource
import tennisi.borzot.strada.fragments.news.domain.entity.NewsItem
import tennisi.borzot.strada.fragments.news.domain.repository.NewsListRepository
import javax.inject.Inject


class NewsListRepositoryImpl @Inject constructor(
    private val mapper: NewsItemMapper,
    private val retrofit: NewsApi
) : NewsListRepository {

    private var newsList = listOf<ArticleDto>()
    private var olderTime = String()
    private var newestTime = String()
    private var updateNewsList = listOf<ArticleDto>()


    override suspend fun getNewsList(): List<NewsItem> {
       // newsList = retrofit.getPost().article
        olderTime = newsList[newsList.size - 1].publishedAt
        newestTime = newsList[0].publishedAt
        return mapper.mapListNetworkModelToListEntityNews(newsList)
    }


    override suspend fun updateNewsList(): List<NewsItem> {
       // updateNewsList = retrofit.getPost(from = newestNews(newestTime)).article + newsList
        newsList = updateNewsList
        newestTime = newsList[0].publishedAt
        return mapper.mapListNetworkModelToListEntityNews(updateNewsList)
    }


    override suspend fun addNewsList(): List<NewsItem> {
       // newsList = newsList + retrofit.getPost(to = oldestNews(olderTime)).article
        olderTime = newsList[newsList.size - 1].publishedAt
        return mapper.mapListNetworkModelToListEntityNews(newsList)
    }

    override fun getPagedNews(): Flow<PagingData<NewsItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { NewsPagingSource(retrofit, mapper) }
        ).flow
    }

    fun refreshPagedNews(): Flow<PagingData<NewsItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { NewsPagingSource(retrofit, mapper) }
        ).flow
    }

    private fun newestNews(newestTime: String): String {
        val newTime = Instant.parse(newestTime).toDateTime()
        return newTime.plusSeconds(ADD_SECOND).toString()
    }

    private fun oldestNews(oldestTime: String): String {
        val oldTime = Instant.parse(oldestTime).toDateTime()
        return oldTime.minusSeconds(ADD_SECOND).toString()
    }

    companion object {
        private const val ADD_SECOND = 5
    }


}
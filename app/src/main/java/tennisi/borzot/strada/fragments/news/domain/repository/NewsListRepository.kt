package tennisi.borzot.strada.fragments.news.domain.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import tennisi.borzot.strada.fragments.news.domain.entity.NewsItem


interface NewsListRepository {


    suspend fun getNewsList(): List<NewsItem>

    suspend fun updateNewsList(): List<NewsItem>

    suspend fun addNewsList(): List<NewsItem>

    fun getPagedNews(): Flow<PagingData<NewsItem>>
}
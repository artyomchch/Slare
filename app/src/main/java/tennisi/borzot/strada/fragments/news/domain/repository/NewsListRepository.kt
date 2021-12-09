package tennisi.borzot.strada.fragments.news.domain.repository

import androidx.lifecycle.LiveData
import tennisi.borzot.strada.network.pojo.Article

interface NewsListRepository {

    suspend fun getNewsList(): List<Article>

    suspend fun updateNewsList(): List<Article>

    suspend fun addNewsList(): List<Article>
}
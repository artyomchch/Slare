package tennisi.borzot.strada.fragments.news.domain.repository

import tennisi.borzot.strada.fragments.news.data.network.pojo.Article

interface NewsListRepository {

    suspend fun getNewsList(): List<Article>

    suspend fun updateNewsList(): List<Article>

    suspend fun addNewsList(): List<Article>
}
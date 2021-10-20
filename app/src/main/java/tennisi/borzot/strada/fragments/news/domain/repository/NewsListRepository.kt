package tennisi.borzot.strada.fragments.news.domain.repository

import androidx.lifecycle.LiveData
import tennisi.borzot.strada.network.pojo.Article

interface NewsListRepository {

    fun getNewsList(): LiveData<List<Article>>

    fun updateNewsList(): LiveData<List<Article>>

    fun addNewsList(article: Article)
}
package tennisi.borzot.strada.fragments.news.domain

import androidx.lifecycle.LiveData
import tennisi.borzot.strada.network.pojo.Article

interface NewsListRepository {

    fun getNewsList(): LiveData<List<Article>>
}
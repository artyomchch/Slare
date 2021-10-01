package tennisi.borzot.strada.fragments.news.domain

import androidx.lifecycle.LiveData
import tennisi.borzot.strada.network.pojo.Article

class GetNewsListUseCase(private val newsListRepository: NewsListRepository) {

    fun getNewsList(): LiveData<List<Article>> {
        return newsListRepository.getNewsList()
    }
}
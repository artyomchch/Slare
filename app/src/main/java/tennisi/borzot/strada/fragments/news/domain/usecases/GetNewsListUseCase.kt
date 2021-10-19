package tennisi.borzot.strada.fragments.news.domain.usecases

import androidx.lifecycle.LiveData
import tennisi.borzot.strada.fragments.news.domain.repository.NewsListRepository
import tennisi.borzot.strada.network.pojo.Article

class GetNewsListUseCase(private val newsListRepository: NewsListRepository) {

    operator fun invoke(): LiveData<List<Article>> = newsListRepository.getNewsList()

}
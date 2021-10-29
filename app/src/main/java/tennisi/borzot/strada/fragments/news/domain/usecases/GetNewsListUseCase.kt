package tennisi.borzot.strada.fragments.news.domain.usecases

import tennisi.borzot.strada.fragments.news.domain.repository.NewsListRepository
import tennisi.borzot.strada.network.pojo.Article

class GetNewsListUseCase(private val newsListRepository: NewsListRepository) {

    suspend operator fun invoke(): List<Article> = newsListRepository.getNewsList()

}
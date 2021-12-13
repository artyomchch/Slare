package tennisi.borzot.strada.fragments.news.domain.usecases

import tennisi.borzot.strada.fragments.news.domain.entity.NewsItem
import tennisi.borzot.strada.fragments.news.domain.repository.NewsListRepository

class GetNewsListUseCase(private val newsListRepository: NewsListRepository) {

    suspend operator fun invoke(): List<NewsItem> = newsListRepository.getNewsList()

}
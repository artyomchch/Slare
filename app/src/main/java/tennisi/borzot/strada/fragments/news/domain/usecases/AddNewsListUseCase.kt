package tennisi.borzot.strada.fragments.news.domain.usecases

import tennisi.borzot.strada.fragments.news.domain.repository.NewsListRepository

class AddNewsListUseCase(private val newsListRepository: NewsListRepository) {

    suspend operator fun invoke() = newsListRepository.addNewsList()

}
package tennisi.borzot.strada.fragments.news.domain.usecases

import tennisi.borzot.strada.fragments.news.domain.repository.NewsListRepository

class UpdateNewsListUseCase(private val newsListRepository: NewsListRepository) {

    operator fun invoke() = newsListRepository.updateNewsList()
}
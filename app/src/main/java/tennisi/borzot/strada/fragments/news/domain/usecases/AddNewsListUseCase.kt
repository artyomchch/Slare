package tennisi.borzot.strada.fragments.news.domain.usecases

import tennisi.borzot.strada.fragments.news.domain.repository.NewsListRepository
import javax.inject.Inject

class AddNewsListUseCase @Inject constructor(private val newsListRepository: NewsListRepository) {

    suspend operator fun invoke() = newsListRepository.addNewsList()

}
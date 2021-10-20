package tennisi.borzot.strada.fragments.news.domain.usecases

import tennisi.borzot.strada.fragments.news.domain.repository.NewsListRepository

import tennisi.borzot.strada.network.pojo.Article

class AddNewsListUseCase(private val newsListRepository: NewsListRepository) {

    operator fun invoke(article: Article) {
        newsListRepository.addNewsList(article)
    }
}
package tennisi.borzot.strada.fragments.news.domain.usecases

import androidx.lifecycle.LiveData
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import tennisi.borzot.strada.fragments.news.domain.entity.NewsItem
import tennisi.borzot.strada.fragments.news.domain.repository.NewsListRepository
import javax.inject.Inject

class GetNewsPagingListUseCase @Inject constructor(private val newsListRepository: NewsListRepository) {

    operator fun invoke(): Flow<PagingData<NewsItem>> = newsListRepository.getPagedNews()

}
package tennisi.borzot.strada.fragments.news.presentation.newsFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.Flow
import tennisi.borzot.strada.fragments.news.domain.entity.NewsItem
import tennisi.borzot.strada.fragments.news.domain.usecases.GetNewsPagingListUseCase
import javax.inject.Inject


class NewsFragmentViewModel @Inject constructor(
    private val getNewsPagingListUseCase: GetNewsPagingListUseCase
) : ViewModel() {

    var newsFlow: Flow<PagingData<NewsItem>> = getNewsPagingListUseCase.invoke().cachedIn(viewModelScope)


    fun refresh() {
        this.newsFlow = getNewsPagingListUseCase.invoke().cachedIn(viewModelScope)
    }


}
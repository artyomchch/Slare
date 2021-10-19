package tennisi.borzot.strada.fragments.news.presentation

import androidx.lifecycle.ViewModel
import tennisi.borzot.strada.fragments.news.data.NewsListRepositoryImpl
import tennisi.borzot.strada.fragments.news.domain.usecases.GetNewsListUseCase

class NewsFragmentViewModel : ViewModel() {

    private val repository = NewsListRepositoryImpl
    private val getNewsListUseCase = GetNewsListUseCase(repository)

    val newsList = getNewsListUseCase.invoke()


}
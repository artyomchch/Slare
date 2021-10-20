package tennisi.borzot.strada.fragments.news.presentation

import androidx.lifecycle.ViewModel
import tennisi.borzot.strada.fragments.news.data.NewsListRepositoryImpl
import tennisi.borzot.strada.fragments.news.domain.usecases.GetNewsListUseCase
import tennisi.borzot.strada.fragments.news.domain.usecases.UpdateNewsListUseCase

class NewsFragmentViewModel : ViewModel() {

    private val repository = NewsListRepositoryImpl
    private val getNewsListUseCase = GetNewsListUseCase(repository)
    private val updateNewsListUseCase = UpdateNewsListUseCase(repository)

    val newsList = getNewsListUseCase.invoke()

    //val updateNewsList = updateNewsListUseCase.invoke()
}
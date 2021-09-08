package tennisi.borzot.strada.fragments.news.utils

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

import tennisi.borzot.strada.database.MyApplication
import tennisi.borzot.strada.fragments.news.viewModel.NewsViewModel
import java.lang.IllegalStateException

class ViewModelFactory(
    private val app: MyApplication
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val viewModel = when (modelClass){
            NewsViewModel::class.java -> {
                NewsViewModel(app.usersService)
            }
            else -> {
                throw IllegalStateException("Unknown view model class")
            }
        }
        return viewModel as T
    }
}

fun Fragment.factory() = ViewModelFactory(requireContext().applicationContext as MyApplication)
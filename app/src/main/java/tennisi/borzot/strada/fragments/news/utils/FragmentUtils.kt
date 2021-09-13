package tennisi.borzot.strada.fragments.news.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import tennisi.borzot.strada.database.MyApplication
import tennisi.borzot.strada.fragments.news.Navigator
import tennisi.borzot.strada.fragments.news.viewModel.NewsViewModel

typealias ViewModelCreator = (MyApplication) -> ViewModel?

class ViewModelFactory(
    private val app: MyApplication,
    private val viewModelCreator: ViewModelCreator = { null }
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val viewModel = when (modelClass) {
            NewsViewModel::class.java -> {
                NewsViewModel(app.usersService)
            }

            else -> {
                viewModelCreator(app) ?: throw IllegalStateException("Unknown view model class")

            }
        }
        return viewModel as T
    }
}

fun Fragment.factory() = ViewModelFactory(requireContext().applicationContext as MyApplication)

fun Fragment.navigator() = requireParentFragment() as Navigator

inline fun <reified VM : ViewModel> Fragment.viewModelCreator(noinline creator: ViewModelCreator): Lazy<VM> {
   return viewModels { ViewModelFactory(requireContext().applicationContext as MyApplication, creator) }
}
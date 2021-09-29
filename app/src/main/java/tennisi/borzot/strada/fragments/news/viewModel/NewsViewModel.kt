package tennisi.borzot.strada.fragments.news.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import tennisi.borzot.strada.fragments.news.UserActionListener
import tennisi.borzot.strada.fragments.news.model.User
import tennisi.borzot.strada.fragments.news.model.UsersListener
import tennisi.borzot.strada.fragments.news.model.UsersService
import tennisi.borzot.strada.fragments.news.promisses.EmptyResult
import tennisi.borzot.strada.fragments.news.promisses.PendingResult
import tennisi.borzot.strada.fragments.news.promisses.Result
import tennisi.borzot.strada.fragments.news.promisses.SuccessResult
import tennisi.borzot.strada.network.pojo.NewsItem

data class UserListItem(
    val user: User,
    val isInProgress: Boolean
)

class NewsViewModel(
    private val usersService: UsersService
) : BaseViewModel(), UserActionListener {

    private val _users = MutableLiveData<Result<List<UserListItem>>>()
    val users: LiveData<Result<List<UserListItem>>> = _users

    private val _actionShowDetails = MutableLiveData<Event<User>>()
    val actionShowDetails: LiveData<Event<User>> = _actionShowDetails

    private val _actionShowToast = MutableLiveData<Event<Int>>()
    val actionShowToast: LiveData<Event<Int>> = _actionShowToast

    val newsResponse: MutableLiveData<NewsItem> = MutableLiveData()

    private val userIdsInProgress = mutableSetOf<Long>()
    private var usersResult: Result<List<User>> = EmptyResult()
        set(value) {
            field = value
            notifyUpdates()
        }


    private val listener: UsersListener = {
        usersResult = if (it.isEmpty()) {
            EmptyResult()
        } else {
            SuccessResult(it)
        }
    }

    init {
        usersService.addListener(listener)
        loadPost()
        viewModelScope.launch {
            loadUsers()
        }
    }

    override fun onCleared() {
        super.onCleared()
        usersService.removeListener(listener)
    }

    override fun onUserDetails(user: User) {
        _actionShowDetails.value = Event(user)
    }


    private suspend fun loadUsers() {
        usersResult = PendingResult()
        usersService.loadUsers()
    }

    private fun loadPost() {
        viewModelScope.launch {
            val response = usersService.loadPost()
            newsResponse.value = response
        }
    }

    private fun addProgressTo(user: User) {
        userIdsInProgress.add(user.id)
        notifyUpdates()
    }

    private fun removeProgressFrom(user: User) {
        userIdsInProgress.remove(user.id)
        notifyUpdates()
    }

    private fun isInProgress(user: User): Boolean = userIdsInProgress.contains(user.id)

    private fun notifyUpdates() {
        _users.postValue(usersResult.map { users ->
            users.map { user -> UserListItem(user, isInProgress(user)) }
        })
    }

}












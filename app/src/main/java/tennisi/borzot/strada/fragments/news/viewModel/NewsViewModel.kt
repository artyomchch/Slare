package tennisi.borzot.strada.fragments.news.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import tennisi.borzot.strada.R
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
        viewModelScope.launch {
            loadUsers()
            loadPost()
        }
    }


    override fun onCleared() {
        super.onCleared()
        usersService.removeListener(listener)
    }

    override fun onUserMove(user: User, moveBy: Int): Job = viewModelScope.launch {
        if (isInProgress(user))
            usersResult = PendingResult()
        addProgressTo(user)

        try {
            usersService.moveUser(user, moveBy)
            removeProgressFrom(user)

        } catch (e: Exception) {
            removeProgressFrom(user)
            _actionShowToast.value = Event(R.string.cant_move_user)
        }

    }

    override fun onUserDelete(user: User): Job = viewModelScope.launch {
        if (isInProgress(user))
            usersResult = PendingResult()
        addProgressTo(user)
        usersService.deleteUser(user)
        try {

            removeProgressFrom(user)
        } catch (e: Exception) {
            removeProgressFrom(user)
            _actionShowToast.value = Event(R.string.cant_delete_user)  //  .onSuccess {
        }
    }

    override fun onUserDetails(user: User) {
        _actionShowDetails.value = Event(user)
    }

    override fun onUserFire(user: User): Job = viewModelScope.launch {
        if (isInProgress(user))
            usersResult = PendingResult()
        addProgressTo(user)
        usersService.fireUser(user)
        try {
            removeProgressFrom(user)
        } catch (e: Exception) {
            removeProgressFrom(user)
            _actionShowToast.value = Event(R.string.cant_fire_user)//  .onSuccess {
        }
    }


    private suspend fun loadUsers() {
        usersResult = PendingResult()
        usersService.loadUsers()
    }

    private fun loadPost(){
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












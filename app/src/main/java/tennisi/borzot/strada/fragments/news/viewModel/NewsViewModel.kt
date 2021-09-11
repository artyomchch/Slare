package tennisi.borzot.strada.fragments.news.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.javafaker.Bool
import tennisi.borzot.strada.fragments.news.model.*

data class UserListItem(
    val user: User,
    val isInProgress: Boolean
)


class NewsViewModel(
    private val usersService: UsersService
): ViewModel() {

    private val _users = MutableLiveData<Result<List<UserListItem>>>()
    val users: LiveData<Result<List<UserListItem>>> = _users

    private val userIdsInProgress = mutableListOf<Long>()
    private var userResult: Result<List<User>> = EmptyResult()
        set(value) {
            field = value
                notifyUpdates()
        }



    private val listener: UsersListener = {
        userResult =  if (it.isEmpty()){
            EmptyResult()
        } else{
            SuccessResult(it)
        }

    }

    init {
        usersService.addListener(listener)
        loadUsers()
    }

    override fun onCleared() {
        super.onCleared()
        usersService.removeListener(listener)
    }

    fun loadUsers(){
       userResult = PendingResult()
        usersService.loadUsers()
            .onError {
                userResult = ErrorResult(it)
            }
    }

    fun moveUser(user: User, moveBy: Int){
        if (isInProgress(user)) return
        addProgressTo(user)
        usersService.moveUser(user, moveBy)
            .onSuccess {
                removeProgressFrom(user)
            }
            .onError {
                removeProgressFrom(user)
            }
    }

    fun deleteUser(user: User){
        if (isInProgress(user)) return
        addProgressTo(user)
        usersService.deleteUser(user)
            .onSuccess {
                removeProgressFrom(user)
            }
            .onError {
                removeProgressFrom(user)
            }
    }

    private fun addProgressTo(user: User){
        userIdsInProgress.add(user.id)
        notifyUpdates()
    }

    private fun removeProgressFrom(user: User){
        userIdsInProgress.remove(user.id)
        notifyUpdates()
    }

    private fun isInProgress(user: User): Boolean = userIdsInProgress.contains(user.id)



    private fun notifyUpdates() {
        _users.postValue(userResult.map { users ->
            users.map {user -> UserListItem(user, isInProgress(user))  }
        })
    }



}
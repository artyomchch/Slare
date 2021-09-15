package tennisi.borzot.strada.fragments.news.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import tennisi.borzot.strada.R
import tennisi.borzot.strada.UserNotFoundException
import tennisi.borzot.strada.fragments.news.model.User
import tennisi.borzot.strada.fragments.news.model.UserDetails
import tennisi.borzot.strada.fragments.news.model.UsersService
import tennisi.borzot.strada.fragments.news.promisses.EmptyResult
import tennisi.borzot.strada.fragments.news.promisses.PendingResult
import tennisi.borzot.strada.fragments.news.promisses.Result
import tennisi.borzot.strada.fragments.news.promisses.SuccessResult

class UserDetailsViewModel(
    private val usersService: UsersService,
    private val userId: Long
): BaseViewModel() {

    private val _state = MutableLiveData<State>()
    val state: LiveData<State> = _state

    private val _actionShowToast = MutableLiveData<Event<Int>>()
    val actionShowToast: LiveData<Event<Int>> = _actionShowToast

    private val _actionGoBack = MutableLiveData<Event<Unit>>()
    val actionGoBack: LiveData<Event<Unit>> = _actionGoBack

    private val currentState: State get() = state.value!!

    init {
        _state.value = State(
            userDetailsResult = EmptyResult(),
            deletingInProgress = false
        )
        viewModelScope.launch {
            loadUser()
        }

    }

    fun deleteUser(){
       val userDetailsResult = currentState.userDetailsResult
        if(userDetailsResult !is SuccessResult) return
        _state.value = currentState.copy(deletingInProgress = true)
        usersService.deleteUser(userDetailsResult.data.user)
            .onSuccess {
                _actionShowToast.value = Event(R.string.user_has_been_deleted)
                _actionGoBack.value = Event(Unit)
            }
            .onError {
                _state.value = currentState.copy(deletingInProgress = false)
                _actionShowToast.value = Event(R.string.cant_delete_user)
            }
            .autoCancel()
    }

    private suspend fun loadUser(){
        if (currentState.userDetailsResult !is EmptyResult) return

        _state.value = currentState.copy(userDetailsResult = PendingResult())

        usersService.getById(userId)
            .onSuccess {
                _state.value = currentState.copy(userDetailsResult = SuccessResult(it))
            }
            .onError {
                _actionShowToast.value = Event(R.string.cant_load_user_details)
                _actionGoBack.value = Event(Unit)
            }
            .autoCancel()

    }

    data class State(
        val userDetailsResult: Result<UserDetails>,
        private val deletingInProgress: Boolean

    ){
        val showContent: Boolean get() = userDetailsResult is SuccessResult
        val showProgress: Boolean get() = userDetailsResult is PendingResult || deletingInProgress
        val enableDeleteButton: Boolean get() = !deletingInProgress
    }
}
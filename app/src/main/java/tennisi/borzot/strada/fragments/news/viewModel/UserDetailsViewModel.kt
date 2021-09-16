package tennisi.borzot.strada.fragments.news.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import tennisi.borzot.strada.R
import tennisi.borzot.strada.fragments.news.model.UserDetails
import tennisi.borzot.strada.fragments.news.model.UsersService
import tennisi.borzot.strada.fragments.news.promisses.EmptyResult
import tennisi.borzot.strada.fragments.news.promisses.PendingResult
import tennisi.borzot.strada.fragments.news.promisses.Result
import tennisi.borzot.strada.fragments.news.promisses.SuccessResult

class UserDetailsViewModel(
    private val usersService: UsersService,
    private val userId: Long
) : BaseViewModel() {

    private val _state = MutableLiveData<State>()
    val state: LiveData<State> = _state

    private val _actionShowToast = MutableLiveData<Event<Int>>()
    val actionShowToast: LiveData<Event<Int>> = _actionShowToast

    private val _actionGoBack = MutableLiveData<Event<Unit>>()
    val actionGoBack: LiveData<Event<Unit>> = _actionGoBack

    private val currentState: State get() = state.value!!

    private lateinit var data: UserDetails

    init {
        _state.value = State(
            userDetailsResult = EmptyResult(),
            deletingInProgress = false
        )
        viewModelScope.launch {
            loadUser()
        }

    }

    fun deleteUser() = viewModelScope.launch {
        val userDetailsResult = currentState.userDetailsResult
        if (userDetailsResult !is SuccessResult)
            _state.value?.showProgress
        _state.value?.enableDeleteButton
        _state.value = currentState.copy(deletingInProgress = true)
        usersService.deleteUser(data.user)
        try {
            _state.value?.showProgress
            _actionShowToast.value = Event(R.string.user_has_been_deleted)
            _actionGoBack.value = Event(Unit)
            _state.value?.enableDeleteButton
        } catch (e: Exception) {
            _state.value = currentState.copy(deletingInProgress = false)
            _actionShowToast.value = Event(R.string.cant_delete_user)
        } finally {

        }


        //.autoCancel()
    }

    private suspend fun loadUser() {
        if (currentState.userDetailsResult !is EmptyResult)
            _state.value?.showProgress
            _state.value = currentState.copy(userDetailsResult = PendingResult())
        try {
            data = usersService.getById(userId)
            _state.value = currentState.copy(userDetailsResult = SuccessResult(data))
        } catch (e: Exception) {
            _actionShowToast.value = Event(R.string.cant_load_user_details)
            _actionGoBack.value = Event(Unit)
        } finally {

        }


    }

    data class State(
        val userDetailsResult: Result<UserDetails>,
        private val deletingInProgress: Boolean

    ) {
        val showContent: Boolean get() = userDetailsResult is SuccessResult
        val showProgress: Boolean get() = userDetailsResult is PendingResult || deletingInProgress
        val enableDeleteButton: Boolean get() = !deletingInProgress
    }
}
package tennisi.borzot.strada.fragments.news.promisses

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

typealias Callback<T> = (T) -> Unit
interface Task<T> {

    fun onSuccess(callback: Callback<T>): Task<T>

    fun onError(callback: Callback<Throwable>): Task<T>

    fun cancel()

    fun await(): T

    fun enqueue(dispatcher: Dispatcher, callback: Callback<T>)
    @Suppress("UNCHECKED_CAST")
    @ExperimentalCoroutinesApi
    suspend fun suspend():T = suspendCancellableCoroutine { continuation ->
        continuation.invokeOnCancellation { cancel() }
        enqueue(ImmediateDispatcher()) {
            when (it){
                is SuccessResult<*> -> continuation.resume(it.data as T)
                is ErrorResult<*> -> continuation.resumeWithException(it.error)
            }
        }
    }

}
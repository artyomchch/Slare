package tennisi.borzot.strada.fragments.add.presentation.addFragmentUI

sealed class CarObserver<T>(val info: T? = null) {
    class Warning<T>(val stateRepeat: Boolean =false): CarObserver<T>()
    class Success<T>: CarObserver<T>()
}

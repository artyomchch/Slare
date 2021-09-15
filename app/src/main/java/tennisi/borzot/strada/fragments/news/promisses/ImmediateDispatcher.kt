package tennisi.borzot.strada.fragments.news.promisses

class ImmediateDispatcher: Dispatcher {
    override fun dispatch(block: () -> Unit) {
        block()
    }
}
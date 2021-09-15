package tennisi.borzot.strada.fragments.news.promisses

interface Dispatcher {
    fun dispatch(block: () -> Unit)
}
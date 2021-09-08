package tennisi.borzot.strada.fragments.news

import tennisi.borzot.strada.fragments.news.model.User

interface Navigator {

    fun showDetails(user: User)

    fun goBack()

    fun toast(messageRes: Int)
}
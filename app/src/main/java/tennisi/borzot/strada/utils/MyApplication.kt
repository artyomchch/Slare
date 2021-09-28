package tennisi.borzot.strada.utils

import android.app.Application
import io.realm.Realm
import tennisi.borzot.strada.fragments.news.model.UsersService

class MyApplication: Application() {
    val usersService = UsersService()
    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
    }
}
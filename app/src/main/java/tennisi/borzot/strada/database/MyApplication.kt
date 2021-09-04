package tennisi.borzot.strada.database

import android.app.Application
import io.realm.Realm
import tennisi.borzot.strada.fragments.news.model.UsersService

class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        val usersService = UsersService()
    }
}
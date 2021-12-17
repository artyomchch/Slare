package tennisi.borzot.strada

import android.app.Application
import tennisi.borzot.strada.di.DaggerApplicationComponent

class StradaApplication : Application() {

    val component by lazy {
        DaggerApplicationComponent.factory()
            .create(this)
    }
}
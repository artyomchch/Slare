package tennisi.borzot.strada.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import tennisi.borzot.strada.fragments.add.data.repository.CarListRepositoryImpl
import tennisi.borzot.strada.fragments.add.presentation.addFragmentUI.AddFragment

@Component(modules = [AddDomainModule::class])
interface ApplicationComponent {

    fun inject(fragment: AddFragment)




    @Component.Factory
    interface ApplicationComponentFactory {

        fun create(
            @BindsInstance context: Context
        ): ApplicationComponent
    }
}
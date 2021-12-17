package tennisi.borzot.strada.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import tennisi.borzot.strada.fragments.add.presentation.addFragmentUI.AddFragment
import tennisi.borzot.strada.fragments.add.presentation.carItemUI.CarItemFragment

@Component(modules = [AddDomainModule::class, ViewModelModule::class])
interface ApplicationComponent {

    fun inject(fragment: AddFragment)

    fun inject(fragment: CarItemFragment)

    @Component.Factory
    interface ApplicationComponentFactory {

        fun create(
            @BindsInstance context: Context
        ): ApplicationComponent
    }
}
package tennisi.borzot.strada.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import tennisi.borzot.strada.fragments.add.presentation.addFragmentUI.AddFragment
import tennisi.borzot.strada.fragments.add.presentation.carItemUI.CarItemFragment
import tennisi.borzot.strada.fragments.news.presentation.newsFragment.NewsFragment
import tennisi.borzot.strada.fragments.news.presentation.sourceFragment.SourceFragment
import tennisi.borzot.strada.fragments.speed.SpeedFragment

@ApplicationScope
@Component(
    modules = [AddDomainModule::class, ViewModelModule::class,
        NewsDomainModule::class, FirestoreModule::class]
)
interface ApplicationComponent {

    fun inject(fragment: AddFragment)

    fun inject(fragment: CarItemFragment)

    fun inject(fragment: NewsFragment)

    fun inject(fragment: SourceFragment)

    fun inject(fragment: SpeedFragment)

    @Component.Factory
    interface ApplicationComponentFactory {

        fun create(
            @BindsInstance context: Context
        ): ApplicationComponent
    }
}
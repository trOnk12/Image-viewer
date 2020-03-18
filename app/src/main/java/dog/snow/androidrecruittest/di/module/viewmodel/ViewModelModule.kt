package dog.snow.androidrecruittest.di.module.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import dog.snow.androidrecruittest.feature.detail.DetailsFragmentViewModel
import dog.snow.androidrecruittest.feature.list.ListFragmentViewModel
import dog.snow.androidrecruittest.feature.splash.SplashViewModel

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModel::class)
    internal abstract fun splashViewModel(viewModel: SplashViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ListFragmentViewModel::class)
    internal abstract fun listFragmentViewModel(viewModel: ListFragmentViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailsFragmentViewModel::class)
    internal abstract fun detailFragmentViewModel(viewModel: DetailsFragmentViewModel): ViewModel

}

package dog.snow.androidrecruittest.feature.splash

import dagger.Component
import dog.snow.androidrecruittest.di.component.CoreComponent
import dog.snow.androidrecruittest.di.scope.FeatureScope


@FeatureScope
@Component(dependencies = [CoreComponent::class])
interface SplashComponent {
    fun inject(splashActivity: SplashActivity)
}
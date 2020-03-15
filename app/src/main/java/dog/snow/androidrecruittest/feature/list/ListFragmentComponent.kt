package dog.snow.androidrecruittest.feature.list

import dagger.Component
import dog.snow.androidrecruittest.di.component.CoreComponent
import dog.snow.androidrecruittest.di.scope.FeatureScope

@FeatureScope
@Component(dependencies = [CoreComponent::class])
interface ListFragmentComponent {
    fun inject(listFragment: ListFragment)
}
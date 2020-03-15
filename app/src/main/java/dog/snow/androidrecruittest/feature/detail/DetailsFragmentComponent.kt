package dog.snow.androidrecruittest.feature.detail

import dagger.Component
import dog.snow.androidrecruittest.di.component.CoreComponent
import dog.snow.androidrecruittest.di.scope.FeatureScope

@FeatureScope
@Component(dependencies = [CoreComponent::class])
interface DetailsFragmentComponent {
    fun inject(detailsFragment: DetailsFragment)
}
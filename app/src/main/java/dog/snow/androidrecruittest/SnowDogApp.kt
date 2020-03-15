package dog.snow.androidrecruittest

import android.app.Application
import android.content.Context
import dog.snow.androidrecruittest.di.component.CoreComponent
import dog.snow.androidrecruittest.di.component.DaggerCoreComponent
import dog.snow.androidrecruittest.di.module.ContextModule

class SnowDogApp : Application() {

    lateinit var coreComponent: CoreComponent

    companion object {
        fun coreComponent(context: Context) =
            (context.applicationContext as SnowDogApp).coreComponent
    }

    override fun onCreate() {
        super.onCreate()
        initDaggerCoreComponent()
    }

    private fun initDaggerCoreComponent() {
        coreComponent = DaggerCoreComponent
            .builder()
            .contextModule(ContextModule(this))
            .build()
    }

}
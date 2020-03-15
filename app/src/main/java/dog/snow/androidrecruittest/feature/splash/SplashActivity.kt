package dog.snow.androidrecruittest.feature.splash

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.transition.AutoTransition
import androidx.transition.Transition
import androidx.transition.TransitionManager
import androidx.transition.TransitionSet
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dog.snow.androidrecruittest.R
import dog.snow.androidrecruittest.SnowDogApp
import dog.snow.androidrecruittest.core.extension.observe
import dog.snow.androidrecruittest.feature.MainActivity
import kotlinx.android.synthetic.main.splash_activity.*
import javax.inject.Inject

class SplashActivity : AppCompatActivity(R.layout.splash_activity) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val splashViewModel: SplashViewModel by lazy {
        viewModelFactory.create(SplashViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        initDaggerDependency()
        super.onCreate(savedInstanceState)
        observe(splashViewModel.splashViewEvent, ::onViewEvent)
    }

    private fun initDaggerDependency() {
        DaggerSplashComponent
            .builder()
            .coreComponent(SnowDogApp.coreComponent(this))
            .build()
            .inject(this)
    }

    private fun onViewEvent(viewEvent: SplashViewEvent) {
        when (viewEvent) {
            is SplashViewEvent.NavigateToMainScreen -> navigateToMainScreen()
            is SplashViewEvent.ShowErrorMessage -> showError(viewEvent.message)
            is SplashViewEvent.IsLoading -> isLoading()
        }
    }

    private fun isLoading() {
        layout_progressbar.visibility = View.VISIBLE
    }

    private fun navigateToMainScreen() {
        val transitionSet = TransitionSet()
        transitionSet.addTransition(AutoTransition())
        transitionSet.addListener(object : TransitionFinishedListener {
            override fun onTransitionFinished(transition: Transition) {
                showMainActivity()
            }
        })

        TransitionManager.beginDelayedTransition(splash_view_container, transitionSet)
        layout_progressbar.visibility = View.GONE
    }

    private fun showMainActivity() {
        startActivity(MainActivity.callingIntent(this))
        finish()
    }

    private fun showError(errorMessage: String?) {
        MaterialAlertDialogBuilder(this)
            .setTitle(R.string.cant_download_dialog_title)
            .setMessage(getString(R.string.cant_download_dialog_message, errorMessage))
            .setPositiveButton(R.string.cant_download_dialog_btn_positive) { _, _ -> /*tryAgain()*/ }
            .setNegativeButton(R.string.cant_download_dialog_btn_negative) { _, _ -> finish() }
            .create()
            .apply { setCanceledOnTouchOutside(false) }
            .show()
    }

}
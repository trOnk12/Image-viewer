package dog.snow.androidrecruittest.feature.splash

import androidx.transition.Transition

interface TransitionFinishedListener : Transition.TransitionListener {
    fun onTransitionFinished(transition:Transition)

    override fun onTransitionEnd(transition: Transition) {
        onTransitionFinished(transition)
    }

    override fun onTransitionResume(transition: Transition) {

    }

    override fun onTransitionPause(transition: Transition) {

    }

    override fun onTransitionCancel(transition: Transition) {

    }

    override fun onTransitionStart(transition: Transition) {

    }

}
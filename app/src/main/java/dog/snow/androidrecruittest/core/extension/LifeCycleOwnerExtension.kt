package dog.snow.androidrecruittest.core.extension

import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer


fun <T> LifecycleOwner.observe(liveData: LiveData<T>, onDataChanged: (T) -> Unit) {
    liveData.observe(this, Observer {
        it?.let { t -> onDataChanged(t) }
    })
}

fun <T> Fragment.observe(liveData: LiveData<T>, onDataChanged: (T) -> Unit) {
    liveData.observe(viewLifecycleOwner, Observer {
        it?.let { t -> onDataChanged(t) }
    })
}



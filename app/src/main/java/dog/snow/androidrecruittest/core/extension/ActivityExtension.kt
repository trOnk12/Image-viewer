package dog.snow.androidrecruittest.core.extension

import android.content.Intent
import androidx.fragment.app.FragmentActivity

fun FragmentActivity.startAndFinish(intent: Intent) {
    startActivity(intent)
    finish()
}
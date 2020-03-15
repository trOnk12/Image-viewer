package dog.snow.androidrecruittest.feature.list

import android.text.Editable
import android.text.TextWatcher

interface OnSearchTermChangedListener : TextWatcher {
    fun onSearchTermChanged(p0: CharSequence?)

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        onSearchTermChanged(p0)
    }

    //NOT USED
    override fun afterTextChanged(p0: Editable?) {}

    //NOT USED
    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
}
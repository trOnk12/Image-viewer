package dog.snow.androidrecruittest.feature

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso
import dog.snow.androidrecruittest.R

@BindingAdapter("imageUrl")
fun loadImage(imageView: ImageView, url: String?) {
    url?.let{
        Picasso.get().load(it).placeholder(R.drawable.ic_placeholder).into(imageView)
    }
}
package uz.med.shared.util

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

val BASE_URL_IMAGE = "https://image.tmdb.org/t/p/original/"

fun ViewGroup.inflate(@LayoutRes layoutResId: Int): View {
    return LayoutInflater.from(context).inflate(layoutResId, this, false)
}

fun AppCompatImageView.setImageFromUrlPath(imgUrl: String?, placeholder: Drawable? = null) {
    Glide.with(context)
        .load(BASE_URL_IMAGE + imgUrl)
        .placeholder(placeholder)
        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
        .into(this)
}

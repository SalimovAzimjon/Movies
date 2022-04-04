package uz.med.ui_core

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.android.material.card.MaterialCardView

val BASE_URL_IMAGE = "https://image.tmdb.org/t/p/original/"

class RoundedImageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : MaterialCardView(context, attrs, defStyle) {

    private val img = ImageView(context)

    init {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.RoundedImageView,
            0, 0
        ).apply {
            try {
                val imageRadius = getDimension(R.styleable.RoundedImageView_radius, 4f)
                this@RoundedImageView.radius = imageRadius
            } finally {
                recycle()
            }
        }
        img.scaleType = ImageView.ScaleType.CENTER_CROP
        this.cardElevation = 0f
        addView(img)
    }

    fun setImageResource(@DrawableRes res: Int) {
        img.setImageResource(res)
    }

    fun setImageRemote(imgUrl: String) {
        Glide.with(context)
            .load(BASE_URL_IMAGE + imgUrl)
            .placeholder(R.drawable.placeholder)
            .diskCacheStrategy(DiskCacheStrategy.DATA)
            .into(img)
    }

}

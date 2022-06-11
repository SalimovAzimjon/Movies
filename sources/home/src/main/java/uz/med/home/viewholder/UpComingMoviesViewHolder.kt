package uz.med.home.viewholder

import android.view.View
import uz.med.core_api.dto.Movie
import uz.med.home.R
import uz.med.home.databinding.ItemUpcomingMovieBinding
import uz.med.shared.BaseViewHolder

class UpComingMoviesViewHolder(view: View) :
    BaseViewHolder<ItemUpcomingMovieBinding>(view, ItemUpcomingMovieBinding::class.java) {

    fun onBind(movie: Movie) = bindView {
        imgUpcomingMovie.setImageFromUrlPath(movie.backdropPath)
        tvMovieTitle.text = movie.title
        tvMovieReleaseDate.text = itemView.context.getString(R.string.release_date,movie.releaseDate)
    }

}

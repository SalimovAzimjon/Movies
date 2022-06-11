package uz.med.home.viewholder

import android.view.View
import androidx.recyclerview.widget.DiffUtil
import uz.med.core_api.dto.Movie
import uz.med.home.databinding.ItemListMovieBinding
import uz.med.shared.BaseViewHolder

class MoviesViewHolder(view: View) :
    BaseViewHolder<ItemListMovieBinding>(view, ItemListMovieBinding::class.java) {

    fun onBind(movie: Movie) = bindView {
        imgMovie.setImageFromUrlPath(movie.posterPath)
        tvRating.text = movie.voteAverage.toString()
        tvMovieTitle.text = movie.title ?: movie.originalName
    }

}

class MovieComparator : DiffUtil.ItemCallback<Movie>() {

    override fun areItemsTheSame(oldItem: Movie, newItem: Movie) = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie) = oldItem.title == newItem.title
}

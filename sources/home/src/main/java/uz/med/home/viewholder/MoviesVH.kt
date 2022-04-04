package uz.med.home.viewholder

import android.view.View
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import uz.med.core_api.dto.Movie

class MoviesVH(view: View) : RecyclerView.ViewHolder(view) {



}

class MovieComparator : DiffUtil.ItemCallback<Movie>() {

    override fun areItemsTheSame(oldItem: Movie, newItem: Movie) = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie) = oldItem.title == newItem.title
}

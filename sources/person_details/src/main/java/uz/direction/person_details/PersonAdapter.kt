package uz.direction.person_details

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.direction.person_details.data.model.Movie
import uz.direction.person_details.databinding.ItemPersonMovieBinding
import uz.med.shared.BaseViewHolder
import uz.med.shared.util.inflate
import uz.med.ui_core.RoundedImageView

class PersonMoviesAdapter(
    private val movies: List<Movie>,
    private val onClick:(Long)->Unit
) : RecyclerView.Adapter<PersonMoviesAdapter.PersonMoviesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonMoviesViewHolder {
        return PersonMoviesViewHolder(parent.inflate(R.layout.item_person_movie))
    }

    override fun onBindViewHolder(holder: PersonMoviesViewHolder, position: Int) {
        holder.onBind(movies[position])
        holder.itemView.setOnClickListener { onClick.invoke(movies[position].id) }
    }

    override fun getItemCount() = movies.size

    class PersonMoviesViewHolder(view: View) :
        BaseViewHolder<ItemPersonMovieBinding>(view, ItemPersonMovieBinding::class.java) {

        fun onBind(movie: Movie)  {
            itemView.findViewById<RoundedImageView>(R.id.img_person_movie).setImageFromUrlPath(movie.posterPath)
        }
    }

}

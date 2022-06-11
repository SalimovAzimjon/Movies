package uz.med.home.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.med.core_api.dto.Movie
import uz.med.home.R
import uz.med.home.data.MainScreenViewType
import uz.med.home.viewholder.MovieComparator
import uz.med.home.viewholder.MoviesViewHolder
import uz.med.home.viewholder.UpComingMoviesViewHolder
import uz.med.shared.util.inflate

class MoviesAdapter(private val mainScreenViewType: MainScreenViewType) :
    PagingDataAdapter<Movie, RecyclerView.ViewHolder>(MovieComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            MainScreenViewType.VIEW_TYPE_UPCOMING.code -> UpComingMoviesViewHolder(parent.inflate(R.layout.item_upcoming_movie))
            else -> MoviesViewHolder(parent.inflate(R.layout.item_list_movie))
        }
    }

    override fun getItemViewType(position: Int): Int {
        return mainScreenViewType.code
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        getItem(position)?.let { movie ->
            when (holder) {
                is MoviesViewHolder -> holder.onBind(movie)
                is UpComingMoviesViewHolder -> holder.onBind(movie)
            }
        }
    }

}

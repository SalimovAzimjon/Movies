package uz.med.home.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import uz.med.core_api.dto.Movie
import uz.med.home.R
import uz.med.home.viewholder.MovieComparator
import uz.med.home.viewholder.MoviesVH
import uz.med.shared.util.inflate

class MoviesAdapter : PagingDataAdapter<Movie, MoviesVH>(MovieComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesVH {
        return MoviesVH(parent.inflate(R.layout.item_movie))
    }

    override fun onBindViewHolder(holder: MoviesVH, position: Int) {
    }

}

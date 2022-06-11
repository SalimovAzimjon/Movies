package uz.med.home.data.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import uz.med.core_api.dto.BaseResponse
import uz.med.core_api.dto.Movie

private const val START = 1

class MoviesPagingSource(
    private val func: suspend (Int) -> BaseResponse<Movie>
) : PagingSource<Int, Movie>() {

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val page = params.key ?: START
        return try {
            val response = func.invoke(page)
            LoadResult.Page(
                data = response.results,
                prevKey = if (page == START) null else page - 1,
                nextKey = if (page == response.totalPages) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(Throwable(e.message))
        }
    }
}

package uz.med.core_api.dto

import com.squareup.moshi.Json

data class BaseResponse<T>(
    @field:Json(name = "page")
    val page: Int,
    @field:Json(name = "results")
    val results: List<T>,
    @field:Json(name = "total_pages")
    val totalPages: Int,
    @field:Json(name = "total_results")
    val totalResults: Int
)

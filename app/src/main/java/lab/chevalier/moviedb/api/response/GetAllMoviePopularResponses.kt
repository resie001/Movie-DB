package lab.chevalier.moviedb.api.response


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GetAllMoviePopularResponses(
    @Json(name = "page")
    val page: Int = 0,
    @Json(name = "results")
    val results: List<Result> = listOf(),
    @Json(name = "total_pages")
    val totalPages: Int = 0,
    @Json(name = "total_results")
    val totalResults: Int = 0
)
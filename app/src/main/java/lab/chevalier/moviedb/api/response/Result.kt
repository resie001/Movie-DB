package lab.chevalier.moviedb.api.response


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Entity(tableName = "tbl_movie")
@JsonClass(generateAdapter = true)
data class Result(
    @Json(name = "adult")
    val adult: Boolean = false,
    @Json(name = "backdrop_path")
    val backdropPath: String? = "",
    @Json(name = "genre_ids")
    val genreIds: List<Int> = listOf(),
    @PrimaryKey(autoGenerate = false)
    @Json(name = "id")
    val id: Int = 0,
    @Json(name = "original_language")
    val originalLanguage: String = "",
    @Json(name = "original_title")
    val originalTitle: String = "",
    @Json(name = "overview")
    val overview: String = "",
    @Json(name = "popularity")
    val popularity: Double = 0.0,
    @Json(name = "poster_path")
    val posterPath: String = "",
    @Json(name = "title")
    val title: String = "",
    @Json(name = "video")
    val video: Boolean = false,
    @Json(name = "vote_average")
    val voteAverage: Int = 0,
    @Json(name = "vote_count")
    val voteCount: Int = 0
)
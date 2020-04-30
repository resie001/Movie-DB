package lab.chevalier.moviedb.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbl_movie")
data class Movie (
    val adult: Boolean = false,
    val backdropPath: String? = "",
    @PrimaryKey
    val id: Int = 0,
    val originalLanguage: String = "",
    val originalTitle: String = "",
    val overview: String = "",
    val popularity: Double = 0.0,
    val posterPath: String = "",
    val title: String = "",
    val video: Boolean = false
)
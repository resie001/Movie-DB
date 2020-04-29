package lab.chevalier.moviedb.utilities

import lab.chevalier.moviedb.api.response.Result
import lab.chevalier.moviedb.database.Movie

object Converter {

    fun toMovie(result: Result) = Movie(
        adult = result.adult,
        backdropPath = result.backdropPath,
        id = result.id,
        originalLanguage = result.originalLanguage,
        originalTitle = result.originalTitle,
        overview = result.overview,
        popularity = result.popularity,
        posterPath = result.posterPath,
        title = result.title,
        video = result.video,
        voteAverage = result.voteAverage,
        voteCount = result.voteCount
    )
}
package lab.chevalier.moviedb.data.api

import kotlinx.coroutines.Deferred
import lab.chevalier.moviedb.data.api.response.GetAllMoviePopularResponses
import retrofit2.http.GET
import retrofit2.http.Query

interface ServiceApi {

    @GET("movie/popular")
    fun getAllPopular(@Query("api_key") apiKey : String) : Deferred<GetAllMoviePopularResponses>

}
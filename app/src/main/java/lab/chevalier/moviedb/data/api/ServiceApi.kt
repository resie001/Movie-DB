package lab.chevalier.moviedb.data.api

import kotlinx.coroutines.Deferred
import lab.chevalier.moviedb.data.api.response.GetAllMoviePopularResponses
import lab.chevalier.moviedb.data.api.response.Movie
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ServiceApi {

    @GET("movie/popular")
    fun getAllPopularAsync(@Query("api_key") apiKey : String) : Deferred<GetAllMoviePopularResponses>

    @GET("movie/latest")
    fun getLatestMovie(@Query("api_key") apiKey: String) : Call<Movie>

}
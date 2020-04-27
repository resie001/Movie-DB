package lab.chevalier.moviedb.api

import lab.chevalier.moviedb.api.response.GetAllMoviePopularResponses
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ServiceApi {

    @GET("movie/popular")
    fun getAllPopular(@Query("api_key") apiKey : String) : Call<GetAllMoviePopularResponses>

}
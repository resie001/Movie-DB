package lab.chevalier.moviedb.worker

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import kotlinx.coroutines.coroutineScope
import lab.chevalier.moviedb.api.BaseApi
import lab.chevalier.moviedb.api.response.GetAllMoviePopularResponses
import lab.chevalier.moviedb.utilities.Constanta
import lab.chevalier.moviedb.database.MovieDB
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class MovieAllWorker(
    context: Context,
    workerParameters: WorkerParameters
) : CoroutineWorker(context, workerParameters) {
    override suspend fun doWork(): Result = coroutineScope {
        try {
            val listData = mutableListOf<lab.chevalier.moviedb.api.response.Result>()
            BaseApi().services.getAllPopular(Constanta.apiKey)
                .enqueue(object : Callback<GetAllMoviePopularResponses> {
                    override fun onFailure(call: Call<GetAllMoviePopularResponses>, t: Throwable) {
                        Log.e("MovieAllWorker", t.toString())
                    }

                    override fun onResponse(
                        call: Call<GetAllMoviePopularResponses>,
                        response: Response<GetAllMoviePopularResponses>
                    ) {
                        if (response.isSuccessful){
                            response.body()?.let { listData.addAll(it.results) }
                        }
                    }
                })
            val database = MovieDB.getInstance(applicationContext)
            if (listData.size != 0) database.movieDao().insertAll(listData)
            Result.success()
        } catch (ex: Exception) {
            Log.e("MovieAllWorker", ex.toString())
            Result.failure()
        }
    }

}
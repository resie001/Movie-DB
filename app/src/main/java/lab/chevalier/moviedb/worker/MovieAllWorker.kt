package lab.chevalier.moviedb.worker

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import kotlinx.coroutines.coroutineScope
import lab.chevalier.moviedb.api.BaseApi
import lab.chevalier.moviedb.utilities.Constanta
import lab.chevalier.moviedb.database.MovieDB
import java.lang.Exception

class MovieAllWorker(
    context: Context,
    workerParameters: WorkerParameters
) : CoroutineWorker(context, workerParameters) {
    override suspend fun doWork(): Result = coroutineScope {
        try {
            val listMovie = BaseApi().services.getAllPopular(Constanta.apiKey).await()
//            BaseApi().services.getAllPopular(Constanta.apiKey)
//                .enqueue(object : Callback<GetAllMoviePopularResponses> {
//                    override fun onFailure(call: Call<GetAllMoviePopularResponses>, t: Throwable) {
//                        Log.e("MovieAllWorker", t.toString())
//                    }
//
//                    override fun onResponse(
//                        call: Call<GetAllMoviePopularResponses>,
//                        response: Response<GetAllMoviePopularResponses>
//                    ) {
//                        if (response.isSuccessful){
//                            response.body()?.let { listData.addAll(it.results) }
//                            for (item in listData){
//                                listMovie.add(Converter.toMovie(item))
//                            }
//
//                        }
//                    }
//                })
            val database = MovieDB.getInstance(applicationContext)
            database.movieDao().insertAll(listMovie.results)
            Log.e("MovieAllWorker Success", listMovie.toString())
            Result.success()
        } catch (ex: Exception) {
            Log.e("MovieAllWorker Failed", ex.toString())
            Result.failure()
        }
    }

}
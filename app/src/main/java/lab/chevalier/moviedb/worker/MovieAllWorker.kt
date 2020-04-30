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
            val database = MovieDB.getInstance(applicationContext)
            database.movieDao().insertAll(listMovie.results)
            Result.success()
        } catch (ex: Exception) {
            Result.failure()
        }
    }

}
package lab.chevalier.moviedb.worker

import android.content.Context
import android.widget.Toast
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import kotlinx.coroutines.coroutineScope
import lab.chevalier.moviedb.data.api.BaseApi
import lab.chevalier.moviedb.utilities.Constanta
import lab.chevalier.moviedb.data.database.MovieDB
import java.lang.Exception

class MovieAllWorker(
    context: Context,
    workerParameters: WorkerParameters
) : CoroutineWorker(context, workerParameters) {
    override suspend fun doWork(): Result = coroutineScope {
        try {
            val listMovie = BaseApi().services.getAllPopularAsync(Constanta.apiKey).await()
            val database = MovieDB.getInstance(applicationContext)
            database.movieDao().insertAll(listMovie.results)
            Result.success()
        } catch (ex: Exception) {
            Toast.makeText(applicationContext, "Tidak Ada Internet", Toast.LENGTH_SHORT).show()
            Result.failure()
        }
    }

}
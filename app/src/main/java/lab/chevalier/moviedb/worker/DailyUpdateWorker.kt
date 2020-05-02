package lab.chevalier.moviedb.worker

import android.content.Context
import android.util.Log
import androidx.work.*
import lab.chevalier.moviedb.data.api.BaseApi
import lab.chevalier.moviedb.data.api.response.Movie
import lab.chevalier.moviedb.data.database.MovieDB
import lab.chevalier.moviedb.utilities.Constanta
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception
import java.util.*
import java.util.concurrent.TimeUnit

class DailyUpdateWorker(
    context: Context,
    workerParameters: WorkerParameters
) : Worker(context, workerParameters) {
    override fun doWork(): Result {
        try {
            val currentDate = Calendar.getInstance()
            val dueDate = Calendar.getInstance()

            dueDate.set(Calendar.HOUR_OF_DAY, 5)
            dueDate.set(Calendar.MINUTE, 0)
            dueDate.set(Calendar.SECOND, 0)

            if (dueDate.before(currentDate)) dueDate.add(Calendar.HOUR_OF_DAY, 24)

            val timeDiff = dueDate.timeInMillis - currentDate.timeInMillis

            val dailyworker = OneTimeWorkRequestBuilder<DailyUpdateWorker>()
                .setInitialDelay(timeDiff, TimeUnit.MILLISECONDS)
                .setConstraints(Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build())
                .build()

            val database = MovieDB.getInstance(applicationContext)
            BaseApi().services.getLatestMovie(Constanta.apiKey).enqueue(object : Callback<Movie>{
                override fun onFailure(call: Call<Movie>, t: Throwable) {
                    Log.e("DailyWorker API Failed", t.toString())
                }

                override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                    if (response.isSuccessful){
                        response.body()?.let { database.movieDao().insert(it) }
                        Log.e("DailyWorker Success", "berhasil")
                    }
                }
            })
            WorkManager.getInstance().enqueue(dailyworker)
            return Result.success()
        } catch (ex : Exception){
            Log.e("DailyWorker Failure", ex.toString())
            return Result.failure()
        }
    }
}
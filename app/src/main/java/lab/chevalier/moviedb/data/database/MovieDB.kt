package lab.chevalier.moviedb.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import lab.chevalier.moviedb.data.api.response.Result
import lab.chevalier.moviedb.worker.MovieAllWorker

@Database(entities = [Result::class], version = 1, exportSchema = false)
abstract class MovieDB : RoomDatabase() {

    abstract fun movieDao() : MovieDao

    companion object {

        @Volatile private var instance : MovieDB? = null

        fun getInstance(context: Context) : MovieDB {
            return instance
                ?: synchronized(this) {
                instance
                    ?: buildDatabase(
                        context
                    )
            }
        }

        private fun buildDatabase(context: Context): MovieDB {
            return Room.databaseBuilder(context, MovieDB::class.java, "db_movie")
                .addCallback(object : RoomDatabase.Callback(){
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        val request = OneTimeWorkRequestBuilder<MovieAllWorker>().build()
                        WorkManager.getInstance().enqueue(request)
                    }
                }).build()
        }
    }

}
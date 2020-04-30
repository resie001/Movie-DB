package lab.chevalier.moviedb.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import lab.chevalier.moviedb.api.response.Result

@Dao
interface MovieDao {

    @Query("SELECT * FROM tbl_movie")
    fun getAllMovie() : LiveData<List<Result>>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(movie : Result)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(movies : List<Result>)

}
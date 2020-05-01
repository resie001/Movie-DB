package lab.chevalier.moviedb.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import lab.chevalier.moviedb.data.api.response.Movie

@Dao
interface MovieDao {

    @Query("SELECT * FROM tbl_movie")
    fun getAllMovie() : LiveData<List<Movie>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movie : Movie)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(movies : List<Movie>)

}
package lab.chevalier.moviedb.repository

import lab.chevalier.moviedb.data.database.MovieDao

class HomeRepository private constructor(private val movieDao: MovieDao){

    fun getAllMovie() = movieDao.getAllMovie()

    companion object {

        @Volatile private var instance: HomeRepository? = null

        fun getInstance(movieDao: MovieDao) =
            instance ?: synchronized(this){
                instance
                    ?: HomeRepository(movieDao)
                        .also { instance = it }
            }
    }

}
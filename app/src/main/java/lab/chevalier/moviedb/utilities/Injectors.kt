package lab.chevalier.moviedb.utilities

import android.content.Context
import androidx.fragment.app.Fragment
import lab.chevalier.moviedb.data.database.MovieDB
import lab.chevalier.moviedb.factory.HomeViewModelFactory
import lab.chevalier.moviedb.repository.HomeRepository

object Injectors {

    private fun getHomeRepository(context: Context) : HomeRepository{
        return HomeRepository.getInstance(MovieDB.getInstance(context.applicationContext).movieDao())
    }

    fun provideHomeViewModelFactory(fragment: Fragment) : HomeViewModelFactory{
        val repository = getHomeRepository(fragment.requireContext())
        return HomeViewModelFactory(repository, fragment)
    }

}
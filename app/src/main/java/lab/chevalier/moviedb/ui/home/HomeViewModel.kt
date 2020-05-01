package lab.chevalier.moviedb.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import lab.chevalier.moviedb.data.api.response.Movie
import lab.chevalier.moviedb.repository.HomeRepository

class HomeViewModel internal constructor(
    homeRepository: HomeRepository
) : ViewModel() {

    val movies : LiveData<List<Movie>> = homeRepository.getAllMovie()

}
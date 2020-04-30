package lab.chevalier.moviedb.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import lab.chevalier.moviedb.api.response.Result
import lab.chevalier.moviedb.repository.HomeRepository

class HomeViewModel internal constructor(
    homeRepository: HomeRepository
) : ViewModel() {

    val movies : LiveData<List<Result>> = homeRepository.getAllMovie()

}
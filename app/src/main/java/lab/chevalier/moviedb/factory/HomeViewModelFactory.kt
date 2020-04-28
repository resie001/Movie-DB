package lab.chevalier.moviedb.factory

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import lab.chevalier.moviedb.repository.HomeRepository

class HomeViewModelFactory(
    private val homeRepository: HomeRepository,
    savedStateRegistryOwner: SavedStateRegistryOwner,
    bundle: Bundle
) : AbstractSavedStateViewModelFactory(savedStateRegistryOwner, bundle) {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
        TODO("Not yet implemented")
    }
}
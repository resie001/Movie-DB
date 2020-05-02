package lab.chevalier.moviedb.ui.home

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import lab.chevalier.moviedb.R
import lab.chevalier.moviedb.adapter.MovieAllAdapter
import lab.chevalier.moviedb.data.api.BaseApi
import lab.chevalier.moviedb.data.api.response.Movie
import lab.chevalier.moviedb.data.database.MovieDB
import lab.chevalier.moviedb.databinding.FragmentHomeBinding
import lab.chevalier.moviedb.utilities.Constanta
import lab.chevalier.moviedb.utilities.Injectors
import lab.chevalier.moviedb.worker.MovieAllWorker

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {

    private lateinit var binding : FragmentHomeBinding

    private val homeViewModel : HomeViewModel by viewModels {
        Injectors.provideHomeViewModelFactory(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        val adapter = MovieAllAdapter(requireContext()) { movie: Movie -> navigate(movie) }
        binding.rvMovie.apply {
            this.adapter = adapter
            this.layoutManager = LinearLayoutManager(requireContext())
        }
        subcribeUI(adapter)
        return binding.root
    }

    private fun subcribeUI(movieAllAdapter: MovieAllAdapter){
        homeViewModel.movies.observe(viewLifecycleOwner, Observer {
            Log.e("HomeFragment Success", it.toString())
            movieAllAdapter.listData = it
        })

        binding.btnReload.setOnClickListener { reload() }
    }

    private fun navigate( data : Movie){
        requireView().findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToDetailFragment(data))
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.main_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, requireView().findNavController()) || super.onOptionsItemSelected(item)
    }

    private fun reload(){
        val job = Job()
        val uiscope = CoroutineScope(job + Dispatchers.IO)
        uiscope.launch {
            val listMovie = BaseApi().services.getAllPopularAsync(Constanta.apiKey).await()
            val database = MovieDB.getInstance(requireContext())
            database.movieDao().insertAll(listMovie.results)
        }
    }

}



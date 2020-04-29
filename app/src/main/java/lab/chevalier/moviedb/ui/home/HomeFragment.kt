package lab.chevalier.moviedb.ui.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager

import lab.chevalier.moviedb.R
import lab.chevalier.moviedb.adapter.MovieAllAdapter
import lab.chevalier.moviedb.databinding.FragmentHomeBinding
import lab.chevalier.moviedb.utilities.Injectors

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {

    lateinit var binding : FragmentHomeBinding

    private val homeViewModel : HomeViewModel by viewModels {
        Injectors.provideHomeViewModelFactory(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        context ?: return binding.root

        val adapter = MovieAllAdapter(requireContext())
        binding.rvMovie.apply {
            this.adapter = adapter
            this.layoutManager = LinearLayoutManager(requireContext())
        }
        subcribeUI(adapter)
        return binding.root
    }

    private fun subcribeUI(movieAllAdapter: MovieAllAdapter){
        homeViewModel.movies.observe(viewLifecycleOwner, Observer {
            movieAllAdapter.listData = it
        })
    }

}



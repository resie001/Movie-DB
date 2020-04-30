package lab.chevalier.moviedb.ui.home

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import lab.chevalier.moviedb.R
import lab.chevalier.moviedb.adapter.MovieAllAdapter
import lab.chevalier.moviedb.api.response.Result
import lab.chevalier.moviedb.databinding.FragmentHomeBinding
import lab.chevalier.moviedb.utilities.Injectors

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
        val adapter = MovieAllAdapter(requireContext()) { result: Result -> navigate(result) }
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
    }

    private fun navigate( data : Result ){
        requireView().findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToDetailFragment(data))
    }

}



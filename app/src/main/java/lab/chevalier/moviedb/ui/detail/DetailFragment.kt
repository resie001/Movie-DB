package lab.chevalier.moviedb.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide

import lab.chevalier.moviedb.R
import lab.chevalier.moviedb.databinding.FragmentDetailBinding
import lab.chevalier.moviedb.utilities.Constanta

/**
 * A simple [Fragment] subclass.
 */
class DetailFragment : Fragment() {

    val args : DetailFragmentArgs by navArgs()

    private lateinit var binding: FragmentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val data = args.data

        binding.apply {
            Glide.with(requireContext())
                .load(Constanta.imagePath+data.poster_path)
                .override(150, 150)
                .into(imgPoster)

            tvId.text = data.id.toString()
            tvAdult.text = data.adult.toString()
            tvTitle.text = data.title
            tvDate.text = data.release_date
            tvOriginalLanguage.text = data.original_language
            tvOriginalTitle.text = data.original_title
            tvOverview.text = data.overview
            tvPopularity.text = data.popularity.toString()
        }
    }

}

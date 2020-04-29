package lab.chevalier.moviedb.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import lab.chevalier.moviedb.R
import lab.chevalier.moviedb.database.Movie
import lab.chevalier.moviedb.databinding.ItemMovieBinding
import lab.chevalier.moviedb.utilities.Constanta

class MovieAllAdapter(val context: Context) : RecyclerView.Adapter<MovieAllAdapter.MovieViewHolder>() {

    var listData : List<Movie> = mutableListOf()

    inner class MovieViewHolder(val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(data : Movie){
            binding.tvTitle.text = data.originalTitle
            binding.tvOverview.text = data.overview
            Glide.with(context)
                .load(Constanta.imagePath+data.posterPath)
                .override(150, 150)
                .into(binding.imgPoster)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : MovieViewHolder = MovieViewHolder(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.item_movie, parent, false)
    )

    override fun getItemCount(): Int = listData.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(listData[position])
    }

}
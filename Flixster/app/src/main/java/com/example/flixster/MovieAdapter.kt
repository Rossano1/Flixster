package com.example.flixster

import android.icu.text.CaseMap.Title
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide


class MovieAdapter(private val movies: List<Movie> ) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView2)
        val title: TextView = itemView.findViewById(R.id.textView)
        val description: TextView = itemView.findViewById(R.id.textView2)
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieAdapter.MovieViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.itemlist, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieAdapter.MovieViewHolder, position: Int) {
        val currentMovie = movies[position]
        Glide.with(holder.itemView.context)
            .load(currentMovie.poster)
            .into(holder.imageView)
        holder.title.text = currentMovie.title
        holder.description.text = currentMovie.description
    }

    override fun getItemCount(): Int {

        return movies.size
    }
}
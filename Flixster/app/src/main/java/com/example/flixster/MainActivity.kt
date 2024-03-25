package com.example.flixster

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var movieAdapter: MovieAdapter
    private lateinit var movieList: MutableList<Movie>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        movieList = mutableListOf()
        movieAdapter = MovieAdapter(movieList)
        recyclerView = findViewById<RecyclerView>(R.id.recyclerView2)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = movieAdapter
        fetchMovies()
    }
    private fun fetchMovies() {
        val apiKey = "a07e22bc18f5cb106bfe4cc1f83ad8ed"
        val url = "https://api.themoviedb.org/3/discover/movie?api_key=$apiKey"

        val queue: RequestQueue = Volley.newRequestQueue(this)
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            Response.Listener { response ->
                parseResponse(response)
            },
            Response.ErrorListener { error ->
                Log.e("APIError", "Error fetching movie data: ${error.message}")
            })

        queue.add(jsonObjectRequest)
    }

    private fun parseResponse(response: JSONObject) {
        val results: JSONArray = response.getJSONArray("results")

        for (i in 0 until results.length()) {
            val movieObject: JSONObject = results.getJSONObject(i)
            val title = movieObject.getString("title")
            val description = movieObject.getString("overview")
            val posterPath = movieObject.getString("poster_path")
            val posterUrl = "https://image.tmdb.org/t/p/w500$posterPath"
            val rating = movieObject.getDouble("vote_average")
            val genresArray = movieObject.getJSONArray("genre_ids")
            val genres = mutableListOf<String>()
            for (j in 0 until genresArray.length()) {
                val genreId = genresArray.getInt(j)
                val genreName = mapGenreIdToName(genreId)
                genres.add(genreName)
            }


            val movie = Movie(title, description, posterUrl,rating,genres)

            movieList.add(movie)
        }

        movieAdapter.notifyDataSetChanged()
    }

    private fun mapGenreIdToName(genreId: Int): String {
        val genreMap = mapOf(
            28 to "Action",
            12 to "Adventure",
            16 to "Animation",
            35 to "Comedy",
            80 to "Crime",
            99 to "Documentary",
            18 to "Drama",
            10751 to "Family",
            14 to "Fantasy",
            36 to "History",
            27 to "Horror",
            10402 to "Music",
            9648 to "Mystery",
            10749 to "Romance",
            878 to "Science Fiction",
            10770 to "TV Movie",
            53 to "Thriller",
            10752 to "War",
            37 to "Western"
        )

        return genreMap[genreId] ?: "Unknown Genre"
    }


}
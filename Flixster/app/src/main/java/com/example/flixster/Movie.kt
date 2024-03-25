package com.example.flixster

import org.json.JSONArray

data class Movie(var title: String, var description: String, var poster: String, var rating:Double, var genres: List<String>)

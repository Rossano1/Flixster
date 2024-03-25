package com.example.flixster

import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.bumptech.glide.Glide
import kotlinx.coroutines.selects.SelectInstance

class DetailedActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detailed_activity)

        val title = intent.getStringExtra("title")
        val description = intent.getStringExtra("description")
        val posterUrl = intent.getStringExtra("image")
        val genres = intent.getStringArrayExtra("genres")
        val rating = intent.getDoubleExtra("rating", 0.0)

        findViewById<TextView>(R.id.textView3).text = title
        findViewById<TextView>(R.id.textView4).text = description
        findViewById<TextView>(R.id.editTextNumberDecimal).text = "Rating: $rating"



        findViewById<TextView>(R.id.textView5).text = genres?.joinToString(", ")


        Glide.with(this)
            .load(posterUrl)
            .into(findViewById<ImageView>(R.id.imageView))



    }
}
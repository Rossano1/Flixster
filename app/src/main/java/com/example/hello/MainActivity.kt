package com.example.hello

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
       val button = findViewById<Button>(R.id.HelloButton)
        button.setOnClickListener{
            Log.v("Hello World", "Button Clicked")
            Toast.makeText(this, "Hello to you to!", Toast.LENGTH_SHORT).show()
        }

    }
}
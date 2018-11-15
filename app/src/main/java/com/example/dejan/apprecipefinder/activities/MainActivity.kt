package com.example.dejan.apprecipefinder.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.dejan.apprecipefinder.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        searchButtonId.setOnClickListener {
            startActivity(Intent(this, RecipeListActivity::class.java))
        }
    }
}

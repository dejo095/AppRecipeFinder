package com.example.dejan.apprecipefinder.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.example.dejan.apprecipefinder.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var ingredients = ingredeientsEdit.text.toString().trim()
        var searchTerm = searchTermEdit.text.toString().trim()

        searchButtonId.setOnClickListener {

            if (!TextUtils.isEmpty(ingredeientsEdit.text) && !TextUtils.isEmpty(searchTermEdit.text)) {
                var intent = Intent(this, RecipeListActivity::class.java)
                intent.putExtra("ingredients", ingredients)
                intent.putExtra("searchTerm", searchTerm)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Please enter search term and ingredients!", Toast.LENGTH_LONG).show()
            }

        }
    }


}

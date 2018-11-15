package com.example.dejan.apprecipefinder.activities

import android.app.DownloadManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.dejan.apprecipefinder.R
import com.example.dejan.apprecipefinder.model.Recipe
import org.json.JSONException
import org.json.JSONObject
import java.util.ArrayList

class RecipeListActivity : AppCompatActivity() {

    var volleyRequest: RequestQueue? = null
    var recipeList: ArrayList<Recipe>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_list)

        // temp
        var urlString = "http://www.recipepuppy.com/api/?i=onions,garlic&q=omelet&p=3"

        recipeList = ArrayList<Recipe>()
        volleyRequest = Volley.newRequestQueue(this)

        // cal api
        getRecipe(urlString)

    }

    fun getRecipe(url: String) {

        val recipeRequest = JsonObjectRequest(
            Request.Method.GET,
            url,
            Response.Listener {
                response: JSONObject ->
                try {

                    val resultArray = response.getJSONArray("results")

                    for (i in 0..resultArray.length() - 1) {

                        var recipeObj = resultArray.getJSONObject(i)

                        // get values from api results
                        var title = recipeObj.getString("title")
                        var link = recipeObj.getString("href")
                        var thumbnail = recipeObj.getString("thumbnail")
                        var ingredients = recipeObj.getString("ingredients")

                        Log.d("Result ==> ", title)

                        // create new recipe object
                        var recipe = Recipe()

                        // fill it with values
                        recipe.title = title
                        recipe.thumbnail = thumbnail
                        recipe.ingredients = ingredients
                        recipe.link = link

                        // in each iteration add recipe to recipelist
                        recipeList!!.add(recipe)

                    } // end for

                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            },
            Response.ErrorListener {
                error: VolleyError ->
                try {
                    Log.d("error", error.toString())
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            })

        volleyRequest!!.add(recipeRequest)
    }

}

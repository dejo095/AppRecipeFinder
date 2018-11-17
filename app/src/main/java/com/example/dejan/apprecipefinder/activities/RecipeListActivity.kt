package com.example.dejan.apprecipefinder.activities

import android.app.DownloadManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.dejan.apprecipefinder.R
import com.example.dejan.apprecipefinder.data.RecipeListAdapter
import com.example.dejan.apprecipefinder.model.LEFT_LINK
import com.example.dejan.apprecipefinder.model.QUERY
import com.example.dejan.apprecipefinder.model.Recipe
import kotlinx.android.synthetic.main.activity_recipe_list.*
import org.json.JSONException
import org.json.JSONObject
import java.util.ArrayList

class RecipeListActivity : AppCompatActivity() {

    var volleyRequest: RequestQueue? = null
    var recipeList: ArrayList<Recipe>? = null
    var recipeAdapter: RecipeListAdapter? = null
    var layoutManager: RecyclerView.LayoutManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_list)

        var url: String?

        var extras = intent.extras
        var ingredients = extras.get("ingredients")
        var searchTerm = extras.get("searchTerm")

        // construct url
        var tempurl = LEFT_LINK + ingredients + QUERY + searchTerm
        url = tempurl

        recipeList = ArrayList<Recipe>()
        volleyRequest = Volley.newRequestQueue(this)

        // cal api
        getRecipe(url)

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

                        // create new recipe object
                        var recipe = Recipe()

                        // fill it with values
                        recipe.title = title
                        if (!thumbnail.isBlank()) {
                            recipe.thumbnail = thumbnail
                        } else {
                            recipe.thumbnail = getString(R.mipmap.ic_launcher!!)
                        }
                        recipe.ingredients = "Ingredients: $ingredients"
                        recipe.link = link

                        // in each iteration add recipe to recipelist
                        recipeList!!.add(recipe)

                        // adapter
                        recipeAdapter = RecipeListAdapter(recipeList!!, this)
                        layoutManager = LinearLayoutManager(this)

                        // setup recyclerview
                        recyclerViewID.layoutManager = layoutManager
                        recyclerViewID.adapter = recipeAdapter
                    } // end for

                    recipeAdapter!!.notifyDataSetChanged() // !!IMPORTANT!!

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

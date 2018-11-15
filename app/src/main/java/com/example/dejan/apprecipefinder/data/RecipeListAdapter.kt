package com.example.dejan.apprecipefinder.data

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.dejan.apprecipefinder.R
import com.example.dejan.apprecipefinder.model.Recipe
import com.squareup.picasso.Picasso

class RecipeListAdapter(private val list: ArrayList<Recipe>, private val context: Context) : RecyclerView.Adapter<RecipeListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_row, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder!!.bindView(list[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        var title = itemView.findViewById<TextView>(R.id.recipeTitle)
        var thumbnail = itemView.findViewById<ImageView>(R.id.thumbnail)
        var ingredients = itemView.findViewById<TextView>(R.id.recipeIngredients)
        var linkButton = itemView.findViewById<Button>(R.id.linkButton)

        fun bindView(recipe: Recipe) {

            title.text = recipe.title
            ingredients.text = recipe.ingredients

            linkButton.setOnClickListener {

            }

            if (!TextUtils.isEmpty(recipe.thumbnail)) {
                Picasso
                    .get()
                    .load(recipe.thumbnail)
                    .placeholder(android.R.drawable.ic_menu_report_image)
                    .error(android.R.drawable.ic_menu_report_image)
                    .into(thumbnail)
            } else {
                Picasso
                    .get()
                    .load(R.mipmap.ic_launcher)
                    .into(thumbnail)
            }
        }

    }
}
package com.david.weathermvvm.view.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.david.weathermvvm.R
import com.david.weathermvvm.model.datasource.db.room.Cities
import javax.inject.Inject

class FavoriteAdapter
@Inject constructor(
    val deleteCity: (cityNames: String) -> Unit,
    val openFragment: (bundle: Bundle) -> Unit
) : ListAdapter<Cities, FavoriteAdapter.ViewHolder>(DIFF_CALLBACK) {


    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Cities>() {
            override fun areItemsTheSame(oldItem: Cities, newItem: Cities): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Cities, newItem: Cities): Boolean =
                oldItem == newItem

        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cityName: TextView = itemView.findViewById(R.id.tvCityNameFavorite)
        val deleteIcon: ImageView = itemView.findViewById(R.id.ivDeleteFavorite)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.favorite_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position).let { item ->
            holder.cityName.text = item.cityName
            holder.deleteIcon.setOnClickListener {
                deleteCity(item.cityName)
                Toast.makeText(
                    holder.itemView.context,
                    "City ${item.cityName} deleted",
                    Toast.LENGTH_SHORT
                ).show()
            }
            val bundle = Bundle()
            bundle.putString("cityName", item.cityName)
            holder.cityName.setOnClickListener {
                openFragment(bundle)
            }
        }
    }
}
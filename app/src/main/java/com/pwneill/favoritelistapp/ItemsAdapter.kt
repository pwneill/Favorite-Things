package com.pwneill.favoritelistapp

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ItemsAdapter (private val category: CategoryModel) : RecyclerView.Adapter<ItemViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {

        val view: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_view_holder, parent, false)

        return ItemViewHolder(view)

    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {

        holder.txtView.append(category.items[position])
        Log.i("cat", category.items[position])


    }

    override fun getItemCount(): Int {

        return category.items.size

    }


}
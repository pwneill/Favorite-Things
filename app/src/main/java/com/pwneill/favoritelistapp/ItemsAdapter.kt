package com.pwneill.favoritelistapp

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ItemsAdapter (private var category: CategoryModel) : RecyclerView.Adapter<ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {

        val view: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_view_holder, parent, false)

        return ItemViewHolder(view)

    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {

        holder.titleView.text = category.items[position].itemTitle
        holder.descView.text = category.items[position].itemDesc
        Log.i("cat", category.items[position].toString())


    }

    override fun getItemCount(): Int {

        return category.items.size

    }

    fun setCategory(cat: CategoryModel) {

        this.category = cat

    }


}
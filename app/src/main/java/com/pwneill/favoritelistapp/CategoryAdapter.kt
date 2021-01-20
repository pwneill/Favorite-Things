package com.pwneill.favoritelistapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView


class CategoryAdapter() :
    RecyclerView.Adapter<CategoryViewHolder>() {


    private val categories = arrayOf("Hobbies", "Sports", "Games", "Food", "Artists", "Beer")


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {

        val view: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.category_view_holder, parent, false)

        return CategoryViewHolder(view)

    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {

        val newPos = position + 1
        holder.txtCategoryNumber.text = newPos.toString()
        holder.txtCategoryName.text = categories[position]

    }

    override fun getItemCount(): Int {
        return categories.size
    }

    }


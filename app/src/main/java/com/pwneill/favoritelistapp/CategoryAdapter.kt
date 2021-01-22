package com.pwneill.favoritelistapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView


class CategoryAdapter(cat: ArrayList<CategoryModel>) :
    RecyclerView.Adapter<CategoryViewHolder>() {

    private val categories = ArrayList<CategoryModel>(cat)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {

        val view: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.category_view_holder, parent, false)

        return CategoryViewHolder(view)

    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {

        val newPos: Int  = if (position > 1) {
            position + 1

        } else {
            position
        }

        holder.txtCategoryNumber.text = newPos.toString()
        holder.txtCategoryName.text = categories.get(newPos).name

    }

    override fun getItemCount(): Int {
        return categories.size
    }

    fun addCategory(cat: CategoryModel) {

        categories.add(cat)
        notifyItemInserted(categories.size -1)

    }
   }


package com.pwneill.favoritelistapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView


class CategoryAdapter(cat: ArrayList<CategoryModel>, private val categoryIsClickedInterface: CategoryIsClickedListener) :
    RecyclerView.Adapter<CategoryViewHolder>() {

    private val categories = ArrayList<CategoryModel>(cat)

    interface CategoryIsClickedListener {

         fun categoryIsClicked (category: CategoryModel) {

         }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {

        val view: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.category_view_holder, parent, false)

        return CategoryViewHolder(view)

    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {

        val newPos: Int  = position

        holder.txtCategoryNumber.text = newPos.toString()
        holder.txtCategoryName.text = categories[newPos].name

        holder.itemView.setOnClickListener {
            categoryIsClickedInterface.categoryIsClicked(categories[newPos])
        }
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    fun addCategory(cat: CategoryModel) {

        categories.add(cat)
        notifyItemInserted(categories.size -1)

    }

   }


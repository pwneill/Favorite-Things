package com.pwneill.favoritelistapp

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CategoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val txtCategoryNumber: TextView = view.findViewById(R.id.category_number_textView)
    val txtCategoryName: TextView = view.findViewById(R.id.category_name_textView)

}

package com.pwneill.favoritelistapp

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class CategoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val textView: TextView

    init {
        // Define click listener for the ViewHolder's View.
        textView = view.findViewById(R.id.category_listView)
    }
}

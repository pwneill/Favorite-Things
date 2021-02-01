package com.pwneill.favoritelistapp

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

data class ItemViewHolder(val view: View) : RecyclerView.ViewHolder(view)  {

    val titleView: TextView = itemView.findViewById(R.id.itemTitleView)
    val descView: TextView = itemView.findViewById(R.id.itemDescView)


}
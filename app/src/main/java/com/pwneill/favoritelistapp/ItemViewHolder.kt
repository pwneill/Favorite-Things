package com.pwneill.favoritelistapp

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

data class ItemViewHolder(val view: View) : RecyclerView.ViewHolder(view)  {

    val txtView: TextView = itemView.findViewById(R.id.itemTextView)


}
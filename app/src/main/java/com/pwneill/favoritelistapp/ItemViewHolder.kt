package com.pwneill.favoritelistapp

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view)  {

    public val txtView: TextView = itemView.findViewById(R.id.itemTextView)


}
package com.pwneill.favoritelistapp
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CategoryModel (val name: String, val items: ArrayList<String>) : Parcelable {



}

package com.pwneill.favoritelistapp

import kotlinx.serialization.Serializable

@Serializable
data class CategoryModel (val name: String, val items: ArrayList<String>) : java.io.Serializable {



}

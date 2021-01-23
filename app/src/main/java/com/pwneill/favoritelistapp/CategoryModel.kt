package com.pwneill.favoritelistapp

import kotlinx.serialization.Serializable

@Serializable
class CategoryModel (val name: String, val items: ArrayList<String>) {

}

//fun main () {
//
//    val data = Project(this.name, this.items)
//
//}
package com.pwneill.favoritelistapp

import android.content.Context
import androidx.preference.PreferenceManager


class CategoryManager(private val context: Context) {

    fun saveCategory(category: CategoryModel) {

        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        val editor = sharedPreferences.edit()

        val itemHashSet = HashSet(category.items as List<String>)

        editor.putStringSet(category.name, itemHashSet)

        editor.apply()
    }

    fun retrieveCategories(): ArrayList<CategoryModel> {

        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        val data = sharedPreferences.all

        val categories = ArrayList<CategoryModel>()

        for (item in data) {

            val i = item.value as ArrayList<CategoryItemModel>
            val category = CategoryModel(item.key, i)

                categories.add(category)
            }

        return categories

    }

}
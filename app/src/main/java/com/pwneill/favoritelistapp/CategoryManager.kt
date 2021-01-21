package com.pwneill.favoritelistapp

import android.R.attr
import android.content.Context
import androidx.preference.PreferenceManager


class CategoryManager(private val context: Context) {

    fun saveCategory(category: CategoryModel) {

        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        val editor = sharedPreferences.edit()

        val itemHashSet = hashSetOf(category.items)

        editor.putStringSet(category.name, setOf(itemHashSet.toString()))

        editor.apply()
    }

    fun retrieveCategories(): ArrayList<CategoryModel> {

        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        val data = sharedPreferences.all

        val categories = ArrayList<CategoryModel>()

        for (item in data) {

            val category = CategoryModel(item.key, arrayListOf(item.value.toString()))

            categories.add(category)

        }

        return categories

    }
}
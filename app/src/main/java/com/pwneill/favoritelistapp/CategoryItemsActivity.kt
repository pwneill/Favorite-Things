package com.pwneill.favoritelistapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class CategoryItemsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_items)

        val category: CategoryModel =
            Json.decodeFromString(intent.getSerializableExtra(MainActivity().categoryObjKey) as String)

        title = category.name
    }
}
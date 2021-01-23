package com.pwneill.favoritelistapp

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

   private val categoryManager = CategoryManager(this)
    private lateinit var categoryRecyclerView: RecyclerView
    val CATEGORY_OBJECT_KEY: String = "CATEGORY_KEY"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))


        val categories: ArrayList<CategoryModel> = categoryManager.retrieveCategories()
         categoryRecyclerView = findViewById(R.id.category_listView)
        categoryRecyclerView.adapter = CategoryAdapter(categories)
        categoryRecyclerView.layoutManager = LinearLayoutManager(this@MainActivity)

        val fab: FloatingActionButton = findViewById((R.id.fab))
        fab.setOnClickListener {
            Toast.makeText(this@MainActivity, "Ghostride the Whip", Toast.LENGTH_SHORT).show()
            displayCreateCategoryDialog()
        }
    }

    private fun displayCreateCategoryDialog() {

        val alertTitle = getString(R.string.alertTitle)
        val positiveBtnTitle = getString(R.string.btnTitle)

        val alertDialogBuilder = Builder(this@MainActivity)
        alertDialogBuilder.setTitle(alertTitle)

            val categoryEditText = EditText(this@MainActivity)
            alertDialogBuilder.setView(categoryEditText)
            alertDialogBuilder.setPositiveButton(positiveBtnTitle) { dialogInterface , _ ->

                val category = CategoryModel(categoryEditText.text.toString(), ArrayList<String>())
                categoryManager.saveCategory((category))

                val categoryRecyclerAdapter: CategoryAdapter = categoryRecyclerView.adapter as CategoryAdapter
                categoryRecyclerAdapter.addCategory(category)

                dialogInterface.dismiss()
            }

        alertDialogBuilder.show()
     }

    private fun displayCategoryItems(cat: CategoryModel) {

        val categoryItemsIntent = Intent(this, CategoryItemsActivity::class.java)
//        categoryItemsIntent.putExtra(CATEGORY_OBJECT_KEY, cat)
    }
}
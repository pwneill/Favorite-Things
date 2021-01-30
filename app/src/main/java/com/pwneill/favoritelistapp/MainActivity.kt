package com.pwneill.favoritelistapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AlertDialog.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(), CategoryFragment.OnCategoryInteractionListener {

    private val categoryFragment: CategoryFragment = CategoryFragment()

    val categoryObjKey: String = "CATEGORY_OBJECT_KEY"
    private val mainActivityReqCode: Int = 69

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        supportFragmentManager.beginTransaction()
            .add(R.id.category_fragment_container, categoryFragment)
            .commit()

        val fab: FloatingActionButton = findViewById((R.id.fab))
        fab.setOnClickListener {
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

                val category = CategoryModel(categoryEditText.text.toString(), ArrayList())
                categoryFragment.addCategoryToManager(category)

                dialogInterface.dismiss()
                displayCategoryItems(category)
            }

        alertDialogBuilder.show()
     }

    private fun displayCategoryItems(cat: CategoryModel) {

        val categoryItemsIntent = Intent(this, CategoryItemsActivity::class.java)
        categoryItemsIntent.putExtra(categoryObjKey, cat)

        startActivityForResult(categoryItemsIntent,mainActivityReqCode)

    }

    @Override
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == mainActivityReqCode && resultCode == Activity.RESULT_OK) {

            if (data != null) categoryFragment.saveCategory(data.getParcelableExtra<CategoryModel>(categoryObjKey) as CategoryModel)
        }
    }

    override fun categoryIsTapped(cat: CategoryModel) {

        displayCategoryItems(cat)

        }
    }

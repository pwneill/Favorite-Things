package com.pwneill.favoritelistapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.InputType.TYPE_CLASS_TEXT
import android.widget.EditText
import android.widget.FrameLayout
import androidx.appcompat.app.AlertDialog.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(), CategoryFragment.OnCategoryInteractionListener {

    private lateinit var categoryFragment: CategoryFragment
    private var isTablet: Boolean = false
    private var categoryItemsFragment: CategoryItemsFragment? = null
    private var categoryItemsFragmentContainer: FrameLayout? = null
    private lateinit var fab: FloatingActionButton

    val categoryObjKey: String = "CATEGORY_OBJECT_KEY"
    private val mainActivityReqCode: Int = 69

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        categoryFragment = supportFragmentManager.findFragmentById(R.id.category_fragment) as CategoryFragment
        categoryItemsFragmentContainer = findViewById(R.id.category_items_fragment_container)

        isTablet = categoryItemsFragmentContainer != null
        fab = findViewById(R.id.fab)

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

        if (!isTablet) {

            val categoryItemsIntent = Intent(this, CategoryItemsActivity::class.java)
            categoryItemsIntent.putExtra(categoryObjKey, cat)

            startActivityForResult(categoryItemsIntent,mainActivityReqCode)

        } else {
            title = cat.name
            if (categoryItemsFragment != null) {

                categoryItemsFragment = CategoryItemsFragment.newInstance(cat)
                supportFragmentManager.beginTransaction()
                    .replace(R.id.category_items_fragment_container, categoryItemsFragment!!)
                    .addToBackStack(null).commit()

            }

            fab.setOnClickListener {
                displayCreateCategoryItemDialog()
            }

        }

    }

    private fun displayCreateCategoryItemDialog() {

        val editItemText = EditText(this@MainActivity)
        editItemText.inputType = TYPE_CLASS_TEXT

        val alertDialogBuilder = Builder(this@MainActivity)
        alertDialogBuilder.setTitle("Enter Item Name Here")
            .setView(editItemText)
            .setPositiveButton("Create"){ dialogInterface , _ ->

                val item: String = editItemText.text.toString()
                categoryItemsFragment?.addItemToCategory(item)
                dialogInterface.dismiss()

            }

            alertDialogBuilder.show()

    }

    @Override
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == mainActivityReqCode && resultCode == Activity.RESULT_OK) {

            if (data != null) categoryFragment.saveCategory(data.getParcelableExtra<CategoryModel>(categoryObjKey) as CategoryModel)
        }
    }

    @Override
    override fun categoryIsTapped(cat: CategoryModel) {

        displayCategoryItems(cat)

        }

    @Override
    override fun onBackPressed() {
        super.onBackPressed()
        setTitle(R.string.app_name)

        if (categoryItemsFragment?.category != null) {

            categoryFragment.categoryManager.saveCategory(categoryItemsFragment!!.category)
        }

        if (categoryItemsFragment != null) {

            supportFragmentManager.beginTransaction().remove(categoryItemsFragment!!).commit()
            categoryItemsFragment = null

        }

        fab.setOnClickListener {

            displayCreateCategoryDialog()
        }

    }
    }

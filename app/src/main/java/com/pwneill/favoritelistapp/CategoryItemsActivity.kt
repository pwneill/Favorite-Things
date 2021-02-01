package com.pwneill.favoritelistapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.InputType.TYPE_CLASS_TEXT
import android.widget.EditText
import android.widget.LinearLayout
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton


class CategoryItemsActivity : AppCompatActivity() {

    private lateinit var itemsRecyclerView: RecyclerView
    private lateinit var addItemFloatingActionButton: FloatingActionButton
    private lateinit var category: CategoryModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_items)

        category = intent.getParcelableExtra(MainActivity().categoryObjKey)!!
        title = category.name

        itemsRecyclerView = findViewById(R.id.itemsRecyclerView)
        itemsRecyclerView.adapter = ItemsAdapter(category)
        itemsRecyclerView.layoutManager = LinearLayoutManager(this@CategoryItemsActivity)
        addItemFloatingActionButton = findViewById(R.id.addItemBtn)

        addItemFloatingActionButton.setOnClickListener {

            displayCreateItemDialog()

        }
    }

    private fun displayCreateItemDialog() {


        val layout = LinearLayout(this@CategoryItemsActivity)
        val itemTitleEditText = EditText(this@CategoryItemsActivity)
        itemTitleEditText.inputType = TYPE_CLASS_TEXT

        val itemDescEditText = EditText(this@CategoryItemsActivity)
        itemDescEditText.inputType = TYPE_CLASS_TEXT

        layout.addView(itemTitleEditText)
        layout.addView(itemDescEditText)

        AlertDialog.Builder(this@CategoryItemsActivity)
                .setTitle(getString(R.string.itemDialogTitle))
                .setView(layout)
                .setPositiveButton(getString(R.string.positiveBtnItem)) { dialogInterface, _ ->

                    val itemName: String = itemTitleEditText.text.toString()
                    val itemDesc: String = itemDescEditText.text.toString()

                    val item = CategoryItemModel(itemName, itemDesc)

                    category.items.add(item)

                    val itemsRecyclerAdapter: ItemsAdapter = itemsRecyclerView.adapter as ItemsAdapter
                    itemsRecyclerAdapter.notifyItemInserted(category.items.size - 1)

                    dialogInterface.dismiss()
                }
                .create()
                .show()
    }

    override fun onBackPressed() {

        val bundle = Bundle()
        bundle.putParcelable(MainActivity().categoryObjKey, category)

        val intent = Intent()
        intent.putExtras(bundle)
        setResult(Activity.RESULT_OK, intent)


        super.onBackPressed()

    }
}

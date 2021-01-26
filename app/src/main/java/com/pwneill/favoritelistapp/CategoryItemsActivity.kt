package com.pwneill.favoritelistapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.InputType.TYPE_CLASS_TEXT
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class CategoryItemsActivity : AppCompatActivity() {

    private lateinit var itemsRecyclerView: RecyclerView
    private lateinit var addItemFloatingActionButton: FloatingActionButton
    private lateinit var category: CategoryModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_items)

        category = Json.decodeFromString(intent.getSerializableExtra(MainActivity().categoryObjKey) as String)
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

        val itemEditText = EditText(this@CategoryItemsActivity)
        itemEditText.inputType = TYPE_CLASS_TEXT

        AlertDialog.Builder(this@CategoryItemsActivity)
                .setTitle(getString(R.string.itemDialogTitle))
                .setView(itemEditText)
                .setPositiveButton(getString(R.string.positiveBtnItem)) { dialogInterface, _ ->

                    val itemName: String = itemEditText.text.toString()
                    category.items.add(itemName)

                    val itemsRecyclerAdapter: ItemsAdapter = itemsRecyclerView.adapter as ItemsAdapter
                    itemsRecyclerAdapter.notifyItemInserted(category.items.size - 1)

                    dialogInterface.dismiss()
                }
                .create()
                .show()
    }

    override fun onBackPressed() {

        val bundle = Bundle()
        bundle.putSerializable(MainActivity().categoryObjKey, category)

        val intent = Intent()
        intent.putExtras(bundle)
        setResult(Activity.RESULT_OK, intent)


        super.onBackPressed()

    }
}

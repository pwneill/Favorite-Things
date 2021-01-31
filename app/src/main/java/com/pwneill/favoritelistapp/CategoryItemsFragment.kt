package com.pwneill.favoritelistapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class CategoryItemsFragment : Fragment() {

    private val categoryArgs: String = "category_args"
    private lateinit var itemsRecyclerView: RecyclerView
    lateinit var category: CategoryModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (arguments != null) {

            category = requireArguments().getParcelable(categoryArgs)!!

        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.fragment_category_items, container, false)
        itemsRecyclerView = view.findViewById(R.id.itemsRecyclerView)
        itemsRecyclerView.adapter = ItemsAdapter(category)

        itemsRecyclerView.layoutManager = LinearLayoutManager(context)

        // Inflate the layout for this fragment
        return view
    }

    fun addItemToCategory(item: String) {
        category.items.add(item)
        val itemsAdapter: ItemsAdapter = itemsRecyclerView.adapter as ItemsAdapter
        itemsAdapter.setCategory(category)
        itemsAdapter.notifyDataSetChanged()
    }

    companion object {

        @JvmStatic
        fun newInstance(cat: CategoryModel) =
            CategoryItemsFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(categoryArgs, cat)
                }
            }
    }
}
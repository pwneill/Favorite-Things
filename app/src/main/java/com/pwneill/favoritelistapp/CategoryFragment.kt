package com.pwneill.favoritelistapp

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class CategoryFragment : Fragment(), CategoryAdapter.CategoryIsClickedListener {

    private lateinit var categoryManager: CategoryManager
    private lateinit var categoryRecyclerView: RecyclerView

    interface OnCategoryInteractionListener {

        fun categoryIsTapped(cat: CategoryModel)

    }

    private var listenerObj: OnCategoryInteractionListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is OnCategoryInteractionListener) {

            listenerObj = context
            categoryManager = CategoryManager(context)


        } else {
            throw RuntimeException("ERROR: Context for Activity must implement OnCategoryInteractionListener")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_category, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val categories: ArrayList<CategoryModel> = categoryManager.retrieveCategories()
        if (view != null) {
            categoryRecyclerView = this.requireView().findViewById(R.id.category_listView)
            categoryRecyclerView.adapter = CategoryAdapter(categories, this)
            categoryRecyclerView.layoutManager = LinearLayoutManager(activity)
        }
    }


    override fun onDetach() {
        super.onDetach()
        listenerObj = null

    }

    @Override
    override fun categoryIsClicked(category: CategoryModel) {

        if (listenerObj != null) {
            listenerObj?.categoryIsTapped(category)

        }
    }

    fun addCategoryToManager(category: CategoryModel) {

        categoryManager.saveCategory(category)
        val categoryRecyclerAdapter: CategoryAdapter = categoryRecyclerView.adapter as CategoryAdapter
        categoryRecyclerAdapter.addCategory(category)

    }

    fun saveCategory(category: CategoryModel) {
        categoryManager.saveCategory(category)
        updateCategories()

    }

    private fun updateCategories() {
        val categories: ArrayList<CategoryModel> = categoryManager.retrieveCategories()
        categoryRecyclerView.adapter = CategoryAdapter(categories, this)
    }

    companion object {

        @JvmStatic
        fun newInstance() =
                CategoryFragment().apply {
                    arguments = Bundle().apply {
                    }
                }
    }
}
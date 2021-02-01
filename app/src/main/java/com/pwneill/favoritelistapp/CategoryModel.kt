package com.pwneill.favoritelistapp
import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class CategoryModel(val name: String, val items: @RawValue ArrayList<CategoryItemModel>) : Parcelable {



}

class CategoryItemModel (val itemTitle: String, val itemDesc: String){

}

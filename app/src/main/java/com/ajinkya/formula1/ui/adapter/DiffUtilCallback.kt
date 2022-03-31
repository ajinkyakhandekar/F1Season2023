package com.ajinkya.formula1.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.ajinkya.formula1.common.jsonString

class DiffUtilCallback<Data : Any>(
    private val oldList: List<Data>,
    private val newList: List<Data>
) : DiffUtil.Callback() {

    override fun getOldListSize() = oldList.size
    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition].jsonString
        val newItem = newList[newItemPosition].jsonString

        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]

        return oldItem == newItem
    }

}
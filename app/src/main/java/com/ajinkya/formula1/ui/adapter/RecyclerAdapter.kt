package com.ajinkya.formula1.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.ajinkya.formula1.common.log
import com.ajinkya.formula1.common.toast
import kotlin.reflect.KClass

class RecyclerAdapter<Data : Any, VB : ViewBinding>(
    private val bindingInflater: (LayoutInflater, ViewGroup, Boolean) -> VB,
    private val bind: VB.(Data, Int) -> Unit
) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder<VB>>() {

    var setClickListeners: (ViewHolder<VB>.(MutableList<Data>) -> Unit)? = null
    private var itemList = mutableListOf<Data>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder<VB> {
        val binding = bindingInflater.invoke(LayoutInflater.from(parent.context), parent, false)
        val holder = ViewHolder(binding)
        setClickListeners?.invoke(holder, itemList)

        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder<VB>, position: Int) {
        holder.binding.bind(itemList[position], position)
    }

    override fun getItemCount() = itemList.count()

    fun updateData(itemList: List<Data>) {
        val diffUtilCallback = DiffUtilCallback(this.itemList, itemList)
        val diffResult = DiffUtil.calculateDiff(diffUtilCallback)

        this.itemList.clear()
        this.itemList.addAll( itemList)
        diffResult.dispatchUpdatesTo(this)
    }

    class ViewHolder<VB : ViewBinding>(val binding: VB) : RecyclerView.ViewHolder(binding.root)

}


internal fun <Data : Any, VB : ViewBinding> RecyclerView.withAdapter(
    bindingInflater: (LayoutInflater, ViewGroup, Boolean) -> VB,
    bind: VB.(Data, Int) -> Unit
): RecyclerAdapter<Data, VB> {
    val recyclerAdapter = RecyclerAdapter(bindingInflater, bind)
    itemAnimator = null
    adapter = recyclerAdapter
    return recyclerAdapter
}
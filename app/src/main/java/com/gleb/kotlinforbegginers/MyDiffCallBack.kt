package com.gleb.kotlinforbegginers

import androidx.recyclerview.widget.DiffUtil

open class MyDiffCallBack(
    private val oldList: List<FilmCard>,
    private val newList: List<FilmCard>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return  oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}
package com.gleb.kotlinforbegginers.viewmodel

import androidx.recyclerview.widget.DiffUtil
import com.gleb.kotlinforbegginers.model.FilmCardDTO
import com.gleb.kotlinforbegginers.model.ReviewCardDTO

open class DiffCallBackForReview(
    private val oldList: List<ReviewCardDTO?>,
    private val newList: List<ReviewCardDTO?>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
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
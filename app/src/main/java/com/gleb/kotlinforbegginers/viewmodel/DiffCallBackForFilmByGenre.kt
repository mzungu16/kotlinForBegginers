package com.gleb.kotlinforbegginers.viewmodel

import androidx.recyclerview.widget.DiffUtil
import com.gleb.kotlinforbegginers.model.FilmByGenreCardDTO
import com.gleb.kotlinforbegginers.model.GenreCardDTO

class DiffCallBackForFilmByGenre(
    private val oldList: List<FilmByGenreCardDTO?>,
    private val newList: List<FilmByGenreCardDTO?>
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
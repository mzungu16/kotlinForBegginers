package com.gleb.kotlinforbegginers

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

class GenresAdapter : RecyclerView.Adapter<GenresAdapter.GenresAdapterViewHolder>() {

    private var genreCards: List<GenreCardDTO?> = listOf()

    var listener: GenresAdapter.OnItemClick? = null


    fun setGenreCards(genreCardsParam: List<GenreCardDTO?>) {
        val diffCallBack = DiffCallBackForGenre(this.genreCards, genreCardsParam)
        DiffUtil.calculateDiff(diffCallBack).also { diffResult ->
            diffResult.dispatchUpdatesTo(this)
        }
        genreCards = genreCardsParam
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenresAdapterViewHolder {
        return GenresAdapterViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.recycler_view_item_genres, parent, false)
        )
    }

    override fun onBindViewHolder(holder: GenresAdapterViewHolder, position: Int) {
        holder.binding(genreCards[position])
    }

    override fun getItemCount(): Int = genreCards.size

    inner class GenresAdapterViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun binding(card: GenreCardDTO?) {
            with(itemView) {
                findViewById<TextView>(R.id.genreText).apply {
                    text = card?.name
                }
            }
        }
    }

    interface OnItemClick {

    }
}

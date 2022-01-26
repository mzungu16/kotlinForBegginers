package com.gleb.kotlinforbegginers.viewmodel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.gleb.kotlinforbegginers.R
import com.gleb.kotlinforbegginers.model.FilmCardDTO
import com.gleb.kotlinforbegginers.model.GenreCardDTO

class GenreAdapter : RecyclerView.Adapter<GenreAdapter.GenreViewHolder>() {
    private var genreCards = listOf<GenreCardDTO?>()
    var listener: OnItemClick? = null
    fun setGenreCards(genreCardParam: List<GenreCardDTO?>) {
        val diffCallBack = DiffCallBackForGenre(this.genreCards, genreCardParam)
        DiffUtil.calculateDiff(diffCallBack).also { diffResult ->
            diffResult.dispatchUpdatesTo(this)
        }
        genreCards = genreCardParam
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
        return GenreViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.recycler_view_item_genres, parent, false)
        )
    }

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        holder.binding(genreCards[position])
    }

    override fun getItemCount() = genreCards.size

    inner class GenreViewHolder(view: View) : RecyclerView.ViewHolder(view){
        fun binding(genre:GenreCardDTO?){
            with(itemView){
                findViewById<Button>(R.id.genreText).apply{
                    text = genre?.name
                    setOnClickListener(View.OnClickListener {
                        listener?.onClick(genre)
                    })
                }
            }
        }
    }
    interface OnItemClick {
        fun onClick(genreCard: GenreCardDTO?)
    }
}
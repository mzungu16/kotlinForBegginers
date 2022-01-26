package com.gleb.kotlinforbegginers.viewmodel

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.gleb.kotlinforbegginers.viewmodel.DiffCallBackForFilm
import com.gleb.kotlinforbegginers.R
import com.gleb.kotlinforbegginers.model.FilmCardDTO

class FilmAdapter : RecyclerView.Adapter<FilmAdapter.MyViewHolder>() {
    private var filmCards: List<FilmCardDTO?> = listOf()

    var listener: OnItemClick? = null

    fun setFilmCards(filmCardsParam: List<FilmCardDTO?>) {
        val diffCallBack = DiffCallBackForFilm(this.filmCards, filmCardsParam)
        DiffUtil.calculateDiff(diffCallBack).also { diffResult ->
            diffResult.dispatchUpdatesTo(this)
        }
        filmCards = filmCardsParam
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding(filmCards[position])
    }

    override fun getItemCount(): Int = filmCards.size

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun binding(card: FilmCardDTO?) {

            with(itemView) {
                findViewById<ImageView>(R.id.front_image_id).apply {
                    load("https://image.tmdb.org/t/p/original/${card?.poster_path}")
                    setOnClickListener(View.OnClickListener {
                        listener?.onClick(card)
                    })
                }
            }
        }
    }

    interface OnItemClick {
        fun onClick(filmCard: FilmCardDTO?)
    }
}
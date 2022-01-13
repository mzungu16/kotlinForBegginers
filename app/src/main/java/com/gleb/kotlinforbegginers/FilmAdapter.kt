package com.gleb.kotlinforbegginers

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.api.load

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
                /* findViewById<ImageView>(R.id.back_image_id).apply {
                     Picasso.with(context).load("https://image.tmdb.org/t/p/w500/${card?.poster_path}").into(this)
                     scaleType = ImageView.ScaleType.CENTER_CROP
                 }*/
                findViewById<ImageView>(R.id.front_image_id).apply {
                    load("https://image.tmdb.org/t/p/original/${card?.poster_path}")
                    setOnClickListener(View.OnClickListener {
                        listener?.onClick(card)
                    })
                }
                findViewById<Button>(R.id.voteAverage).apply {
                    text = card?.vote_average.toString()
                    setTextColor(Color.BLACK)
                    textSize = 15F
                }
                /*findViewById<TextView>(R.id.title_of_film_id).apply {
                    text = card?.original_title
                    textSize = 12F
                }*/
            }
        }
    }

    interface OnItemClick {
        fun onClick(filmCard: FilmCardDTO?)
    }
}
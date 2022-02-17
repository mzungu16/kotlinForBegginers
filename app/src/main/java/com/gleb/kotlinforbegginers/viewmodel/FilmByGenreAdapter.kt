package com.gleb.kotlinforbegginers.viewmodel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.gleb.kotlinforbegginers.R
import com.gleb.kotlinforbegginers.model.FilmByGenreCardDTO

class FilmByGenreAdapter : RecyclerView.Adapter<FilmByGenreAdapter.FilmByGenreViewHolder>() {
    private var filmByGenreCards = listOf<FilmByGenreCardDTO?>()
    var listener: OnItemClick? = null
    fun setFilmByGenreCard(filmByGenreCardParam: List<FilmByGenreCardDTO?>) {
        val diffCallBack = DiffCallBackForFilmByGenre(this.filmByGenreCards, filmByGenreCardParam)
        DiffUtil.calculateDiff(diffCallBack).also { diffResult ->
            diffResult.dispatchUpdatesTo(this)
        }
        filmByGenreCards = filmByGenreCardParam
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmByGenreViewHolder {
        return FilmByGenreViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.film_by_genre_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: FilmByGenreViewHolder, position: Int) {
        holder.binding(filmByGenreCards[position])
    }

    override fun getItemCount() = filmByGenreCards.size

    inner class FilmByGenreViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun binding(filmByGenre: FilmByGenreCardDTO?) {
            with(itemView) {
                findViewById<ImageView>(R.id.front_image_id2).apply {
                    load("https://image.tmdb.org/t/p/original/${filmByGenre?.poster_path}")
                    setOnClickListener(View.OnClickListener {
                        listener?.onClick(filmByGenre)
                    })
                }
               /* findViewById<Button>(R.id.voteAverage2).apply {
                    text = filmByGenre?.vote_average.toString()
                    setTextColor(Color.BLACK)
                    textSize = 15F
                }*/
            }
        }
    }

    interface OnItemClick {
        fun onClick(filmByGenre: FilmByGenreCardDTO?)
    }
}
package com.gleb.kotlinforbegginers

import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
    private var filmCards: List<FilmCard> = listOf()

    var listener: OnItemClick? = null

    fun setFilmCards(filmCardsParam: List<FilmCard>) {
        filmCards = filmCardsParam
        notifyDataSetChanged()
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
        fun binding(card: FilmCard) {

            with(itemView) {
                findViewById<ImageView>(R.id.back_image_id).apply {
                    setImageResource(card.image)
                    scaleType = ImageView.ScaleType.CENTER_CROP
                }
                findViewById<ImageView>(R.id.front_image_id).apply {
                    setImageResource(card.image)
                    scaleType = ImageView.ScaleType.CENTER_CROP
                    setOnClickListener(View.OnClickListener {
                        listener?.onClick(card)
                    })
                }
                findViewById<TextView>(R.id.title_of_film_id).apply {
                    text = card.title
                    textSize = 30F
                    typeface = Typeface.DEFAULT_BOLD
                }
            }
        }
    }

    interface OnItemClick {
        fun onClick(filmCard: FilmCard)
    }
}
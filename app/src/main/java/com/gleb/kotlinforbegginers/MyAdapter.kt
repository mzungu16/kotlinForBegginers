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
            val imageBack = itemView.findViewById<ImageView>(R.id.back_image_id)
            imageBack.setImageResource(card.image)
            imageBack.scaleType = ImageView.ScaleType.CENTER_CROP
            val image = itemView.findViewById<ImageView>(R.id.front_image_id)
            image.setImageResource(card.image)
            image.scaleType = ImageView.ScaleType.CENTER_CROP
            image.setOnClickListener(View.OnClickListener {
                listener?.onClick(card)

            })
            val title = itemView.findViewById<TextView>(R.id.title_of_film_id)
            title.text = card.title
            title.textSize = 30F
            title.typeface = Typeface.DEFAULT_BOLD
        }
    }

    interface OnItemClick {
        fun onClick(filmCard: FilmCard)
    }
}
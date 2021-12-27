package com.gleb.kotlinforbegginers

import android.graphics.Typeface
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

class MyAdapter : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
    private var filmCards: List<FactDTO?> = listOf()

    var listener: OnItemClick? = null

    fun setFilmCards(filmCardsParam: List<FactDTO?>) {
        val diffCallBack = MyDiffCallBack(this.filmCards, filmCardsParam)
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
        fun binding(card: FactDTO?) {

            with(itemView) {
                findViewById<ImageView>(R.id.back_image_id).apply {
                    setImageURI(Uri.parse(card?.poster_path))
                    scaleType = ImageView.ScaleType.CENTER_CROP
                }
                findViewById<ImageView>(R.id.front_image_id).apply {
                    setImageURI(Uri.parse(card?.poster_path))
                    scaleType = ImageView.ScaleType.CENTER_CROP
                    setOnClickListener(View.OnClickListener {
                        listener?.onClick(card)
                    })
                }
                findViewById<TextView>(R.id.title_of_film_id).apply {
                    text = card?.original_title
                    textSize = 30F
                    typeface = Typeface.DEFAULT_BOLD
                }
            }
        }
    }

    interface OnItemClick {
        fun onClick(filmCard: FactDTO?)
    }
}
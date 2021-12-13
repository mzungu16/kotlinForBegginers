package com.gleb.kotlinforbegginers

import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.RecyclerView


class MyAdapter(private var list: List<Card>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var titleText: TextView? = null
        var frontImage: ImageView? = null
        var beckImage: ImageView? = null
        var checkButton: AppCompatButton? = null

        init {
            titleText = itemView.findViewById(R.id.title_of_film_id)
            frontImage = itemView.findViewById(R.id.front_image_id)
            beckImage = itemView.findViewById(R.id.beck_image_id)
            checkButton = itemView.findViewById(R.id.check_film_button_id)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycle_view_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        for (i in list.indices) {
            holder.titleText?.text = list[position].title
            holder.titleText?.textSize = 30F
            holder.titleText?.typeface = Typeface.DEFAULT_BOLD
            holder.frontImage?.setImageResource(list[position].image)
            holder.frontImage?.scaleType = ImageView.ScaleType.CENTER_CROP
            holder.beckImage?.setImageResource(list[position].image)
            holder.beckImage?.scaleType = ImageView.ScaleType.CENTER_CROP
        }

    }

    override fun getItemCount() = list.size

}
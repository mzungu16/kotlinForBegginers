package com.gleb.kotlinforbegginers.viewmodel

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.gleb.kotlinforbegginers.R
import com.gleb.kotlinforbegginers.model.FilmCardDTO
import com.gleb.kotlinforbegginers.model.ReviewCardDTO

class ReviewAdapter : RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder>() {
    private var filmReviews: List<ReviewCardDTO?> = listOf()

    fun setFilmCards(filmReviewsParam: List<ReviewCardDTO?>) {
        val diffCallBack = DiffCallBackForReview(this.filmReviews, filmReviewsParam)
        DiffUtil.calculateDiff(diffCallBack).also { diffResult ->
            diffResult.dispatchUpdatesTo(this)
        }
        filmReviews = filmReviewsParam
    }


    inner class ReviewViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun binding(reviewCard: ReviewCardDTO?) {
            with(itemView) {
                findViewById<ImageView>(R.id.avatar_id).apply {
                    Log.d("TAGUS", "${reviewCard?.author_details?.avatar_path}")
                    load("${reviewCard?.author_details?.avatar_path}"){
                        placeholder(R.drawable.ic_no_photo_vector)
                    }
                }
                findViewById<TextView>(R.id.header_id).text = reviewCard?.author
                findViewById<TextView>(R.id.content_id).text = reviewCard?.content
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        return ReviewViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.review_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        holder.binding(filmReviews[position])
    }

    override fun getItemCount() = filmReviews.size

}
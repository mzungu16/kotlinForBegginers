package com.gleb.kotlinforbegginers.model

class ReviewsDTO(
    val results: List<ReviewCardDTO?>
)

data class ReviewCardDTO(
    val author: String?,
    val author_details:DetailsDTO?,
    val content: String?
)

data class DetailsDTO(
    val avatar_path: String?,
)
package com.gleb.kotlinforbegginers


import android.media.Image
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheet(val filmCard: FilmCard) : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val customView = layoutInflater.inflate(R.layout.bottom_sheet_layout, null)
        val image1 = customView.findViewById<ImageView>(R.id.first_image)
        image1.setImageResource(filmCard.imageOfMainActor)
        image1.scaleType = ImageView.ScaleType.CENTER_CROP
        val image2 = customView.findViewById<ImageView>(R.id.second_image)
        image2.setImageResource(filmCard.imageOfMainActor2)
        image2.scaleType = ImageView.ScaleType.CENTER_CROP
        val image3 = customView.findViewById<ImageView>(R.id.third_image)
        image3.setImageResource(filmCard.imageOfMainActor3)
        image3.scaleType = ImageView.ScaleType.CENTER_CROP
        val textView = customView.findViewById<TextView>(R.id.text_of_description)
        textView.text = filmCard.description
        return customView
    }

}
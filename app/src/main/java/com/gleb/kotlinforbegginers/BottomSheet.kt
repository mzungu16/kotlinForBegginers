package com.gleb.kotlinforbegginers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheet(val filmCard: FactDTO?) : BottomSheetDialogFragment() {

    private val customView: View by lazy {
        layoutInflater.inflate(R.layout.bottom_sheet_layout, null)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        with(customView) {
           /* findViewById<ImageView>(R.id.first_image).apply {
                setImageResource(filmCard.imageOfMainActor)
                scaleType = ImageView.ScaleType.CENTER_CROP
            }*/
            /*findViewById<ImageView>(R.id.second_image).apply {
                setImageResource(filmCard.imageOfMainActor2)
                scaleType = ImageView.ScaleType.CENTER_CROP
            }
*/            /*findViewById<ImageView>(R.id.third_image).apply {
                setImageResource(filmCard.imageOfMainActor3)
                scaleType = ImageView.ScaleType.CENTER_CROP
            }*/
            findViewById<TextView>(R.id.text_of_description).apply {
                text = filmCard?.overview
            }
        }
        return customView
    }
}
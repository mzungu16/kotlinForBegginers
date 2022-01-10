package com.gleb.kotlinforbegginers

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.squareup.picasso.Picasso

class BottomSheet(val filmCard: FilmCardDTO?) : BottomSheetDialogFragment() {
    private val viewModel: BottomSheetViewModel by lazy {
        ViewModelProvider(this).get(
            BottomSheetViewModel::class.java
        )
    }
    private var creditsCast: List<CastDTO?> = listOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.bottom_sheet_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getData().observe(viewLifecycleOwner, {
            creditsCast = render(it)
            with(view) {
                findViewById<ImageView>(R.id.first_image).apply {
                    Picasso.with(context)
                        .load("https://image.tmdb.org/t/p/original/${creditsCast[0]?.profile_path}")
                        .into(this)
                    scaleType = ImageView.ScaleType.CENTER_CROP
                }
                findViewById<ImageView>(R.id.second_image).apply {
                    Picasso.with(context)
                        .load("https://image.tmdb.org/t/p/original/${creditsCast[1]?.profile_path}")
                        .into(this)
                    scaleType = ImageView.ScaleType.CENTER_CROP
                }
                findViewById<ImageView>(R.id.third_image).apply {
                    Picasso.with(context)
                        .load("https://image.tmdb.org/t/p/original/${creditsCast[2]?.profile_path}")
                        .into(this)
                    scaleType = ImageView.ScaleType.CENTER_CROP
                }
                findViewById<TextView>(R.id.text_of_description).apply {
                    text = filmCard?.overview
                }
            }
            Log.d(FilmLoader.TAG, "$creditsCast")
        })
        viewModel.getDataFromServer2(filmCard?.id)
    }

    private fun render(state: State): List<CastDTO?> {
        when (state) {
            is State.SuccessToCredits -> {
                return state.creditCard
            }
            is State.Success -> {}
        }
        return emptyList()
    }
}
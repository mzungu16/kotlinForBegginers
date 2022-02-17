package com.gleb.kotlinforbegginers.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import coil.api.load
import com.gleb.kotlinforbegginers.R
import com.gleb.kotlinforbegginers.model.CreditsCardDTO
import com.gleb.kotlinforbegginers.model.FilmByGenreCardDTO
import com.gleb.kotlinforbegginers.model.FilmCardDTO
import com.gleb.kotlinforbegginers.viewmodel.CreditsBottomSheetViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheet(val filmCard: Any?) : BottomSheetDialogFragment() {
    private val creditsViewModel: CreditsBottomSheetViewModel by lazy {
        ViewModelProvider(this).get(CreditsBottomSheetViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.bottom_sheet_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val emptyCreditList = listOf<CreditsCardDTO?>()
        creditsViewModel.getLiveData().observe(viewLifecycleOwner) {
            with(view) {
                val creditsCast = it
                findViewById<ImageView>(R.id.first_image).apply {
                    load("https://image.tmdb.org/t/p/original/${creditsCast[0]?.profile_path}")
                    setOnClickListener {
                        Toast.makeText(
                            requireContext(),
                            creditsCast[0]?.name,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
                findViewById<ImageView>(R.id.second_image).apply {
                    load("https://image.tmdb.org/t/p/original/${creditsCast[1]?.profile_path}")
                    setOnClickListener {
                        Toast.makeText(
                            requireContext(),
                            creditsCast[1]?.name,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
                findViewById<ImageView>(R.id.third_image).apply {
                    load("https://image.tmdb.org/t/p/original/${creditsCast[2]?.profile_path}")
                    setOnClickListener {
                        Toast.makeText(
                            requireContext(),
                            creditsCast[2]?.name,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
                findViewById<TextView>(R.id.text_of_description).apply {
                    when (filmCard) {
                        is FilmCardDTO? -> text = filmCard?.overview
                        is FilmByGenreCardDTO? -> text = filmCard?.overview
                    }
                }
            }
        }
        creditsViewModel.setLiveDataValue(emptyCreditList)
        when (filmCard) {
            is FilmCardDTO? -> creditsViewModel.getCreditData(filmCard?.id)
            is FilmByGenreCardDTO? ->  creditsViewModel.getCreditData(filmCard?.id)
        }
    }
}
package com.gleb.kotlinforbegginers

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import coil.api.load
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheet(val filmCard: FilmCardDTO?) : BottomSheetDialogFragment() {
    private val viewModel: BottomSheetViewModel by lazy {
        ViewModelProvider(this).get(
            BottomSheetViewModel::class.java
        )
    }
    private var creditsCast: List<CreditsCardDTO?> = listOf()

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
                    load("https://image.tmdb.org/t/p/original/${creditsCast[0]?.profile_path}")
                    setOnClickListener { Toast.makeText(requireContext(),creditsCast[0]?.name,Toast.LENGTH_SHORT).show() }
                }
                findViewById<ImageView>(R.id.second_image).apply {
                    load("https://image.tmdb.org/t/p/original/${creditsCast[1]?.profile_path}")
                    setOnClickListener { Toast.makeText(requireContext(),creditsCast[1]?.name,Toast.LENGTH_SHORT).show()}
                }
                findViewById<ImageView>(R.id.third_image).apply {
                    load("https://image.tmdb.org/t/p/original/${creditsCast[2]?.profile_path}")
                    setOnClickListener { Toast.makeText(requireContext(),creditsCast[2]?.name,Toast.LENGTH_SHORT).show()}
                }
                findViewById<TextView>(R.id.text_of_description).apply {
                    text = filmCard?.overview
                }
            }
            Log.d(InternetLoader.TAG, "$creditsCast")
        })
        viewModel.getDataFromServer2(filmCard?.id)
    }

    private fun render(state: State): List<CreditsCardDTO?> {
        when (state) {
            is State.SuccessToCredits -> {
                return state.creditCard
            }
            is State.Success -> {}
        }
        return emptyList()
    }
}
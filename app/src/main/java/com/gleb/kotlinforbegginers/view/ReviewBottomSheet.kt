package com.gleb.kotlinforbegginers.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gleb.kotlinforbegginers.R
import com.gleb.kotlinforbegginers.model.FilmByGenreCardDTO
import com.gleb.kotlinforbegginers.model.FilmCardDTO
import com.gleb.kotlinforbegginers.viewmodel.ReviewAdapter
import com.gleb.kotlinforbegginers.viewmodel.ReviewViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ReviewBottomSheet(val filmCard: Any?) : BottomSheetDialogFragment() {
    private val reviewViewModel: ReviewViewModel by lazy {
        ViewModelProvider(this).get(ReviewViewModel::class.java)
    }
    private val reviewAdapter = ReviewAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.review_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        reviewViewModel.getFilmReviewLiveData().observe(viewLifecycleOwner) {
            reviewAdapter.setFilmCards(it)
        }
        reviewViewModel.setFilmReviewLiveData()
        when (filmCard) {
            is FilmCardDTO? -> reviewViewModel.getFilmReviewData(filmCard?.id)
            is FilmByGenreCardDTO? -> reviewViewModel.getFilmReviewData(filmCard?.id)
        }
        view.findViewById<RecyclerView>(R.id.review_recycler).apply {
            setHasFixedSize(true)
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = reviewAdapter
        }
    }

}
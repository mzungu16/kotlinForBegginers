package com.gleb.kotlinforbegginers

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.*

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private val filmViewModel: FilmViewModel by lazy { ViewModelProvider(this).get(FilmViewModel::class.java) }

    //    private val genreViewModel: GenreViewModel by lazy { ViewModelProvider(this).get(GenreViewModel::class.java) }
    private val filmAdapter = FilmAdapter()
    private val genresAdapter = GenresAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val simpleFilmList = listOf<FilmCardDTO?>()
        filmViewModel.getLiveData().observe(viewLifecycleOwner, {
            filmAdapter.setFilmCards(it)
        })
        filmViewModel.setLiveDataValueMethod(simpleFilmList)
        filmViewModel.getFilmData()

        /*   genreViewModel.getData2().observe(viewLifecycleOwner,{
               render(it)
           })
           genreViewModel.getGenreDataFromServer()*/

        view.findViewById<RecyclerView>(R.id.recyclerView).apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = filmAdapter
        }
        filmAdapter.listener = object : FilmAdapter.OnItemClick {
            override fun onClick(filmCard: FilmCardDTO?) {
                BottomSheet(filmCard).also { btnDescription ->
                    btnDescription.show(requireActivity().supportFragmentManager, "BottomSheet")
                }
            }
        }
        /*view.findViewById<RecyclerView>(R.id.recyclerViewGenre).apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = myAdapter2
        }*/
    }


}
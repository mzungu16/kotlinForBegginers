package com.gleb.kotlinforbegginers

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.*

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private val viewModel: MainViewModel by lazy { ViewModelProvider(this).get(MainViewModel::class.java) }
//    private val genreViewModel: GenreViewModel by lazy { ViewModelProvider(this).get(GenreViewModel::class.java) }
    private val myAdapter1 = MyAdapter()
    private val myAdapter2 = GenresAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getData().observe(viewLifecycleOwner, {
            render(it)
        })
        viewModel.getFilmDataFromServer()

     /*   genreViewModel.getData2().observe(viewLifecycleOwner,{
            render(it)
        })
        genreViewModel.getGenreDataFromServer()*/

        view.findViewById<RecyclerView>(R.id.recyclerView).apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = myAdapter1
        }
        myAdapter1.listener = object : MyAdapter.OnItemClick {
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

    private fun render(state: State) = when (state) {
        is State.Success -> {
            myAdapter1.setFilmCards(state.filmCards)
        }
        /*is State.SuccessToGenres -> {
            myAdapter2.setGenreCards(state.genreCard)
        }*/
        else -> {}
    }
}
package com.gleb.kotlinforbegginers.view

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.opengl.Visibility
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.*
import com.gleb.kotlinforbegginers.viewmodel.FilmAdapter
import com.gleb.kotlinforbegginers.R
import com.gleb.kotlinforbegginers.model.FilmByGenreCardDTO
import com.gleb.kotlinforbegginers.model.FilmCardDTO
import com.gleb.kotlinforbegginers.model.GenreCardDTO
import com.gleb.kotlinforbegginers.viewmodel.FilmByGenreAdapter
import com.gleb.kotlinforbegginers.viewmodel.FilmViewModel
import com.gleb.kotlinforbegginers.viewmodel.GenreAdapter

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private val filmViewModel: FilmViewModel by lazy { ViewModelProvider(this).get(FilmViewModel::class.java) }
    private val filmAdapter = FilmAdapter()
    private val genreAdapter = GenreAdapter()
    private val filmByGenreAdapter = FilmByGenreAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val emptyFilmList = listOf<FilmCardDTO?>()
        val emptyGenreList = listOf<GenreCardDTO?>()
        val emptyFilmByGenreList = listOf<FilmByGenreCardDTO?>()
        filmViewModel.getFilmLiveData().observe(viewLifecycleOwner, {
            filmAdapter.setFilmCards(it)
        })
        filmViewModel.setFilmLiveDataValueMethod(emptyFilmList)
        filmViewModel.getFilmData()

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

        filmViewModel.getGenreLiveData().observe(viewLifecycleOwner, {
            genreAdapter.setGenreCards(it)
        })
        filmViewModel.setGenreLiveDataValueMethod(emptyGenreList)
        filmViewModel.getGenreData()
        view.findViewById<RecyclerView>(R.id.recyclerView_genre).apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = genreAdapter
        }
        genreAdapter.listener = object : GenreAdapter.OnItemClick {
            override fun onClick(genreCard: GenreCardDTO?) {

                filmViewModel.getFilmByGenreLiveData().observe(viewLifecycleOwner, {
                    filmByGenreAdapter.setFilmByGenreCard(it)
                })
                filmViewModel.setFilmByGenreLiveDataValueMethod(emptyFilmByGenreList)
                filmViewModel.getFilmByGenreData(genreCard?.id)

                view.findViewById<LinearLayout>(R.id.linear).visibility = View.VISIBLE

                view.findViewById<RecyclerView>(R.id.recyclerView_film_by_genre).apply {
                    layoutManager =
                        LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                    adapter = filmByGenreAdapter
                }
            }
        }
        view.findViewById<TextView>(R.id.hideBtn).setOnClickListener(View.OnClickListener {
            view.findViewById<LinearLayout>(R.id.linear).visibility = View.GONE
        })
    }

    private fun checkConnection(): Boolean {
        val connectivityManager: ConnectivityManager =
            context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            return when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                else -> false
            }
        }
        return false
    }
}
package com.gleb.kotlinforbegginers.view

import kotlinx.android.synthetic.main.main_fragment.view.*
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.*
import com.gleb.kotlinforbegginers.R
import com.gleb.kotlinforbegginers.model.FilmByGenreCardDTO
import com.gleb.kotlinforbegginers.model.FilmCardDTO
import com.gleb.kotlinforbegginers.model.GenreCardDTO
import com.gleb.kotlinforbegginers.viewmodel.*

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private val filmViewModel: FilmViewModel by lazy { ViewModelProvider(this).get(FilmViewModel::class.java) }
    private val filmAdapter = FilmAdapter()
    private val genreAdapter = GenreAdapter()
    private val filmByGenreAdapter = FilmByGenreAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.main_fragment, container, false)
        (activity as AppCompatActivity).setSupportActionBar(view.app_bar)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        filmMethod(view)
        genreMethod(view)

    }
    /*view.layout_with_filmByGenres.visibility = View.GONE
    view.layout_with_popularFilms.visibility = View.VISIBLE*/

    private fun genreMethod(view: View) {
        with(filmViewModel) {
            getGenreLiveData().observe(viewLifecycleOwner) {
                genreAdapter.setGenreCards(it)
            }
            setGenreLiveDataValueMethod()
            getGenreData()
        }
        view.genre_recycler_view.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = genreAdapter
        }

        genreAdapter.listener = object : GenreAdapter.OnItemClick {
            override fun onClick(genreCard: GenreCardDTO?) {
                view.layout_with_popularFilms.visibility = View.GONE

                with(filmViewModel) {
                    getFilmByGenreLiveData().observe(viewLifecycleOwner) {
                        filmByGenreAdapter.setFilmByGenreCard(it)
                    }
                    setFilmByGenreLiveDataValueMethod()
                    getFilmByGenreData(genreCard?.id)
                }
                view.film_by_genre_recycler_view.apply {
                    layoutManager =
                        GridLayoutManager(requireContext(), 2, RecyclerView.VERTICAL, false)
                    adapter = filmByGenreAdapter
                }
                filmByGenreAdapter.listener = object : FilmByGenreAdapter.OnItemClick {
                    override fun onClick(filmByGenre: FilmByGenreCardDTO?) {
                        BottomSheet(filmByGenre).also { btnDescription ->
                            btnDescription.show(
                                requireActivity().supportFragmentManager,
                                "BottomSheet1"
                            )
                        }
                    }
                }
                view.layout_with_filmByGenres.visibility = View.VISIBLE
            }
        }
    }

    private fun filmMethod(view: View) {
        with(filmViewModel) {
            getFilmLiveData().observe(viewLifecycleOwner) {
                filmAdapter.setFilmCards(it)
            }
            setFilmLiveDataValueMethod()
            getFilmData()
        }
        view.film_recycler_view.apply {
            setHasFixedSize(true)
            layoutManager =
                GridLayoutManager(requireContext(), 2, RecyclerView.VERTICAL, false)
            adapter = filmAdapter
        }
        filmAdapter.listener = object : FilmAdapter.OnItemClick {
            override fun onImageClick(filmCard: FilmCardDTO?) {
                BottomSheet(filmCard).also { btnDescription ->
                    btnDescription.show(requireActivity().supportFragmentManager, "BottomSheet")
                }
            }

            override fun onReviewBtnClick(filmCard: FilmCardDTO?) {
                /* ReviewBottomSheet(filmCard).also { review_btn ->
                     review_btn.show(requireActivity().supportFragmentManager, "ReviewBottomSheer")
                 }*/
                requireActivity().supportFragmentManager.beginTransaction()
                    .add(R.id.container, ReviewBottomSheet(filmCard)).addToBackStack(null).commit()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.shr_toolbar_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }
}
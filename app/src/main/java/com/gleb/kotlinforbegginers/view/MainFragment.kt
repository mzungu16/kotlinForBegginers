package com.gleb.kotlinforbegginers.view

import kotlinx.android.synthetic.main.main_fragment.view.*
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.*
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.CompoundButton
import androidx.fragment.app.Fragment
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.*
import com.gleb.kotlinforbegginers.R
import com.gleb.kotlinforbegginers.model.FilmByGenreCardDTO
import com.gleb.kotlinforbegginers.model.FilmCardDTO
import com.gleb.kotlinforbegginers.model.GenreCardDTO
import com.gleb.kotlinforbegginers.viewmodel.*
import com.google.android.material.switchmaterial.SwitchMaterial

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
        view.app_bar.setNavigationOnClickListener(
            NavigationIconClickListener(
                requireActivity(),
                view.product_grid,
                AccelerateDecelerateInterpolator(),
                ContextCompat.getDrawable(requireContext(), R.drawable.shr_menu),
                ContextCompat.getDrawable(requireContext(), R.drawable.shr_close_menu)
            )
        )
        view.product_grid.background =
            context?.getDrawable(R.drawable.shr_product_grid_background_shape)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val emptyFilmList = listOf<FilmCardDTO?>()
        val emptyGenreList = listOf<GenreCardDTO?>()
        val emptyFilmByGenreList = listOf<FilmByGenreCardDTO?>()
        filmMethod(emptyFilmList, view)
        genreMethod(emptyGenreList, view, emptyFilmByGenreList)
        view.hideBtn
            .setOnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked) {
                    view.layout_with_filmByGenres.visibility = View.GONE
                    view.layout_with_popularFilms.visibility = View.VISIBLE
                    buttonView?.isChecked = false
                }
            }
    }

    private fun genreMethod(
        emptyGenreList: List<GenreCardDTO?>,
        view: View,
        emptyFilmByGenreList: List<FilmByGenreCardDTO?>
    ) {
        with(filmViewModel) {
            getGenreLiveData().observe(viewLifecycleOwner) {
                genreAdapter.setGenreCards(it)
            }
            setGenreLiveDataValueMethod(emptyGenreList)
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
                    setFilmByGenreLiveDataValueMethod(emptyFilmByGenreList)
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

    private fun filmMethod(
        emptyFilmList: List<FilmCardDTO?>,
        view: View
    ) {
        with(filmViewModel) {
            getFilmLiveData().observe(viewLifecycleOwner) {
                filmAdapter.setFilmCards(it)
            }
            setFilmLiveDataValueMethod(emptyFilmList)
            getFilmData()
        }
        view.film_recycler_view.apply {
            setHasFixedSize(true)
            layoutManager =
                GridLayoutManager(requireContext(), 2, RecyclerView.VERTICAL, false)
            adapter = filmAdapter
        }
        filmAdapter.listener = object : FilmAdapter.OnItemClick {
            override fun onClick(filmCard: FilmCardDTO?) {
                BottomSheet(filmCard).also { btnDescription ->
                    btnDescription.show(requireActivity().supportFragmentManager, "BottomSheet")
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.shr_toolbar_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }
}
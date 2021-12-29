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
    private val myAdapter = MyAdapter()
    private val snapHelper: SnapHelper = PagerSnapHelper()

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
        viewModel.getDataFromServer()

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView).apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = myAdapter
        }
        snapHelper.attachToRecyclerView(recyclerView)
        myAdapter.listener = object : MyAdapter.OnItemClick {
            override fun onClick(filmCard: FactDTO?) {
                BottomSheet(filmCard).also { btnDescription ->
                    btnDescription.show(requireActivity().supportFragmentManager, "BottomSheet")
                }
            }
        }
    }

    private fun render(state: State) = when (state) {
        is State.Success -> {
            myAdapter.setFilmCards(state.filmCards)
            Log.d(FilmLoader.TAG, "State.filmCards ${state.filmCards}")
        }
        else -> {}
    }
}
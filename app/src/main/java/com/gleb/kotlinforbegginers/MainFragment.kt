package com.gleb.kotlinforbegginers

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.commit
import androidx.recyclerview.widget.*
import com.google.android.material.bottomsheet.BottomSheetDialog

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    private val adapter = MyAdapter()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.getData().observe(viewLifecycleOwner, {
            render(it)
        })
        viewModel.getDataFromLocalSource()

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        val snapHelper: SnapHelper = PagerSnapHelper()
        recyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        snapHelper.attachToRecyclerView(recyclerView)
        recyclerView.adapter = adapter
        adapter.listener = object : MyAdapter.OnItemClick {
            override fun onClick(filmCard: FilmCard) {
                val btnSheet = BottomSheet(filmCard)
                btnSheet.show(requireActivity().supportFragmentManager, "BottomSheet")
            }

        }
    }

    private fun render(state: State) {
        when (state) {
            is State.Success -> {
                val cards = state.filmCards
                adapter.setFilmCards(cards)
            }
        }
    }

}
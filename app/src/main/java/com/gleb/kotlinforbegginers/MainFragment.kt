package com.gleb.kotlinforbegginers

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.gleb.kotlinforbegginers.databinding.MainFragmentBinding

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var listOfCards: MutableList<Card>
    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        val listOfImages: List<Int> =
            listOf(R.drawable.harry, R.drawable.lord, R.drawable.pirates)

        val listOfTitles: List<String> =
            listOf("Harry Potter", "Lord Of the Rings", "Pirates")
        listOfCards = mutableListOf()
        for (i in 0 until 3) {
            listOfCards.add(Card(listOfTitles[i], listOfImages[i]))
        }

        val snapHelper = PagerSnapHelper()


        binding.recyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        snapHelper.attachToRecyclerView(binding.recyclerView)

        binding.recyclerView.adapter = MyAdapter(listOfCards)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
package com.gleb.kotlinforbegginers

import android.graphics.Typeface
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.gleb.kotlinforbegginers.databinding.MainFragmentBinding
import com.google.android.material.snackbar.Snackbar

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
        viewModel.getData().observe(viewLifecycleOwner, { state ->
            render(state)
        })
        viewModel.getCard()
    }

    fun render(state: State) {
        when (state) {
            is State.Success -> {
                binding.loadingContainer.visibility = View.GONE
                val card = state.card as Card
                binding.beckImageId.setImageResource(card.image)
                binding.beckImageId.scaleType = ImageView.ScaleType.CENTER_CROP
                binding.frontImageId.setImageResource(card.image)
                binding.frontImageId.scaleType = ImageView.ScaleType.CENTER_CROP
                binding.titleOfFilmId.text = card.title
                binding.titleOfFilmId.textSize = 30F
                binding.titleOfFilmId.typeface = Typeface.DEFAULT_BOLD
            }
            is State.Error -> {
                binding.loadingContainer.visibility = View.VISIBLE
                Snackbar.make(
                    binding.root,
                    state.error.message.toString(),
                    Snackbar.LENGTH_INDEFINITE
                ).setAction("Try again") {
                    viewModel.getCard()
                }.show()
            }
            is State.Loading -> {
                binding.loadingContainer.visibility = View.VISIBLE
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
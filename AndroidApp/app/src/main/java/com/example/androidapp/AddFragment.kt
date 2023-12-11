package com.example.androidapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.androidapp.data.Series
import com.example.androidapp.databinding.FragmentAddBinding

class AddFragment : Fragment() {
    private val viewModel: SeriesViewModel by activityViewModels {
        SeriesViewModelFactory(
            (activity?.application as SeriesApplication).repository
        )
    }

    private val navigationArgs: AddFragmentArgs by navArgs()

    lateinit var series: Series

    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun bind(series: Series) {
        binding.apply {
            seriesTitle.setText(series.title, TextView.BufferType.SPANNABLE)
        }
    }

    private fun addNewItem() {
        if (isEntryValid()) {
            viewModel.addNewItem(
                this.binding.seriesTitle.text.toString(),
                this.binding.seriesSeasons.text.toString().toInt(),
                this.binding.seriesEpisodes.text.toString().toInt()
            )
            val action = AddFragmentDirections.actionAddFragmentToListFragment()
            findNavController().navigate(action)
        }
    }

    private fun isEntryValid(): Boolean {
        return viewModel.isEntryValid(
            binding.seriesTitle.text.toString(),
            binding.seriesSeasons.text.toString(),
            binding.seriesEpisodes.text.toString(),
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.saveAction.setOnClickListener { addNewItem() }
    }
}
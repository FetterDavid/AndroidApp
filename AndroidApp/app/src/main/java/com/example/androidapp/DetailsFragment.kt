package com.example.androidapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.example.androidapp.databinding.FragmentDetailsBinding
import kotlinx.coroutines.launch


class DetailsFragment : Fragment() {
    private val viewModel: SeriesViewModel by activityViewModels {
        SeriesViewModelFactory(
            (activity?.application as SeriesApplication).repository
        )
    }

    val args: DetailsFragmentArgs by navArgs()

    private lateinit var binding: FragmentDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_details,container,false)
        val id = args.id

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getItem(id).collect { series ->
                binding.series = series
            }
        }

        return binding.root
    }
}
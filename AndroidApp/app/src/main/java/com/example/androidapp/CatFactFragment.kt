package com.example.androidapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.androidapp.databinding.FragmentCatFactBinding

class CatFactFragment : Fragment() {
    private val viewModel: SeriesViewModel by activityViewModels {
        SeriesViewModelFactory(
            (activity?.application as SeriesApplication).repository
        )
    }

    private var _binding: FragmentCatFactBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCatFactBinding.inflate(inflater, container, false)

        viewModel.getCatFact()
        viewModel.myResponse.observe(viewLifecycleOwner, Observer { response ->
            if(response.isSuccessful) {
                binding.catFact.text=response.body()?.fact
            }
            else{
                binding.catFact.text= response.code().toString()
            }
        })

        return binding.root
    }
}
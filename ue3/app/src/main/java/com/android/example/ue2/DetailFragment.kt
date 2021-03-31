package com.android.example.ue2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.android.example.ue2.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentDetailBinding>(inflater, R.layout.fragment_detail, container, false)
        val args = DetailFragmentArgs.fromBundle(requireArguments())
        val m = MovieList().movieList[args.whichMovie]
        binding.movie = m
        binding.moviePoster.setImageResource(m.poster)
        return binding.root
    }

}
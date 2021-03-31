package com.android.example.ue2

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.android.example.ue2.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentHomeBinding>(
            inflater,
            R.layout.fragment_home,
            container,
            false
        )
        binding.cardMovie1Button.setOnClickListener { view: View ->
            view.findNavController()
                .navigate(HomeFragmentDirections.actionHomeFragmentToDetailFragment(0))
        }
        binding.cardMovie2Button.setOnClickListener { view: View ->
            view.findNavController()
                .navigate(HomeFragmentDirections.actionHomeFragmentToDetailFragment(1))
        }
        binding.cardMovie3Button.setOnClickListener { view: View ->
            view.findNavController()
                .navigate(HomeFragmentDirections.actionHomeFragmentToDetailFragment(2))
        }
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.options_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, requireView().findNavController())
                || super.onOptionsItemSelected(item)
    }

}
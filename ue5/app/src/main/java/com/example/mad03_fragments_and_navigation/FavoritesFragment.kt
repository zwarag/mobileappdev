package com.example.mad03_fragments_and_navigation

import android.app.AlertDialog
import android.os.Bundle
import android.text.InputType
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mad03_fragments_and_navigation.adapters.FavoritesListAdapter
import com.example.mad03_fragments_and_navigation.database.AppDatabase
import com.example.mad03_fragments_and_navigation.databinding.FragmentFavoritesBinding
import com.example.mad03_fragments_and_navigation.models.Movie
import com.example.mad03_fragments_and_navigation.repositories.MovieRepository
import com.example.mad03_fragments_and_navigation.viewmodels.MovieFavoritesViewModel
import com.example.mad03_fragments_and_navigation.viewmodels.MovieFavoritesViewModelFactory


class FavoritesFragment : Fragment() {
    private lateinit var binding: FragmentFavoritesBinding
    private lateinit var favouriteMovieViewModel: MovieFavoritesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_favorites, container, false)

        val adapter = FavoritesListAdapter(
            dataSet = listOf(),     // start with empty list
            onDeleteClicked = {movieId -> onDeleteMovieClicked(movieId)},   // pass functions to adapter
            onEditClicked = {movie -> onEditMovieClicked(movie)},           // pass functions to adapter
        )

        with(binding){
            recyclerView.adapter = adapter
        }

        val applicationContext = requireNotNull(this.activity).application
        val dataSource = AppDatabase.getInstance(applicationContext).movieDao

        val viewModelFactory = MovieFavoritesViewModelFactory(MovieRepository.getInstance(dataSource))
        favouriteMovieViewModel = ViewModelProvider(this, viewModelFactory).get(MovieFavoritesViewModel::class.java)

        favouriteMovieViewModel.getAll.observe(viewLifecycleOwner, {
            adapter.updateDataSet(it)
        })

        binding.clearBtn.setOnClickListener {
            favouriteMovieViewModel.clearTable()
        }

        return binding.root
    }

    // This is called when recyclerview item edit button is clicked
    private fun onEditMovieClicked(movie: Movie){
        val builder: AlertDialog.Builder =
            AlertDialog.Builder(requireNotNull(this.activity))
        builder.setTitle("Edit Note")

        val layout = LinearLayout(requireNotNull(this.activity))
        layout.orientation = LinearLayout.VERTICAL
        layout.gravity = Gravity.CENTER_HORIZONTAL
        layout.setPadding(48, 0, 48, 0)

        val inputField = EditText(requireNotNull(this.activity))
        inputField.inputType = InputType.TYPE_CLASS_TEXT
        inputField.setText(movie.note)
        inputField.setSelection(inputField.text.toString().length)

        layout.addView(inputField)
        builder.setView(layout)

        builder.setPositiveButton("Update") { _, _ ->
            movie.note = inputField.text.toString()
            favouriteMovieViewModel.update(movie)
        }

        builder.setNegativeButton("Cancel") { dialog, _ ->
            dialog.cancel()
        }

        builder.show()
    }

    // This is called when recyclerview item remove button is clicked
    private fun onDeleteMovieClicked(movieId: Long){
        favouriteMovieViewModel.delete(movieId)
    }
}
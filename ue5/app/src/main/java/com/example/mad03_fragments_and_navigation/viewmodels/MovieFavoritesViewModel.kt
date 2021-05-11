package com.example.mad03_fragments_and_navigation.viewmodels

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mad03_fragments_and_navigation.models.Movie
import com.example.mad03_fragments_and_navigation.repositories.MovieRepository
import kotlinx.coroutines.launch

class MovieFavoritesViewModel(private val repository: MovieRepository) : ViewModel() {

    val getAll: LiveData<List<Movie>> = repository.getAll()

    fun create(movie: Movie, context: Context) {
        viewModelScope.launch {
            val id = repository.create(movie)
            if(id < 0) {
                Toast.makeText(
                    context,
                    "Movie already a favourite!",
                    Toast.LENGTH_SHORT
                ).show()
                return@launch
            }

            Toast.makeText(
                context,
                "Added movie to favourites!",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    fun update(movie: Movie) {
        viewModelScope.launch {
            repository.update(movie)
        }
    }

    fun delete(movieId: Long) {
        viewModelScope.launch {
            repository.delete(movieId)
        }
    }

    fun clearTable() {
        viewModelScope.launch {
            repository.clearTable()
        }
    }

 }
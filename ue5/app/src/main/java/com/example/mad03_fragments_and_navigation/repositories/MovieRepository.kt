package com.example.mad03_fragments_and_navigation.repositories

import android.database.sqlite.SQLiteException
import android.util.Log
import androidx.lifecycle.LiveData
import com.example.mad03_fragments_and_navigation.database.MovieDao
import com.example.mad03_fragments_and_navigation.models.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieRepository(private val movieDao: MovieDao) {


    companion object {
        // For Singleton instantiation
        @Volatile
        private var instance: MovieRepository? = null

        fun getInstance(dao: MovieDao) =
            instance ?: synchronized(this) {
                instance ?: MovieRepository(dao).also { instance = it }
            }
    }

    suspend fun create(movie: Movie): Long =
        withContext(Dispatchers.IO) {
            try {
                return@withContext movieDao.create(movie)
            } catch (e: SQLiteException) {
                Log.e("MovieREpository", "Error while inserting movie into database.")
                return@withContext -1L
            }
        }


    suspend fun update(movie: Movie) =
        withContext(Dispatchers.IO) {
            movieDao.update(movie)
        }


    suspend fun delete(movieId: Long) =
        withContext(Dispatchers.IO) {
            movieDao.delete(movieId)
        }


    suspend fun clearTable() =
        withContext(Dispatchers.IO) {
            movieDao.clearTable()
        }


    fun getAll() = movieDao.getAll()


}
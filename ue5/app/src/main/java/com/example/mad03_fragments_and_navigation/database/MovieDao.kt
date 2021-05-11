package com.example.mad03_fragments_and_navigation.database

import android.database.sqlite.SQLiteException
import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.mad03_fragments_and_navigation.models.Movie

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    @Throws(SQLiteException::class)
    fun create(movie: Movie): Long

    @Update
    fun update(movie: Movie)

    @Query("DELETE FROM favorite_movies_table WHERE id = :movieId")
    fun delete(movieId: Long)

    @Query("DELETE FROM favorite_movies_table")
    fun clearTable()

    @Query("Select * from favorite_movies_table")
    fun getAll(): LiveData<List<Movie>>
}
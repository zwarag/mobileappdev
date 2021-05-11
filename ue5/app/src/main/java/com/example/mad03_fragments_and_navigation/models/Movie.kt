package com.example.mad03_fragments_and_navigation.models

import androidx.room.*
import com.example.mad03_fragments_and_navigation.R

@Entity(tableName = "favorite_movies_table", indices = [Index(value = ["title"], unique = true)])
data class Movie(
    @ColumnInfo
    var title: String = "",
    @Ignore
    var description: String = ""
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null

    @Ignore
    var rating: Float = 0.0F
        set(value) {
            if (value in 0.0..5.0) field = value
            else throw IllegalArgumentException("Rating value must be between 0 and 5")
        }

    @ColumnInfo
    var note: String = ""

    @Ignore
    var imageId: Int = R.drawable.no_preview_3

    @Ignore
    var actors: MutableList<String> = mutableListOf()

    @Ignore
    var creators: MutableList<String> = mutableListOf()

    @Ignore
    var genres: List<String>? = null
}

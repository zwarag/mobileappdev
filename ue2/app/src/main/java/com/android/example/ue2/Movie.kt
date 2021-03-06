package com.android.example.ue2

import android.widget.RatingBar
import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("android:text")
fun setToText(textView: TextView, list: List<String>) {
    textView.text = list.joinToString(", ")
}

@BindingAdapter("android:rating")
fun setRating(androidRating: RatingBar, rate: Float) {
    if (rate in 0.0..5.0) {
        androidRating.rating = rate
    }
}

data class Movie(
    val title: String = "The Queen's Gambit",
    val description: String = "The Queen\'s Gambit follows the life of an orphan chess prodigy, Elizabeth Harmon, during her quest to become the world's greatest chess player while struggling with emotional problems and drug and alcohol dependency. The title of the series refers to a chess opening of the same name. The story begins in the mid-1950s and proceeds into the 1960s.[4] The story begins in Lexington, Kentucky, where a nine-year-old Beth, having lost her mother in a car crash, is taken to an orphanage where she is taught chess by the building's custodian, Mr. Shaibel. As was common during the 1950s, the orphanage dispenses daily tranquilizer pills to the girls,[5][6] which turns into an addiction for Beth. She quickly becomes a strong chess player due to her visualization skills, which are enhanced by the tranquilizers. A few years later, Beth is adopted by Alma Wheatley and her husband from Lexington. As she adjusts to her new home, Beth enters a chess tournament and wins despite having no prior experience in competitive chess. She develops friendships with several people, including former Kentucky State Champion Harry Beltik, United States National Champion Benny Watts, and journalist and fellow player D.L. Townes. As Beth rises to the top of the chess world and reaps the financial benefits of her success, her drug and alcohol dependency becomes worse.",
    val rating: Float = 4.5f,
    val genres: List<String> = listOf("Drama", "Sport"),
    val creators: String = "Scott Frank, Alan Scott",
    val actors: String = "Anya Taylor-Joy, Chloe Pirrie"
)

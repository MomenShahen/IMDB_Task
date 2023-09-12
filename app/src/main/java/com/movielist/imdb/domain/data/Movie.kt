package com.movielist.imdb.domain.data

import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Keep
@Entity(
    tableName = "movie"
)
data class Movie(
    var original_title: String? = null,
    var overview: String? = null,
    var popularity: Double? = null,
    var poster_path: String? = null,
    var release_date: String? = null,
    var title: String? = null,
    var vote_average: Double? = null,
    var vote_count: Int? = null
) : Serializable {
    @PrimaryKey
    var id: Int = 0
}
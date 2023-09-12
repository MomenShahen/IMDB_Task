package com.movielist.imdb.domain.data

import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

//@Keep
//@Entity(
//    tableName = "movie"
//)
data class Movie(
    var originalTitle: String? = null,
    var overview: String? = null,
    var popularity: Double? = null,
    var posterPath: String? = null,
    var releaseDate: String? = null,
    var title: String? = null,
    var voteAverage: Double? = null,
    var voteCount: Int? = null
) : Serializable {
//    @PrimaryKey
    var id: Int = 0
}
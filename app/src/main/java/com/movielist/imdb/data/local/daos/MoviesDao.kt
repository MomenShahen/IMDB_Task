package com.movielist.imdb.data.local.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.movielist.imdb.domain.data.Movie
import kotlinx.coroutines.flow.Flow

@Dao
interface MoviesDao {
    @Query("SELECT * FROM movie")
    fun getAllMovies(): Flow<List<Movie?>?>

    @Query("DELETE FROM movie")
    fun deleteAll()

    @Insert
    fun addAll(data: List<Movie>)

    @Query("SELECT * FROM movie WHERE id = :id")
    suspend fun getMovie(id: Int): Movie?

    @Transaction
    fun nukeTableAndAdd(data: List<Movie>) {
        deleteAll()
        addAll(data)
    }
}

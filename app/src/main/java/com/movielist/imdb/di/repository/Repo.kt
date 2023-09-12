package com.movielist.imdb.di.repository

import com.movielist.imdb.data.repo.MoviesRepoImpl
import com.movielist.imdb.domain.repository.MoviesRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class Repo {
    @Singleton
    @Binds
    abstract fun bindsGetMoviesRepo(repoImpl: MoviesRepoImpl): MoviesRepo
}
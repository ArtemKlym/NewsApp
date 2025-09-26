package com.artemklymenko.newsapp.di

import com.artemklymenko.data.repository.NewsRepositoryImpl
import com.artemklymenko.domain.repository.NewsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class MainModule {

    @Binds
    abstract fun provideNewsRepository(impl: NewsRepositoryImpl): NewsRepository
}
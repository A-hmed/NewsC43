package com.route.newsc43.data.di

import com.route.newsc43.data.repositories.news_repository.NewsRepositoryImpl
import com.route.newsc43.data.repositories.news_repository.data_sources.news_local_data_source.NewsLocalDataSource
import com.route.newsc43.data.repositories.news_repository.data_sources.news_local_data_source.NewsLocalDataSourceImpl
import com.route.newsc43.data.repositories.news_repository.data_sources.news_remote_data_source.NewsRemoteDataSource
import com.route.newsc43.data.repositories.news_repository.data_sources.news_remote_data_source.NewsRemoteDataSourceImpl
import com.route.newsc43.domain.repository.NewsRepository
import com.route.newsc43.utils.Connectivity
import com.route.newsc43.utils.ConnectivityImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    abstract fun bindNewsRepo(arg: NewsRepositoryImpl): NewsRepository

    @Binds
    abstract fun bindNewsLocalDataSource(arg: NewsLocalDataSourceImpl): NewsLocalDataSource

    @Binds
    abstract fun bindNewsRemoteDataSource(arg: NewsRemoteDataSourceImpl): NewsRemoteDataSource

    @Binds
    abstract fun bindConnectivity(arg: ConnectivityImpl): Connectivity
}
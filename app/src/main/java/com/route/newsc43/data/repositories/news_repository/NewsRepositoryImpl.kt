package com.route.newsc43.data.repositories.news_repository

import android.util.Log
import com.route.newsc43.data.mapper.SourcesMapper
import com.route.newsc43.data.repositories.news_repository.data_sources.news_local_data_source.NewsLocalDataSource
import com.route.newsc43.data.repositories.news_repository.data_sources.news_remote_data_source.NewsRemoteDataSource
import com.route.newsc43.domain.model.Source
import com.route.newsc43.domain.repository.NewsRepository
import com.route.newsc43.utils.Connectivity
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    var localDataSource: NewsLocalDataSource,
    var remoteDataSource: NewsRemoteDataSource,
    var connectivity: Connectivity,
    var sourcesMapper: SourcesMapper
) : NewsRepository {


    suspend override fun getSources(category: String): List<Source> {

        val isConnected = connectivity.isOnline()
        Log.e("NewsRepository", "isConnected = ${isConnected}")
        if (isConnected) {
            val sourcesResponse = remoteDataSource.getSources(category)
            localDataSource.saveSources(category, sourcesResponse.sources!!)
            return sourcesMapper.toSources(sourcesResponse.sources)
        } else {
            var sources = localDataSource.getSources(category)
            return sourcesMapper.toSources(sources)
        }
    }
}
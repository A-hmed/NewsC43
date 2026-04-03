package com.route.newsc43.data.repositories.news_repository.data_sources.news_remote_data_source

import com.route.newsc43.data.api.ApiManager
import com.route.newsc43.data.api.model.SourcesResponse

interface NewsRemoteDataSource {
    suspend fun getSources(category: String): SourcesResponse
}
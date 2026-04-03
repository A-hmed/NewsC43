package com.route.newsc43.data.repositories.news_repository.data_sources.news_remote_data_source

import com.route.newsc43.data.api.WebServices
import com.route.newsc43.data.api.model.SourcesResponse
import javax.inject.Inject

class NewsRemoteDataSourceImpl @Inject constructor(var services: WebServices) :
    NewsRemoteDataSource {
    suspend override fun getSources(category: String): SourcesResponse {
        return services.getSources(category = category)
    }
}
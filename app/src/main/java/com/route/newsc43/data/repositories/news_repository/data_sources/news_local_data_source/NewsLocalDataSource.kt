package com.route.newsc43.data.repositories.news_repository.data_sources.news_local_data_source

import com.route.newsc43.data.api.model.SourceDM

interface NewsLocalDataSource {
    suspend fun getSources(category: String): List<SourceDM>
    suspend fun getAllSources(): List<SourceDM>
    suspend fun saveSources(category: String, sources: List<SourceDM>)

}
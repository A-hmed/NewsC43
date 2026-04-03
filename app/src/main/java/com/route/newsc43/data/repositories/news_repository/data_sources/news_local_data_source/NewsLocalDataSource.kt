package com.route.newsc43.data.repositories.news_repository.data_sources.news_local_data_source

import com.route.newsc43.data.api.model.SourceDM
import com.route.newsc43.data.api.model.SourcesResponse
import com.route.newsc43.data.database.MyDatabase
import java.util.Locale
import java.util.Locale.getDefault

class NewsLocalDataSource {
    var dao = MyDatabase.getDataBase().getSourcesDao()
    suspend fun getSources(category: String): List<SourceDM> {
        return dao.getSources(category.lowercase(getDefault()))
    }
    suspend fun getAllSources(): List<SourceDM> {
        return dao.getAllSources()
    }
    suspend fun saveSources(category: String, sources: List<SourceDM>) {
        dao.saveSources(sources)
    }

}
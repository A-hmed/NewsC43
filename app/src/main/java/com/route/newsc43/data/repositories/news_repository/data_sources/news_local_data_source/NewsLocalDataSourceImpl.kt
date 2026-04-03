package com.route.newsc43.data.repositories.news_repository.data_sources.news_local_data_source

import com.route.newsc43.data.api.model.SourceDM
import com.route.newsc43.data.database.dao.SourcesDao
import java.util.Locale.getDefault
import javax.inject.Inject

class NewsLocalDataSourceImpl @Inject constructor(var dao: SourcesDao) : NewsLocalDataSource {
    suspend override fun getSources(category: String): List<SourceDM> {
        return dao.getSources(category.lowercase(getDefault()))
    }

    suspend override fun getAllSources(): List<SourceDM> {
        return dao.getAllSources()
    }

    suspend override fun saveSources(category: String, sources: List<SourceDM>) {
        dao.saveSources(sources)
    }

}
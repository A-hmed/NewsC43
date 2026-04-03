package com.route.newsc43.domain.repository
import com.route.newsc43.domain.model.Source
interface NewsRepository {

    suspend fun getSources(category: String): List<Source>
}
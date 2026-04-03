package com.route.newsc43.domain.repository

import com.route.newsc43.data.api.model.SourceDM

interface NewsRepository {

    suspend fun getSources(category: String): List<SourceDM>
}
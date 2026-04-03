package com.route.newsc43.domain.usecase

import com.route.newsc43.domain.model.Source
import com.route.newsc43.domain.repository.NewsRepository
import javax.inject.Inject

class GetSourcesUseCase @Inject constructor(var repository: NewsRepository) {

    suspend fun execute(category: String): List<Source> = repository.getSources(category)
}
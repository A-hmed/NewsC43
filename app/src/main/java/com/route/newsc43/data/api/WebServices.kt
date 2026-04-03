package com.route.newsc43.data.api

import com.route.newsc43.data.api.model.ArticlesResponse
import com.route.newsc43.data.api.model.SourcesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WebServices {
    @GET("/v2/top-headlines/sources")
    suspend fun getSources(
        @Query("apiKey") apiKey: String = ApiManager.API_KEY,
        @Query("category") category: String
    ): SourcesResponse

    @GET("/v2/everything")
    suspend fun getArticles(
        @Query("apiKey") apiKey: String = ApiManager.API_KEY,
        @Query("sources") source: String
    ): ArticlesResponse
}
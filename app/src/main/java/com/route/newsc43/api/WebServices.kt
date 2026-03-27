package com.route.newsc43.api

import com.route.newsc43.api.model.ArticlesResponse
import com.route.newsc43.api.model.SourcesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WebServices {
    @GET("/v2/top-headlines/sources")
    fun getSources(
        @Query("apiKey") apiKey: String = ApiManager.API_KEY,
        @Query("category") category: String
    ): Call<SourcesResponse>

    @GET("/v2/everything")
    fun getArticles(
        @Query("apiKey") apiKey: String = ApiManager.API_KEY,
        @Query("sources") source: String
    ): Call<ArticlesResponse>
}
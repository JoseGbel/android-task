package com.example.degree53androidtest.network

import com.example.degree53androidtest.model.SearchResponseObject
import retrofit2.http.GET
import retrofit2.http.Query

interface GitHubService {
    @GET("/search/repositories")
    suspend fun search(@Query("q") name: String) : SearchResponseObject
}

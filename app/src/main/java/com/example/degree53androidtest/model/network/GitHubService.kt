package com.example.degree53androidtest.model.network

import com.example.degree53androidtest.model.ReadmeResponse
import com.example.degree53androidtest.model.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GitHubService {
    @GET("/search/repositories")
    suspend fun search(@Query("q") name: String) : SearchResponse

    @GET("/repos/{owner}/{repo}/readme")
    suspend fun readme(@Path("owner") owner: String, @Path("repo") repo: String) : ReadmeResponse
}

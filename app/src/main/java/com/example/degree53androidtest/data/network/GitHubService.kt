package com.example.degree53androidtest.data.network

import com.example.degree53androidtest.data.models.ReadmeResponse
import com.example.degree53androidtest.data.models.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface GitHubService {

    @Headers ("Accept: application/vnd.github.v3+json")
    @GET("/search/repositories")
    suspend fun search(@Query("q") name: String) : SearchResponse

    @Headers ("Accept: application/vnd.github.v3+json")
    @GET("/repos/{owner}/{repo}/readme")
    suspend fun readme(@Path("owner") owner: String, @Path("repo") repo: String) : ReadmeResponse
}

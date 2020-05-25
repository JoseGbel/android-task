package com.example.degree53androidtest.data.repository

import com.example.degree53androidtest.data.models.SearchResponse
import com.example.degree53androidtest.data.models.ReadmeResponse

interface MainRepository {
    suspend fun getGitHubReposByName(name: String) : SearchResponse
    suspend fun getReadmeFile(owner: String, repo: String) : ReadmeResponse
}

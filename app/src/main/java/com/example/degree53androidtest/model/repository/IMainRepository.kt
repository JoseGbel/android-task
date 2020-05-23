package com.example.degree53androidtest.model.repository

import com.example.degree53androidtest.model.SearchResponse
import com.example.degree53androidtest.model.ReadmeResponse

interface IMainRepository {
    suspend fun getGitHubReposByName(name: String) : SearchResponse
    suspend fun getReadmeFile(owner: String, repo: String) : ReadmeResponse
}

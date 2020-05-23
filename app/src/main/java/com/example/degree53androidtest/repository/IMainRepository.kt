package com.example.degree53androidtest.repository

import com.example.degree53androidtest.model.SearchResponseObject
import com.example.degree53androidtest.network.ReadmeResponse

interface IMainRepository {
    suspend fun getGitHubReposByName(name: String) : SearchResponseObject
    suspend fun getReadmeFile(owner: String, repo: String) : ReadmeResponse
}

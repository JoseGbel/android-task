package com.example.degree53androidtest.data.repository

import com.example.degree53androidtest.data.models.SearchResponse
import com.example.degree53androidtest.data.network.GitHubServiceFactory
import com.example.degree53androidtest.data.network.GitHubService
import com.example.degree53androidtest.data.models.ReadmeResponse

class MainRepository(private val api : GitHubService = GitHubServiceFactory.createService())
    : IMainRepository {

        override suspend fun getGitHubReposByName(name: String) : SearchResponse {
            return api.search(name)
    }

    override suspend fun getReadmeFile(owner: String, repo: String): ReadmeResponse {
        return api.readme(owner, repo)
    }
}
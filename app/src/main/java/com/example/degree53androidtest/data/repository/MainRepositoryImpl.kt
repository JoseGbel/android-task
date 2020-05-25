package com.example.degree53androidtest.data.repository

import com.example.degree53androidtest.data.models.SearchResponse
import com.example.degree53androidtest.data.network.GitHubServiceFactory
import com.example.degree53androidtest.data.network.GitHubService
import com.example.degree53androidtest.data.models.ReadmeResponse

class MainRepositoryImpl(private val gitHubService : GitHubService = GitHubServiceFactory.createService())
    : MainRepository {

        override suspend fun getGitHubReposByName(name: String) : SearchResponse {
            return gitHubService.search(name)
    }

    override suspend fun getReadmeFile(owner: String, repo: String): ReadmeResponse {
        return gitHubService.readme(owner, repo)
    }
}
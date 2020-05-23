package com.example.degree53androidtest.model.repository

import com.example.degree53androidtest.model.SearchResponse
import com.example.degree53androidtest.model.network.GitHubServiceFactory
import com.example.degree53androidtest.model.network.GitHubService
import com.example.degree53androidtest.model.ReadmeResponse

class MainRepository(private val api : GitHubService = GitHubServiceFactory.createService())
    : IMainRepository {

        override suspend fun getGitHubReposByName(name: String) : SearchResponse{
            return api.search(name)
    }

    override suspend fun getReadmeFile(owner: String, repo: String): ReadmeResponse {
        return api.readme(owner, repo)
    }
}
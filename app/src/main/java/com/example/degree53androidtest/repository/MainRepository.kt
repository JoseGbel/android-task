package com.example.degree53androidtest.repository

import androidx.lifecycle.LiveData
import com.example.degree53androidtest.model.SearchResponseObject
import com.example.degree53androidtest.network.GitHubServiceFactory
import com.example.degree53androidtest.network.GitHubService
import com.example.degree53androidtest.network.ReadmeResponse

class MainRepository(private val api : GitHubService = GitHubServiceFactory.createService())
    : IMainRepository {

        override suspend fun getGitHubReposByName(name: String) : SearchResponseObject{
            return api.search(name)
    }

    override suspend fun getReadmeFile(owner: String, repo: String): ReadmeResponse {
        return api.readme(owner, repo)
    }
}
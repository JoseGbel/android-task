package com.example.degree53androidtest.repository

import com.example.degree53androidtest.model.SearchResponseObject
import com.example.degree53androidtest.network.GitHubServiceFactory
import com.example.degree53androidtest.network.GitHubService

class MainRepository(private val api : GitHubService = GitHubServiceFactory.createService())
    : IMainRepository {

        override suspend fun getGitHubReposByName(name: String) : SearchResponseObject{
            return api.search(name)
    }
}
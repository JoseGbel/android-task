package com.example.degree53androidtest.repository

import com.example.degree53androidtest.model.SearchResponseObject

interface IMainRepository {
    suspend fun getGitHubReposByName(name: String) : SearchResponseObject
}

package com.example.degree53androidtest.network

import retrofit2.http.GET

interface ReposApi {
    @GET("repos/octokit/octokit.rb")
    fun search()
}

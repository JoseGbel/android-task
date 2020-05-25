package com.example.degree53androidtest.data.network

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Factory that creates instances of the GitHubService
 */
object GitHubServiceFactory {
    private const val API_BASE_URL = "https://api.github.com"

    fun createService(): GitHubService {
        val httpClient = OkHttpClient.Builder()

        val builder = Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))

        return builder
            .client(httpClient.build())
            .build().create(GitHubService::class.java)
    }
}
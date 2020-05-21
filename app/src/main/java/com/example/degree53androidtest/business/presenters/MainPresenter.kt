package com.example.degree53androidtest.business.presenters

import com.example.degree53androidtest.model.SearchResponseObject
import com.example.degree53androidtest.presentation.IEntryActivity
import com.example.degree53androidtest.repository.IMainRepository
import kotlinx.coroutines.runBlocking

class MainPresenter(val view: IEntryActivity, val repository: IMainRepository){

    fun searchByName(name: String) : SearchResponseObject = runBlocking{
        repository.getGitHubReposByName(name)
    }
}
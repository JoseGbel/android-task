package com.example.degree53androidtest.business.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.degree53androidtest.model.GitHubRepo

class SearchViewModel : ViewModel() {
    private val _githubRepos = MutableLiveData<List<GitHubRepo>>()
    val gitHubRepos: LiveData<List<GitHubRepo>>
        get() = _githubRepos
}

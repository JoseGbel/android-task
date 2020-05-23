package com.example.degree53androidtest.business.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.degree53androidtest.model.SearchResponse
import com.example.degree53androidtest.model.repository.MainRepository
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {
    private val _githubRepos = MutableLiveData<SearchResponse>()
    val gitHubRepos: LiveData<SearchResponse>
        get() = _githubRepos

    fun searchGitHubRepos(name: String) {
        viewModelScope.launch {
            val response = MainRepository().getGitHubReposByName(name)
            _githubRepos.postValue(response)
        }
    }
}

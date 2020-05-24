package com.example.degree53androidtest.business.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.degree53androidtest.data.models.SearchResponse
import com.example.degree53androidtest.data.repository.MainRepository
import com.example.degree53androidtest.utils.EspressoIdlingResource
import kotlinx.coroutines.launch

class RepoListViewModel : ViewModel() {
    private val _githubRepos = MutableLiveData<SearchResponse>()
    val gitHubRepos: LiveData<SearchResponse>
        get() = _githubRepos

    private val _isLoading = MutableLiveData<Boolean>(false)
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    fun searchGitHubRepos(name: String) {
        EspressoIdlingResource.increment()
        _isLoading.postValue(true)
        val job = viewModelScope.launch {
            val response = MainRepository().getGitHubReposByName(name)
            _githubRepos.postValue(response)
        }

        job.invokeOnCompletion {
            _isLoading.postValue(false)
            EspressoIdlingResource.decrement()
        }
    }
}

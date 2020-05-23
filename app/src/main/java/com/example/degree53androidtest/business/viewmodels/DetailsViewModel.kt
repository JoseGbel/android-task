package com.example.degree53androidtest.business.viewmodels

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.degree53androidtest.R
import com.example.degree53androidtest.network.ReadmeResponse
import com.example.degree53androidtest.repository.MainRepository
import kotlinx.android.synthetic.main.details_fragment.view.*
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.lang.Exception

class DetailsViewModel : ViewModel() {

    private val _readme = MutableLiveData<ReadmeResponse>()
    val readme : LiveData<ReadmeResponse>
        get() = _readme

    private val _readMeNotFound = MutableLiveData<Boolean>()
    val readmeNotFound : LiveData<Boolean>
        get() = _readMeNotFound

    private val _connectionProblem = MutableLiveData<Boolean>()
    val connectionProblem : LiveData<Boolean>
        get() = _connectionProblem

    fun fetchReadme(owner:String, repo: String) {
        viewModelScope.launch {
            try {
                _readme.postValue(MainRepository().getReadmeFile(owner, repo))
            } catch (e: HttpException) {
                _readMeNotFound.postValue(true)
            } catch (e: Exception) {
                _connectionProblem.postValue(true)
            }
        }
    }
}
package com.example.degree53androidtest.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.degree53androidtest.R
import com.example.degree53androidtest.business.viewmodels.DetailsViewModel
import com.example.degree53androidtest.model.GitHubRepoData
import com.example.degree53androidtest.model.Owner

class DetailsFragment : Fragment() {
    private lateinit var viewModel: DetailsViewModel
    private lateinit var repoData: GitHubRepoData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this).get(DetailsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return inflater.inflate(R.layout.details_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        repoData = arguments?.getParcelable("RepositoryData")
            ?: GitHubRepoData(Owner("", 0, ""),"", 0,0,0,0)

//        viewModel.searchGitHubRepos(searchWord)
//        reposLiveData = viewModel.gitHubRepos
    }
}

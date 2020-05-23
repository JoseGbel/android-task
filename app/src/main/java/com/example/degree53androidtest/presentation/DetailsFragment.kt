package com.example.degree53androidtest.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.degree53androidtest.R
import com.example.degree53androidtest.business.viewmodels.DetailsViewModel
import com.example.degree53androidtest.model.GitHubRepoData
import com.example.degree53androidtest.model.Owner
import com.example.degree53androidtest.utils.NetworkStatus
import com.example.degree53androidtest.utils.NetworkStatusLiveData
import com.example.degree53androidtest.utils.kRoundify
import kotlinx.android.synthetic.main.details_fragment.view.*

class DetailsFragment : Fragment() {

    private var connected: Boolean = false
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
        repoData = arguments?.getParcelable("RepositoryData")
            ?: GitHubRepoData(Owner("", 0, ""),"", 0,0,0,0)

        view.fork_value_tv.text = repoData.forks.kRoundify()
        view.watch_value_tv.text = repoData.watchers.kRoundify()
        view.issues_value_tv.text = repoData.open_issues.kRoundify()
        view.star_value_tv.text = repoData.stars.kRoundify()

        viewModel.fetchReadme(repoData.owner.login, repoData.name)

        viewModel.readme.observe(viewLifecycleOwner, Observer { readMe ->
            val data =
                android.util.Base64.decode(readMe.content, android.util.Base64.DEFAULT)
            view.readme_value.text = String(data)

        })

        viewModel.readmeNotFound.observe(viewLifecycleOwner, Observer { notFoundReadme ->
            if (notFoundReadme) {
                view.readme_value.text = getString(R.string.readme_doesnt_exist)
            }
        })

        viewModel.connectionProblem.observe(viewLifecycleOwner, Observer { connectionProblem ->
            if (connectionProblem) {
                displayUnableToConnectDialog()
            }
        })
    }

    private fun displayUnableToConnectDialog() {
        FailedConnectionFragment().show(activity!!.supportFragmentManager, "UnableToConnect")
    }
}

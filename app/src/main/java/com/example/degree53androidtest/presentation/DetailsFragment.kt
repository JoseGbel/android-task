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
import com.example.degree53androidtest.data.models.Owner
import com.example.degree53androidtest.data.models.RepoDetails
import com.example.degree53androidtest.utils.kRoundify
import kotlinx.android.synthetic.main.details_fragment.view.*

class DetailsFragment : Fragment() {

    private lateinit var viewModel: DetailsViewModel
    private lateinit var repoData: RepoDetails

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

        // get data from bundle
        repoData = arguments?.getParcelable("RepositoryData")
            ?: RepoDetails(
                Owner(
                    "",
                    0,
                    ""
                ), "", 0, 0, 0, 0
            )

        // bind data to views
        view.fork_value_tv.text = repoData.forks.kRoundify()
        view.watch_value_tv.text = repoData.watchers.kRoundify()
        view.issues_value_tv.text = repoData.open_issues.kRoundify()
        view.star_value_tv.text = repoData.stars.kRoundify()

        // make the network call
        viewModel.fetchReadme(repoData.owner.login, repoData.name)

        // observe the viewModel for readme file
        viewModel.readme.observe(viewLifecycleOwner, Observer { readMe ->
            val data =
                android.util.Base64.decode(readMe.content, android.util.Base64.DEFAULT)
            view.readme_value.text = String(data)

        })

        // observe the view model for not found readme file
        viewModel.readmeNotFound.observe(viewLifecycleOwner, Observer { notFoundReadme ->
            if (notFoundReadme) {
                view.readme_value.text = getString(R.string.readme_doesnt_exist)
            }
        })

        // observe the viewModel for connection problems
        viewModel.connectionProblem.observe(viewLifecycleOwner, Observer { connectionProblem ->
            if (connectionProblem) {
                displayUnableToConnectDialog()
            }
        })
    }

    private fun displayUnableToConnectDialog() {
        FailedConnectionDialogFragment()
            .show(requireActivity().supportFragmentManager, "UnableToConnect")
    }
}

package com.example.degree53androidtest.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.degree53androidtest.R
import com.example.degree53androidtest.business.viewmodels.RepoListViewModel
import com.example.degree53androidtest.data.models.RepoDetails
import com.example.degree53androidtest.data.models.SearchResponse
import com.example.degree53androidtest.presentation.adapters.ReposRecyclerViewAdapter
import com.example.degree53androidtest.utils.NetworkStatus
import com.example.degree53androidtest.utils.NetworkStatusLiveData
import kotlinx.android.synthetic.main.repo_list_fragment.*


class RepoListFragment : Fragment(), ReposRecyclerViewAdapter.OnRepoListener {

    private val TAG: String? = "AppDebug"
    private var connected: Boolean = false
    private var reposAdapter: ReposRecyclerViewAdapter? = null
    private lateinit var reposLiveData : LiveData<SearchResponse>
    private lateinit var recyclerView : RecyclerView
    private lateinit var viewModel: RepoListViewModel
    private lateinit var searchWord : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activity?.let {
            viewModel = ViewModelProvider(it).get(RepoListViewModel::class.java)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.repo_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.search_recycler_view)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        searchWord = arguments?.getString("Keyword") ?: ""

        observeNetworkConnectivity()

        repo_search_view.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                recyclerView.adapter = null
                if (query != null) {
                    if (connected) {
                        viewModel.searchGitHubRepos(query)
                    } else {
                        displayUnableToConnectDialog()
                    }
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                // TODO due to github's Api rate limit, this feature has been disabled.
//                if (newText != null) {
//                    if (connected) {
//                        viewModel.searchGitHubRepos(newText)
//                    } else {
//                        displayUnableToConnectDialog()
//                    }
//                }
                return false
            }
        })

        repo_search_view.setOnClickListener { repo_search_view.isIconified = false }

        reposLiveData = viewModel.gitHubRepos
        Log.d (TAG , "Has observers ${reposLiveData.hasObservers()}")
        reposLiveData.observe(viewLifecycleOwner, Observer { data ->
            if (data.total_count == 0) {
                Toast.makeText(context, getString(R.string.no_results), Toast.LENGTH_LONG)
                    .show()
            } else {
                setUpRecyclerView()
            }
        })
        Log.d (TAG , "Has observers ${reposLiveData.hasObservers()}")
    }

    private fun setUpRecyclerView(){
        reposAdapter = ReposRecyclerViewAdapter(reposLiveData.value!!, context,this)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = reposAdapter

        Log.d(TAG, "SetupRecyclerView: " + recyclerView.adapter)
    }

    override fun onRepoClick(position: Int, repoData: RepoDetails) {
        if (connected) { // open the search fragment
            launchDetailsFragment(repoData)
        } else {
            displayUnableToConnectDialog()
        }
    }

    private fun launchDetailsFragment(repoData: RepoDetails) {
        val fragmentManager = requireActivity().supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
            .setCustomAnimations(
                R.anim.entry_top_to_bottom, R.anim.exit_top_to_bottom,
                R.anim.entry_bottom_to_top, R.anim.exit_bottom_to_top
            )
        val fragment = DetailsFragment()
        val bundle = Bundle()
        bundle.putParcelable("RepositoryData", repoData)
        fragment.arguments = bundle
        fragmentTransaction.addToBackStack("DetailsFragment")
        fragmentTransaction.add(R.id.fragment_container, fragment)
        fragmentTransaction.commit()
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "On Resume: " + recyclerView.adapter)
    }
    private fun displayUnableToConnectDialog() {
        FailedConnectionDialogFragment()
            .show(requireActivity().supportFragmentManager, "UnableToConnect")
    }

    private fun observeNetworkConnectivity() {
        NetworkStatusLiveData.observe(viewLifecycleOwner, Observer {
            if (it == NetworkStatus.AVAILABLE) connected = true
            if (it == NetworkStatus.UNAVAILABLE) connected = false
            if (it == NetworkStatus.LOST) connected = false
        })
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "On Stop: " + recyclerView.adapter)
    }

    fun resetAdapter() {
        Log.d(TAG, "Reset adapter: " + recyclerView.adapter)
        recyclerView.adapter = null

        reposAdapter = null

        reposLiveData
        reposLiveData.removeObservers(viewLifecycleOwner)

        Log.d (TAG , "Has observers ${reposLiveData.hasObservers()} " +
                           "HasActiveObs ${reposLiveData.hasActiveObservers()}")
    }
}

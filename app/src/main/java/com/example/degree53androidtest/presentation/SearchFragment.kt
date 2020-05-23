package com.example.degree53androidtest.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.degree53androidtest.R
import com.example.degree53androidtest.business.viewmodels.SearchViewModel
import com.example.degree53androidtest.model.GitHubRepoData
import com.example.degree53androidtest.model.SearchResponseObject
import com.example.degree53androidtest.presentation.adapters.ReposRecyclerViewAdapter
import com.example.degree53androidtest.utils.NetworkStatus
import com.example.degree53androidtest.utils.NetworkStatusLiveData


class SearchFragment : Fragment(), ReposRecyclerViewAdapter.OnRepoListener {

    private var connected: Boolean = false
    private lateinit var reposLiveData : LiveData<SearchResponseObject>
    private var reposAdapter: ReposRecyclerViewAdapter? = null
    private lateinit var recyclerView : RecyclerView
    private lateinit var viewModel: SearchViewModel
    private lateinit var searchWord : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this).get(SearchViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return inflater.inflate(R.layout.search_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.search_recycler_view)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        searchWord = arguments?.getString("KeyForFragmentBundle") ?: ""

        observeNetworkConnectivity()

        viewModel.searchGitHubRepos(searchWord)
        reposLiveData = viewModel.gitHubRepos
        reposLiveData.observe(viewLifecycleOwner, Observer { data ->
            setUpRecyclerView()
        })
    }

    private fun setUpRecyclerView(){
        if (reposAdapter == null){
            reposAdapter = ReposRecyclerViewAdapter(reposLiveData.value!!, context, this)
            recyclerView.layoutManager = LinearLayoutManager(context)
            recyclerView.adapter = reposAdapter
        } else {
            reposAdapter!!.notifyDataSetChanged()
        }
    }

    override fun onRepoClick(position: Int, repoData: GitHubRepoData) {

        if (connected) { // open the search fragment
            val fragmentManager = activity!!.supportFragmentManager
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
        } else {
            displayUnableToConnectDialog()
        }
    }

    private fun displayUnableToConnectDialog() {
        FailedConnectionFragment().show(activity!!.supportFragmentManager, "UnableToConnect")
    }

    private fun observeNetworkConnectivity() {
        NetworkStatusLiveData.observe(this, Observer {
            if (it == NetworkStatus.AVAILABLE) connected = true
            if (it == NetworkStatus.UNAVAILABLE) connected = false
            if (it == NetworkStatus.LOST) connected = false
        })
    }
}
package com.example.degree53androidtest.presentation

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.degree53androidtest.R
import com.example.degree53androidtest.business.viewmodels.SearchViewModel
import com.example.degree53androidtest.utils.NetworkStatusLiveData
import kotlinx.android.synthetic.main.activity_main.*

/**
 * @author Jose Garcia
 */
class EntryActivity : AppCompatActivity() {
    private val fragmentManager = supportFragmentManager
    private lateinit var searchViewModel : SearchViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        searchViewModel = ViewModelProvider(this).get(SearchViewModel::class.java)
    }

    override fun onStart() {
        super.onStart()

        // initialise the Network Status detector
        NetworkStatusLiveData.init(application)

        searchViewModel.isLoading.observe(this, Observer { isLoading ->
            if(isLoading)
                progress_bar.visibility = View.VISIBLE
            else
                progress_bar.visibility = View.INVISIBLE
        })

        init()
    }

    private fun init(){
        if(supportFragmentManager.fragments.size == 0){
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, SearchFragment(), "RepoListFragment")
                .commit()
        }
    }

    override fun onBackPressed() {
        val topFragmentIndex = fragmentManager.backStackEntryCount-1
        if (topFragmentIndex > 0) {
            val backStackEntry = fragmentManager.getBackStackEntryAt(topFragmentIndex)
            if (backStackEntry.name == "RepoListFragment") {
                (fragmentManager.findFragmentByTag("RepoListFragment") as SearchFragment)
                    .resetAdapter()
            }
        }

        if (fragmentManager.backStackEntryCount > 0){
            fragmentManager.popBackStackImmediate()
        }
        else super.onBackPressed()
    }
}
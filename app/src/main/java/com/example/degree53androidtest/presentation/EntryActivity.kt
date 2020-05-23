package com.example.degree53androidtest.presentation

import android.os.Bundle
import android.text.SpannableStringBuilder
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.Observer
import com.example.degree53androidtest.R
import com.example.degree53androidtest.utils.NetworkStatus
import com.example.degree53androidtest.utils.NetworkStatusLiveData
import com.example.degree53androidtest.utils.hideKeyboard


class EntryActivity : AppCompatActivity() {

    val FRAGMENTDATA: String = "KeyForFragmentBundle"
    private lateinit var button: Button
    private lateinit var editText: EditText
    private lateinit var layout: ConstraintLayout
    private val fragmentManager = supportFragmentManager
    private var connected : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button = findViewById(R.id.search_btn_main_act)
        editText = findViewById(R.id.introduce_name_et)
        layout = findViewById(R.id.entry_activity_layout)
    }

    override fun onStart() {
        super.onStart()

        // initialise the Network Status detector
        NetworkStatusLiveData.init(application)

        observeConnectivity()

        button.setOnClickListener {
            if (connected) {
                if (editText.text.toString() == ""){
                    editText.error = getString(R.string.introduce_something_error)
                } else{
                    startSearchFragment()

                    // restart the EditText field to empty string
                    editText.text = SpannableStringBuilder("")

                    hideKeyboard()
                }
            }else {
                displayUnableToConnectDialog()
            }
        }
    }

    private fun startSearchFragment() {
        val fragmentTransaction = fragmentManager.beginTransaction()
        val fragment = SearchFragment()
        val bundle = Bundle()
        bundle.putString(FRAGMENTDATA, editText.text.toString())
        fragment.arguments = bundle
        fragmentTransaction.add(R.id.fragment_container, fragment)
        fragmentTransaction.addToBackStack("SearchFragment")
        fragmentTransaction.commit()
    }

    private fun observeConnectivity() {
        NetworkStatusLiveData.observe(this, Observer {
            if (it == NetworkStatus.AVAILABLE) connected = true
            if (it == NetworkStatus.UNAVAILABLE) connected = false
            if (it == NetworkStatus.LOST) connected = false
        })
    }

    override fun onBackPressed() {
        if (fragmentManager.backStackEntryCount > 0){
            fragmentManager.popBackStackImmediate()
        }
        else super.onBackPressed()
    }

    private fun displayUnableToConnectDialog() {
        FailedConnectionDialogFragment().show(supportFragmentManager, "UnableToConnect")
    }
}
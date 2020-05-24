package com.example.degree53androidtest.presentation

import android.os.Bundle
import android.text.SpannableStringBuilder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.degree53androidtest.R
import com.example.degree53androidtest.utils.NetworkStatus
import com.example.degree53androidtest.utils.NetworkStatusLiveData
import com.example.degree53androidtest.utils.hideKeyboard
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.search_fragment.*

/**
 * @author Jose Garcia
 */
class SearchFragment : Fragment() {

    private lateinit var editText: EditText
    private var connected : Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.search_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        editText = view.findViewById(R.id.introduce_name_et)
    }

    override fun onStart() {
        super.onStart()

        observeConnectivity()

        search_btn.setOnClickListener {
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

    private fun observeConnectivity() {
        NetworkStatusLiveData.observe(this, Observer {
            if (it == NetworkStatus.AVAILABLE) connected = true
            if (it == NetworkStatus.UNAVAILABLE) connected = false
            if (it == NetworkStatus.LOST) connected = false
        })
    }

    private fun startSearchFragment() {
        val fragmentTransaction = requireFragmentManager().beginTransaction()
        val fragment = RepoListFragment()
        val bundle = Bundle()
        bundle.putString("Keyword", editText.text.toString())
        fragment.arguments = bundle
        fragmentTransaction.add(R.id.fragment_container, fragment, "RepoListFragment")
        fragmentTransaction.addToBackStack("RepoListFragment")
        fragmentTransaction.commit()
    }

    private fun displayUnableToConnectDialog() {
        FailedConnectionDialogFragment()
            .show(requireActivity().supportFragmentManager, "UnableToConnect")
    }
}
package com.example.degree53androidtest.presentation

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.degree53androidtest.R
import com.example.degree53androidtest.business.presenters.MainPresenter
import com.example.degree53androidtest.repository.MainRepository

class EntryActivity : AppCompatActivity(), IEntryActivity {

    private val FRAGMENTDATA: String = "KeyForFragmentBundle"
    private lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button : Button = findViewById(R.id.search_btn_main_act)
        val editText : EditText = findViewById(R.id.introduce_name_et)
        val layout : ConstraintLayout = findViewById(R.id.entry_activity_layout)

        presenter = MainPresenter(this, MainRepository())

        button.setOnClickListener {
            layout.visibility = View.GONE
            val searchResponseObject = presenter.searchByName(editText.text.toString())
            val fragmentManager = supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            val fragment = SearchFragment()
            val bundle = Bundle()
            bundle.putParcelable(FRAGMENTDATA, searchResponseObject)
            fragment.arguments = bundle
            fragmentTransaction.add(R.id.fragment_container, fragment)
            fragmentTransaction.commit()
        }
    }
}
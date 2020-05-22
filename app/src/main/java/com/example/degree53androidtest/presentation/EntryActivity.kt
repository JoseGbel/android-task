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

    val FRAGMENTDATA: String = "KeyForFragmentBundle"
    private lateinit var presenter: MainPresenter
    private lateinit var button: Button
    private lateinit var editText: EditText
    private lateinit var layout: ConstraintLayout
    private val fragmentManager = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button = findViewById(R.id.search_btn_main_act)
        editText = findViewById(R.id.introduce_name_et)
        layout = findViewById(R.id.entry_activity_layout)
        presenter = MainPresenter(this, MainRepository())
    }

    override fun onStart() {
        super.onStart()

        button.setOnClickListener {
            layout.visibility = View.GONE

            val fragmentTransaction = fragmentManager.beginTransaction()
            val fragment = SearchFragment()
            val bundle = Bundle()
            bundle.putString(FRAGMENTDATA, editText.text.toString())
            fragment.arguments = bundle
            fragmentTransaction.add(R.id.fragment_container, fragment)
            fragmentTransaction.addToBackStack("SearchFragment")
            fragmentTransaction.commit()
        }
    }

    override fun onBackPressed() {
        if (fragmentManager.backStackEntryCount > 0){
            fragmentManager.popBackStackImmediate()
        }
        else super.onBackPressed()
    }
}
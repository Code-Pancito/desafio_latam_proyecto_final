package com.codepancito.yu_gi_ohmonsterbattle.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.codepancito.yu_gi_ohmonsterbattle.R
import com.codepancito.yu_gi_ohmonsterbattle.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val tag = "MainActivity"

    private val homePageFragment = HomePageFragment.newInstance()
    private val homePageTag = "HomePage"
    private val cardSearchFragment = CardSearchFragment.newInstance()
    private val cardSearchTag = "CardSearch"

    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        showFragment(homePageFragment, homePageTag)

        viewModel = MainActivityViewModel(application)
        viewModel.fetchDataFromServer()

        bottomNavigationView_Menu.setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.menu_home -> {
                    showFragment(homePageFragment, homePageTag)
                    true
                }

                R.id.menu_search_card -> {
                    showFragment(cardSearchFragment, cardSearchTag)
                    true
                }
                else -> false
            }
        }

    }

    private fun showFragment(fragment: Fragment, tag: String) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frameLayout_Container, fragment, tag)
            .commit()
    }
}
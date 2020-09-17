package com.codepancito.yu_gi_ohmonsterbattle.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.codepancito.yu_gi_ohmonsterbattle.R
import com.codepancito.yu_gi_ohmonsterbattle.viewmodel.MainActivityViewModel

class MainActivity : AppCompatActivity() {

    private val tag = "MainActivity"

    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = MainActivityViewModel(application)

        viewModel.getAllMonsterCards().observe(this, {
            Log.d(tag, "size: ${it.size}")
        })

        viewModel.fetchDataFromServer()
    }
}
package com.example.myapplication

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.base.BaseActivity
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.utils.Connection
import com.example.myapplication.utils.Unknown

class MainActivity : BaseActivity() {

    private lateinit var viewModel: MainViewModel //by viewModels { viewModelFactory }

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater).apply {
            viewModel = viewModel
        }
        setContentView(binding.root)

        getData()
    }

    private fun getData() {
        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
        viewModel.getCreditScoreData(this@MainActivity)

        viewModel.exception.observe(this, { exception ->
            when (exception) {
                is Connection -> {}
                is Unknown -> {}
            }
        })
    }

}
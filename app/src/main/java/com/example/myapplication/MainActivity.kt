package com.example.myapplication

import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.example.myapplication.base.BaseActivity
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.utils.Connection
import com.example.myapplication.utils.showSnackBarStatic

class MainActivity : BaseActivity() {

    private val viewModel: MainViewModel by viewModels { viewModelFactory }

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = viewModel

        getData()
    }

    private fun getData() {
        viewModel.getCreditScoreData(this@MainActivity)

        viewModel.exception.observe(this, { exception ->
            val errorText = when (exception) {
                is Connection -> R.string.error_connection_message
                else -> R.string.error_generic_message
            }
            binding.activityMainRoot.showSnackBarStatic(errorText)
        })
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}
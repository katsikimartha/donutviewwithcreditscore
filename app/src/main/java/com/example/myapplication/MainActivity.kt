package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.myapplication.base.ViewModelFactory
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.utils.Connection
import com.example.myapplication.utils.Unknown
import dagger.android.AndroidInjection
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: MainViewModel by viewModels { viewModelFactory }

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater).apply {
            viewModel = viewModel
        }
        setContentView(binding.root)

        getData()
    }

    private fun getData() {
        viewModel.getCreditScoreData()

        viewModel.exception.observe(this, { exception ->
            when(exception) {
                is Connection -> {}
                is Unknown -> {}
            }
        })

        viewModel.totalScore.observe(this, { score ->
            binding.textviewOutOf.text = getString(R.string.out_of, score.toString())
        })

        viewModel.creditScore.observe(this, { score ->
            binding.textviewCreditScore.text = score.toString()
        })

        viewModel.creditScorePercentage.observe(this, { score ->
            binding.progressBarCreditScore.progress = score
        })
    }

}
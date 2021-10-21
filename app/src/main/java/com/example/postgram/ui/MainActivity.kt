package com.example.postgram.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.postgram.adapter.RecyclerViewAdapter
import com.example.postgram.databinding.ActivityMainBinding
import com.example.postgram.utils.ApiState
import com.example.postgram.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var postAdapter: RecyclerViewAdapter
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        initRecyclerView()
        mainViewModel.getPost()

        lifecycleScope.launchWhenStarted {
            mainViewModel._postStateFlow.collect {
                when (it) {
                    is ApiState.Loading -> {
                        binding.recyclerView.isVisible = false
                        binding.progressMain.isVisible = true
                    }
                    is ApiState.Failure -> {
                        binding.recyclerView.isVisible = false
                        binding.progressMain.isVisible = false
                        Log.d("MainActivity", "onCreate: ${it.msg} ")
                    }
                    is ApiState.Success -> {
                        binding.recyclerView.isVisible = true
                        binding.progressMain.isVisible = false
                        postAdapter.setData(it.data)
                    }
                    ApiState.Empty -> {

                    }
                }
            }
        }

        setContentView(binding.root)
    }

    private fun initRecyclerView() {
        postAdapter = RecyclerViewAdapter(ArrayList())
        binding.recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = postAdapter
        }
    }

}
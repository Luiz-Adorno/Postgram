package com.example.postgram.ui

/**
 * Created by Luiz Adorno on October 21, 2021
 * Linkedln profile https://www.linkedin.com/in/luiz-adorno/
 */

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.postgram.adapter.PostAdapter
import com.example.postgram.databinding.ActivityMainBinding
import com.example.postgram.models.PostListItem
import com.example.postgram.utils.PostApiState
import com.example.postgram.utils.Utils
import com.example.postgram.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var postAdapter: PostAdapter
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        initRecyclerView()
        mainViewModel.getPost()

        lifecycleScope.launchWhenStarted {
            mainViewModel._postStateFlow.collect {
                when (it) {
                    is PostApiState.Loading -> {
                        binding.recyclerView.isVisible = false
                        binding.progressMain.isVisible = true
                    }
                    is PostApiState.Failure -> {
                        binding.recyclerView.isVisible = false
                        binding.progressMain.isVisible = false
                        Log.d("MainActivity", "onCreate: ${it.msg} ")
                    }
                    is PostApiState.Success -> {
                        binding.recyclerView.isVisible = true
                        binding.progressMain.isVisible = false
                        postAdapter.setData(it.data)
                    }
                    PostApiState.Empty -> {

                    }
                }
            }
        }

        setContentView(binding.root)
    }

    private fun initRecyclerView() {
        postAdapter = PostAdapter(ArrayList(), this@MainActivity:: openPostDetail)
        binding.recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = postAdapter
        }
    }

    private fun openPostDetail(post: PostListItem){
        val detailIntent = Intent(this, PostItemDetailActivity::class.java)
        detailIntent.putExtra("postid", Utils.getGsonParser().toJson(post))
        startActivity(detailIntent)
    }

}
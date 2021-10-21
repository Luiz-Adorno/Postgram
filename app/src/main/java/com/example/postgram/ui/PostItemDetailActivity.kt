package com.example.postgram.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.postgram.adapter.CommentAdapter
import com.example.postgram.databinding.ActivityPostItemDetailBinding
import com.example.postgram.models.PostListItem
import com.example.postgram.utils.CommentApiState
import com.example.postgram.utils.UserApiState
import com.example.postgram.utils.Utils.getGsonParser
import com.example.postgram.viewmodel.CommentViewModel
import com.example.postgram.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class PostItemDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPostItemDetailBinding
    private lateinit var commentAdapter: CommentAdapter
    private val commentViewModel: CommentViewModel by viewModels()
    private val userViewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPostItemDetailBinding.inflate(layoutInflater)
        val postJsonString = intent.getStringExtra("postid")
        val post: PostListItem = getGsonParser().fromJson(
            postJsonString,
            PostListItem::class.java
        )

        binding.title.text = post.title
        binding.body.text = post.body

        initRecyclerView()
        commentViewModel.getComment(post.id)
        userViewModel.getUser(post.userId)
        getOwnerPost()

        lifecycleScope.launchWhenStarted {
            commentViewModel._commentStateFlow.collect {
                when (it) {
                    is CommentApiState.Loading -> {
                        binding.recyclerView.isVisible = false
                        binding.progressMain.isVisible = true
                    }
                    is CommentApiState.Failure -> {
                        binding.recyclerView.isVisible = false
                        binding.progressMain.isVisible = false
                        Log.d("PostItemDetailActivity", "onCreate: ${it.msg} ")
                    }
                    is CommentApiState.Success -> {
                        binding.recyclerView.isVisible = true
                        binding.progressMain.isVisible = false
                        commentAdapter.setData(it.data)
                        binding.commentNumber.text = commentAdapter.itemCount.toString()
                    }
                    CommentApiState.Empty -> {

                    }
                }
            }
        }
        setContentView(binding.root)
    }

    private fun initRecyclerView() {
        commentAdapter = CommentAdapter(ArrayList())
        binding.recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@PostItemDetailActivity)
            adapter = commentAdapter
        }
    }

    private fun getOwnerPost(){
        lifecycleScope.launchWhenStarted {
            userViewModel._userStateFlow.collect {
                when (it) {
                    is UserApiState.Loading -> {
                        binding.progressMain.isVisible = true
                    }
                    is UserApiState.Failure -> {
                        binding.progressMain.isVisible = false
                        Log.d("PostItemDetailActivity", "onCreate: ${it.msg} ")
                    }
                    is UserApiState.Success -> {
                        binding.progressMain.isVisible = false
                        binding.woner.text = it.data[0].name
                    }
                    UserApiState.Empty -> {

                    }
                }
            }
        }
    }

}
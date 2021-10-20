package com.example.postgram.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.postgram.adapter.RecyclerViewAdapter
import com.example.postgram.databinding.ActivityMainBinding
import com.example.postgram.models.PostListItem
import com.example.postgram.viewmodel.MainActivityViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        val recyclerViewAdapter = binding.recyclerView

        recyclerViewAdapter.layoutManager = LinearLayoutManager(this)
        initViewModel(recyclerViewAdapter, binding)

        setContentView(binding.root)
    }
    private fun initViewModel(recyclerView: RecyclerView, binding: ActivityMainBinding){
        binding.progressMain.visibility = View.VISIBLE

        val viewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]

        viewModel.getPostListObserver().observe(this, Observer<List<PostListItem>>{
            //usar let
            if(it != null ){
                binding.progressMain.visibility = View.GONE


                recyclerView.adapter = RecyclerViewAdapter(it, this@MainActivity::openPostDetail)
            }else {
                binding.progressMain.visibility = View.GONE
                Toast.makeText(this, "Error in geting data", Toast.LENGTH_SHORT).show()
            }
        })
        viewModel.makeApiCall()
    }

    private fun openPostDetail(postListItem: PostListItem){
        val detailIntent = Intent(this, PostItemDetail::class.java)
        //detailIntent.putExtra("post", postListItem)
        startActivity(detailIntent)
    }

}
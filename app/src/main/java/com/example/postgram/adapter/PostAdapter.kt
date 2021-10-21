package com.example.postgram.adapter

/**
 * Created by Luiz Adorno on October 21, 2021
 * Linkedln profile https://www.linkedin.com/in/luiz-adorno/
 */

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.postgram.databinding.RecyclerPostItemBinding
import com.example.postgram.models.PostListItem

class PostAdapter(
    private var items: List<PostListItem>,
    private val onItemClick: (PostListItem) -> Unit
): RecyclerView.Adapter<PostAdapter.MyViewHolder>(){

    class MyViewHolder(val binding: RecyclerPostItemBinding): RecyclerView.ViewHolder(binding.root)

    //inflate the layout with view binding library
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(RecyclerPostItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,false
        ))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val post = items[position]

        holder.binding.title.text = post.title
        holder.binding.body.text = post.body

        holder.itemView.setOnClickListener{
            onItemClick(post)
        }

    }

    fun setData(postListItem: List<PostListItem>){
        this.items = postListItem
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return items.size
    }
}
package com.example.postgram.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.postgram.databinding.RecyclerPostItemBinding
import com.example.postgram.models.PostListItem

class RecyclerViewAdapter(
    private val items: List<PostListItem>,
    private val onItemClick: (PostListItem) -> Unit
): RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>(){

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

    override fun getItemCount(): Int {
        return items.size
    }
}
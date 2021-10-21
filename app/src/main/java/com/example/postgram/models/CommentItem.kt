package com.example.postgram.models

/**
 * Created by Luiz Adorno on October 21, 2021
 * Linkedln profile https://www.linkedin.com/in/luiz-adorno/
 */

data class CommentItem(
    val body: String,
    val email: String,
    val id: Int,
    val name: String,
    val postId: Int
)
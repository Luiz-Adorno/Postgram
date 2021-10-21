package com.example.postgram.network

/**
 * Created by Luiz Adorno on October 21, 2021
 * Linkedln profile https://www.linkedin.com/in/luiz-adorno/
 */

import com.example.postgram.models.CommentItem
import com.example.postgram.models.PostListItem
import com.example.postgram.models.UserItem
import javax.inject.Inject

class ApiServiceImpl
    @Inject constructor(
    private val retroService: RetroService
) {
    suspend fun getPost(): List<PostListItem> = retroService.getPost()
    suspend fun getComment(postId: Int): List<CommentItem> = retroService.getComment(postId)
    suspend fun getUser(userID: Int): List<UserItem> = retroService.getUser(userID)
}
package com.example.postgram.network

/**
 * Created by Luiz Adorno on October 21, 2021
 * Linkedln profile https://www.linkedin.com/in/luiz-adorno/
 */

import com.example.postgram.models.CommentItem
import com.example.postgram.models.PostListItem
import com.example.postgram.models.UserItem
import com.example.postgram.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Query

interface RetroService {
    @GET(Constants.POST_END_POINT)
    suspend fun getPost(): List<PostListItem>

    @GET(Constants.COMMENTS_END_POINT)
    suspend fun getComment(@Query("postId") postId: Int?): List<CommentItem>

    @GET(Constants.USERS_END_POINT)
    suspend fun getUser(@Query("id") id: Int?): List<UserItem>
}
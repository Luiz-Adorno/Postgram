package com.example.postgram.network

import com.example.postgram.models.CommentItem
import com.example.postgram.models.PostListItem
import com.example.postgram.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Query

interface RetroService {
    @GET(Constants.POST_END_POINT)
    suspend fun getPost(): List<PostListItem>

    @GET(Constants.COMMENTS_END_POINT)
    suspend fun getComment(@Query("postId") postId: Int?): List<CommentItem>
}
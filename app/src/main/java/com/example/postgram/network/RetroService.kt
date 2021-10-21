package com.example.postgram.network

import com.example.postgram.models.PostListItem
import com.example.postgram.utils.Constants
import retrofit2.http.GET

interface RetroService {
    @GET(Constants.POST_END_POINT)
    suspend fun getPost(): List<PostListItem>
}
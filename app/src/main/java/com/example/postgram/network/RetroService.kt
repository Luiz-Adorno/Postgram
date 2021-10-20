package com.example.postgram.network

import com.example.postgram.models.PostListItem
import com.example.postgram.utils.Constants
import retrofit2.Call
import retrofit2.http.GET

interface RetroService {
    @get:GET(Constants.POST_END_POINT)
    val getPosts: Call<List<PostListItem>>
}
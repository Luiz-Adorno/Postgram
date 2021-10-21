package com.example.postgram.network

import com.example.postgram.models.PostListItem
import javax.inject.Inject

class ApiServiceImpl
    @Inject constructor(
    private val retroService: RetroService
) {
    suspend fun getPost(): List<PostListItem> = retroService.getPost()
}
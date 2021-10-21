package com.example.postgram.repository

/**
 * Created by Luiz Adorno on October 21, 2021
 * Linkedln profile https://www.linkedin.com/in/luiz-adorno/
 */

import com.example.postgram.models.CommentItem
import com.example.postgram.models.PostListItem
import com.example.postgram.models.UserItem
import com.example.postgram.network.ApiServiceImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class Repository
@Inject
constructor(
    private val apiServiceImpl: ApiServiceImpl
){
    fun getPost(): Flow<List<PostListItem>> = flow {
        emit(apiServiceImpl.getPost())
    }.flowOn(Dispatchers.IO)

    fun getComment(postId: Int): Flow<List<CommentItem>> = flow {
        emit(apiServiceImpl.getComment(postId))
    }.flowOn(Dispatchers.IO)

    fun getUser(userId: Int): Flow<List<UserItem>> = flow {
        emit(apiServiceImpl.getUser(userId))
    }.flowOn(Dispatchers.IO)
}
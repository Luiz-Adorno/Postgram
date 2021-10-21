package com.example.postgram.utils

import com.example.postgram.models.PostListItem

sealed class PostApiState{
    object Loading : PostApiState()
    class Failure(val msg:Throwable) : PostApiState()
    class Success(val data:List<PostListItem>) : PostApiState()
    object Empty : PostApiState()
}

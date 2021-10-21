package com.example.postgram.utils

import com.example.postgram.models.PostListItem

sealed class ApiState{
    object Loading : ApiState()
    class Failure(val msg:Throwable) : ApiState()
    class Success(val data:List<PostListItem>) : ApiState()
    object Empty : ApiState()
}

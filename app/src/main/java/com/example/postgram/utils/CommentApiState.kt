package com.example.postgram.utils

import com.example.postgram.models.CommentItem

sealed class CommentApiState{
    object Loading : CommentApiState()
    class Failure(val msg:Throwable) : CommentApiState()
    class Success(val data:List<CommentItem>) : CommentApiState()
    object Empty : CommentApiState()
}

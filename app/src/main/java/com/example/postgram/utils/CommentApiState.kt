package com.example.postgram.utils

/**
 * Created by Luiz Adorno on October 21, 2021
 * Linkedln profile https://www.linkedin.com/in/luiz-adorno/
 */

import com.example.postgram.models.CommentItem

sealed class CommentApiState{
    object Loading : CommentApiState()
    class Failure(val msg:Throwable) : CommentApiState()
    class Success(val data:List<CommentItem>) : CommentApiState()
    object Empty : CommentApiState()
}

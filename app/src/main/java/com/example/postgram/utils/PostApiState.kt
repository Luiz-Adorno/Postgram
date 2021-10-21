package com.example.postgram.utils

/**
 * Created by Luiz Adorno on October 21, 2021
 * Linkedln profile https://www.linkedin.com/in/luiz-adorno/
 */

import com.example.postgram.models.PostListItem

sealed class PostApiState{
    object Loading : PostApiState()
    class Failure(val msg:Throwable) : PostApiState()
    class Success(val data:List<PostListItem>) : PostApiState()
    object Empty : PostApiState()
}

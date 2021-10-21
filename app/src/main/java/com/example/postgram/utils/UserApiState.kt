package com.example.postgram.utils

import com.example.postgram.models.UserItem

sealed class UserApiState{
    object Loading : UserApiState()
    class Failure(val msg:Throwable) : UserApiState()
    class Success(val data: List<UserItem>) : UserApiState()
    object Empty : UserApiState()
}

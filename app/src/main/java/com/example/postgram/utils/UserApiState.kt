package com.example.postgram.utils

/**
 * Created by Luiz Adorno on October 21, 2021
 * Linkedln profile https://www.linkedin.com/in/luiz-adorno/
 */

import com.example.postgram.models.UserItem

sealed class UserApiState{
    object Loading : UserApiState()
    class Failure(val msg:Throwable) : UserApiState()
    class Success(val data: List<UserItem>) : UserApiState()
    object Empty : UserApiState()
}

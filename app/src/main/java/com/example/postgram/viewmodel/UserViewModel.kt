package com.example.postgram.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.postgram.repository.Repository
import com.example.postgram.utils.UserApiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel
@Inject
constructor(
    private val repository: Repository,
) : ViewModel() {

    private val userStateFlow: MutableStateFlow<UserApiState> =
        MutableStateFlow(UserApiState.Empty)

    val _userStateFlow: StateFlow<UserApiState> = userStateFlow

    fun getUser(postId: Int) = viewModelScope.launch {
        userStateFlow.value = UserApiState.Loading

        repository.getUser(postId).catch { e ->
            userStateFlow.value = UserApiState.Failure(e)
        }.collect { data ->
            userStateFlow.value = UserApiState.Success(data)
        }

    }
}
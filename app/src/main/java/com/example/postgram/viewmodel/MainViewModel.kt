package com.example.postgram.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.postgram.repository.Repository
import com.example.postgram.utils.PostApiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel
@Inject
constructor(
    private val repository: Repository
): ViewModel() {

    private val postStateFlow: MutableStateFlow<PostApiState> = MutableStateFlow(PostApiState.Empty)

    val _postStateFlow: StateFlow<PostApiState> = postStateFlow

    fun getPost() = viewModelScope.launch {
        postStateFlow.value = PostApiState.Loading

        repository.getPost().catch { e ->
            postStateFlow.value = PostApiState.Failure(e)
        }.collect { data ->
            postStateFlow.value = PostApiState.Success(data)
        }
    }
}
package com.example.postgram.viewmodel

/**
 * Created by Luiz Adorno on October 21, 2021
 * Linkedln profile https://www.linkedin.com/in/luiz-adorno/
 */

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.postgram.repository.Repository
import com.example.postgram.utils.CommentApiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CommentViewModel
@Inject
constructor(
    private val repository: Repository,
) : ViewModel() {

    private val commentStateFlow: MutableStateFlow<CommentApiState> =
        MutableStateFlow(CommentApiState.Empty)

    val _commentStateFlow: StateFlow<CommentApiState> = commentStateFlow

    fun getComment(postId: Int) = viewModelScope.launch {
        commentStateFlow.value = CommentApiState.Loading

        repository.getComment(postId).catch { e ->
            commentStateFlow.value = CommentApiState.Failure(e)
        }.collect { data ->
            commentStateFlow.value = CommentApiState.Success(data)
        }

    }
}
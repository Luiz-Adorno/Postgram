package com.example.postgram.viewmodel

/**
 * Created by Luiz Adorno on October 21, 2021
 * Linkedln profile https://www.linkedin.com/in/luiz-adorno/
 */

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.postgram.repository.Repository
import com.example.postgram.utils.PostFactory
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MainViewModelTest{

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val repository = mockk<Repository>()

    val dispatcher = TestCoroutineDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when viewmodel fetches data then it should call the repository`(){
        val viewModel = instantiateViewModel()

        val mockedList = PostFactory.post
        every { repository.getPost() } returns MutableStateFlow(mockedList)

        viewModel.getPost()

        verify { repository. getPost() }

    }


    private fun instantiateViewModel(): MainViewModel{
        return MainViewModel(repository)
    }

}

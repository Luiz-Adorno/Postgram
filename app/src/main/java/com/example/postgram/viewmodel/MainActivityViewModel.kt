package com.example.postgram.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.postgram.models.PostListItem
import com.example.postgram.network.AppModule
import com.example.postgram.network.RetroService
import com.example.postgram.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel
@Inject
constructor(
private val retroService: RetroService
) : ViewModel() {
    var postListLiveData: MutableLiveData<List<PostListItem>> = MutableLiveData()

    fun getPostListObserver(): MutableLiveData<List<PostListItem>> {
        return postListLiveData
    }

    fun makeApiCall() {
        viewModelScope.launch(Dispatchers.IO) {
            val retroInstance = AppModule.provideRetrofitInstance(Constants.BASE_URL)
            val call: Call<List<PostListItem>> = retroInstance.getPosts

            call.enqueue(object : Callback<List<PostListItem>?> {
                override fun onResponse(
                    call: Call<List<PostListItem>?>,
                    response: Response<List<PostListItem>?>
                ) {
                    val resp: List<PostListItem>? = response.body()
                    postListLiveData.postValue(resp!!)
                }

                override fun onFailure(call: Call<List<PostListItem>?>, t: Throwable) {
                    Log.d("TAG", "onFailure: fail message: ${t.message}")
                }
            })
        }
    }
}

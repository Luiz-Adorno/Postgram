package com.example.postgram.utils

/**
 * Created by Luiz Adorno on October 21, 2021
 * Linkedln profile https://www.linkedin.com/in/luiz-adorno/
 */

import com.google.gson.GsonBuilder

import com.google.gson.Gson

object Utils {
    private var gson: Gson? = null

    fun getGsonParser(): Gson {
        if (null == gson) {
            val builder = GsonBuilder()
            gson = builder.create()
        }
        return gson!!
    }
}

package com.example.postgram.utils

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

package com.example.postgram.models

/**
 * Created by Luiz Adorno on October 21, 2021
 * Linkedln profile https://www.linkedin.com/in/luiz-adorno/
 */

data class Address(
    val city: String,
    val geo: Geo,
    val street: String,
    val suite: String,
    val zipcode: String
)
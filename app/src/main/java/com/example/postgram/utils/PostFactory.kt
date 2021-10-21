package com.example.postgram.utils

/**
 * Created by Luiz Adorno on October 21, 2021
 * Linkedln profile https://www.linkedin.com/in/luiz-adorno/
 */

import com.example.postgram.models.PostListItem

object PostFactory {
    val post = listOf(
        PostListItem(
            body = "quia et suscipit nsuscipit recusandae consequuntur expedita et cum nreprehenderit molestiae ut ut quas",
            id = 1,
            title = "sunt aut facere repellat provident occaecati excepturi",
            userId = 1
        ),
        PostListItem(
            body = "quia et suscipit nsuscipit recusandae consequuntur expedita et cum nreprehenderit molestiae ut ut quas",
            id = 2,
            title = "sunt aut facere repellat provident occaecati excepturi",
            userId = 2
        )
    )
}
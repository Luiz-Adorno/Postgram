package com.example.postgram.utils

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
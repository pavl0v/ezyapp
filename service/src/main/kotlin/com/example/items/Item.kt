package com.example.items

import java.util.UUID

data class Item(
    val id: UUID = UUID.randomUUID(),
    val name: String,
) {
    fun toGetItemResponse(): GetItemResponse {
        return GetItemResponse(
            id = this.id,
            name = this.name,
        )
    }
}

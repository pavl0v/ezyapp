package com.example.items

import java.util.UUID

data class Item(
    val id: UUID = UUID.randomUUID(),
    val name: String,
)

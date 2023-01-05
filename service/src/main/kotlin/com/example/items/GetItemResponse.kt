package com.example.items

import io.micronaut.serde.annotation.Serdeable
import java.util.UUID

@Serdeable
data class GetItemResponse(
    val id: UUID,
    val name: String,
)

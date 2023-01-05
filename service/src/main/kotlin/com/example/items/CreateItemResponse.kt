package com.example.items

import io.micronaut.serde.annotation.Serdeable
import java.util.UUID

@Serdeable
data class CreateItemResponse(
    val id: UUID
)

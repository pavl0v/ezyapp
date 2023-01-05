package com.example.items

import com.fasterxml.jackson.annotation.JsonProperty
import io.micronaut.serde.annotation.Serdeable

@Serdeable
data class CreateItemRequest(
    @JsonProperty("item_name")
    @field:JsonProperty("item_name")
    val name: String
) {
    fun toItem(): Item {
        return Item(
            name = this.name
        )
    }
}

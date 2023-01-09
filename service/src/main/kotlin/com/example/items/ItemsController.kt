package com.example.items

import com.example.common.PgClient
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.inject.Inject
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.util.UUID

@Tag(name = "items")
@Controller("/api/items")
class ItemsController(
    @Inject private val client: PgClient
) {
    private val logger: Logger = LoggerFactory.getLogger(ItemsController::class.java)

    @Get("{id}")
    suspend fun getItem(id: String): GetItemResponse {
        logger.info("Item id: $id")

        return try {
            client.query(
                ItemSpec(id = UUID.fromString(id))
            ).first().toGetItemResponse()
        } catch (ex: RuntimeException) {
            logger.error(ex.message, ex)
            throw ex
        }
    }

    @Post()
    suspend fun createItem(@Body request: CreateItemRequest): CreateItemResponse {
        try {
            val item = request.toItem()
            client.execute(NewItemSpec(item))
            return CreateItemResponse(item.id)
        } catch (ex: RuntimeException) {
            logger.error(ex.message, ex)
            throw ex
        }
    }
}

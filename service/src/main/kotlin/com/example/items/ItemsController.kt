package com.example.items

import com.example.common.PgClient
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import jakarta.inject.Inject
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.util.UUID

@Controller("/api/items")
class ItemsController(
    @Inject private val client: PgClient
) {
    private val logger: Logger = LoggerFactory.getLogger(ItemsController::class.java)

    @Get("{id}")
    suspend fun getItem(id: String): List<Item> {
        logger.info("Item id: $id")

        return try {
            client.query(ItemSpec(id = UUID.fromString(id)))
        } catch (ex: Exception) {
            println(ex)
            emptyList()
        }
    }

    @Get("/create")
    suspend fun createItem() {
        val item1 = Item(name = "name1-" + UUID.randomUUID().toString().take(4))
        val item2 = Item(name = "name2-" + UUID.randomUUID().toString().take(4))
        val item3 = Item(name = "name3-" + UUID.randomUUID().toString().take(4))

        val spec1 = NewItemSpec(item1)
        val spec2 = NewItemSpec(item2)
        val spec3 = NewItemSpec(item3)

        client.executeTransaction(spec1, spec2, spec3)
    }
}

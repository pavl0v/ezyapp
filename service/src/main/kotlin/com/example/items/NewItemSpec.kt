package com.example.items

import com.example.common.ExecuteSpec
import io.vertx.reactivex.sqlclient.Tuple

class NewItemSpec(item: Item) : ExecuteSpec {
    override val sql: String =
        "insert into items(id, name) values($1, $2)"
    override val parameters: Tuple = Tuple.tuple(
        listOf<Any>(
            item.id,
            item.name
        )
    )
}

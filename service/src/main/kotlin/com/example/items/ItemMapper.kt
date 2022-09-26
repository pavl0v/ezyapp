package com.example.items

import com.example.common.QueryMapper
import io.vertx.reactivex.sqlclient.Row
import io.vertx.reactivex.sqlclient.RowSet

class ItemMapper : QueryMapper<Item>() {
    override fun map(rows: RowSet<Row>): List<Item> {
        return rows.map { row ->
            Item(
                id = row.getUUID("id"),
                name = row.getString("name")
            )
        }.toList()
    }
}

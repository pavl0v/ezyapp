package com.example.items

import com.example.common.QueryMapper
import com.example.common.QuerySpec
import io.vertx.reactivex.sqlclient.Tuple
import java.util.UUID

class ItemSpec(id: UUID) : QuerySpec<Item>() {
    override val mapper: QueryMapper<Item> = ItemMapper()
    override val sql: String = "select * from public.items where id=$1"
    override val parameters: Tuple = Tuple.tuple(listOf(id))
}

package com.example.common

import io.vertx.reactivex.sqlclient.Tuple

interface QuerySpec<T> {
    val sql: String
    val parameters: Tuple
    val mapper: QueryMapper<T>
}

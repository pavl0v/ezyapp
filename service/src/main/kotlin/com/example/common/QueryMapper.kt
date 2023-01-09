package com.example.common

import io.vertx.reactivex.sqlclient.Row
import io.vertx.reactivex.sqlclient.RowSet

interface QueryMapper<T> {
    fun map(rows: RowSet<Row>): List<T>
}

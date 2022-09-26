package com.example.common

import io.vertx.reactivex.sqlclient.Row
import io.vertx.reactivex.sqlclient.RowSet

abstract class QueryMapper<T> {
    abstract fun map(rows: RowSet<Row>): List<T>
}

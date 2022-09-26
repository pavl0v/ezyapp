package com.example.common

import io.vertx.reactivex.sqlclient.Tuple

abstract class ExecuteSpec {
    abstract val sql: String
    abstract val parameters: Tuple
}

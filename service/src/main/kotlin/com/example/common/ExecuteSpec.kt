package com.example.common

import io.vertx.reactivex.sqlclient.Tuple

interface ExecuteSpec {
    val sql: String
    val parameters: Tuple
}

package com.example.common

import io.reactivex.Maybe
import io.reactivex.Single
import io.vertx.reactivex.pgclient.PgPool
import io.vertx.reactivex.sqlclient.SqlConnection
import io.vertx.reactivex.sqlclient.SqlResult
import jakarta.inject.Inject
import jakarta.inject.Singleton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Singleton
class PgClient(
    @Inject
    private val pgPool: PgPool
) {
    suspend fun <T> query(spec: QuerySpec<T>): List<T> {
        return withContext(Dispatchers.IO) {
            pgPool.preparedQuery(spec.sql).rxExecute(spec.parameters).map {
                spec.mapper.map(it)
            }.toDeferred(this)
        }
    }

    suspend fun execute(spec: ExecuteSpec): Int {
        return withContext(Dispatchers.IO) {
            pgPool.preparedQuery(spec.sql).rxExecute(spec.parameters).map {
                it.rowCount()
            }.toDeferred(this)
        }
    }

    suspend fun executeTransaction(specs: List<ExecuteSpec>): Int? {
        return withContext(Dispatchers.IO) {
            pgPool.rxWithTransaction(fun(tx: SqlConnection): Maybe<Int> {
                val sources = specs.map { tx.preparedQuery(it.sql).rxExecute(it.parameters) }
                return Single.zip(sources, fun(results: Array<Any>): Int {
                    return results.sumOf { (it as SqlResult<*>).rowCount() }
                }).toMaybe()
            }).toDeferred(this)
        }
    }
}

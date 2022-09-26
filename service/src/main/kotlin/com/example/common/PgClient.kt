package com.example.common

import io.reactivex.Maybe
import io.reactivex.Single
import io.vertx.reactivex.pgclient.PgPool
import io.vertx.reactivex.sqlclient.Row
import io.vertx.reactivex.sqlclient.RowSet
import io.vertx.reactivex.sqlclient.SqlConnection
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

    fun executeTransaction(spec1: ExecuteSpec, spec2: ExecuteSpec, spec3: ExecuteSpec) {
        val f = listOf<ExecuteSpec>(
            spec1, spec2, spec3
        )

        pgPool.rxWithTransaction(fun(tx: SqlConnection): Maybe<RowSet<Row>> {
            val lst = f.flatMap {
                listOf(tx.preparedQuery(it.sql).rxExecute(it.parameters))
            }
            val res = Single.zip(lst, fun(arr: Array<Any>): RowSet<Row> {
                return arr[arr.lastIndex] as RowSet<Row>
            }).toMaybe()
            return res

//            return tx.preparedQuery(spec1.sql).rxExecute(spec1.parameters).flatMap {
//                tx.preparedQuery(spec2.sql).rxExecute(spec2.parameters).flatMap {
//                    tx.preparedQuery(spec3.sql).rxExecute(spec3.parameters)
//                }
//            }
//            .toMaybe()
        }).blockingGet()
    }
}

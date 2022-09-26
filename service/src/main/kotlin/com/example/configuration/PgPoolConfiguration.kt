package com.example.configuration

import io.micronaut.context.annotation.Factory
import io.vertx.pgclient.PgConnectOptions
import io.vertx.reactivex.pgclient.PgPool
import io.vertx.sqlclient.PoolOptions
import jakarta.inject.Singleton

@Factory
class PgPoolConfiguration {
        @Singleton
        fun client(): PgPool {
            val connectOptions = PgConnectOptions()
                .setPort(5432)
                .setHost("127.0.0.1")
                .setDatabase("ezyapp")
                .setUser("postgres")
                .setPassword("postgres")

            // Pool options
            val poolOptions = PoolOptions()
                .setMaxSize(5)
            return PgPool.pool(connectOptions, poolOptions)
        }
}

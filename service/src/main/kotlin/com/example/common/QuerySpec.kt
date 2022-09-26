package com.example.common

import io.vertx.reactivex.sqlclient.Tuple
//import java.lang.IllegalStateException

abstract class QuerySpec<T> {
    abstract val sql: String
    abstract val parameters: Tuple
    abstract val mapper: QueryMapper<T>

//    fun toTuple(): Tuple {
//        val requiredParameters = sql.split(" ").filter { it.contains("=$") }
//            .map { toPair(it) }.sortedBy { it.second }.map { it.first }
//        val tupleElements = mutableListOf<Any>()
//        val missedParameters = mutableListOf<String>()
//        requiredParameters.forEach {
//            if (parameters.containsKey(it)) {
//                tupleElements.add(parameters[it]!!)
//            } else {
//                missedParameters.add(it)
//            }
//        }
//        if (missedParameters.isNotEmpty()) {
//            throw IllegalStateException("SQL parameters missed: "
//                    + missedParameters.joinToString(", "))
//        }
//        return Tuple.tuple(tupleElements)
//    }
//
//    private fun toPair(s: String): Pair<String, Int> {
//        val lst = s.split("=$")
//        return Pair<String, Int>(lst[0], lst[1].toInt())
//    }
}

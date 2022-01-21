package com.example.myapplication.utils

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

class Utils {

    companion object {
        fun ordinal(i: Int): String {
            val suffixes = arrayOf("th", "st", "nd", "rd", "th", "th", "th", "th", "th", "th")
            return when (i % 100) {
                11, 12, 13 -> i.toString() + "th"
                else -> i.toString() + suffixes[i % 10]
            }
        }

        inline fun <reified T : Any> convertStringToListObject(obj: String): List<T> {
            return try {
                val type = Types.newParameterizedType(MutableList::class.java, T::class.java)
                val jsonAdapter: JsonAdapter<List<T>> = Moshi.Builder().build().adapter(type)
                return jsonAdapter.fromJson(obj) ?: listOf()
            } catch (e: Exception) {
                listOf()
            }
        }

        inline fun <reified T : Any> convertStringToObject(obj: String): T? {
            return try {
                val jsonAdapter: JsonAdapter<T> = Moshi.Builder().build().adapter(T::class.java)
                jsonAdapter.fromJson(obj)
            } catch (e: Exception) {
                null
            }
        }
    }
}
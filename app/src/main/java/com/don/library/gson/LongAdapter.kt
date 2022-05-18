package com.don.library.gson

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter

class LongAdapter : TypeAdapter<Long>() {
    override fun write(out: JsonWriter?, value: Long?) {
        out?.value(value ?: 0L)
    }

    override fun read(`in`: JsonReader?): Long {
        `in`?.apply {
            try {
                if (peek() === JsonToken.NULL) {
                    nextNull()
                    return 0L
                }
                return nextLong()
            } catch (e: Exception) {
            }
        }
        return 0L
    }

}
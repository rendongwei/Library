package com.don.library.gson

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter

class IntAdapter : TypeAdapter<Int>() {
    override fun write(out: JsonWriter?, value: Int?) {
        out?.value(value ?: 0)
    }

    override fun read(`in`: JsonReader?): Int {
        `in`?.apply {
            try {
                if (peek() === JsonToken.NULL) {
                    nextNull()
                    return 0
                }
                return nextInt()
            } catch (e: Exception) {
            }
        }
        return 0
    }

}
package com.don.library.gson

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter

class BooleanAdapter : TypeAdapter<Boolean>() {
    override fun write(out: JsonWriter?, value: Boolean?) {
        out?.value(value ?: false)
    }

    override fun read(`in`: JsonReader?): Boolean {
        `in`?.apply {
            try {
                if (peek() === JsonToken.NULL) {
                    nextNull()
                    return false
                }
                return nextBoolean()
            } catch (e: Exception) {
            }
        }
        return false
    }

}
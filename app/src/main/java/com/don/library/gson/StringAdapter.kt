package com.don.library.gson

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter

class StringAdapter : TypeAdapter<String>() {

    override fun write(out: JsonWriter?, value: String?) {
        out?.value(if (value.isNullOrEmpty()) {
            ""
        } else {
            value
        })
    }

    override fun read(`in`: JsonReader?): String {
        `in`?.apply {
            try {
                if (peek() === JsonToken.NULL) {
                    nextNull()
                    return ""
                }
                return nextString()
            } catch (e: Exception) {
            }
        }
        return ""
    }
}
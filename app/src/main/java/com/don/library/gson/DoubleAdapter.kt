package com.don.library.gson

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter

class DoubleAdapter : TypeAdapter<Double>() {
    override fun write(out: JsonWriter?, value: Double?) {
        out?.value(value ?: 0.0)
    }

    override fun read(`in`: JsonReader?): Double {
        `in`?.apply {
            try {
                if (peek() === JsonToken.NULL) {
                    nextNull()
                    return 0.0
                }
                return nextDouble()
            } catch (e: Exception) {
            }
        }
        return 0.0
    }

}
package com.don.library.gson

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter

class FloatAdapter : TypeAdapter<Float>() {
    override fun write(out: JsonWriter?, value: Float?) {
        out?.value(value ?: 0F)
    }

    override fun read(`in`: JsonReader?): Float {
        `in`?.apply {
            try {
                if (peek() === JsonToken.NULL) {
                    nextNull()
                    return 0F
                }
                return nextDouble().toFloat()
            } catch (e: Exception) {
            }
        }
        return 0F
    }

}
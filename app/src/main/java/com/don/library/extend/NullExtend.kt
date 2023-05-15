package com.don.library.extend


fun String?.value(default: String = ""): String {
    return this ?: default
}

fun Int?.value(default: Int = 0): Int {
    return this ?: default
}

fun Boolean?.value(default: Boolean = false): Boolean {
    return this ?: default
}

fun Float?.value(default: Float = 0f): Float {
    return this ?: default
}

fun Double?.value(default: Double = 0.0): Double {
    return this ?: default
}

fun Byte?.value(default: Byte = 0): Byte {
    return this ?: default
}
package com.don.library.extend

fun String.notEmpty(block: (String) -> Unit, empty: () -> Unit) {
    if (isNotEmpty()) {
        block(this)
    } else {
        empty()
    }
}

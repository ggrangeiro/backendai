package com.fit.utils

import java.security.MessageDigest

fun String.sha256Hex(): String {
    val bytes = MessageDigest.getInstance("SHA-256").digest(this.toByteArray())
    return bytes.joinToString("") { "%02x".format(it) }
}
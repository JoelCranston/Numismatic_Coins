package io.ktor.http

internal class InvalidCookieDateException(data: String, reason: String) : IllegalStateException("Failed to parse date string: \"$data\". Reason: \"$reason"")

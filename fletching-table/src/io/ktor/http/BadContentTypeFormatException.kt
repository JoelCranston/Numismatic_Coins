package io.ktor.http

public class BadContentTypeFormatException(value: String) : Exception("Bad Content-Type format: $value")

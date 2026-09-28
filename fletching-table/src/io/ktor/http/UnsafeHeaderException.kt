package io.ktor.http

public class UnsafeHeaderException(header: String) : IllegalArgumentException("Header(s) $header are controlled by the engine and cannot be set explicitly")

package io.ktor.http

public class URLParserException(urlString: String, cause: Throwable) : IllegalStateException("Fail to parse url: $urlString", cause)

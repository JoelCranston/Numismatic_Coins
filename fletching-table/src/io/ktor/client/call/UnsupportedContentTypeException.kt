package io.ktor.client.call

import io.ktor.http.content.OutgoingContent

public class UnsupportedContentTypeException(content: OutgoingContent) : IllegalStateException("Failed to write body: ${content.getClass()::class}")

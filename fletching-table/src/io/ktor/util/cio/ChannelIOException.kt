package io.ktor.util.cio

import java.io.IOException

public open class ChannelIOException(message: String, exception: Throwable) : IOException(message, exception)

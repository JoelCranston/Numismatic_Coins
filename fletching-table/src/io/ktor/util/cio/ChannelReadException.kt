package io.ktor.util.cio

public class ChannelReadException(message: String = "Cannot read from a channel", exception: Throwable) : ChannelIOException(message, exception)

package io.ktor.util.cio

public class ChannelWriteException(message: String = "Cannot write to channel", exception: Throwable) : ChannelIOException(message, exception)

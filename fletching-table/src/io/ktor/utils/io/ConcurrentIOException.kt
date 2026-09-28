package io.ktor.utils.io

public class ConcurrentIOException(taskName: String, cause: Throwable? = null) : IllegalStateException("Concurrent $taskName attempts", cause)

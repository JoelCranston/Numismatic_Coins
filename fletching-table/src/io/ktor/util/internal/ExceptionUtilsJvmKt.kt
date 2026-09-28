package io.ktor.util.internal

public fun Throwable.initCauseBridge(cause: Throwable) {
   `$this$initCauseBridge`.initCause(cause);
}

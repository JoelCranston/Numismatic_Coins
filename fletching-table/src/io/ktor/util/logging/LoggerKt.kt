package io.ktor.util.logging

import org.slf4j.Logger

public fun Logger.error(exception: Throwable) {
   var var10001: java.lang.String = exception.getMessage();
   if (var10001 == null) {
      var10001 = "Exception of type ${exception.getClass()::class}";
   }

   `$this$error`.error(var10001, exception);
}

public inline fun Logger.trace(message: () -> String) {
   if (LoggerJvmKt.isTraceEnabled(`$this$trace`)) {
      `$this$trace`.trace(message.invoke() as java.lang.String);
   }
}

public inline fun Logger.debug(message: () -> String) {
   if (LoggerJvmKt.isDebugEnabled(`$this$debug`)) {
      `$this$debug`.debug(message.invoke() as java.lang.String);
   }
}

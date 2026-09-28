package io.ktor.util

import io.ktor.utils.io.InternalAPI

@InternalAPI
public final val rootCause: Throwable?
   public final get() {
      var rootCause: java.lang.Throwable = `$this$rootCause`;

      while ((rootCause != null ? rootCause.getCause() : null) != null) {
         rootCause = rootCause.getCause();
      }

      return rootCause;
   }


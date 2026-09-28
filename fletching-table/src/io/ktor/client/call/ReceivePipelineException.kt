package io.ktor.client.call

import io.ktor.util.reflect.TypeInfo

public class ReceivePipelineException(request: HttpClientCall, info: TypeInfo, cause: Throwable) : IllegalStateException("Fail to run receive pipeline: $cause") {
   public final val request: HttpClientCall
   public final val info: TypeInfo
   public open val cause: Throwable

   init {
      this.request = request;
      this.info = info;
      this.cause = cause;
   }
}

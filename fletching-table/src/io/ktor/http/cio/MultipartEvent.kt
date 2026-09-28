package io.ktor.http.cio

import io.ktor.utils.io.ByteReadChannel
import kotlinx.coroutines.Deferred
import kotlinx.io.Source

public sealed class MultipartEvent protected constructor() {
   public abstract fun release() {
   }

   public class Epilogue(body: Source) : MultipartEvent() {
      public final val body: Source

      init {
         this.body = body;
      }

      public override fun release() {
         this.body.close();
      }
   }

   public class MultipartPart(headers: Deferred<HttpHeadersMap>, body: ByteReadChannel) : MultipartEvent() {
      public final val headers: Deferred<HttpHeadersMap>
      public final val body: ByteReadChannel

      init {
         this.headers = headers;
         this.body = body;
      }

      public override fun release() {
         this.headers.invokeOnCompletion(MultipartEvent.MultipartPart::release$lambda$0);
         MultipartJvmAndPosixKt.discardBlocking(this.body);
      }

      @JvmStatic
      fun `release$lambda$0`(`this$0`: MultipartEvent.MultipartPart, t: java.lang.Throwable): Unit {
         if (t != null) {
            `this$0`.headers.getCompleted().release();
         }

         return Unit.INSTANCE;
      }
   }

   public class Preamble(body: Source) : MultipartEvent() {
      public final val body: Source

      init {
         this.body = body;
      }

      public override fun release() {
         this.body.close();
      }
   }
}

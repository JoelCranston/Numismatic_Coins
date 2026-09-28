package io.ktor.utils.io

import java.io.IOException
import kotlin.coroutines.jvm.internal.Boxing
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.io.Source

@SourceDebugExtension(["SMAP\nSourceByteReadChannel.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SourceByteReadChannel.kt\nio/ktor/utils/io/SourceByteReadChannel\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,41:1\n1#2:42\n*E\n"])
internal class SourceByteReadChannel(source: Source) : ByteReadChannel {
   private final val source: Source
   private final var closed: CloseToken?

   public open val closedCause: Throwable?
      public open get() {
         return if (this.closed != null) CloseToken.wrapCause$default(this.closed, null, 1, null) else null;
      }


   public open val isClosedForRead: Boolean
      public open get() {
         return this.source.exhausted();
      }


   @InternalAPI
   public open val readBuffer: Source
      public open get() {
         val var10000: java.lang.Throwable = this.getClosedCause();
         if (var10000 != null) {
            throw var10000;
         } else {
            return this.source.getBuffer();
         }
      }


   init {
      this.source = source;
   }

   public override suspend fun awaitContent(min: Int): Boolean {
      val var10000: java.lang.Throwable = this.getClosedCause();
      if (var10000 != null) {
         throw var10000;
      } else {
         return Boxing.boxBoolean(this.source.request((long)min));
      }
   }

   public override fun cancel(cause: Throwable?) {
      if (this.closed == null) {
         var var10001: CloseToken;
         var var10003: IOException;
         var var10005: java.lang.String;
         label14: {
            this.source.close();
            var10001 = new CloseToken;
            var10003 = new IOException;
            if (cause != null) {
               var10005 = cause.getMessage();
               if (var10005 != null) {
                  break label14;
               }
            }

            var10005 = "Channel was cancelled";
         }

         var10003./* $VF: Unable to resugar constructor */<init>(var10005, cause);
         var10001./* $VF: Unable to resugar constructor */<init>(var10003);
         this.closed = var10001;
      }
   }
}

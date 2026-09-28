package io.ktor.utils.io.jvm.javaio

import io.ktor.utils.io.ByteReadChannel
import io.ktor.utils.io.CloseToken
import io.ktor.utils.io.InternalAPI
import io.ktor.utils.io.core.ByteReadPacketKt
import io.ktor.utils.io.jvm.javaio.RawSourceChannel.awaitContent.1
import io.ktor.utils.io.jvm.javaio.RawSourceChannel.awaitContent.2
import java.io.IOException
import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.intrinsics.IntrinsicsKt
import kotlin.coroutines.jvm.internal.Boxing
import kotlin.jvm.functions.Function2
import kotlinx.coroutines.BuildersKt
import kotlinx.coroutines.CompletableJob
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.Job
import kotlinx.coroutines.JobKt
import kotlinx.io.Buffer
import kotlinx.io.RawSource
import kotlinx.io.Source

internal class RawSourceChannel(source: RawSource, parent: CoroutineContext) : ByteReadChannel {
   private final val source: RawSource
   private final val parent: CoroutineContext
   private final var closedToken: CloseToken?
   private final val buffer: Buffer

   public open val closedCause: Throwable?
      public open get() {
         return if (this.closedToken != null) CloseToken.wrapCause$default(this.closedToken, null, 1, null) else null;
      }


   public open val isClosedForRead: Boolean
      public open get() {
         return this.closedToken != null && this.buffer.exhausted();
      }


   public final val job: CompletableJob
   public final val coroutineContext: CoroutineContext

   @InternalAPI
   public open val readBuffer: Source
      public open get() {
         return this.buffer;
      }


   init {
      this.source = source;
      this.parent = parent;
      this.buffer = new Buffer();
      this.job = JobKt.Job(this.parent.get(Job.Key));
      this.coroutineContext = this.parent.plus(this.job).plus(new CoroutineName("RawSourceChannel"));
   }

   public override suspend fun awaitContent(min: Int): Boolean {
      var `$continuation`: Continuation;
      label29: {
         if (`$completion` is 1) {
            `$continuation` = `$completion` as 1;
            if (((`$completion` as 1).label and Integer.MIN_VALUE) != 0) {
               `$continuation`.label -= Integer.MIN_VALUE;
               break label29;
            }
         }

         `$continuation` = new 1(this, `$completion`);
      }

      val `$result`: Any = `$continuation`.result;
      val var5: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
      switch ($continuation.label) {
         case 0:
            ResultKt.throwOnFailure(`$result`);
            if (this.closedToken != null) {
               return Boxing.boxBoolean(true);
            }

            val var10000: CoroutineContext = this.coroutineContext;
            val var10001: Function2 = new 2(this, min, null);
            `$continuation`.I$0 = min;
            `$continuation`.label = 1;
            if (BuildersKt.withContext(var10000, var10001, `$continuation`) === var5) {
               return var5;
            }
            break;
         case 1:
            min = `$continuation`.I$0;
            ResultKt.throwOnFailure(`$result`);
            break;
         default:
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
      }

      return Boxing.boxBoolean(ByteReadPacketKt.getRemaining(this.buffer) >= (long)min);
   }

   public override fun cancel(cause: Throwable?) {
      if (this.closedToken == null) {
         var var10000: Job;
         var var10001: java.lang.String;
         label22: {
            var10000 = this.job;
            if (cause != null) {
               var10001 = cause.getMessage();
               if (var10001 != null) {
                  break label22;
               }
            }

            var10001 = "Channel was cancelled";
         }

         var var10003: IOException;
         var var10005: java.lang.String;
         label17: {
            JobKt.cancel(var10000, var10001, cause);
            this.source.close();
            var2 = new CloseToken;
            var10003 = new IOException;
            if (cause != null) {
               var10005 = cause.getMessage();
               if (var10005 != null) {
                  break label17;
               }
            }

            var10005 = "Channel was cancelled";
         }

         var10003./* $VF: Unable to resugar constructor */<init>(var10005, cause);
         var2./* $VF: Unable to resugar constructor */<init>(var10003);
         this.closedToken = var2;
      }
   }
}

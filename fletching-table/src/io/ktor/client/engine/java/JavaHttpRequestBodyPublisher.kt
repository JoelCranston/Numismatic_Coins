package io.ktor.client.engine.java

import io.ktor.client.engine.java.JavaHttpRequestBodyPublisher.ReadableByteChannelSubscription.readData.1
import io.ktor.utils.io.ByteReadChannel
import io.ktor.utils.io.ByteReadChannelKt
import java.net.http.HttpRequest.BodyPublisher
import java.nio.ByteBuffer
import java.util.concurrent.Flow.Subscriber
import java.util.concurrent.Flow.Subscription
import kotlin.coroutines.CoroutineContext
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.coroutines.BuildersKt
import kotlinx.coroutines.CoroutineScope

@SourceDebugExtension(["SMAP\nJavaHttpRequestBodyPublisher.kt\nKotlin\n*S Kotlin\n*F\n+ 1 JavaHttpRequestBodyPublisher.kt\nio/ktor/client/engine/java/JavaHttpRequestBodyPublisher\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,167:1\n1#2:168\n*E\n"])
internal class JavaHttpRequestBodyPublisher(coroutineContext: CoroutineContext, contentLength: Long = -1L, getChannel: () -> ByteReadChannel) : BodyPublisher {
   private final val coroutineContext: CoroutineContext
   private final val contentLength: Long
   private final val getChannel: () -> ByteReadChannel

   init {
      this.coroutineContext = coroutineContext;
      this.contentLength = contentLength;
      this.getChannel = getChannel;
   }

   public override fun contentLength(): Long {
      return this.contentLength;
   }

   public override fun subscribe(subscriber: Subscriber<in ByteBuffer>) {
      try {
         val cause: JavaHttpRequestBodyPublisher.ReadableByteChannelSubscription = new JavaHttpRequestBodyPublisher.ReadableByteChannelSubscription(
            this.coroutineContext, this.getChannel.invoke(), subscriber
         );
         synchronized (subscription) {
            subscriber.onSubscribe(cause);
         }
      } catch (var7: java.lang.Throwable) {
         subscriber.onSubscribe(new JavaHttpRequestBodyPublisher.NullSubscription());
         subscriber.onError(var7);
      }
   }

   private class NullSubscription : Subscription {
      public override fun request(n: Long) {
      }

      public override fun cancel() {
      }
   }

   @SourceDebugExtension(["SMAP\nJavaHttpRequestBodyPublisher.kt\nKotlin\n*S Kotlin\n*F\n+ 1 JavaHttpRequestBodyPublisher.kt\nio/ktor/client/engine/java/JavaHttpRequestBodyPublisher$ReadableByteChannelSubscription\n+ 2 AtomicFU.common.kt\nkotlinx/atomicfu/AtomicFU_commonKt\n*L\n1#1,167:1\n487#2,4:168\n264#2,4:172\n*S KotlinDebug\n*F\n+ 1 JavaHttpRequestBodyPublisher.kt\nio/ktor/client/engine/java/JavaHttpRequestBodyPublisher$ReadableByteChannelSubscription\n*L\n69#1:168,4\n92#1:172,4\n*E\n"])
   private class ReadableByteChannelSubscription(coroutineContext: CoroutineContext, inputChannel: ByteReadChannel, subscriber: Subscriber<in ByteBuffer>) :
      Subscription,
      CoroutineScope {
      public open val coroutineContext: CoroutineContext
      private final val inputChannel: ByteReadChannel
      private final val subscriber: Subscriber<in ByteBuffer>

      init {
         this.coroutineContext = coroutineContext;
         this.inputChannel = inputChannel;
         this.subscriber = subscriber;
         this.outstandingDemand = 0L;
         this.writeInProgress = 0;
         this.done = 0;
      }

      public override fun request(n: Long) {
         if (this.done == 0) {
            if (n < 1L) {
               this.signalOnError(
                  new IllegalArgumentException("${this.subscriber} violated the Reactive Streams rule 3.9 by requesting a non-positive number of elements.")
               );
            } else {
               try {
                  val cause: JavaHttpRequestBodyPublisher.ReadableByteChannelSubscription = this;

                  do {
                     val `cur$iv`: Long = cause.outstandingDemand;
                  } while (
                     !outstandingDemand$FU.compareAndSet(
                        $this$getAndUpdate$iv,
                        $this$getAndUpdate$iv.outstandingDemand,
                        java.lang.Long.MAX_VALUE - $this$getAndUpdate$iv.outstandingDemand < n
                           ? java.lang.Long.MAX_VALUE
                           : $this$getAndUpdate$iv.outstandingDemand + n
                     )
                  );

                  if (writeInProgress$FU.compareAndSet(this, 0, 1)) {
                     this.readData();
                  }
               } catch (var10: java.lang.Throwable) {
                  this.signalOnError(var10);
               }
            }
         }
      }

      public override fun cancel() {
         if (done$FU.compareAndSet(this, 0, 1)) {
            this.closeChannel();
         }
      }

      private fun checkHaveMorePermits(): Boolean {
         val `$this$updateAndGet$iv`: JavaHttpRequestBodyPublisher.ReadableByteChannelSubscription = this;

         val `cur$iv`: Boolean;
         val `upd$iv`: Boolean;
         do {
            `cur$iv` = (boolean)`$this$updateAndGet$iv`.writeInProgress;
            `upd$iv` = outstandingDemand$FU.decrementAndGet(this) > 0L;
         } while (!writeInProgress$FU.compareAndSet($this$updateAndGet$iv, cur$iv, upd$iv));

         return `upd$iv`;
      }

      private fun readData() {
         if (this.inputChannel.isClosedForRead()) {
            this.tryToSignalOnErrorFromChannel();
            this.signalOnComplete();
         } else {
            BuildersKt.launch$default(this, null, null, new 1(this, null), 3, null);
         }
      }

      private fun closeChannel() {
         try {
            ByteReadChannelKt.cancel(this.inputChannel);
         } catch (var2: java.lang.Throwable) {
            this.signalOnError(var2);
         }
      }

      private fun signalOnNext(buffer: ByteBuffer) {
         if (this.done == 0) {
            this.subscriber.onNext(buffer);
         }
      }

      private fun signalOnComplete() {
         if (done$FU.compareAndSet(this, 0, 1)) {
            this.subscriber.onComplete();
         }
      }

      private fun signalOnError(cause: Throwable) {
         if (done$FU.compareAndSet(this, 0, 1)) {
            this.subscriber.onError(cause);
         }
      }

      private fun tryToSignalOnErrorFromChannel() {
         val var10000: java.lang.Throwable = this.inputChannel.getClosedCause();
         if (var10000 != null) {
            this.signalOnError(var10000);
         }
      }
   }
}

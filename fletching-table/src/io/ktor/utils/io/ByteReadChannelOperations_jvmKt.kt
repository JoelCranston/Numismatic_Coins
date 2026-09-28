@file:SourceDebugExtension(["SMAP\nByteReadChannelOperations.jvm.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ByteReadChannelOperations.jvm.kt\nio/ktor/utils/io/ByteReadChannelOperations_jvmKt\n+ 2 Builder.kt\nio/ktor/utils/io/core/BuilderKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 4 UnsafeBufferOperations.kt\nkotlinx/io/unsafe/UnsafeBufferOperations\n*L\n1#1,206:1\n196#1:211\n197#1,9:213\n21#2,3:207\n1#3:210\n1#3:212\n1#3:223\n99#4:222\n100#4,8:224\n*S KotlinDebug\n*F\n+ 1 ByteReadChannelOperations.jvm.kt\nio/ktor/utils/io/ByteReadChannelOperations_jvmKt\n*L\n100#1:211\n100#1:213,9\n20#1:207,3\n100#1:212\n159#1:223\n159#1:222\n159#1:224,8\n*E\n"])

package io.ktor.utils.io

import io.ktor.utils.io.ByteReadChannelOperations_jvmKt.readAvailable.1
import io.ktor.utils.io.ByteReadChannelOperations_jvmKt.skipDelimiter.2
import io.ktor.utils.io.core.BytePacketBuilderExtensions_jvmKt
import io.ktor.utils.io.core.ByteReadPacketExtensions_jvmKt
import java.io.EOFException
import java.nio.ByteBuffer
import java.nio.channels.SelectableChannel
import java.nio.channels.WritableByteChannel
import kotlin.coroutines.Continuation
import kotlin.coroutines.intrinsics.IntrinsicsKt
import kotlin.coroutines.jvm.internal.Boxing
import kotlin.coroutines.jvm.internal.SpillingKt
import kotlin.jvm.functions.Function1
import kotlin.jvm.internal.InlineMarker
import kotlin.jvm.internal.Ref
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.io.Buffer
import kotlinx.io.Segment
import kotlinx.io.SourcesJvmKt
import kotlinx.io.bytestring.ByteString
import kotlinx.io.unsafe.UnsafeBufferOperations

public fun ByteReadChannel(content: ByteBuffer): ByteReadChannel {
   val `builder$iv`: Buffer = new Buffer();
   BytePacketBuilderExtensions_jvmKt.writeFully(`builder$iv`, content);
   return ByteChannelCtorKt.ByteReadChannel(`builder$iv`);
}

public suspend fun ByteReadChannel.readAvailable(buffer: ByteBuffer): Int {
   var `$continuation`: Continuation;
   label38: {
      if (`$completion` is 1) {
         `$continuation` = `$completion` as 1;
         if (((`$completion` as 1).label and Integer.MIN_VALUE) != 0) {
            `$continuation`.label -= Integer.MIN_VALUE;
            break label38;
         }
      }

      `$continuation` = new 1(`$completion`);
   }

   val `$result`: Any = `$continuation`.result;
   val var5: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
   switch ($continuation.label) {
      case 0:
         ResultKt.throwOnFailure(`$result`);
         if (`$this$readAvailable`.isClosedForRead()) {
            return Boxing.boxInt(-1);
         }

         if (`$this$readAvailable`.getReadBuffer().exhausted()) {
            `$continuation`.L$0 = `$this$readAvailable`;
            `$continuation`.L$1 = buffer;
            `$continuation`.label = 1;
            if (ByteReadChannel.awaitContent$default(`$this$readAvailable`, 0, `$continuation`, 1, null) === var5) {
               return var5;
            }
         }
         break;
      case 1:
         buffer = `$continuation`.L$1 as ByteBuffer;
         `$this$readAvailable` = `$continuation`.L$0 as ByteReadChannel;
         ResultKt.throwOnFailure(`$result`);
         break;
      default:
         throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
   }

   return if (`$this$readAvailable`.isClosedForRead())
      Boxing.boxInt(-1)
      else
      Boxing.boxInt(SourcesJvmKt.readAtMostTo(`$this$readAvailable`.getReadBuffer(), buffer));
}

public fun ByteString(buffer: ByteBuffer): ByteString {
   val array: ByteArray = new byte[buffer.remaining()];
   ((java.nio.Buffer)buffer).mark();
   buffer.get(array);
   ((java.nio.Buffer)buffer).reset();
   return new ByteString(array, 0, 0, 6, null);
}

public suspend fun ByteReadChannel.copyTo(channel: WritableByteChannel, limit: Long = ...): Long {
   var `$continuation`: Continuation;
   label81: {
      if (`$completion` is io.ktor.utils.io.ByteReadChannelOperations_jvmKt.copyTo.1) {
         `$continuation` = `$completion` as io.ktor.utils.io.ByteReadChannelOperations_jvmKt.copyTo.1;
         if (((`$completion` as io.ktor.utils.io.ByteReadChannelOperations_jvmKt.copyTo.1).label and Integer.MIN_VALUE) != 0) {
            `$continuation`.label -= Integer.MIN_VALUE;
            break label81;
         }
      }

      `$continuation` = new io.ktor.utils.io.ByteReadChannelOperations_jvmKt.copyTo.1(`$completion`);
   }

   val `$result`: Any = `$continuation`.result;
   val var14: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
   var copied: Ref.LongRef;
   var copy: Function1;
   switch ($continuation.label) {
      case 0:
         ResultKt.throwOnFailure(`$result`);
         if (limit < 0L) {
            throw new IllegalArgumentException(("Limit shouldn't be negative: $limit").toString());
         }

         if (channel is SelectableChannel && !(channel as SelectableChannel).isBlocking()) {
            throw new IllegalArgumentException("Non-blocking channels are not supported");
         }

         if (`$this$copyTo`.isClosedForRead()) {
            val var28: java.lang.Throwable = `$this$copyTo`.getClosedCause();
            if (var28 != null) {
               throw var28;
            }

            return Boxing.boxLong(0L);
         }

         copied = new Ref.LongRef();
         copy = ByteReadChannelOperations_jvmKt::copyTo$lambda$2;
         break;
      case 1:
         val var24: Int = `$continuation`.I$1;
         val var19: Int = `$continuation`.I$0;
         limit = `$continuation`.J$0;
         val var22: Function1 = `$continuation`.L$5 as Function1;
         val var17: ByteReadChannel = `$continuation`.L$4 as ByteReadChannel;
         copy = `$continuation`.L$3 as Function1;
         copied = `$continuation`.L$2 as Ref.LongRef;
         channel = `$continuation`.L$1 as WritableByteChannel;
         `$this$copyTo` = `$continuation`.L$0 as ByteReadChannel;
         ResultKt.throwOnFailure(`$result`);
         if (!`$result` as java.lang.Boolean) {
            throw new EOFException("Not enough bytes available: required $var19 but ${ByteReadChannelOperationsKt.getAvailableForRead(var17)} available");
         }

         ByteReadPacketExtensions_jvmKt.read(var17.getReadBuffer(), var22);
         break;
      case 2:
         val var10: Int = `$continuation`.I$1;
         val `min$iv`: Int = `$continuation`.I$0;
         limit = `$continuation`.J$0;
         val it: Function1 = `$continuation`.L$5 as Function1;
         val `$this$read$iv`: ByteReadChannel = `$continuation`.L$4 as ByteReadChannel;
         copy = `$continuation`.L$3 as Function1;
         copied = `$continuation`.L$2 as Ref.LongRef;
         channel = `$continuation`.L$1 as WritableByteChannel;
         `$this$copyTo` = `$continuation`.L$0 as ByteReadChannel;
         ResultKt.throwOnFailure(`$result`);
         if (`$result` as java.lang.Boolean) {
            ByteReadPacketExtensions_jvmKt.read(`$this$read$iv`.getReadBuffer(), it);
         }
         break;
      default:
         throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
   }

   while (copied.element < limit && !$this$copyTo.isClosedForRead()) {
      `$continuation`.L$0 = `$this$copyTo`;
      `$continuation`.L$1 = SpillingKt.nullOutSpilledVariable(channel);
      `$continuation`.L$2 = copied;
      `$continuation`.L$3 = copy;
      `$continuation`.L$4 = `$this$copyTo`;
      `$continuation`.L$5 = copy;
      `$continuation`.J$0 = limit;
      `$continuation`.I$0 = 0;
      `$continuation`.I$1 = 0;
      `$continuation`.label = 2;
      val var10000: Any = ByteReadChannel.awaitContent$default(`$this$copyTo`, 0, `$continuation`, 1, null);
      if (var10000 === var14) {
         return var14;
      }

      if (var10000 as java.lang.Boolean) {
         ByteReadPacketExtensions_jvmKt.read(`$this$copyTo`.getReadBuffer(), copy);
      }
   }

   val var27: java.lang.Throwable = `$this$copyTo`.getClosedCause();
   if (var27 != null) {
      throw var27;
   } else {
      return Boxing.boxLong(copied.element);
   }
}

@JvmSynthetic
fun `copyTo$default`(var0: ByteReadChannel, var1: WritableByteChannel, var2: Long, var4: Continuation, var5: Int, var6: Any): Any {
   if ((var5 and 2) != 0) {
      var2 = java.lang.Long.MAX_VALUE;
   }

   return copyTo(var0, var1, var2, var4);
}

public suspend fun ByteReadChannel.skipDelimiter(delimiter: ByteBuffer) {
   val var10000: Any = skipDelimiter(`$this$skipDelimiter`, ByteString(delimiter), `$completion`);
   return if (var10000 === IntrinsicsKt.getCOROUTINE_SUSPENDED()) var10000 else Unit.INSTANCE;
}

public suspend fun ByteReadChannel.skipDelimiter(delimiter: ByteString) {
   var `$continuation`: Continuation;
   label41: {
      if (`$completion` is 2) {
         `$continuation` = `$completion` as 2;
         if (((`$completion` as 2).label and Integer.MIN_VALUE) != 0) {
            `$continuation`.label -= Integer.MIN_VALUE;
            break label41;
         }
      }

      `$continuation` = new 2(`$completion`);
   }

   val `$result`: Any = `$continuation`.result;
   val var8: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
   var i: Int;
   var var4: Int;
   switch ($continuation.label) {
      case 0:
         ResultKt.throwOnFailure(`$result`);
         i = 0;
         var4 = delimiter.getSize();
         break;
      case 1:
         var4 = `$continuation`.I$1;
         i = `$continuation`.I$0;
         delimiter = `$continuation`.L$1 as ByteString;
         `$this$skipDelimiter` = `$continuation`.L$0 as ByteReadChannel;
         ResultKt.throwOnFailure(`$result`);
         if ((`$result` as java.lang.Number).byteValue() != delimiter.get(i)) {
            throw new IllegalStateException("Delimiter is not found");
         }

         i++;
         break;
      default:
         throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
   }

   while (i < var4) {
      `$continuation`.L$0 = `$this$skipDelimiter`;
      `$continuation`.L$1 = delimiter;
      `$continuation`.I$0 = i;
      `$continuation`.I$1 = var4;
      `$continuation`.label = 1;
      val var10000: Any = ByteReadChannelOperationsKt.readByte(`$this$skipDelimiter`, `$continuation`);
      if (var10000 === var8) {
         return var8;
      }

      if ((var10000 as java.lang.Number).byteValue() != delimiter.get(i)) {
         throw new IllegalStateException("Delimiter is not found");
      }

      i++;
   }

   return Unit.INSTANCE;
}

public suspend fun ByteReadChannel.readFully(buffer: ByteBuffer) {
   var `$continuation`: Continuation;
   label41: {
      if (`$completion` is io.ktor.utils.io.ByteReadChannelOperations_jvmKt.readFully.1) {
         `$continuation` = `$completion` as io.ktor.utils.io.ByteReadChannelOperations_jvmKt.readFully.1;
         if (((`$completion` as io.ktor.utils.io.ByteReadChannelOperations_jvmKt.readFully.1).label and Integer.MIN_VALUE) != 0) {
            `$continuation`.label -= Integer.MIN_VALUE;
            break label41;
         }
      }

      `$continuation` = new io.ktor.utils.io.ByteReadChannelOperations_jvmKt.readFully.1(`$completion`);
   }

   val `$result`: Any = `$continuation`.result;
   val var5: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
   switch ($continuation.label) {
      case 0:
         ResultKt.throwOnFailure(`$result`);
         break;
      case 1:
         buffer = `$continuation`.L$1 as ByteBuffer;
         `$this$readFully` = `$continuation`.L$0 as ByteReadChannel;
         ResultKt.throwOnFailure(`$result`);
         if (!`$result` as java.lang.Boolean) {
            throw new EOFException("Not enough bytes available: expected ${buffer.remaining()} more bytes");
         }

         SourcesJvmKt.readAtMostTo(`$this$readFully`.getReadBuffer(), buffer);
         break;
      default:
         throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
   }

   while (buffer.hasRemaining()) {
      `$continuation`.L$0 = `$this$readFully`;
      `$continuation`.L$1 = buffer;
      `$continuation`.label = 1;
      val var10000: Any = ByteReadChannel.awaitContent$default(`$this$readFully`, 0, `$continuation`, 1, null);
      if (var10000 === var5) {
         return var5;
      }

      if (!var10000 as java.lang.Boolean) {
         throw new EOFException("Not enough bytes available: expected ${buffer.remaining()} more bytes");
      }

      SourcesJvmKt.readAtMostTo(`$this$readFully`.getReadBuffer(), buffer);
   }

   return Unit.INSTANCE;
}

public fun ByteReadChannel.readAvailable(block: (ByteBuffer) -> Int): Int {
   if (!`$this$readAvailable`.isClosedForRead() && !`$this$readAvailable`.getReadBuffer().exhausted()) {
      val `this_$iv`: UnsafeBufferOperations = UnsafeBufferOperations.INSTANCE;
      val `buffer$iv`: Buffer = `$this$readAvailable`.getReadBuffer().getBuffer();
      if (`buffer$iv`.exhausted()) {
         throw new IllegalArgumentException("Buffer is empty".toString());
      } else {
         val var10000: Segment = `buffer$iv`.getHead();
         val var15: ByteArray = var10000.dataAsByteArray(true);
         val start: Int = var10000.getPos();
         val buffer: ByteBuffer = ByteBuffer.wrap(var15, start, var10000.getLimit() - start);
         val var13: Int = (block.invoke(buffer) as java.lang.Number).intValue();
         if (var13 != 0) {
            if (var13 < 0) {
               throw new IllegalStateException("Returned negative read bytes count");
            }

            if (var13 > var10000.getSize()) {
               throw new IllegalStateException("Returned too many bytes");
            }

            `buffer$iv`.skip((long)var13);
         }

         return var13;
      }
   } else {
      return -1;
   }
}

public suspend inline fun ByteReadChannel.read(min: Int = ..., noinline consumer: (ByteBuffer) -> Unit) {
   var `$continuation`: Continuation;
   label52: {
      if (`$completion` is io.ktor.utils.io.ByteReadChannelOperations_jvmKt.read.1) {
         `$continuation` = `$completion` as io.ktor.utils.io.ByteReadChannelOperations_jvmKt.read.1;
         if (((`$completion` as io.ktor.utils.io.ByteReadChannelOperations_jvmKt.read.1).label and Integer.MIN_VALUE) != 0) {
            `$continuation`.label -= Integer.MIN_VALUE;
            break label52;
         }
      }

      `$continuation` = new io.ktor.utils.io.ByteReadChannelOperations_jvmKt.read.1(`$completion`);
   }

   var var10000: Any;
   label63: {
      val `$result`: Any = `$continuation`.result;
      val var8: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
      switch ($continuation.label) {
         case 0:
            ResultKt.throwOnFailure(`$result`);
            if (min < 0) {
               throw new IllegalArgumentException("min should be positive or zero".toString());
            }

            if (min <= 0) {
               `$continuation`.L$0 = `$this$read`;
               `$continuation`.L$1 = consumer;
               `$continuation`.I$0 = min;
               `$continuation`.I$1 = 0;
               `$continuation`.label = 2;
               var10000 = ByteReadChannel.awaitContent$default(`$this$read`, 0, `$continuation`, 1, null);
               if (var10000 === var8) {
                  return var8;
               }
               break label63;
            }

            `$continuation`.L$0 = `$this$read`;
            `$continuation`.L$1 = consumer;
            `$continuation`.I$0 = min;
            `$continuation`.I$1 = 0;
            `$continuation`.label = 1;
            var10000 = `$this$read`.awaitContent(min, `$continuation`);
            if (var10000 === var8) {
               return var8;
            }
            break;
         case 1: {
            val var10: Int = `$continuation`.I$1;
            min = `$continuation`.I$0;
            consumer = `$continuation`.L$1 as Function1;
            `$this$read` = `$continuation`.L$0 as ByteReadChannel;
            ResultKt.throwOnFailure(`$result`);
            var10000 = `$result`;
            break;
         }
         case 2: {
            val `$i$f$read`: Int = `$continuation`.I$1;
            min = `$continuation`.I$0;
            consumer = `$continuation`.L$1 as Function1;
            `$this$read` = `$continuation`.L$0 as ByteReadChannel;
            ResultKt.throwOnFailure(`$result`);
            var10000 = `$result`;
            break label63;
         }
         default:
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
      }

      if (!var10000 as java.lang.Boolean) {
         throw new EOFException("Not enough bytes available: required $min but ${ByteReadChannelOperationsKt.getAvailableForRead(`$this$read`)} available");
      }

      ByteReadPacketExtensions_jvmKt.read(`$this$read`.getReadBuffer(), consumer);
      return Unit.INSTANCE;
   }

   if (var10000 as java.lang.Boolean) {
      ByteReadPacketExtensions_jvmKt.read(`$this$read`.getReadBuffer(), consumer);
   }

   return Unit.INSTANCE;
}

fun ByteReadChannel.`read$$forInline`(min: Int, consumer: (ByteBuffer?) -> Unit, `$completion`: Continuation<? super Unit>): Any {
   if (min < 0) {
      throw new IllegalArgumentException("min should be positive or zero".toString());
   } else {
      if (min > 0) {
         InlineMarker.mark(0);
         val var10000: Any = `$this$read`.awaitContent(min, `$completion`);
         InlineMarker.mark(1);
         if (!var10000 as java.lang.Boolean) {
            throw new EOFException("Not enough bytes available: required $min but ${ByteReadChannelOperationsKt.getAvailableForRead(`$this$read`)} available");
         }

         ByteReadPacketExtensions_jvmKt.read(`$this$read`.getReadBuffer(), consumer);
      } else {
         InlineMarker.mark(0);
         val var8: Any = ByteReadChannel.awaitContent$default(`$this$read`, 0, `$completion`, 1, null);
         InlineMarker.mark(1);
         if (var8 as java.lang.Boolean) {
            ByteReadPacketExtensions_jvmKt.read(`$this$read`.getReadBuffer(), consumer);
         }
      }

      return Unit.INSTANCE;
   }
}

@JvmSynthetic
fun ByteReadChannel.`read$default`(min: Int, consumer: Function1, `$completion`: Continuation, `$i$f$read`: Int, var5: Any): Any {
   if ((`$i$f$read` and 1) != 0) {
      min = 1;
   }

   if (min < 0) {
      throw new IllegalArgumentException("min should be positive or zero".toString());
   } else {
      if (min > 0) {
         InlineMarker.mark(0);
         val var10000: Any = `$this$read_u24default`.awaitContent(min, `$completion`);
         InlineMarker.mark(1);
         if (!var10000 as java.lang.Boolean) {
            throw new EOFException(
               "Not enough bytes available: required $min but ${ByteReadChannelOperationsKt.getAvailableForRead(`$this$read_u24default`)} available"
            );
         }

         ByteReadPacketExtensions_jvmKt.read(`$this$read_u24default`.getReadBuffer(), consumer);
      } else {
         InlineMarker.mark(0);
         val var10: Any = ByteReadChannel.awaitContent$default(`$this$read_u24default`, 0, `$completion`, 1, null);
         InlineMarker.mark(1);
         if (var10 as java.lang.Boolean) {
            ByteReadPacketExtensions_jvmKt.read(`$this$read_u24default`.getReadBuffer(), consumer);
         }
      }

      return Unit.INSTANCE;
   }
}

fun `copyTo$lambda$2`(`$limit`: Long, `$copied`: Ref.LongRef, `$channel`: WritableByteChannel, bb: ByteBuffer): Unit {
   val rem: Long = `$limit` - `$copied`.element;
   if (`$limit` - `$copied`.element < bb.remaining()) {
      val written: Int = bb.limit();
      ((java.nio.Buffer)bb).limit(bb.position() + (int)rem);

      while (bb.hasRemaining()) {
         `$channel`.write(bb);
      }

      ((java.nio.Buffer)bb).limit(written);
      `$copied`.element += rem;
   } else {
      var var9: Long = 0L;

      while (bb.hasRemaining()) {
         var9 += `$channel`.write(bb);
      }

      `$copied`.element += var9;
   }

   return Unit.INSTANCE;
}

package io.ktor.network.sockets

import io.ktor.network.selector.SelectInterest
import io.ktor.network.selector.Selectable
import io.ktor.network.selector.SelectorManager
import io.ktor.network.sockets.DatagramSendChannel.sendSuspend.1
import io.ktor.network.util.PoolsKt
import io.ktor.utils.io.core.ByteReadPacketKt
import io.ktor.utils.io.pool.ObjectPool
import java.nio.ByteBuffer
import java.nio.channels.DatagramChannel
import kotlin.coroutines.Continuation
import kotlin.coroutines.intrinsics.IntrinsicsKt
import kotlin.jvm.functions.Function1
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.ChannelResult
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.selects.SelectClause2
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.MutexKt
import kotlinx.io.Buffer
import kotlinx.io.Segment
import kotlinx.io.unsafe.UnsafeBufferOperations

@SourceDebugExtension(["SMAP\nDatagramSendChannel.kt\nKotlin\n*S Kotlin\n*F\n+ 1 DatagramSendChannel.kt\nio/ktor/network/sockets/DatagramSendChannel\n+ 2 UnsafeBufferOperationsJvm.kt\nkotlinx/io/unsafe/UnsafeBufferOperationsJvmKt\n+ 3 UnsafeBufferOperations.kt\nkotlinx/io/unsafe/UnsafeBufferOperations\n+ 4 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 5 Pool.kt\nio/ktor/utils/io/pool/PoolKt\n+ 6 Mutex.kt\nkotlinx/coroutines/sync/MutexKt\n*L\n1#1,195:1\n50#2:196\n51#2,3:201\n99#3:197\n100#3,2:199\n102#3,6:204\n1#4:198\n182#5,5:210\n116#6,11:215\n*S KotlinDebug\n*F\n+ 1 DatagramSendChannel.kt\nio/ktor/network/sockets/DatagramSendChannel\n*L\n60#1:196\n60#1:201,3\n60#1:197\n60#1:199,2\n60#1:204,6\n60#1:198\n76#1:210,5\n94#1:215,11\n*E\n"])
internal class DatagramSendChannel(channel: DatagramChannel, socket: DatagramSocketImpl) : SendChannel<Datagram> {
   public final val channel: DatagramChannel
   public final val socket: DatagramSocketImpl
   private final val lock: Mutex

   @DelicateCoroutinesApi
   public open val isClosedForSend: Boolean
      public open get() {
         return (boolean)this.closed;
      }


   public open val onSend: SelectClause2<Datagram, SendChannel<Datagram>>
      public open get() {
         throw new NotImplementedError("An operation is not implemented: [DatagramSendChannel] doesn't support [onSend] select clause");
      }


   init {
      this.channel = channel;
      this.socket = socket;
      this.onCloseHandler = null;
      this.closed = 0;
      this.closedCause = null;
      this.lock = MutexKt.Mutex$default(false, 1, null);
   }

   public override fun close(cause: Throwable?): Boolean {
      if (!closed$FU.compareAndSet(this, 0, 1)) {
         return false;
      } else {
         this.closedCause = cause;
         if (!this.socket.isClosed()) {
            this.socket.close();
         }

         this.closeAndCheckHandler();
         return true;
      }
   }

   public open fun trySend(element: Datagram): ChannelResult<Unit> {
      if (!Mutex.DefaultImpls.tryLock$default(this.lock, null, 1, null)) {
         return ChannelResult.Companion.failure-PtdJZtk();
      } else {
         label164: {
            try {
               val packetSize: Long = ByteReadPacketKt.getRemaining(element.getPacket());
               var writeWithPool: Boolean = false;
               val `$this$useInstance$iv`: UnsafeBufferOperations = UnsafeBufferOperations.INSTANCE;
               val `$i$f$useInstance`: Buffer = element.getPacket().getBuffer();
               if (`$i$f$useInstance`.exhausted()) {
                  throw new IllegalArgumentException("Buffer is empty".toString());
               }

               val var10000: Segment = `$i$f$useInstance`.getHead();
               val var35: ByteArray = var10000.dataAsByteArray(true);
               val `pos$iv`: Int = var10000.getPos();
               val `bb$iv`: ByteBuffer = ByteBuffer.wrap(var35, `pos$iv`, var10000.getLimit() - `pos$iv`).slice().asReadOnlyBuffer();
               if (`bb$iv`.remaining() < packetSize) {
                  writeWithPool = true;
               } else if (this.channel.send(`bb$iv`, JavaSocketAddressUtilsKt.toJavaAddress(element.getAddress())) == 0) {
                  ((java.nio.Buffer)`bb$iv`).position(`bb$iv`.limit());
               } else {
                  ((java.nio.Buffer)`bb$iv`).position(0);
               }

               val `bytesRead$iv$iv`: Int = `bb$iv`.position();
               if (`bytesRead$iv$iv` != 0) {
                  if (`bytesRead$iv$iv` < 0) {
                     throw new IllegalStateException("Returned negative read bytes count");
                  }

                  if (`bytesRead$iv$iv` > var10000.getSize()) {
                     throw new IllegalStateException("Returned too many bytes");
                  }

                  `$i$f$useInstance`.skip((long)`bytesRead$iv$iv`);
               }

               if (writeWithPool) {
                  val var28: ObjectPool = PoolsKt.getDefaultDatagramByteBufferPool();
                  val var30: Any = var28.borrow();

                  try {
                     val buffer: ByteBuffer = var30 as ByteBuffer;
                     DatagramSendChannelKt.access$writeMessageTo(element.getPacket().peek(), var30 as ByteBuffer);
                     if (this.channel.send(buffer, JavaSocketAddressUtilsKt.toJavaAddress(element.getAddress())) == 0) {
                        ByteReadPacketKt.discard$default(element.getPacket(), 0L, 1, null);
                     }
                  } catch (var22: java.lang.Throwable) {
                     var28.recycle(var30);
                  }

                  var28.recycle(var30);
               }
            } catch (var23: java.lang.Throwable) {
               Mutex.DefaultImpls.unlock$default(this.lock, null, 1, null);
            }

            Mutex.DefaultImpls.unlock$default(this.lock, null, 1, null);
         }
      }
   }

   public open suspend fun send(element: Datagram) {
      // $VF: Couldn't be decompiled
      // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
      // java.lang.IndexOutOfBoundsException: Index -1 out of bounds for length 0
      //   at java.base/jdk.internal.util.Preconditions.outOfBounds(Preconditions.java:100)
      //   at java.base/jdk.internal.util.Preconditions.outOfBoundsCheckIndex(Preconditions.java:106)
      //   at java.base/jdk.internal.util.Preconditions.checkIndex(Preconditions.java:302)
      //   at java.base/java.util.Objects.checkIndex(Objects.java:365)
      //   at java.base/java.util.ArrayList.remove(ArrayList.java:552)
      //   at org.jetbrains.java.decompiler.modules.decompiler.FinallyProcessor.removeExceptionInstructionsEx(FinallyProcessor.java:1064)
      //   at org.jetbrains.java.decompiler.modules.decompiler.FinallyProcessor.verifyFinallyEx(FinallyProcessor.java:565)
      //   at org.jetbrains.java.decompiler.modules.decompiler.FinallyProcessor.iterateGraph(FinallyProcessor.java:90)
      //
      // Bytecode:
      // 000: aload 2
      // 001: instanceof io/ktor/network/sockets/DatagramSendChannel$send$1
      // 004: ifeq 029
      // 007: aload 2
      // 008: checkcast io/ktor/network/sockets/DatagramSendChannel$send$1
      // 00b: astore 10
      // 00d: aload 10
      // 00f: getfield io/ktor/network/sockets/DatagramSendChannel$send$1.label I
      // 012: ldc_w -2147483648
      // 015: iand
      // 016: ifeq 029
      // 019: aload 10
      // 01b: dup
      // 01c: getfield io/ktor/network/sockets/DatagramSendChannel$send$1.label I
      // 01f: ldc_w -2147483648
      // 022: isub
      // 023: putfield io/ktor/network/sockets/DatagramSendChannel$send$1.label I
      // 026: goto 034
      // 029: new io/ktor/network/sockets/DatagramSendChannel$send$1
      // 02c: dup
      // 02d: aload 0
      // 02e: aload 2
      // 02f: invokespecial io/ktor/network/sockets/DatagramSendChannel$send$1.<init> (Lio/ktor/network/sockets/DatagramSendChannel;Lkotlin/coroutines/Continuation;)V
      // 032: astore 10
      // 034: aload 10
      // 036: getfield io/ktor/network/sockets/DatagramSendChannel$send$1.result Ljava/lang/Object;
      // 039: astore 9
      // 03b: invokestatic kotlin/coroutines/intrinsics/IntrinsicsKt.getCOROUTINE_SUSPENDED ()Ljava/lang/Object;
      // 03e: astore 11
      // 040: aload 10
      // 042: getfield io/ktor/network/sockets/DatagramSendChannel$send$1.label I
      // 045: tableswitch 273 0 2 27 87 195
      // 060: aload 9
      // 062: invokestatic kotlin/ResultKt.throwOnFailure (Ljava/lang/Object;)V
      // 065: aload 0
      // 066: getfield io/ktor/network/sockets/DatagramSendChannel.lock Lkotlinx/coroutines/sync/Mutex;
      // 069: astore 3
      // 06a: aconst_null
      // 06b: astore 4
      // 06d: bipush 0
      // 06e: istore 5
      // 070: aload 3
      // 071: aload 4
      // 073: aload 10
      // 075: aload 10
      // 077: aload 1
      // 078: putfield io/ktor/network/sockets/DatagramSendChannel$send$1.L$0 Ljava/lang/Object;
      // 07b: aload 10
      // 07d: aload 3
      // 07e: putfield io/ktor/network/sockets/DatagramSendChannel$send$1.L$1 Ljava/lang/Object;
      // 081: aload 10
      // 083: iload 5
      // 085: putfield io/ktor/network/sockets/DatagramSendChannel$send$1.I$0 I
      // 088: aload 10
      // 08a: bipush 1
      // 08b: putfield io/ktor/network/sockets/DatagramSendChannel$send$1.label I
      // 08e: invokeinterface kotlinx/coroutines/sync/Mutex.lock (Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object; 3
      // 093: dup
      // 094: aload 11
      // 096: if_acmpne 0bf
      // 099: aload 11
      // 09b: areturn
      // 09c: aload 10
      // 09e: getfield io/ktor/network/sockets/DatagramSendChannel$send$1.I$0 I
      // 0a1: istore 5
      // 0a3: aconst_null
      // 0a4: astore 4
      // 0a6: aload 10
      // 0a8: getfield io/ktor/network/sockets/DatagramSendChannel$send$1.L$1 Ljava/lang/Object;
      // 0ab: checkcast kotlinx/coroutines/sync/Mutex
      // 0ae: astore 3
      // 0af: aload 10
      // 0b1: getfield io/ktor/network/sockets/DatagramSendChannel$send$1.L$0 Ljava/lang/Object;
      // 0b4: checkcast io/ktor/network/sockets/Datagram
      // 0b7: astore 1
      // 0b8: aload 9
      // 0ba: invokestatic kotlin/ResultKt.throwOnFailure (Ljava/lang/Object;)V
      // 0bd: aload 9
      // 0bf: pop
      // 0c0: nop
      // 0c1: bipush 0
      // 0c2: istore 6
      // 0c4: invokestatic kotlinx/coroutines/Dispatchers.getIO ()Lkotlinx/coroutines/CoroutineDispatcher;
      // 0c7: checkcast kotlin/coroutines/CoroutineContext
      // 0ca: new io/ktor/network/sockets/DatagramSendChannel$send$2$1
      // 0cd: dup
      // 0ce: aload 1
      // 0cf: aload 0
      // 0d0: aconst_null
      // 0d1: invokespecial io/ktor/network/sockets/DatagramSendChannel$send$2$1.<init> (Lio/ktor/network/sockets/Datagram;Lio/ktor/network/sockets/DatagramSendChannel;Lkotlin/coroutines/Continuation;)V
      // 0d4: checkcast kotlin/jvm/functions/Function2
      // 0d7: aload 10
      // 0d9: aload 10
      // 0db: aload 1
      // 0dc: invokestatic kotlin/coroutines/jvm/internal/SpillingKt.nullOutSpilledVariable (Ljava/lang/Object;)Ljava/lang/Object;
      // 0df: putfield io/ktor/network/sockets/DatagramSendChannel$send$1.L$0 Ljava/lang/Object;
      // 0e2: aload 10
      // 0e4: aload 3
      // 0e5: putfield io/ktor/network/sockets/DatagramSendChannel$send$1.L$1 Ljava/lang/Object;
      // 0e8: aload 10
      // 0ea: iload 5
      // 0ec: putfield io/ktor/network/sockets/DatagramSendChannel$send$1.I$0 I
      // 0ef: aload 10
      // 0f1: iload 6
      // 0f3: putfield io/ktor/network/sockets/DatagramSendChannel$send$1.I$1 I
      // 0f6: aload 10
      // 0f8: bipush 2
      // 0f9: putfield io/ktor/network/sockets/DatagramSendChannel$send$1.label I
      // 0fc: invokestatic kotlinx/coroutines/BuildersKt.withContext (Lkotlin/coroutines/CoroutineContext;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;
      // 0ff: dup
      // 100: aload 11
      // 102: if_acmpne 133
      // 105: aload 11
      // 107: areturn
      // 108: aload 10
      // 10a: getfield io/ktor/network/sockets/DatagramSendChannel$send$1.I$1 I
      // 10d: istore 6
      // 10f: aload 10
      // 111: getfield io/ktor/network/sockets/DatagramSendChannel$send$1.I$0 I
      // 114: istore 5
      // 116: aconst_null
      // 117: astore 4
      // 119: aload 10
      // 11b: getfield io/ktor/network/sockets/DatagramSendChannel$send$1.L$1 Ljava/lang/Object;
      // 11e: checkcast kotlinx/coroutines/sync/Mutex
      // 121: astore 3
      // 122: aload 10
      // 124: getfield io/ktor/network/sockets/DatagramSendChannel$send$1.L$0 Ljava/lang/Object;
      // 127: checkcast io/ktor/network/sockets/Datagram
      // 12a: astore 1
      // 12b: nop
      // 12c: aload 9
      // 12e: invokestatic kotlin/ResultKt.throwOnFailure (Ljava/lang/Object;)V
      // 131: aload 9
      // 133: pop
      // 134: getstatic kotlin/Unit.INSTANCE Lkotlin/Unit;
      // 137: astore 7
      // 139: aload 3
      // 13a: aload 4
      // 13c: invokeinterface kotlinx/coroutines/sync/Mutex.unlock (Ljava/lang/Object;)V 2
      // 141: goto 151
      // 144: astore 8
      // 146: aload 3
      // 147: aload 4
      // 149: invokeinterface kotlinx/coroutines/sync/Mutex.unlock (Ljava/lang/Object;)V 2
      // 14e: aload 8
      // 150: athrow
      // 151: nop
      // 152: getstatic kotlin/Unit.INSTANCE Lkotlin/Unit;
      // 155: areturn
      // 156: new java/lang/IllegalStateException
      // 159: dup
      // 15a: ldc_w "call to 'resume' before 'invoke' with coroutine"
      // 15d: invokespecial java/lang/IllegalStateException.<init> (Ljava/lang/String;)V
      // 160: athrow
   }

   private suspend fun sendSuspend(buffer: ByteBuffer, address: SocketAddress) {
      var `$continuation`: Continuation;
      label34: {
         if (`$completion` is 1) {
            `$continuation` = `$completion` as 1;
            if (((`$completion` as 1).label and Integer.MIN_VALUE) != 0) {
               `$continuation`.label -= Integer.MIN_VALUE;
               break label34;
            }
         }

         `$continuation` = new 1(this, `$completion`);
      }

      val `$result`: Any = `$continuation`.result;
      val var6: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
      switch ($continuation.label) {
         case 0:
            ResultKt.throwOnFailure(`$result`);
            break;
         case 1:
            address = `$continuation`.L$1 as SocketAddress;
            buffer = `$continuation`.L$0 as ByteBuffer;
            ResultKt.throwOnFailure(`$result`);
            if (this.channel.send(buffer, JavaSocketAddressUtilsKt.toJavaAddress(address)) != 0) {
               this.socket.interestOp(SelectInterest.WRITE, false);
               return Unit.INSTANCE;
            }
            break;
         default:
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
      }

      do {
         this.socket.interestOp(SelectInterest.WRITE, true);
         val var10000: SelectorManager = this.socket.getSelector();
         val var10001: Selectable = this.socket;
         val var10002: SelectInterest = SelectInterest.WRITE;
         `$continuation`.L$0 = buffer;
         `$continuation`.L$1 = address;
         `$continuation`.label = 1;
         if (var10000.select(var10001, var10002, `$continuation`) === var6) {
            return var6;
         }
      } while (this.channel.send(buffer, JavaSocketAddressUtilsKt.toJavaAddress(address)) == 0);

      this.socket.interestOp(SelectInterest.WRITE, false);
      return Unit.INSTANCE;
   }

   @ExperimentalCoroutinesApi
   public override fun invokeOnClose(handler: (Throwable?) -> Unit) {
      if (!onCloseHandler$FU.compareAndSet(this, null, handler)) {
         if (this.onCloseHandler === DatagramSendChannelKt.access$getCLOSED$p()) {
            if (!onCloseHandler$FU.compareAndSet(this, DatagramSendChannelKt.access$getCLOSED$p(), DatagramSendChannelKt.access$getCLOSED_INVOKED$p())) {
               throw new IllegalArgumentException("Failed requirement.".toString());
            } else {
               handler.invoke(this.closedCause);
            }
         } else {
            DatagramSendChannelKt.access$failInvokeOnClose(this.onCloseHandler as Function1);
         }
      }
   }

   private fun closeAndCheckHandler() {
      while (true) {
         val handler: Function1 = this.onCloseHandler as Function1;
         if (this.onCloseHandler as Function1 != DatagramSendChannelKt.access$getCLOSED_INVOKED$p()) {
            if (handler == null) {
               if (!onCloseHandler$FU.compareAndSet(this, null, DatagramSendChannelKt.access$getCLOSED$p())) {
                  continue;
               }
            } else {
               if (!onCloseHandler$FU.compareAndSet(this, handler, DatagramSendChannelKt.access$getCLOSED_INVOKED$p())) {
                  throw new IllegalArgumentException("Failed requirement.".toString());
               }

               handler.invoke(this.closedCause);
            }
         }

         return;
      }
   }
}

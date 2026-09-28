package io.ktor.utils.io

import io.ktor.utils.io.ByteChannel.readBuffer.1
import kotlin.coroutines.Continuation
import kotlin.coroutines.intrinsics.IntrinsicsKt
import kotlin.coroutines.jvm.internal.Boxing
import kotlin.coroutines.jvm.internal.DebugProbesKt
import kotlin.coroutines.jvm.internal.SpillingKt
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.coroutines.CancellableContinuationImpl
import kotlinx.io.Buffer
import kotlinx.io.Sink
import kotlinx.io.Source

@SourceDebugExtension(["SMAP\nByteChannel.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ByteChannel.kt\nio/ktor/utils/io/ByteChannel\n+ 2 CancellableContinuation.kt\nkotlinx/coroutines/CancellableContinuationKt\n+ 3 Synchronized.kt\nio/ktor/utils/io/locks/SynchronizedKt\n*L\n1#1,281:1\n152#1,2:282\n154#1:293\n185#1,27:294\n165#1,5:321\n213#1:326\n155#1:327\n157#1:330\n165#1,5:332\n152#1,2:337\n154#1:348\n185#1,27:349\n165#1,5:376\n213#1:381\n155#1:382\n157#1:385\n165#1,5:387\n185#1,27:401\n165#1,5:428\n213#1:433\n165#1,5:436\n426#2,9:284\n435#2,2:328\n426#2,9:339\n435#2,2:383\n426#2,9:392\n435#2,2:434\n84#3:331\n84#3:386\n*S KotlinDebug\n*F\n+ 1 ByteChannel.kt\nio/ktor/utils/io/ByteChannel\n*L\n74#1:282,2\n74#1:293\n74#1:294,27\n74#1:321,5\n74#1:326\n74#1:327\n74#1:330\n89#1:332,5\n99#1:337,2\n99#1:348\n99#1:349,27\n99#1:376,5\n99#1:381\n99#1:382\n99#1:385\n114#1:387,5\n154#1:401,27\n154#1:428,5\n154#1:433\n211#1:436,5\n74#1:284,9\n74#1:328,2\n99#1:339,9\n99#1:383,2\n153#1:392,9\n153#1:434,2\n84#1:331\n108#1:386\n*E\n"])
public class ByteChannel(autoFlush: Boolean = false) : ByteReadChannel, BufferedByteWriteChannel {
   public open val autoFlush: Boolean
   private final val flushBuffer: Buffer
   private final var flushBufferSize: Int
   private final val flushBufferMutex: Any
   private final val _readBuffer: Buffer
   private final val _writeBuffer: Buffer

   @InternalAPI
   public open val readBuffer: Source
      public open get() {
         val var10000: CloseToken = this._closedCause as CloseToken;
         if (this._closedCause as CloseToken != null) {
            var10000.throwOrNull(1.INSTANCE);
         }

         if (this._readBuffer.exhausted()) {
            this.moveFlushToReadBuffer();
         }

         return this._readBuffer;
      }


   @InternalAPI
   public open val writeBuffer: Sink
      public open get() {
         if (this.isClosedForWrite()
            && (
               this._closedCause as CloseToken == null
                  || (this._closedCause as CloseToken).throwOrNull(io.ktor.utils.io.ByteChannel.writeBuffer.1.INSTANCE) == null
            )) {
            throw new ClosedWriteChannelException(null, 1, null);
         } else {
            return this._writeBuffer;
         }
      }


   public open val closedCause: Throwable?
      public open get() {
         return if (this._closedCause as CloseToken != null) CloseToken.wrapCause$default(this._closedCause as CloseToken, null, 1, null) else null;
      }


   public open val isClosedForWrite: Boolean
      public open get() {
         return this._closedCause != null;
      }


   public open val isClosedForRead: Boolean
      public open get() {
         return this.getClosedCause() != null || this.isClosedForWrite() && this.flushBufferSize == 0 && this._readBuffer.exhausted();
      }


   init {
      this.autoFlush = autoFlush;
      this.flushBuffer = new Buffer();
      this.flushBufferMutex = new Object();
      this.suspensionSlot = ByteChannel.Slot.Empty.INSTANCE;
      this._readBuffer = new Buffer();
      this._writeBuffer = new Buffer();
      this._closedCause = null;
   }

   public override suspend fun awaitContent(min: Int): Boolean {
      var `$continuation`: Continuation;
      label101: {
         if (`$completion` is io.ktor.utils.io.ByteChannel.awaitContent.1) {
            `$continuation` = `$completion` as io.ktor.utils.io.ByteChannel.awaitContent.1;
            if (((`$completion` as io.ktor.utils.io.ByteChannel.awaitContent.1).label and Integer.MIN_VALUE) != 0) {
               `$continuation`.label -= Integer.MIN_VALUE;
               break label101;
            }
         }

         `$continuation` = new io.ktor.utils.io.ByteChannel.awaitContent.1(this, `$completion`);
      }

      val `$result`: Any = `$continuation`.result;
      val var22: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
      var `this_$iv`: ByteChannel;
      var `$i$f$sleepWhile`: Int;
      switch ($continuation.label) {
         case 0:
            ResultKt.throwOnFailure(`$result`);
            ByteReadChannelOperationsKt.rethrowCloseCauseIfNeeded(this);
            if (this._readBuffer.getSize() >= min) {
               return Boxing.boxBoolean(true);
            }

            `this_$iv` = this;
            `$i$f$sleepWhile` = 0;
            break;
         case 1:
            val `$i$f$suspendCancellableCoroutine`: Int = `$continuation`.I$2;
            `$i$f$sleepWhile` = `$continuation`.I$1;
            min = `$continuation`.I$0;
            `this_$iv` = `$continuation`.L$0 as ByteChannel;
            ResultKt.throwOnFailure(`$result`);
            break;
         default:
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
      }

      val var10000: Any;
      do {
         if (access$getFlushBufferSize$p(this) + access$get_readBuffer$p(this).getSize() >= min || this._closedCause != null) {
            if (this._readBuffer.getSize() < 1048576L) {
               this.moveFlushToReadBuffer();
            }

            return Boxing.boxBoolean(this._readBuffer.getSize() >= (long)min);
         }

         `$continuation`.L$0 = `this_$iv`;
         `$continuation`.I$0 = min;
         `$continuation`.I$1 = `$i$f$sleepWhile`;
         `$continuation`.I$2 = 0;
         `$continuation`.label = 1;
         val `cancellable$iv$iv`: CancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(`$continuation`), 1);
         `cancellable$iv$iv`.initCancellability();
         val `slot$iv$iv`: ByteChannel.Slot.Task = new ByteChannel.Slot.Read(`cancellable$iv$iv`);
         val `previous$iv$iv`: ByteChannel.Slot = `this_$iv`.suspensionSlot as ByteChannel.Slot;
         if ((`this_$iv`.suspensionSlot as ByteChannel.Slot) !is ByteChannel.Slot.Closed
            && !suspensionSlot$FU.compareAndSet(`this_$iv`, `this_$iv`.suspensionSlot as ByteChannel.Slot, `slot$iv$iv`)) {
            `slot$iv$iv`.resume();
         } else {
            label111: {
               if (`previous$iv$iv` is ByteChannel.Slot.Read) {
                  (`previous$iv$iv` as ByteChannel.Slot.Task)
                     .resume(new ConcurrentIOException(`slot$iv$iv`.taskName(), (`previous$iv$iv` as ByteChannel.Slot.Task).getCreated()));
               } else if (`previous$iv$iv` is ByteChannel.Slot.Task) {
                  (`previous$iv$iv` as ByteChannel.Slot.Task).resume();
               } else {
                  if (`previous$iv$iv` is ByteChannel.Slot.Closed) {
                     `slot$iv$iv`.resume((`previous$iv$iv` as ByteChannel.Slot.Closed).getCause());
                     break label111;
                  }

                  if (!(`previous$iv$iv` == ByteChannel.Slot.Empty.INSTANCE)) {
                     throw new NoWhenBranchMatchedException();
                  }
               }

               if (access$getFlushBufferSize$p(this) + access$get_readBuffer$p(this).getSize() >= min || this._closedCause != null) {
                  val `current$iv$iv$iv`: ByteChannel.Slot = `this_$iv`.suspensionSlot as ByteChannel.Slot;
                  if (`this_$iv`.suspensionSlot as ByteChannel.Slot is ByteChannel.Slot.Read
                     && suspensionSlot$FU.compareAndSet(`this_$iv`, `this_$iv`.suspensionSlot as ByteChannel.Slot, ByteChannel.Slot.Empty.INSTANCE)) {
                     (`current$iv$iv$iv` as ByteChannel.Slot.Task).resume();
                  }
               }
            }
         }

         var10000 = `cancellable$iv$iv`.getResult();
         if (var10000 === IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(`$continuation`);
         }
      } while (var10000 != var22);

      return var22;
   }

   private fun moveFlushToReadBuffer() {
      synchronized (this.flushBufferMutex) {
         this.flushBuffer.transferTo(this._readBuffer);
         this.flushBufferSize = 0;
      }

      val `current$iv`: ByteChannel.Slot = this.suspensionSlot as ByteChannel.Slot;
      if (this.suspensionSlot as ByteChannel.Slot is ByteChannel.Slot.Write
         && suspensionSlot$FU.compareAndSet(this, this.suspensionSlot as ByteChannel.Slot, ByteChannel.Slot.Empty.INSTANCE)) {
         (`current$iv` as ByteChannel.Slot.Task).resume();
      }
   }

   public override suspend fun flush() {
      var `$continuation`: Continuation;
      label92: {
         if (`$completion` is io.ktor.utils.io.ByteChannel.flush.1) {
            `$continuation` = `$completion` as io.ktor.utils.io.ByteChannel.flush.1;
            if (((`$completion` as io.ktor.utils.io.ByteChannel.flush.1).label and Integer.MIN_VALUE) != 0) {
               `$continuation`.label -= Integer.MIN_VALUE;
               break label92;
            }
         }

         `$continuation` = new io.ktor.utils.io.ByteChannel.flush.1(this, `$completion`);
      }

      val `$result`: Any = `$continuation`.result;
      val var21: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
      var `this_$iv`: ByteChannel;
      var `$i$f$sleepWhile`: Int;
      switch ($continuation.label) {
         case 0:
            ResultKt.throwOnFailure(`$result`);
            ByteReadChannelOperationsKt.rethrowCloseCauseIfNeeded(this);
            this.flushWriteBuffer();
            if (this.flushBufferSize < 1048576) {
               return Unit.INSTANCE;
            }

            `this_$iv` = this;
            `$i$f$sleepWhile` = 0;
            break;
         case 1:
            val `$i$f$suspendCancellableCoroutine`: Int = `$continuation`.I$1;
            `$i$f$sleepWhile` = `$continuation`.I$0;
            `this_$iv` = `$continuation`.L$0 as ByteChannel;
            ResultKt.throwOnFailure(`$result`);
            break;
         default:
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
      }

      val var10000: Any;
      do {
         if (access$getFlushBufferSize$p(this) < 1048576 || this._closedCause != null) {
            return Unit.INSTANCE;
         }

         `$continuation`.L$0 = `this_$iv`;
         `$continuation`.I$0 = `$i$f$sleepWhile`;
         `$continuation`.I$1 = 0;
         `$continuation`.label = 1;
         val `cancellable$iv$iv`: CancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(`$continuation`), 1);
         `cancellable$iv$iv`.initCancellability();
         val `slot$iv$iv`: ByteChannel.Slot.Task = new ByteChannel.Slot.Write(`cancellable$iv$iv`);
         val `previous$iv$iv`: ByteChannel.Slot = `this_$iv`.suspensionSlot as ByteChannel.Slot;
         if ((`this_$iv`.suspensionSlot as ByteChannel.Slot) !is ByteChannel.Slot.Closed
            && !suspensionSlot$FU.compareAndSet(`this_$iv`, `this_$iv`.suspensionSlot as ByteChannel.Slot, `slot$iv$iv`)) {
            `slot$iv$iv`.resume();
         } else {
            label100: {
               if (`previous$iv$iv` is ByteChannel.Slot.Write) {
                  (`previous$iv$iv` as ByteChannel.Slot.Task)
                     .resume(new ConcurrentIOException(`slot$iv$iv`.taskName(), (`previous$iv$iv` as ByteChannel.Slot.Task).getCreated()));
               } else if (`previous$iv$iv` is ByteChannel.Slot.Task) {
                  (`previous$iv$iv` as ByteChannel.Slot.Task).resume();
               } else {
                  if (`previous$iv$iv` is ByteChannel.Slot.Closed) {
                     `slot$iv$iv`.resume((`previous$iv$iv` as ByteChannel.Slot.Closed).getCause());
                     break label100;
                  }

                  if (!(`previous$iv$iv` == ByteChannel.Slot.Empty.INSTANCE)) {
                     throw new NoWhenBranchMatchedException();
                  }
               }

               if (access$getFlushBufferSize$p(this) < 1048576 || this._closedCause != null) {
                  val `current$iv$iv$iv`: ByteChannel.Slot = `this_$iv`.suspensionSlot as ByteChannel.Slot;
                  if (`this_$iv`.suspensionSlot as ByteChannel.Slot is ByteChannel.Slot.Write
                     && suspensionSlot$FU.compareAndSet(`this_$iv`, `this_$iv`.suspensionSlot as ByteChannel.Slot, ByteChannel.Slot.Empty.INSTANCE)) {
                     (`current$iv$iv$iv` as ByteChannel.Slot.Task).resume();
                  }
               }
            }
         }

         var10000 = `cancellable$iv$iv`.getResult();
         if (var10000 === IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(`$continuation`);
         }
      } while (var10000 != var21);

      return var21;
   }

   @InternalAPI
   public override fun flushWriteBuffer() {
      if (!this._writeBuffer.exhausted()) {
         synchronized (this.flushBufferMutex) {
            val count: Int = (int)this._writeBuffer.getSize();
            this.flushBuffer.transferFrom(this._writeBuffer);
            this.flushBufferSize += count;
         }

         val `current$iv`: ByteChannel.Slot = this.suspensionSlot as ByteChannel.Slot;
         if (this.suspensionSlot as ByteChannel.Slot is ByteChannel.Slot.Read
            && suspensionSlot$FU.compareAndSet(this, this.suspensionSlot as ByteChannel.Slot, ByteChannel.Slot.Empty.INSTANCE)) {
            (`current$iv` as ByteChannel.Slot.Task).resume();
         }
      }
   }

   public override fun close() {
      this.flushWriteBuffer();
      if (_closedCause$FU.compareAndSet(this, null, CloseTokenKt.getCLOSED())) {
         this.closeSlot(null);
      }
   }

   public override suspend fun flushAndClose() {
      var `$continuation`: Continuation;
      label47: {
         if (`$completion` is io.ktor.utils.io.ByteChannel.flushAndClose.1) {
            `$continuation` = `$completion` as io.ktor.utils.io.ByteChannel.flushAndClose.1;
            if (((`$completion` as io.ktor.utils.io.ByteChannel.flushAndClose.1).label and Integer.MIN_VALUE) != 0) {
               `$continuation`.label -= Integer.MIN_VALUE;
               break label47;
            }
         }

         `$continuation` = new io.ktor.utils.io.ByteChannel.flushAndClose.1(this, `$completion`);
      }

      label41: {
         val `$result`: Any = `$continuation`.result;
         val var7: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
         switch ($continuation.label) {
            case 0:
               ResultKt.throwOnFailure(`$result`);
               val var2: ByteChannel = this;

               var var10000: Any;
               try {
                  val var13: ByteChannel = var2;
                  `$continuation`.L$0 = SpillingKt.nullOutSpilledVariable(var2);
                  `$continuation`.I$0 = 0;
                  `$continuation`.label = 1;
                  var10000 = var13.flush(`$continuation`);
               } catch (var9: java.lang.Throwable) {
                  val var12: Any = Result.constructor-impl(ResultKt.createFailure(var9));
                  break label41;
               }

               if (var10000 === var7) {
                  return var7;
               }
               break;
            case 1:
               val var4: Int = `$continuation`.I$0;
               var `$this$flushAndClose_u24lambda_u240`: ByteChannel = `$continuation`.L$0 as ByteChannel;

               try {
                  ResultKt.throwOnFailure(`$result`);
                  break;
               } catch (var10: java.lang.Throwable) {
                  `$this$flushAndClose_u24lambda_u240` = (ByteChannel)Result.constructor-impl(ResultKt.createFailure(var10));
                  break label41;
               }
            default:
               throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
         }

         try {
            val var15: Any = Result.constructor-impl(Unit.INSTANCE);
         } catch (var8: java.lang.Throwable) {
            val var14: Any = Result.constructor-impl(ResultKt.createFailure(var8));
         }
      }

      if (!_closedCause$FU.compareAndSet(this, null, CloseTokenKt.getCLOSED())) {
         return Unit.INSTANCE;
      } else {
         this.closeSlot(null);
         return Unit.INSTANCE;
      }
   }

   public override fun cancel(cause: Throwable?) {
      if (this._closedCause == null) {
         val closedToken: CloseToken = new CloseToken(cause);
         _closedCause$FU.compareAndSet(this, null, closedToken);
         this.closeSlot(CloseToken.wrapCause$default(closedToken, null, 1, null));
      }
   }

   public override fun toString(): String {
      return "ByteChannel[${this.hashCode()}]";
   }

   private fun closeSlot(cause: Throwable?) {
      val continuation: ByteChannel.Slot = suspensionSlot$FU.getAndSet(
         this, if (cause != null) new ByteChannel.Slot.Closed(cause) else ByteChannel.Slot.Companion.getCLOSED()
      );
      if (continuation is ByteChannel.Slot.Task) {
         (continuation as ByteChannel.Slot.Task).resume(cause);
      }
   }

   fun ByteChannel() {
      this(false, 1, null);
   }

   private sealed interface Slot {
      public data class Closed(cause: Throwable?) : ByteChannel.Slot {
         public final val cause: Throwable?

         init {
            this.cause = cause;
         }

         public operator fun component1(): Throwable? {
            return this.cause;
         }

         public fun copy(cause: Throwable? = this.cause): io.ktor.utils.io.ByteChannel.Slot.Closed {
            return new ByteChannel.Slot.Closed(cause);
         }

         public override fun toString(): String {
            return "Closed(cause=${this.cause})";
         }

         public override fun hashCode(): Int {
            return if (this.cause == null) 0 else this.cause.hashCode();
         }

         public override operator fun equals(other: Any?): Boolean {
            if (this === other) {
               return true;
            } else if (other !is ByteChannel.Slot.Closed) {
               return false;
            } else {
               return this.cause == (other as ByteChannel.Slot.Closed).cause;
            }
         }
      }

      public companion object {
         @JvmStatic
         public final val CLOSED: io.ktor.utils.io.ByteChannel.Slot.Closed = new ByteChannel.Slot.Closed(null)

         @JvmStatic
         public final val RESUME: Result<Unit>
      }

      public data object Empty : ByteChannel.Slot {
         public override fun toString(): String {
            return "Empty";
         }

         public override fun hashCode(): Int {
            return -231472095;
         }

         public override operator fun equals(other: Any?): Boolean {
            if (this === other) {
               return true;
            } else {
               return other is ByteChannel.Slot.Empty;
            }
         }
      }

      public class Read(continuation: Continuation<Unit>) : ByteChannel.Slot.Task {
         public open val continuation: Continuation<Unit>
         public open var created: Throwable?

         init {
            this.continuation = continuation;
            if (ByteChannel_jvmKt.getDEVELOPMENT_MODE()) {
               val var10003: StringBuilder = new StringBuilder().append("ReadTask 0x");
               val var10004: java.lang.String = Integer.toString(this.getContinuation().hashCode(), CharsKt.checkRadix(16));
               val var2: java.lang.Throwable = new java.lang.Throwable(var10003.append(var10004).toString());
               kotlin.ExceptionsKt.stackTraceToString(var2);
               this.setCreated(var2);
            }
         }

         public override fun taskName(): String {
            return "read";
         }
      }

      @SourceDebugExtension(["SMAP\nByteChannel.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ByteChannel.kt\nio/ktor/utils/io/ByteChannel$Slot$Task\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,281:1\n1#2:282\n*E\n"])
      public sealed interface Task : ByteChannel.Slot {
         public val created: Throwable?
         public val continuation: Continuation<Unit>

         public abstract fun taskName(): String {
         }

         public open fun resume() {
            this.getContinuation().resumeWith(ByteChannel.Slot.Companion.getRESUME-d1pmJ48());
         }

         public open fun resume(throwable: Throwable? = null) {
            this.getContinuation()
               .resumeWith(
                  if (throwable != null) Result.constructor-impl(ResultKt.createFailure(throwable)) else ByteChannel.Slot.Companion.getRESUME-d1pmJ48()
               );
         }

         // $VF: Class flags could not be determined
         internal class DefaultImpls {
            @Deprecated
            @JvmStatic
            fun resume(`$this`: ByteChannel.Slot.Task) {
               ByteChannel.Slot.Task.access$resume$jd(`$this`);
            }

            @Deprecated
            @JvmStatic
            fun resume(`$this`: ByteChannel.Slot.Task, throwable: java.lang.Throwable?) {
               ByteChannel.Slot.Task.access$resume$jd(`$this`, throwable);
            }
         }
      }

      public class Write(continuation: Continuation<Unit>) : ByteChannel.Slot.Task {
         public open val continuation: Continuation<Unit>
         public open var created: Throwable?

         init {
            this.continuation = continuation;
            if (ByteChannel_jvmKt.getDEVELOPMENT_MODE()) {
               val var10003: StringBuilder = new StringBuilder().append("WriteTask 0x");
               val var10004: java.lang.String = Integer.toString(this.getContinuation().hashCode(), CharsKt.checkRadix(16));
               val var2: java.lang.Throwable = new java.lang.Throwable(var10003.append(var10004).toString());
               kotlin.ExceptionsKt.stackTraceToString(var2);
               this.setCreated(var2);
            }
         }

         public override fun taskName(): String {
            return "write";
         }
      }
   }
}

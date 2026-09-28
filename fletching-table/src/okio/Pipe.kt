package okio

import java.util.concurrent.TimeUnit
import java.util.concurrent.locks.Condition
import java.util.concurrent.locks.Lock
import java.util.concurrent.locks.ReentrantLock
import kotlin.jvm.internal.InlineMarker
import kotlin.jvm.internal.SourceDebugExtension
import okio.Pipe.sink.1

@SourceDebugExtension(["SMAP\nPipe.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Pipe.kt\nokio/Pipe\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 Timeout.kt\nokio/Timeout\n*L\n1#1,262:1\n1#2:263\n302#3,26:264\n*S KotlinDebug\n*F\n+ 1 Pipe.kt\nokio/Pipe\n*L\n222#1:264,26\n*E\n"])
public class Pipe(maxBufferSize: Long) {
   internal final val maxBufferSize: Long
   internal final val buffer: Buffer
   internal final var canceled: Boolean
   internal final var sinkClosed: Boolean
   internal final var sourceClosed: Boolean
   internal final var foldedSink: Sink?
   public final val lock: ReentrantLock
   public final val condition: Condition
   public final val sink: Sink
   public final val source: Source

   init {
      this.maxBufferSize = maxBufferSize;
      this.buffer = new Buffer();
      this.lock = new ReentrantLock();
      val var10001: Condition = this.lock.newCondition();
      this.condition = var10001;
      if (this.maxBufferSize < 1L) {
         throw new IllegalArgumentException(("maxBufferSize < 1: ${this.maxBufferSize}").toString());
      } else {
         this.sink = new 1(this);
         this.source = new okio.Pipe.source.1(this);
      }
   }

   @Throws(java/io/IOException::class)
   public fun fold(sink: Sink) {
      // $VF: Couldn't be decompiled
      // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
      // java.lang.IndexOutOfBoundsException: Index -1 out of bounds for length 0
      //   at java.base/jdk.internal.util.Preconditions.outOfBounds(Preconditions.java:100)
      //   at java.base/jdk.internal.util.Preconditions.outOfBoundsCheckIndex(Preconditions.java:106)
      //   at java.base/jdk.internal.util.Preconditions.checkIndex(Preconditions.java:302)
      //   at java.base/java.util.Objects.checkIndex(Objects.java:365)
      //   at java.base/java.util.ArrayList.remove(ArrayList.java:552)
      //   at org.jetbrains.java.decompiler.util.collections.ListStack.pop(ListStack.java:31)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.processBlock(ExprProcessor.java:569)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.processStatement(ExprProcessor.java:134)
      //
      // Bytecode:
      // 000: aload 1
      // 001: ldc "sink"
      // 003: invokestatic kotlin/jvm/internal/Intrinsics.checkNotNullParameter (Ljava/lang/Object;Ljava/lang/String;)V
      // 006: nop
      // 007: bipush 0
      // 008: istore 2
      // 009: bipush 0
      // 00a: istore 3
      // 00b: aconst_null
      // 00c: astore 4
      // 00e: aload 0
      // 00f: getfield okio/Pipe.lock Ljava/util/concurrent/locks/ReentrantLock;
      // 012: checkcast java/util/concurrent/locks/Lock
      // 015: astore 5
      // 017: aload 5
      // 019: invokeinterface java/util/concurrent/locks/Lock.lock ()V 1
      // 01e: nop
      // 01f: bipush 0
      // 020: istore 6
      // 022: aload 0
      // 023: getfield okio/Pipe.foldedSink Lokio/Sink;
      // 026: ifnonnull 02d
      // 029: bipush 1
      // 02a: goto 02e
      // 02d: bipush 0
      // 02e: ifne 045
      // 031: bipush 0
      // 032: istore 7
      // 034: ldc "sink already folded"
      // 036: astore 7
      // 038: new java/lang/IllegalStateException
      // 03b: dup
      // 03c: aload 7
      // 03e: invokevirtual java/lang/Object.toString ()Ljava/lang/String;
      // 041: invokespecial java/lang/IllegalStateException.<init> (Ljava/lang/String;)V
      // 044: athrow
      // 045: aload 0
      // 046: getfield okio/Pipe.canceled Z
      // 049: ifeq 05b
      // 04c: aload 0
      // 04d: aload 1
      // 04e: putfield okio/Pipe.foldedSink Lokio/Sink;
      // 051: new java/io/IOException
      // 054: dup
      // 055: ldc "canceled"
      // 057: invokespecial java/io/IOException.<init> (Ljava/lang/String;)V
      // 05a: athrow
      // 05b: aload 0
      // 05c: getfield okio/Pipe.sinkClosed Z
      // 05f: istore 2
      // 060: aload 0
      // 061: getfield okio/Pipe.buffer Lokio/Buffer;
      // 064: invokevirtual okio/Buffer.exhausted ()Z
      // 067: ifeq 079
      // 06a: aload 0
      // 06b: bipush 1
      // 06c: putfield okio/Pipe.sourceClosed Z
      // 06f: aload 0
      // 070: aload 1
      // 071: putfield okio/Pipe.foldedSink Lokio/Sink;
      // 074: bipush 1
      // 075: istore 3
      // 076: goto 09c
      // 079: new okio/Buffer
      // 07c: dup
      // 07d: invokespecial okio/Buffer.<init> ()V
      // 080: astore 4
      // 082: aload 4
      // 084: aload 0
      // 085: getfield okio/Pipe.buffer Lokio/Buffer;
      // 088: aload 0
      // 089: getfield okio/Pipe.buffer Lokio/Buffer;
      // 08c: invokevirtual okio/Buffer.size ()J
      // 08f: invokevirtual okio/Buffer.write (Lokio/Buffer;J)V
      // 092: aload 0
      // 093: getfield okio/Pipe.condition Ljava/util/concurrent/locks/Condition;
      // 096: invokeinterface java/util/concurrent/locks/Condition.signalAll ()V 1
      // 09b: nop
      // 09c: getstatic kotlin/Unit.INSTANCE Lkotlin/Unit;
      // 09f: astore 6
      // 0a1: aload 5
      // 0a3: invokeinterface java/util/concurrent/locks/Lock.unlock ()V 1
      // 0a8: goto 0b7
      // 0ab: astore 7
      // 0ad: aload 5
      // 0af: invokeinterface java/util/concurrent/locks/Lock.unlock ()V 1
      // 0b4: aload 7
      // 0b6: athrow
      // 0b7: iload 3
      // 0b8: ifeq 0c6
      // 0bb: iload 2
      // 0bc: ifeq 0c5
      // 0bf: aload 1
      // 0c0: invokeinterface okio/Sink.close ()V 1
      // 0c5: return
      // 0c6: bipush 0
      // 0c7: istore 5
      // 0c9: nop
      // 0ca: aload 1
      // 0cb: aload 4
      // 0cd: dup
      // 0ce: ifnonnull 0d8
      // 0d1: pop
      // 0d2: ldc "sinkBuffer"
      // 0d4: invokestatic kotlin/jvm/internal/Intrinsics.throwUninitializedPropertyAccessException (Ljava/lang/String;)V
      // 0d7: aconst_null
      // 0d8: aload 4
      // 0da: invokevirtual okio/Buffer.size ()J
      // 0dd: invokeinterface okio/Sink.write (Lokio/Buffer;J)V 4
      // 0e2: aload 1
      // 0e3: invokeinterface okio/Sink.flush ()V 1
      // 0e8: bipush 1
      // 0e9: istore 5
      // 0eb: nop
      // 0ec: goto 006
      // 0ef: astore 6
      // 0f1: aload 0
      // 0f2: getfield okio/Pipe.lock Ljava/util/concurrent/locks/ReentrantLock;
      // 0f5: checkcast java/util/concurrent/locks/Lock
      // 0f8: astore 7
      // 0fa: aload 7
      // 0fc: invokeinterface java/util/concurrent/locks/Lock.lock ()V 1
      // 101: nop
      // 102: bipush 0
      // 103: istore 8
      // 105: aload 0
      // 106: bipush 1
      // 107: putfield okio/Pipe.sourceClosed Z
      // 10a: aload 0
      // 10b: getfield okio/Pipe.condition Ljava/util/concurrent/locks/Condition;
      // 10e: invokeinterface java/util/concurrent/locks/Condition.signalAll ()V 1
      // 113: nop
      // 114: getstatic kotlin/Unit.INSTANCE Lkotlin/Unit;
      // 117: astore 8
      // 119: aload 7
      // 11b: invokeinterface java/util/concurrent/locks/Lock.unlock ()V 1
      // 120: goto 12f
      // 123: astore 9
      // 125: aload 7
      // 127: invokeinterface java/util/concurrent/locks/Lock.unlock ()V 1
      // 12c: aload 9
      // 12e: athrow
      // 12f: aload 6
      // 131: athrow
   }

   private inline fun Sink.forward(block: (Sink) -> Unit) {
      label50: {
         val `this_$iv`: Timeout = `$this$forward`.timeout();
         val `other$iv`: Timeout = this.sink().timeout();
         val `originalTimeout$iv`: Long = `this_$iv`.timeoutNanos();
         `this_$iv`.timeout(Timeout.Companion.minTimeout(`other$iv`.timeoutNanos(), `this_$iv`.timeoutNanos()), TimeUnit.NANOSECONDS);
         if (`this_$iv`.hasDeadline()) {
            val `originalDeadline$iv`: Long = `this_$iv`.deadlineNanoTime();
            if (`other$iv`.hasDeadline()) {
               `this_$iv`.deadlineNanoTime(Math.min(`this_$iv`.deadlineNanoTime(), `other$iv`.deadlineNanoTime()));
            }

            try {
               block.invoke(`$this$forward`);
            } catch (var17: java.lang.Throwable) {
               InlineMarker.finallyStart(1);
               `this_$iv`.timeout(`originalTimeout$iv`, TimeUnit.NANOSECONDS);
               if (`other$iv`.hasDeadline()) {
                  `this_$iv`.deadlineNanoTime(`originalDeadline$iv`);
               }

               InlineMarker.finallyEnd(1);
            }

            InlineMarker.finallyStart(1);
            `this_$iv`.timeout(`originalTimeout$iv`, TimeUnit.NANOSECONDS);
            if (`other$iv`.hasDeadline()) {
               `this_$iv`.deadlineNanoTime(`originalDeadline$iv`);
            }

            InlineMarker.finallyEnd(1);
         } else {
            if (`other$iv`.hasDeadline()) {
               `this_$iv`.deadlineNanoTime(`other$iv`.deadlineNanoTime());
            }

            try {
               block.invoke(`$this$forward`);
            } catch (var16: java.lang.Throwable) {
               InlineMarker.finallyStart(1);
               `this_$iv`.timeout(`originalTimeout$iv`, TimeUnit.NANOSECONDS);
               if (`other$iv`.hasDeadline()) {
                  `this_$iv`.clearDeadline();
               }

               InlineMarker.finallyEnd(1);
            }

            InlineMarker.finallyStart(1);
            `this_$iv`.timeout(`originalTimeout$iv`, TimeUnit.NANOSECONDS);
            if (`other$iv`.hasDeadline()) {
               `this_$iv`.clearDeadline();
            }

            InlineMarker.finallyEnd(1);
         }
      }
   }

   @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(expression = "sink", imports = []), level = DeprecationLevel.ERROR)
   @JvmName(name = "-deprecated_sink")
   public fun sink(): Sink {
      return this.sink;
   }

   @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(expression = "source", imports = []), level = DeprecationLevel.ERROR)
   @JvmName(name = "-deprecated_source")
   public fun source(): Source {
      return this.source;
   }

   public fun cancel() {
      label15: {
         val var1: Lock = this.lock;
         this.lock.lock();

         try {
            this.canceled = true;
            this.buffer.clear();
            this.condition.signalAll();
         } catch (var4: java.lang.Throwable) {
            var1.unlock();
         }

         var1.unlock();
      }
   }
}

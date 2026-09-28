package kotlinx.coroutines

import java.util.concurrent.locks.LockSupport
import kotlin.coroutines.CoroutineContext
import kotlin.jvm.internal.SourceDebugExtension

@SourceDebugExtension(["SMAP\nBuilders.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Builders.kt\nkotlinx/coroutines/BlockingCoroutine\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,112:1\n1#2:113\n*E\n"])
private class BlockingCoroutine<T>(parentContext: CoroutineContext, blockedThread: Thread, eventLoop: EventLoop?) : AbstractCoroutine(parentContext, true, true) {
   private final val blockedThread: Thread
   private final val eventLoop: EventLoop?

   protected open val isScopedCoroutine: Boolean
      protected open get() {
         return true;
      }


   init {
      this.blockedThread = blockedThread;
      this.eventLoop = eventLoop;
   }

   protected override fun afterCompletion(state: Any?) {
      if (!(Thread.currentThread() == this.blockedThread)) {
         val var2: Thread = this.blockedThread;
         val var10000: AbstractTimeSource = AbstractTimeSourceKt.access$getTimeSource$p();
         if (var10000 != null) {
            var10000.unpark(var2);
         } else {
            LockSupport.unpark(var2);
         }
      }
   }

   public fun joinBlocking(): Any {
      // $VF: Couldn't be decompiled
      // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
      // java.lang.IndexOutOfBoundsException: Index -1 out of bounds for length 0
      //   at java.base/jdk.internal.util.Preconditions.outOfBounds(Preconditions.java:100)
      //   at java.base/jdk.internal.util.Preconditions.outOfBoundsCheckIndex(Preconditions.java:106)
      //   at java.base/jdk.internal.util.Preconditions.checkIndex(Preconditions.java:302)
      //   at java.base/java.util.Objects.checkIndex(Objects.java:365)
      //   at java.base/java.util.ArrayList.remove(ArrayList.java:552)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.IfStatement.initExprents(IfStatement.java:276)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.initStatementExprents(ExprProcessor.java:189)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.initStatementExprents(ExprProcessor.java:192)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.initStatementExprents(ExprProcessor.java:192)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.processStatement(ExprProcessor.java:148)
      //
      // Bytecode:
      // 00: invokestatic kotlinx/coroutines/AbstractTimeSourceKt.access$getTimeSource$p ()Lkotlinx/coroutines/AbstractTimeSource;
      // 03: dup
      // 04: ifnull 0d
      // 07: invokevirtual kotlinx/coroutines/AbstractTimeSource.registerTimeLoopThread ()V
      // 0a: goto 0e
      // 0d: pop
      // 0e: nop
      // 0f: aload 0
      // 10: getfield kotlinx/coroutines/BlockingCoroutine.eventLoop Lkotlinx/coroutines/EventLoop;
      // 13: dup
      // 14: ifnull 20
      // 17: bipush 0
      // 18: bipush 1
      // 19: aconst_null
      // 1a: invokestatic kotlinx/coroutines/EventLoop.incrementUseCount$default (Lkotlinx/coroutines/EventLoop;ZILjava/lang/Object;)V
      // 1d: goto 21
      // 20: pop
      // 21: nop
      // 22: nop
      // 23: aload 0
      // 24: getfield kotlinx/coroutines/BlockingCoroutine.eventLoop Lkotlinx/coroutines/EventLoop;
      // 27: dup
      // 28: ifnull 31
      // 2b: invokevirtual kotlinx/coroutines/EventLoop.processNextEvent ()J
      // 2e: goto 35
      // 31: pop
      // 32: ldc2_w 9223372036854775807
      // 35: lstore 1
      // 36: aload 0
      // 37: invokevirtual kotlinx/coroutines/BlockingCoroutine.isCompleted ()Z
      // 3a: ifne 6a
      // 3d: invokestatic kotlinx/coroutines/AbstractTimeSourceKt.access$getTimeSource$p ()Lkotlinx/coroutines/AbstractTimeSource;
      // 40: dup
      // 41: ifnull 4c
      // 44: aload 0
      // 45: lload 1
      // 46: invokevirtual kotlinx/coroutines/AbstractTimeSource.parkNanos (Ljava/lang/Object;J)V
      // 49: goto 52
      // 4c: pop
      // 4d: aload 0
      // 4e: lload 1
      // 4f: invokestatic java/util/concurrent/locks/LockSupport.parkNanos (Ljava/lang/Object;J)V
      // 52: invokestatic java/lang/Thread.interrupted ()Z
      // 55: ifeq 22
      // 58: aload 0
      // 59: new java/lang/InterruptedException
      // 5c: dup
      // 5d: invokespecial java/lang/InterruptedException.<init> ()V
      // 60: checkcast java/lang/Throwable
      // 63: invokevirtual kotlinx/coroutines/BlockingCoroutine.cancelCoroutine (Ljava/lang/Throwable;)Z
      // 66: pop
      // 67: goto 22
      // 6a: aload 0
      // 6b: getfield kotlinx/coroutines/BlockingCoroutine.eventLoop Lkotlinx/coroutines/EventLoop;
      // 6e: dup
      // 6f: ifnull 7b
      // 72: bipush 0
      // 73: bipush 1
      // 74: aconst_null
      // 75: invokestatic kotlinx/coroutines/EventLoop.decrementUseCount$default (Lkotlinx/coroutines/EventLoop;ZILjava/lang/Object;)V
      // 78: goto 7c
      // 7b: pop
      // 7c: goto 94
      // 7f: astore 1
      // 80: aload 0
      // 81: getfield kotlinx/coroutines/BlockingCoroutine.eventLoop Lkotlinx/coroutines/EventLoop;
      // 84: dup
      // 85: ifnull 91
      // 88: bipush 0
      // 89: bipush 1
      // 8a: aconst_null
      // 8b: invokestatic kotlinx/coroutines/EventLoop.decrementUseCount$default (Lkotlinx/coroutines/EventLoop;ZILjava/lang/Object;)V
      // 8e: goto 92
      // 91: pop
      // 92: aload 1
      // 93: athrow
      // 94: invokestatic kotlinx/coroutines/AbstractTimeSourceKt.access$getTimeSource$p ()Lkotlinx/coroutines/AbstractTimeSource;
      // 97: dup
      // 98: ifnull a1
      // 9b: invokevirtual kotlinx/coroutines/AbstractTimeSource.unregisterTimeLoopThread ()V
      // 9e: goto a2
      // a1: pop
      // a2: goto b6
      // a5: astore 1
      // a6: invokestatic kotlinx/coroutines/AbstractTimeSourceKt.access$getTimeSource$p ()Lkotlinx/coroutines/AbstractTimeSource;
      // a9: dup
      // aa: ifnull b3
      // ad: invokevirtual kotlinx/coroutines/AbstractTimeSource.unregisterTimeLoopThread ()V
      // b0: goto b4
      // b3: pop
      // b4: aload 1
      // b5: athrow
      // b6: aload 0
      // b7: invokevirtual kotlinx/coroutines/BlockingCoroutine.getState$kotlinx_coroutines_core ()Ljava/lang/Object;
      // ba: invokestatic kotlinx/coroutines/JobSupportKt.unboxState (Ljava/lang/Object;)Ljava/lang/Object;
      // bd: astore 1
      // be: aload 1
      // bf: instanceof kotlinx/coroutines/CompletedExceptionally
      // c2: ifeq cc
      // c5: aload 1
      // c6: checkcast kotlinx/coroutines/CompletedExceptionally
      // c9: goto cd
      // cc: aconst_null
      // cd: astore 2
      // ce: aload 2
      // cf: ifnull dc
      // d2: aload 2
      // d3: astore 3
      // d4: bipush 0
      // d5: istore 4
      // d7: aload 3
      // d8: getfield kotlinx/coroutines/CompletedExceptionally.cause Ljava/lang/Throwable;
      // db: athrow
      // dc: aload 1
      // dd: areturn
   }
}

package io.ktor.network.selector

import io.ktor.network.selector.ActorSelectorManager.1
import java.io.Closeable
import java.nio.channels.ClosedChannelException
import java.nio.channels.ClosedSelectorException
import java.nio.channels.SelectionKey
import java.nio.channels.Selector
import java.util.concurrent.atomic.AtomicLong
import java.util.concurrent.atomic.AtomicReference
import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.intrinsics.IntrinsicsKt
import kotlin.coroutines.jvm.internal.Boxing
import kotlin.coroutines.jvm.internal.DebugProbesKt
import kotlin.coroutines.jvm.internal.SpillingKt
import kotlin.jvm.internal.InlineMarker
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.coroutines.BuildersKt
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.YieldKt

@SourceDebugExtension(["SMAP\nActorSelectorManager.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ActorSelectorManager.kt\nio/ktor/network/selector/ActorSelectorManager\n+ 2 ActorSelectorManager.kt\nio/ktor/network/selector/ActorSelectorManager$ContinuationHolder\n*L\n1#1,209:1\n110#1,4:210\n200#2,6:214\n*S KotlinDebug\n*F\n+ 1 ActorSelectorManager.kt\nio/ktor/network/selector/ActorSelectorManager\n*L\n97#1:210,4\n169#1:214,6\n*E\n"])
public class ActorSelectorManager(context: CoroutineContext) : SelectorManagerSupport, Closeable, CoroutineScope {
   private final var selectorRef: Selector?
   private final val wakeup: AtomicLong = new AtomicLong()
   private final var inSelect: Boolean
   private final val continuation: io.ktor.network.selector.ActorSelectorManager.ContinuationHolder<Unit, Continuation<Unit>> =
      new ActorSelectorManager.ContinuationHolder()
      private final var closed: Boolean
   private final val selectionQueue: LockFreeMPSCQueue<Selectable> = new LockFreeMPSCQueue()
   public open val coroutineContext: CoroutineContext

   init {
      this.coroutineContext = context.plus(new CoroutineName("selector"));
      BuildersKt.launch$default(this, null, null, new 1(this, null), 3, null);
   }

   private suspend fun process(mb: LockFreeMPSCQueue<Selectable>, selector: Selector) {
      var `$continuation`: Continuation;
      label102: {
         if (`$completion` is io.ktor.network.selector.ActorSelectorManager.process.1) {
            `$continuation` = `$completion` as io.ktor.network.selector.ActorSelectorManager.process.1;
            if (((`$completion` as io.ktor.network.selector.ActorSelectorManager.process.1).label and Integer.MIN_VALUE) != 0) {
               `$continuation`.label -= Integer.MIN_VALUE;
               break label102;
            }
         }

         `$continuation` = new io.ktor.network.selector.ActorSelectorManager.process.1(this, `$completion`);
      }

      val `$result`: Any = `$continuation`.result;
      val var7: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
      switch ($continuation.label) {
         case 0:
            ResultKt.throwOnFailure(`$result`);
            if (this.closed) {
               return Unit.INSTANCE;
            }
            break;
         case 1:
            selector = `$continuation`.L$1 as Selector;
            mb = `$continuation`.L$0 as LockFreeMPSCQueue;
            ResultKt.throwOnFailure(`$result`);
            if ((`$result` as java.lang.Number).intValue() > 0) {
               val var10001: java.util.Set = selector.selectedKeys();
               val var10002: java.util.Set = selector.keys();
               this.handleSelectedKeys(var10001, var10002);
            } else {
               val var9: Selectable = mb.removeFirstOrNull() as Selectable;
               if (var9 != null) {
                  this.applyInterest(selector, var9);
               } else {
                  `$continuation`.L$0 = mb;
                  `$continuation`.L$1 = selector;
                  `$continuation`.L$2 = SpillingKt.nullOutSpilledVariable(var9);
                  `$continuation`.label = 2;
                  if (YieldKt.yield(`$continuation`) === var7) {
                     return var7;
                  }
               }
            }

            if (this.closed) {
               return Unit.INSTANCE;
            }
            break;
         case 2:
            val var8: Selectable = `$continuation`.L$2 as Selectable;
            selector = `$continuation`.L$1 as Selector;
            mb = `$continuation`.L$0 as LockFreeMPSCQueue;
            ResultKt.throwOnFailure(`$result`);
            if (this.closed) {
               return Unit.INSTANCE;
            }
            break;
         case 3:
            selector = `$continuation`.L$1 as Selector;
            mb = `$continuation`.L$0 as LockFreeMPSCQueue;
            ResultKt.throwOnFailure(`$result`);
            val var10000: Selectable = `$result` as Selectable;
            if (`$result` as Selectable == null) {
               return Unit.INSTANCE;
            }

            this.applyInterest(selector, var10000);
            if (this.closed) {
               return Unit.INSTANCE;
            }
            break;
         default:
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
      }

      while (true) {
         this.processInterests(mb, selector);
         if (this.getPending() > 0) {
            `$continuation`.L$0 = mb;
            `$continuation`.L$1 = selector;
            `$continuation`.L$2 = null;
            `$continuation`.label = 1;
            val var14: Any = this.select(selector, `$continuation`);
            if (var14 === var7) {
               return var7;
            }

            if ((var14 as java.lang.Number).intValue() > 0) {
               val var16: java.util.Set = selector.selectedKeys();
               val var18: java.util.Set = selector.keys();
               this.handleSelectedKeys(var16, var18);
            } else {
               val var11: Selectable = mb.removeFirstOrNull() as Selectable;
               if (var11 != null) {
                  this.applyInterest(selector, var11);
               } else {
                  `$continuation`.L$0 = mb;
                  `$continuation`.L$1 = selector;
                  `$continuation`.L$2 = SpillingKt.nullOutSpilledVariable(var11);
                  `$continuation`.label = 2;
                  if (YieldKt.yield(`$continuation`) === var7) {
                     return var7;
                  }
               }
            }

            if (this.closed) {
               break;
            }
         } else {
            if (this.getCancelled() > 0) {
               selector.selectNow();
               if (this.getPending() > 0) {
                  val var15: java.util.Set = selector.selectedKeys();
                  val var17: java.util.Set = selector.keys();
                  this.handleSelectedKeys(var15, var17);
               } else {
                  this.setCancelled(0);
               }
            } else {
               `$continuation`.L$0 = mb;
               `$continuation`.L$1 = selector;
               `$continuation`.L$2 = null;
               `$continuation`.label = 3;
               var var12: Any = this.receiveOrNull(mb, `$continuation`);
               if (var12 === var7) {
                  return var7;
               }

               var12 = var12 as Selectable;
               if (var12 as Selectable == null) {
                  break;
               }

               this.applyInterest(selector, (Selectable)var12);
            }

            if (this.closed) {
               break;
            }
         }
      }

      return Unit.INSTANCE;
   }

   private suspend fun select(selector: Selector): Int {
      var `$continuation`: Continuation;
      label25: {
         if (`$completion` is io.ktor.network.selector.ActorSelectorManager.select.1) {
            `$continuation` = `$completion` as io.ktor.network.selector.ActorSelectorManager.select.1;
            if (((`$completion` as io.ktor.network.selector.ActorSelectorManager.select.1).label and Integer.MIN_VALUE) != 0) {
               `$continuation`.label -= Integer.MIN_VALUE;
               break label25;
            }
         }

         `$continuation` = new io.ktor.network.selector.ActorSelectorManager.select.1(this, `$completion`);
      }

      val `$result`: Any = `$continuation`.result;
      val var7: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
      switch ($continuation.label) {
         case 0:
            ResultKt.throwOnFailure(`$result`);
            this.inSelect = true;
            `$continuation`.L$0 = selector;
            `$continuation`.L$1 = SpillingKt.nullOutSpilledVariable(this);
            `$continuation`.I$0 = 0;
            `$continuation`.label = 1;
            if (YieldKt.yield(`$continuation`) === var7) {
               return var7;
            }
            break;
         case 1:
            val `$i$f$dispatchIfNeeded`: Int = `$continuation`.I$0;
            val count: ActorSelectorManager = `$continuation`.L$1 as ActorSelectorManager;
            selector = `$continuation`.L$0 as Selector;
            ResultKt.throwOnFailure(`$result`);
            break;
         default:
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
      }

      val var10000: Int;
      if (this.wakeup.get() == 0L) {
         val var8: Int = selector.select(500L);
         this.inSelect = false;
         var10000 = var8;
      } else {
         this.inSelect = false;
         this.wakeup.set(0L);
         var10000 = selector.selectNow();
      }

      return Boxing.boxInt(var10000);
   }

   private suspend inline fun dispatchIfNeeded() {
      InlineMarker.mark(0);
      YieldKt.yield(`$completion`);
      InlineMarker.mark(1);
      return Unit.INSTANCE;
   }

   private fun selectWakeup() {
      if (this.wakeup.incrementAndGet() == 1L && this.inSelect) {
         if (this.selectorRef != null) {
            this.selectorRef.wakeup();
         }
      }
   }

   private fun processInterests(mb: LockFreeMPSCQueue<Selectable>, selector: Selector) {
      while (true) {
         val var10000: Selectable = mb.removeFirstOrNull() as Selectable;
         if (var10000 == null) {
            return;
         }

         this.applyInterest(selector, var10000);
      }
   }

   public override fun notifyClosed(selectable: Selectable) {
      this.cancelAllSuspensions(selectable, new ClosedChannelException());
      if (this.selectorRef != null) {
         val selector: Selector = this.selectorRef;
         val var10000: SelectionKey = selectable.getChannel().keyFor(selector);
         if (var10000 != null) {
            var10000.cancel();
            this.selectWakeup();
         }
      }
   }

   protected override fun publishInterest(selectable: Selectable) {
      try {
         if (!this.selectionQueue.addLast(selectable)) {
            if (selectable.getChannel().isOpen()) {
               throw new ClosedSelectorException();
            }

            throw new ClosedChannelException();
         }

         this.continuation.resume(Unit.INSTANCE);
         this.selectWakeup();
      } catch (var3: java.lang.Throwable) {
         this.cancelAllSuspensions(selectable, var3);
      }
   }

   private suspend fun LockFreeMPSCQueue<Selectable>.receiveOrNull(): Selectable? {
      val var10000: Selectable = `$this$receiveOrNull`.removeFirstOrNull() as Selectable;
      return if (var10000 == null) this.receiveOrNullSuspend(`$this$receiveOrNull`, `$completion`) else var10000;
   }

   private suspend fun LockFreeMPSCQueue<Selectable>.receiveOrNullSuspend(): Selectable? {
      var `$continuation`: Continuation;
      label82: {
         if (`$completion` is io.ktor.network.selector.ActorSelectorManager.receiveOrNullSuspend.1) {
            `$continuation` = `$completion` as io.ktor.network.selector.ActorSelectorManager.receiveOrNullSuspend.1;
            if (((`$completion` as io.ktor.network.selector.ActorSelectorManager.receiveOrNullSuspend.1).label and Integer.MIN_VALUE) != 0) {
               `$continuation`.label -= Integer.MIN_VALUE;
               break label82;
            }
         }

         `$continuation` = new io.ktor.network.selector.ActorSelectorManager.receiveOrNullSuspend.1(this, `$completion`);
      }

      val `$result`: Any = `$continuation`.result;
      val var12: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
      switch ($continuation.label) {
         case 0:
            ResultKt.throwOnFailure(`$result`);
            break;
         case 1:
            val selectable: Selectable = `$continuation`.L$1 as Selectable;
            `$this$receiveOrNullSuspend` = `$continuation`.L$0 as LockFreeMPSCQueue;
            ResultKt.throwOnFailure(`$result`);
            break;
         default:
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
      }

      var var10000: Any;
      do {
         val var13: Selectable = `$this$receiveOrNullSuspend`.removeFirstOrNull() as Selectable;
         if (var13 != null) {
            return var13;
         }

         if (this.closed) {
            return null;
         }

         `$continuation`.L$0 = `$this$receiveOrNullSuspend`;
         `$continuation`.L$1 = SpillingKt.nullOutSpilledVariable(var13);
         `$continuation`.label = 1;
         val it: Continuation = `$continuation`;
         val `this_$iv`: ActorSelectorManager.ContinuationHolder = this.continuation;
         if (!`$this$receiveOrNullSuspend`.isEmpty() || this.closed) {
            var10000 = null;
         } else {
            if (!ActorSelectorManager.ContinuationHolder.access$getRef$p(`this_$iv`).compareAndSet(null, it)) {
               throw new IllegalStateException("Continuation is already set");
            }

            var10000 = if ((!`$this$receiveOrNullSuspend`.isEmpty() || this.closed)
                  && ActorSelectorManager.ContinuationHolder.access$getRef$p(`this_$iv`).compareAndSet(it, null))
               null
               else
               IntrinsicsKt.getCOROUTINE_SUSPENDED();
         }

         if (var10000 == null) {
            var10000 = Unit.INSTANCE;
         }

         if (var10000 === IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(`$continuation`);
         }
      } while (var10000 != var12);

      return var12;
   }

   public override fun close() {
      this.closed = true;
      this.selectionQueue.close();
      if (!this.continuation.resume(Unit.INSTANCE)) {
         this.selectWakeup();
      }
   }

   private class ContinuationHolder<R, C extends Continuation<? super R>> {
      private final val ref: AtomicReference<Any?> = new AtomicReference(null)

      public fun resume(value: Any): Boolean {
         val var10000: Continuation = this.ref.getAndSet(null);
         if (var10000 == null) {
            return false;
         } else {
            var10000.resumeWith(Result.constructor-impl(value));
            return true;
         }
      }

      public inline fun suspendIf(continuation: Any, condition: () -> Boolean): Any? {
         if (!condition.invoke() as java.lang.Boolean) {
            return null;
         } else if (!access$getRef$p(this).compareAndSet(null, continuation)) {
            throw new IllegalStateException("Continuation is already set");
         } else {
            return if (!condition.invoke() && access$getRef$p(this).compareAndSet(continuation, null)) null else IntrinsicsKt.getCOROUTINE_SUSPENDED();
         }
      }
   }
}

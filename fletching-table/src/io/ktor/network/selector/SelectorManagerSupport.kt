package io.ktor.network.selector

import io.ktor.network.selector.SelectorManagerSupport.select.2.1
import java.nio.channels.CancelledKeyException
import java.nio.channels.ClosedChannelException
import java.nio.channels.SelectableChannel
import java.nio.channels.SelectionKey
import java.nio.channels.Selector
import java.nio.channels.spi.SelectorProvider
import java.util.concurrent.CancellationException
import kotlin.coroutines.intrinsics.IntrinsicsKt
import kotlin.coroutines.jvm.internal.DebugProbesKt
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.coroutines.CancellableContinuation
import kotlinx.coroutines.CancellableContinuationImpl

@SourceDebugExtension(["SMAP\nSelectorManagerSupport.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SelectorManagerSupport.kt\nio/ktor/network/selector/SelectorManagerSupport\n+ 2 CancellableContinuation.kt\nkotlinx/coroutines/CancellableContinuationKt\n+ 3 InterestSuspensionsMap.kt\nio/ktor/network/selector/InterestSuspensionsMap\n+ 4 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 5 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,187:1\n426#2,11:188\n32#3,8:199\n42#3,2:207\n45#3:210\n1#4:209\n1#4:212\n1869#5:211\n1870#5:213\n*S KotlinDebug\n*F\n+ 1 SelectorManagerSupport.kt\nio/ktor/network/selector/SelectorManagerSupport\n*L\n44#1:188,11\n86#1:199,8\n150#1:207,2\n150#1:210\n150#1:209\n161#1:211\n161#1:213\n*E\n"])
public abstract class SelectorManagerSupport : SelectorManager {
   public final val provider: SelectorProvider
   protected final var pending: Int
   protected final var cancelled: Int

   private final var subject: Selectable?
      private final get() {
         val var2: Any = `$this$subject`.attachment();
         return var2 as? Selectable;
      }

      private final set(newValue) {
         `$this$subject`.attach(newValue);
      }


   open fun SelectorManagerSupport() {
      val var10001: SelectorProvider = SelectorProvider.provider();
      this.provider = var10001;
   }

   protected abstract fun publishInterest(selectable: Selectable) {
   }

   public override suspend fun select(selectable: Selectable, interest: SelectInterest) {
      val interestedOps: Int = selectable.getInterestedOps();
      val flag: Int = interest.getFlag();
      if (selectable.isClosed()) {
         SelectorManagerSupportKt.access$selectableIsClosed();
         throw new KotlinNothingValueException();
      } else if ((interestedOps and flag) == 0) {
         SelectorManagerSupportKt.access$selectableIsInvalid(interestedOps, flag);
         throw new KotlinNothingValueException();
      } else {
         val `cancellable$iv`: CancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(`$completion`), 1);
         `cancellable$iv`.initCancellability();
         val continuation: CancellableContinuation = `cancellable$iv`;
         `cancellable$iv`.invokeOnCancellation(1.INSTANCE);
         selectable.getSuspensions().addSuspension(interest, continuation);
         if (!continuation.isCancelled()) {
            this.publishInterest(selectable);
         }

         val var10000: Any = `cancellable$iv`.getResult();
         if (var10000 === IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(`$completion`);
         }

         return if (var10000 === IntrinsicsKt.getCOROUTINE_SUSPENDED()) var10000 else Unit.INSTANCE;
      }
   }

   protected fun handleSelectedKeys(selectedKeys: MutableSet<SelectionKey>, keys: Set<SelectionKey>) {
      val selectedCount: Int = selectedKeys.size();
      this.pending = keys.size() - selectedCount;
      this.cancelled = 0;
      if (selectedCount > 0) {
         val iter: java.util.Iterator = selectedKeys.iterator();

         while (iter.hasNext()) {
            this.handleSelectedKey(iter.next() as SelectionKey);
            iter.remove();
         }
      }
   }

   protected fun handleSelectedKey(key: SelectionKey) {
      try {
         val readyOps: Int = key.readyOps();
         val cause: Int = key.interestOps();
         val var14: Selectable = this.getSubject(key);
         if (var14 == null) {
            key.cancel();
            val newOps: Int = this.cancelled++;
         } else {
            val var15: InterestSuspensionsMap = var14.getSuspensions();
            var var17: Int = readyOps;
            val `flags$iv`: IntArray = SelectInterest.Companion.getFlags();
            var `ordinal$iv`: Int = 0;

            for (int var10 = flags$iv.length; ordinal$iv < var10; ordinal$iv++) {
               if ((`flags$iv`[`ordinal$iv`] and var17) != 0) {
                  val var20: CancellableContinuation = var15.removeSuspension(`ordinal$iv`);
                  if (var20 != null) {
                     var20.resumeWith(Result.constructor-impl(Unit.INSTANCE));
                  }
               }
            }

            val var16: Int = cause and readyOps.inv();
            if ((cause and readyOps.inv()) != cause) {
               key.interestOps(var16);
            }

            if (var16 != 0) {
               var17 = this.pending++;
            }
         }
      } catch (var13: java.lang.Throwable) {
         key.cancel();
         val subject: Int = this.cancelled++;
         val var10000: Selectable = this.getSubject(key);
         if (var10000 != null) {
            this.cancelAllSuspensions(var10000, var13);
            this.setSubject(key, null);
         }
      }
   }

   protected fun applyInterest(selector: Selector, selectable: Selectable) {
      try {
         val cause: SelectableChannel = selectable.getChannel();
         val key: SelectionKey = cause.keyFor(selector);
         val ops: Int = selectable.getInterestedOps();
         if (key == null) {
            if (ops != 0) {
               cause.register(selector, ops, selectable);
            }
         } else if (key.interestOps() != ops) {
            key.interestOps(ops);
         }

         if (ops != 0) {
            val var6: Int = this.pending++;
         }
      } catch (var7: java.lang.Throwable) {
         val var10000: SelectionKey = selectable.getChannel().keyFor(selector);
         if (var10000 != null) {
            var10000.cancel();
         }

         this.cancelAllSuspensions(selectable, var7);
      }
   }

   protected fun notifyClosedImpl(selector: Selector, key: SelectionKey, attachment: Selectable) {
      this.cancelAllSuspensions(attachment, new ClosedChannelException());
      this.setSubject(key, null);
      selector.wakeup();
   }

   protected fun cancelAllSuspensions(attachment: Selectable, cause: Throwable) {
      val `this_$iv`: InterestSuspensionsMap = attachment.getSuspensions();

      for (SelectInterest interest$iv : SelectInterest.Companion.getAllInterests()) {
         val var10000: CancellableContinuation = `this_$iv`.removeSuspension(`interest$iv`);
         if (var10000 != null) {
            var10000.resumeWith(Result.constructor-impl(ResultKt.createFailure(cause)));
         }
      }
   }

   protected fun cancelAllSuspensions(selector: Selector, cause: Throwable?) {
      var var10000: java.lang.Throwable = cause;
      if (cause == null) {
         var10000 = new SelectorManagerSupport.ClosedSelectorCancellationException();
      }

      val currentCause: java.lang.Throwable = var10000;

      val `$this$forEach$iv`: java.lang.Iterable;
      for (Object element$iv : $this$forEach$iv) {
         val key: SelectionKey = `element$iv` as SelectionKey;

         try {
            if (key.isValid()) {
               key.interestOps(0);
            }
         } catch (var14: CancelledKeyException) {
         }

         val var11: Any = (`element$iv` as SelectionKey).attachment();
         val var16: Selectable = var11 as? Selectable;
         if ((var11 as? Selectable) != null) {
            this.cancelAllSuspensions(var16, currentCause);
         }

         (`element$iv` as SelectionKey).cancel();
      }
   }

   public class ClosedSelectorCancellationException : CancellationException("Closed selector")
}

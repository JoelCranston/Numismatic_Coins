package kotlinx.coroutines.flow.internal

import java.util.Arrays
import kotlin.coroutines.Continuation
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.coroutines.flow.StateFlow

@SourceDebugExtension(["SMAP\nAbstractSharedFlow.kt\nKotlin\n*S Kotlin\n*F\n+ 1 AbstractSharedFlow.kt\nkotlinx/coroutines/flow/internal/AbstractSharedFlow\n+ 2 Synchronized.common.kt\nkotlinx/coroutines/internal/Synchronized_commonKt\n+ 3 Synchronized.kt\nkotlinx/coroutines/internal/SynchronizedKt\n+ 4 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 5 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n*L\n1#1,130:1\n29#2:131\n29#2:133\n29#2:136\n16#3:132\n16#3:134\n16#3:137\n1#4:135\n13402#5,2:138\n*S KotlinDebug\n*F\n+ 1 AbstractSharedFlow.kt\nkotlinx/coroutines/flow/internal/AbstractSharedFlow\n*L\n27#1:131\n42#1:133\n73#1:136\n27#1:132\n42#1:134\n73#1:137\n92#1:138,2\n*E\n"])
internal abstract class AbstractSharedFlow<S extends AbstractSharedFlowSlot<?>> {
   protected final var slots: Array<Any?>?
      private set

   protected final var nCollectors: Int
      private set

   private final var nextIndex: Int
   private final var _subscriptionCount: SubscriptionCountStateFlow?

   public final val subscriptionCount: StateFlow<Int>
      public final get() {
         var var10: SubscriptionCountStateFlow;
         synchronized (this) {
            var10 = this._subscriptionCount;
            if (this._subscriptionCount == null) {
               val var5: SubscriptionCountStateFlow = new SubscriptionCountStateFlow(this.nCollectors);
               this._subscriptionCount = var5;
               var10 = var5;
            }

            var10 = var10;
         }

         return var10;
      }


   protected abstract fun createSlot(): Any {
   }

   protected abstract fun createSlotArray(size: Int): Array<Any?> {
   }

   protected fun allocateSlot(): Any {
      val var16: SubscriptionCountStateFlow;
      var var27: AbstractSharedFlowSlot;
      synchronized (this) {
         val var10000: Array<AbstractSharedFlowSlot>;
         if (this.slots == null) {
            val slot: Array<AbstractSharedFlowSlot> = this.createSlotArray(2);
            this.slots = (S[])slot;
            var10000 = slot;
         } else if (this.nCollectors >= this.slots.length) {
            val var25: Array<Any> = Arrays.copyOf(this.slots, 2 * this.slots.length);
            this.slots = (S[])var25;
            var10000 = var25 as Array<AbstractSharedFlowSlot>;
         } else {
            var10000 = this.slots;
         }

         val slots: Array<AbstractSharedFlowSlot> = var10000;
         var var18: Int = this.nextIndex;

         do {
            var27 = slots[var18];
            if (slots[var18] == null) {
               val var24: AbstractSharedFlowSlot = this.createSlot();
               slots[var18] = var24;
               var27 = var24;
            }

            if (++var18 >= slots.length) {
               var18 = 0;
            }
         } while (!var27.allocateLocked(this));

         this.nextIndex = var18;
         val var22: Int = this.nCollectors++;
         var16 = this._subscriptionCount;
         var27 = var27;
      }

      if (var16 != null) {
         var16.increment(1);
      }

      return (S)var27;
   }

   protected fun freeSlot(slot: Any) {
      val var12: SubscriptionCountStateFlow;
      val var10000: Array<Continuation>;
      synchronized (this) {
         this.nCollectors += -1;
         var12 = this._subscriptionCount;
         if (this.nCollectors == 0) {
            this.nextIndex = 0;
         }

         var10000 = slot.freeLocked(this);
      }

      for (Continuation cont : var10000) {
         if (cont != null) {
            cont.resumeWith(Result.constructor-impl(Unit.INSTANCE));
         }
      }

      if (var12 != null) {
         var12.increment(-1);
      }
   }

   protected inline fun forEachSlotLocked(block: (Any) -> Unit) {
      if (access$getNCollectors(this) != 0) {
         if (access$getSlots(this) != null) {
            val `$this$forEach$iv`: Any;
            for (Object element$iv : $this$forEach$iv) {
               if (`element$iv` != null) {
                  block.invoke(`element$iv`);
               }
            }
         }
      }
   }
}

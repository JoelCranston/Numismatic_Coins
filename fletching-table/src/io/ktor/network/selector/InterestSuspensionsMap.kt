package io.ktor.network.selector

import io.ktor.network.selector.InterestSuspensionsMap.Companion.updaters.1.property.1
import io.ktor.network.selector.InterestSuspensionsMap.Companion.updaters.1.property.2
import io.ktor.network.selector.InterestSuspensionsMap.Companion.updaters.1.property.3
import io.ktor.network.selector.InterestSuspensionsMap.Companion.updaters.1.property.4
import java.util.ArrayList
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater
import kotlin.jvm.internal.SourceDebugExtension
import kotlin.reflect.KMutableProperty1
import kotlinx.coroutines.CancellableContinuation

@SourceDebugExtension(["SMAP\nInterestSuspensionsMap.kt\nKotlin\n*S Kotlin\n*F\n+ 1 InterestSuspensionsMap.kt\nio/ktor/network/selector/InterestSuspensionsMap\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n+ 4 ArraysJVM.kt\nkotlin/collections/ArraysKt__ArraysJVMKt\n*L\n1#1,79:1\n1#2:80\n11561#3:81\n11896#3,3:82\n37#4,2:85\n*S KotlinDebug\n*F\n+ 1 InterestSuspensionsMap.kt\nio/ktor/network/selector/InterestSuspensionsMap\n*L\n59#1:81\n59#1:82,3\n71#1:85,2\n*E\n"])
public class InterestSuspensionsMap {
   private final var readHandlerReference: CancellableContinuation<Unit>?
   private final var writeHandlerReference: CancellableContinuation<Unit>?
   private final var connectHandlerReference: CancellableContinuation<Unit>?
   private final var acceptHandlerReference: CancellableContinuation<Unit>?

   public fun addSuspension(interest: SelectInterest, continuation: CancellableContinuation<Unit>) {
      if (!InterestSuspensionsMap.Companion.access$updater(Companion, interest).compareAndSet(this, null, continuation)) {
         throw new IllegalStateException(("Handler for ${interest.name()} is already registered").toString());
      }
   }

   public inline fun invokeForEachPresent(readyOps: Int, block: (CancellableContinuation<Unit>) -> Unit) {
      val flags: IntArray = SelectInterest.Companion.getFlags();
      var ordinal: Int = 0;

      for (int var6 = flags.length; ordinal < var6; ordinal++) {
         if ((flags[ordinal] and readyOps) != 0) {
            val var10000: CancellableContinuation = this.removeSuspension(ordinal);
            if (var10000 != null) {
               block.invoke(var10000);
            }
         }
      }
   }

   public inline fun invokeForEachPresent(block: (CancellableContinuation<Unit>, SelectInterest) -> Unit) {
      for (SelectInterest interest : SelectInterest.Companion.getAllInterests()) {
         val var10000: CancellableContinuation = this.removeSuspension(interest);
         if (var10000 != null) {
            block.invoke(var10000, interest);
         }
      }
   }

   public fun removeSuspension(interest: SelectInterest): CancellableContinuation<Unit>? {
      return InterestSuspensionsMap.Companion.access$updater(Companion, interest).getAndSet(this, null) as CancellableContinuation<Unit>;
   }

   public fun removeSuspension(interestOrdinal: Int): CancellableContinuation<Unit>? {
      return updaters[interestOrdinal].getAndSet(this, null);
   }

   public override fun toString(): String {
      return "R ${this.readHandlerReference} W ${this.writeHandlerReference} C ${this.connectHandlerReference} A ${this.acceptHandlerReference}";
   }

   @JvmStatic
   fun {
      val `$this$toTypedArray$iv`: Array<Any> = SelectInterest.Companion.getAllInterests();
      val `destination$iv$iv`: java.util.Collection = new ArrayList(`$this$toTypedArray$iv`.length);

      for (Object item$iv$iv : $this$map$iv) {
         var var10000: KMutableProperty1;
         switch (InterestSuspensionsMap.WhenMappings.$EnumSwitchMapping$0[((SelectInterest)item$iv$iv).ordinal()]) {
            case 1:
               var10000 = 1.INSTANCE;
               break;
            case 2:
               var10000 = 2.INSTANCE;
               break;
            case 3:
               var10000 = 3.INSTANCE;
               break;
            case 4:
               var10000 = 4.INSTANCE;
               break;
            default:
               throw new NoWhenBranchMatchedException();
         }

         val var14: AtomicReferenceFieldUpdater = AtomicReferenceFieldUpdater.newUpdater(
            InterestSuspensionsMap.class, CancellableContinuation.class, var10000.getName()
         );
         `destination$iv$iv`.add(var14);
      }

      updaters = (`destination$iv$iv` as java.util.List).toArray(new AtomicReferenceFieldUpdater[0]);
   }

   public companion object {
      private final val updaters: Array<AtomicReferenceFieldUpdater<InterestSuspensionsMap, CancellableContinuation<Unit>?>>

      private fun updater(interest: SelectInterest): AtomicReferenceFieldUpdater<InterestSuspensionsMap, CancellableContinuation<Unit>?> {
         return InterestSuspensionsMap.access$getUpdaters$cp()[interest.ordinal()];
      }
   }
}

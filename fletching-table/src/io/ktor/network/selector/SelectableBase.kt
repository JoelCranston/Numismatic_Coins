package io.ktor.network.selector

import java.util.concurrent.atomic.AtomicBoolean
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.coroutines.CancellableContinuation

@SourceDebugExtension(["SMAP\nSelectableBase.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SelectableBase.kt\nio/ktor/network/selector/SelectableBase\n+ 2 InterestSuspensionsMap.kt\nio/ktor/network/selector/InterestSuspensionsMap\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,43:1\n42#2,2:44\n45#2:47\n1#3:46\n*S KotlinDebug\n*F\n+ 1 SelectableBase.kt\nio/ktor/network/selector/SelectableBase\n*L\n38#1:44,2\n38#1:47\n38#1:46\n*E\n"])
internal abstract class SelectableBase : Selectable {
   private final val _isClosed: AtomicBoolean = new AtomicBoolean(false)
   public open val suspensions: InterestSuspensionsMap = new InterestSuspensionsMap()

   public open val isClosed: Boolean
      public open get() {
         return this._isClosed.get();
      }


   public open val interestedOps: Int
      public open get() {
         return this._interestedOps;
      }


   public override fun interestOp(interest: SelectInterest, state: Boolean) {
      val flag: Int = interest.getFlag();

      do {
         val before: Int = this._interestedOps;
      } while (!_interestedOps$FU.compareAndSet(this, this._interestedOps, state ? this._interestedOps | flag : this._interestedOps & ~flag));
   }

   public override fun close() {
      if (this._isClosed.compareAndSet(false, true)) {
         this._interestedOps = 0;
         val `this_$iv`: InterestSuspensionsMap = this.getSuspensions();

         for (SelectInterest interest$iv : SelectInterest.Companion.getAllInterests()) {
            val var10000: CancellableContinuation = `this_$iv`.removeSuspension(`interest$iv`);
            if (var10000 != null) {
               var10000.resumeWith(Result.constructor-impl(ResultKt.createFailure(new ClosedChannelCancellationException())));
            }
         }
      }
   }
}

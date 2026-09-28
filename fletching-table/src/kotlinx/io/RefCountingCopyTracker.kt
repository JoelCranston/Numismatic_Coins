package kotlinx.io

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater
import kotlin.jvm.internal.SourceDebugExtension

@SourceDebugExtension(["SMAP\nSegmentPool.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SegmentPool.kt\nkotlinx/io/RefCountingCopyTracker\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,305:1\n1#2:306\n*E\n"])
internal class RefCountingCopyTracker : SegmentCopyTracker {
   private final var copyCount: Int

   public open val shared: Boolean
      public open get() {
         return this.copyCount > 0;
      }


   public override fun addCopy() {
      fieldUpdater.incrementAndGet(this);
   }

   public override fun removeCopy(): Boolean {
      if (this.copyCount == 0) {
         return false;
      } else {
         val updatedValue: Int = fieldUpdater.decrementAndGet(this);
         if (updatedValue >= 0) {
            return true;
         } else if (updatedValue != -1) {
            throw new IllegalStateException(("Shared copies count is negative: ${updatedValue + 1}").toString());
         } else {
            this.copyCount = 0;
            return false;
         }
      }
   }

   public companion object {
      @JvmStatic
      private final val fieldUpdater: AtomicIntegerFieldUpdater<RefCountingCopyTracker>
   }
}

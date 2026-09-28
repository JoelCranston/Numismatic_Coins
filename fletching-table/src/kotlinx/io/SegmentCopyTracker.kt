package kotlinx.io

internal abstract class SegmentCopyTracker {
   public abstract val shared: Boolean

   public abstract fun addCopy() {
   }

   public abstract fun removeCopy(): Boolean {
   }
}

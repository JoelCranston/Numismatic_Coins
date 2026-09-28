package kotlinx.io

internal object AlwaysSharedCopyTracker : SegmentCopyTracker {
   public open val shared: Boolean
      public open get() {
         return true;
      }


   public override fun addCopy() {
   }

   public override fun removeCopy(): Boolean {
      return true;
   }
}

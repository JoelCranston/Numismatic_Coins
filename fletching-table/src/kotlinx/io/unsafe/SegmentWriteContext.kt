package kotlinx.io.unsafe

import kotlinx.io.Segment
import kotlinx.io.UnsafeIoApi

@UnsafeIoApi
public interface SegmentWriteContext {
   public abstract fun setUnchecked(segment: Segment, offset: Int, value: Byte) {
   }

   public abstract fun setUnchecked(segment: Segment, offset: Int, b0: Byte, b1: Byte) {
   }

   public abstract fun setUnchecked(segment: Segment, offset: Int, b0: Byte, b1: Byte, b2: Byte) {
   }

   public abstract fun setUnchecked(segment: Segment, offset: Int, b0: Byte, b1: Byte, b2: Byte, b3: Byte) {
   }
}

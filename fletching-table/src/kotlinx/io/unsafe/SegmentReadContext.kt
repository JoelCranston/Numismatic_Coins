package kotlinx.io.unsafe

import kotlinx.io.Segment
import kotlinx.io.UnsafeIoApi

@UnsafeIoApi
public interface SegmentReadContext {
   public abstract fun getUnchecked(segment: Segment, offset: Int): Byte {
   }
}

package kotlinx.io.unsafe

import kotlinx.io.Segment
import kotlinx.io.UnsafeIoApi

@UnsafeIoApi
public interface BufferIterationContext : SegmentReadContext {
   public abstract fun next(segment: Segment): Segment? {
   }
}

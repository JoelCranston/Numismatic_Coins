package kotlinx.io.unsafe

import kotlin.contracts.InvocationKind
import kotlinx.io.Segment
import kotlinx.io.UnsafeIoApi
import kotlinx.io.unsafe.UnsafeBufferOperationsKt.SegmentReadContextImpl.1

@UnsafeIoApi
@PublishedApi
internal final val SegmentReadContextImpl: SegmentReadContext = (new 1()) as SegmentReadContext

@UnsafeIoApi
@PublishedApi
internal final val SegmentWriteContextImpl: SegmentWriteContext =
   (new kotlinx.io.unsafe.UnsafeBufferOperationsKt.SegmentWriteContextImpl.1()) as SegmentWriteContext

@UnsafeIoApi
@PublishedApi
internal final val BufferIterationContextImpl: BufferIterationContext =
   (new kotlinx.io.unsafe.UnsafeBufferOperationsKt.BufferIterationContextImpl.1()) as BufferIterationContext

@UnsafeIoApi
@JvmSynthetic
public inline fun SegmentReadContext.withData(segment: Segment, readAction: (ByteArray, Int, Int) -> Unit) {
   contract {
      callsInPlace(readAction, InvocationKind.EXACTLY_ONCE)
   }

   readAction.invoke(segment.dataAsByteArray(true), segment.getPos(), segment.getLimit());
}

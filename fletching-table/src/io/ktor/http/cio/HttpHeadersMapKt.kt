package io.ktor.http.cio

import io.ktor.http.cio.HttpHeadersMapKt.IntArrayPool.1
import io.ktor.utils.io.pool.DefaultPool

private const val EXPECTED_HEADERS_QTY: Int = 128
private const val HEADER_SIZE: Int = 6
private const val HEADER_ARRAY_POOL_SIZE: Int = 1000
private const val HEADER_ARRAY_SIZE: Int = 768
private const val EMPTY_INDEX: Int = -1
private const val RESIZE_THRESHOLD: Double = 0.75
private const val OFFSET_NAME_HASH: Int = 0
private const val OFFSET_HEADER_NAME_START: Int = 1
private const val OFFSET_HEADER_NAME_END: Int = 2
private const val OFFSET_HEADER_VALUE_START: Int = 3
private const val OFFSET_HEADER_VALUE_END: Int = 4
private const val OFFSET_NEXT_HEADER: Int = 5
private final val IntArrayPool: DefaultPool<IntArray> = (new 1()) as DefaultPool
private final val HeadersDataPool: DefaultPool<HeadersData> = (new io.ktor.http.cio.HttpHeadersMapKt.HeadersDataPool.1()) as DefaultPool

internal fun HttpHeadersMap.dumpTo(indent: String, out: Appendable) {
   val var3: java.util.Iterator = `$this$dumpTo`.offsets().iterator();

   while (var3.hasNext()) {
      val offset: Int = (var3.next() as java.lang.Number).intValue();
      out.append(indent);
      out.append(`$this$dumpTo`.nameAtOffset(offset));
      out.append(" => ");
      out.append(`$this$dumpTo`.valueAtOffset(offset));
      out.append("\n");
   }
}

@JvmSynthetic
fun `access$getHeadersDataPool$p`(): DefaultPool {
   return HeadersDataPool;
}

@JvmSynthetic
fun `access$getIntArrayPool$p`(): DefaultPool {
   return IntArrayPool;
}

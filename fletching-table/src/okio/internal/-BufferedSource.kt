@file:JvmName(name = "-BufferedSource")

package okio.internal

import okio.BufferedSource
import okio.TypedOptions

internal inline fun <T : Any> BufferedSource.commonSelect(options: TypedOptions<T>): T? {
   val index: Int = `$this$commonSelect`.select(options.getOptions$okio());
   return (T)(if (index == -1) null else options.get(index));
}

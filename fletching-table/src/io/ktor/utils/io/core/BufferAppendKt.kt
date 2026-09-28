package io.ktor.utils.io.core

import kotlinx.io.Buffer

@Deprecated(message = "\n    We're migrating to the new kotlinx-io library.\n    This declaration is deprecated and will be removed in Ktor 4.0.0\n    If you have any problems with migration, please contact us in \n    https://youtrack.jetbrains.com/issue/KTOR-6030/Migrate-to-new-kotlinx.io-library\n    ", replaceWith = @ReplaceWith(expression = "write(other, min(other.size, maxSize.toLong())", imports = []), level = DeprecationLevel.ERROR)
internal fun Buffer.writeBufferAppend(other: Buffer, maxSize: Int): Int {
   val byteCount: Long = Math.min(other.getSize(), (long)maxSize);
   `$this$writeBufferAppend`.write(other, byteCount);
   return (int)byteCount;
}

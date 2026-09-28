package okio

import okio.internal.ZipFilesKt

@JvmSynthetic
internal class Okio__ZlibOkioKt {
   @Throws(java/io/IOException::class)
   @JvmStatic
   public fun FileSystem.openZip(zipPath: Path): FileSystem {
      return ZipFilesKt.openZip$default(zipPath, `$this$openZip`, null, 4, null);
   }
}

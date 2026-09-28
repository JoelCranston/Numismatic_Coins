package kotlin.io

import java.io.File
import java.io.IOException

public open class FileSystemException(file: File, other: File? = null, reason: String? = null) : IOException(
      ExceptionsKt.access$constructMessage(file, other, reason)
   ) {
   public final val file: File
   public final val other: File?
   public final val reason: String?

   init {
      this.file = file;
      this.other = other;
      this.reason = reason;
   }
}

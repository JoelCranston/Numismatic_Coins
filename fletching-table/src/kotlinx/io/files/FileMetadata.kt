package kotlinx.io.files

public class FileMetadata(isRegularFile: Boolean = false, isDirectory: Boolean = false, size: Long = 0L) {
   public final val isRegularFile: Boolean
   public final val isDirectory: Boolean
   public final val size: Long

   init {
      this.isRegularFile = isRegularFile;
      this.isDirectory = isDirectory;
      this.size = size;
   }

   fun FileMetadata() {
      this(false, false, 0L, 7, null);
   }
}

package okio.internal

import java.util.ArrayList
import okio.Path

internal class ZipEntry(canonicalPath: Path,
   isDirectory: Boolean = false,
   comment: String = "",
   crc: Long = -1L,
   compressedSize: Long = -1L,
   size: Long = -1L,
   compressionMethod: Int = -1,
   offset: Long = -1L,
   dosLastModifiedAtDate: Int = -1,
   dosLastModifiedAtTime: Int = -1,
   ntfsLastModifiedAtFiletime: Long? = null,
   ntfsLastAccessedAtFiletime: Long? = null,
   ntfsCreatedAtFiletime: Long? = null,
   extendedLastModifiedAtSeconds: Int? = null,
   extendedLastAccessedAtSeconds: Int? = null,
   extendedCreatedAtSeconds: Int? = null
) {
   public final val canonicalPath: Path
   public final val isDirectory: Boolean
   public final val comment: String
   public final val crc: Long
   public final val compressedSize: Long
   public final val size: Long
   public final val compressionMethod: Int
   public final val offset: Long
   public final val dosLastModifiedAtDate: Int
   public final val dosLastModifiedAtTime: Int
   public final val ntfsLastModifiedAtFiletime: Long?
   public final val ntfsLastAccessedAtFiletime: Long?
   public final val ntfsCreatedAtFiletime: Long?
   public final val extendedLastModifiedAtSeconds: Int?
   public final val extendedLastAccessedAtSeconds: Int?
   public final val extendedCreatedAtSeconds: Int?
   public final val children: MutableList<Path>

   internal final val lastAccessedAtMillis: Long?
      internal final get() {
         return if (this.ntfsLastAccessedAtFiletime != null)
            ZipFilesKt.filetimeToEpochMillis(this.ntfsLastAccessedAtFiletime)
            else
            (if (this.extendedLastAccessedAtSeconds != null) (long)this.extendedLastAccessedAtSeconds.intValue() * 1000L else null);
      }


   internal final val lastModifiedAtMillis: Long?
      internal final get() {
         return if (this.ntfsLastModifiedAtFiletime != null)
            ZipFilesKt.filetimeToEpochMillis(this.ntfsLastModifiedAtFiletime)
            else
            (
               if (this.extendedLastModifiedAtSeconds != null)
                  (long)this.extendedLastModifiedAtSeconds.intValue() * 1000L
                  else
                  (if (this.dosLastModifiedAtTime != -1) ZipFilesKt.dosDateTimeToEpochMillis(this.dosLastModifiedAtDate, this.dosLastModifiedAtTime) else null)
            );
      }


   internal final val createdAtMillis: Long?
      internal final get() {
         return if (this.ntfsCreatedAtFiletime != null)
            ZipFilesKt.filetimeToEpochMillis(this.ntfsCreatedAtFiletime)
            else
            (if (this.extendedCreatedAtSeconds != null) (long)this.extendedCreatedAtSeconds.intValue() * 1000L else null);
      }


   init {
      this.canonicalPath = canonicalPath;
      this.isDirectory = isDirectory;
      this.comment = comment;
      this.crc = crc;
      this.compressedSize = compressedSize;
      this.size = size;
      this.compressionMethod = compressionMethod;
      this.offset = offset;
      this.dosLastModifiedAtDate = dosLastModifiedAtDate;
      this.dosLastModifiedAtTime = dosLastModifiedAtTime;
      this.ntfsLastModifiedAtFiletime = ntfsLastModifiedAtFiletime;
      this.ntfsLastAccessedAtFiletime = ntfsLastAccessedAtFiletime;
      this.ntfsCreatedAtFiletime = ntfsCreatedAtFiletime;
      this.extendedLastModifiedAtSeconds = extendedLastModifiedAtSeconds;
      this.extendedLastAccessedAtSeconds = extendedLastAccessedAtSeconds;
      this.extendedCreatedAtSeconds = extendedCreatedAtSeconds;
      this.children = new ArrayList<>();
   }

   internal fun copy(extendedLastModifiedAtSeconds: Int?, extendedLastAccessedAtSeconds: Int?, extendedCreatedAtSeconds: Int?): ZipEntry {
      return new ZipEntry(
         this.canonicalPath,
         this.isDirectory,
         this.comment,
         this.crc,
         this.compressedSize,
         this.size,
         this.compressionMethod,
         this.offset,
         this.dosLastModifiedAtDate,
         this.dosLastModifiedAtTime,
         this.ntfsLastModifiedAtFiletime,
         this.ntfsLastAccessedAtFiletime,
         this.ntfsCreatedAtFiletime,
         extendedLastModifiedAtSeconds,
         extendedLastAccessedAtSeconds,
         extendedCreatedAtSeconds
      );
   }
}

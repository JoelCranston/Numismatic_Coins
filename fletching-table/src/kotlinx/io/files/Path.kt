package kotlinx.io.files

import java.io.File

public class Path internal constructor(file: File) {
   internal final val file: File

   public final val parent: Path?
      public final get() {
         val var10000: File = this.file.getParentFile();
         return if (var10000 == null) null else new Path(var10000);
      }


   public final val isAbsolute: Boolean
      public final get() {
         return this.file.isAbsolute();
      }


   public final val name: String
      public final get() {
         val var10000: java.lang.String = this.file.getName();
         return var10000;
      }


   init {
      this.file = file;
   }

   public override fun toString(): String {
      val var10000: java.lang.String = this.file.toString();
      return var10000;
   }

   public override operator fun equals(other: Any?): Boolean {
      if (this === other) {
         return true;
      } else {
         return other is Path && this.toString() == (other as Path).toString();
      }
   }

   public override fun hashCode(): Int {
      return this.toString().hashCode();
   }
}

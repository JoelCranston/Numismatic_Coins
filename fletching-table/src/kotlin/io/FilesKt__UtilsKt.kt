package kotlin.io

import java.io.Closeable
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
import java.util.ArrayList
import kotlin.io.FilesKt__UtilsKt.copyRecursively.1
import kotlin.jvm.functions.Function2
import kotlin.jvm.internal.SourceDebugExtension

@SourceDebugExtension(["SMAP\nUtils.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Utils.kt\nkotlin/io/FilesKt__UtilsKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Sequences.kt\nkotlin/sequences/SequencesKt___SequencesKt\n*L\n1#1,473:1\n1#2:474\n1292#3,3:475\n*S KotlinDebug\n*F\n+ 1 Utils.kt\nkotlin/io/FilesKt__UtilsKt\n*L\n347#1:475,3\n*E\n"])
internal class FilesKt__UtilsKt : FilesKt__FileTreeWalkKt {
   public final val extension: String
      public final get() {
         val var10000: java.lang.String = `$this$extension`.getName();
         return StringsKt.substringAfterLast(var10000, '.', "");
      }


   public final val invariantSeparatorsPath: String
      public final get() {
         var var1: java.lang.String;
         if (File.separatorChar != '/') {
            var1 = `$this$invariantSeparatorsPath`.getPath();
            var1 = StringsKt.replace$default(var1, File.separatorChar, '/', false, 4, null);
         } else {
            var1 = `$this$invariantSeparatorsPath`.getPath();
         }

         return var1;
      }


   public final val nameWithoutExtension: String
      public final get() {
         val var10000: java.lang.String = `$this$nameWithoutExtension`.getName();
         return StringsKt.substringBeforeLast$default(var10000, ".", null, 2, null);
      }


   @Deprecated(message = "Avoid creating temporary directories in the default temp location with this function due to too wide permissions on the newly created directory. Use kotlin.io.path.createTempDirectory instead.")
   @JvmStatic
   public fun createTempDir(prefix: String = "tmp", suffix: String? = null, directory: File? = null): File {
      val dir: File = File.createTempFile(prefix, suffix, directory);
      dir.delete();
      if (dir.mkdir()) {
         return dir;
      } else {
         throw new IOException("Unable to create temporary directory $dir.");
      }
   }

   @Deprecated(message = "Avoid creating temporary files in the default temp location with this function due to too wide permissions on the newly created file. Use kotlin.io.path.createTempFile instead or resort to java.io.File.createTempFile.")
   @JvmStatic
   public fun createTempFile(prefix: String = "tmp", suffix: String? = null, directory: File? = null): File {
      val var10000: File = File.createTempFile(prefix, suffix, directory);
      return var10000;
   }

   @JvmStatic
   public fun File.toRelativeString(base: File): String {
      val var10000: java.lang.String = toRelativeStringOrNull$FilesKt__UtilsKt(`$this$toRelativeString`, base);
      if (var10000 == null) {
         throw new IllegalArgumentException("this and base files have different roots: $`$this$toRelativeString` and $base.");
      } else {
         return var10000;
      }
   }

   @JvmStatic
   public fun File.relativeTo(base: File): File {
      return new File(FilesKt.toRelativeString(`$this$relativeTo`, base));
   }

   @JvmStatic
   public fun File.relativeToOrSelf(base: File): File {
      val var10000: java.lang.String = toRelativeStringOrNull$FilesKt__UtilsKt(`$this$relativeToOrSelf`, base);
      return if (var10000 != null) new File(var10000) else `$this$relativeToOrSelf`;
   }

   @JvmStatic
   public fun File.relativeToOrNull(base: File): File? {
      val var10000: java.lang.String = toRelativeStringOrNull$FilesKt__UtilsKt(`$this$relativeToOrNull`, base);
      return if (var10000 != null) new File(var10000) else null;
   }

   @JvmStatic
   private fun File.toRelativeStringOrNull(base: File): String? {
      val thisComponents: FilePathComponents = normalize$FilesKt__UtilsKt(FilesKt.toComponents(`$this$toRelativeStringOrNull`));
      val baseComponents: FilePathComponents = normalize$FilesKt__UtilsKt(FilesKt.toComponents(base));
      if (!(thisComponents.getRoot() == baseComponents.getRoot())) {
         return null;
      } else {
         val baseCount: Int = baseComponents.getSize();
         val thisCount: Int = thisComponents.getSize();
         var i: Int = 0;
         val maxSameCount: Int = Math.min(thisCount, baseCount);

         while (i < maxSameCount && thisComponents.getSegments().get(i) == baseComponents.getSegments().get(i)) {
            i++;
         }

         val sameCount: Int = i;
         val res: StringBuilder = new StringBuilder();
         var ix: Int = baseCount - 1;
         if (i <= baseCount - 1) {
            while (true) {
               if (baseComponents.getSegments().get(ix).getName() == "..") {
                  return null;
               }

               res.append("..");
               if (ix != sameCount) {
                  res.append(File.separatorChar);
               }

               if (ix == sameCount) {
                  break;
               }

               ix--;
            }
         }

         if (sameCount < thisCount) {
            if (sameCount < baseCount) {
               res.append(File.separatorChar);
            }

            val var10000: java.lang.Iterable = CollectionsKt.drop(thisComponents.getSegments(), sameCount);
            val var10001: Appendable = res;
            val var10002: java.lang.String = File.separator;
            CollectionsKt.joinTo$default(var10000, var10001, var10002, null, null, 0, null, null, 124, null);
         }

         return res.toString();
      }
   }

   @JvmStatic
   public fun File.copyTo(target: File, overwrite: Boolean = false, bufferSize: Int = 8192): File {
      if (!`$this$copyTo`.exists()) {
         throw new NoSuchFileException(`$this$copyTo`, null, "The source file doesn't exist.", 2, null);
      } else {
         label110: {
            if (target.exists()) {
               if (!overwrite) {
                  throw new FileAlreadyExistsException(`$this$copyTo`, target, "The destination file already exists.");
               }

               if (!target.delete()) {
                  throw new FileAlreadyExistsException(`$this$copyTo`, target, "Tried to overwrite the destination, but failed to delete it.");
               }
            }

            if (`$this$copyTo`.isDirectory()) {
               if (!target.mkdirs()) {
                  throw new FileSystemException(`$this$copyTo`, target, "Failed to create target directory.");
               }
            } else {
               val var10000: File = target.getParentFile();
               if (var10000 != null) {
                  var10000.mkdirs();
               }

               val var4: Closeable = new FileInputStream(`$this$copyTo`);
               var var5: java.lang.Throwable = null;

               try {
                  try {
                     val input: FileInputStream = var4 as FileInputStream;
                     val var8: Closeable = new FileOutputStream(target);
                     var var9: java.lang.Throwable = null;

                     try {
                        try {
                           val var25: Long = ByteStreamsKt.copyTo(input, var8 as FileOutputStream, bufferSize);
                        } catch (var13: java.lang.Throwable) {
                           var9 = var13;
                           throw var13;
                        }
                     } catch (var14: java.lang.Throwable) {
                        CloseableKt.closeFinally(var8, var9);
                     }

                     CloseableKt.closeFinally(var8, null);
                  } catch (var15: java.lang.Throwable) {
                     var5 = var15;
                     throw var15;
                  }
               } catch (var16: java.lang.Throwable) {
                  CloseableKt.closeFinally(var4, var5);
               }

               CloseableKt.closeFinally(var4, null);
            }

            return target;
         }
      }
   }

   @JvmStatic
   public fun File.copyRecursively(target: File, overwrite: Boolean = false, onError: (File, IOException) -> OnErrorAction = 1.INSTANCE as Function2): Boolean {
      if (!`$this$copyRecursively`.exists()) {
         return onError.invoke(`$this$copyRecursively`, new NoSuchFileException(`$this$copyRecursively`, null, "The source file doesn't exist.", 2, null))
            != OnErrorAction.TERMINATE;
      } else {
         try {
            for (File src : FilesKt.walkTopDown($this$copyRecursively).onFail(FilesKt__UtilsKt::copyRecursively$lambda$0$FilesKt__UtilsKt)) {
               if (!e.exists()) {
                  if (onError.invoke(e, new NoSuchFileException(e, null, "The source file doesn't exist.", 2, null)) === OnErrorAction.TERMINATE) {
                     return false;
                  }
               } else {
                  val dstFile: File = new File(target, FilesKt.toRelativeString(e, `$this$copyRecursively`));
                  if (dstFile.exists()
                     && (!e.isDirectory() || !dstFile.isDirectory())
                     && (!overwrite || (if (dstFile.isDirectory()) !FilesKt.deleteRecursively(dstFile) else !dstFile.delete()))) {
                     if (onError.invoke(dstFile, new FileAlreadyExistsException(e, dstFile, "The destination file already exists.")) === OnErrorAction.TERMINATE
                        )
                      {
                        return false;
                     }
                     continue;
                  } else if (e.isDirectory()) {
                     dstFile.mkdirs();
                  } else if (FilesKt.copyTo$default(e, dstFile, overwrite, 0, 4, null).length() != e.length()
                     && onError.invoke(e, new IOException("Source file wasn't copied completely, length of destination file differs.")) === OnErrorAction.TERMINATE
                     )
                   {
                     return false;
                  }
               }
            }

            return true;
         } catch (var9: TerminateException) {
            return false;
         }
      }
   }

   @JvmStatic
   public fun File.deleteRecursively(): Boolean {
      val `$this$fold$iv`: Sequence = FilesKt.walkBottomUp(`$this$deleteRecursively`);
      var `accumulator$iv`: Boolean = true;

      for (Object element$iv : $this$fold$iv) {
         `accumulator$iv` = ((`element$iv` as File).delete() || !(`element$iv` as File).exists()) && `accumulator$iv`;
      }

      return `accumulator$iv`;
   }

   @JvmStatic
   public fun File.startsWith(other: File): Boolean {
      val components: FilePathComponents = FilesKt.toComponents(`$this$startsWith`);
      val otherComponents: FilePathComponents = FilesKt.toComponents(other);
      if (!(components.getRoot() == otherComponents.getRoot())) {
         return false;
      } else {
         return components.getSize() >= otherComponents.getSize()
            && components.getSegments().subList(0, otherComponents.getSize()).equals(otherComponents.getSegments());
      }
   }

   @JvmStatic
   public fun File.startsWith(other: String): Boolean {
      return FilesKt.startsWith(`$this$startsWith`, new File(other));
   }

   @JvmStatic
   public fun File.endsWith(other: File): Boolean {
      val components: FilePathComponents = FilesKt.toComponents(`$this$endsWith`);
      val otherComponents: FilePathComponents = FilesKt.toComponents(other);
      if (otherComponents.isRooted()) {
         return `$this$endsWith` == other;
      } else {
         val shift: Int = components.getSize() - otherComponents.getSize();
         return shift >= 0 && components.getSegments().subList(shift, components.getSize()).equals(otherComponents.getSegments());
      }
   }

   @JvmStatic
   public fun File.endsWith(other: String): Boolean {
      return FilesKt.endsWith(`$this$endsWith`, new File(other));
   }

   @JvmStatic
   public fun File.normalize(): File {
      val `$this$normalize_u24lambda_u240`: FilePathComponents = FilesKt.toComponents(`$this$normalize`);
      val var10000: File = `$this$normalize_u24lambda_u240`.getRoot();
      val var10001: java.lang.Iterable = normalize$FilesKt__UtilsKt(`$this$normalize_u24lambda_u240`.getSegments());
      val var10002: java.lang.String = File.separator;
      return FilesKt.resolve(var10000, CollectionsKt.joinToString$default(var10001, var10002, null, null, 0, null, null, 62, null));
   }

   @JvmStatic
   private fun FilePathComponents.normalize(): FilePathComponents {
      return new FilePathComponents(`$this$normalize`.getRoot(), normalize$FilesKt__UtilsKt(`$this$normalize`.getSegments()));
   }

   @JvmStatic
   private fun List<File>.normalize(): List<File> {
      val list: java.util.List = new ArrayList(`$this$normalize`.size());

      for (File file : $this$normalize) {
         val var4: java.lang.String = file.getName();
         if (!(var4 == ".")) {
            if (var4 == "..") {
               if (!list.isEmpty() && !(CollectionsKt.<File>last(list).getName() == "..")) {
                  val var5: java.lang.Comparable = list.remove(list.size() - 1) as java.lang.Comparable;
               } else {
                  val var10000: java.lang.Comparable = list.add(file);
               }
            } else {
               list.add(file);
            }
         }
      }

      return list;
   }

   @JvmStatic
   public fun File.resolve(relative: File): File {
      if (FilesKt.isRooted(relative)) {
         return relative;
      } else {
         val var10000: java.lang.String = `$this$resolve`.toString();
         return if (var10000.length() != 0 && !StringsKt.endsWith$default(var10000, File.separatorChar, false, 2, null))
            new File("$var10000${File.separatorChar}$relative")
            else
            new File("$var10000$relative");
      }
   }

   @JvmStatic
   public fun File.resolve(relative: String): File {
      return FilesKt.resolve(`$this$resolve`, new File(relative));
   }

   @JvmStatic
   public fun File.resolveSibling(relative: File): File {
      val components: FilePathComponents = FilesKt.toComponents(`$this$resolveSibling`);
      return FilesKt.resolve(
         FilesKt.resolve(components.getRoot(), if (components.getSize() == 0) new File("..") else components.subPath(0, components.getSize() - 1)), relative
      );
   }

   @JvmStatic
   public fun File.resolveSibling(relative: String): File {
      return FilesKt.resolveSibling(`$this$resolveSibling`, new File(relative));
   }

   @JvmStatic
   fun `copyRecursively$lambda$0$FilesKt__UtilsKt`(`$onError`: Function2, f: File, e: IOException): Unit {
      if (`$onError`.invoke(f, e) === OnErrorAction.TERMINATE) {
         throw new TerminateException(f);
      } else {
         return Unit.INSTANCE;
      }
   }

   open fun FilesKt__UtilsKt() {
   }
}

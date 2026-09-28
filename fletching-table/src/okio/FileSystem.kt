package okio

import java.io.Closeable
import kotlin.contracts.InvocationKind
import kotlin.jvm.internal.InlineMarker
import kotlin.jvm.internal.SourceDebugExtension
import okio.internal.-FileSystem
import okio.internal.ResourceFileSystem

@SourceDebugExtension(["SMAP\nFileSystem.kt\nKotlin\n*S Kotlin\n*F\n+ 1 FileSystem.kt\nokio/FileSystem\n+ 2 Okio.kt\nokio/Okio__OkioKt\n*L\n1#1,191:1\n58#2,22:192\n58#2,22:214\n*S KotlinDebug\n*F\n+ 1 FileSystem.kt\nokio/FileSystem\n*L\n73#1:192,22\n95#1:214,22\n*E\n"])
public abstract class FileSystem : Closeable {
   @Throws(java/io/IOException::class)
   public abstract fun canonicalize(path: Path): Path {
   }

   @Throws(java/io/IOException::class)
   public fun metadata(path: Path): FileMetadata {
      return -FileSystem.commonMetadata(this, path);
   }

   @Throws(java/io/IOException::class)
   public abstract fun metadataOrNull(path: Path): FileMetadata? {
   }

   @Throws(java/io/IOException::class)
   public fun exists(path: Path): Boolean {
      return -FileSystem.commonExists(this, path);
   }

   @Throws(java/io/IOException::class)
   public abstract fun list(dir: Path): List<Path> {
   }

   public abstract fun listOrNull(dir: Path): List<Path>? {
   }

   public open fun listRecursively(dir: Path, followSymlinks: Boolean = false): Sequence<Path> {
      return -FileSystem.commonListRecursively(this, dir, followSymlinks);
   }

   public fun listRecursively(dir: Path): Sequence<Path> {
      return this.listRecursively(dir, false);
   }

   @Throws(java/io/IOException::class)
   public abstract fun openReadOnly(file: Path): FileHandle {
   }

   @Throws(java/io/IOException::class)
   public abstract fun openReadWrite(file: Path, mustCreate: Boolean = false, mustExist: Boolean = false): FileHandle {
   }

   @Throws(java/io/IOException::class)
   public fun openReadWrite(file: Path): FileHandle {
      return this.openReadWrite(file, false, false);
   }

   @Throws(java/io/IOException::class)
   public abstract fun source(file: Path): Source {
   }

   @JvmName(name = "-read")
   @Throws(java/io/IOException::class)
   public inline fun <T> read(file: Path, readerAction: (BufferedSource) -> T): T {
      contract {
         callsInPlace(readerAction, InvocationKind.EXACTLY_ONCE)
      }

      label74: {
         val `$this$use$iv`: Closeable = Okio.buffer(this.source(file));
         var `thrown$iv`: java.lang.Throwable = null;

         var var8: Any;
         label75: {
            label76: {
               try {
                  try {
                     break label76;
                  } catch (var15: java.lang.Throwable) {
                     `thrown$iv` = var15;
                     var8 = null;
                  }
               } catch (var16: java.lang.Throwable) {
                  InlineMarker.finallyStart(1);

                  try {
                     if (`$this$use$iv` != null) {
                        `$this$use$iv`.close();
                     }
                  } catch (var13: java.lang.Throwable) {
                     if (`thrown$iv` != null) {
                        ExceptionsKt.addSuppressed(`thrown$iv`, var13);
                     }
                  }

                  InlineMarker.finallyEnd(1);
               }

               InlineMarker.finallyStart(1);

               try {
                  if (`$this$use$iv` != null) {
                     `$this$use$iv`.close();
                  }
               } catch (var14: java.lang.Throwable) {
                  if (`thrown$iv` == null) {
                     `thrown$iv` = var14;
                  } else {
                     ExceptionsKt.addSuppressed(`thrown$iv`, var14);
                  }
               }

               InlineMarker.finallyEnd(1);
               break label75;
            }

            InlineMarker.finallyStart(1);

            try {
               if (`$this$use$iv` != null) {
                  `$this$use$iv`.close();
               }
            } catch (var12: java.lang.Throwable) {
               `thrown$iv` = var12;
            }

            InlineMarker.finallyEnd(1);
         }

         if (`thrown$iv` != null) {
            throw `thrown$iv`;
         } else {
            return (T)var8;
         }
      }
   }

   @Throws(java/io/IOException::class)
   public abstract fun sink(file: Path, mustCreate: Boolean = false): Sink {
   }

   @Throws(java/io/IOException::class)
   public fun sink(file: Path): Sink {
      return this.sink(file, false);
   }

   @JvmName(name = "-write")
   @Throws(java/io/IOException::class)
   public inline fun <T> write(file: Path, mustCreate: Boolean = ..., writerAction: (BufferedSink) -> T): T {
      contract {
         callsInPlace(writerAction, InvocationKind.EXACTLY_ONCE)
      }

      label74: {
         val `$this$use$iv`: Closeable = Okio.buffer(this.sink(file, mustCreate));
         var `thrown$iv`: java.lang.Throwable = null;

         var var9: Any;
         label75: {
            label76: {
               try {
                  try {
                     break label76;
                  } catch (var16: java.lang.Throwable) {
                     `thrown$iv` = var16;
                     var9 = null;
                  }
               } catch (var17: java.lang.Throwable) {
                  InlineMarker.finallyStart(1);

                  try {
                     if (`$this$use$iv` != null) {
                        `$this$use$iv`.close();
                     }
                  } catch (var14: java.lang.Throwable) {
                     if (`thrown$iv` != null) {
                        ExceptionsKt.addSuppressed(`thrown$iv`, var14);
                     }
                  }

                  InlineMarker.finallyEnd(1);
               }

               InlineMarker.finallyStart(1);

               try {
                  if (`$this$use$iv` != null) {
                     `$this$use$iv`.close();
                  }
               } catch (var15: java.lang.Throwable) {
                  if (`thrown$iv` == null) {
                     `thrown$iv` = var15;
                  } else {
                     ExceptionsKt.addSuppressed(`thrown$iv`, var15);
                  }
               }

               InlineMarker.finallyEnd(1);
               break label75;
            }

            InlineMarker.finallyStart(1);

            try {
               if (`$this$use$iv` != null) {
                  `$this$use$iv`.close();
               }
            } catch (var13: java.lang.Throwable) {
               `thrown$iv` = var13;
            }

            InlineMarker.finallyEnd(1);
         }

         if (`thrown$iv` != null) {
            throw `thrown$iv`;
         } else {
            return (T)var9;
         }
      }
   }

   @Throws(java/io/IOException::class)
   public abstract fun appendingSink(file: Path, mustExist: Boolean = false): Sink {
   }

   @Throws(java/io/IOException::class)
   public fun appendingSink(file: Path): Sink {
      return this.appendingSink(file, false);
   }

   @Throws(java/io/IOException::class)
   public abstract fun createDirectory(dir: Path, mustCreate: Boolean = false) {
   }

   @Throws(java/io/IOException::class)
   public fun createDirectory(dir: Path) {
      this.createDirectory(dir, false);
   }

   @Throws(java/io/IOException::class)
   public fun createDirectories(dir: Path, mustCreate: Boolean = false) {
      -FileSystem.commonCreateDirectories(this, dir, mustCreate);
   }

   @Throws(java/io/IOException::class)
   public fun createDirectories(dir: Path) {
      this.createDirectories(dir, false);
   }

   @Throws(java/io/IOException::class)
   public abstract fun atomicMove(source: Path, target: Path) {
   }

   @Throws(java/io/IOException::class)
   public open fun copy(source: Path, target: Path) {
      -FileSystem.commonCopy(this, source, target);
   }

   @Throws(java/io/IOException::class)
   public abstract fun delete(path: Path, mustExist: Boolean = false) {
   }

   @Throws(java/io/IOException::class)
   public fun delete(path: Path) {
      this.delete(path, false);
   }

   @Throws(java/io/IOException::class)
   public open fun deleteRecursively(fileOrDirectory: Path, mustExist: Boolean = false) {
      -FileSystem.commonDeleteRecursively(this, fileOrDirectory, mustExist);
   }

   @Throws(java/io/IOException::class)
   public fun deleteRecursively(fileOrDirectory: Path) {
      this.deleteRecursively(fileOrDirectory, false);
   }

   @Throws(java/io/IOException::class)
   public abstract fun createSymlink(source: Path, target: Path) {
   }

   @Throws(java/io/IOException::class)
   public override fun close() {
   }

   @JvmStatic
   fun {
      val `$this$SYSTEM_u24lambda_u240`: FileSystem.Companion = Companion;

      var var10000: JvmSystemFileSystem;
      try {
         Class.forName("java.nio.file.Files");
         var10000 = new NioSystemFileSystem();
      } catch (var3: ClassNotFoundException) {
         var10000 = new JvmSystemFileSystem();
      }

      SYSTEM = var10000;
      val var4: Path.Companion = Path.Companion;
      val var10001: java.lang.String = System.getProperty("java.io.tmpdir");
      SYSTEM_TEMPORARY_DIRECTORY = Path.Companion.get$default(var4, var10001, false, 1, null);
      val var10002: ClassLoader = ResourceFileSystem.class.getClassLoader();
      RESOURCES = new ResourceFileSystem(var10002, false, null, 4, null);
   }

   public companion object {
      public final val SYSTEM: FileSystem
      public final val SYSTEM_TEMPORARY_DIRECTORY: Path
      public final val RESOURCES: FileSystem

      @JvmName(name = "get")
      public fun java.nio.file.FileSystem.asOkioFileSystem(): FileSystem {
         return new NioFileSystemWrappingFileSystem(`$this$asOkioFileSystem`);
      }
   }
}

package okio

import java.io.Closeable
import java.io.FileNotFoundException
import java.io.IOException
import java.util.zip.Inflater
import kotlin.jvm.internal.SourceDebugExtension
import okio.internal.FixedLengthSource
import okio.internal.ZipEntry
import okio.internal.ZipFilesKt

@SourceDebugExtension(["SMAP\nZipFileSystem.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ZipFileSystem.kt\nokio/ZipFileSystem\n+ 2 Okio.kt\nokio/Okio__OkioKt\n*L\n1#1,142:1\n58#2,4:143\n58#2,22:147\n66#2,10:169\n62#2,3:179\n77#2,3:182\n58#2,22:185\n*S KotlinDebug\n*F\n+ 1 ZipFileSystem.kt\nokio/ZipFileSystem\n*L\n55#1:143,4\n56#1:147,22\n55#1:169,10\n55#1:179,3\n55#1:182,3\n99#1:185,22\n*E\n"])
internal class ZipFileSystem internal constructor(zipPath: Path, fileSystem: FileSystem, entries: Map<Path, ZipEntry>, comment: String?) : FileSystem {
   private final val zipPath: Path
   private final val fileSystem: FileSystem
   private final val entries: Map<Path, ZipEntry>
   private final val comment: String?

   init {
      this.zipPath = zipPath;
      this.fileSystem = fileSystem;
      this.entries = entries;
      this.comment = comment;
   }

   public override fun canonicalize(path: Path): Path {
      val canonical: Path = this.canonicalizeInternal(path);
      if (!this.entries.containsKey(canonical)) {
         throw new FileNotFoundException(java.lang.String.valueOf(path));
      } else {
         return canonical;
      }
   }

   private fun canonicalizeInternal(path: Path): Path {
      return ROOT.resolve(path, true);
   }

   public override fun metadataOrNull(path: Path): FileMetadata? {
      // $VF: Couldn't be decompiled
      // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
      // java.lang.IndexOutOfBoundsException: Index -1 out of bounds for length 0
      //   at java.base/jdk.internal.util.Preconditions.outOfBounds(Preconditions.java:100)
      //   at java.base/jdk.internal.util.Preconditions.outOfBoundsCheckIndex(Preconditions.java:106)
      //   at java.base/jdk.internal.util.Preconditions.checkIndex(Preconditions.java:302)
      //   at java.base/java.util.Objects.checkIndex(Objects.java:365)
      //   at java.base/java.util.ArrayList.remove(ArrayList.java:552)
      //   at org.jetbrains.java.decompiler.util.collections.ListStack.pop(ListStack.java:31)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.processBlock(ExprProcessor.java:569)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.processStatement(ExprProcessor.java:134)
      //
      // Bytecode:
      // 000: aload 1
      // 001: ldc "path"
      // 003: invokestatic kotlin/jvm/internal/Intrinsics.checkNotNullParameter (Ljava/lang/Object;Ljava/lang/String;)V
      // 006: aload 0
      // 007: aload 1
      // 008: invokespecial okio/ZipFileSystem.canonicalizeInternal (Lokio/Path;)Lokio/Path;
      // 00b: astore 2
      // 00c: aload 0
      // 00d: getfield okio/ZipFileSystem.entries Ljava/util/Map;
      // 010: aload 2
      // 011: invokeinterface java/util/Map.get (Ljava/lang/Object;)Ljava/lang/Object; 2
      // 016: checkcast okio/internal/ZipEntry
      // 019: dup
      // 01a: ifnonnull 020
      // 01d: pop
      // 01e: aconst_null
      // 01f: areturn
      // 020: astore 3
      // 021: nop
      // 022: aload 3
      // 023: invokevirtual okio/internal/ZipEntry.getOffset ()J
      // 026: ldc2_w -1
      // 029: lcmp
      // 02a: ifeq 1a0
      // 02d: aload 0
      // 02e: getfield okio/ZipFileSystem.fileSystem Lokio/FileSystem;
      // 031: aload 0
      // 032: getfield okio/ZipFileSystem.zipPath Lokio/Path;
      // 035: invokevirtual okio/FileSystem.openReadOnly (Lokio/Path;)Lokio/FileHandle;
      // 038: checkcast java/io/Closeable
      // 03b: astore 5
      // 03d: bipush 0
      // 03e: istore 6
      // 040: aconst_null
      // 041: astore 7
      // 043: nop
      // 044: aload 5
      // 046: checkcast okio/FileHandle
      // 049: astore 8
      // 04b: bipush 0
      // 04c: istore 9
      // 04e: aload 8
      // 050: aload 3
      // 051: invokevirtual okio/internal/ZipEntry.getOffset ()J
      // 054: invokevirtual okio/FileHandle.source (J)Lokio/Source;
      // 057: invokestatic okio/Okio.buffer (Lokio/Source;)Lokio/BufferedSource;
      // 05a: checkcast java/io/Closeable
      // 05d: astore 10
      // 05f: bipush 0
      // 060: istore 11
      // 062: aconst_null
      // 063: astore 12
      // 065: nop
      // 066: aload 10
      // 068: checkcast okio/BufferedSource
      // 06b: astore 13
      // 06d: bipush 0
      // 06e: istore 14
      // 070: aload 13
      // 072: aload 3
      // 073: invokestatic okio/internal/ZipFilesKt.readLocalHeader (Lokio/BufferedSource;Lokio/internal/ZipEntry;)Lokio/internal/ZipEntry;
      // 076: astore 14
      // 078: nop
      // 079: aload 10
      // 07b: dup
      // 07c: ifnull 087
      // 07f: invokeinterface java/io/Closeable.close ()V 1
      // 084: goto 091
      // 087: pop
      // 088: goto 091
      // 08b: astore 15
      // 08d: aload 15
      // 08f: astore 12
      // 091: goto 0f5
      // 094: astore 16
      // 096: aload 16
      // 098: astore 12
      // 09a: aconst_null
      // 09b: astore 14
      // 09d: nop
      // 09e: aload 10
      // 0a0: dup
      // 0a1: ifnull 0ac
      // 0a4: invokeinterface java/io/Closeable.close ()V 1
      // 0a9: goto 0c5
      // 0ac: pop
      // 0ad: goto 0c5
      // 0b0: astore 15
      // 0b2: aload 12
      // 0b4: ifnonnull 0be
      // 0b7: aload 15
      // 0b9: astore 12
      // 0bb: goto 0c5
      // 0be: aload 12
      // 0c0: aload 15
      // 0c2: invokestatic kotlin/ExceptionsKt.addSuppressed (Ljava/lang/Throwable;Ljava/lang/Throwable;)V
      // 0c5: goto 0f5
      // 0c8: astore 16
      // 0ca: nop
      // 0cb: aload 10
      // 0cd: dup
      // 0ce: ifnull 0d9
      // 0d1: invokeinterface java/io/Closeable.close ()V 1
      // 0d6: goto 0f2
      // 0d9: pop
      // 0da: goto 0f2
      // 0dd: astore 17
      // 0df: aload 12
      // 0e1: ifnonnull 0eb
      // 0e4: aload 17
      // 0e6: astore 12
      // 0e8: goto 0f2
      // 0eb: aload 12
      // 0ed: aload 17
      // 0ef: invokestatic kotlin/ExceptionsKt.addSuppressed (Ljava/lang/Throwable;Ljava/lang/Throwable;)V
      // 0f2: aload 16
      // 0f4: athrow
      // 0f5: aload 14
      // 0f7: astore 13
      // 0f9: aload 12
      // 0fb: dup
      // 0fc: ifnull 100
      // 0ff: athrow
      // 100: pop
      // 101: aload 13
      // 103: checkcast java/lang/Object
      // 106: checkcast okio/internal/ZipEntry
      // 109: nop
      // 10a: astore 9
      // 10c: nop
      // 10d: aload 5
      // 10f: dup
      // 110: ifnull 11b
      // 113: invokeinterface java/io/Closeable.close ()V 1
      // 118: goto 125
      // 11b: pop
      // 11c: goto 125
      // 11f: astore 11
      // 121: aload 11
      // 123: astore 7
      // 125: goto 189
      // 128: astore 10
      // 12a: aload 10
      // 12c: astore 7
      // 12e: aconst_null
      // 12f: astore 9
      // 131: nop
      // 132: aload 5
      // 134: dup
      // 135: ifnull 140
      // 138: invokeinterface java/io/Closeable.close ()V 1
      // 13d: goto 159
      // 140: pop
      // 141: goto 159
      // 144: astore 11
      // 146: aload 7
      // 148: ifnonnull 152
      // 14b: aload 11
      // 14d: astore 7
      // 14f: goto 159
      // 152: aload 7
      // 154: aload 11
      // 156: invokestatic kotlin/ExceptionsKt.addSuppressed (Ljava/lang/Throwable;Ljava/lang/Throwable;)V
      // 159: goto 189
      // 15c: astore 10
      // 15e: nop
      // 15f: aload 5
      // 161: dup
      // 162: ifnull 16d
      // 165: invokeinterface java/io/Closeable.close ()V 1
      // 16a: goto 186
      // 16d: pop
      // 16e: goto 186
      // 171: astore 12
      // 173: aload 7
      // 175: ifnonnull 17f
      // 178: aload 12
      // 17a: astore 7
      // 17c: goto 186
      // 17f: aload 7
      // 181: aload 12
      // 183: invokestatic kotlin/ExceptionsKt.addSuppressed (Ljava/lang/Throwable;Ljava/lang/Throwable;)V
      // 186: aload 10
      // 188: athrow
      // 189: aload 9
      // 18b: astore 8
      // 18d: aload 7
      // 18f: dup
      // 190: ifnull 194
      // 193: athrow
      // 194: pop
      // 195: aload 8
      // 197: checkcast java/lang/Object
      // 19a: checkcast okio/internal/ZipEntry
      // 19d: goto 1a1
      // 1a0: aload 3
      // 1a1: astore 4
      // 1a3: new okio/FileMetadata
      // 1a6: dup
      // 1a7: aload 4
      // 1a9: invokevirtual okio/internal/ZipEntry.isDirectory ()Z
      // 1ac: ifne 1b3
      // 1af: bipush 1
      // 1b0: goto 1b4
      // 1b3: bipush 0
      // 1b4: aload 4
      // 1b6: invokevirtual okio/internal/ZipEntry.isDirectory ()Z
      // 1b9: aconst_null
      // 1ba: aload 4
      // 1bc: invokevirtual okio/internal/ZipEntry.isDirectory ()Z
      // 1bf: ifeq 1c6
      // 1c2: aconst_null
      // 1c3: goto 1ce
      // 1c6: aload 4
      // 1c8: invokevirtual okio/internal/ZipEntry.getSize ()J
      // 1cb: invokestatic java/lang/Long.valueOf (J)Ljava/lang/Long;
      // 1ce: aload 4
      // 1d0: invokevirtual okio/internal/ZipEntry.getCreatedAtMillis$okio ()Ljava/lang/Long;
      // 1d3: aload 4
      // 1d5: invokevirtual okio/internal/ZipEntry.getLastModifiedAtMillis$okio ()Ljava/lang/Long;
      // 1d8: aload 4
      // 1da: invokevirtual okio/internal/ZipEntry.getLastAccessedAtMillis$okio ()Ljava/lang/Long;
      // 1dd: aconst_null
      // 1de: sipush 128
      // 1e1: aconst_null
      // 1e2: invokespecial okio/FileMetadata.<init> (ZZLokio/Path;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Ljava/util/Map;ILkotlin/jvm/internal/DefaultConstructorMarker;)V
      // 1e5: areturn
   }

   public override fun openReadOnly(file: Path): FileHandle {
      throw new UnsupportedOperationException("not implemented yet!");
   }

   public override fun openReadWrite(file: Path, mustCreate: Boolean, mustExist: Boolean): FileHandle {
      throw new IOException("zip entries are not writable");
   }

   public override fun list(dir: Path): List<Path> {
      val var10000: java.util.List = this.list(dir, true);
      return var10000;
   }

   public override fun listOrNull(dir: Path): List<Path>? {
      return this.list(dir, false);
   }

   private fun list(dir: Path, throwOnFailure: Boolean): List<Path>? {
      val var10000: ZipEntry = this.entries.get(this.canonicalizeInternal(dir));
      if (var10000 == null) {
         if (throwOnFailure) {
            throw new IOException("not a directory: $dir");
         } else {
            return null;
         }
      } else {
         return CollectionsKt.toList(var10000.getChildren());
      }
   }

   @Throws(java/io/IOException::class)
   public override fun source(file: Path): Source {
      val var10000: ZipEntry = this.entries.get(this.canonicalizeInternal(file));
      if (var10000 == null) {
         throw new FileNotFoundException("no such file: $file");
      } else {
         label110: {
            val `$this$use$iv`: Closeable = this.fileSystem.openReadOnly(this.zipPath);
            var `thrown$iv`: java.lang.Throwable = null;

            var var9: Any;
            label69: {
               label68: {
                  try {
                     try {
                        break label68;
                     } catch (var16: java.lang.Throwable) {
                        `thrown$iv` = var16;
                        var9 = null;
                     }
                  } catch (var17: java.lang.Throwable) {
                     try {
                        if (`$this$use$iv` != null) {
                           `$this$use$iv`.close();
                        }
                     } catch (var14: java.lang.Throwable) {
                        if (`thrown$iv` == null) {
                           ;
                        } else {
                           ExceptionsKt.addSuppressed(`thrown$iv`, var14);
                        }
                     }
                  }

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
                  break label69;
               }

               try {
                  if (`$this$use$iv` != null) {
                     `$this$use$iv`.close();
                  }
               } catch (var13: java.lang.Throwable) {
                  `thrown$iv` = var13;
               }
            }

            if (`thrown$iv` != null) {
               throw `thrown$iv`;
            } else {
               val source: BufferedSource = var9 as BufferedSource;
               ZipFilesKt.skipLocalHeader(var9 as BufferedSource);
               return if (var10000.getCompressionMethod() == 0)
                  new FixedLengthSource(source, var10000.getSize(), true)
                  else
                  new FixedLengthSource(
                     new InflaterSource(new FixedLengthSource(source, var10000.getCompressedSize(), true), new Inflater(true)), var10000.getSize(), false
                  );
            }
         }
      }
   }

   public override fun sink(file: Path, mustCreate: Boolean): Sink {
      throw new IOException("zip file systems are read-only");
   }

   public override fun appendingSink(file: Path, mustExist: Boolean): Sink {
      throw new IOException("zip file systems are read-only");
   }

   public override fun createDirectory(dir: Path, mustCreate: Boolean) {
      throw new IOException("zip file systems are read-only");
   }

   public override fun atomicMove(source: Path, target: Path) {
      throw new IOException("zip file systems are read-only");
   }

   public override fun delete(path: Path, mustExist: Boolean) {
      throw new IOException("zip file systems are read-only");
   }

   public override fun createSymlink(source: Path, target: Path) {
      throw new IOException("zip file systems are read-only");
   }

   private companion object {
      public final val ROOT: Path
   }
}

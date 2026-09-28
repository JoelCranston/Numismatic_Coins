@file:SourceDebugExtension(["SMAP\nZipFiles.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ZipFiles.kt\nokio/internal/ZipFilesKt\n+ 2 Okio.kt\nokio/Okio__OkioKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,503:1\n58#2,4:504\n58#2,4:508\n58#2,22:512\n66#2,10:534\n62#2,3:544\n77#2,3:547\n58#2,22:550\n66#2,10:572\n62#2,3:582\n77#2,3:585\n1056#3:588\n*S KotlinDebug\n*F\n+ 1 ZipFiles.kt\nokio/internal/ZipFilesKt\n*L\n66#1:504,4\n101#1:508,4\n109#1:512,22\n101#1:534,10\n101#1:544,3\n101#1:547,3\n125#1:550,22\n66#1:572,10\n66#1:582,3\n66#1:585,3\n155#1:588\n*E\n"])

package okio.internal

import java.io.IOException
import kotlin.jvm.functions.Function1
import kotlin.jvm.internal.Ref
import kotlin.jvm.internal.SourceDebugExtension
import okio.BufferedSource
import okio.FileSystem
import okio.Path
import okio.ZipFileSystem
import okio.internal.ZipFilesKt.buildIndex..inlined.sortedBy.1

private const val LOCAL_FILE_HEADER_SIGNATURE: Int = 67324752
private const val CENTRAL_FILE_HEADER_SIGNATURE: Int = 33639248
private const val END_OF_CENTRAL_DIRECTORY_SIGNATURE: Int = 101010256
private const val ZIP64_LOCATOR_SIGNATURE: Int = 117853008
private const val ZIP64_EOCD_RECORD_SIGNATURE: Int = 101075792
internal const val COMPRESSION_METHOD_DEFLATED: Int = 8
internal const val COMPRESSION_METHOD_STORED: Int = 0
private const val BIT_FLAG_ENCRYPTED: Int = 1
private const val BIT_FLAG_UNSUPPORTED_MASK: Int = 1
private const val MAX_ZIP_ENTRY_AND_ARCHIVE_SIZE: Long = 4294967295L
private const val HEADER_ID_ZIP64_EXTENDED_INFO: Int = 1
private const val HEADER_ID_NTFS_EXTRA: Int = 10
private const val HEADER_ID_EXTENDED_TIMESTAMP: Int = 21589

private final val hex: String
   private final get() {
      val var10000: StringBuilder = new StringBuilder().append("0x");
      val var10001: java.lang.String = Integer.toString(`$this$hex`, CharsKt.checkRadix(16));
      return var10000.append(var10001).toString();
   }


@Throws(java/io/IOException::class)
internal fun openZip(zipPath: Path, fileSystem: FileSystem, predicate: (ZipEntry) -> Boolean = ZipFilesKt::openZip$lambda$0): ZipFileSystem {
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
   // 000: aload 0
   // 001: ldc "zipPath"
   // 003: invokestatic kotlin/jvm/internal/Intrinsics.checkNotNullParameter (Ljava/lang/Object;Ljava/lang/String;)V
   // 006: aload 1
   // 007: ldc "fileSystem"
   // 009: invokestatic kotlin/jvm/internal/Intrinsics.checkNotNullParameter (Ljava/lang/Object;Ljava/lang/String;)V
   // 00c: aload 2
   // 00d: ldc "predicate"
   // 00f: invokestatic kotlin/jvm/internal/Intrinsics.checkNotNullParameter (Ljava/lang/Object;Ljava/lang/String;)V
   // 012: aload 1
   // 013: aload 0
   // 014: invokevirtual okio/FileSystem.openReadOnly (Lokio/Path;)Lokio/FileHandle;
   // 017: checkcast java/io/Closeable
   // 01a: astore 3
   // 01b: bipush 0
   // 01c: istore 4
   // 01e: aconst_null
   // 01f: astore 5
   // 021: nop
   // 022: aload 3
   // 023: checkcast okio/FileHandle
   // 026: astore 6
   // 028: bipush 0
   // 029: istore 7
   // 02b: aload 6
   // 02d: invokevirtual okio/FileHandle.size ()J
   // 030: bipush 22
   // 032: i2l
   // 033: lsub
   // 034: lstore 8
   // 036: lload 8
   // 038: lconst_0
   // 039: lcmp
   // 03a: ifge 05c
   // 03d: new java/io/IOException
   // 040: dup
   // 041: new java/lang/StringBuilder
   // 044: dup
   // 045: invokespecial java/lang/StringBuilder.<init> ()V
   // 048: ldc "not a zip: size="
   // 04a: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
   // 04d: aload 6
   // 04f: invokevirtual okio/FileHandle.size ()J
   // 052: invokevirtual java/lang/StringBuilder.append (J)Ljava/lang/StringBuilder;
   // 055: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
   // 058: invokespecial java/io/IOException.<init> (Ljava/lang/String;)V
   // 05b: athrow
   // 05c: lload 8
   // 05e: ldc2_w 65536
   // 061: lsub
   // 062: lconst_0
   // 063: invokestatic java/lang/Math.max (JJ)J
   // 066: lstore 10
   // 068: lconst_0
   // 069: lstore 12
   // 06b: aconst_null
   // 06c: astore 14
   // 06e: aconst_null
   // 06f: astore 15
   // 071: nop
   // 072: aload 6
   // 074: lload 8
   // 076: invokevirtual okio/FileHandle.source (J)Lokio/Source;
   // 079: invokestatic okio/Okio.buffer (Lokio/Source;)Lokio/BufferedSource;
   // 07c: astore 16
   // 07e: nop
   // 07f: aload 16
   // 081: invokeinterface okio/BufferedSource.readIntLe ()I 1
   // 086: ldc 101010256
   // 088: if_icmpne 0b0
   // 08b: lload 8
   // 08d: lstore 12
   // 08f: aload 16
   // 091: invokestatic okio/internal/ZipFilesKt.readEocdRecord (Lokio/BufferedSource;)Lokio/internal/EocdRecord;
   // 094: astore 14
   // 096: aload 16
   // 098: aload 14
   // 09a: invokevirtual okio/internal/EocdRecord.getCommentByteCount ()I
   // 09d: i2l
   // 09e: invokeinterface okio/BufferedSource.readUtf8 (J)Ljava/lang/String; 3
   // 0a3: astore 15
   // 0a5: nop
   // 0a6: aload 16
   // 0a8: invokeinterface okio/BufferedSource.close ()V 1
   // 0ad: goto 0e0
   // 0b0: aload 16
   // 0b2: invokeinterface okio/BufferedSource.close ()V 1
   // 0b7: goto 0c6
   // 0ba: astore 17
   // 0bc: aload 16
   // 0be: invokeinterface okio/BufferedSource.close ()V 1
   // 0c3: aload 17
   // 0c5: athrow
   // 0c6: lload 8
   // 0c8: ldc2_w -1
   // 0cb: ladd
   // 0cc: lstore 8
   // 0ce: lload 8
   // 0d0: lload 10
   // 0d2: lcmp
   // 0d3: ifge 071
   // 0d6: new java/io/IOException
   // 0d9: dup
   // 0da: ldc "not a zip: end of central directory signature not found"
   // 0dc: invokespecial java/io/IOException.<init> (Ljava/lang/String;)V
   // 0df: athrow
   // 0e0: lload 12
   // 0e2: bipush 20
   // 0e4: i2l
   // 0e5: lsub
   // 0e6: lstore 18
   // 0e8: lload 18
   // 0ea: lconst_0
   // 0eb: lcmp
   // 0ec: ifle 2f8
   // 0ef: aload 6
   // 0f1: lload 18
   // 0f3: invokevirtual okio/FileHandle.source (J)Lokio/Source;
   // 0f6: invokestatic okio/Okio.buffer (Lokio/Source;)Lokio/BufferedSource;
   // 0f9: checkcast java/io/Closeable
   // 0fc: astore 20
   // 0fe: bipush 0
   // 0ff: istore 21
   // 101: aconst_null
   // 102: astore 22
   // 104: nop
   // 105: aload 20
   // 107: checkcast okio/BufferedSource
   // 10a: astore 23
   // 10c: bipush 0
   // 10d: istore 24
   // 10f: aload 23
   // 111: invokeinterface okio/BufferedSource.readIntLe ()I 1
   // 116: ldc 117853008
   // 118: if_icmpne 254
   // 11b: aload 23
   // 11d: invokeinterface okio/BufferedSource.readIntLe ()I 1
   // 122: istore 25
   // 124: aload 23
   // 126: invokeinterface okio/BufferedSource.readLongLe ()J 1
   // 12b: lstore 26
   // 12d: aload 23
   // 12f: invokeinterface okio/BufferedSource.readIntLe ()I 1
   // 134: istore 28
   // 136: iload 28
   // 138: bipush 1
   // 139: if_icmpne 141
   // 13c: iload 25
   // 13e: ifeq 14b
   // 141: new java/io/IOException
   // 144: dup
   // 145: ldc "unsupported zip: spanned"
   // 147: invokespecial java/io/IOException.<init> (Ljava/lang/String;)V
   // 14a: athrow
   // 14b: aload 6
   // 14d: lload 26
   // 14f: invokevirtual okio/FileHandle.source (J)Lokio/Source;
   // 152: invokestatic okio/Okio.buffer (Lokio/Source;)Lokio/BufferedSource;
   // 155: checkcast java/io/Closeable
   // 158: astore 29
   // 15a: bipush 0
   // 15b: istore 30
   // 15d: aconst_null
   // 15e: astore 31
   // 160: nop
   // 161: aload 29
   // 163: checkcast okio/BufferedSource
   // 166: astore 32
   // 168: bipush 0
   // 169: istore 33
   // 16b: aload 32
   // 16d: invokeinterface okio/BufferedSource.readIntLe ()I 1
   // 172: istore 34
   // 174: iload 34
   // 176: ldc 101075792
   // 178: if_icmpeq 1a7
   // 17b: new java/io/IOException
   // 17e: dup
   // 17f: new java/lang/StringBuilder
   // 182: dup
   // 183: invokespecial java/lang/StringBuilder.<init> ()V
   // 186: ldc "bad zip: expected "
   // 188: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
   // 18b: ldc 101075792
   // 18d: invokestatic okio/internal/ZipFilesKt.getHex (I)Ljava/lang/String;
   // 190: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
   // 193: ldc " but was "
   // 195: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
   // 198: iload 34
   // 19a: invokestatic okio/internal/ZipFilesKt.getHex (I)Ljava/lang/String;
   // 19d: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
   // 1a0: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
   // 1a3: invokespecial java/io/IOException.<init> (Ljava/lang/String;)V
   // 1a6: athrow
   // 1a7: aload 32
   // 1a9: aload 14
   // 1ab: invokestatic okio/internal/ZipFilesKt.readZip64EocdRecord (Lokio/BufferedSource;Lokio/internal/EocdRecord;)Lokio/internal/EocdRecord;
   // 1ae: astore 14
   // 1b0: nop
   // 1b1: getstatic kotlin/Unit.INSTANCE Lkotlin/Unit;
   // 1b4: astore 33
   // 1b6: nop
   // 1b7: aload 29
   // 1b9: dup
   // 1ba: ifnull 1c8
   // 1bd: invokeinterface java/io/Closeable.close ()V 1
   // 1c2: getstatic kotlin/Unit.INSTANCE Lkotlin/Unit;
   // 1c5: goto 1ca
   // 1c8: pop
   // 1c9: aconst_null
   // 1ca: pop
   // 1cb: goto 1d4
   // 1ce: astore 35
   // 1d0: aload 35
   // 1d2: astore 31
   // 1d4: goto 242
   // 1d7: astore 34
   // 1d9: aload 34
   // 1db: astore 31
   // 1dd: aconst_null
   // 1de: astore 33
   // 1e0: nop
   // 1e1: aload 29
   // 1e3: dup
   // 1e4: ifnull 1f2
   // 1e7: invokeinterface java/io/Closeable.close ()V 1
   // 1ec: getstatic kotlin/Unit.INSTANCE Lkotlin/Unit;
   // 1ef: goto 1f4
   // 1f2: pop
   // 1f3: aconst_null
   // 1f4: pop
   // 1f5: goto 20d
   // 1f8: astore 35
   // 1fa: aload 31
   // 1fc: ifnonnull 206
   // 1ff: aload 35
   // 201: astore 31
   // 203: goto 20d
   // 206: aload 31
   // 208: aload 35
   // 20a: invokestatic kotlin/ExceptionsKt.addSuppressed (Ljava/lang/Throwable;Ljava/lang/Throwable;)V
   // 20d: goto 242
   // 210: astore 34
   // 212: nop
   // 213: aload 29
   // 215: dup
   // 216: ifnull 224
   // 219: invokeinterface java/io/Closeable.close ()V 1
   // 21e: getstatic kotlin/Unit.INSTANCE Lkotlin/Unit;
   // 221: goto 226
   // 224: pop
   // 225: aconst_null
   // 226: pop
   // 227: goto 23f
   // 22a: astore 36
   // 22c: aload 31
   // 22e: ifnonnull 238
   // 231: aload 36
   // 233: astore 31
   // 235: goto 23f
   // 238: aload 31
   // 23a: aload 36
   // 23c: invokestatic kotlin/ExceptionsKt.addSuppressed (Ljava/lang/Throwable;Ljava/lang/Throwable;)V
   // 23f: aload 34
   // 241: athrow
   // 242: aload 33
   // 244: astore 32
   // 246: aload 31
   // 248: dup
   // 249: ifnull 24d
   // 24c: athrow
   // 24d: pop
   // 24e: aload 32
   // 250: checkcast java/lang/Object
   // 253: pop
   // 254: nop
   // 255: getstatic kotlin/Unit.INSTANCE Lkotlin/Unit;
   // 258: astore 23
   // 25a: nop
   // 25b: aload 20
   // 25d: dup
   // 25e: ifnull 26c
   // 261: invokeinterface java/io/Closeable.close ()V 1
   // 266: getstatic kotlin/Unit.INSTANCE Lkotlin/Unit;
   // 269: goto 26e
   // 26c: pop
   // 26d: aconst_null
   // 26e: pop
   // 26f: goto 278
   // 272: astore 25
   // 274: aload 25
   // 276: astore 22
   // 278: goto 2e6
   // 27b: astore 24
   // 27d: aload 24
   // 27f: astore 22
   // 281: aconst_null
   // 282: astore 23
   // 284: nop
   // 285: aload 20
   // 287: dup
   // 288: ifnull 296
   // 28b: invokeinterface java/io/Closeable.close ()V 1
   // 290: getstatic kotlin/Unit.INSTANCE Lkotlin/Unit;
   // 293: goto 298
   // 296: pop
   // 297: aconst_null
   // 298: pop
   // 299: goto 2b1
   // 29c: astore 25
   // 29e: aload 22
   // 2a0: ifnonnull 2aa
   // 2a3: aload 25
   // 2a5: astore 22
   // 2a7: goto 2b1
   // 2aa: aload 22
   // 2ac: aload 25
   // 2ae: invokestatic kotlin/ExceptionsKt.addSuppressed (Ljava/lang/Throwable;Ljava/lang/Throwable;)V
   // 2b1: goto 2e6
   // 2b4: astore 24
   // 2b6: nop
   // 2b7: aload 20
   // 2b9: dup
   // 2ba: ifnull 2c8
   // 2bd: invokeinterface java/io/Closeable.close ()V 1
   // 2c2: getstatic kotlin/Unit.INSTANCE Lkotlin/Unit;
   // 2c5: goto 2ca
   // 2c8: pop
   // 2c9: aconst_null
   // 2ca: pop
   // 2cb: goto 2e3
   // 2ce: astore 37
   // 2d0: aload 22
   // 2d2: ifnonnull 2dc
   // 2d5: aload 37
   // 2d7: astore 22
   // 2d9: goto 2e3
   // 2dc: aload 22
   // 2de: aload 37
   // 2e0: invokestatic kotlin/ExceptionsKt.addSuppressed (Ljava/lang/Throwable;Ljava/lang/Throwable;)V
   // 2e3: aload 24
   // 2e5: athrow
   // 2e6: aload 23
   // 2e8: astore 38
   // 2ea: aload 22
   // 2ec: dup
   // 2ed: ifnull 2f1
   // 2f0: athrow
   // 2f1: pop
   // 2f2: aload 38
   // 2f4: checkcast java/lang/Object
   // 2f7: pop
   // 2f8: new java/util/ArrayList
   // 2fb: dup
   // 2fc: invokespecial java/util/ArrayList.<init> ()V
   // 2ff: checkcast java/util/List
   // 302: astore 20
   // 304: aload 6
   // 306: aload 14
   // 308: invokevirtual okio/internal/EocdRecord.getCentralDirectoryOffset ()J
   // 30b: invokevirtual okio/FileHandle.source (J)Lokio/Source;
   // 30e: invokestatic okio/Okio.buffer (Lokio/Source;)Lokio/BufferedSource;
   // 311: checkcast java/io/Closeable
   // 314: astore 21
   // 316: bipush 0
   // 317: istore 22
   // 319: aconst_null
   // 31a: astore 23
   // 31c: nop
   // 31d: aload 21
   // 31f: checkcast okio/BufferedSource
   // 322: astore 24
   // 324: bipush 0
   // 325: istore 25
   // 327: lconst_0
   // 328: lstore 26
   // 32a: aload 14
   // 32c: invokevirtual okio/internal/EocdRecord.getEntryCount ()J
   // 32f: lstore 39
   // 331: lload 26
   // 333: lload 39
   // 335: lcmp
   // 336: ifge 37f
   // 339: aload 24
   // 33b: invokestatic okio/internal/ZipFilesKt.readCentralDirectoryZipEntry (Lokio/BufferedSource;)Lokio/internal/ZipEntry;
   // 33e: astore 30
   // 340: aload 30
   // 342: invokevirtual okio/internal/ZipEntry.getOffset ()J
   // 345: aload 14
   // 347: invokevirtual okio/internal/EocdRecord.getCentralDirectoryOffset ()J
   // 34a: lcmp
   // 34b: iflt 358
   // 34e: new java/io/IOException
   // 351: dup
   // 352: ldc "bad zip: local file header offset >= central directory offset"
   // 354: invokespecial java/io/IOException.<init> (Ljava/lang/String;)V
   // 357: athrow
   // 358: aload 2
   // 359: aload 30
   // 35b: invokeinterface kotlin/jvm/functions/Function1.invoke (Ljava/lang/Object;)Ljava/lang/Object; 2
   // 360: checkcast java/lang/Boolean
   // 363: invokevirtual java/lang/Boolean.booleanValue ()Z
   // 366: ifeq 376
   // 369: aload 20
   // 36b: checkcast java/util/Collection
   // 36e: aload 30
   // 370: invokeinterface java/util/Collection.add (Ljava/lang/Object;)Z 2
   // 375: pop
   // 376: lload 26
   // 378: lconst_1
   // 379: ladd
   // 37a: lstore 26
   // 37c: goto 331
   // 37f: nop
   // 380: getstatic kotlin/Unit.INSTANCE Lkotlin/Unit;
   // 383: astore 31
   // 385: nop
   // 386: aload 21
   // 388: dup
   // 389: ifnull 397
   // 38c: invokeinterface java/io/Closeable.close ()V 1
   // 391: getstatic kotlin/Unit.INSTANCE Lkotlin/Unit;
   // 394: goto 399
   // 397: pop
   // 398: aconst_null
   // 399: pop
   // 39a: goto 3a3
   // 39d: astore 25
   // 39f: aload 25
   // 3a1: astore 23
   // 3a3: goto 411
   // 3a6: astore 24
   // 3a8: aload 24
   // 3aa: astore 23
   // 3ac: aconst_null
   // 3ad: astore 31
   // 3af: nop
   // 3b0: aload 21
   // 3b2: dup
   // 3b3: ifnull 3c1
   // 3b6: invokeinterface java/io/Closeable.close ()V 1
   // 3bb: getstatic kotlin/Unit.INSTANCE Lkotlin/Unit;
   // 3be: goto 3c3
   // 3c1: pop
   // 3c2: aconst_null
   // 3c3: pop
   // 3c4: goto 3dc
   // 3c7: astore 25
   // 3c9: aload 23
   // 3cb: ifnonnull 3d5
   // 3ce: aload 25
   // 3d0: astore 23
   // 3d2: goto 3dc
   // 3d5: aload 23
   // 3d7: aload 25
   // 3d9: invokestatic kotlin/ExceptionsKt.addSuppressed (Ljava/lang/Throwable;Ljava/lang/Throwable;)V
   // 3dc: goto 411
   // 3df: astore 24
   // 3e1: nop
   // 3e2: aload 21
   // 3e4: dup
   // 3e5: ifnull 3f3
   // 3e8: invokeinterface java/io/Closeable.close ()V 1
   // 3ed: getstatic kotlin/Unit.INSTANCE Lkotlin/Unit;
   // 3f0: goto 3f5
   // 3f3: pop
   // 3f4: aconst_null
   // 3f5: pop
   // 3f6: goto 40e
   // 3f9: astore 32
   // 3fb: aload 23
   // 3fd: ifnonnull 407
   // 400: aload 32
   // 402: astore 23
   // 404: goto 40e
   // 407: aload 23
   // 409: aload 32
   // 40b: invokestatic kotlin/ExceptionsKt.addSuppressed (Ljava/lang/Throwable;Ljava/lang/Throwable;)V
   // 40e: aload 24
   // 410: athrow
   // 411: aload 31
   // 413: astore 33
   // 415: aload 23
   // 417: dup
   // 418: ifnull 41c
   // 41b: athrow
   // 41c: pop
   // 41d: aload 33
   // 41f: checkcast java/lang/Object
   // 422: pop
   // 423: aload 20
   // 425: invokestatic okio/internal/ZipFilesKt.buildIndex (Ljava/util/List;)Ljava/util/Map;
   // 428: astore 21
   // 42a: new okio/ZipFileSystem
   // 42d: dup
   // 42e: aload 0
   // 42f: aload 1
   // 430: aload 21
   // 432: aload 15
   // 434: invokespecial okio/ZipFileSystem.<init> (Lokio/Path;Lokio/FileSystem;Ljava/util/Map;Ljava/lang/String;)V
   // 437: astore 44
   // 439: nop
   // 43a: aload 3
   // 43b: dup
   // 43c: ifnull 44a
   // 43f: invokeinterface java/io/Closeable.close ()V 1
   // 444: getstatic kotlin/Unit.INSTANCE Lkotlin/Unit;
   // 447: goto 44c
   // 44a: pop
   // 44b: aconst_null
   // 44c: pop
   // 44d: goto 452
   // 450: astore 7
   // 452: aload 44
   // 454: areturn
   // 455: astore 6
   // 457: aload 6
   // 459: astore 5
   // 45b: aconst_null
   // 45c: astore 41
   // 45e: nop
   // 45f: aload 3
   // 460: dup
   // 461: ifnull 46f
   // 464: invokeinterface java/io/Closeable.close ()V 1
   // 469: getstatic kotlin/Unit.INSTANCE Lkotlin/Unit;
   // 46c: goto 471
   // 46f: pop
   // 470: aconst_null
   // 471: pop
   // 472: goto 48a
   // 475: astore 7
   // 477: aload 5
   // 479: ifnonnull 483
   // 47c: aload 7
   // 47e: astore 5
   // 480: goto 48a
   // 483: aload 5
   // 485: aload 7
   // 487: invokestatic kotlin/ExceptionsKt.addSuppressed (Ljava/lang/Throwable;Ljava/lang/Throwable;)V
   // 48a: goto 4be
   // 48d: astore 6
   // 48f: nop
   // 490: aload 3
   // 491: dup
   // 492: ifnull 4a0
   // 495: invokeinterface java/io/Closeable.close ()V 1
   // 49a: getstatic kotlin/Unit.INSTANCE Lkotlin/Unit;
   // 49d: goto 4a2
   // 4a0: pop
   // 4a1: aconst_null
   // 4a2: pop
   // 4a3: goto 4bb
   // 4a6: astore 42
   // 4a8: aload 5
   // 4aa: ifnonnull 4b4
   // 4ad: aload 42
   // 4af: astore 5
   // 4b1: goto 4bb
   // 4b4: aload 5
   // 4b6: aload 42
   // 4b8: invokestatic kotlin/ExceptionsKt.addSuppressed (Ljava/lang/Throwable;Ljava/lang/Throwable;)V
   // 4bb: aload 6
   // 4bd: athrow
   // 4be: aload 41
   // 4c0: astore 43
   // 4c2: aload 5
   // 4c4: dup
   // 4c5: ifnull 4c9
   // 4c8: athrow
   // 4c9: pop
   // 4ca: aload 43
   // 4cc: pop
   // 4cd: new kotlin/KotlinNothingValueException
   // 4d0: dup
   // 4d1: invokespecial kotlin/KotlinNothingValueException.<init> ()V
   // 4d4: athrow
}

@Throws(java/io/IOException::class)
@JvmSynthetic
fun `openZip$default`(var0: Path, var1: FileSystem, var2: Function1, var3: Int, var4: Any): ZipFileSystem {
   if ((var3 and 4) != 0) {
      var2 = ZipFilesKt::openZip$lambda$0;
   }

   return openZip(var0, var1, var2);
}

private fun buildIndex(entries: List<ZipEntry>): Map<Path, ZipEntry> {
   val root: Path = Path.Companion.get$default(Path.Companion, "/", false, 1, null);
   val result: java.util.Map = MapsKt.mutableMapOf(
      new Pair[]{TuplesKt.to(root, new ZipEntry(root, true, null, 0L, 0L, 0L, 0, 0L, 0, 0, null, null, null, null, null, null, 65532, null))}
   );

   for (ZipEntry entry : CollectionsKt.sortedWith(entries, new 1())) {
      if (result.put(var10.getCanonicalPath(), var10) == null) {
         var child: ZipEntry = var10;

         while (true) {
            val var10000: Path = child.getCanonicalPath().parent();
            if (var10000 == null) {
               break;
            }

            var parentEntry: ZipEntry = result.get(var10000) as ZipEntry;
            if (parentEntry != null) {
               parentEntry.getChildren().add(child.getCanonicalPath());
               break;
            }

            parentEntry = new ZipEntry(var10000, true, null, 0L, 0L, 0L, 0, 0L, 0, 0, null, null, null, null, null, null, 65532, null);
            result.put(var10000, parentEntry);
            parentEntry.getChildren().add(child.getCanonicalPath());
            child = parentEntry;
         }
      }
   }

   return result;
}

@Throws(java/io/IOException::class)
internal fun BufferedSource.readCentralDirectoryZipEntry(): ZipEntry {
   val signature: Int = `$this$readCentralDirectoryZipEntry`.readIntLe();
   if (signature != 33639248) {
      throw new IOException("bad zip: expected ${getHex(33639248)} but was ${getHex(signature)}");
   } else {
      `$this$readCentralDirectoryZipEntry`.skip(4L);
      val bitFlag: Int = `$this$readCentralDirectoryZipEntry`.readShortLe() and '\uffff';
      if ((bitFlag and 1) != 0) {
         throw new IOException("unsupported zip: general purpose bit flag=${getHex(bitFlag)}");
      } else {
         val compressionMethod: Int = `$this$readCentralDirectoryZipEntry`.readShortLe() and '\uffff';
         val dosLastModifiedTime: Int = `$this$readCentralDirectoryZipEntry`.readShortLe() and '\uffff';
         val dosLastModifiedDate: Int = `$this$readCentralDirectoryZipEntry`.readShortLe() and '\uffff';
         val crc: Long = `$this$readCentralDirectoryZipEntry`.readIntLe() and 4294967295L;
         val compressedSize: Ref.LongRef = new Ref.LongRef();
         compressedSize.element = `$this$readCentralDirectoryZipEntry`.readIntLe() and 4294967295L;
         val size: Ref.LongRef = new Ref.LongRef();
         size.element = `$this$readCentralDirectoryZipEntry`.readIntLe() and 4294967295L;
         val nameSize: Int = `$this$readCentralDirectoryZipEntry`.readShortLe() and '\uffff';
         val extraSize: Int = `$this$readCentralDirectoryZipEntry`.readShortLe() and '\uffff';
         val commentByteCount: Int = `$this$readCentralDirectoryZipEntry`.readShortLe() and '\uffff';
         `$this$readCentralDirectoryZipEntry`.skip(8L);
         val offset: Ref.LongRef = new Ref.LongRef();
         offset.element = `$this$readCentralDirectoryZipEntry`.readIntLe() and 4294967295L;
         val name: java.lang.String = `$this$readCentralDirectoryZipEntry`.readUtf8((long)nameSize);
         if (StringsKt.contains$default(name, '\u0000', false, 2, null)) {
            throw new IOException("bad zip: filename contains 0x00");
         } else {
            var hasZip64Extra: Long = 0L;
            if (size.element == 4294967295L) {
               hasZip64Extra = 0L + 8;
            }

            if (compressedSize.element == 4294967295L) {
               hasZip64Extra += 8;
            }

            if (offset.element == 4294967295L) {
               hasZip64Extra += 8;
            }

            val ntfsLastModifiedAtFiletime: Ref.ObjectRef = new Ref.ObjectRef();
            val ntfsLastAccessedAtFiletime: Ref.ObjectRef = new Ref.ObjectRef();
            val var24: Ref.ObjectRef = new Ref.ObjectRef();
            val var25: Ref.BooleanRef = new Ref.BooleanRef();
            readExtra(`$this$readCentralDirectoryZipEntry`, extraSize, ZipFilesKt::readCentralDirectoryZipEntry$lambda$1);
            if (hasZip64Extra > 0L && !var25.element) {
               throw new IOException("bad zip: zip64 extra required but absent");
            } else {
               return new ZipEntry(
                  Path.Companion.get$default(Path.Companion, "/", false, 1, null).resolve(name),
                  StringsKt.endsWith$default(name, "/", false, 2, null),
                  `$this$readCentralDirectoryZipEntry`.readUtf8((long)commentByteCount),
                  crc,
                  compressedSize.element,
                  size.element,
                  compressionMethod,
                  offset.element,
                  dosLastModifiedDate,
                  dosLastModifiedTime,
                  ntfsLastModifiedAtFiletime.element as java.lang.Long,
                  ntfsLastAccessedAtFiletime.element as java.lang.Long,
                  var24.element as java.lang.Long,
                  null,
                  null,
                  null,
                  57344,
                  null
               );
            }
         }
      }
   }
}

@Throws(java/io/IOException::class)
private fun BufferedSource.readEocdRecord(): EocdRecord {
   val diskNumber: Int = `$this$readEocdRecord`.readShortLe() and '\uffff';
   val diskWithCentralDir: Int = `$this$readEocdRecord`.readShortLe() and '\uffff';
   val entryCount: Long = `$this$readEocdRecord`.readShortLe() and '\uffff';
   if (entryCount == (`$this$readEocdRecord`.readShortLe() and '\uffff') && diskNumber == 0 && diskWithCentralDir == 0) {
      `$this$readEocdRecord`.skip(4L);
      return new EocdRecord(entryCount, `$this$readEocdRecord`.readIntLe() and 4294967295L, `$this$readEocdRecord`.readShortLe() and '\uffff');
   } else {
      throw new IOException("unsupported zip: spanned");
   }
}

@Throws(java/io/IOException::class)
private fun BufferedSource.readZip64EocdRecord(regularRecord: EocdRecord): EocdRecord {
   `$this$readZip64EocdRecord`.skip(12L);
   val diskNumber: Int = `$this$readZip64EocdRecord`.readIntLe();
   val diskWithCentralDirStart: Int = `$this$readZip64EocdRecord`.readIntLe();
   val entryCount: Long = `$this$readZip64EocdRecord`.readLongLe();
   if (entryCount == `$this$readZip64EocdRecord`.readLongLe() && diskNumber == 0 && diskWithCentralDirStart == 0) {
      `$this$readZip64EocdRecord`.skip(8L);
      return new EocdRecord(entryCount, `$this$readZip64EocdRecord`.readLongLe(), regularRecord.getCommentByteCount());
   } else {
      throw new IOException("unsupported zip: spanned");
   }
}

private fun BufferedSource.readExtra(extraSize: Int, block: (Int, Long) -> Unit) {
   var remaining: Long = extraSize;

   while (remaining != 0L) {
      if (remaining < 4L) {
         throw new IOException("bad zip: truncated header in extra field");
      }

      val headerId: Int = `$this$readExtra`.readShortLe() and '\uffff';
      val dataSize: Long = `$this$readExtra`.readShortLe() and 65535L;
      remaining = remaining - 4;
      if (remaining - 4 < dataSize) {
         throw new IOException("bad zip: truncated value in extra field");
      }

      `$this$readExtra`.require(dataSize);
      val sizeBefore: Long = `$this$readExtra`.getBuffer().size();
      block.invoke(headerId, dataSize);
      val fieldRemaining: Long = dataSize + `$this$readExtra`.getBuffer().size() - sizeBefore;
      if (fieldRemaining < 0L) {
         throw new IOException("unsupported zip: too many bytes processed for $headerId");
      }

      if (fieldRemaining > 0L) {
         `$this$readExtra`.getBuffer().skip(fieldRemaining);
      }

      remaining = remaining - dataSize;
   }
}

internal fun BufferedSource.skipLocalHeader() {
   readOrSkipLocalHeader(`$this$skipLocalHeader`, null);
}

internal fun BufferedSource.readLocalHeader(centralDirectoryZipEntry: ZipEntry): ZipEntry {
   val var10000: ZipEntry = readOrSkipLocalHeader(`$this$readLocalHeader`, centralDirectoryZipEntry);
   return var10000;
}

private fun BufferedSource.readOrSkipLocalHeader(centralDirectoryZipEntry: ZipEntry?): ZipEntry? {
   val signature: Int = `$this$readOrSkipLocalHeader`.readIntLe();
   if (signature != 67324752) {
      throw new IOException("bad zip: expected ${getHex(67324752)} but was ${getHex(signature)}");
   } else {
      `$this$readOrSkipLocalHeader`.skip(2L);
      val bitFlag: Int = `$this$readOrSkipLocalHeader`.readShortLe() and '\uffff';
      if ((bitFlag and 1) != 0) {
         throw new IOException("unsupported zip: general purpose bit flag=${getHex(bitFlag)}");
      } else {
         `$this$readOrSkipLocalHeader`.skip(18L);
         val fileNameLength: Long = `$this$readOrSkipLocalHeader`.readShortLe() and 65535L;
         val extraSize: Int = `$this$readOrSkipLocalHeader`.readShortLe() and '\uffff';
         `$this$readOrSkipLocalHeader`.skip(fileNameLength);
         if (centralDirectoryZipEntry == null) {
            `$this$readOrSkipLocalHeader`.skip((long)extraSize);
            return null;
         } else {
            val extendedLastModifiedAtSeconds: Ref.ObjectRef = new Ref.ObjectRef();
            val extendedLastAccessedAtSeconds: Ref.ObjectRef = new Ref.ObjectRef();
            val extendedCreatedAtSeconds: Ref.ObjectRef = new Ref.ObjectRef();
            readExtra(`$this$readOrSkipLocalHeader`, extraSize, ZipFilesKt::readOrSkipLocalHeader$lambda$0);
            return centralDirectoryZipEntry.copy$okio(
               extendedLastModifiedAtSeconds.element as Int, extendedLastAccessedAtSeconds.element as Int, extendedCreatedAtSeconds.element as Int
            );
         }
      }
   }
}

internal fun filetimeToEpochMillis(filetime: Long): Long {
   return filetime / 10000 - 11644473600000L;
}

internal fun dosDateTimeToEpochMillis(date: Int, time: Int): Long? {
   return if (time == -1)
      null
      else
      _ZlibJvmKt.datePartsToEpochMillis(1980 + (date shr 9 and 127), date shr 5 and 15, date and 31, time shr 11 and 31, time shr 5 and 63, (time and 31) shl 1);
}

fun `openZip$lambda$0`(it: ZipEntry): Boolean {
   return true;
}

fun `readCentralDirectoryZipEntry$lambda$1`(
   `$hasZip64Extra`: Ref.BooleanRef,
   `$requiredZip64ExtraSize`: Long,
   `$size`: Ref.LongRef,
   `$this_readCentralDirectoryZipEntry`: BufferedSource,
   `$compressedSize`: Ref.LongRef,
   `$offset`: Ref.LongRef,
   `$ntfsLastModifiedAtFiletime`: Ref.ObjectRef,
   `$ntfsLastAccessedAtFiletime`: Ref.ObjectRef,
   `$ntfsCreatedAtFiletime`: Ref.ObjectRef,
   headerId: Int,
   dataSize: Long
): Unit {
   switch (headerId) {
      case 1:
         if (`$hasZip64Extra`.element) {
            throw new IOException("bad zip: zip64 extra repeated");
         }

         `$hasZip64Extra`.element = true;
         if (dataSize < `$requiredZip64ExtraSize`) {
            throw new IOException("bad zip: zip64 extra too short");
         }

         `$size`.element = if (`$size`.element == 4294967295L) `$this_readCentralDirectoryZipEntry`.readLongLe() else `$size`.element;
         `$compressedSize`.element = if (`$compressedSize`.element == 4294967295L) `$this_readCentralDirectoryZipEntry`.readLongLe() else 0L;
         `$offset`.element = if (`$offset`.element == 4294967295L) `$this_readCentralDirectoryZipEntry`.readLongLe() else 0L;
         break;
      case 10:
         if (dataSize < 4L) {
            throw new IOException("bad zip: NTFS extra too short");
         }

         `$this_readCentralDirectoryZipEntry`.skip(4L);
         readExtra(`$this_readCentralDirectoryZipEntry`, (int)(dataSize - 4L), ZipFilesKt::readCentralDirectoryZipEntry$lambda$1$0);
      default:
   }

   return Unit.INSTANCE;
}

fun `readCentralDirectoryZipEntry$lambda$1$0`(
   `$ntfsLastModifiedAtFiletime`: Ref.ObjectRef,
   `$this_readCentralDirectoryZipEntry`: BufferedSource,
   `$ntfsLastAccessedAtFiletime`: Ref.ObjectRef,
   `$ntfsCreatedAtFiletime`: Ref.ObjectRef,
   attributeId: Int,
   attributeSize: Long
): Unit {
   if (attributeId == 1) {
      if (`$ntfsLastModifiedAtFiletime`.element != null) {
         throw new IOException("bad zip: NTFS extra attribute tag 0x0001 repeated");
      }

      if (attributeSize != 24L) {
         throw new IOException("bad zip: NTFS extra attribute tag 0x0001 size != 24");
      }

      `$ntfsLastModifiedAtFiletime`.element = (T)`$this_readCentralDirectoryZipEntry`.readLongLe();
      `$ntfsLastAccessedAtFiletime`.element = (T)`$this_readCentralDirectoryZipEntry`.readLongLe();
      `$ntfsCreatedAtFiletime`.element = (T)`$this_readCentralDirectoryZipEntry`.readLongLe();
   }

   return Unit.INSTANCE;
}

fun `readOrSkipLocalHeader$lambda$0`(
   `$this_readOrSkipLocalHeader`: BufferedSource,
   `$extendedLastModifiedAtSeconds`: Ref.ObjectRef,
   `$extendedLastAccessedAtSeconds`: Ref.ObjectRef,
   `$extendedCreatedAtSeconds`: Ref.ObjectRef,
   headerId: Int,
   dataSize: Long
): Unit {
   if (headerId == 21589) {
      if (dataSize < 1L) {
         throw new IOException("bad zip: extended timestamp extra too short");
      }

      val flags: Int = `$this_readOrSkipLocalHeader`.readByte() and 255;
      val hasLastModifiedAtMillis: Boolean = (flags and 1) == 1;
      val hasLastAccessedAtMillis: Boolean = (flags and 2) == 2;
      val hasCreatedAtMillis: Boolean = (flags and 4) == 4;
      var result: Long = 1L;
      if (hasLastModifiedAtMillis) {
         result = 1L + 4L;
      }

      if (hasLastAccessedAtMillis) {
         result += 4L;
      }

      if (hasCreatedAtMillis) {
         result += 4L;
      }

      if (dataSize < result) {
         throw new IOException("bad zip: extended timestamp extra too short");
      }

      if (hasLastModifiedAtMillis) {
         `$extendedLastModifiedAtSeconds`.element = (T)`$this_readOrSkipLocalHeader`.readIntLe();
      }

      if (hasLastAccessedAtMillis) {
         `$extendedLastAccessedAtSeconds`.element = (T)`$this_readOrSkipLocalHeader`.readIntLe();
      }

      if (hasCreatedAtMillis) {
         `$extendedCreatedAtSeconds`.element = (T)`$this_readOrSkipLocalHeader`.readIntLe();
      }
   }

   return Unit.INSTANCE;
}

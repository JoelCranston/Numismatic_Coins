@file:JvmName(name = "-FileSystem")

@file:SourceDebugExtension(["SMAP\nFileSystem.kt\nKotlin\n*S Kotlin\n*F\n+ 1 FileSystem.kt\nokio/internal/-FileSystem\n+ 2 Okio.kt\nokio/Okio__OkioKt\n*L\n1#1,155:1\n58#2,4:156\n58#2,22:160\n66#2,10:182\n62#2,3:192\n77#2,3:195\n*S KotlinDebug\n*F\n+ 1 FileSystem.kt\nokio/internal/-FileSystem\n*L\n65#1:156,4\n66#1:160,22\n65#1:182,10\n65#1:192,3\n65#1:195,3\n*E\n"])

package okio.internal

import java.io.FileNotFoundException
import java.io.IOException
import kotlin.jvm.internal.SourceDebugExtension
import okio.FileMetadata
import okio.FileSystem
import okio.Path
import okio.internal.-FileSystem.commonDeleteRecursively.sequence.1

@Throws(java/io/IOException::class)
internal fun FileSystem.commonMetadata(path: Path): FileMetadata {
   val var10000: FileMetadata = `$this$commonMetadata`.metadataOrNull(path);
   if (var10000 == null) {
      throw new FileNotFoundException("no such file: $path");
   } else {
      return var10000;
   }
}

@Throws(java/io/IOException::class)
internal fun FileSystem.commonExists(path: Path): Boolean {
   return `$this$commonExists`.metadataOrNull(path) != null;
}

@Throws(java/io/IOException::class)
internal fun FileSystem.commonCreateDirectories(dir: Path, mustCreate: Boolean) {
   val directories: ArrayDeque = new ArrayDeque();

   for (Path path = dir; path != null && !$this$commonCreateDirectories.exists(path); path = path.parent()) {
      directories.addFirst(path);
   }

   if (mustCreate && directories.isEmpty()) {
      throw new IOException("$dir already exists.");
   } else {
      for (Path toCreate : directories) {
         FileSystem.createDirectory$default(`$this$commonCreateDirectories`, toCreate, false, 2, null);
      }
   }
}

@Throws(java/io/IOException::class)
internal fun FileSystem.commonCopy(source: Path, target: Path) {
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
   // 001: ldc "<this>"
   // 003: invokestatic kotlin/jvm/internal/Intrinsics.checkNotNullParameter (Ljava/lang/Object;Ljava/lang/String;)V
   // 006: aload 1
   // 007: ldc "source"
   // 009: invokestatic kotlin/jvm/internal/Intrinsics.checkNotNullParameter (Ljava/lang/Object;Ljava/lang/String;)V
   // 00c: aload 2
   // 00d: ldc "target"
   // 00f: invokestatic kotlin/jvm/internal/Intrinsics.checkNotNullParameter (Ljava/lang/Object;Ljava/lang/String;)V
   // 012: aload 0
   // 013: aload 1
   // 014: invokevirtual okio/FileSystem.source (Lokio/Path;)Lokio/Source;
   // 017: checkcast java/io/Closeable
   // 01a: astore 3
   // 01b: bipush 0
   // 01c: istore 4
   // 01e: aconst_null
   // 01f: astore 5
   // 021: nop
   // 022: aload 3
   // 023: checkcast okio/Source
   // 026: astore 6
   // 028: bipush 0
   // 029: istore 7
   // 02b: aload 0
   // 02c: aload 2
   // 02d: bipush 0
   // 02e: bipush 2
   // 02f: aconst_null
   // 030: invokestatic okio/FileSystem.sink$default (Lokio/FileSystem;Lokio/Path;ZILjava/lang/Object;)Lokio/Sink;
   // 033: invokestatic okio/Okio.buffer (Lokio/Sink;)Lokio/BufferedSink;
   // 036: checkcast java/io/Closeable
   // 039: astore 8
   // 03b: bipush 0
   // 03c: istore 9
   // 03e: aconst_null
   // 03f: astore 10
   // 041: nop
   // 042: aload 8
   // 044: checkcast okio/BufferedSink
   // 047: astore 11
   // 049: bipush 0
   // 04a: istore 12
   // 04c: aload 11
   // 04e: aload 6
   // 050: invokeinterface okio/BufferedSink.writeAll (Lokio/Source;)J 2
   // 055: invokestatic java/lang/Long.valueOf (J)Ljava/lang/Long;
   // 058: astore 12
   // 05a: nop
   // 05b: aload 8
   // 05d: dup
   // 05e: ifnull 069
   // 061: invokeinterface java/io/Closeable.close ()V 1
   // 066: goto 077
   // 069: pop
   // 06a: goto 077
   // 06d: astore 13
   // 06f: aload 13
   // 071: astore 10
   // 073: goto 076
   // 076: nop
   // 077: goto 0db
   // 07a: astore 14
   // 07c: aload 14
   // 07e: astore 10
   // 080: aconst_null
   // 081: astore 12
   // 083: nop
   // 084: aload 8
   // 086: dup
   // 087: ifnull 092
   // 08a: invokeinterface java/io/Closeable.close ()V 1
   // 08f: goto 0ab
   // 092: pop
   // 093: goto 0ab
   // 096: astore 13
   // 098: aload 10
   // 09a: ifnonnull 0a4
   // 09d: aload 13
   // 09f: astore 10
   // 0a1: goto 0ab
   // 0a4: aload 10
   // 0a6: aload 13
   // 0a8: invokestatic kotlin/ExceptionsKt.addSuppressed (Ljava/lang/Throwable;Ljava/lang/Throwable;)V
   // 0ab: goto 0db
   // 0ae: astore 14
   // 0b0: nop
   // 0b1: aload 8
   // 0b3: dup
   // 0b4: ifnull 0bf
   // 0b7: invokeinterface java/io/Closeable.close ()V 1
   // 0bc: goto 0d8
   // 0bf: pop
   // 0c0: goto 0d8
   // 0c3: astore 15
   // 0c5: aload 10
   // 0c7: ifnonnull 0d1
   // 0ca: aload 15
   // 0cc: astore 10
   // 0ce: goto 0d8
   // 0d1: aload 10
   // 0d3: aload 15
   // 0d5: invokestatic kotlin/ExceptionsKt.addSuppressed (Ljava/lang/Throwable;Ljava/lang/Throwable;)V
   // 0d8: aload 14
   // 0da: athrow
   // 0db: aload 12
   // 0dd: astore 11
   // 0df: aload 10
   // 0e1: dup
   // 0e2: ifnull 0e6
   // 0e5: athrow
   // 0e6: pop
   // 0e7: aload 11
   // 0e9: checkcast java/lang/Object
   // 0ec: checkcast java/lang/Number
   // 0ef: invokevirtual java/lang/Number.longValue ()J
   // 0f2: nop
   // 0f3: invokestatic java/lang/Long.valueOf (J)Ljava/lang/Long;
   // 0f6: astore 6
   // 0f8: nop
   // 0f9: aload 3
   // 0fa: dup
   // 0fb: ifnull 106
   // 0fe: invokeinterface java/io/Closeable.close ()V 1
   // 103: goto 114
   // 106: pop
   // 107: goto 114
   // 10a: astore 8
   // 10c: aload 8
   // 10e: astore 5
   // 110: goto 113
   // 113: nop
   // 114: goto 176
   // 117: astore 7
   // 119: aload 7
   // 11b: astore 5
   // 11d: aconst_null
   // 11e: astore 6
   // 120: nop
   // 121: aload 3
   // 122: dup
   // 123: ifnull 12e
   // 126: invokeinterface java/io/Closeable.close ()V 1
   // 12b: goto 147
   // 12e: pop
   // 12f: goto 147
   // 132: astore 8
   // 134: aload 5
   // 136: ifnonnull 140
   // 139: aload 8
   // 13b: astore 5
   // 13d: goto 147
   // 140: aload 5
   // 142: aload 8
   // 144: invokestatic kotlin/ExceptionsKt.addSuppressed (Ljava/lang/Throwable;Ljava/lang/Throwable;)V
   // 147: goto 176
   // 14a: astore 7
   // 14c: nop
   // 14d: aload 3
   // 14e: dup
   // 14f: ifnull 15a
   // 152: invokeinterface java/io/Closeable.close ()V 1
   // 157: goto 173
   // 15a: pop
   // 15b: goto 173
   // 15e: astore 9
   // 160: aload 5
   // 162: ifnonnull 16c
   // 165: aload 9
   // 167: astore 5
   // 169: goto 173
   // 16c: aload 5
   // 16e: aload 9
   // 170: invokestatic kotlin/ExceptionsKt.addSuppressed (Ljava/lang/Throwable;Ljava/lang/Throwable;)V
   // 173: aload 7
   // 175: athrow
   // 176: aload 6
   // 178: astore 16
   // 17a: aload 5
   // 17c: dup
   // 17d: ifnull 181
   // 180: athrow
   // 181: pop
   // 182: aload 16
   // 184: checkcast java/lang/Object
   // 187: pop
   // 188: return
}

@Throws(java/io/IOException::class)
internal fun FileSystem.commonDeleteRecursively(fileOrDirectory: Path, mustExist: Boolean) {
   val iterator: java.util.Iterator = SequencesKt.sequence(new 1(`$this$commonDeleteRecursively`, fileOrDirectory, null)).iterator();

   while (iterator.hasNext()) {
      `$this$commonDeleteRecursively`.delete(iterator.next() as Path, mustExist && !iterator.hasNext());
   }
}

@Throws(java/io/IOException::class)
internal fun FileSystem.commonListRecursively(dir: Path, followSymlinks: Boolean): Sequence<Path> {
   return SequencesKt.sequence(new okio.internal.-FileSystem.commonListRecursively.1(dir, `$this$commonListRecursively`, followSymlinks, null));
}

internal suspend fun SequenceScope<Path>.collectRecursively(
   fileSystem: FileSystem,
   stack: ArrayDeque<Path>,
   path: Path,
   followSymlinks: Boolean,
   postorder: Boolean
) {
   // $VF: Couldn't be decompiled
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   // java.lang.IndexOutOfBoundsException: Index 0 out of bounds for length 0
   //   at java.base/jdk.internal.util.Preconditions.outOfBounds(Preconditions.java:100)
   //   at java.base/jdk.internal.util.Preconditions.outOfBoundsCheckIndex(Preconditions.java:106)
   //   at java.base/jdk.internal.util.Preconditions.checkIndex(Preconditions.java:302)
   //   at java.base/java.util.Objects.checkIndex(Objects.java:365)
   //   at java.base/java.util.ArrayList.remove(ArrayList.java:552)
   //   at org.jetbrains.java.decompiler.modules.decompiler.FinallyProcessor.removeExceptionInstructionsEx(FinallyProcessor.java:1057)
   //   at org.jetbrains.java.decompiler.modules.decompiler.FinallyProcessor.insertSemaphore(FinallyProcessor.java:350)
   //   at org.jetbrains.java.decompiler.modules.decompiler.FinallyProcessor.iterateGraph(FinallyProcessor.java:99)
   //
   // Bytecode:
   // 000: aload 6
   // 002: instanceof okio/internal/-FileSystem$collectRecursively$1
   // 005: ifeq 029
   // 008: aload 6
   // 00a: checkcast okio/internal/-FileSystem$collectRecursively$1
   // 00d: astore 13
   // 00f: aload 13
   // 011: getfield okio/internal/-FileSystem$collectRecursively$1.label I
   // 014: ldc -2147483648
   // 016: iand
   // 017: ifeq 029
   // 01a: aload 13
   // 01c: dup
   // 01d: getfield okio/internal/-FileSystem$collectRecursively$1.label I
   // 020: ldc -2147483648
   // 022: isub
   // 023: putfield okio/internal/-FileSystem$collectRecursively$1.label I
   // 026: goto 034
   // 029: new okio/internal/-FileSystem$collectRecursively$1
   // 02c: dup
   // 02d: aload 6
   // 02f: invokespecial okio/internal/-FileSystem$collectRecursively$1.<init> (Lkotlin/coroutines/Continuation;)V
   // 032: astore 13
   // 034: aload 13
   // 036: getfield okio/internal/-FileSystem$collectRecursively$1.result Ljava/lang/Object;
   // 039: astore 12
   // 03b: invokestatic kotlin/coroutines/intrinsics/IntrinsicsKt.getCOROUTINE_SUSPENDED ()Ljava/lang/Object;
   // 03e: astore 14
   // 040: aload 13
   // 042: getfield okio/internal/-FileSystem$collectRecursively$1.label I
   // 045: tableswitch 749 0 3 31 101 441 673
   // 064: aload 12
   // 066: invokestatic kotlin/ResultKt.throwOnFailure (Ljava/lang/Object;)V
   // 069: iload 5
   // 06b: ifne 0e4
   // 06e: aload 0
   // 06f: aload 3
   // 070: aload 13
   // 072: aload 13
   // 074: aload 0
   // 075: putfield okio/internal/-FileSystem$collectRecursively$1.L$0 Ljava/lang/Object;
   // 078: aload 13
   // 07a: aload 1
   // 07b: putfield okio/internal/-FileSystem$collectRecursively$1.L$1 Ljava/lang/Object;
   // 07e: aload 13
   // 080: aload 2
   // 081: putfield okio/internal/-FileSystem$collectRecursively$1.L$2 Ljava/lang/Object;
   // 084: aload 13
   // 086: aload 3
   // 087: putfield okio/internal/-FileSystem$collectRecursively$1.L$3 Ljava/lang/Object;
   // 08a: aload 13
   // 08c: iload 4
   // 08e: putfield okio/internal/-FileSystem$collectRecursively$1.Z$0 Z
   // 091: aload 13
   // 093: iload 5
   // 095: putfield okio/internal/-FileSystem$collectRecursively$1.Z$1 Z
   // 098: aload 13
   // 09a: bipush 1
   // 09b: putfield okio/internal/-FileSystem$collectRecursively$1.label I
   // 09e: invokevirtual kotlin/sequences/SequenceScope.yield (Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;
   // 0a1: dup
   // 0a2: aload 14
   // 0a4: if_acmpne 0e3
   // 0a7: aload 14
   // 0a9: areturn
   // 0aa: aload 13
   // 0ac: getfield okio/internal/-FileSystem$collectRecursively$1.Z$1 Z
   // 0af: istore 5
   // 0b1: aload 13
   // 0b3: getfield okio/internal/-FileSystem$collectRecursively$1.Z$0 Z
   // 0b6: istore 4
   // 0b8: aload 13
   // 0ba: getfield okio/internal/-FileSystem$collectRecursively$1.L$3 Ljava/lang/Object;
   // 0bd: checkcast okio/Path
   // 0c0: astore 3
   // 0c1: aload 13
   // 0c3: getfield okio/internal/-FileSystem$collectRecursively$1.L$2 Ljava/lang/Object;
   // 0c6: checkcast kotlin/collections/ArrayDeque
   // 0c9: astore 2
   // 0ca: aload 13
   // 0cc: getfield okio/internal/-FileSystem$collectRecursively$1.L$1 Ljava/lang/Object;
   // 0cf: checkcast okio/FileSystem
   // 0d2: astore 1
   // 0d3: aload 13
   // 0d5: getfield okio/internal/-FileSystem$collectRecursively$1.L$0 Ljava/lang/Object;
   // 0d8: checkcast kotlin/sequences/SequenceScope
   // 0db: astore 0
   // 0dc: aload 12
   // 0de: invokestatic kotlin/ResultKt.throwOnFailure (Ljava/lang/Object;)V
   // 0e1: aload 12
   // 0e3: pop
   // 0e4: aload 1
   // 0e5: aload 3
   // 0e6: invokevirtual okio/FileSystem.listOrNull (Lokio/Path;)Ljava/util/List;
   // 0e9: dup
   // 0ea: ifnonnull 0f1
   // 0ed: pop
   // 0ee: invokestatic kotlin/collections/CollectionsKt.emptyList ()Ljava/util/List;
   // 0f1: astore 7
   // 0f3: aload 7
   // 0f5: checkcast java/util/Collection
   // 0f8: invokeinterface java/util/Collection.isEmpty ()Z 1
   // 0fd: ifne 104
   // 100: bipush 1
   // 101: goto 105
   // 104: bipush 0
   // 105: ifeq 27d
   // 108: aload 3
   // 109: astore 8
   // 10b: bipush 0
   // 10c: istore 9
   // 10e: nop
   // 10f: iload 4
   // 111: ifeq 139
   // 114: aload 2
   // 115: aload 8
   // 117: invokevirtual kotlin/collections/ArrayDeque.contains (Ljava/lang/Object;)Z
   // 11a: ifeq 139
   // 11d: new java/io/IOException
   // 120: dup
   // 121: new java/lang/StringBuilder
   // 124: dup
   // 125: invokespecial java/lang/StringBuilder.<init> ()V
   // 128: ldc_w "symlink cycle at "
   // 12b: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
   // 12e: aload 3
   // 12f: invokevirtual java/lang/StringBuilder.append (Ljava/lang/Object;)Ljava/lang/StringBuilder;
   // 132: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
   // 135: invokespecial java/io/IOException.<init> (Ljava/lang/String;)V
   // 138: athrow
   // 139: aload 1
   // 13a: aload 8
   // 13c: invokestatic okio/internal/-FileSystem.symlinkTarget (Lokio/FileSystem;Lokio/Path;)Lokio/Path;
   // 13f: dup
   // 140: ifnonnull 147
   // 143: pop
   // 144: goto 14f
   // 147: astore 8
   // 149: iinc 9 1
   // 14c: goto 10e
   // 14f: iload 4
   // 151: ifne 159
   // 154: iload 9
   // 156: ifne 27d
   // 159: aload 2
   // 15a: aload 8
   // 15c: invokevirtual kotlin/collections/ArrayDeque.addLast (Ljava/lang/Object;)V
   // 15f: nop
   // 160: aload 7
   // 162: invokeinterface java/util/List.iterator ()Ljava/util/Iterator; 1
   // 167: astore 10
   // 169: aload 10
   // 16b: invokeinterface java/util/Iterator.hasNext ()Z 1
   // 170: ifeq 26b
   // 173: aload 10
   // 175: invokeinterface java/util/Iterator.next ()Ljava/lang/Object; 1
   // 17a: checkcast okio/Path
   // 17d: astore 11
   // 17f: aload 0
   // 180: aload 1
   // 181: aload 2
   // 182: aload 11
   // 184: iload 4
   // 186: ifeq 18d
   // 189: bipush 1
   // 18a: goto 18e
   // 18d: bipush 0
   // 18e: iload 5
   // 190: ifeq 197
   // 193: bipush 1
   // 194: goto 198
   // 197: bipush 0
   // 198: aload 13
   // 19a: aload 13
   // 19c: aload 0
   // 19d: putfield okio/internal/-FileSystem$collectRecursively$1.L$0 Ljava/lang/Object;
   // 1a0: aload 13
   // 1a2: aload 1
   // 1a3: putfield okio/internal/-FileSystem$collectRecursively$1.L$1 Ljava/lang/Object;
   // 1a6: aload 13
   // 1a8: aload 2
   // 1a9: putfield okio/internal/-FileSystem$collectRecursively$1.L$2 Ljava/lang/Object;
   // 1ac: aload 13
   // 1ae: aload 3
   // 1af: putfield okio/internal/-FileSystem$collectRecursively$1.L$3 Ljava/lang/Object;
   // 1b2: aload 13
   // 1b4: aload 7
   // 1b6: invokestatic kotlin/coroutines/jvm/internal/SpillingKt.nullOutSpilledVariable (Ljava/lang/Object;)Ljava/lang/Object;
   // 1b9: putfield okio/internal/-FileSystem$collectRecursively$1.L$4 Ljava/lang/Object;
   // 1bc: aload 13
   // 1be: aload 8
   // 1c0: invokestatic kotlin/coroutines/jvm/internal/SpillingKt.nullOutSpilledVariable (Ljava/lang/Object;)Ljava/lang/Object;
   // 1c3: putfield okio/internal/-FileSystem$collectRecursively$1.L$5 Ljava/lang/Object;
   // 1c6: aload 13
   // 1c8: aload 10
   // 1ca: putfield okio/internal/-FileSystem$collectRecursively$1.L$6 Ljava/lang/Object;
   // 1cd: aload 13
   // 1cf: aload 11
   // 1d1: invokestatic kotlin/coroutines/jvm/internal/SpillingKt.nullOutSpilledVariable (Ljava/lang/Object;)Ljava/lang/Object;
   // 1d4: putfield okio/internal/-FileSystem$collectRecursively$1.L$7 Ljava/lang/Object;
   // 1d7: aload 13
   // 1d9: iload 4
   // 1db: putfield okio/internal/-FileSystem$collectRecursively$1.Z$0 Z
   // 1de: aload 13
   // 1e0: iload 5
   // 1e2: putfield okio/internal/-FileSystem$collectRecursively$1.Z$1 Z
   // 1e5: aload 13
   // 1e7: iload 9
   // 1e9: putfield okio/internal/-FileSystem$collectRecursively$1.I$0 I
   // 1ec: aload 13
   // 1ee: bipush 2
   // 1ef: putfield okio/internal/-FileSystem$collectRecursively$1.label I
   // 1f2: invokestatic okio/internal/-FileSystem.collectRecursively (Lkotlin/sequences/SequenceScope;Lokio/FileSystem;Lkotlin/collections/ArrayDeque;Lokio/Path;ZZLkotlin/coroutines/Continuation;)Ljava/lang/Object;
   // 1f5: dup
   // 1f6: aload 14
   // 1f8: if_acmpne 267
   // 1fb: aload 14
   // 1fd: areturn
   // 1fe: aload 13
   // 200: getfield okio/internal/-FileSystem$collectRecursively$1.I$0 I
   // 203: istore 9
   // 205: aload 13
   // 207: getfield okio/internal/-FileSystem$collectRecursively$1.Z$1 Z
   // 20a: istore 5
   // 20c: aload 13
   // 20e: getfield okio/internal/-FileSystem$collectRecursively$1.Z$0 Z
   // 211: istore 4
   // 213: aload 13
   // 215: getfield okio/internal/-FileSystem$collectRecursively$1.L$7 Ljava/lang/Object;
   // 218: checkcast okio/Path
   // 21b: astore 11
   // 21d: aload 13
   // 21f: getfield okio/internal/-FileSystem$collectRecursively$1.L$6 Ljava/lang/Object;
   // 222: checkcast java/util/Iterator
   // 225: astore 10
   // 227: aload 13
   // 229: getfield okio/internal/-FileSystem$collectRecursively$1.L$5 Ljava/lang/Object;
   // 22c: checkcast okio/Path
   // 22f: astore 8
   // 231: aload 13
   // 233: getfield okio/internal/-FileSystem$collectRecursively$1.L$4 Ljava/lang/Object;
   // 236: checkcast java/util/List
   // 239: astore 7
   // 23b: aload 13
   // 23d: getfield okio/internal/-FileSystem$collectRecursively$1.L$3 Ljava/lang/Object;
   // 240: checkcast okio/Path
   // 243: astore 3
   // 244: aload 13
   // 246: getfield okio/internal/-FileSystem$collectRecursively$1.L$2 Ljava/lang/Object;
   // 249: checkcast kotlin/collections/ArrayDeque
   // 24c: astore 2
   // 24d: aload 13
   // 24f: getfield okio/internal/-FileSystem$collectRecursively$1.L$1 Ljava/lang/Object;
   // 252: checkcast okio/FileSystem
   // 255: astore 1
   // 256: aload 13
   // 258: getfield okio/internal/-FileSystem$collectRecursively$1.L$0 Ljava/lang/Object;
   // 25b: checkcast kotlin/sequences/SequenceScope
   // 25e: astore 0
   // 25f: nop
   // 260: aload 12
   // 262: invokestatic kotlin/ResultKt.throwOnFailure (Ljava/lang/Object;)V
   // 265: aload 12
   // 267: pop
   // 268: goto 169
   // 26b: aload 2
   // 26c: invokevirtual kotlin/collections/ArrayDeque.removeLast ()Ljava/lang/Object;
   // 26f: pop
   // 270: goto 27d
   // 273: astore 10
   // 275: aload 2
   // 276: invokevirtual kotlin/collections/ArrayDeque.removeLast ()Ljava/lang/Object;
   // 279: pop
   // 27a: aload 10
   // 27c: athrow
   // 27d: iload 5
   // 27f: ifeq 32e
   // 282: aload 0
   // 283: aload 3
   // 284: aload 13
   // 286: aload 13
   // 288: aload 0
   // 289: invokestatic kotlin/coroutines/jvm/internal/SpillingKt.nullOutSpilledVariable (Ljava/lang/Object;)Ljava/lang/Object;
   // 28c: putfield okio/internal/-FileSystem$collectRecursively$1.L$0 Ljava/lang/Object;
   // 28f: aload 13
   // 291: aload 1
   // 292: invokestatic kotlin/coroutines/jvm/internal/SpillingKt.nullOutSpilledVariable (Ljava/lang/Object;)Ljava/lang/Object;
   // 295: putfield okio/internal/-FileSystem$collectRecursively$1.L$1 Ljava/lang/Object;
   // 298: aload 13
   // 29a: aload 2
   // 29b: invokestatic kotlin/coroutines/jvm/internal/SpillingKt.nullOutSpilledVariable (Ljava/lang/Object;)Ljava/lang/Object;
   // 29e: putfield okio/internal/-FileSystem$collectRecursively$1.L$2 Ljava/lang/Object;
   // 2a1: aload 13
   // 2a3: aload 3
   // 2a4: invokestatic kotlin/coroutines/jvm/internal/SpillingKt.nullOutSpilledVariable (Ljava/lang/Object;)Ljava/lang/Object;
   // 2a7: putfield okio/internal/-FileSystem$collectRecursively$1.L$3 Ljava/lang/Object;
   // 2aa: aload 13
   // 2ac: aload 7
   // 2ae: invokestatic kotlin/coroutines/jvm/internal/SpillingKt.nullOutSpilledVariable (Ljava/lang/Object;)Ljava/lang/Object;
   // 2b1: putfield okio/internal/-FileSystem$collectRecursively$1.L$4 Ljava/lang/Object;
   // 2b4: aload 13
   // 2b6: aconst_null
   // 2b7: putfield okio/internal/-FileSystem$collectRecursively$1.L$5 Ljava/lang/Object;
   // 2ba: aload 13
   // 2bc: aconst_null
   // 2bd: putfield okio/internal/-FileSystem$collectRecursively$1.L$6 Ljava/lang/Object;
   // 2c0: aload 13
   // 2c2: aconst_null
   // 2c3: putfield okio/internal/-FileSystem$collectRecursively$1.L$7 Ljava/lang/Object;
   // 2c6: aload 13
   // 2c8: iload 4
   // 2ca: putfield okio/internal/-FileSystem$collectRecursively$1.Z$0 Z
   // 2cd: aload 13
   // 2cf: iload 5
   // 2d1: putfield okio/internal/-FileSystem$collectRecursively$1.Z$1 Z
   // 2d4: aload 13
   // 2d6: bipush 3
   // 2d7: putfield okio/internal/-FileSystem$collectRecursively$1.label I
   // 2da: invokevirtual kotlin/sequences/SequenceScope.yield (Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;
   // 2dd: dup
   // 2de: aload 14
   // 2e0: if_acmpne 329
   // 2e3: aload 14
   // 2e5: areturn
   // 2e6: aload 13
   // 2e8: getfield okio/internal/-FileSystem$collectRecursively$1.Z$1 Z
   // 2eb: istore 5
   // 2ed: aload 13
   // 2ef: getfield okio/internal/-FileSystem$collectRecursively$1.Z$0 Z
   // 2f2: istore 4
   // 2f4: aload 13
   // 2f6: getfield okio/internal/-FileSystem$collectRecursively$1.L$4 Ljava/lang/Object;
   // 2f9: checkcast java/util/List
   // 2fc: astore 7
   // 2fe: aload 13
   // 300: getfield okio/internal/-FileSystem$collectRecursively$1.L$3 Ljava/lang/Object;
   // 303: checkcast okio/Path
   // 306: astore 3
   // 307: aload 13
   // 309: getfield okio/internal/-FileSystem$collectRecursively$1.L$2 Ljava/lang/Object;
   // 30c: checkcast kotlin/collections/ArrayDeque
   // 30f: astore 2
   // 310: aload 13
   // 312: getfield okio/internal/-FileSystem$collectRecursively$1.L$1 Ljava/lang/Object;
   // 315: checkcast okio/FileSystem
   // 318: astore 1
   // 319: aload 13
   // 31b: getfield okio/internal/-FileSystem$collectRecursively$1.L$0 Ljava/lang/Object;
   // 31e: checkcast kotlin/sequences/SequenceScope
   // 321: astore 0
   // 322: aload 12
   // 324: invokestatic kotlin/ResultKt.throwOnFailure (Ljava/lang/Object;)V
   // 327: aload 12
   // 329: pop
   // 32a: getstatic kotlin/Unit.INSTANCE Lkotlin/Unit;
   // 32d: areturn
   // 32e: getstatic kotlin/Unit.INSTANCE Lkotlin/Unit;
   // 331: areturn
   // 332: new java/lang/IllegalStateException
   // 335: dup
   // 336: ldc_w "call to 'resume' before 'invoke' with coroutine"
   // 339: invokespecial java/lang/IllegalStateException.<init> (Ljava/lang/String;)V
   // 33c: athrow
}

@Throws(java/io/IOException::class)
internal fun FileSystem.symlinkTarget(path: Path): Path? {
   val var10000: Path = `$this$symlinkTarget`.metadata(path).getSymlinkTarget();
   if (var10000 == null) {
      return null;
   } else {
      val var3: Path = path.parent();
      return var3.resolve(var10000);
   }
}

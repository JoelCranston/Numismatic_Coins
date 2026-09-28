package it.krzeminski.snakeyaml.engine.kmp.api

import it.krzeminski.snakeyaml.engine.kmp.api.YamlUnicodeReader.CharEncoding.Companion.special..inlined.sortedByDescending.1
import kotlin.enums.EnumEntries
import kotlin.jvm.internal.SourceDebugExtension
import okio.Buffer
import okio.BufferedSource
import okio.ByteString
import okio.Okio
import okio.Options
import okio.Source
import okio.Timeout
import okio.TypedOptions

@SourceDebugExtension(["SMAP\nYamlUnicodeReader.kt\nKotlin\n*S Kotlin\n*F\n+ 1 YamlUnicodeReader.kt\nit/krzeminski/snakeyaml/engine/kmp/api/YamlUnicodeReader\n+ 2 Okio.kt\nokio/Okio__OkioKt\n*L\n1#1,141:1\n72#2:142\n58#2,22:143\n*S KotlinDebug\n*F\n+ 1 YamlUnicodeReader.kt\nit/krzeminski/snakeyaml/engine/kmp/api/YamlUnicodeReader\n*L\n80#1:142\n80#1:143,22\n*E\n"])
public class YamlUnicodeReader(source: BufferedSource) : Source {
   private final val source: BufferedSource
   public final val encoding: it.krzeminski.snakeyaml.engine.kmp.api.YamlUnicodeReader.CharEncoding
   private final val codepointReader: it.krzeminski.snakeyaml.engine.kmp.api.YamlUnicodeReader.CodepointReader?

   init {
      this.source = source;
      var var10001: YamlUnicodeReader.CharEncoding = YamlUnicodeReader.CharEncoding.Companion.detectCharEncoding$snakeyaml_engine_kmp(this.source);
      if (var10001 == null) {
         var10001 = YamlUnicodeReader.CharEncoding.UTF_8;
      }

      this.encoding = var10001;
      var var2: YamlUnicodeReader.CodepointReader;
      switch (YamlUnicodeReader.WhenMappings.$EnumSwitchMapping$0[this.encoding.ordinal()]) {
         case 1:
            var2 = null;
            break;
         case 2:
            var2 = YamlUnicodeReader.CodepointReader.Companion.getUTF_16BE();
            break;
         case 3:
            var2 = YamlUnicodeReader.CodepointReader.Companion.getUTF_16LE();
            break;
         case 4:
            var2 = YamlUnicodeReader.CodepointReader.Companion.getUTF_32BE();
            break;
         case 5:
            var2 = YamlUnicodeReader.CodepointReader.Companion.getUTF_32LE();
            break;
         default:
            throw new NoWhenBranchMatchedException();
      }

      this.codepointReader = var2;
   }

   public constructor(source: Source) : this(Okio.buffer(source))
   public override fun read(sink: Buffer, byteCount: Long): Long {
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
      // 001: ldc "sink"
      // 003: invokestatic kotlin/jvm/internal/Intrinsics.checkNotNullParameter (Ljava/lang/Object;Ljava/lang/String;)V
      // 006: aload 0
      // 007: getfield it/krzeminski/snakeyaml/engine/kmp/api/YamlUnicodeReader.codepointReader Lit/krzeminski/snakeyaml/engine/kmp/api/YamlUnicodeReader$CodepointReader;
      // 00a: ifnull 106
      // 00d: new okio/Buffer
      // 010: dup
      // 011: invokespecial okio/Buffer.<init> ()V
      // 014: checkcast java/io/Closeable
      // 017: astore 4
      // 019: bipush 0
      // 01a: istore 5
      // 01c: aconst_null
      // 01d: astore 6
      // 01f: nop
      // 020: aload 4
      // 022: checkcast okio/Buffer
      // 025: astore 7
      // 027: bipush 0
      // 028: istore 8
      // 02a: aload 0
      // 02b: getfield it/krzeminski/snakeyaml/engine/kmp/api/YamlUnicodeReader.source Lokio/BufferedSource;
      // 02e: aload 0
      // 02f: getfield it/krzeminski/snakeyaml/engine/kmp/api/YamlUnicodeReader.encoding Lit/krzeminski/snakeyaml/engine/kmp/api/YamlUnicodeReader$CharEncoding;
      // 032: invokevirtual it/krzeminski/snakeyaml/engine/kmp/api/YamlUnicodeReader$CharEncoding.getCharSize$snakeyaml_engine_kmp ()J
      // 035: invokeinterface okio/BufferedSource.request (J)Z 3
      // 03a: ifeq 061
      // 03d: aload 7
      // 03f: invokevirtual okio/Buffer.size ()J
      // 042: lload 2
      // 043: lcmp
      // 044: ifge 061
      // 047: aload 0
      // 048: getfield it/krzeminski/snakeyaml/engine/kmp/api/YamlUnicodeReader.codepointReader Lit/krzeminski/snakeyaml/engine/kmp/api/YamlUnicodeReader$CodepointReader;
      // 04b: aload 0
      // 04c: getfield it/krzeminski/snakeyaml/engine/kmp/api/YamlUnicodeReader.source Lokio/BufferedSource;
      // 04f: invokeinterface it/krzeminski/snakeyaml/engine/kmp/api/YamlUnicodeReader$CodepointReader.readCodepoint (Lokio/BufferedSource;)I 2
      // 054: istore 9
      // 056: aload 7
      // 058: iload 9
      // 05a: invokevirtual okio/Buffer.writeUtf8CodePoint (I)Lokio/Buffer;
      // 05d: pop
      // 05e: goto 02a
      // 061: aload 7
      // 063: invokevirtual okio/Buffer.size ()J
      // 066: lconst_0
      // 067: lcmp
      // 068: ifgt 080
      // 06b: ldc2_w -1
      // 06e: lstore 15
      // 070: nop
      // 071: aload 4
      // 073: invokeinterface java/io/Closeable.close ()V 1
      // 078: goto 07d
      // 07b: astore 7
      // 07d: lload 15
      // 07f: lreturn
      // 080: aload 7
      // 082: aload 1
      // 083: lconst_0
      // 084: bipush 2
      // 085: aconst_null
      // 086: invokestatic okio/Buffer.copyTo$default (Lokio/Buffer;Lokio/Buffer;JILjava/lang/Object;)Lokio/Buffer;
      // 089: pop
      // 08a: aload 7
      // 08c: invokevirtual okio/Buffer.size ()J
      // 08f: lstore 13
      // 091: nop
      // 092: aload 4
      // 094: invokeinterface java/io/Closeable.close ()V 1
      // 099: goto 09e
      // 09c: astore 7
      // 09e: lload 13
      // 0a0: lreturn
      // 0a1: astore 11
      // 0a3: aload 11
      // 0a5: astore 6
      // 0a7: aconst_null
      // 0a8: astore 10
      // 0aa: nop
      // 0ab: aload 4
      // 0ad: invokeinterface java/io/Closeable.close ()V 1
      // 0b2: goto 0ca
      // 0b5: astore 7
      // 0b7: aload 6
      // 0b9: ifnonnull 0c3
      // 0bc: aload 7
      // 0be: astore 6
      // 0c0: goto 0ca
      // 0c3: aload 6
      // 0c5: aload 7
      // 0c7: invokestatic kotlin/ExceptionsKt.addSuppressed (Ljava/lang/Throwable;Ljava/lang/Throwable;)V
      // 0ca: goto 0f2
      // 0cd: astore 11
      // 0cf: nop
      // 0d0: aload 4
      // 0d2: invokeinterface java/io/Closeable.close ()V 1
      // 0d7: goto 0ef
      // 0da: astore 8
      // 0dc: aload 6
      // 0de: ifnonnull 0e8
      // 0e1: aload 8
      // 0e3: astore 6
      // 0e5: goto 0ef
      // 0e8: aload 6
      // 0ea: aload 8
      // 0ec: invokestatic kotlin/ExceptionsKt.addSuppressed (Ljava/lang/Throwable;Ljava/lang/Throwable;)V
      // 0ef: aload 11
      // 0f1: athrow
      // 0f2: aload 10
      // 0f4: astore 12
      // 0f6: aload 6
      // 0f8: dup
      // 0f9: ifnull 0fd
      // 0fc: athrow
      // 0fd: pop
      // 0fe: new kotlin/KotlinNothingValueException
      // 101: dup
      // 102: invokespecial kotlin/KotlinNothingValueException.<init> ()V
      // 105: athrow
      // 106: aload 0
      // 107: getfield it/krzeminski/snakeyaml/engine/kmp/api/YamlUnicodeReader.source Lokio/BufferedSource;
      // 10a: aload 1
      // 10b: lload 2
      // 10c: invokeinterface okio/BufferedSource.read (Lokio/Buffer;J)J 4
      // 111: lreturn
   }

   public override fun close() {
      this.source.close();
   }

   public override fun timeout(): Timeout {
      return this.source.timeout();
   }

   internal fun readString(): String {
      return Okio.buffer(this).readUtf8();
   }

   public enum class CharEncoding(bom: ByteString, charSize: Long) {
      UTF_8("efbbbf", 2),
      UTF_16BE("feff", 2),
      UTF_16LE("fffe", 2),
      UTF_32BE("0000feff", 4),
      UTF_32LE("fffe0000", 4)
      internal final val bom: ByteString
      internal final val charSize: Long
      @JvmStatic
      public YamlUnicodeReader.CharEncoding.Companion Companion = new YamlUnicodeReader.CharEncoding.Companion(null);

      init {
         this.bom = bom;
         this.charSize = charSize;
      }

      private constructor(bom: String, charSize: Int) : this(ByteString.Companion.decodeHex(bom), (long)charSize)
      @JvmStatic
      fun getEntries(): EnumEntries<YamlUnicodeReader.CharEncoding> {
         return $ENTRIES;
      }

      @SourceDebugExtension(["SMAP\nYamlUnicodeReader.kt\nKotlin\n*S Kotlin\n*F\n+ 1 YamlUnicodeReader.kt\nit/krzeminski/snakeyaml/engine/kmp/api/YamlUnicodeReader$CharEncoding$Companion\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 TypedOptions.kt\nokio/TypedOptions$Companion\n*L\n1#1,141:1\n1068#2:142\n46#3,3:143\n*S KotlinDebug\n*F\n+ 1 YamlUnicodeReader.kt\nit/krzeminski/snakeyaml/engine/kmp/api/YamlUnicodeReader$CharEncoding$Companion\n*L\n132#1:142\n134#1:143,3\n*E\n"])
      public companion object {
         private final val UnicodeBomOptions: TypedOptions<it.krzeminski.snakeyaml.engine.kmp.api.YamlUnicodeReader.CharEncoding>
            private final get() {
               val sortedEntries: java.util.List = CollectionsKt.sortedWith(YamlUnicodeReader.CharEncoding.getEntries(), new 1<>());
               val var17: TypedOptions.Companion = TypedOptions.Companion;
               val `list$iv`: java.util.List = CollectionsKt.toList(sortedEntries);
               val var10000: Options.Companion = Options.Companion;
               var var6: Int = 0;
               val var7: Int = `list$iv`.size();
               val var8: Array<ByteString> = new ByteString[var7];

               for (; var6 < var7; var6++) {
                  var8[var6] = (`list$iv`.get(var6) as YamlUnicodeReader.CharEncoding).getBom$snakeyaml_engine_kmp();
               }

               return new TypedOptions<>(`list$iv`, var10000.of(var8));
            }


         internal fun BufferedSource.detectCharEncoding(): it.krzeminski.snakeyaml.engine.kmp.api.YamlUnicodeReader.CharEncoding? {
            return `$this$detectCharEncoding`.select(this.getUnicodeBomOptions());
         }
      }
   }

   private fun interface CodepointReader {
      public abstract fun readCodepoint(source: BufferedSource): Int {
      }

      public companion object {
         public final val UTF_16BE: it.krzeminski.snakeyaml.engine.kmp.api.YamlUnicodeReader.CodepointReader =
            YamlUnicodeReader.CodepointReader.Companion::UTF_16BE$lambda$0
            public final val UTF_16LE: it.krzeminski.snakeyaml.engine.kmp.api.YamlUnicodeReader.CodepointReader =
            YamlUnicodeReader.CodepointReader.Companion::UTF_16LE$lambda$1
            public final val UTF_32BE: it.krzeminski.snakeyaml.engine.kmp.api.YamlUnicodeReader.CodepointReader =
            YamlUnicodeReader.CodepointReader.Companion::UTF_32BE$lambda$2
            public final val UTF_32LE: it.krzeminski.snakeyaml.engine.kmp.api.YamlUnicodeReader.CodepointReader =
            YamlUnicodeReader.CodepointReader.Companion::UTF_32LE$lambda$3

         @JvmStatic
         fun `UTF_16BE$lambda$0`(source: BufferedSource): Int {
            return source.readShort();
         }

         @JvmStatic
         fun `UTF_16LE$lambda$1`(source: BufferedSource): Int {
            return source.readShortLe();
         }

         @JvmStatic
         fun `UTF_32BE$lambda$2`(source: BufferedSource): Int {
            return source.readInt();
         }

         @JvmStatic
         fun `UTF_32LE$lambda$3`(source: BufferedSource): Int {
            return source.readIntLe();
         }
      }
   }

   public companion object
}

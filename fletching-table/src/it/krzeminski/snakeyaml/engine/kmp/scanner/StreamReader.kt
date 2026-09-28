package it.krzeminski.snakeyaml.engine.kmp.scanner

import it.krzeminski.snakeyaml.engine.kmp.api.LoadSettings
import it.krzeminski.snakeyaml.engine.kmp.common.CharConstants
import it.krzeminski.snakeyaml.engine.kmp.exceptions.Mark
import it.krzeminski.snakeyaml.engine.kmp.exceptions.ReaderException
import it.krzeminski.snakeyaml.engine.kmp.exceptions.YamlEngineException
import it.krzeminski.snakeyaml.engine.kmp.internal.utils.CharSequenceExtensionsKt
import it.krzeminski.snakeyaml.engine.kmp.internal.utils.Character
import java.io.IOException
import kotlin.jvm.internal.SourceDebugExtension
import okio.Buffer
import okio.BufferedSource
import okio.ByteString
import okio.Source

@SourceDebugExtension(["SMAP\nStreamReader.kt\nKotlin\n*S Kotlin\n*F\n+ 1 StreamReader.kt\nit/krzeminski/snakeyaml/engine/kmp/scanner/StreamReader\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 BufferedSourceExtensions.kt\nit/krzeminski/snakeyaml/engine/kmp/internal/utils/BufferedSourceExtensionsKt\n*L\n1#1,313:1\n1#2:314\n11#3,17:315\n86#3,2:332\n31#3,13:334\n86#3,2:347\n44#3,18:349\n86#3,2:367\n67#3,6:369\n95#3,11:375\n74#3,8:386\n*S KotlinDebug\n*F\n+ 1 StreamReader.kt\nit/krzeminski/snakeyaml/engine/kmp/scanner/StreamReader\n*L\n202#1:315,17\n202#1:332,2\n202#1:334,13\n202#1:347,2\n202#1:349,18\n202#1:367,2\n202#1:369,6\n202#1:375,11\n202#1:386,8\n*E\n"])
public class StreamReader(loadSettings: LoadSettings, stream: Source) {
   private final val stream: BufferedSource
   private final val name: String
   private final val useMarks: Boolean
   private final val bufferReadSize: Long
   private final var codePointsWindow: IntArray
   private final var dataLength: Int
   private final var pointer: Int
   private final var eof: Boolean

   public final var index: Int
      private set

   public final var documentIndex: Int
      private set

   public final var line: Int
      private set

   public final var column: Int
      private set

   public constructor(loadSettings: LoadSettings, stream: String) : this(loadSettings, new Buffer().write(ByteString.Companion.encodeUtf8(stream)))
   public fun getMark(): Mark? {
      return if (!this.useMarks) null else new Mark(this.name, this.index, this.line, this.column, ArraysKt.asList(this.codePointsWindow), this.pointer);
   }

   @JvmOverloads
   public fun forward(length: Int = 1) {
      for (int var2 = 0; var2 < length; var2++) {
         if (ensureEnoughData$default(this, 0, 1, null)) {
            val c: Int = this.codePointsWindow[this.pointer++];
            this.moveIndices(1);
            if (!CharConstants.LINEBR.has(c) && (c != 13 || !ensureEnoughData$default(this, 0, 1, null) || this.codePointsWindow[this.pointer] == 10)) {
               if (c != 65279) {
                  val var8: Int = this.column++;
               }
            } else {
               val var7: Int = this.line++;
               this.column = 0;
            }
         }
      }
   }

   public fun peek(): Int {
      return if (ensureEnoughData$default(this, 0, 1, null)) this.codePointsWindow[this.pointer] else 0;
   }

   public fun peek(index: Int): Int {
      return if (this.ensureEnoughData(index)) this.codePointsWindow[this.pointer + index] else 0;
   }

   public fun prefix(length: Int): String {
      if (length == 0) {
         return "";
      } else {
         val stringLength: Int = if (this.ensureEnoughData(length)) length else RangesKt.coerceAtMost(length, this.dataLength - this.pointer);
         val var3: StringBuilder = new StringBuilder(stringLength);
         val `$this$prefix_u24lambda_u242`: StringBuilder = var3;
         var i: Int = this.pointer;

         for (int var7 = this.pointer + stringLength; i < var7; i++) {
            `$this$prefix_u24lambda_u242`.appendCodePoint(this.codePointsWindow[i]);
         }

         return var3.toString();
      }
   }

   public fun prefixForward(length: Int): String {
      val prefix: java.lang.String = this.prefix(length);
      this.pointer += length;
      this.moveIndices(length);
      this.column += length;
      return prefix;
   }

   private fun ensureEnoughData(size: Int = 0): Boolean {
      if (!this.eof && this.pointer + size >= this.dataLength) {
         this.update();
      }

      return this.pointer + size < this.dataLength;
   }

   private fun update() {
      try {
         val read: BufferedSource = this.stream;
         val cpIndex: Long = this.bufferReadSize;
         val `buffer$iv`: BufferedSource = this.stream;
         val `originalSize$iv$iv`: Long = if (this.stream.request(this.bufferReadSize)) cpIndex else read.getBuffer().size();
         var var10000: Long;
         if (`originalSize$iv$iv` == 0L) {
            var10000 = 0L;
         } else {
            val `byte$iv$iv`: Byte = read.getBuffer().getByte(`originalSize$iv$iv` - 1L);
            if ((`byte$iv$iv` and 192) != 128) {
               var10000 = `originalSize$iv$iv` - (if (`byte$iv$iv` < 0) 1 else 0);
            } else if (!read.request(`originalSize$iv$iv` + 1L)) {
               var10000 = `originalSize$iv$iv`;
            } else if ((read.getBuffer().getByte(`originalSize$iv$iv`) and 192) != 128) {
               var10000 = `originalSize$iv$iv`;
            } else {
               val var34: Long = `originalSize$iv$iv`;
               val var32: Byte = 3;
               var `asInt$iv$iv$iv`: Int = 0;

               while (true) {
                  if (`asInt$iv$iv$iv` >= var32) {
                     var10000 = `originalSize$iv$iv`;
                     break;
                  }

                  if (--var34 == 0L) {
                     var10000 = `originalSize$iv$iv`;
                     break;
                  }

                  val `byte$iv$ivx`: Byte = `buffer$iv`.getBuffer().getByte(var34 - 1L);
                  if ((`byte$iv$ivx` and 192) != 128) {
                     var10000 = if (`byte$iv$ivx` >= 0)
                        `originalSize$iv$iv`
                        else
                        (
                           if ((
                                    if ((`byte$iv$ivx` and 224) == 192)
                                       1
                                       else
                                       (if ((`byte$iv$ivx` and 240) == 224) 2 else (if ((`byte$iv$ivx` and 248) == 240) 3 else 0))
                                 )
                                 < `asInt$iv$iv$iv` + 1)
                              `originalSize$iv$iv`
                              else
                              var34 - 1L
                        );
                     break;
                  }

                  `asInt$iv$iv$iv`++;
               }
            }
         }

         val var30: java.lang.String = read.readUtf8(var10000);
         val var28: Int = var30.length();
         if (var28 <= 0) {
            this.eof = true;
         } else {
            this.dataLength = this.transcodeAndValidateToWindow(var30, var28, this.prepareWindowFor(var28));
            this.pointer = 0;
         }
      } catch (var27: IOException) {
         throw new YamlEngineException(var27);
      }
   }

   private fun prepareWindowFor(read: Int): Int {
      val cpIndex: Int = this.dataLength - this.pointer;
      this.codePointsWindow = StreamReader.Companion.access$copyOfRangeSafe(Companion, this.codePointsWindow, this.pointer, this.dataLength + read);
      return cpIndex;
   }

   private fun transcodeAndValidateToWindow(buffer: String, read: Int, cpIndexStart: Int): Int {
      var cpIndex: Int = cpIndexStart;

      for (int i = 0; i < read; cpIndex++) {
         val codePoint: Int = CharSequenceExtensionsKt.codePointAt(buffer, i);
         this.codePointsWindow[cpIndex] = codePoint;
         if (!Companion.isPrintable(codePoint)) {
            throw new ReaderException(this.name, this.index + cpIndex, codePoint, "special characters are not allowed");
         }

         i += Character.INSTANCE.charCount$snakeyaml_engine_kmp(codePoint);
      }

      return cpIndex;
   }

   private fun moveIndices(length: Int) {
      this.index += length;
      this.documentIndex += length;
   }

   public fun resetDocumentIndex() {
      this.documentIndex = 0;
   }

   @JvmOverloads
   fun forward() {
      forward$default(this, 0, 1, null);
   }

   @SourceDebugExtension(["SMAP\nStreamReader.kt\nKotlin\n*S Kotlin\n*F\n+ 1 StreamReader.kt\nit/krzeminski/snakeyaml/engine/kmp/scanner/StreamReader$Companion\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,313:1\n1#2:314\n*E\n"])
   public companion object {
      public fun isPrintable(data: String): Boolean {
         val length: Int = data.length();
         var offset: Int = 0;

         while (offset < length) {
            val codePoint: Int = CharSequenceExtensionsKt.codePointAt(data, offset);
            if (!this.isPrintable(codePoint)) {
               return false;
            }

            offset += Character.INSTANCE.charCount$snakeyaml_engine_kmp(codePoint);
         }

         return true;
      }

      public fun isPrintable(c: Int): Boolean {
         return 32 <= c && c < 127 || c == 9 || c == 10 || c == 13 || c == 133 || 160 <= c && c < 55296 || 57344 <= c && c < 65534 || 65536 <= c && c < 1114112;
      }

      private fun IntArray.copyOfRangeSafe(fromIndex: Int, toIndex: Int): IntArray {
         var var4: Int = 0;
         val var5: Int = toIndex - fromIndex;

         val var6: IntArray;
         for (var6 = new int[toIndex - fromIndex]; var4 < var5; var4++) {
            var var10000: IntArray = var6;
            var var10001: Int = var4;
            val var9: Int = fromIndex + var4;
            val var10002: Int;
            if (0 <= fromIndex + var4 && fromIndex + var4 < `$this$copyOfRangeSafe`.length) {
               var10002 = `$this$copyOfRangeSafe`[var9];
            } else {
               var10000 = var6;
               var10001 = var4;
               var10002 = 0;
            }

            var10000[var10001] = var10002;
         }

         return var6;
      }
   }
}

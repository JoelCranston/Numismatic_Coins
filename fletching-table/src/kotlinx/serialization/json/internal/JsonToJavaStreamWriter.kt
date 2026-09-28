package kotlinx.serialization.json.internal

import java.io.OutputStream
import java.util.Arrays
import kotlin.jvm.internal.SourceDebugExtension

@SourceDebugExtension(["SMAP\nJvmJsonStreams.kt\nKotlin\n*S Kotlin\n*F\n+ 1 JvmJsonStreams.kt\nkotlinx/serialization/json/internal/JsonToJavaStreamWriter\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,268:1\n130#1:269\n117#1:271\n130#1:272\n118#1,3:273\n125#1,2:276\n130#1:278\n125#1,2:279\n117#1:281\n130#1:282\n118#1,3:283\n125#1,2:286\n125#1,2:288\n117#1:290\n130#1:291\n118#1,3:292\n125#1,2:295\n125#1,2:297\n125#1,2:299\n117#1:301\n130#1:302\n118#1,3:303\n125#1,2:306\n117#1:308\n130#1:309\n118#1,3:310\n125#1,2:313\n125#1,2:315\n125#1,2:317\n125#1,2:319\n117#1:321\n130#1:322\n118#1,3:323\n125#1,2:326\n117#1:328\n130#1:329\n118#1,3:330\n125#1,2:333\n125#1,2:335\n117#1:337\n130#1:338\n118#1,3:339\n125#1,2:342\n117#1:344\n130#1:345\n118#1,3:346\n125#1,2:349\n125#1,2:351\n125#1,2:353\n117#1:355\n130#1:356\n118#1,3:357\n125#1,2:360\n125#1,2:362\n125#1,2:364\n125#1,2:366\n1#2:270\n*S KotlinDebug\n*F\n+ 1 JvmJsonStreams.kt\nkotlinx/serialization/json/internal/JsonToJavaStreamWriter\n*L\n117#1:269\n148#1:271\n148#1:272\n148#1:273,3\n149#1:276,2\n151#1:278\n158#1:279,2\n165#1:281\n165#1:282\n165#1:283,3\n166#1:286,2\n167#1:288,2\n173#1:290\n173#1:291\n173#1:292,3\n174#1:295,2\n175#1:297,2\n176#1:299,2\n186#1:301\n186#1:302\n186#1:303,3\n187#1:306,2\n196#1:308\n196#1:309\n196#1:310,3\n197#1:313,2\n198#1:315,2\n199#1:317,2\n200#1:319,2\n215#1:321\n215#1:322\n215#1:323,3\n216#1:326,2\n221#1:328\n221#1:329\n221#1:330,3\n222#1:333,2\n223#1:335,2\n228#1:337\n228#1:338\n228#1:339,3\n229#1:342,2\n234#1:344\n234#1:345\n234#1:346,3\n235#1:349,2\n236#1:351,2\n237#1:353,2\n242#1:355\n242#1:356\n242#1:357,3\n243#1:360,2\n244#1:362,2\n245#1:364,2\n246#1:366,2\n*E\n"])
internal class JsonToJavaStreamWriter(stream: OutputStream) : InternalJsonWriter {
   private final val stream: OutputStream
   private final val buffer: ByteArray
   private final var charArray: CharArray
   private final var indexInBuffer: Int

   init {
      this.stream = stream;
      this.buffer = ByteArrayPool.INSTANCE.take();
      this.charArray = CharArrayPool.INSTANCE.take();
   }

   public override fun writeLong(value: Long) {
      this.write(java.lang.String.valueOf(value));
   }

   public override fun writeChar(char: Char) {
      this.writeUtf8CodePoint(var1);
   }

   public override fun write(text: String) {
      val length: Int = text.length();
      this.ensureTotalCapacity(0, length);
      text.getChars(0, length, this.charArray, 0);
      this.writeUtf8(this.charArray, length);
   }

   public override fun writeQuoted(text: String) {
      this.ensureTotalCapacity(0, text.length() + 2);
      val arr: CharArray = this.charArray;
      this.charArray[0] = '"';
      val length: Int = text.length();
      text.getChars(0, length, arr, 1);
      var i: Int = 1;

      for (int var7 = 1 + length; i < var7; i++) {
         if (arr[i] < StringOpsKt.getESCAPE_MARKERS().length && StringOpsKt.getESCAPE_MARKERS()[arr[i]] != 0) {
            this.appendStringSlowPath(i, text);
            return;
         }
      }

      arr[length + 1] = '"';
      this.writeUtf8(arr, length + 2);
      this.flush();
   }

   private fun appendStringSlowPath(currentSize: Int, string: String) {
      var var13: Int = currentSize;
      var i: Int = currentSize - 1;

      for (int var5 = string.length(); i < var5; i++) {
         var13 = this.ensureTotalCapacity(var13, 2);
         val ch: Int = string.charAt(i);
         if (ch < StringOpsKt.getESCAPE_MARKERS().length) {
            val marker: Byte = StringOpsKt.getESCAPE_MARKERS()[ch];
            if (marker == 0) {
               this.charArray[var13++] = (char)ch;
            } else if (marker == 1) {
               val var10000: java.lang.String = StringOpsKt.getESCAPE_STRINGS()[ch];
               var13 = this.ensureTotalCapacity(var13, var10000.length());
               val var10: CharArray = this.charArray;
               var10000.getChars(0, var10000.length(), var10, var13);
               var13 = var13 + var10000.length();
            } else {
               this.charArray[var13] = '\\';
               this.charArray[var13 + 1] = (char)marker;
               var13 += 2;
            }
         } else {
            this.charArray[var13++] = (char)ch;
         }
      }

      this.ensureTotalCapacity(var13, 1);
      this.charArray[var13++] = '"';
      this.writeUtf8(this.charArray, var13);
      this.flush();
   }

   private fun ensureTotalCapacity(oldSize: Int, additional: Int): Int {
      val newSize: Int = oldSize + additional;
      if (this.charArray.length <= oldSize + additional) {
         val var10001: CharArray = Arrays.copyOf(this.charArray, RangesKt.coerceAtLeast(newSize, oldSize * 2));
         this.charArray = var10001;
      }

      return oldSize;
   }

   public override fun release() {
      this.flush();
      CharArrayPool.INSTANCE.release(this.charArray);
      ByteArrayPool.INSTANCE.release(this.buffer);
   }

   private fun flush() {
      this.stream.write(this.buffer, 0, this.indexInBuffer);
      this.indexInBuffer = 0;
   }

   private inline fun ensure(bytesCount: Int) {
      if (this.buffer.length - this.indexInBuffer < bytesCount) {
         this.flush();
      }
   }

   private inline fun write(byte: Int) {
      this.buffer[this.indexInBuffer++] = (byte)var1;
   }

   private inline fun rest(): Int {
      return this.buffer.length - this.indexInBuffer;
   }

   private fun writeUtf8(string: CharArray, count: Int) {
      if (count < 0) {
         throw new IllegalArgumentException("count < 0".toString());
      } else if (count > string.length) {
         throw new IllegalArgumentException(("count > string.length: $count > ${string.length}").toString());
      } else {
         var i: Int = 0;

         while (i < count) {
            val c: Int = string[i];
            if (string[i] < 128) {
               if (this.buffer.length - this.indexInBuffer < 1) {
                  this.flush();
               }

               this.buffer[this.indexInBuffer++] = (byte)c;

               for (int runLimit = Math.min(count, ++i + (this.buffer.length - this.indexInBuffer)); i < runLimit; i++) {
                  val var12: Char = string[i];
                  if (string[i] >= 128) {
                     break;
                  }

                  this.buffer[this.indexInBuffer++] = (byte)var12;
               }
            } else if (c < 2048) {
               if (this.buffer.length - this.indexInBuffer < 2) {
                  this.flush();
               }

               this.buffer[this.indexInBuffer++] = (byte)(c shr 6 or 192);
               this.buffer[this.indexInBuffer++] = (byte)(c and 63 or 128);
               i++;
            } else if (c >= 55296 && c <= 57343) {
               val low: Int = if (i + 1 < count) string[i + 1] else 0;
               if (c <= 56319 && '\udc00' <= (if (i + 1 < count) string[i + 1] else 0) && (if (i + 1 < count) string[i + 1] else 0) < '\ue000') {
                  val var21: Int = 65536 + ((c and 1023) shl 10 or low and 1023);
                  if (this.buffer.length - this.indexInBuffer < 4) {
                     this.flush();
                  }

                  this.buffer[this.indexInBuffer++] = (byte)(var21 shr 18 or 240);
                  this.buffer[this.indexInBuffer++] = (byte)(var21 shr 12 and 63 or 128);
                  this.buffer[this.indexInBuffer++] = (byte)(var21 shr 6 and 63 or 128);
                  this.buffer[this.indexInBuffer++] = (byte)(var21 and 63 or 128);
                  i += 2;
               } else {
                  if (this.buffer.length - this.indexInBuffer < 1) {
                     this.flush();
                  }

                  this.buffer[this.indexInBuffer++] = 63;
                  i++;
               }
            } else {
               if (this.buffer.length - this.indexInBuffer < 3) {
                  this.flush();
               }

               this.buffer[this.indexInBuffer++] = (byte)(c shr 12 or 224);
               this.buffer[this.indexInBuffer++] = (byte)(c shr 6 and 63 or 128);
               this.buffer[this.indexInBuffer++] = (byte)(c and 63 or 128);
               i++;
            }
         }
      }
   }

   private fun writeUtf8CodePoint(codePoint: Int) {
      if (codePoint < 128) {
         if (this.buffer.length - this.indexInBuffer < 1) {
            this.flush();
         }

         this.buffer[this.indexInBuffer++] = (byte)codePoint;
      } else if (codePoint < 2048) {
         if (this.buffer.length - this.indexInBuffer < 2) {
            this.flush();
         }

         this.buffer[this.indexInBuffer++] = (byte)(codePoint shr 6 or 192);
         this.buffer[this.indexInBuffer++] = (byte)(codePoint and 63 or 128);
      } else if (55296 <= codePoint && codePoint < 57344) {
         if (this.buffer.length - this.indexInBuffer < 1) {
            this.flush();
         }

         this.buffer[this.indexInBuffer++] = 63;
      } else if (codePoint < 65536) {
         if (this.buffer.length - this.indexInBuffer < 3) {
            this.flush();
         }

         this.buffer[this.indexInBuffer++] = (byte)(codePoint shr 12 or 224);
         this.buffer[this.indexInBuffer++] = (byte)(codePoint shr 6 and 63 or 128);
         this.buffer[this.indexInBuffer++] = (byte)(codePoint and 63 or 128);
      } else {
         if (codePoint > 1114111) {
            throw new JsonEncodingException("Unexpected code point: $codePoint");
         }

         if (this.buffer.length - this.indexInBuffer < 4) {
            this.flush();
         }

         this.buffer[this.indexInBuffer++] = (byte)(codePoint shr 18 or 240);
         this.buffer[this.indexInBuffer++] = (byte)(codePoint shr 12 and 63 or 128);
         this.buffer[this.indexInBuffer++] = (byte)(codePoint shr 6 and 63 or 128);
         this.buffer[this.indexInBuffer++] = (byte)(codePoint and 63 or 128);
      }
   }
}

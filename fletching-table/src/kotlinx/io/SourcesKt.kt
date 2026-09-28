@file:SourceDebugExtension(["SMAP\nSources.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Sources.kt\nkotlinx/io/SourcesKt\n+ 2 Buffer.kt\nkotlinx/io/BufferKt\n+ 3 -Util.kt\nkotlinx/io/_UtilKt\n+ 4 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,465:1\n659#2,25:466\n659#2,25:491\n52#3:516\n53#3:518\n38#3:520\n1#4:517\n1#4:519\n*S KotlinDebug\n*F\n+ 1 Sources.kt\nkotlinx/io/SourcesKt\n*L\n94#1:466,25\n156#1:491,25\n251#1:516\n251#1:518\n291#1:520\n251#1:517\n*E\n"])

package kotlinx.io

import java.io.EOFException
import kotlin.jvm.internal.SourceDebugExtension

internal const val OVERFLOW_ZONE: Long = -922337203685477580L
internal const val OVERFLOW_DIGIT_START: Long = -7L

public fun Source.readShortLe(): Short {
   return _UtilsJvmKt.reverseBytes(`$this$readShortLe`.readShort());
}

public fun Source.readIntLe(): Int {
   return _UtilsJvmKt.reverseBytes(`$this$readIntLe`.readInt());
}

public fun Source.readLongLe(): Long {
   return _UtilsJvmKt.reverseBytes(`$this$readLongLe`.readLong());
}

public fun Source.readDecimalLong(): Long {
   `$this$readDecimalLong`.require(1L);
   var negative: Boolean = false;
   var value: Long = 0L;
   var var54: Long = -7L;
   val b: Byte = `$this$readDecimalLong`.getBuffer().get(0L);
   if (b == 45) {
      negative = true;
      var54 = -7L + -1L;
      `$this$readDecimalLong`.require(2L);
      val finished: Byte = `$this$readDecimalLong`.getBuffer().get(1L);
      if (48 > finished || finished >= 58) {
         throw new NumberFormatException("Expected a digit but was 0x${_UtilKt.toHexString(`$this$readDecimalLong`.getBuffer().get(1L))}");
      }
   } else {
      if (48 > b || b >= 58) {
         throw new NumberFormatException("Expected a digit or '-' but was 0x${_UtilKt.toHexString(b)}");
      }

      value = 48 - b;
   }

   var var55: Long = 1L;

   while ($this$readDecimalLong.request(var55 + 1L)) {
      var var10000: Boolean;
      val `$this$seek$iv`: Buffer = `$this$readDecimalLong`.getBuffer();
      val `fromIndex$iv`: Long = var55;
      label198:
      if (`$this$seek$iv`.getHead() == null) {
         val seg: Segment = null;
         var var12: Int = (int)(var55 - -1L);

         for (int size = null.getSize(); currIdx < size; var55++) {
            val sizex: Byte = seg.getUnchecked$kotlinx_io_core(var12);
            if (48 > sizex || sizex >= 58) {
               var10000 = true;
               break label198;
            }

            val bx: Int = 48 - sizex;
            if (value < -922337203685477580L || value == -922337203685477580L && 48 - sizex < var54) {
               val digit: Buffer = new Buffer();
               SinksKt.writeDecimalLong(digit, value);
               digit.writeByte(sizex);
               if (!negative) {
                  digit.readByte();
               }

               throw new NumberFormatException("Number too large: ${Utf8Kt.readString(digit)}");
            }

            value = value * 10L + bx;
            var12++;
         }

         var10000 = false;
      } else {
         label187:
         if (`$this$seek$iv`.getSize() - var55 < var55) {
            var `s$iv`: Segment = `$this$seek$iv`.getTail();

            var `offset$iv`: Long;
            for (offset$iv = $this$seek$iv.getSize(); s$iv != null && offset$iv > fromIndex$iv; s$iv = s$iv.getPrev()) {
               `offset$iv` -= `s$iv`.getLimit() - `s$iv`.getPos();
               if (`offset$iv` <= `fromIndex$iv`) {
                  break;
               }
            }

            val var38: Segment = `s$iv`;
            var var40: Int = (int)(var55 - `offset$iv`);

            for (int size = s$iv.getSize(); currIdx < size; var55++) {
               val bxx: Byte = var38.getUnchecked$kotlinx_io_core(var40);
               if (48 > bxx || bxx >= 58) {
                  var10000 = true;
                  break label187;
               }

               val var46: Int = 48 - bxx;
               if (value < -922337203685477580L || value == -922337203685477580L && 48 - bxx < var54) {
                  val var48: Buffer = new Buffer();
                  SinksKt.writeDecimalLong(var48, value);
                  var48.writeByte(bxx);
                  if (!negative) {
                     var48.readByte();
                  }

                  throw new NumberFormatException("Number too large: ${Utf8Kt.readString(var48)}");
               }

               value = value * 10L + var46;
               var40++;
            }

            var10000 = false;
         } else {
            label223: {
               var var52: Segment = `$this$seek$iv`.getHead();
               var var53: Long = 0L;

               while (s$iv != null) {
                  val `nextOffset$iv`: Long = var53 + (var52.getLimit() - var52.getPos());
                  if (`nextOffset$iv` > `fromIndex$iv`) {
                     break;
                  }

                  var52 = var52.getNext();
                  var53 = `nextOffset$iv`;
               }

               val seg: Segment = var52;
               var var43: Int = (int)(var55 - var53);

               for (int size = s$iv.getSize(); currIdx < size; var55++) {
                  val bxxx: Byte = seg.getUnchecked$kotlinx_io_core(var43);
                  if (48 > bxxx || bxxx >= 58) {
                     var10000 = true;
                     break label223;
                  }

                  val var49: Int = 48 - bxxx;
                  if (value < -922337203685477580L || value == -922337203685477580L && 48 - bxxx < var54) {
                     val var51: Buffer = new Buffer();
                     SinksKt.writeDecimalLong(var51, value);
                     var51.writeByte(bxxx);
                     if (!negative) {
                        var51.readByte();
                     }

                     throw new NumberFormatException("Number too large: ${Utf8Kt.readString(var51)}");
                  }

                  value = value * 10L + var49;
                  var43++;
               }

               var10000 = false;
            }
         }
      }

      if (var10000) {
         break;
      }
   }

   `$this$readDecimalLong`.skip(var55);
   return if (negative) value else -value;
}

public fun Source.readHexadecimalUnsignedLong(): Long {
   `$this$readHexadecimalUnsignedLong`.require(1L);
   val b: Byte = `$this$readHexadecimalUnsignedLong`.getBuffer().get(0L);
   var var10000: Int;
   if (48 <= b && b < 58) {
      var10000 = b - 48;
   } else if (97 <= b && b < 103) {
      var10000 = b - 97 + 10;
   } else {
      if (65 > b || b >= 71) {
         throw new NumberFormatException("Expected leading [0-9a-fA-F] character but was 0x${_UtilKt.toHexString(b)}");
      }

      var10000 = b - 65 + 10;
   }

   var var56: Long = var10000;
   var var57: Long = 1L;

   while ($this$readHexadecimalUnsignedLong.request(var57 + 1L)) {
      val `$this$seek$iv`: Buffer = `$this$readHexadecimalUnsignedLong`.getBuffer();
      val `fromIndex$iv`: Long = var57;
      label235:
      if (`$this$seek$iv`.getHead() == null) {
         val seg: Segment = null;
         var startIndex: Int = (int)(var57 - -1L);

         for (int var13 = null.getSize(); localOffset < var13; localOffset++) {
            val startIndexx: Byte = seg.getUnchecked$kotlinx_io_core(startIndex);
            if (48 <= startIndexx && startIndexx < 58) {
               var10000 = startIndexx - 48;
            } else if (97 <= startIndexx && startIndexx < 103) {
               var10000 = startIndexx - 97 + 10;
            } else {
               if (65 > startIndexx || startIndexx >= 71) {
                  var59 = true;
                  break label235;
               }

               var10000 = startIndexx - 65 + 10;
            }

            if ((var56 and -1152921504606846976L) != 0L) {
               val `$this$readHexadecimalUnsignedLong_u24lambda_u243_u24lambda_u242`: Buffer = new Buffer();
               SinksKt.writeHexadecimalUnsignedLong(`$this$readHexadecimalUnsignedLong_u24lambda_u243_u24lambda_u242`, var56);
               `$this$readHexadecimalUnsignedLong_u24lambda_u243_u24lambda_u242`.writeByte(startIndexx);
               throw new NumberFormatException("Number too large: ${Utf8Kt.readString(`$this$readHexadecimalUnsignedLong_u24lambda_u243_u24lambda_u242`)}");
            }

            var56 = (var56 shl 4) + var10000;
            var57++;
         }

         var59 = false;
      } else {
         label219:
         if (`$this$seek$iv`.getSize() - var57 < var57) {
            var `s$iv`: Segment = `$this$seek$iv`.getTail();

            var `offset$iv`: Long;
            for (offset$iv = $this$seek$iv.getSize(); s$iv != null && offset$iv > fromIndex$iv; s$iv = s$iv.getPrev()) {
               `offset$iv` -= `s$iv`.getLimit() - `s$iv`.getPos();
               if (`offset$iv` <= `fromIndex$iv`) {
                  break;
               }
            }

            val seg: Segment = `s$iv`;
            var var44: Int = (int)(var57 - `offset$iv`);

            for (int var46 = s$iv.getSize(); localOffset < var46; localOffset++) {
               val bxx: Byte = seg.getUnchecked$kotlinx_io_core(var44);
               if (48 <= bxx && bxx < 58) {
                  var10000 = bxx - 48;
               } else if (97 <= bxx && bxx < 103) {
                  var10000 = bxx - 97 + 10;
               } else {
                  if (65 > bxx || bxx >= 71) {
                     var59 = true;
                     break label219;
                  }

                  var10000 = bxx - 65 + 10;
               }

               if ((var56 and -1152921504606846976L) != 0L) {
                  val var50: Buffer = new Buffer();
                  SinksKt.writeHexadecimalUnsignedLong(var50, var56);
                  var50.writeByte(bxx);
                  throw new NumberFormatException("Number too large: ${Utf8Kt.readString(var50)}");
               }

               var56 = (var56 shl 4) + var10000;
               var57++;
            }

            var59 = false;
         } else {
            label267: {
               var var53: Segment = `$this$seek$iv`.getHead();
               var var54: Long = 0L;

               while (s$iv != null) {
                  val var40: Long = var54 + (var53.getLimit() - var53.getPos());
                  if (var40 > `fromIndex$iv`) {
                     break;
                  }

                  var53 = var53.getNext();
                  var54 = var40;
               }

               val var41: Segment = var53;
               var var48: Int = (int)(var57 - var54);

               for (int var49 = s$iv.getSize(); localOffset < var49; localOffset++) {
                  val bxxx: Byte = var41.getUnchecked$kotlinx_io_core(var48);
                  if (48 <= bxxx && bxxx < 58) {
                     var10000 = bxxx - 48;
                  } else if (97 <= bxxx && bxxx < 103) {
                     var10000 = bxxx - 97 + 10;
                  } else {
                     if (65 > bxxx || bxxx >= 71) {
                        var59 = true;
                        break label267;
                     }

                     var10000 = bxxx - 65 + 10;
                  }

                  if ((var56 and -1152921504606846976L) != 0L) {
                     val var55: Buffer = new Buffer();
                     SinksKt.writeHexadecimalUnsignedLong(var55, var56);
                     var55.writeByte(bxxx);
                     throw new NumberFormatException("Number too large: ${Utf8Kt.readString(var55)}");
                  }

                  var56 = (var56 shl 4) + var10000;
                  var57++;
               }

               var59 = false;
            }
         }
      }

      if (var59) {
         break;
      }
   }

   `$this$readHexadecimalUnsignedLong`.skip(var57);
   return var56;
}

public fun Source.indexOf(byte: Byte, startIndex: Long = 0L, endIndex: Long = java.lang.Long.MAX_VALUE): Long {
   if (0L > startIndex || startIndex > endIndex) {
      throw new IllegalArgumentException(
         (if (endIndex < 0L)
               "startIndex ($startIndex) and endIndex ($endIndex) should be non negative"
               else
               "startIndex ($startIndex) is not within the range [0..endIndex($endIndex))")
            .toString()
      );
   } else if (startIndex == endIndex) {
      return -1L;
   } else {
      for (long offset = startIndex; offset < endIndex && $this$indexOf.request(offset + 1L); offset = $this$indexOf.getBuffer().getSize()) {
         val idx: Long = BuffersKt.indexOf(`$this$indexOf`.getBuffer(), var1, offset, Math.min(endIndex, `$this$indexOf`.getBuffer().getSize()));
         if (idx != -1L) {
            return idx;
         }
      }

      return -1L;
   }
}

@JvmSynthetic
fun `indexOf$default`(var0: Source, var1: Byte, var2: Long, var4: Long, var6: Int, var7: Any): Long {
   if ((var6 and 2) != 0) {
      var2 = 0L;
   }

   if ((var6 and 4) != 0) {
      var4 = java.lang.Long.MAX_VALUE;
   }

   return indexOf(var0, var1, var2, var4);
}

public fun Source.readByteArray(): ByteArray {
   return readByteArrayImpl(`$this$readByteArray`, -1);
}

public fun Source.readByteArray(byteCount: Int): ByteArray {
   val `byteCount$iv`: Long = byteCount;
   if (byteCount < 0L) {
      throw new IllegalArgumentException(("byteCount ($`byteCount$iv`) < 0").toString());
   } else {
      return readByteArrayImpl(`$this$readByteArray`, byteCount);
   }
}

private fun Source.readByteArrayImpl(size: Int): ByteArray {
   var arraySize: Int = size;
   if (size == -1) {
      var array: Long = 2147483647L;

      while ($this$readByteArrayImpl.getBuffer().getSize() < 2147483647L && $this$readByteArrayImpl.request(fetchSize)) {
         array *= 2;
      }

      if (`$this$readByteArrayImpl`.getBuffer().getSize() >= 2147483647L) {
         throw new IllegalStateException(("Can't create an array of size ${`$this$readByteArrayImpl`.getBuffer().getSize()}").toString());
      }

      arraySize = (int)`$this$readByteArrayImpl`.getBuffer().getSize();
   } else {
      `$this$readByteArrayImpl`.require((long)size);
   }

   val var6: ByteArray = new byte[arraySize];
   readTo$default(`$this$readByteArrayImpl`.getBuffer(), var6, 0, 0, 6, null);
   return var6;
}

public fun Source.readTo(sink: ByteArray, startIndex: Int = 0, endIndex: Int = sink.length) {
   _UtilKt.checkBounds((long)sink.length, (long)startIndex, (long)endIndex);
   var var6: Int = startIndex;

   while (offset < endIndex) {
      val var7: Int = `$this$readTo`.readAtMostTo(sink, var6, endIndex);
      if (var7 == -1) {
         throw new EOFException("Source exhausted before reading ${endIndex - startIndex} bytes. Only $var7 bytes were read.");
      }

      var6 += var7;
   }
}

@JvmSynthetic
fun `readTo$default`(var0: Source, var1: ByteArray, var2: Int, var3: Int, var4: Int, var5: Any) {
   if ((var4 and 2) != 0) {
      var2 = 0;
   }

   if ((var4 and 4) != 0) {
      var3 = var1.length;
   }

   readTo(var0, var1, var2, var3);
}

public fun Source.readUByte(): UByte {
   return UByte.constructor-impl(`$this$readUByte`.readByte());
}

public fun Source.readUShort(): UShort {
   return UShort.constructor-impl(`$this$readUShort`.readShort());
}

public fun Source.readUInt(): UInt {
   return UInt.constructor-impl(`$this$readUInt`.readInt());
}

public fun Source.readULong(): ULong {
   return ULong.constructor-impl(`$this$readULong`.readLong());
}

public fun Source.readUShortLe(): UShort {
   return UShort.constructor-impl(readShortLe(`$this$readUShortLe`));
}

public fun Source.readUIntLe(): UInt {
   return UInt.constructor-impl(readIntLe(`$this$readUIntLe`));
}

public fun Source.readULongLe(): ULong {
   return ULong.constructor-impl(readLongLe(`$this$readULongLe`));
}

public fun Source.readFloat(): Float {
   return java.lang.Float.intBitsToFloat(`$this$readFloat`.readInt());
}

public fun Source.readDouble(): Double {
   return java.lang.Double.longBitsToDouble(`$this$readDouble`.readLong());
}

public fun Source.readFloatLe(): Float {
   return java.lang.Float.intBitsToFloat(readIntLe(`$this$readFloatLe`));
}

public fun Source.readDoubleLe(): Double {
   return java.lang.Double.longBitsToDouble(readLongLe(`$this$readDoubleLe`));
}

public fun Source.startsWith(byte: Byte): Boolean {
   return `$this$startsWith`.request(1L) && `$this$startsWith`.getBuffer().get(0L) == var1;
}

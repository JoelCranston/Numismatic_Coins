@file:SourceDebugExtension(["SMAP\nSinks.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Sinks.kt\nkotlinx/io/SinksKt\n+ 2 UnsafeBufferOperations.kt\nkotlinx/io/unsafe/UnsafeBufferOperations\n+ 3 -Util.kt\nkotlinx/io/_UtilKt\n*L\n1#1,377:1\n374#1:378\n375#1,2:402\n374#1:408\n375#1,2:432\n262#2,23:379\n262#2,23:409\n179#3,4:404\n*S KotlinDebug\n*F\n+ 1 Sinks.kt\nkotlinx/io/SinksKt\n*L\n123#1:378\n123#1:402,2\n161#1:408\n161#1:432,2\n124#1:379,23\n162#1:409,23\n159#1:404,4\n*E\n"])

package kotlinx.io

import kotlin.contracts.InvocationKind
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.io.unsafe.SegmentWriteContext
import kotlinx.io.unsafe.UnsafeBufferOperations
import kotlinx.io.unsafe.UnsafeBufferOperationsKt

private final val HEX_DIGIT_BYTES: ByteArray

public fun Sink.writeShortLe(short: Short) {
   `$this$writeShortLe`.writeShort(_UtilsJvmKt.reverseBytes(var1));
}

public fun Sink.writeIntLe(int: Int) {
   `$this$writeIntLe`.writeInt(_UtilsJvmKt.reverseBytes(var1));
}

public fun Sink.writeLongLe(long: Long) {
   `$this$writeLongLe`.writeLong(_UtilsJvmKt.reverseBytes(var1));
}

public fun Sink.writeDecimalLong(long: Long) {
   var var25: Long = var1;
   if (var1 == 0L) {
      `$this$writeDecimalLong`.writeByte((byte)48);
   } else {
      var negative: Boolean = false;
      if (var1 < 0L) {
         var25 = -var1;
         if (-var1 < 0L) {
            Utf8Kt.writeString$default(`$this$writeDecimalLong`, "-9223372036854775808", 0, 0, 6, null);
            return;
         }

         negative = true;
      }

      var var23: Int = if (var25 < 100000000L)
         (
            if (var25 < 10000L)
               (if (var25 < 100L) (if (var25 < 10L) 1 else 2) else (if (var25 < 1000L) 3 else 4))
               else
               (if (var25 < 1000000L) (if (var25 < 100000L) 5 else 6) else (if (var25 < 10000000L) 7 else 8))
         )
         else
         (
            if (var25 < 1000000000000L)
               (if (var25 < 10000000000L) (if (var25 < 1000000000L) 9 else 10) else (if (var25 < 100000000000L) 11 else 12))
               else
               (
                  if (var25 < 1000000000000000L)
                     (if (var25 < 10000000000000L) 13 else (if (var25 < 100000000000000L) 14 else 15))
                     else
                     (if (var25 < 100000000000000000L) (if (var25 < 10000000000000000L) 16 else 17) else (if (var25 < 1000000000000000000L) 18 else 19))
               )
         );
      if (negative) {
         var23++;
      }

      val buffer: Buffer = `$this$writeDecimalLong`.getBuffer();
      val `this_$iv`: UnsafeBufferOperations = UnsafeBufferOperations.INSTANCE;
      val `tail$iv`: Segment = buffer.writableSegment(var23);
      val var10000: SegmentWriteContext = UnsafeBufferOperationsKt.getSegmentWriteContextImpl();
      val segment: Segment = `tail$iv`;
      val ctx: SegmentWriteContext = var10000;
      var pos: Int = var23 - 1;
      val var17: Int = if (negative) 1 else 0;
      if ((if (negative) 1 else 0) <= pos) {
         while (true) {
            ctx.setUnchecked(segment, pos, HEX_DIGIT_BYTES[(byte)((int)(var25 % (long)10))]);
            var25 /= 10;
            if (pos == var17) {
               break;
            }

            pos--;
         }
      }

      if (negative) {
         ctx.setUnchecked(segment, 0, (byte)45);
      }

      if (var23 == var23) {
         `tail$iv`.setLimit(`tail$iv`.getLimit() + var23);
         buffer.setSizeMut(buffer.getSizeMut() + (long)var23);
      } else {
         if (0 > var23 || var23 > `tail$iv`.getRemainingCapacity()) {
            throw new IllegalStateException(("Invalid number of bytes written: $var23. Should be in 0..${`tail$iv`.getRemainingCapacity()}").toString());
         }

         if (var23 != 0) {
            `tail$iv`.setLimit(`tail$iv`.getLimit() + var23);
            buffer.setSizeMut(buffer.getSizeMut() + (long)var23);
         } else if (SegmentKt.isEmpty(`tail$iv`)) {
            buffer.recycleTail();
         }
      }

      `$this$writeDecimalLong`.hintEmit();
   }
}

public fun Sink.writeHexadecimalUnsignedLong(long: Long) {
   var var22: Long = var1;
   if (var1 == 0L) {
      `$this$writeHexadecimalUnsignedLong`.writeByte((byte)48);
   } else {
      val var10000: Int = if (var1 == 0L) 1 else (64 - java.lang.Long.numberOfLeadingZeros(var1) + 3) / 4;
      val var19: Buffer = `$this$writeHexadecimalUnsignedLong`.getBuffer();
      val `this_$iv`: UnsafeBufferOperations = UnsafeBufferOperations.INSTANCE;
      val `tail$iv`: Segment = var19.writableSegment(var10000);
      val var23: SegmentWriteContext = UnsafeBufferOperationsKt.getSegmentWriteContextImpl();
      val segment: Segment = `tail$iv`;
      val ctx: SegmentWriteContext = var23;

      for (int pos = var10000 - 1; -1 < pos; pos--) {
         ctx.setUnchecked(segment, pos, HEX_DIGIT_BYTES[(int)var22 and 15]);
         var22 >>>= 4;
      }

      if (var10000 == var10000) {
         `tail$iv`.setLimit(`tail$iv`.getLimit() + var10000);
         var19.setSizeMut(var19.getSizeMut() + (long)var10000);
      } else {
         if (0 > var10000 || var10000 > `tail$iv`.getRemainingCapacity()) {
            throw new IllegalStateException(("Invalid number of bytes written: $var10000. Should be in 0..${`tail$iv`.getRemainingCapacity()}").toString());
         }

         if (var10000 != 0) {
            `tail$iv`.setLimit(`tail$iv`.getLimit() + var10000);
            var19.setSizeMut(var19.getSizeMut() + (long)var10000);
         } else if (SegmentKt.isEmpty(`tail$iv`)) {
            var19.recycleTail();
         }
      }

      `$this$writeHexadecimalUnsignedLong`.hintEmit();
   }
}

public fun Sink.writeUByte(byte: UByte) {
   `$this$writeUByte_u2dEK_u2d6454`.writeByte(var1);
}

public fun Sink.writeUShort(short: UShort) {
   `$this$writeUShort_u2di8woANY`.writeShort(var1);
}

public fun Sink.writeUInt(int: UInt) {
   `$this$writeUInt_u2dQn1smSk`.writeInt(var1);
}

public fun Sink.writeULong(long: ULong) {
   `$this$writeULong_u2d2TYgG_w`.writeLong(var1);
}

public fun Sink.writeUShortLe(short: UShort) {
   writeShortLe(`$this$writeUShortLe_u2di8woANY`, var1);
}

public fun Sink.writeUIntLe(int: UInt) {
   writeIntLe(`$this$writeUIntLe_u2dQn1smSk`, var1);
}

public fun Sink.writeULongLe(long: ULong) {
   writeLongLe(`$this$writeULongLe_u2d2TYgG_w`, var1);
}

public fun Sink.writeFloat(float: Float) {
   `$this$writeFloat`.writeInt(java.lang.Float.floatToIntBits(var1));
}

public fun Sink.writeDouble(double: Double) {
   `$this$writeDouble`.writeLong(java.lang.Double.doubleToLongBits(var1));
}

public fun Sink.writeFloatLe(float: Float) {
   writeIntLe(`$this$writeFloatLe`, java.lang.Float.floatToIntBits(var1));
}

public fun Sink.writeDoubleLe(double: Double) {
   writeLongLe(`$this$writeDoubleLe`, java.lang.Double.doubleToLongBits(var1));
}

@DelicateIoApi
public inline fun Sink.writeToInternalBuffer(lambda: (Buffer) -> Unit) {
   contract {
      callsInPlace(lambda, InvocationKind.EXACTLY_ONCE)
   }

   lambda.invoke(`$this$writeToInternalBuffer`.getBuffer());
   `$this$writeToInternalBuffer`.hintEmit();
}

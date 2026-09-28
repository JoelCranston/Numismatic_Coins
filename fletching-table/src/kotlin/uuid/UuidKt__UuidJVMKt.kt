package kotlin.uuid

import java.nio.BufferOverflowException
import java.nio.BufferUnderflowException
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.util.UUID
import kotlin.jvm.internal.SourceDebugExtension

@SourceDebugExtension(["SMAP\nUuidJVM.kt\nKotlin\n*S Kotlin\n*F\n+ 1 UuidJVM.kt\nkotlin/uuid/UuidKt__UuidJVMKt\n*L\n1#1,277:1\n277#1:278\n277#1:279\n277#1:280\n277#1:281\n277#1:282\n277#1:283\n277#1:284\n277#1:285\n*S KotlinDebug\n*F\n+ 1 UuidJVM.kt\nkotlin/uuid/UuidKt__UuidJVMKt\n*L\n139#1:278\n140#1:279\n184#1:280\n185#1:281\n224#1:282\n225#1:283\n271#1:284\n272#1:285\n*E\n"])
internal class UuidKt__UuidJVMKt {
   @ExperimentalUuidApi
   @JvmStatic
   internal fun secureRandomUuid(): Uuid {
      val randomBytes: ByteArray = new byte[16];
      SecureRandomHolder.INSTANCE.getInstance().nextBytes(randomBytes);
      return UuidKt.uuidFromRandomBytes(randomBytes);
   }

   @ExperimentalUuidApi
   @JvmStatic
   internal fun serializedUuid(uuid: Uuid): Any {
      return new UuidSerialized(uuid.getMostSignificantBits(), uuid.getLeastSignificantBits());
   }

   @ExperimentalUuidApi
   @JvmStatic
   internal fun ByteArray.getLongAt(index: Int): Long {
      return UuidKt.getLongAtCommonImpl(`$this$getLongAt`, index);
   }

   @ExperimentalUuidApi
   @JvmStatic
   internal fun Long.formatBytesInto(dst: ByteArray, dstOffset: Int, startIndex: Int, endIndex: Int) {
      UuidKt.formatBytesIntoCommonImpl(`$this$formatBytesInto`, dst, dstOffset, startIndex, endIndex);
   }

   @ExperimentalUuidApi
   @JvmStatic
   internal fun ByteArray.setLongAt(index: Int, value: Long) {
      UuidKt.setLongAtCommonImpl(`$this$setLongAt`, index, value);
   }

   @ExperimentalUuidApi
   @JvmStatic
   internal fun uuidParseHexDash(hexDashString: String): Uuid {
      return UuidKt.uuidParseHexDashCommonImpl(hexDashString);
   }

   @ExperimentalUuidApi
   @JvmStatic
   internal fun uuidParseHex(hexString: String): Uuid {
      return UuidKt.uuidParseHexCommonImpl(hexString);
   }

   @SinceKotlin(version = "2.0")
   @ExperimentalUuidApi
   @JvmStatic
   public inline fun UUID.toKotlinUuid(): Uuid {
      return Uuid.Companion.fromLongs(`$this$toKotlinUuid`.getMostSignificantBits(), `$this$toKotlinUuid`.getLeastSignificantBits());
   }

   @SinceKotlin(version = "2.0")
   @ExperimentalUuidApi
   @JvmStatic
   public inline fun Uuid.toJavaUuid(): UUID {
      return new UUID(`$this$toJavaUuid`.getMostSignificantBits(), `$this$toJavaUuid`.getLeastSignificantBits());
   }

   @SinceKotlin(version = "2.0")
   @ExperimentalUuidApi
   @JvmStatic
   public fun ByteBuffer.getUuid(): Uuid {
      if (`$this$getUuid`.position() + 15 >= `$this$getUuid`.limit()) {
         throw new BufferUnderflowException();
      } else {
         var msb: Long = `$this$getUuid`.getLong();
         var lsb: Long = `$this$getUuid`.getLong();
         if (`$this$getUuid`.order() == ByteOrder.LITTLE_ENDIAN) {
            msb = java.lang.Long.reverseBytes(msb);
            lsb = java.lang.Long.reverseBytes(lsb);
         }

         return Uuid.Companion.fromLongs(msb, lsb);
      }
   }

   @SinceKotlin(version = "2.0")
   @ExperimentalUuidApi
   @JvmStatic
   public fun ByteBuffer.getUuid(index: Int): Uuid {
      if (index < 0) {
         throw new IndexOutOfBoundsException("Negative index: $index");
      } else if (index + 15 >= `$this$getUuid`.limit()) {
         throw new IndexOutOfBoundsException("Not enough bytes to read a uuid at index: $index, with limit: ${`$this$getUuid`.limit()} ");
      } else {
         var msb: Long = `$this$getUuid`.getLong(index);
         var lsb: Long = `$this$getUuid`.getLong(index + 8);
         if (`$this$getUuid`.order() == ByteOrder.LITTLE_ENDIAN) {
            msb = java.lang.Long.reverseBytes(msb);
            lsb = java.lang.Long.reverseBytes(lsb);
         }

         return Uuid.Companion.fromLongs(msb, lsb);
      }
   }

   @SinceKotlin(version = "2.0")
   @ExperimentalUuidApi
   @JvmStatic
   public fun ByteBuffer.putUuid(uuid: Uuid): ByteBuffer {
      val lsb: Long = uuid.getLeastSignificantBits();
      val msb: Long = uuid.getMostSignificantBits();
      if (`$this$putUuid`.position() + 15 >= `$this$putUuid`.limit()) {
         throw new BufferOverflowException();
      } else {
         val var10000: ByteBuffer;
         if (`$this$putUuid`.order() == ByteOrder.BIG_ENDIAN) {
            `$this$putUuid`.putLong(msb);
            var10000 = `$this$putUuid`.putLong(lsb);
         } else {
            `$this$putUuid`.putLong(java.lang.Long.reverseBytes(msb));
            var10000 = `$this$putUuid`.putLong(java.lang.Long.reverseBytes(lsb));
         }

         return var10000;
      }
   }

   @SinceKotlin(version = "2.0")
   @ExperimentalUuidApi
   @JvmStatic
   public fun ByteBuffer.putUuid(index: Int, uuid: Uuid): ByteBuffer {
      val lsb: Long = uuid.getLeastSignificantBits();
      val msb: Long = uuid.getMostSignificantBits();
      if (index < 0) {
         throw new IndexOutOfBoundsException("Negative index: $index");
      } else if (index + 15 >= `$this$putUuid`.limit()) {
         throw new IndexOutOfBoundsException("Not enough capacity to write a uuid at index: $index, with limit: ${`$this$putUuid`.limit()} ");
      } else {
         val var10000: ByteBuffer;
         if (`$this$putUuid`.order() == ByteOrder.BIG_ENDIAN) {
            `$this$putUuid`.putLong(index, msb);
            var10000 = `$this$putUuid`.putLong(index + 8, lsb);
         } else {
            `$this$putUuid`.putLong(index, java.lang.Long.reverseBytes(msb));
            var10000 = `$this$putUuid`.putLong(index + 8, java.lang.Long.reverseBytes(lsb));
         }

         return var10000;
      }
   }

   @JvmStatic
   internal inline fun Long.reverseBytes(): Long {
      return java.lang.Long.reverseBytes(`$this$reverseBytes`);
   }

   open fun UuidKt__UuidJVMKt() {
   }
}

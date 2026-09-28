package kotlinx.io.bytestring

import java.util.Arrays

public final val indices: IntRange
   public final get() {
      return RangesKt.until(0, `$this$indices`.getSize());
   }


public fun ByteString(bytes: ByteArray): ByteString {
   return if (bytes.length == 0) ByteString.Companion.getEMPTY$kotlinx_io_bytestring() else ByteString.Companion.wrap$kotlinx_io_bytestring(bytes);
}

public fun ByteString(bytes: UByteArray): ByteString {
   return if (UByteArray.isEmpty-impl(bytes)) ByteString.Companion.getEMPTY$kotlinx_io_bytestring() else ByteString.Companion.wrap$kotlinx_io_bytestring(bytes);
}

public fun ByteString(): ByteString {
   return ByteString.Companion.getEMPTY$kotlinx_io_bytestring();
}

public fun ByteString.indexOf(byte: Byte, startIndex: Int = 0): Int {
   val localData: ByteArray = `$this$indexOf`.getBackingArrayReference();
   var i: Int = Math.max(startIndex, 0);

   for (int var5 = $this$indexOf.getSize(); i < var5; i++) {
      if (localData[i] == var1) {
         return i;
      }
   }

   return -1;
}

@JvmSynthetic
fun `indexOf$default`(var0: ByteString, var1: Byte, var2: Int, var3: Int, var4: Any): Int {
   if ((var3 and 2) != 0) {
      var2 = 0;
   }

   return indexOf(var0, var1, var2);
}

public fun ByteString.indexOf(byteString: ByteString, startIndex: Int = 0): Int {
   if (isEmpty(byteString)) {
      return Math.max(Math.min(startIndex, `$this$indexOf`.getSize()), 0);
   } else {
      val localData: ByteArray = `$this$indexOf`.getBackingArrayReference();
      val firstByte: Byte = byteString.get(0);
      var i: Int = Math.max(startIndex, 0);
      val var6: Int = `$this$indexOf`.getSize() - byteString.getSize();
      if (i <= var6) {
         while (true) {
            if (localData[i] == firstByte && rangeEquals$default(`$this$indexOf`, i, byteString, 0, 0, 12, null)) {
               return i;
            }

            if (i == var6) {
               break;
            }

            i++;
         }
      }

      return -1;
   }
}

@JvmSynthetic
fun `indexOf$default`(var0: ByteString, var1: ByteString, var2: Int, var3: Int, var4: Any): Int {
   if ((var3 and 2) != 0) {
      var2 = 0;
   }

   return indexOf(var0, var1, var2);
}

public fun ByteString.indexOf(byteArray: ByteArray, startIndex: Int = 0): Int {
   if (byteArray.length == 0) {
      return Math.max(Math.min(startIndex, `$this$indexOf`.getSize()), 0);
   } else {
      val localData: ByteArray = `$this$indexOf`.getBackingArrayReference();
      val firstByte: Byte = byteArray[0];
      var i: Int = Math.max(0, startIndex);
      val var6: Int = `$this$indexOf`.getSize() - byteArray.length;
      if (i <= var6) {
         while (true) {
            if (localData[i] == firstByte && rangeEquals$default(`$this$indexOf`, i, byteArray, 0, 0, 12, null)) {
               return i;
            }

            if (i == var6) {
               break;
            }

            i++;
         }
      }

      return -1;
   }
}

@JvmSynthetic
fun `indexOf$default`(var0: ByteString, var1: ByteArray, var2: Int, var3: Int, var4: Any): Int {
   if ((var3 and 2) != 0) {
      var2 = 0;
   }

   return indexOf(var0, var1, var2);
}

public fun ByteString.lastIndexOf(byte: Byte, startIndex: Int = 0): Int {
   val localData: ByteArray = `$this$lastIndexOf`.getBackingArrayReference();
   var i: Int = `$this$lastIndexOf`.getSize() - 1;
   val var5: Int = Math.max(0, startIndex);
   if (var5 <= i) {
      while (true) {
         if (localData[i] == var1) {
            return i;
         }

         if (i == var5) {
            break;
         }

         i--;
      }
   }

   return -1;
}

@JvmSynthetic
fun `lastIndexOf$default`(var0: ByteString, var1: Byte, var2: Int, var3: Int, var4: Any): Int {
   if ((var3 and 2) != 0) {
      var2 = 0;
   }

   return lastIndexOf(var0, var1, var2);
}

public fun ByteString.lastIndexOf(byteString: ByteString, startIndex: Int = 0): Int {
   if (isEmpty(byteString)) {
      return `$this$lastIndexOf`.getSize();
   } else {
      var idx: Int = `$this$lastIndexOf`.getSize() - byteString.getSize();
      val var4: Int = Math.max(0, startIndex);
      if (var4 <= idx) {
         while (true) {
            if (rangeEquals$default(`$this$lastIndexOf`, idx, byteString, 0, 0, 8, null)) {
               return idx;
            }

            if (idx == var4) {
               break;
            }

            idx--;
         }
      }

      return -1;
   }
}

@JvmSynthetic
fun `lastIndexOf$default`(var0: ByteString, var1: ByteString, var2: Int, var3: Int, var4: Any): Int {
   if ((var3 and 2) != 0) {
      var2 = 0;
   }

   return lastIndexOf(var0, var1, var2);
}

public fun ByteString.lastIndexOf(byteArray: ByteArray, startIndex: Int = 0): Int {
   if (byteArray.length == 0) {
      return `$this$lastIndexOf`.getSize();
   } else {
      var idx: Int = `$this$lastIndexOf`.getSize() - byteArray.length;
      val var4: Int = Math.max(0, startIndex);
      if (var4 <= idx) {
         while (true) {
            if (rangeEquals$default(`$this$lastIndexOf`, idx, byteArray, 0, 0, 8, null)) {
               return idx;
            }

            if (idx == var4) {
               break;
            }

            idx--;
         }
      }

      return -1;
   }
}

@JvmSynthetic
fun `lastIndexOf$default`(var0: ByteString, var1: ByteArray, var2: Int, var3: Int, var4: Any): Int {
   if ((var3 and 2) != 0) {
      var2 = 0;
   }

   return lastIndexOf(var0, var1, var2);
}

public fun ByteString.startsWith(byteArray: ByteArray): Boolean {
   return byteArray.length <= `$this$startsWith`.getSize() && rangeEquals$default(`$this$startsWith`, 0, byteArray, 0, 0, 12, null);
}

public fun ByteString.startsWith(byteString: ByteString): Boolean {
   return byteString.getSize() <= `$this$startsWith`.getSize()
      && (
         if (byteString.getSize() == `$this$startsWith`.getSize())
            `$this$startsWith`.equals(byteString)
            else
            rangeEquals$default(`$this$startsWith`, 0, byteString, 0, 0, 12, null)
      );
}

public fun ByteString.endsWith(byteArray: ByteArray): Boolean {
   return byteArray.length <= `$this$endsWith`.getSize()
      && rangeEquals$default(`$this$endsWith`, `$this$endsWith`.getSize() - byteArray.length, byteArray, 0, 0, 12, null);
}

public fun ByteString.endsWith(byteString: ByteString): Boolean {
   return byteString.getSize() <= `$this$endsWith`.getSize()
      && (
         if (byteString.getSize() == `$this$endsWith`.getSize())
            `$this$endsWith`.equals(byteString)
            else
            rangeEquals$default(`$this$endsWith`, `$this$endsWith`.getSize() - byteString.getSize(), byteString, 0, 0, 12, null)
      );
}

private fun ByteString.rangeEquals(offset: Int, other: ByteString, otherOffset: Int = 0, byteCount: Int = other.getSize() - otherOffset): Boolean {
   val localData: ByteArray = `$this$rangeEquals`.getBackingArrayReference();
   val otherData: ByteArray = other.getBackingArrayReference();

   for (int i = 0; i < byteCount; i++) {
      if (localData[offset + i] != otherData[otherOffset + i]) {
         return false;
      }
   }

   return true;
}

@JvmSynthetic
fun `rangeEquals$default`(var0: ByteString, var1: Int, var2: ByteString, var3: Int, var4: Int, var5: Int, var6: Any): Boolean {
   if ((var5 and 4) != 0) {
      var3 = 0;
   }

   if ((var5 and 8) != 0) {
      var4 = var2.getSize() - var3;
   }

   return rangeEquals(var0, var1, var2, var3, var4);
}

private fun ByteString.rangeEquals(offset: Int, other: ByteArray, otherOffset: Int = 0, byteCount: Int = other.length - otherOffset): Boolean {
   val localData: ByteArray = `$this$rangeEquals`.getBackingArrayReference();

   for (int i = 0; i < byteCount; i++) {
      if (localData[offset + i] != other[otherOffset + i]) {
         return false;
      }
   }

   return true;
}

@JvmSynthetic
fun `rangeEquals$default`(var0: ByteString, var1: Int, var2: ByteArray, var3: Int, var4: Int, var5: Int, var6: Any): Boolean {
   if ((var5 and 4) != 0) {
      var3 = 0;
   }

   if ((var5 and 8) != 0) {
      var4 = var2.length - var3;
   }

   return rangeEquals(var0, var1, var2, var3, var4);
}

public fun ByteString.isEmpty(): Boolean {
   return `$this$isEmpty`.getSize() == 0;
}

public fun ByteString.isNotEmpty(): Boolean {
   return !isEmpty(`$this$isNotEmpty`);
}

public fun ByteString.decodeToString(): String {
   return StringsKt.decodeToString(`$this$decodeToString`.getBackingArrayReference());
}

public fun String.encodeToByteString(): ByteString {
   return ByteString.Companion.wrap$kotlinx_io_bytestring(StringsKt.encodeToByteArray(`$this$encodeToByteString`));
}

public fun ByteString.contentEquals(array: ByteArray): Boolean {
   return Arrays.equals(`$this$contentEquals`.getBackingArrayReference(), array);
}

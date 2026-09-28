package kotlinx.serialization.json.internal

import java.util.Arrays

internal class JsonToStringWriter : InternalJsonWriter {
   private final var array: CharArray = CharArrayPool.INSTANCE.take()
   private final var size: Int

   public override fun writeLong(value: Long) {
      this.write(java.lang.String.valueOf(value));
   }

   public override fun writeChar(char: Char) {
      this.ensureAdditionalCapacity(1);
      this.array[this.size++] = var1;
   }

   public override fun write(text: String) {
      val length: Int = text.length();
      if (length != 0) {
         this.ensureAdditionalCapacity(length);
         val var4: CharArray = this.array;
         val var5: Int = this.size;
         text.getChars(0, text.length(), var4, var5);
         this.size += length;
      }
   }

   public override fun writeQuoted(text: String) {
      this.ensureAdditionalCapacity(text.length() + 2);
      val arr: CharArray = this.array;
      this.array[this.size++] = '"';
      val length: Int = text.length();
      val sz: Int;
      text.getChars(0, length, arr, sz);
      var i: Int = sz;

      for (int var11 = sz + length; i < var11; i++) {
         if (arr[i] < StringOpsKt.getESCAPE_MARKERS().length && StringOpsKt.getESCAPE_MARKERS()[arr[i]] != 0) {
            this.appendStringSlowPath(i - sz, i, text);
            return;
         }
      }

      arr[(sz + length)++] = '"';
      this.size = sz;
   }

   private fun appendStringSlowPath(firstEscapedChar: Int, currentSize: Int, string: String) {
      var var14: Int = currentSize;
      var i: Int = firstEscapedChar;

      for (int var6 = string.length(); i < var6; i++) {
         var14 = this.ensureTotalCapacity(var14, 2);
         val ch: Int = string.charAt(i);
         if (ch < StringOpsKt.getESCAPE_MARKERS().length) {
            val marker: Byte = StringOpsKt.getESCAPE_MARKERS()[ch];
            if (marker == 0) {
               this.array[var14++] = (char)ch;
            } else if (marker == 1) {
               val var10000: java.lang.String = StringOpsKt.getESCAPE_STRINGS()[ch];
               val var15: Int = this.ensureTotalCapacity(var14, var10000.length());
               val var11: CharArray = this.array;
               var10000.getChars(0, var10000.length(), var11, var15);
               var14 = var15 + var10000.length();
               this.size = var14;
            } else {
               this.array[var14] = '\\';
               this.array[var14 + 1] = (char)marker;
               var14 += 2;
               this.size = var14;
            }
         } else {
            this.array[var14++] = (char)ch;
         }
      }

      this.array[this.ensureTotalCapacity(var14, 1)++] = '"';
      this.size = var14;
   }

   public override fun release() {
      CharArrayPool.INSTANCE.release(this.array);
   }

   public override fun toString(): String {
      return new java.lang.String(this.array, 0, this.size);
   }

   private fun ensureAdditionalCapacity(expected: Int) {
      this.ensureTotalCapacity(this.size, expected);
   }

   private fun ensureTotalCapacity(oldSize: Int, additional: Int): Int {
      val newSize: Int = oldSize + additional;
      if (this.array.length <= oldSize + additional) {
         val var10001: CharArray = Arrays.copyOf(this.array, RangesKt.coerceAtLeast(newSize, oldSize * 2));
         this.array = var10001;
      }

      return oldSize;
   }
}

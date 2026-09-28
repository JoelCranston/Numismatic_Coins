package io.ktor.util

internal class Sha1 : HashFunction {
   private final var messageLength: Long
   private final val unprocessed: ByteArray = new byte[64]
   private final var unprocessedLimit: Int
   private final val words: IntArray = new int[80]
   private final var h0: Int = 1732584193
   private final var h1: Int = -271733879
   private final var h2: Int = -1732584194
   private final var h3: Int = 271733878
   private final var h4: Int = -1009589776

   public override fun update(input: ByteArray, offset: Int, length: Int) {
      this.messageLength += length;
      var pos: Int = offset;
      val limit: Int = offset + length;
      val unprocessed: ByteArray = this.unprocessed;
      val unprocessedLimit: Int = this.unprocessedLimit;
      if (this.unprocessedLimit > 0) {
         if (this.unprocessedLimit + length < 64) {
            ArraysKt.copyInto(input, this.unprocessed, this.unprocessedLimit, offset, limit);
            this.unprocessedLimit = unprocessedLimit + length;
            return;
         }

         val nextPos: Int = 64 - this.unprocessedLimit;
         ArraysKt.copyInto(input, this.unprocessed, this.unprocessedLimit, offset, offset + (64 - this.unprocessedLimit));
         this.processChunk(unprocessed, 0);
         this.unprocessedLimit = 0;
         pos = offset + nextPos;
      }

      while (pos < limit) {
         val var9: Int = pos + 64;
         if (pos + 64 > limit) {
            ArraysKt.copyInto(input, this.unprocessed, 0, pos, limit);
            this.unprocessedLimit = limit - pos;
            return;
         }

         this.processChunk(input, pos);
         pos = var9;
      }
   }

   private fun processChunk(input: ByteArray, pos: Int) {
      val words: IntArray = this.words;
      var currentPosition: Int = pos;

      for (int w = 0; w < 16; w++) {
         words[a] = (input[currentPosition++] and 255) shl 24 or (input[currentPosition++] and 255) shl 16 or (input[currentPosition++] and 255) shl 8 or input[currentPosition++] and 255;
      }

      for (int w = 16; w < 80; w++) {
         words[var17] = HashFunctionKt.access$leftRotate(words[var17 - 3] xor words[var17 - 8] xor words[var17 - 14] xor words[var17 - 16], 1);
      }

      var var18: Int = this.h0;
      var b: Int = this.h1;
      var c: Int = this.h2;
      var d: Int = this.h3;
      var e: Int = this.h4;

      for (int i = 0; i < 80; i++) {
         val var10000: Int = if (i < 20)
            HashFunctionKt.access$leftRotate(var18, 5) + (d xor b and (c xor d)) + e + 1518500249 + words[i]
            else
            (
               if (i < 40)
                  HashFunctionKt.access$leftRotate(var18, 5) + (b xor c xor d) + e + 1859775393 + words[i]
                  else
                  (
                     if (i < 60)
                        HashFunctionKt.access$leftRotate(var18, 5) + (b and c or b and d or c and d) + e + -1894007588 + words[i]
                        else
                        HashFunctionKt.access$leftRotate(var18, 5) + (b xor c xor d) + e + -899497514 + words[i]
                  )
            );
         e = d;
         d = c;
         c = HashFunctionKt.access$leftRotate(b, 30);
         b = var18;
         var18 = var10000;
      }

      this.h0 += var18;
      this.h1 += b;
      this.h2 += c;
      this.h3 += d;
      this.h4 += e;
   }

   public override fun digest(): ByteArray {
      val unprocessed: ByteArray = this.unprocessed;
      val messageLengthBits: Long = this.messageLength * 8;
      this.unprocessed[this.unprocessedLimit++] = -128;
      val unprocessedLimit: Int;
      if (unprocessedLimit > 56) {
         ArraysKt.fill(this.unprocessed, (byte)0, unprocessedLimit, 64);
         this.processChunk(unprocessed, 0);
         ArraysKt.fill(unprocessed, (byte)0, 0, unprocessedLimit);
      } else {
         ArraysKt.fill(this.unprocessed, (byte)0, unprocessedLimit, 56);
      }

      unprocessed[56] = (byte)(messageLengthBits ushr 56);
      unprocessed[57] = (byte)(messageLengthBits ushr 48);
      unprocessed[58] = (byte)(messageLengthBits ushr 40);
      unprocessed[59] = (byte)(messageLengthBits ushr 32);
      unprocessed[60] = (byte)(messageLengthBits ushr 24);
      unprocessed[61] = (byte)(messageLengthBits ushr 16);
      unprocessed[62] = (byte)(messageLengthBits ushr 8);
      unprocessed[63] = (byte)messageLengthBits;
      this.processChunk(unprocessed, 0);
      val a: Int = this.h0;
      val b: Int = this.h1;
      val c: Int = this.h2;
      val d: Int = this.h3;
      val e: Int = this.h4;
      this.reset();
      return new byte[]{
         (byte)(a shr 24),
         (byte)(a shr 16),
         (byte)(a shr 8),
         (byte)a,
         (byte)(b shr 24),
         (byte)(b shr 16),
         (byte)(b shr 8),
         (byte)b,
         (byte)(c shr 24),
         (byte)(c shr 16),
         (byte)(c shr 8),
         (byte)c,
         (byte)(d shr 24),
         (byte)(d shr 16),
         (byte)(d shr 8),
         (byte)d,
         (byte)(e shr 24),
         (byte)(e shr 16),
         (byte)(e shr 8),
         (byte)e
      };
   }

   private fun reset() {
      this.messageLength = 0L;
      ArraysKt.fill$default(this.unprocessed, (byte)0, 0, 0, 6, null);
      this.unprocessedLimit = 0;
      ArraysKt.fill$default(this.words, 0, 0, 0, 6, null);
      this.h0 = 1732584193;
      this.h1 = -271733879;
      this.h2 = -1732584194;
      this.h3 = 271733878;
      this.h4 = -1009589776;
   }
}

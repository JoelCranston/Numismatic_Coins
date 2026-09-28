package kotlin.random

import java.io.InvalidObjectException
import java.io.ObjectInputStream
import java.io.Serializable
import kotlin.jvm.internal.SourceDebugExtension

@SinceKotlin(version = "1.3")
@SourceDebugExtension(["SMAP\nRandom.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Random.kt\nkotlin/random/Random\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,387:1\n1#2:388\n*E\n"])
public abstract class Random {
   public abstract fun nextBits(bitCount: Int): Int {
   }

   public open fun nextInt(): Int {
      return this.nextBits(32);
   }

   public open fun nextInt(until: Int): Int {
      return this.nextInt(0, until);
   }

   public open fun nextInt(from: Int, until: Int): Int {
      RandomKt.checkRangeBounds(from, until);
      val n: Int = until - from;
      if (until - from <= 0 && until - from != Integer.MIN_VALUE) {
         val var7: Int;
         do {
            var7 = this.nextInt();
         } while (from > rnd || rnd >= until);

         return var7;
      } else {
         val var10000: Int;
         if ((n and -n) == n) {
            var10000 = this.nextBits(RandomKt.fastLog2(n));
         } else {
            val bits: Int;
            do {
               bits = this.nextInt() ushr 1;
            } while (bits - bits % n + (n - 1) < 0);

            var10000 = bits % n;
         }

         return from + var10000;
      }
   }

   public open fun nextLong(): Long {
      return ((long)this.nextInt() shl 32) + this.nextInt();
   }

   public open fun nextLong(until: Long): Long {
      return this.nextLong(0L, until);
   }

   public open fun nextLong(from: Long, until: Long): Long {
      RandomKt.checkRangeBounds(from, until);
      val n: Long = until - from;
      if (until - from <= 0L) {
         val var14: Long;
         do {
            var14 = this.nextLong();
         } while (from > rnd || rnd >= until);

         return var14;
      } else {
         val var13: Long;
         if ((n and -n) == n) {
            var13 = if ((int)n != 0)
               this.nextBits(RandomKt.fastLog2((int)n)) and 4294967295L
               else
               (
                  if ((int)(n ushr 32) == 1)
                     this.nextInt() and 4294967295L
                     else
                     ((long)this.nextBits(RandomKt.fastLog2((int)(n ushr 32))) shl 32) + (this.nextInt() and 4294967295L)
               );
         } else {
            val var18: Long;
            do {
               var18 = this.nextLong() ushr 1;
            } while (bits - bits % n + (n - 1L) < 0L);

            var13 = var18 % n;
         }

         return from + var13;
      }
   }

   public open fun nextBoolean(): Boolean {
      return this.nextBits(1) != 0;
   }

   public open fun nextDouble(): Double {
      return PlatformRandomKt.doubleFromParts(this.nextBits(26), this.nextBits(27));
   }

   public open fun nextDouble(until: Double): Double {
      return this.nextDouble(0.0, until);
   }

   public open fun nextDouble(from: Double, until: Double): Double {
      RandomKt.checkRangeBounds(from, until);
      val size: Double = until - from;
      val var10000: Double;
      if (java.lang.Double.isInfinite(until - from) && Math.abs(from) <= java.lang.Double.MAX_VALUE && Math.abs(until) <= java.lang.Double.MAX_VALUE) {
         val r1: Double = this.nextDouble() * (until / 2 - from / 2);
         var10000 = from + r1 + r1;
      } else {
         var10000 = from + this.nextDouble() * size;
      }

      return if (var10000 >= until) Math.nextAfter(until, java.lang.Double.NEGATIVE_INFINITY) else var10000;
   }

   public open fun nextFloat(): Float {
      return this.nextBits(24) / 1.6777216E7F;
   }

   public open fun nextBytes(array: ByteArray, fromIndex: Int = 0, toIndex: Int = array.length): ByteArray {
      if (0 > fromIndex || fromIndex > array.length || 0 > toIndex || toIndex > array.length) {
         throw new IllegalArgumentException(("fromIndex ($fromIndex) or toIndex ($toIndex) are out of range: 0..${array.length}.").toString());
      } else if (fromIndex > toIndex) {
         throw new IllegalArgumentException(("fromIndex ($fromIndex) must be not greater than toIndex ($toIndex).").toString());
      } else {
         val steps: Int = (toIndex - fromIndex) / 4;
         var var10: Int = fromIndex;

         for (int remainder = 0; remainder < steps; remainder++) {
            val v: Int = this.nextInt();
            array[var10] = (byte)v;
            array[var10 + 1] = (byte)(v ushr 8);
            array[var10 + 2] = (byte)(v ushr 16);
            array[var10 + 3] = (byte)(v ushr 24);
            var10 += 4;
         }

         val var15: Int = toIndex - var10;
         val vr: Int = this.nextBits((toIndex - var10) * 8);

         for (int i = 0; i < remainder; i++) {
            array[var10 + var16] = (byte)(vr ushr var16 * 8);
         }

         return array;
      }
   }

   public open fun nextBytes(array: ByteArray): ByteArray {
      return this.nextBytes(array, 0, array.length);
   }

   public open fun nextBytes(size: Int): ByteArray {
      return this.nextBytes(new byte[size]);
   }

   public companion object Default : Random, Serializable {
      private final val defaultRandom: Random

      private fun writeReplace(): Any {
         return Random.Default.Serialized.INSTANCE;
      }

      private fun readObject(input: ObjectInputStream) {
         throw new InvalidObjectException("Deserialization is supported via proxy only");
      }

      public override fun nextBits(bitCount: Int): Int {
         return Random.access$getDefaultRandom$cp().nextBits(bitCount);
      }

      public override fun nextInt(): Int {
         return Random.access$getDefaultRandom$cp().nextInt();
      }

      public override fun nextInt(until: Int): Int {
         return Random.access$getDefaultRandom$cp().nextInt(until);
      }

      public override fun nextInt(from: Int, until: Int): Int {
         return Random.access$getDefaultRandom$cp().nextInt(from, until);
      }

      public override fun nextLong(): Long {
         return Random.access$getDefaultRandom$cp().nextLong();
      }

      public override fun nextLong(until: Long): Long {
         return Random.access$getDefaultRandom$cp().nextLong(until);
      }

      public override fun nextLong(from: Long, until: Long): Long {
         return Random.access$getDefaultRandom$cp().nextLong(from, until);
      }

      public override fun nextBoolean(): Boolean {
         return Random.access$getDefaultRandom$cp().nextBoolean();
      }

      public override fun nextDouble(): Double {
         return Random.access$getDefaultRandom$cp().nextDouble();
      }

      public override fun nextDouble(until: Double): Double {
         return Random.access$getDefaultRandom$cp().nextDouble(until);
      }

      public override fun nextDouble(from: Double, until: Double): Double {
         return Random.access$getDefaultRandom$cp().nextDouble(from, until);
      }

      public override fun nextFloat(): Float {
         return Random.access$getDefaultRandom$cp().nextFloat();
      }

      public override fun nextBytes(array: ByteArray): ByteArray {
         return Random.access$getDefaultRandom$cp().nextBytes(array);
      }

      public override fun nextBytes(size: Int): ByteArray {
         return Random.access$getDefaultRandom$cp().nextBytes(size);
      }

      public override fun nextBytes(array: ByteArray, fromIndex: Int, toIndex: Int): ByteArray {
         return Random.access$getDefaultRandom$cp().nextBytes(array, fromIndex, toIndex);
      }

      private object Serialized : Serializable {
         private const val serialVersionUID: Long = 0L

         private fun readResolve(): Any {
            return Random.Default;
         }
      }
   }
}

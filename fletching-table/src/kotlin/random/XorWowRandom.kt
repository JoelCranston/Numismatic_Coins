package kotlin.random

import java.io.InvalidObjectException
import java.io.Serializable
import kotlin.jvm.internal.SourceDebugExtension

@SourceDebugExtension(["SMAP\nXorWowRandom.kt\nKotlin\n*S Kotlin\n*F\n+ 1 XorWowRandom.kt\nkotlin/random/XorWowRandom\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,68:1\n1#2:69\n*E\n"])
internal class XorWowRandom internal constructor(x: Int, y: Int, z: Int, w: Int, v: Int, addend: Int) : Random, Serializable {
   private final var x: Int
   private final var y: Int
   private final var z: Int
   private final var w: Int
   private final var v: Int
   private final var addend: Int

   init {
      this.x = x;
      this.y = y;
      this.z = z;
      this.w = w;
      this.v = v;
      this.addend = addend;
      this.checkInvariants();
      val var7: Byte = 64;

      for (int var8 = 0; var8 < var7; var8++) {
         this.nextInt();
      }
   }

   internal constructor(seed1: Int, seed2: Int) : this(seed1, seed2, 0, 0, seed1.inv(), seed1 shl 10 xor seed2 ushr 4)
   private fun checkInvariants() {
      if ((this.x or this.y or this.z or this.w or this.v) == 0) {
         throw new IllegalArgumentException("Initial state must have at least one non-zero element.".toString());
      }
   }

   private fun readResolve(): Any {
      val it: XorWowRandom = this;

      try {
         this.checkInvariants();
      } catch (var5: java.lang.Throwable) {
         val var10000: java.lang.Throwable = new InvalidObjectException(var5.getMessage()).initCause(var5);
         throw var10000;
      }

      return this;
   }

   public override fun nextInt(): Int {
      val var3: Int = this.x xor this.x ushr 2;
      this.x = this.y;
      this.y = this.z;
      this.z = this.w;
      val v0: Int = this.v;
      this.w = this.v;
      val var4: Int = var3 xor var3 shl 1 xor v0 xor v0 shl 4;
      this.v = var3 xor var3 shl 1 xor v0 xor v0 shl 4;
      this.addend += 362437;
      return var4 + this.addend;
   }

   public override fun nextBits(bitCount: Int): Int {
      return RandomKt.takeUpperBits(this.nextInt(), bitCount);
   }

   private companion object {
      private const val serialVersionUID: Long
   }
}

package kotlin.internal

private fun differenceModulo(a: UInt, b: UInt, c: UInt): UInt {
   val ac: Int = Integer.remainderUnsigned(var0, var2);
   val bc: Int = Integer.remainderUnsigned(var1, var2);
   return if (Integer.compareUnsigned(ac, bc) >= 0) UInt.constructor-impl(ac - bc) else UInt.constructor-impl(UInt.constructor-impl(ac - bc) + var2);
}

private fun differenceModulo(a: ULong, b: ULong, c: ULong): ULong {
   val ac: Long = java.lang.Long.remainderUnsigned(var0, var4);
   val bc: Long = java.lang.Long.remainderUnsigned(var2, var4);
   return if (java.lang.Long.compareUnsigned(ac, bc) >= 0) ULong.constructor-impl(ac - bc) else ULong.constructor-impl(ULong.constructor-impl(ac - bc) + var4);
}

@PublishedApi
@SinceKotlin(version = "1.3")
internal fun getProgressionLastElement(start: UInt, end: UInt, step: Int): UInt {
   val var10000: Int;
   if (step > 0) {
      var10000 = if (Integer.compareUnsigned(var0, var1) >= 0)
         var1
         else
         UInt.constructor-impl(var1 - differenceModulo-WZ9TVnA(var1, var0, UInt.constructor-impl(step)));
   } else {
      if (step >= 0) {
         throw new IllegalArgumentException("Step is zero.");
      }

      var10000 = if (Integer.compareUnsigned(var0, var1) <= 0)
         var1
         else
         UInt.constructor-impl(var1 + differenceModulo-WZ9TVnA(var0, var1, UInt.constructor-impl(-step)));
   }

   return var10000;
}

@PublishedApi
@SinceKotlin(version = "1.3")
internal fun getProgressionLastElement(start: ULong, end: ULong, step: Long): ULong {
   val var10000: Long;
   if (step > 0L) {
      var10000 = if (java.lang.Long.compareUnsigned(var0, var2) >= 0)
         var2
         else
         ULong.constructor-impl(var2 - differenceModulo-sambcqE(var2, var0, ULong.constructor-impl(step)));
   } else {
      if (step >= 0L) {
         throw new IllegalArgumentException("Step is zero.");
      }

      var10000 = if (java.lang.Long.compareUnsigned(var0, var2) <= 0)
         var2
         else
         ULong.constructor-impl(var2 + differenceModulo-sambcqE(var0, var2, ULong.constructor-impl(-step)));
   }

   return var10000;
}

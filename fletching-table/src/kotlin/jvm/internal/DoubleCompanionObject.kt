package kotlin.jvm.internal

internal object DoubleCompanionObject {
   @SinceKotlin(
      version = "1.4"
   )
   public const val MIN_VALUE: Double = java.lang.Double.MIN_VALUE

   @SinceKotlin(
      version = "1.4"
   )
   public const val MAX_VALUE: Double = java.lang.Double.MAX_VALUE

   @SinceKotlin(
      version = "1.4"
   )
   public const val POSITIVE_INFINITY: Double = java.lang.Double.POSITIVE_INFINITY

   @SinceKotlin(
      version = "1.4"
   )
   public const val NEGATIVE_INFINITY: Double = java.lang.Double.NEGATIVE_INFINITY

   @SinceKotlin(
      version = "1.4"
   )
   public const val NaN: Double = java.lang.Double.NaN

   @SinceKotlin(
      version = "1.4"
   )
   public const val SIZE_BYTES: Int = 8

   @SinceKotlin(
      version = "1.4"
   )
   public const val SIZE_BITS: Int = 64

   public fun getMIN_VALUE(): Double {
      return java.lang.Double.MIN_VALUE;
   }

   public fun getMAX_VALUE(): Double {
      return java.lang.Double.MAX_VALUE;
   }

   public fun getPOSITIVE_INFINITY(): Double {
      return java.lang.Double.POSITIVE_INFINITY;
   }

   public fun getNEGATIVE_INFINITY(): Double {
      return java.lang.Double.NEGATIVE_INFINITY;
   }

   public fun getNaN(): Double {
      return java.lang.Double.NaN;
   }
}

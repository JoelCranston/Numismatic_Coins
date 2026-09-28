package kotlin.jvm.internal

internal object FloatCompanionObject {
   @SinceKotlin(
      version = "1.4"
   )
   public const val MIN_VALUE: Float = java.lang.Float.MIN_VALUE

   @SinceKotlin(
      version = "1.4"
   )
   public const val MAX_VALUE: Float = java.lang.Float.MAX_VALUE

   @SinceKotlin(
      version = "1.4"
   )
   public const val POSITIVE_INFINITY: Float = java.lang.Float.POSITIVE_INFINITY

   @SinceKotlin(
      version = "1.4"
   )
   public const val NEGATIVE_INFINITY: Float = java.lang.Float.NEGATIVE_INFINITY

   @SinceKotlin(
      version = "1.4"
   )
   public const val NaN: Float = java.lang.Float.NaN

   @SinceKotlin(
      version = "1.4"
   )
   public const val SIZE_BYTES: Int = 4

   @SinceKotlin(
      version = "1.4"
   )
   public const val SIZE_BITS: Int = 32

   public fun getMIN_VALUE(): Float {
      return java.lang.Float.MIN_VALUE;
   }

   public fun getMAX_VALUE(): Float {
      return java.lang.Float.MAX_VALUE;
   }

   public fun getPOSITIVE_INFINITY(): Float {
      return java.lang.Float.POSITIVE_INFINITY;
   }

   public fun getNEGATIVE_INFINITY(): Float {
      return java.lang.Float.NEGATIVE_INFINITY;
   }

   public fun getNaN(): Float {
      return java.lang.Float.NaN;
   }
}

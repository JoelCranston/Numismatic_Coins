package kotlin.jvm.internal

internal object ShortCompanionObject {
   public const val MIN_VALUE: Short = -32768
   public const val MAX_VALUE: Short = 32767

   @SinceKotlin(
      version = "1.3"
   )
   public const val SIZE_BYTES: Int = 2

   @SinceKotlin(
      version = "1.3"
   )
   public const val SIZE_BITS: Int = 16
}

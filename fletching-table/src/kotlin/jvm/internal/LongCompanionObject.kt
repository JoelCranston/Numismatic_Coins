package kotlin.jvm.internal

internal object LongCompanionObject {
   public const val MIN_VALUE: Long = java.lang.Long.MIN_VALUE
   public const val MAX_VALUE: Long = java.lang.Long.MAX_VALUE

   @SinceKotlin(
      version = "1.3"
   )
   public const val SIZE_BYTES: Int = 8

   @SinceKotlin(
      version = "1.3"
   )
   public const val SIZE_BITS: Int = 64
}

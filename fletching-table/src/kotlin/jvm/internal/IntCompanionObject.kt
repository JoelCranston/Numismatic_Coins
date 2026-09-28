package kotlin.jvm.internal

internal object IntCompanionObject {
   public const val MIN_VALUE: Int = Integer.MIN_VALUE
   public const val MAX_VALUE: Int = Integer.MAX_VALUE

   @SinceKotlin(
      version = "1.3"
   )
   public const val SIZE_BYTES: Int = 4

   @SinceKotlin(
      version = "1.3"
   )
   public const val SIZE_BITS: Int = 32
}

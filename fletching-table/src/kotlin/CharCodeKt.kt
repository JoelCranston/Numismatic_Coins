package kotlin

import kotlin.internal.InlineOnly
import kotlin.internal.IntrinsicConstEvaluation

@SinceKotlin(
   version = "1.5"
)
@InlineOnly
@IntrinsicConstEvaluation
public final val code: Int
   public final inline get() {
      return `$this$code`;
   }


@SinceKotlin(version = "1.5")
@InlineOnly
public inline fun Char(code: Int): Char {
   if (code >= 0 && code <= 65535) {
      return (char)code;
   } else {
      throw new IllegalArgumentException("Invalid Char code: $code");
   }
}

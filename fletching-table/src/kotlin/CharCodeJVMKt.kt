package kotlin

import kotlin.internal.InlineOnly

@SinceKotlin(version = "1.5")
@InlineOnly
public inline fun Char(code: UShort): Char {
   return (char)(var0 and '\uffff');
}

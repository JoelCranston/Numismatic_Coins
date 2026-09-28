package it.krzeminski.snakeyaml.engine.kmp.internal.utils

internal fun String.toCharArray(destination: CharArray, destinationOffset: Int, startIndex: Int, endIndex: Int): CharArray {
   return ArraysKt.copyInto$default(StringsKt.toCharArray(`$this$toCharArray`, startIndex, endIndex), destination, destinationOffset, 0, 0, 12, null);
}

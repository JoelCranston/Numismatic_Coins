@file:SourceDebugExtension(["SMAP\nAppendableExtensions.kt\nKotlin\n*S Kotlin\n*F\n+ 1 AppendableExtensions.kt\nit/krzeminski/snakeyaml/engine/kmp/internal/utils/AppendableExtensionsKt\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,56:1\n1869#2,2:57\n*S KotlinDebug\n*F\n+ 1 AppendableExtensions.kt\nit/krzeminski/snakeyaml/engine/kmp/internal/utils/AppendableExtensionsKt\n*L\n53#1:57,2\n*E\n"])

package it.krzeminski.snakeyaml.engine.kmp.internal.utils

import kotlin.jvm.internal.SourceDebugExtension

internal fun <T : Appendable> T.appendCodePoint(codePoint: Int): Appendable {
   if (Character.INSTANCE.isBmpCodePoint$snakeyaml_engine_kmp(codePoint)) {
      `$this$appendCodePoint`.append((char)codePoint);
   } else {
      `$this$appendCodePoint`.append(Character.INSTANCE.highSurrogateOf$snakeyaml_engine_kmp(codePoint));
      `$this$appendCodePoint`.append(Character.INSTANCE.lowSurrogateOf$snakeyaml_engine_kmp(codePoint));
   }

   return `$this$appendCodePoint`;
}

internal fun Iterable<Int>.joinCodepointsToString(): String {
   val var1: StringBuilder = new StringBuilder();
   appendCodePoints(var1, `$this$joinCodepointsToString`);
   return var1.toString();
}

private fun <T : Appendable> T.appendCodePoints(codePoints: Iterable<Int>): T {
   for (Object element$iv : codePoints) {
      appendCodePoint(`$this$appendCodePoints`, (`element$iv` as java.lang.Number).intValue());
   }

   return (T)`$this$appendCodePoints`;
}

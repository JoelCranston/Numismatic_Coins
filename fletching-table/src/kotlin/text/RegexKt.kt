@file:SourceDebugExtension(["SMAP\nRegex.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Regex.kt\nkotlin/text/RegexKt\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,404:1\n1803#2,3:405\n*S KotlinDebug\n*F\n+ 1 Regex.kt\nkotlin/text/RegexKt\n*L\n21#1:405,3\n*E\n"])

package kotlin.text

import java.util.Collections
import java.util.EnumSet
import java.util.regex.Matcher
import kotlin.jvm.internal.Intrinsics
import kotlin.jvm.internal.SourceDebugExtension
import kotlin.text.RegexKt.fromInt.1.1

private fun Iterable<FlagEnum>.toInt(): Int {
   var `accumulator$iv`: Int = 0;

   for (Object element$iv : $this$toInt) {
      `accumulator$iv` |= (`element$iv` as FlagEnum).getValue();
   }

   return `accumulator$iv`;
}

@JvmSynthetic
private inline fun <reified T> fromInt(value: Int): Set<T> where T : FlagEnum, T : Enum<T> {
   Intrinsics.reifiedOperationMarker(4, "T");
   val var2: EnumSet = EnumSet.allOf(java.lang.Enum::class.java);
   val `$this$fromInt_u24lambda_u240`: EnumSet = var2;
   val var10000: java.lang.Iterable = `$this$fromInt_u24lambda_u240`;
   Intrinsics.needClassReification();
   CollectionsKt.retainAll(var10000, new 1(value));
   val var5: java.util.Set = Collections.unmodifiableSet(var2);
   return var5;
}

private fun Matcher.findNext(from: Int, input: CharSequence): MatchResult? {
   return if (!`$this$findNext`.find(from)) null else new MatcherMatchResult(`$this$findNext`, input);
}

private fun Matcher.matchEntire(input: CharSequence): MatchResult? {
   return if (!`$this$matchEntire`.matches()) null else new MatcherMatchResult(`$this$matchEntire`, input);
}

private fun java.util.regex.MatchResult.range(): IntRange {
   return RangesKt.until(`$this$range`.start(), `$this$range`.end());
}

private fun java.util.regex.MatchResult.range(groupIndex: Int): IntRange {
   return RangesKt.until(`$this$range`.start(groupIndex), `$this$range`.end(groupIndex));
}

@JvmSynthetic
fun `access$toInt`(`$receiver`: java.lang.Iterable): Int {
   return toInt(`$receiver`);
}

@JvmSynthetic
fun `access$findNext`(`$receiver`: Matcher, from: Int, input: java.lang.CharSequence): MatchResult {
   return findNext(`$receiver`, from, input);
}

@JvmSynthetic
fun `access$matchEntire`(`$receiver`: Matcher, input: java.lang.CharSequence): MatchResult {
   return matchEntire(`$receiver`, input);
}

@JvmSynthetic
fun `access$range`(`$receiver`: java.util.regex.MatchResult): IntRange {
   return range(`$receiver`);
}

@JvmSynthetic
fun `access$range`(`$receiver`: java.util.regex.MatchResult, groupIndex: Int): IntRange {
   return range(`$receiver`, groupIndex);
}

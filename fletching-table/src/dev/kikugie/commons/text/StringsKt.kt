@file:SourceDebugExtension(["SMAP\nStrings.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Strings.kt\ndev/kikugie/commons/text/StringsKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,87:1\n48#1:89\n65#1,5:90\n56#1,14:95\n65#1,5:109\n65#1,5:114\n65#1,5:119\n1#2:88\n*S KotlinDebug\n*F\n+ 1 Strings.kt\ndev/kikugie/commons/text/StringsKt\n*L\n23#1:89\n23#1:90,5\n31#1:95,14\n40#1:109,5\n48#1:114,5\n56#1:119,5\n*E\n"])

package dev.kikugie.commons.text

import dev.kikugie.commons.text.StringsKt.reverseView.1
import kotlin.jvm.functions.Function1
import kotlin.jvm.internal.SourceDebugExtension

public fun CharSequence.getOrDefault(index: Int, default: Char = 32): Char {
   return if (0 <= index && index < `$this$getOrDefault`.length()) `$this$getOrDefault`.charAt(index) else var2;
}

@JvmSynthetic
fun `getOrDefault$default`(var0: java.lang.CharSequence, var1: Int, var2: Char, var3: Int, var4: Any): Char {
   if ((var3 and 2) != 0) {
      var2 = ' ';
   }

   return getOrDefault(var0, var1, var2);
}

@JvmName(name = "countMatchingFull")
public fun CharSequence.countMatching(chars: CharArray): Int {
   val `$this$countWhile$iv$iv`: java.lang.CharSequence = `$this$countMatching`;
   val `end$iv$iv`: Int = `$this$countMatching`.length();
   var `count$iv$iv`: Int = 0;
   var `i$iv$iv`: Int = RangesKt.coerceAtLeast(0, 0);

   for (int var10 = RangesKt.coerceAtMost(end$iv$iv, $this$countMatching.length()); i$iv$iv < var10; i$iv$iv++) {
      if (!ArraysKt.contains(chars, `$this$countWhile$iv$iv`.charAt(`i$iv$iv`))) {
         break;
      }

      `count$iv$iv`++;
   }

   return `count$iv$iv`;
}

public fun CharSequence.countMatching(range: IntRange, chars: CharArray): Int {
   val `$this$countWhile$iv$iv`: java.lang.CharSequence = `$this$countMatching`;
   val `start$iv$iv`: Int = range.getFirst();
   val `end$iv$iv`: Int = range.getLast() + 1;
   var `count$iv$iv`: Int = 0;
   var `i$iv$iv`: Int = RangesKt.coerceAtLeast(`start$iv$iv`, 0);

   for (int var12 = RangesKt.coerceAtMost(end$iv$iv, $this$countMatching.length()); i$iv$iv < var12; i$iv$iv++) {
      if (!ArraysKt.contains(chars, `$this$countWhile$iv$iv`.charAt(`i$iv$iv`))) {
         break;
      }

      `count$iv$iv`++;
   }

   return `count$iv$iv`;
}

public fun CharSequence.countMatching(start: Int = 0, end: Int = `$this$countMatching`.length(), chars: CharArray): Int {
   val `$this$countWhile$iv`: java.lang.CharSequence = `$this$countMatching`;
   var `count$iv`: Int = 0;
   var `i$iv`: Int = RangesKt.coerceAtLeast(start, 0);

   for (int var10 = RangesKt.coerceAtMost(end, $this$countMatching.length()); i$iv < var10; i$iv++) {
      if (!ArraysKt.contains(chars, `$this$countWhile$iv`.charAt(`i$iv`))) {
         break;
      }

      `count$iv`++;
   }

   return `count$iv`;
}

@JvmSynthetic
fun `countMatching$default`(var0: java.lang.CharSequence, var1: Int, var2: Int, var3: CharArray, var4: Int, var5: Any): Int {
   if ((var4 and 1) != 0) {
      var1 = 0;
   }

   if ((var4 and 2) != 0) {
      var2 = var0.length();
   }

   return countMatching(var0, var1, var2, var3);
}

@JvmName(name = "countWhileFull")
public inline fun CharSequence.countWhile(predicate: (Char) -> Boolean): Int {
   val `$this$countWhile$iv`: java.lang.CharSequence = `$this$countWhile`;
   val `end$iv`: Int = `$this$countWhile`.length();
   var `count$iv`: Int = 0;
   var `i$iv`: Int = RangesKt.coerceAtLeast(0, 0);

   for (int var9 = RangesKt.coerceAtMost(end$iv, $this$countWhile.length()); i$iv < var9 && predicate.invoke($this$countWhile$iv.charAt(i$iv)); i$iv++) {
      `count$iv`++;
   }

   return `count$iv`;
}

public inline fun CharSequence.countWhile(range: IntRange, predicate: (Char) -> Boolean): Int {
   val `$this$countWhile$iv`: java.lang.CharSequence = `$this$countWhile`;
   val `start$iv`: Int = range.getFirst();
   val `end$iv`: Int = range.getLast() + 1;
   var `count$iv`: Int = 0;
   var `i$iv`: Int = RangesKt.coerceAtLeast(`start$iv`, 0);

   for (int var10 = RangesKt.coerceAtMost(end$iv, $this$countWhile.length()); i$iv < var10 && predicate.invoke($this$countWhile$iv.charAt(i$iv)); i$iv++) {
      `count$iv`++;
   }

   return `count$iv`;
}

public inline fun CharSequence.countWhile(start: Int = 0, end: Int = `$this$countWhile`.length(), predicate: (Char) -> Boolean): Int {
   var count: Int = 0;
   var i: Int = RangesKt.coerceAtLeast(start, 0);

   for (int var7 = RangesKt.coerceAtMost(end, $this$countWhile.length()); i < var7 && predicate.invoke($this$countWhile.charAt(i)); i++) {
      count++;
   }

   return count;
}

@JvmSynthetic
fun java.lang.CharSequence.`countWhile$default`(start: Int, end: Int, predicate: Function1, `$i$f$countWhile`: Int, count: Any): Int {
   if ((`$i$f$countWhile` and 1) != 0) {
      start = 0;
   }

   if ((`$i$f$countWhile` and 2) != 0) {
      end = `$this$countWhile_u24default`.length();
   }

   var var9: Int = 0;
   var i: Int = RangesKt.coerceAtLeast(start, 0);

   for (int var7 = RangesKt.coerceAtMost(end, $this$countWhile_u24default.length()); i < var7 && predicate.invoke($this$countWhile_u24default.charAt(i)); i++) {
      var9++;
   }

   return var9;
}

public fun CharSequence.reverseView(): CharSequence {
   return new 1(`$this$reverseView`);
}

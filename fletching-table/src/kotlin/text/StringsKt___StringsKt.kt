package kotlin.text

import java.util.ArrayList
import java.util.Comparator
import java.util.HashSet
import java.util.LinkedHashMap
import java.util.LinkedHashSet
import java.util.NoSuchElementException
import kotlin.contracts.InvocationKind
import kotlin.internal.InlineOnly
import kotlin.jvm.functions.Function1
import kotlin.jvm.internal.Intrinsics
import kotlin.jvm.internal.SourceDebugExtension
import kotlin.random.Random
import kotlin.text.StringsKt___StringsKt.groupingBy.1

@SourceDebugExtension(["SMAP\n_Strings.kt\nKotlin\n*S Kotlin\n*F\n+ 1 _Strings.kt\nkotlin/text/StringsKt___StringsKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n*L\n1#1,2565:1\n130#1,2:2566\n221#1,5:2568\n507#1,5:2574\n507#1,5:2579\n467#1:2584\n1188#1,2:2585\n468#1,2:2587\n1190#1:2589\n470#1:2590\n467#1:2591\n1188#1,2:2592\n468#1,2:2594\n1190#1:2596\n470#1:2597\n1188#1,3:2598\n497#1,2:2601\n497#1,2:2603\n755#1,4:2605\n724#1,4:2609\n740#1,4:2613\n787#1,4:2617\n887#1,5:2621\n928#1,3:2626\n931#1,3:2636\n946#1,3:2639\n949#1,3:2649\n1046#1,3:2666\n1016#1,4:2669\n1005#1:2673\n1188#1,2:2674\n1190#1:2677\n1006#1:2678\n1188#1,3:2679\n1037#1:2682\n1179#1:2683\n1180#1:2685\n1038#1:2686\n1179#1,2:2687\n1188#1,3:2689\n2069#1,2:2692\n2071#1,6:2695\n2093#1,2:2701\n2095#1,6:2704\n2510#1,6:2710\n2540#1,7:2716\n1#2:2573\n1#2:2676\n1#2:2684\n1#2:2694\n1#2:2703\n382#3,7:2629\n382#3,7:2642\n382#3,7:2652\n382#3,7:2659\n*S KotlinDebug\n*F\n+ 1 _Strings.kt\nkotlin/text/StringsKt___StringsKt\n*L\n56#1:2566,2\n66#1:2568,5\n425#1:2574,5\n434#1:2579,5\n445#1:2584\n445#1:2585,2\n445#1:2587,2\n445#1:2589\n445#1:2590\n456#1:2591\n456#1:2592,2\n456#1:2594,2\n456#1:2596\n456#1:2597\n467#1:2598,3\n479#1:2601,2\n488#1:2603,2\n682#1:2605,4\n697#1:2609,4\n711#1:2613,4\n774#1:2617,4\n847#1:2621,5\n903#1:2626,3\n903#1:2636,3\n916#1:2639,3\n916#1:2649,3\n975#1:2666,3\n985#1:2669,4\n995#1:2673\n995#1:2674,2\n995#1:2677\n995#1:2678\n1005#1:2679,3\n1029#1:2682\n1029#1:2683\n1029#1:2685\n1029#1:2686\n1037#1:2687,2\n1875#1:2689,3\n2163#1:2692,2\n2163#1:2695,6\n2180#1:2701,2\n2180#1:2704,6\n2499#1:2710,6\n2527#1:2716,7\n995#1:2676\n1029#1:2684\n2163#1:2694\n2180#1:2703\n903#1:2629,7\n916#1:2642,7\n930#1:2652,7\n948#1:2659,7\n*E\n"])
internal class StringsKt___StringsKt : StringsKt___StringsJvmKt {
   @InlineOnly
   @JvmStatic
   public inline fun CharSequence.elementAtOrElse(index: Int, defaultValue: (Int) -> Char): Char {
      contract {
         callsInPlace(defaultValue, InvocationKind.AT_MOST_ONCE)
      }

      return if (0 <= index && index < `$this$elementAtOrElse`.length()) `$this$elementAtOrElse`.charAt(index) else defaultValue.invoke(index) as Character;
   }

   @InlineOnly
   @JvmStatic
   public inline fun CharSequence.elementAtOrNull(index: Int): Char? {
      return StringsKt.getOrNull(`$this$elementAtOrNull`, index);
   }

   @InlineOnly
   @JvmStatic
   public inline fun CharSequence.find(predicate: (Char) -> Boolean): Char? {
      val `$this$firstOrNull$iv`: java.lang.CharSequence = `$this$find`;
      var var4: Int = 0;

      var var10000: Character;
      while (true) {
         if (var4 >= `$this$firstOrNull$iv`.length()) {
            var10000 = null;
            break;
         }

         val `element$iv`: Char = `$this$firstOrNull$iv`.charAt(var4);
         if (predicate.invoke(`element$iv`) as java.lang.Boolean) {
            var10000 = `element$iv`;
            break;
         }

         var4++;
      }

      return var10000;
   }

   @InlineOnly
   @JvmStatic
   public inline fun CharSequence.findLast(predicate: (Char) -> Boolean): Char? {
      val `$this$lastOrNull$iv`: java.lang.CharSequence = `$this$findLast`;
      var var4: Int = `$this$findLast`.length() + -1;
      if (0 <= var4) {
         do {
            val `element$iv`: Char = `$this$lastOrNull$iv`.charAt(var4--);
            if (predicate.invoke(`element$iv`) as java.lang.Boolean) {
               return `element$iv`;
            }
         } while (0 <= var4);
      }

      return null;
   }

   @JvmStatic
   public fun CharSequence.first(): Char {
      if (`$this$first`.length() == 0) {
         throw new NoSuchElementException("Char sequence is empty.");
      } else {
         return `$this$first`.charAt(0);
      }
   }

   @JvmStatic
   public inline fun CharSequence.first(predicate: (Char) -> Boolean): Char {
      for (int var3 = 0; var3 < $this$first.length(); var3++) {
         val element: Char = `$this$first`.charAt(var3);
         if (predicate.invoke(element) as java.lang.Boolean) {
            return element;
         }
      }

      throw new NoSuchElementException("Char sequence contains no character matching the predicate.");
   }

   @SinceKotlin(version = "1.5")
   @InlineOnly
   @JvmStatic
   public inline fun <R : Any> CharSequence.firstNotNullOf(transform: (Char) -> R?): R {
      val var2: java.lang.CharSequence = `$this$firstNotNullOf`;
      var var3: Int = 0;

      var var10000: Any;
      while (true) {
         if (var3 >= var2.length()) {
            var10000 = null;
            break;
         }

         var10000 = transform.invoke(var2.charAt(var3));
         if (var10000 != null) {
            break;
         }

         var3++;
      }

      if (var10000 == null) {
         throw new NoSuchElementException("No element of the char sequence was transformed to a non-null value.");
      } else {
         return (R)var10000;
      }
   }

   @SinceKotlin(version = "1.5")
   @InlineOnly
   @JvmStatic
   public inline fun <R : Any> CharSequence.firstNotNullOfOrNull(transform: (Char) -> R?): R? {
      for (int var2 = 0; var2 < $this$firstNotNullOfOrNull.length(); var2++) {
         val result: Any = transform.invoke(`$this$firstNotNullOfOrNull`.charAt(var2));
         if (result != null) {
            return (R)result;
         }
      }

      return null;
   }

   @JvmStatic
   public fun CharSequence.firstOrNull(): Char? {
      return if (`$this$firstOrNull`.length() == 0) null else `$this$firstOrNull`.charAt(0);
   }

   @JvmStatic
   public inline fun CharSequence.firstOrNull(predicate: (Char) -> Boolean): Char? {
      for (int var3 = 0; var3 < $this$firstOrNull.length(); var3++) {
         val element: Char = `$this$firstOrNull`.charAt(var3);
         if (predicate.invoke(element) as java.lang.Boolean) {
            return element;
         }
      }

      return null;
   }

   @InlineOnly
   @JvmStatic
   public inline fun CharSequence.getOrElse(index: Int, defaultValue: (Int) -> Char): Char {
      contract {
         callsInPlace(defaultValue, InvocationKind.AT_MOST_ONCE)
      }

      return if (0 <= index && index < `$this$getOrElse`.length()) `$this$getOrElse`.charAt(index) else defaultValue.invoke(index) as Character;
   }

   @JvmStatic
   public fun CharSequence.getOrNull(index: Int): Char? {
      return if (0 <= index && index < `$this$getOrNull`.length()) `$this$getOrNull`.charAt(index) else null;
   }

   @JvmStatic
   public inline fun CharSequence.indexOfFirst(predicate: (Char) -> Boolean): Int {
      var index: Int = 0;

      for (int var4 = $this$indexOfFirst.length(); index < var4; index++) {
         if (predicate.invoke(`$this$indexOfFirst`.charAt(index)) as java.lang.Boolean) {
            return index;
         }
      }

      return -1;
   }

   @JvmStatic
   public inline fun CharSequence.indexOfLast(predicate: (Char) -> Boolean): Int {
      var var3: Int = `$this$indexOfLast`.length() + -1;
      if (0 <= var3) {
         do {
            val index: Int = var3--;
            if (predicate.invoke(`$this$indexOfLast`.charAt(index)) as java.lang.Boolean) {
               return index;
            }
         } while (0 <= var3);
      }

      return -1;
   }

   @JvmStatic
   public fun CharSequence.last(): Char {
      if (`$this$last`.length() == 0) {
         throw new NoSuchElementException("Char sequence is empty.");
      } else {
         return `$this$last`.charAt(StringsKt.getLastIndex(`$this$last`));
      }
   }

   @JvmStatic
   public inline fun CharSequence.last(predicate: (Char) -> Boolean): Char {
      var var3: Int = `$this$last`.length() + -1;
      if (0 <= var3) {
         do {
            val element: Char = `$this$last`.charAt(var3--);
            if (predicate.invoke(element) as java.lang.Boolean) {
               return element;
            }
         } while (0 <= var3);
      }

      throw new NoSuchElementException("Char sequence contains no character matching the predicate.");
   }

   @JvmStatic
   public fun CharSequence.lastOrNull(): Char? {
      return if (`$this$lastOrNull`.length() == 0) null else `$this$lastOrNull`.charAt(`$this$lastOrNull`.length() - 1);
   }

   @JvmStatic
   public inline fun CharSequence.lastOrNull(predicate: (Char) -> Boolean): Char? {
      var var3: Int = `$this$lastOrNull`.length() + -1;
      if (0 <= var3) {
         do {
            val element: Char = `$this$lastOrNull`.charAt(var3--);
            if (predicate.invoke(element) as java.lang.Boolean) {
               return element;
            }
         } while (0 <= var3);
      }

      return null;
   }

   @SinceKotlin(version = "1.3")
   @InlineOnly
   @JvmStatic
   public inline fun CharSequence.random(): Char {
      return StringsKt.random(`$this$random`, Random.Default);
   }

   @SinceKotlin(version = "1.3")
   @JvmStatic
   public fun CharSequence.random(random: Random): Char {
      if (`$this$random`.length() == 0) {
         throw new NoSuchElementException("Char sequence is empty.");
      } else {
         return `$this$random`.charAt(random.nextInt(`$this$random`.length()));
      }
   }

   @SinceKotlin(version = "1.4")
   @InlineOnly
   @JvmStatic
   public inline fun CharSequence.randomOrNull(): Char? {
      return StringsKt.randomOrNull(`$this$randomOrNull`, Random.Default);
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public fun CharSequence.randomOrNull(random: Random): Char? {
      return if (`$this$randomOrNull`.length() == 0) null else `$this$randomOrNull`.charAt(random.nextInt(`$this$randomOrNull`.length()));
   }

   @JvmStatic
   public fun CharSequence.single(): Char {
      switch ($this$single.length()) {
         case 0:
            throw new NoSuchElementException("Char sequence is empty.");
         case 1:
            return `$this$single`.charAt(0);
         default:
            throw new IllegalArgumentException("Char sequence has more than one element.");
      }
   }

   @JvmStatic
   public inline fun CharSequence.single(predicate: (Char) -> Boolean): Char {
      var single: Character = null;
      var found: Boolean = false;

      for (int var5 = 0; var5 < $this$single.length(); var5++) {
         val element: Char = `$this$single`.charAt(var5);
         if (predicate.invoke(element) as java.lang.Boolean) {
            if (found) {
               throw new IllegalArgumentException("Char sequence contains more than one matching element.");
            }

            single = element;
            found = true;
         }
      }

      if (!found) {
         throw new NoSuchElementException("Char sequence contains no character matching the predicate.");
      } else {
         return single;
      }
   }

   @JvmStatic
   public fun CharSequence.singleOrNull(): Char? {
      return if (`$this$singleOrNull`.length() == 1) `$this$singleOrNull`.charAt(0) else null;
   }

   @JvmStatic
   public inline fun CharSequence.singleOrNull(predicate: (Char) -> Boolean): Char? {
      var single: Character = null;
      var found: Boolean = false;

      for (int var5 = 0; var5 < $this$singleOrNull.length(); var5++) {
         val element: Char = `$this$singleOrNull`.charAt(var5);
         if (predicate.invoke(element) as java.lang.Boolean) {
            if (found) {
               return null;
            }

            single = element;
            found = true;
         }
      }

      return if (!found) null else single;
   }

   @JvmStatic
   public fun CharSequence.drop(n: Int): CharSequence {
      if (n < 0) {
         throw new IllegalArgumentException(("Requested character count $n is less than zero.").toString());
      } else {
         return `$this$drop`.subSequence(RangesKt.coerceAtMost(n, `$this$drop`.length()), `$this$drop`.length());
      }
   }

   @JvmStatic
   public fun String.drop(n: Int): String {
      if (n < 0) {
         throw new IllegalArgumentException(("Requested character count $n is less than zero.").toString());
      } else {
         val var10000: java.lang.String = `$this$drop`.substring(RangesKt.coerceAtMost(n, `$this$drop`.length()));
         return var10000;
      }
   }

   @JvmStatic
   public fun CharSequence.dropLast(n: Int): CharSequence {
      if (n < 0) {
         throw new IllegalArgumentException(("Requested character count $n is less than zero.").toString());
      } else {
         return StringsKt.take(`$this$dropLast`, RangesKt.coerceAtLeast(`$this$dropLast`.length() - n, 0));
      }
   }

   @JvmStatic
   public fun String.dropLast(n: Int): String {
      if (n < 0) {
         throw new IllegalArgumentException(("Requested character count $n is less than zero.").toString());
      } else {
         return StringsKt.take(`$this$dropLast`, RangesKt.coerceAtLeast(`$this$dropLast`.length() - n, 0));
      }
   }

   @JvmStatic
   public inline fun CharSequence.dropLastWhile(predicate: (Char) -> Boolean): CharSequence {
      for (int index = StringsKt.getLastIndex($this$dropLastWhile); -1 < index; index--) {
         if (!predicate.invoke(`$this$dropLastWhile`.charAt(index)) as java.lang.Boolean) {
            return `$this$dropLastWhile`.subSequence(0, index + 1);
         }
      }

      return "";
   }

   @JvmStatic
   public inline fun String.dropLastWhile(predicate: (Char) -> Boolean): String {
      for (int index = StringsKt.getLastIndex($this$dropLastWhile); -1 < index; index--) {
         if (!predicate.invoke(`$this$dropLastWhile`.charAt(index)) as java.lang.Boolean) {
            val var10000: java.lang.String = `$this$dropLastWhile`.substring(0, index + 1);
            return var10000;
         }
      }

      return "";
   }

   @JvmStatic
   public inline fun CharSequence.dropWhile(predicate: (Char) -> Boolean): CharSequence {
      var index: Int = 0;

      for (int var4 = $this$dropWhile.length(); index < var4; index++) {
         if (!predicate.invoke(`$this$dropWhile`.charAt(index)) as java.lang.Boolean) {
            return `$this$dropWhile`.subSequence(index, `$this$dropWhile`.length());
         }
      }

      return "";
   }

   @JvmStatic
   public inline fun String.dropWhile(predicate: (Char) -> Boolean): String {
      var index: Int = 0;

      for (int var4 = $this$dropWhile.length(); index < var4; index++) {
         if (!predicate.invoke(`$this$dropWhile`.charAt(index)) as java.lang.Boolean) {
            val var10000: java.lang.String = `$this$dropWhile`.substring(index);
            return var10000;
         }
      }

      return "";
   }

   @JvmStatic
   public inline fun CharSequence.filter(predicate: (Char) -> Boolean): CharSequence {
      val `$this$filterTo$iv`: java.lang.CharSequence = `$this$filter`;
      val `destination$iv`: Appendable = new StringBuilder();
      var `index$iv`: Int = 0;

      for (int var7 = $this$filter.length(); index$iv < var7; index$iv++) {
         val `element$iv`: Char = `$this$filterTo$iv`.charAt(`index$iv`);
         if (predicate.invoke(`element$iv`) as java.lang.Boolean) {
            `destination$iv`.append(`element$iv`);
         }
      }

      return `destination$iv` as java.lang.CharSequence;
   }

   @JvmStatic
   public inline fun String.filter(predicate: (Char) -> Boolean): String {
      val `$this$filterTo$iv`: java.lang.CharSequence = `$this$filter`;
      val `destination$iv`: Appendable = new StringBuilder();
      var `index$iv`: Int = 0;

      for (int var7 = $this$filterTo$iv.length(); index$iv < var7; index$iv++) {
         val `element$iv`: Char = `$this$filterTo$iv`.charAt(`index$iv`);
         if (predicate.invoke(`element$iv`) as java.lang.Boolean) {
            `destination$iv`.append(`element$iv`);
         }
      }

      return (`destination$iv` as StringBuilder).toString();
   }

   @JvmStatic
   public inline fun CharSequence.filterIndexed(predicate: (Int, Char) -> Boolean): CharSequence {
      val `destination$iv`: Appendable = new StringBuilder();
      val `$this$forEachIndexed$iv$iv`: java.lang.CharSequence = `$this$filterIndexed`;
      val `index$iv$iv`: Int = 0;

      for (int var9 = 0; var9 < $this$forEachIndexed$iv$iv.length(); var9++) {
         val `item$iv$iv`: Char = `$this$forEachIndexed$iv$iv`.charAt(var9);
         if (predicate.invoke(`index$iv$iv`++, `item$iv$iv`) as java.lang.Boolean) {
            `destination$iv`.append(`item$iv$iv`);
         }
      }

      return `destination$iv` as java.lang.CharSequence;
   }

   @JvmStatic
   public inline fun String.filterIndexed(predicate: (Int, Char) -> Boolean): String {
      val `$this$filterIndexedTo$iv`: java.lang.CharSequence = `$this$filterIndexed`;
      val `destination$iv`: Appendable = new StringBuilder();
      val `$this$forEachIndexed$iv$iv`: java.lang.CharSequence = `$this$filterIndexedTo$iv`;
      val `index$iv$iv`: Int = 0;

      for (int var9 = 0; var9 < $this$forEachIndexed$iv$iv.length(); var9++) {
         val `item$iv$iv`: Char = `$this$forEachIndexed$iv$iv`.charAt(var9);
         if (predicate.invoke(`index$iv$iv`++, `item$iv$iv`) as java.lang.Boolean) {
            `destination$iv`.append(`item$iv$iv`);
         }
      }

      return (`destination$iv` as StringBuilder).toString();
   }

   @JvmStatic
   public inline fun <C : Appendable> CharSequence.filterIndexedTo(destination: C, predicate: (Int, Char) -> Boolean): C {
      val `$this$forEachIndexed$iv`: java.lang.CharSequence = `$this$filterIndexedTo`;
      val `index$iv`: Int = 0;

      for (int var7 = 0; var7 < $this$forEachIndexed$iv.length(); var7++) {
         val `item$iv`: Char = `$this$forEachIndexed$iv`.charAt(var7);
         if (predicate.invoke(`index$iv`++, `item$iv`) as java.lang.Boolean) {
            destination.append(`item$iv`);
         }
      }

      return (C)destination;
   }

   @JvmStatic
   public inline fun CharSequence.filterNot(predicate: (Char) -> Boolean): CharSequence {
      val `$this$filterNotTo$iv`: java.lang.CharSequence = `$this$filterNot`;
      val `destination$iv`: Appendable = new StringBuilder();

      for (int var6 = 0; var6 < $this$filterNotTo$iv.length(); var6++) {
         val `element$iv`: Char = `$this$filterNotTo$iv`.charAt(var6);
         if (!predicate.invoke(`element$iv`) as java.lang.Boolean) {
            `destination$iv`.append(`element$iv`);
         }
      }

      return `destination$iv` as java.lang.CharSequence;
   }

   @JvmStatic
   public inline fun String.filterNot(predicate: (Char) -> Boolean): String {
      val `$this$filterNotTo$iv`: java.lang.CharSequence = `$this$filterNot`;
      val `destination$iv`: Appendable = new StringBuilder();

      for (int var6 = 0; var6 < $this$filterNotTo$iv.length(); var6++) {
         val `element$iv`: Char = `$this$filterNotTo$iv`.charAt(var6);
         if (!predicate.invoke(`element$iv`) as java.lang.Boolean) {
            `destination$iv`.append(`element$iv`);
         }
      }

      return (`destination$iv` as StringBuilder).toString();
   }

   @JvmStatic
   public inline fun <C : Appendable> CharSequence.filterNotTo(destination: C, predicate: (Char) -> Boolean): C {
      for (int var4 = 0; var4 < $this$filterNotTo.length(); var4++) {
         val element: Char = `$this$filterNotTo`.charAt(var4);
         if (!predicate.invoke(element) as java.lang.Boolean) {
            destination.append(element);
         }
      }

      return (C)destination;
   }

   @JvmStatic
   public inline fun <C : Appendable> CharSequence.filterTo(destination: C, predicate: (Char) -> Boolean): C {
      var index: Int = 0;

      for (int var5 = $this$filterTo.length(); index < var5; index++) {
         val element: Char = `$this$filterTo`.charAt(index);
         if (predicate.invoke(element) as java.lang.Boolean) {
            destination.append(element);
         }
      }

      return (C)destination;
   }

   @JvmStatic
   public fun CharSequence.slice(indices: IntRange): CharSequence {
      return if (indices.isEmpty()) "" else StringsKt.subSequence(`$this$slice`, indices);
   }

   @JvmStatic
   public fun String.slice(indices: IntRange): String {
      return if (indices.isEmpty()) "" else StringsKt.substring(`$this$slice`, indices);
   }

   @JvmStatic
   public fun CharSequence.slice(indices: Iterable<Int>): CharSequence {
      val size: Int = CollectionsKt.collectionSizeOrDefault(indices, 10);
      if (size == 0) {
         return "";
      } else {
         val result: StringBuilder = new StringBuilder(size);
         val var4: java.util.Iterator = indices.iterator();

         while (var4.hasNext()) {
            result.append(`$this$slice`.charAt((var4.next() as java.lang.Number).intValue()));
         }

         return result;
      }
   }

   @InlineOnly
   @JvmStatic
   public inline fun String.slice(indices: Iterable<Int>): String {
      return StringsKt.slice(`$this$slice`, indices).toString();
   }

   @JvmStatic
   public fun CharSequence.take(n: Int): CharSequence {
      if (n < 0) {
         throw new IllegalArgumentException(("Requested character count $n is less than zero.").toString());
      } else {
         return `$this$take`.subSequence(0, RangesKt.coerceAtMost(n, `$this$take`.length()));
      }
   }

   @JvmStatic
   public fun String.take(n: Int): String {
      if (n < 0) {
         throw new IllegalArgumentException(("Requested character count $n is less than zero.").toString());
      } else {
         val var10000: java.lang.String = `$this$take`.substring(0, RangesKt.coerceAtMost(n, `$this$take`.length()));
         return var10000;
      }
   }

   @JvmStatic
   public fun CharSequence.takeLast(n: Int): CharSequence {
      if (n < 0) {
         throw new IllegalArgumentException(("Requested character count $n is less than zero.").toString());
      } else {
         val length: Int = `$this$takeLast`.length();
         return `$this$takeLast`.subSequence(length - RangesKt.coerceAtMost(n, length), length);
      }
   }

   @JvmStatic
   public fun String.takeLast(n: Int): String {
      if (n < 0) {
         throw new IllegalArgumentException(("Requested character count $n is less than zero.").toString());
      } else {
         val length: Int = `$this$takeLast`.length();
         val var10000: java.lang.String = `$this$takeLast`.substring(length - RangesKt.coerceAtMost(n, length));
         return var10000;
      }
   }

   @JvmStatic
   public inline fun CharSequence.takeLastWhile(predicate: (Char) -> Boolean): CharSequence {
      for (int index = StringsKt.getLastIndex($this$takeLastWhile); -1 < index; index--) {
         if (!predicate.invoke(`$this$takeLastWhile`.charAt(index)) as java.lang.Boolean) {
            return `$this$takeLastWhile`.subSequence(index + 1, `$this$takeLastWhile`.length());
         }
      }

      return `$this$takeLastWhile`.subSequence(0, `$this$takeLastWhile`.length());
   }

   @JvmStatic
   public inline fun String.takeLastWhile(predicate: (Char) -> Boolean): String {
      for (int index = StringsKt.getLastIndex($this$takeLastWhile); -1 < index; index--) {
         if (!predicate.invoke(`$this$takeLastWhile`.charAt(index)) as java.lang.Boolean) {
            val var10000: java.lang.String = `$this$takeLastWhile`.substring(index + 1);
            return var10000;
         }
      }

      return `$this$takeLastWhile`;
   }

   @JvmStatic
   public inline fun CharSequence.takeWhile(predicate: (Char) -> Boolean): CharSequence {
      var index: Int = 0;

      for (int var4 = $this$takeWhile.length(); index < var4; index++) {
         if (!predicate.invoke(`$this$takeWhile`.charAt(index)) as java.lang.Boolean) {
            return `$this$takeWhile`.subSequence(0, index);
         }
      }

      return `$this$takeWhile`.subSequence(0, `$this$takeWhile`.length());
   }

   @JvmStatic
   public inline fun String.takeWhile(predicate: (Char) -> Boolean): String {
      var index: Int = 0;

      for (int var4 = $this$takeWhile.length(); index < var4; index++) {
         if (!predicate.invoke(`$this$takeWhile`.charAt(index)) as java.lang.Boolean) {
            val var10000: java.lang.String = `$this$takeWhile`.substring(0, index);
            return var10000;
         }
      }

      return `$this$takeWhile`;
   }

   @JvmStatic
   public fun CharSequence.reversed(): CharSequence {
      return new StringBuilder(`$this$reversed`).reverse();
   }

   @InlineOnly
   @JvmStatic
   public inline fun String.reversed(): String {
      return StringsKt.reversed(`$this$reversed`).toString();
   }

   @JvmStatic
   public inline fun <K, V> CharSequence.associate(transform: (Char) -> Pair<K, V>): Map<K, V> {
      val capacity: Int = RangesKt.coerceAtLeast(MapsKt.mapCapacity(`$this$associate`.length()), 16);
      val `$this$associateTo$iv`: java.lang.CharSequence = `$this$associate`;
      val `destination$iv`: java.util.Map = new LinkedHashMap(capacity);

      for (int var7 = 0; var7 < $this$associateTo$iv.length(); var7++) {
         val var10: Pair = transform.invoke(`$this$associateTo$iv`.charAt(var7)) as Pair;
         `destination$iv`.put(var10.getFirst(), var10.getSecond());
      }

      return `destination$iv`;
   }

   @JvmStatic
   public inline fun <K> CharSequence.associateBy(keySelector: (Char) -> K): Map<K, Char> {
      val capacity: Int = RangesKt.coerceAtLeast(MapsKt.mapCapacity(`$this$associateBy`.length()), 16);
      val `$this$associateByTo$iv`: java.lang.CharSequence = `$this$associateBy`;
      val `destination$iv`: java.util.Map = new LinkedHashMap(capacity);

      for (int var7 = 0; var7 < $this$associateByTo$iv.length(); var7++) {
         val `element$iv`: Char = `$this$associateByTo$iv`.charAt(var7);
         `destination$iv`.put(keySelector.invoke(`element$iv`), `element$iv`);
      }

      return `destination$iv`;
   }

   @JvmStatic
   public inline fun <K, V> CharSequence.associateBy(keySelector: (Char) -> K, valueTransform: (Char) -> V): Map<K, V> {
      val capacity: Int = RangesKt.coerceAtLeast(MapsKt.mapCapacity(`$this$associateBy`.length()), 16);
      val `$this$associateByTo$iv`: java.lang.CharSequence = `$this$associateBy`;
      val `destination$iv`: java.util.Map = new LinkedHashMap(capacity);

      for (int var8 = 0; var8 < $this$associateByTo$iv.length(); var8++) {
         val `element$iv`: Char = `$this$associateByTo$iv`.charAt(var8);
         `destination$iv`.put(keySelector.invoke(`element$iv`), valueTransform.invoke(`element$iv`));
      }

      return `destination$iv`;
   }

   @JvmStatic
   public inline fun <K, M : MutableMap<in K, in Char>> CharSequence.associateByTo(destination: M, keySelector: (Char) -> K): M {
      for (int var4 = 0; var4 < $this$associateByTo.length(); var4++) {
         val element: Char = `$this$associateByTo`.charAt(var4);
         destination.put(keySelector.invoke(element), element);
      }

      return (M)destination;
   }

   @JvmStatic
   public inline fun <K, V, M : MutableMap<in K, in V>> CharSequence.associateByTo(destination: M, keySelector: (Char) -> K, valueTransform: (Char) -> V): M {
      for (int var5 = 0; var5 < $this$associateByTo.length(); var5++) {
         val element: Char = `$this$associateByTo`.charAt(var5);
         destination.put(keySelector.invoke(element), valueTransform.invoke(element));
      }

      return (M)destination;
   }

   @JvmStatic
   public inline fun <K, V, M : MutableMap<in K, in V>> CharSequence.associateTo(destination: M, transform: (Char) -> Pair<K, V>): M {
      for (int var4 = 0; var4 < $this$associateTo.length(); var4++) {
         val var7: Pair = transform.invoke(`$this$associateTo`.charAt(var4)) as Pair;
         destination.put(var7.getFirst(), var7.getSecond());
      }

      return (M)destination;
   }

   @SinceKotlin(version = "1.3")
   @JvmStatic
   public inline fun <V> CharSequence.associateWith(valueSelector: (Char) -> V): Map<Char, V> {
      val result: LinkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(RangesKt.coerceAtMost(`$this$associateWith`.length(), 128)), 16));
      val `$this$associateWithTo$iv`: java.lang.CharSequence = `$this$associateWith`;
      val `destination$iv`: java.util.Map = result;

      for (int var7 = 0; var7 < $this$associateWithTo$iv.length(); var7++) {
         val `element$iv`: Char = `$this$associateWithTo$iv`.charAt(var7);
         `destination$iv`.put(`element$iv`, valueSelector.invoke(`element$iv`));
      }

      return `destination$iv`;
   }

   @SinceKotlin(version = "1.3")
   @JvmStatic
   public inline fun <V, M : MutableMap<in Char, in V>> CharSequence.associateWithTo(destination: M, valueSelector: (Char) -> V): M {
      for (int var4 = 0; var4 < $this$associateWithTo.length(); var4++) {
         val element: Char = `$this$associateWithTo`.charAt(var4);
         destination.put(element, valueSelector.invoke(element));
      }

      return (M)destination;
   }

   @JvmStatic
   public fun <C : MutableCollection<in Char>> CharSequence.toCollection(destination: C): C {
      for (int var2 = 0; var2 < $this$toCollection.length(); var2++) {
         destination.add(`$this$toCollection`.charAt(var2));
      }

      return (C)destination;
   }

   @JvmStatic
   public fun CharSequence.toHashSet(): HashSet<Char> {
      return StringsKt.toCollection(`$this$toHashSet`, new HashSet<>(MapsKt.mapCapacity(RangesKt.coerceAtMost(`$this$toHashSet`.length(), 128))));
   }

   @JvmStatic
   public fun CharSequence.toList(): List<Char> {
      var var10000: java.util.List;
      switch ($this$toList.length()) {
         case 0:
            var10000 = CollectionsKt.emptyList();
            break;
         case 1:
            var10000 = CollectionsKt.listOf(`$this$toList`.charAt(0));
            break;
         default:
            var10000 = StringsKt.toMutableList(`$this$toList`);
      }

      return var10000;
   }

   @JvmStatic
   public fun CharSequence.toMutableList(): MutableList<Char> {
      return StringsKt.toCollection(`$this$toMutableList`, new ArrayList<>(`$this$toMutableList`.length()));
   }

   @JvmStatic
   public fun CharSequence.toSet(): Set<Char> {
      var var10000: java.util.Set;
      switch ($this$toSet.length()) {
         case 0:
            var10000 = SetsKt.emptySet();
            break;
         case 1:
            var10000 = SetsKt.setOf(`$this$toSet`.charAt(0));
            break;
         default:
            var10000 = StringsKt.toCollection(`$this$toSet`, new LinkedHashSet(MapsKt.mapCapacity(RangesKt.coerceAtMost(`$this$toSet`.length(), 128))));
      }

      return var10000;
   }

   @JvmStatic
   public inline fun <R> CharSequence.flatMap(transform: (Char) -> Iterable<R>): List<R> {
      val `$this$flatMapTo$iv`: java.lang.CharSequence = `$this$flatMap`;
      val `destination$iv`: java.util.Collection = new ArrayList();

      for (int var6 = 0; var6 < $this$flatMapTo$iv.length(); var6++) {
         CollectionsKt.addAll(`destination$iv`, transform.invoke(`$this$flatMapTo$iv`.charAt(var6)) as java.lang.Iterable);
      }

      return `destination$iv` as MutableList<R>;
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @JvmName(name = "flatMapIndexedIterable")
   @InlineOnly
   @JvmStatic
   public inline fun <R> CharSequence.flatMapIndexed(transform: (Int, Char) -> Iterable<R>): List<R> {
      val var2: java.lang.CharSequence = `$this$flatMapIndexed`;
      val var3: java.util.Collection = new ArrayList();
      var var4: Int = 0;

      for (int var5 = 0; var5 < var2.length(); var5++) {
         CollectionsKt.addAll(var3, transform.invoke(var4++, var2.charAt(var5)) as java.lang.Iterable);
      }

      return var3 as MutableList<R>;
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @JvmName(name = "flatMapIndexedIterableTo")
   @InlineOnly
   @JvmStatic
   public inline fun <R, C : MutableCollection<in R>> CharSequence.flatMapIndexedTo(destination: C, transform: (Int, Char) -> Iterable<R>): C {
      var index: Int = 0;

      for (int var4 = 0; var4 < $this$flatMapIndexedTo.length(); var4++) {
         CollectionsKt.addAll(destination, transform.invoke(index++, `$this$flatMapIndexedTo`.charAt(var4)) as java.lang.Iterable);
      }

      return (C)destination;
   }

   @JvmStatic
   public inline fun <R, C : MutableCollection<in R>> CharSequence.flatMapTo(destination: C, transform: (Char) -> Iterable<R>): C {
      for (int var4 = 0; var4 < $this$flatMapTo.length(); var4++) {
         CollectionsKt.addAll(destination, transform.invoke(`$this$flatMapTo`.charAt(var4)) as java.lang.Iterable);
      }

      return (C)destination;
   }

   @JvmStatic
   public inline fun <K> CharSequence.groupBy(keySelector: (Char) -> K): Map<K, List<Char>> {
      val `$this$groupByTo$iv`: java.lang.CharSequence = `$this$groupBy`;
      val `destination$iv`: java.util.Map = new LinkedHashMap();

      for (int var6 = 0; var6 < $this$groupByTo$iv.length(); var6++) {
         val `element$iv`: Char = `$this$groupByTo$iv`.charAt(var6);
         val `key$iv`: Any = keySelector.invoke(`element$iv`);
         val `value$iv$iv`: Any = `destination$iv`.get(`key$iv`);
         val var10000: Any;
         if (`value$iv$iv` == null) {
            val var15: Any = new ArrayList();
            `destination$iv`.put(`key$iv`, var15);
            var10000 = var15;
         } else {
            var10000 = `value$iv$iv`;
         }

         (var10000 as java.util.List).add(`element$iv`);
      }

      return `destination$iv`;
   }

   @JvmStatic
   public inline fun <K, V> CharSequence.groupBy(keySelector: (Char) -> K, valueTransform: (Char) -> V): Map<K, List<V>> {
      val `$this$groupByTo$iv`: java.lang.CharSequence = `$this$groupBy`;
      val `destination$iv`: java.util.Map = new LinkedHashMap();

      for (int var7 = 0; var7 < $this$groupByTo$iv.length(); var7++) {
         val `element$iv`: Char = `$this$groupByTo$iv`.charAt(var7);
         val `key$iv`: Any = keySelector.invoke(`element$iv`);
         val `value$iv$iv`: Any = `destination$iv`.get(`key$iv`);
         val var10000: Any;
         if (`value$iv$iv` == null) {
            val var16: Any = new ArrayList();
            `destination$iv`.put(`key$iv`, var16);
            var10000 = var16;
         } else {
            var10000 = `value$iv$iv`;
         }

         (var10000 as java.util.List).add(valueTransform.invoke(`element$iv`));
      }

      return `destination$iv`;
   }

   @JvmStatic
   public inline fun <K, M : MutableMap<in K, MutableList<Char>>> CharSequence.groupByTo(destination: M, keySelector: (Char) -> K): M {
      for (int var4 = 0; var4 < $this$groupByTo.length(); var4++) {
         val element: Char = `$this$groupByTo`.charAt(var4);
         val key: Any = keySelector.invoke(element);
         val `value$iv`: Any = destination.get(key);
         val var10000: Any;
         if (`value$iv` == null) {
            val var13: Any = new ArrayList();
            destination.put(key, var13);
            var10000 = var13;
         } else {
            var10000 = `value$iv`;
         }

         (var10000 as java.util.List).add(element);
      }

      return (M)destination;
   }

   @JvmStatic
   public inline fun <K, V, M : MutableMap<in K, MutableList<V>>> CharSequence.groupByTo(destination: M, keySelector: (Char) -> K, valueTransform: (Char) -> V): M {
      for (int var5 = 0; var5 < $this$groupByTo.length(); var5++) {
         val element: Char = `$this$groupByTo`.charAt(var5);
         val key: Any = keySelector.invoke(element);
         val `value$iv`: Any = destination.get(key);
         val var10000: Any;
         if (`value$iv` == null) {
            val var14: Any = new ArrayList();
            destination.put(key, var14);
            var10000 = var14;
         } else {
            var10000 = `value$iv`;
         }

         (var10000 as java.util.List).add(valueTransform.invoke(element));
      }

      return (M)destination;
   }

   @SinceKotlin(version = "1.1")
   @JvmStatic
   public inline fun <K> CharSequence.groupingBy(crossinline keySelector: (Char) -> K): Grouping<Char, K> {
      return new 1(`$this$groupingBy`, keySelector);
   }

   @JvmStatic
   public inline fun <R> CharSequence.map(transform: (Char) -> R): List<R> {
      val `$this$mapTo$iv`: java.lang.CharSequence = `$this$map`;
      val `destination$iv`: java.util.Collection = new ArrayList(`$this$map`.length());

      for (int var6 = 0; var6 < $this$mapTo$iv.length(); var6++) {
         `destination$iv`.add(transform.invoke(`$this$mapTo$iv`.charAt(var6)));
      }

      return `destination$iv` as MutableList<R>;
   }

   @JvmStatic
   public inline fun <R> CharSequence.mapIndexed(transform: (Int, Char) -> R): List<R> {
      val `$this$mapIndexedTo$iv`: java.lang.CharSequence = `$this$mapIndexed`;
      val `destination$iv`: java.util.Collection = new ArrayList(`$this$mapIndexed`.length());
      var `index$iv`: Int = 0;

      for (int var7 = 0; var7 < $this$mapIndexedTo$iv.length(); var7++) {
         `destination$iv`.add(transform.invoke(`index$iv`++, `$this$mapIndexedTo$iv`.charAt(var7)));
      }

      return `destination$iv` as MutableList<R>;
   }

   @JvmStatic
   public inline fun <R : Any> CharSequence.mapIndexedNotNull(transform: (Int, Char) -> R?): List<R> {
      val `destination$iv`: java.util.Collection = new ArrayList();
      val `$this$forEachIndexed$iv$iv`: java.lang.CharSequence = `$this$mapIndexedNotNull`;
      var `index$iv$iv`: Int = 0;

      for (int var9 = 0; var9 < $this$forEachIndexed$iv$iv.length(); var9++) {
         val var16: Any = transform.invoke(`index$iv$iv`++, `$this$forEachIndexed$iv$iv`.charAt(var9));
         if (var16 != null) {
            `destination$iv`.add(var16);
         }
      }

      return `destination$iv` as MutableList<R>;
   }

   @JvmStatic
   public inline fun <R : Any, C : MutableCollection<in R>> CharSequence.mapIndexedNotNullTo(destination: C, transform: (Int, Char) -> R?): C {
      val `$this$forEachIndexed$iv`: java.lang.CharSequence = `$this$mapIndexedNotNullTo`;
      var `index$iv`: Int = 0;

      for (int var7 = 0; var7 < $this$forEachIndexed$iv.length(); var7++) {
         val var14: Any = transform.invoke(`index$iv`++, `$this$forEachIndexed$iv`.charAt(var7));
         if (var14 != null) {
            destination.add(var14);
         }
      }

      return (C)destination;
   }

   @JvmStatic
   public inline fun <R, C : MutableCollection<in R>> CharSequence.mapIndexedTo(destination: C, transform: (Int, Char) -> R): C {
      var index: Int = 0;

      for (int var5 = 0; var5 < $this$mapIndexedTo.length(); var5++) {
         destination.add(transform.invoke(index++, `$this$mapIndexedTo`.charAt(var5)));
      }

      return (C)destination;
   }

   @JvmStatic
   public inline fun <R : Any> CharSequence.mapNotNull(transform: (Char) -> R?): List<R> {
      val `destination$iv`: java.util.Collection = new ArrayList();
      val `$this$forEach$iv$iv`: java.lang.CharSequence = `$this$mapNotNull`;

      for (int var8 = 0; var8 < $this$forEach$iv$iv.length(); var8++) {
         val var10000: Any = transform.invoke(`$this$forEach$iv$iv`.charAt(var8));
         if (var10000 != null) {
            `destination$iv`.add(var10000);
         }
      }

      return `destination$iv` as MutableList<R>;
   }

   @JvmStatic
   public inline fun <R : Any, C : MutableCollection<in R>> CharSequence.mapNotNullTo(destination: C, transform: (Char) -> R?): C {
      val `$this$forEach$iv`: java.lang.CharSequence = `$this$mapNotNullTo`;

      for (int var6 = 0; var6 < $this$forEach$iv.length(); var6++) {
         val var10000: Any = transform.invoke(`$this$forEach$iv`.charAt(var6));
         if (var10000 != null) {
            destination.add(var10000);
         }
      }

      return (C)destination;
   }

   @JvmStatic
   public inline fun <R, C : MutableCollection<in R>> CharSequence.mapTo(destination: C, transform: (Char) -> R): C {
      for (int var4 = 0; var4 < $this$mapTo.length(); var4++) {
         destination.add(transform.invoke(`$this$mapTo`.charAt(var4)));
      }

      return (C)destination;
   }

   @JvmStatic
   public fun CharSequence.withIndex(): Iterable<IndexedValue<Char>> {
      return new IndexingIterable<>(StringsKt___StringsKt::withIndex$lambda$0$StringsKt___StringsKt);
   }

   @JvmStatic
   public inline fun CharSequence.all(predicate: (Char) -> Boolean): Boolean {
      for (int var3 = 0; var3 < $this$all.length(); var3++) {
         if (!predicate.invoke(`$this$all`.charAt(var3)) as java.lang.Boolean) {
            return false;
         }
      }

      return true;
   }

   @JvmStatic
   public fun CharSequence.any(): Boolean {
      return `$this$any`.length() != 0;
   }

   @JvmStatic
   public inline fun CharSequence.any(predicate: (Char) -> Boolean): Boolean {
      for (int var3 = 0; var3 < $this$any.length(); var3++) {
         if (predicate.invoke(`$this$any`.charAt(var3)) as java.lang.Boolean) {
            return true;
         }
      }

      return false;
   }

   @InlineOnly
   @JvmStatic
   public inline fun CharSequence.count(): Int {
      return `$this$count`.length();
   }

   @JvmStatic
   public inline fun CharSequence.count(predicate: (Char) -> Boolean): Int {
      var count: Int = 0;

      for (int var4 = 0; var4 < $this$count.length(); var4++) {
         if (predicate.invoke(`$this$count`.charAt(var4)) as java.lang.Boolean) {
            count++;
         }
      }

      return count;
   }

   @JvmStatic
   public inline fun <R> CharSequence.fold(initial: R, operation: (R, Char) -> R): R {
      var accumulator: Any = initial;

      for (int var5 = 0; var5 < $this$fold.length(); var5++) {
         accumulator = operation.invoke(accumulator, `$this$fold`.charAt(var5));
      }

      return (R)accumulator;
   }

   @JvmStatic
   public inline fun <R> CharSequence.foldIndexed(initial: R, operation: (Int, R, Char) -> R): R {
      var index: Int = 0;
      var accumulator: Any = initial;

      for (int var6 = 0; var6 < $this$foldIndexed.length(); var6++) {
         accumulator = operation.invoke(index++, accumulator, `$this$foldIndexed`.charAt(var6));
      }

      return (R)accumulator;
   }

   @JvmStatic
   public inline fun <R> CharSequence.foldRight(initial: R, operation: (Char, R) -> R): R {
      var index: Int = StringsKt.getLastIndex(`$this$foldRight`);
      var accumulator: Any = initial;

      while (index >= 0) {
         accumulator = operation.invoke(`$this$foldRight`.charAt(index--), accumulator);
      }

      return (R)accumulator;
   }

   @JvmStatic
   public inline fun <R> CharSequence.foldRightIndexed(initial: R, operation: (Int, Char, R) -> R): R {
      var index: Int = StringsKt.getLastIndex(`$this$foldRightIndexed`);

      var accumulator: Any;
      for (accumulator = initial; index >= 0; index--) {
         accumulator = operation.invoke(index, `$this$foldRightIndexed`.charAt(index), accumulator);
      }

      return (R)accumulator;
   }

   @JvmStatic
   public inline fun CharSequence.forEach(action: (Char) -> Unit) {
      for (int var3 = 0; var3 < $this$forEach.length(); var3++) {
         action.invoke(`$this$forEach`.charAt(var3));
      }
   }

   @JvmStatic
   public inline fun CharSequence.forEachIndexed(action: (Int, Char) -> Unit) {
      var index: Int = 0;

      for (int var4 = 0; var4 < $this$forEachIndexed.length(); var4++) {
         action.invoke(index++, `$this$forEachIndexed`.charAt(var4));
      }
   }

   @SinceKotlin(version = "1.7")
   @JvmName(name = "maxOrThrow")
   @JvmStatic
   public fun CharSequence.max(): Char {
      if (`$this$max`.length() == 0) {
         throw new NoSuchElementException();
      } else {
         var max: Char = `$this$max`.charAt(0);
         var i: Int = 1;
         val var3: Int = StringsKt.getLastIndex(`$this$max`);
         if (1 <= var3) {
            while (true) {
               val e: Char = `$this$max`.charAt(i);
               if (Intrinsics.compare((int)max, (int)e) < 0) {
                  max = e;
               }

               if (i == var3) {
                  break;
               }

               i++;
            }
         }

         return max;
      }
   }

   @SinceKotlin(version = "1.7")
   @JvmName(name = "maxByOrThrow")
   @JvmStatic
   public inline fun <R : Comparable<R>> CharSequence.maxBy(selector: (Char) -> R): Char {
      if (`$this$maxBy`.length() == 0) {
         throw new NoSuchElementException();
      } else {
         var maxElem: Char = `$this$maxBy`.charAt(0);
         val lastIndex: Int = StringsKt.getLastIndex(`$this$maxBy`);
         if (lastIndex == 0) {
            return maxElem;
         } else {
            var maxValue: java.lang.Comparable = selector.invoke(maxElem) as java.lang.Comparable;
            var i: Int = 1;
            if (1 <= lastIndex) {
               while (true) {
                  val e: Char = `$this$maxBy`.charAt(i);
                  val v: java.lang.Comparable = selector.invoke(e) as java.lang.Comparable;
                  if (maxValue.compareTo(v) < 0) {
                     maxElem = e;
                     maxValue = v;
                  }

                  if (i == lastIndex) {
                     break;
                  }

                  i++;
               }
            }

            return maxElem;
         }
      }
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public inline fun <R : Comparable<R>> CharSequence.maxByOrNull(selector: (Char) -> R): Char? {
      if (`$this$maxByOrNull`.length() == 0) {
         return null;
      } else {
         var maxElem: Char = `$this$maxByOrNull`.charAt(0);
         val lastIndex: Int = StringsKt.getLastIndex(`$this$maxByOrNull`);
         if (lastIndex == 0) {
            return maxElem;
         } else {
            var maxValue: java.lang.Comparable = selector.invoke(maxElem) as java.lang.Comparable;
            var i: Int = 1;
            if (1 <= lastIndex) {
               while (true) {
                  val e: Char = `$this$maxByOrNull`.charAt(i);
                  val v: java.lang.Comparable = selector.invoke(e) as java.lang.Comparable;
                  if (maxValue.compareTo(v) < 0) {
                     maxElem = e;
                     maxValue = v;
                  }

                  if (i == lastIndex) {
                     break;
                  }

                  i++;
               }
            }

            return maxElem;
         }
      }
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @InlineOnly
   @JvmStatic
   public inline fun CharSequence.maxOf(selector: (Char) -> Double): Double {
      if (`$this$maxOf`.length() == 0) {
         throw new NoSuchElementException();
      } else {
         var maxValue: Double = (selector.invoke(`$this$maxOf`.charAt(0)) as java.lang.Number).doubleValue();
         var i: Int = 1;
         val var5: Int = StringsKt.getLastIndex(`$this$maxOf`);
         if (1 <= var5) {
            while (true) {
               maxValue = Math.max(maxValue, (selector.invoke(`$this$maxOf`.charAt(i)) as java.lang.Number).doubleValue());
               if (i == var5) {
                  break;
               }

               i++;
            }
         }

         return maxValue;
      }
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @InlineOnly
   @JvmStatic
   public inline fun CharSequence.maxOf(selector: (Char) -> Float): Float {
      if (`$this$maxOf`.length() == 0) {
         throw new NoSuchElementException();
      } else {
         var maxValue: Float = (selector.invoke(`$this$maxOf`.charAt(0)) as java.lang.Number).floatValue();
         var i: Int = 1;
         val var4: Int = StringsKt.getLastIndex(`$this$maxOf`);
         if (1 <= var4) {
            while (true) {
               maxValue = Math.max(maxValue, (selector.invoke(`$this$maxOf`.charAt(i)) as java.lang.Number).floatValue());
               if (i == var4) {
                  break;
               }

               i++;
            }
         }

         return maxValue;
      }
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @InlineOnly
   @JvmStatic
   public inline fun <R : Comparable<R>> CharSequence.maxOf(selector: (Char) -> R): R {
      if (`$this$maxOf`.length() == 0) {
         throw new NoSuchElementException();
      } else {
         var maxValue: java.lang.Comparable = selector.invoke(`$this$maxOf`.charAt(0)) as java.lang.Comparable;
         var i: Int = 1;
         val var4: Int = StringsKt.getLastIndex(`$this$maxOf`);
         if (1 <= var4) {
            while (true) {
               val v: java.lang.Comparable = selector.invoke(`$this$maxOf`.charAt(i)) as java.lang.Comparable;
               if (maxValue.compareTo(v) < 0) {
                  maxValue = v;
               }

               if (i == var4) {
                  break;
               }

               i++;
            }
         }

         return (R)maxValue;
      }
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @InlineOnly
   @JvmStatic
   public inline fun CharSequence.maxOfOrNull(selector: (Char) -> Double): Double? {
      if (`$this$maxOfOrNull`.length() == 0) {
         return null;
      } else {
         var maxValue: Double = (selector.invoke(`$this$maxOfOrNull`.charAt(0)) as java.lang.Number).doubleValue();
         var i: Int = 1;
         val var5: Int = StringsKt.getLastIndex(`$this$maxOfOrNull`);
         if (1 <= var5) {
            while (true) {
               maxValue = Math.max(maxValue, (selector.invoke(`$this$maxOfOrNull`.charAt(i)) as java.lang.Number).doubleValue());
               if (i == var5) {
                  break;
               }

               i++;
            }
         }

         return maxValue;
      }
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @InlineOnly
   @JvmStatic
   public inline fun CharSequence.maxOfOrNull(selector: (Char) -> Float): Float? {
      if (`$this$maxOfOrNull`.length() == 0) {
         return null;
      } else {
         var maxValue: Float = (selector.invoke(`$this$maxOfOrNull`.charAt(0)) as java.lang.Number).floatValue();
         var i: Int = 1;
         val var4: Int = StringsKt.getLastIndex(`$this$maxOfOrNull`);
         if (1 <= var4) {
            while (true) {
               maxValue = Math.max(maxValue, (selector.invoke(`$this$maxOfOrNull`.charAt(i)) as java.lang.Number).floatValue());
               if (i == var4) {
                  break;
               }

               i++;
            }
         }

         return maxValue;
      }
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @InlineOnly
   @JvmStatic
   public inline fun <R : Comparable<R>> CharSequence.maxOfOrNull(selector: (Char) -> R): R? {
      if (`$this$maxOfOrNull`.length() == 0) {
         return null;
      } else {
         var maxValue: java.lang.Comparable = selector.invoke(`$this$maxOfOrNull`.charAt(0)) as java.lang.Comparable;
         var i: Int = 1;
         val var4: Int = StringsKt.getLastIndex(`$this$maxOfOrNull`);
         if (1 <= var4) {
            while (true) {
               val v: java.lang.Comparable = selector.invoke(`$this$maxOfOrNull`.charAt(i)) as java.lang.Comparable;
               if (maxValue.compareTo(v) < 0) {
                  maxValue = v;
               }

               if (i == var4) {
                  break;
               }

               i++;
            }
         }

         return (R)maxValue;
      }
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @InlineOnly
   @JvmStatic
   public inline fun <R> CharSequence.maxOfWith(comparator: Comparator<in R>, selector: (Char) -> R): R {
      if (`$this$maxOfWith`.length() == 0) {
         throw new NoSuchElementException();
      } else {
         var maxValue: Any = selector.invoke(`$this$maxOfWith`.charAt(0));
         var i: Int = 1;
         val var5: Int = StringsKt.getLastIndex(`$this$maxOfWith`);
         if (1 <= var5) {
            while (true) {
               val v: Any = selector.invoke(`$this$maxOfWith`.charAt(i));
               if (comparator.compare(maxValue, v) < 0) {
                  maxValue = v;
               }

               if (i == var5) {
                  break;
               }

               i++;
            }
         }

         return (R)maxValue;
      }
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @InlineOnly
   @JvmStatic
   public inline fun <R> CharSequence.maxOfWithOrNull(comparator: Comparator<in R>, selector: (Char) -> R): R? {
      if (`$this$maxOfWithOrNull`.length() == 0) {
         return null;
      } else {
         var maxValue: Any = selector.invoke(`$this$maxOfWithOrNull`.charAt(0));
         var i: Int = 1;
         val var5: Int = StringsKt.getLastIndex(`$this$maxOfWithOrNull`);
         if (1 <= var5) {
            while (true) {
               val v: Any = selector.invoke(`$this$maxOfWithOrNull`.charAt(i));
               if (comparator.compare(maxValue, v) < 0) {
                  maxValue = v;
               }

               if (i == var5) {
                  break;
               }

               i++;
            }
         }

         return (R)maxValue;
      }
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public fun CharSequence.maxOrNull(): Char? {
      if (`$this$maxOrNull`.length() == 0) {
         return null;
      } else {
         var max: Char = `$this$maxOrNull`.charAt(0);
         var i: Int = 1;
         val var3: Int = StringsKt.getLastIndex(`$this$maxOrNull`);
         if (1 <= var3) {
            while (true) {
               val e: Char = `$this$maxOrNull`.charAt(i);
               if (Intrinsics.compare((int)max, (int)e) < 0) {
                  max = e;
               }

               if (i == var3) {
                  break;
               }

               i++;
            }
         }

         return max;
      }
   }

   @SinceKotlin(version = "1.7")
   @JvmName(name = "maxWithOrThrow")
   @JvmStatic
   public fun CharSequence.maxWith(comparator: Comparator<in Char>): Char {
      if (`$this$maxWith`.length() == 0) {
         throw new NoSuchElementException();
      } else {
         var max: Char = `$this$maxWith`.charAt(0);
         var i: Int = 1;
         val var4: Int = StringsKt.getLastIndex(`$this$maxWith`);
         if (1 <= var4) {
            while (true) {
               val e: Char = `$this$maxWith`.charAt(i);
               if (comparator.compare(max, e) < 0) {
                  max = e;
               }

               if (i == var4) {
                  break;
               }

               i++;
            }
         }

         return max;
      }
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public fun CharSequence.maxWithOrNull(comparator: Comparator<in Char>): Char? {
      if (`$this$maxWithOrNull`.length() == 0) {
         return null;
      } else {
         var max: Char = `$this$maxWithOrNull`.charAt(0);
         var i: Int = 1;
         val var4: Int = StringsKt.getLastIndex(`$this$maxWithOrNull`);
         if (1 <= var4) {
            while (true) {
               val e: Char = `$this$maxWithOrNull`.charAt(i);
               if (comparator.compare(max, e) < 0) {
                  max = e;
               }

               if (i == var4) {
                  break;
               }

               i++;
            }
         }

         return max;
      }
   }

   @SinceKotlin(version = "1.7")
   @JvmName(name = "minOrThrow")
   @JvmStatic
   public fun CharSequence.min(): Char {
      if (`$this$min`.length() == 0) {
         throw new NoSuchElementException();
      } else {
         var min: Char = `$this$min`.charAt(0);
         var i: Int = 1;
         val var3: Int = StringsKt.getLastIndex(`$this$min`);
         if (1 <= var3) {
            while (true) {
               val e: Char = `$this$min`.charAt(i);
               if (Intrinsics.compare((int)min, (int)e) > 0) {
                  min = e;
               }

               if (i == var3) {
                  break;
               }

               i++;
            }
         }

         return min;
      }
   }

   @SinceKotlin(version = "1.7")
   @JvmName(name = "minByOrThrow")
   @JvmStatic
   public inline fun <R : Comparable<R>> CharSequence.minBy(selector: (Char) -> R): Char {
      if (`$this$minBy`.length() == 0) {
         throw new NoSuchElementException();
      } else {
         var minElem: Char = `$this$minBy`.charAt(0);
         val lastIndex: Int = StringsKt.getLastIndex(`$this$minBy`);
         if (lastIndex == 0) {
            return minElem;
         } else {
            var minValue: java.lang.Comparable = selector.invoke(minElem) as java.lang.Comparable;
            var i: Int = 1;
            if (1 <= lastIndex) {
               while (true) {
                  val e: Char = `$this$minBy`.charAt(i);
                  val v: java.lang.Comparable = selector.invoke(e) as java.lang.Comparable;
                  if (minValue.compareTo(v) > 0) {
                     minElem = e;
                     minValue = v;
                  }

                  if (i == lastIndex) {
                     break;
                  }

                  i++;
               }
            }

            return minElem;
         }
      }
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public inline fun <R : Comparable<R>> CharSequence.minByOrNull(selector: (Char) -> R): Char? {
      if (`$this$minByOrNull`.length() == 0) {
         return null;
      } else {
         var minElem: Char = `$this$minByOrNull`.charAt(0);
         val lastIndex: Int = StringsKt.getLastIndex(`$this$minByOrNull`);
         if (lastIndex == 0) {
            return minElem;
         } else {
            var minValue: java.lang.Comparable = selector.invoke(minElem) as java.lang.Comparable;
            var i: Int = 1;
            if (1 <= lastIndex) {
               while (true) {
                  val e: Char = `$this$minByOrNull`.charAt(i);
                  val v: java.lang.Comparable = selector.invoke(e) as java.lang.Comparable;
                  if (minValue.compareTo(v) > 0) {
                     minElem = e;
                     minValue = v;
                  }

                  if (i == lastIndex) {
                     break;
                  }

                  i++;
               }
            }

            return minElem;
         }
      }
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @InlineOnly
   @JvmStatic
   public inline fun CharSequence.minOf(selector: (Char) -> Double): Double {
      if (`$this$minOf`.length() == 0) {
         throw new NoSuchElementException();
      } else {
         var minValue: Double = (selector.invoke(`$this$minOf`.charAt(0)) as java.lang.Number).doubleValue();
         var i: Int = 1;
         val var5: Int = StringsKt.getLastIndex(`$this$minOf`);
         if (1 <= var5) {
            while (true) {
               minValue = Math.min(minValue, (selector.invoke(`$this$minOf`.charAt(i)) as java.lang.Number).doubleValue());
               if (i == var5) {
                  break;
               }

               i++;
            }
         }

         return minValue;
      }
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @InlineOnly
   @JvmStatic
   public inline fun CharSequence.minOf(selector: (Char) -> Float): Float {
      if (`$this$minOf`.length() == 0) {
         throw new NoSuchElementException();
      } else {
         var minValue: Float = (selector.invoke(`$this$minOf`.charAt(0)) as java.lang.Number).floatValue();
         var i: Int = 1;
         val var4: Int = StringsKt.getLastIndex(`$this$minOf`);
         if (1 <= var4) {
            while (true) {
               minValue = Math.min(minValue, (selector.invoke(`$this$minOf`.charAt(i)) as java.lang.Number).floatValue());
               if (i == var4) {
                  break;
               }

               i++;
            }
         }

         return minValue;
      }
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @InlineOnly
   @JvmStatic
   public inline fun <R : Comparable<R>> CharSequence.minOf(selector: (Char) -> R): R {
      if (`$this$minOf`.length() == 0) {
         throw new NoSuchElementException();
      } else {
         var minValue: java.lang.Comparable = selector.invoke(`$this$minOf`.charAt(0)) as java.lang.Comparable;
         var i: Int = 1;
         val var4: Int = StringsKt.getLastIndex(`$this$minOf`);
         if (1 <= var4) {
            while (true) {
               val v: java.lang.Comparable = selector.invoke(`$this$minOf`.charAt(i)) as java.lang.Comparable;
               if (minValue.compareTo(v) > 0) {
                  minValue = v;
               }

               if (i == var4) {
                  break;
               }

               i++;
            }
         }

         return (R)minValue;
      }
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @InlineOnly
   @JvmStatic
   public inline fun CharSequence.minOfOrNull(selector: (Char) -> Double): Double? {
      if (`$this$minOfOrNull`.length() == 0) {
         return null;
      } else {
         var minValue: Double = (selector.invoke(`$this$minOfOrNull`.charAt(0)) as java.lang.Number).doubleValue();
         var i: Int = 1;
         val var5: Int = StringsKt.getLastIndex(`$this$minOfOrNull`);
         if (1 <= var5) {
            while (true) {
               minValue = Math.min(minValue, (selector.invoke(`$this$minOfOrNull`.charAt(i)) as java.lang.Number).doubleValue());
               if (i == var5) {
                  break;
               }

               i++;
            }
         }

         return minValue;
      }
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @InlineOnly
   @JvmStatic
   public inline fun CharSequence.minOfOrNull(selector: (Char) -> Float): Float? {
      if (`$this$minOfOrNull`.length() == 0) {
         return null;
      } else {
         var minValue: Float = (selector.invoke(`$this$minOfOrNull`.charAt(0)) as java.lang.Number).floatValue();
         var i: Int = 1;
         val var4: Int = StringsKt.getLastIndex(`$this$minOfOrNull`);
         if (1 <= var4) {
            while (true) {
               minValue = Math.min(minValue, (selector.invoke(`$this$minOfOrNull`.charAt(i)) as java.lang.Number).floatValue());
               if (i == var4) {
                  break;
               }

               i++;
            }
         }

         return minValue;
      }
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @InlineOnly
   @JvmStatic
   public inline fun <R : Comparable<R>> CharSequence.minOfOrNull(selector: (Char) -> R): R? {
      if (`$this$minOfOrNull`.length() == 0) {
         return null;
      } else {
         var minValue: java.lang.Comparable = selector.invoke(`$this$minOfOrNull`.charAt(0)) as java.lang.Comparable;
         var i: Int = 1;
         val var4: Int = StringsKt.getLastIndex(`$this$minOfOrNull`);
         if (1 <= var4) {
            while (true) {
               val v: java.lang.Comparable = selector.invoke(`$this$minOfOrNull`.charAt(i)) as java.lang.Comparable;
               if (minValue.compareTo(v) > 0) {
                  minValue = v;
               }

               if (i == var4) {
                  break;
               }

               i++;
            }
         }

         return (R)minValue;
      }
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @InlineOnly
   @JvmStatic
   public inline fun <R> CharSequence.minOfWith(comparator: Comparator<in R>, selector: (Char) -> R): R {
      if (`$this$minOfWith`.length() == 0) {
         throw new NoSuchElementException();
      } else {
         var minValue: Any = selector.invoke(`$this$minOfWith`.charAt(0));
         var i: Int = 1;
         val var5: Int = StringsKt.getLastIndex(`$this$minOfWith`);
         if (1 <= var5) {
            while (true) {
               val v: Any = selector.invoke(`$this$minOfWith`.charAt(i));
               if (comparator.compare(minValue, v) > 0) {
                  minValue = v;
               }

               if (i == var5) {
                  break;
               }

               i++;
            }
         }

         return (R)minValue;
      }
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @InlineOnly
   @JvmStatic
   public inline fun <R> CharSequence.minOfWithOrNull(comparator: Comparator<in R>, selector: (Char) -> R): R? {
      if (`$this$minOfWithOrNull`.length() == 0) {
         return null;
      } else {
         var minValue: Any = selector.invoke(`$this$minOfWithOrNull`.charAt(0));
         var i: Int = 1;
         val var5: Int = StringsKt.getLastIndex(`$this$minOfWithOrNull`);
         if (1 <= var5) {
            while (true) {
               val v: Any = selector.invoke(`$this$minOfWithOrNull`.charAt(i));
               if (comparator.compare(minValue, v) > 0) {
                  minValue = v;
               }

               if (i == var5) {
                  break;
               }

               i++;
            }
         }

         return (R)minValue;
      }
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public fun CharSequence.minOrNull(): Char? {
      if (`$this$minOrNull`.length() == 0) {
         return null;
      } else {
         var min: Char = `$this$minOrNull`.charAt(0);
         var i: Int = 1;
         val var3: Int = StringsKt.getLastIndex(`$this$minOrNull`);
         if (1 <= var3) {
            while (true) {
               val e: Char = `$this$minOrNull`.charAt(i);
               if (Intrinsics.compare((int)min, (int)e) > 0) {
                  min = e;
               }

               if (i == var3) {
                  break;
               }

               i++;
            }
         }

         return min;
      }
   }

   @SinceKotlin(version = "1.7")
   @JvmName(name = "minWithOrThrow")
   @JvmStatic
   public fun CharSequence.minWith(comparator: Comparator<in Char>): Char {
      if (`$this$minWith`.length() == 0) {
         throw new NoSuchElementException();
      } else {
         var min: Char = `$this$minWith`.charAt(0);
         var i: Int = 1;
         val var4: Int = StringsKt.getLastIndex(`$this$minWith`);
         if (1 <= var4) {
            while (true) {
               val e: Char = `$this$minWith`.charAt(i);
               if (comparator.compare(min, e) > 0) {
                  min = e;
               }

               if (i == var4) {
                  break;
               }

               i++;
            }
         }

         return min;
      }
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public fun CharSequence.minWithOrNull(comparator: Comparator<in Char>): Char? {
      if (`$this$minWithOrNull`.length() == 0) {
         return null;
      } else {
         var min: Char = `$this$minWithOrNull`.charAt(0);
         var i: Int = 1;
         val var4: Int = StringsKt.getLastIndex(`$this$minWithOrNull`);
         if (1 <= var4) {
            while (true) {
               val e: Char = `$this$minWithOrNull`.charAt(i);
               if (comparator.compare(min, e) > 0) {
                  min = e;
               }

               if (i == var4) {
                  break;
               }

               i++;
            }
         }

         return min;
      }
   }

   @JvmStatic
   public fun CharSequence.none(): Boolean {
      return `$this$none`.length() == 0;
   }

   @JvmStatic
   public inline fun CharSequence.none(predicate: (Char) -> Boolean): Boolean {
      for (int var3 = 0; var3 < $this$none.length(); var3++) {
         if (predicate.invoke(`$this$none`.charAt(var3)) as java.lang.Boolean) {
            return false;
         }
      }

      return true;
   }

   @SinceKotlin(version = "1.1")
   @JvmStatic
   public inline fun <S : CharSequence> S.onEach(action: (Char) -> Unit): S {
      val `$this$onEach_u24lambda_u240`: java.lang.CharSequence = `$this$onEach`;

      for (int var6 = 0; var6 < $this$onEach_u24lambda_u240.length(); var6++) {
         action.invoke(`$this$onEach_u24lambda_u240`.charAt(var6));
      }

      return (S)`$this$onEach`;
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public inline fun <S : CharSequence> S.onEachIndexed(action: (Int, Char) -> Unit): S {
      val `$this$forEachIndexed$iv`: java.lang.CharSequence = `$this$onEachIndexed`;
      var `index$iv`: Int = 0;

      for (int var9 = 0; var9 < $this$forEachIndexed$iv.length(); var9++) {
         action.invoke(`index$iv`++, `$this$forEachIndexed$iv`.charAt(var9));
      }

      return (S)`$this$onEachIndexed`;
   }

   @JvmStatic
   public inline fun CharSequence.reduce(operation: (Char, Char) -> Char): Char {
      if (`$this$reduce`.length() == 0) {
         throw new UnsupportedOperationException("Empty char sequence can't be reduced.");
      } else {
         var accumulator: Char = `$this$reduce`.charAt(0);
         var index: Int = 1;
         val var5: Int = StringsKt.getLastIndex(`$this$reduce`);
         if (1 <= var5) {
            while (true) {
               accumulator = operation.invoke(accumulator, `$this$reduce`.charAt(index)) as Character;
               if (index == var5) {
                  break;
               }

               index++;
            }
         }

         return accumulator;
      }
   }

   @JvmStatic
   public inline fun CharSequence.reduceIndexed(operation: (Int, Char, Char) -> Char): Char {
      if (`$this$reduceIndexed`.length() == 0) {
         throw new UnsupportedOperationException("Empty char sequence can't be reduced.");
      } else {
         var accumulator: Char = `$this$reduceIndexed`.charAt(0);
         var index: Int = 1;
         val var5: Int = StringsKt.getLastIndex(`$this$reduceIndexed`);
         if (1 <= var5) {
            while (true) {
               accumulator = operation.invoke(index, accumulator, `$this$reduceIndexed`.charAt(index)) as Character;
               if (index == var5) {
                  break;
               }

               index++;
            }
         }

         return accumulator;
      }
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public inline fun CharSequence.reduceIndexedOrNull(operation: (Int, Char, Char) -> Char): Char? {
      if (`$this$reduceIndexedOrNull`.length() == 0) {
         return null;
      } else {
         var accumulator: Char = `$this$reduceIndexedOrNull`.charAt(0);
         var index: Int = 1;
         val var5: Int = StringsKt.getLastIndex(`$this$reduceIndexedOrNull`);
         if (1 <= var5) {
            while (true) {
               accumulator = operation.invoke(index, accumulator, `$this$reduceIndexedOrNull`.charAt(index)) as Character;
               if (index == var5) {
                  break;
               }

               index++;
            }
         }

         return accumulator;
      }
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public inline fun CharSequence.reduceOrNull(operation: (Char, Char) -> Char): Char? {
      if (`$this$reduceOrNull`.length() == 0) {
         return null;
      } else {
         var accumulator: Char = `$this$reduceOrNull`.charAt(0);
         var index: Int = 1;
         val var5: Int = StringsKt.getLastIndex(`$this$reduceOrNull`);
         if (1 <= var5) {
            while (true) {
               accumulator = operation.invoke(accumulator, `$this$reduceOrNull`.charAt(index)) as Character;
               if (index == var5) {
                  break;
               }

               index++;
            }
         }

         return accumulator;
      }
   }

   @JvmStatic
   public inline fun CharSequence.reduceRight(operation: (Char, Char) -> Char): Char {
      var index: Int = StringsKt.getLastIndex(`$this$reduceRight`);
      if (index < 0) {
         throw new UnsupportedOperationException("Empty char sequence can't be reduced.");
      } else {
         var accumulator: Char = `$this$reduceRight`.charAt(index--);

         while (index >= 0) {
            accumulator = operation.invoke(`$this$reduceRight`.charAt(index--), accumulator) as Character;
         }

         return accumulator;
      }
   }

   @JvmStatic
   public inline fun CharSequence.reduceRightIndexed(operation: (Int, Char, Char) -> Char): Char {
      var index: Int = StringsKt.getLastIndex(`$this$reduceRightIndexed`);
      if (index < 0) {
         throw new UnsupportedOperationException("Empty char sequence can't be reduced.");
      } else {
         var accumulator: Char;
         for (accumulator = $this$reduceRightIndexed.charAt(index--); index >= 0; index--) {
            accumulator = operation.invoke(index, `$this$reduceRightIndexed`.charAt(index), accumulator) as Character;
         }

         return accumulator;
      }
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public inline fun CharSequence.reduceRightIndexedOrNull(operation: (Int, Char, Char) -> Char): Char? {
      var index: Int = StringsKt.getLastIndex(`$this$reduceRightIndexedOrNull`);
      if (index < 0) {
         return null;
      } else {
         var accumulator: Char;
         for (accumulator = $this$reduceRightIndexedOrNull.charAt(index--); index >= 0; index--) {
            accumulator = operation.invoke(index, `$this$reduceRightIndexedOrNull`.charAt(index), accumulator) as Character;
         }

         return accumulator;
      }
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public inline fun CharSequence.reduceRightOrNull(operation: (Char, Char) -> Char): Char? {
      var index: Int = StringsKt.getLastIndex(`$this$reduceRightOrNull`);
      if (index < 0) {
         return null;
      } else {
         var accumulator: Char = `$this$reduceRightOrNull`.charAt(index--);

         while (index >= 0) {
            accumulator = operation.invoke(`$this$reduceRightOrNull`.charAt(index--), accumulator) as Character;
         }

         return accumulator;
      }
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public inline fun <R> CharSequence.runningFold(initial: R, operation: (R, Char) -> R): List<R> {
      if (`$this$runningFold`.length() == 0) {
         return (java.util.List<R>)CollectionsKt.listOf(initial);
      } else {
         val accumulator: ArrayList = new ArrayList(`$this$runningFold`.length() + 1);
         accumulator.add(initial);
         val result: ArrayList = accumulator;
         var var8: Any = initial;

         for (int $this$runningFold_u24lambda_u240 = 0; $this$runningFold_u24lambda_u240 < $this$runningFold.length(); $this$runningFold_u24lambda_u240++) {
            var8 = operation.invoke(var8, `$this$runningFold`.charAt(`$this$runningFold_u24lambda_u240`));
            result.add(var8);
         }

         return result;
      }
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public inline fun <R> CharSequence.runningFoldIndexed(initial: R, operation: (Int, R, Char) -> R): List<R> {
      if (`$this$runningFoldIndexed`.length() == 0) {
         return (java.util.List<R>)CollectionsKt.listOf(initial);
      } else {
         val accumulator: ArrayList = new ArrayList(`$this$runningFoldIndexed`.length() + 1);
         accumulator.add(initial);
         val result: ArrayList = accumulator;
         var var8: Any = initial;
         var index: Int = 0;

         for (int var9 = $this$runningFoldIndexed.length(); index < var9; index++) {
            var8 = operation.invoke(index, var8, `$this$runningFoldIndexed`.charAt(index));
            result.add(var8);
         }

         return result;
      }
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public inline fun CharSequence.runningReduce(operation: (Char, Char) -> Char): List<Char> {
      if (`$this$runningReduce`.length() == 0) {
         return CollectionsKt.emptyList();
      } else {
         var var8: Char = `$this$runningReduce`.charAt(0);
         val index: ArrayList = new ArrayList(`$this$runningReduce`.length());
         index.add(var8);
         val result: ArrayList = index;
         var var9: Int = 1;

         for (int $this$runningReduce_u24lambda_u240 = $this$runningReduce.length(); index < $this$runningReduce_u24lambda_u240; index++) {
            var8 = operation.invoke(var8, `$this$runningReduce`.charAt(var9)) as Character;
            result.add(var8);
         }

         return result;
      }
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public inline fun CharSequence.runningReduceIndexed(operation: (Int, Char, Char) -> Char): List<Char> {
      if (`$this$runningReduceIndexed`.length() == 0) {
         return CollectionsKt.emptyList();
      } else {
         var var8: Char = `$this$runningReduceIndexed`.charAt(0);
         val index: ArrayList = new ArrayList(`$this$runningReduceIndexed`.length());
         index.add(var8);
         val result: ArrayList = index;
         var var9: Int = 1;

         for (int $this$runningReduceIndexed_u24lambda_u240 = $this$runningReduceIndexed.length(); index < $this$runningReduceIndexed_u24lambda_u240; index++) {
            var8 = operation.invoke(var9, var8, `$this$runningReduceIndexed`.charAt(var9)) as Character;
            result.add(var8);
         }

         return result;
      }
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public inline fun <R> CharSequence.scan(initial: R, operation: (R, Char) -> R): List<R> {
      val `$this$runningFold$iv`: java.lang.CharSequence = `$this$scan`;
      val var10000: java.util.List;
      if (`$this$scan`.length() == 0) {
         var10000 = CollectionsKt.listOf(initial);
      } else {
         val `accumulator$iv`: ArrayList = new ArrayList(`$this$scan`.length() + 1);
         `accumulator$iv`.add(initial);
         val `result$iv`: ArrayList = `accumulator$iv`;
         var var11: Any = initial;

         for (int $this$runningFold_u24lambda_u240$iv = 0;
            $this$runningFold_u24lambda_u240$iv < $this$runningFold$iv.length();
            $this$runningFold_u24lambda_u240$iv++
         ) {
            var11 = operation.invoke(var11, `$this$runningFold$iv`.charAt(`$this$runningFold_u24lambda_u240$iv`));
            `result$iv`.add(var11);
         }

         var10000 = `result$iv`;
      }

      return var10000;
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public inline fun <R> CharSequence.scanIndexed(initial: R, operation: (Int, R, Char) -> R): List<R> {
      val `$this$runningFoldIndexed$iv`: java.lang.CharSequence = `$this$scanIndexed`;
      val var10000: java.util.List;
      if (`$this$scanIndexed`.length() == 0) {
         var10000 = CollectionsKt.listOf(initial);
      } else {
         val `accumulator$iv`: ArrayList = new ArrayList(`$this$scanIndexed`.length() + 1);
         `accumulator$iv`.add(initial);
         val `result$iv`: ArrayList = `accumulator$iv`;
         var var11: Any = initial;
         var `index$iv`: Int = 0;

         for (int var12 = $this$scanIndexed.length(); index$iv < var12; index$iv++) {
            var11 = operation.invoke(`index$iv`, var11, `$this$runningFoldIndexed$iv`.charAt(`index$iv`));
            `result$iv`.add(var11);
         }

         var10000 = `result$iv`;
      }

      return var10000;
   }

   @Deprecated(message = "Use sumOf instead.", replaceWith = @ReplaceWith(expression = "this.sumOf(selector)", imports = []))
   @DeprecatedSinceKotlin(warningSince = "1.5")
   @JvmStatic
   public inline fun CharSequence.sumBy(selector: (Char) -> Int): Int {
      var sum: Int = 0;

      for (int var4 = 0; var4 < $this$sumBy.length(); var4++) {
         sum += (selector.invoke(`$this$sumBy`.charAt(var4)) as java.lang.Number).intValue();
      }

      return sum;
   }

   @Deprecated(message = "Use sumOf instead.", replaceWith = @ReplaceWith(expression = "this.sumOf(selector)", imports = []))
   @DeprecatedSinceKotlin(warningSince = "1.5")
   @JvmStatic
   public inline fun CharSequence.sumByDouble(selector: (Char) -> Double): Double {
      var sum: Double = 0.0;

      for (int var5 = 0; var5 < $this$sumByDouble.length(); var5++) {
         sum += (selector.invoke(`$this$sumByDouble`.charAt(var5)) as java.lang.Number).doubleValue();
      }

      return sum;
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @JvmName(name = "sumOfDouble")
   @InlineOnly
   @JvmStatic
   public inline fun CharSequence.sumOf(selector: (Char) -> Double): Double {
      var sum: Double = 0.0;

      for (int var4 = 0; var4 < $this$sumOf.length(); var4++) {
         sum += (selector.invoke(`$this$sumOf`.charAt(var4)) as java.lang.Number).doubleValue();
      }

      return sum;
   }

   @SinceKotlin(version = "1.4")
   @JvmName(name = "sumOfInt")
   @InlineOnly
   @JvmStatic
   public inline fun CharSequence.sumOf(selector: (Char) -> Int): Int {
      var sum: Int = 0;

      for (int var3 = 0; var3 < $this$sumOf.length(); var3++) {
         sum += (selector.invoke(`$this$sumOf`.charAt(var3)) as java.lang.Number).intValue();
      }

      return sum;
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @JvmName(name = "sumOfLong")
   @InlineOnly
   @JvmStatic
   public inline fun CharSequence.sumOf(selector: (Char) -> Long): Long {
      var sum: Long = 0L;

      for (int var4 = 0; var4 < $this$sumOf.length(); var4++) {
         sum += (selector.invoke(`$this$sumOf`.charAt(var4)) as java.lang.Number).longValue();
      }

      return sum;
   }

   @SinceKotlin(version = "1.5")
   @JvmName(name = "sumOfUInt")
   @InlineOnly
   @JvmStatic
   public inline fun CharSequence.sumOf(selector: (Char) -> UInt): UInt {
      var sum: Int = UInt.constructor-impl(0);

      for (int var3 = 0; var3 < $this$sumOf.length(); var3++) {
         sum = UInt.constructor-impl(sum + (selector.invoke(`$this$sumOf`.charAt(var3)) as UInt).unbox-impl());
      }

      return sum;
   }

   @SinceKotlin(version = "1.5")
   @OverloadResolutionByLambdaReturnType
   @JvmName(name = "sumOfULong")
   @InlineOnly
   @JvmStatic
   public inline fun CharSequence.sumOf(selector: (Char) -> ULong): ULong {
      var sum: Long = ULong.constructor-impl(0L);

      for (int var4 = 0; var4 < $this$sumOf.length(); var4++) {
         sum = ULong.constructor-impl(sum + (selector.invoke(`$this$sumOf`.charAt(var4)) as ULong).unbox-impl());
      }

      return sum;
   }

   @SinceKotlin(version = "1.2")
   @JvmStatic
   public fun CharSequence.chunked(size: Int): List<String> {
      return StringsKt.windowed(`$this$chunked`, size, size, true);
   }

   @SinceKotlin(version = "1.2")
   @JvmStatic
   public fun <R> CharSequence.chunked(size: Int, transform: (CharSequence) -> R): List<R> {
      return StringsKt.windowed(`$this$chunked`, size, size, true, transform);
   }

   @SinceKotlin(version = "1.2")
   @JvmStatic
   public fun CharSequence.chunkedSequence(size: Int): Sequence<String> {
      return StringsKt.chunkedSequence(`$this$chunkedSequence`, size, StringsKt___StringsKt::chunkedSequence$lambda$0$StringsKt___StringsKt);
   }

   @SinceKotlin(version = "1.2")
   @JvmStatic
   public fun <R> CharSequence.chunkedSequence(size: Int, transform: (CharSequence) -> R): Sequence<R> {
      return StringsKt.windowedSequence(`$this$chunkedSequence`, size, size, true, transform);
   }

   @JvmStatic
   public inline fun CharSequence.partition(predicate: (Char) -> Boolean): Pair<CharSequence, CharSequence> {
      val first: StringBuilder = new StringBuilder();
      val second: StringBuilder = new StringBuilder();

      for (int var5 = 0; var5 < $this$partition.length(); var5++) {
         val element: Char = `$this$partition`.charAt(var5);
         if (predicate.invoke(element) as java.lang.Boolean) {
            first.append(element);
         } else {
            second.append(element);
         }
      }

      return new Pair<>(first, second);
   }

   @JvmStatic
   public inline fun String.partition(predicate: (Char) -> Boolean): Pair<String, String> {
      val first: StringBuilder = new StringBuilder();
      val second: StringBuilder = new StringBuilder();
      var var5: Int = 0;

      for (int var6 = $this$partition.length(); var5 < var6; var5++) {
         val element: Char = `$this$partition`.charAt(var5);
         if (predicate.invoke(element) as java.lang.Boolean) {
            first.append(element);
         } else {
            second.append(element);
         }
      }

      return new Pair<>(first.toString(), second.toString());
   }

   @SinceKotlin(version = "1.2")
   @JvmStatic
   public fun CharSequence.windowed(size: Int, step: Int = 1, partialWindows: Boolean = false): List<String> {
      return StringsKt.windowed(`$this$windowed`, size, step, partialWindows, StringsKt___StringsKt::windowed$lambda$0$StringsKt___StringsKt);
   }

   @SinceKotlin(version = "1.2")
   @JvmStatic
   public fun <R> CharSequence.windowed(size: Int, step: Int = 1, partialWindows: Boolean = false, transform: (CharSequence) -> R): List<R> {
      SlidingWindowKt.checkWindowSizeStep(size, step);
      val thisSize: Int = `$this$windowed`.length();
      val result: ArrayList = new ArrayList(thisSize / step + (if (thisSize % step == 0) 0 else 1));

      for (int index = 0; 0 <= index && index < thisSize; index += step) {
         val end: Int = index + size;
         val var10000: Int;
         if (index + size >= 0 && index + size <= thisSize) {
            var10000 = end;
         } else {
            if (!partialWindows) {
               break;
            }

            var10000 = thisSize;
         }

         result.add(transform.invoke(`$this$windowed`.subSequence(index, var10000)));
      }

      return result;
   }

   @SinceKotlin(version = "1.2")
   @JvmStatic
   public fun CharSequence.windowedSequence(size: Int, step: Int = 1, partialWindows: Boolean = false): Sequence<String> {
      return StringsKt.windowedSequence(
         `$this$windowedSequence`, size, step, partialWindows, StringsKt___StringsKt::windowedSequence$lambda$0$StringsKt___StringsKt
      );
   }

   @SinceKotlin(version = "1.2")
   @JvmStatic
   public fun <R> CharSequence.windowedSequence(size: Int, step: Int = 1, partialWindows: Boolean = false, transform: (CharSequence) -> R): Sequence<R> {
      SlidingWindowKt.checkWindowSizeStep(size, step);
      return SequencesKt.map(
         CollectionsKt.asSequence(
            RangesKt.step(
               if (partialWindows) StringsKt.getIndices(`$this$windowedSequence`) else RangesKt.until(0, `$this$windowedSequence`.length() - size + 1), step
            )
         ),
         StringsKt___StringsKt::windowedSequence$lambda$1$StringsKt___StringsKt
      );
   }

   @JvmStatic
   public infix fun CharSequence.zip(other: CharSequence): List<Pair<Char, Char>> {
      val `$this$zip$iv`: java.lang.CharSequence = `$this$zip`;
      val `other$iv`: java.lang.CharSequence = other;
      val `length$iv`: Int = Math.min(`$this$zip`.length(), other.length());
      val `list$iv`: ArrayList = new ArrayList(`length$iv`);

      for (int i$iv = 0; i$iv < length$iv; i$iv++) {
         `list$iv`.add(TuplesKt.to(`$this$zip$iv`.charAt(`i$iv`), `other$iv`.charAt(`i$iv`)));
      }

      return `list$iv`;
   }

   @JvmStatic
   public inline fun <V> CharSequence.zip(other: CharSequence, transform: (Char, Char) -> V): List<V> {
      val length: Int = Math.min(`$this$zip`.length(), other.length());
      val list: ArrayList = new ArrayList(length);

      for (int i = 0; i < length; i++) {
         list.add(transform.invoke(`$this$zip`.charAt(i), other.charAt(i)));
      }

      return list;
   }

   @SinceKotlin(version = "1.2")
   @JvmStatic
   public fun CharSequence.zipWithNext(): List<Pair<Char, Char>> {
      val `$this$zipWithNext$iv`: java.lang.CharSequence = `$this$zipWithNext`;
      val `size$iv`: Int = `$this$zipWithNext`.length() - 1;
      val var10000: java.util.List;
      if (`size$iv` < 1) {
         var10000 = CollectionsKt.emptyList();
      } else {
         val `result$iv`: ArrayList = new ArrayList(`size$iv`);

         for (int index$iv = 0; index$iv < size$iv; index$iv++) {
            `result$iv`.add(TuplesKt.to(`$this$zipWithNext$iv`.charAt(`index$iv`), `$this$zipWithNext$iv`.charAt(`index$iv` + 1)));
         }

         var10000 = `result$iv`;
      }

      return var10000;
   }

   @SinceKotlin(version = "1.2")
   @JvmStatic
   public inline fun <R> CharSequence.zipWithNext(transform: (Char, Char) -> R): List<R> {
      val size: Int = `$this$zipWithNext`.length() - 1;
      if (size < 1) {
         return CollectionsKt.emptyList();
      } else {
         val result: ArrayList = new ArrayList(size);

         for (int index = 0; index < size; index++) {
            result.add(transform.invoke(`$this$zipWithNext`.charAt(index), `$this$zipWithNext`.charAt(index + 1)));
         }

         return result;
      }
   }

   @JvmStatic
   public fun CharSequence.asIterable(): Iterable<Char> {
      return (java.lang.Iterable<Character>)(if (`$this$asIterable` is java.lang.String && `$this$asIterable`.length() == 0)
         CollectionsKt.emptyList()
         else
         new kotlin.text.StringsKt___StringsKt.asIterable..inlined.Iterable.1(`$this$asIterable`));
   }

   @JvmStatic
   public fun CharSequence.asSequence(): Sequence<Char> {
      return (Sequence<Character>)(if (`$this$asSequence` is java.lang.String && `$this$asSequence`.length() == 0)
         SequencesKt.emptySequence()
         else
         new kotlin.text.StringsKt___StringsKt.asSequence..inlined.Sequence.1(`$this$asSequence`));
   }

   @JvmStatic
   fun `withIndex$lambda$0$StringsKt___StringsKt`(`$this_withIndex`: java.lang.CharSequence): java.util.Iterator {
      return StringsKt.iterator(`$this_withIndex`);
   }

   @JvmStatic
   fun `chunkedSequence$lambda$0$StringsKt___StringsKt`(it: java.lang.CharSequence): java.lang.String {
      return it.toString();
   }

   @JvmStatic
   fun `windowed$lambda$0$StringsKt___StringsKt`(it: java.lang.CharSequence): java.lang.String {
      return it.toString();
   }

   @JvmStatic
   fun `windowedSequence$lambda$0$StringsKt___StringsKt`(it: java.lang.CharSequence): java.lang.String {
      return it.toString();
   }

   @JvmStatic
   fun `windowedSequence$lambda$1$StringsKt___StringsKt`(`$size`: Int, `$this_windowedSequence`: java.lang.CharSequence, `$transform`: Function1, index: Int): Any {
      return `$transform`.invoke(
         `$this_windowedSequence`.subSequence(
            index, if (index + `$size` >= 0 && index + `$size` <= `$this_windowedSequence`.length()) index + `$size` else `$this_windowedSequence`.length()
         )
      );
   }

   open fun StringsKt___StringsKt() {
   }
}

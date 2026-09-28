package kotlin.collections

import java.util.ArrayList
import java.util.Comparator
import java.util.NoSuchElementException
import kotlin.collections.Map.Entry
import kotlin.internal.HidesMembers
import kotlin.internal.InlineOnly
import kotlin.jvm.internal.SourceDebugExtension

@SourceDebugExtension(["SMAP\n_Maps.kt\nKotlin\n*S Kotlin\n*F\n+ 1 _Maps.kt\nkotlin/collections/MapsKt___MapsKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,671:1\n97#1,5:672\n112#1,5:677\n153#1,3:682\n144#1:685\n216#1:686\n217#1:688\n145#1:689\n216#1:690\n217#1:692\n1#2:687\n1#2:691\n1969#3,14:693\n1999#3,14:707\n2393#3,14:721\n2423#3,14:735\n1878#3,3:749\n*S KotlinDebug\n*F\n+ 1 _Maps.kt\nkotlin/collections/MapsKt___MapsKt\n*L\n77#1:672,5\n90#1:677,5\n126#1:682,3\n136#1:685\n136#1:686\n136#1:688\n136#1:689\n144#1:690\n144#1:692\n136#1:687\n238#1:693,14\n256#1:707,14\n436#1:721,14\n454#1:735,14\n651#1:749,3\n*E\n"])
internal class MapsKt___MapsKt : MapsKt___MapsJvmKt {
   @SinceKotlin(version = "1.5")
   @InlineOnly
   @JvmStatic
   public inline fun <K, V, R : Any> Map<out K, V>.firstNotNullOf(transform: (Entry<K, V>) -> R?): R {
      val var2: java.util.Iterator = `$this$firstNotNullOf`.entrySet().iterator();

      var var10000: Any;
      do {
         if (!var2.hasNext()) {
            var10000 = null;
            break;
         }

         var10000 = transform.invoke(var2.next() as java.util.Map.Entry);
      } while (var10000 == null);

      if (var10000 == null) {
         throw new NoSuchElementException("No element of the map was transformed to a non-null value.");
      } else {
         return (R)var10000;
      }
   }

   @SinceKotlin(version = "1.5")
   @InlineOnly
   @JvmStatic
   public inline fun <K, V, R : Any> Map<out K, V>.firstNotNullOfOrNull(transform: (Entry<K, V>) -> R?): R? {
      for (java.util.Map.Entry element : $this$firstNotNullOfOrNull.entrySet()) {
         val result: Any = transform.invoke(element);
         if (result != null) {
            return (R)result;
         }
      }

      return null;
   }

   @JvmStatic
   public fun <K, V> Map<out K, V>.toList(): List<Pair<K, V>> {
      if (`$this$toList`.size() == 0) {
         return CollectionsKt.emptyList();
      } else {
         val iterator: java.util.Iterator = `$this$toList`.entrySet().iterator();
         if (!iterator.hasNext()) {
            return CollectionsKt.emptyList();
         } else {
            val first: java.util.Map.Entry = iterator.next() as java.util.Map.Entry;
            if (!iterator.hasNext()) {
               return CollectionsKt.listOf(new Pair<>(first.getKey(), first.getValue()));
            } else {
               val result: ArrayList = new ArrayList(`$this$toList`.size());
               result.add(new Pair<>(first.getKey(), first.getValue()));

               do {
                  val var4: java.util.Map.Entry = iterator.next() as java.util.Map.Entry;
                  result.add(new Pair<>(var4.getKey(), var4.getValue()));
               } while (iterator.hasNext());

               return result;
            }
         }
      }
   }

   @JvmStatic
   public inline fun <K, V, R> Map<out K, V>.flatMap(transform: (Entry<K, V>) -> Iterable<R>): List<R> {
      val `destination$iv`: java.util.Collection = new ArrayList();

      for (java.util.Map.Entry element$iv : $this$flatMap.entrySet()) {
         CollectionsKt.addAll(`destination$iv`, transform.invoke(`element$iv`) as java.lang.Iterable);
      }

      return `destination$iv` as MutableList<R>;
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @JvmName(name = "flatMapSequence")
   @JvmStatic
   public inline fun <K, V, R> Map<out K, V>.flatMap(transform: (Entry<K, V>) -> Sequence<R>): List<R> {
      val `destination$iv`: java.util.Collection = new ArrayList();

      for (java.util.Map.Entry element$iv : $this$flatMap.entrySet()) {
         CollectionsKt.addAll(`destination$iv`, transform.invoke(`element$iv`) as Sequence);
      }

      return `destination$iv` as MutableList<R>;
   }

   @JvmStatic
   public inline fun <K, V, R, C : MutableCollection<in R>> Map<out K, V>.flatMapTo(destination: C, transform: (Entry<K, V>) -> Iterable<R>): C {
      for (java.util.Map.Entry element : $this$flatMapTo.entrySet()) {
         CollectionsKt.addAll(destination, transform.invoke(element) as java.lang.Iterable);
      }

      return (C)destination;
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @JvmName(name = "flatMapSequenceTo")
   @JvmStatic
   public inline fun <K, V, R, C : MutableCollection<in R>> Map<out K, V>.flatMapTo(destination: C, transform: (Entry<K, V>) -> Sequence<R>): C {
      for (java.util.Map.Entry element : $this$flatMapTo.entrySet()) {
         CollectionsKt.addAll(destination, transform.invoke(element) as Sequence);
      }

      return (C)destination;
   }

   @JvmStatic
   public inline fun <K, V, R> Map<out K, V>.map(transform: (Entry<K, V>) -> R): List<R> {
      val `destination$iv`: java.util.Collection = new ArrayList(`$this$map`.size());

      for (java.util.Map.Entry item$iv : $this$map.entrySet()) {
         `destination$iv`.add(transform.invoke(`item$iv`));
      }

      return `destination$iv` as MutableList<R>;
   }

   @JvmStatic
   public inline fun <K, V, R : Any> Map<out K, V>.mapNotNull(transform: (Entry<K, V>) -> R?): List<R> {
      val `destination$iv`: java.util.Collection = new ArrayList();

      for (java.util.Map.Entry element$iv$iv : $this$mapNotNull.entrySet()) {
         val var10000: Any = transform.invoke(`element$iv$iv`);
         if (var10000 != null) {
            `destination$iv`.add(var10000);
         }
      }

      return `destination$iv` as MutableList<R>;
   }

   @JvmStatic
   public inline fun <K, V, R : Any, C : MutableCollection<in R>> Map<out K, V>.mapNotNullTo(destination: C, transform: (Entry<K, V>) -> R?): C {
      for (java.util.Map.Entry element$iv : $this$mapNotNullTo.entrySet()) {
         val var10000: Any = transform.invoke(`element$iv`);
         if (var10000 != null) {
            destination.add(var10000);
         }
      }

      return (C)destination;
   }

   @JvmStatic
   public inline fun <K, V, R, C : MutableCollection<in R>> Map<out K, V>.mapTo(destination: C, transform: (Entry<K, V>) -> R): C {
      for (java.util.Map.Entry item : $this$mapTo.entrySet()) {
         destination.add(transform.invoke(item));
      }

      return (C)destination;
   }

   @JvmStatic
   public inline fun <K, V> Map<out K, V>.all(predicate: (Entry<K, V>) -> Boolean): Boolean {
      if (`$this$all`.isEmpty()) {
         return true;
      } else {
         for (java.util.Map.Entry element : $this$all.entrySet()) {
            if (!predicate.invoke(element) as java.lang.Boolean) {
               return false;
            }
         }

         return true;
      }
   }

   @JvmStatic
   public fun <K, V> Map<out K, V>.any(): Boolean {
      return !`$this$any`.isEmpty();
   }

   @JvmStatic
   public inline fun <K, V> Map<out K, V>.any(predicate: (Entry<K, V>) -> Boolean): Boolean {
      if (`$this$any`.isEmpty()) {
         return false;
      } else {
         for (java.util.Map.Entry element : $this$any.entrySet()) {
            if (predicate.invoke(element) as java.lang.Boolean) {
               return true;
            }
         }

         return false;
      }
   }

   @InlineOnly
   @JvmStatic
   public inline fun <K, V> Map<out K, V>.count(): Int {
      return `$this$count`.size();
   }

   @JvmStatic
   public inline fun <K, V> Map<out K, V>.count(predicate: (Entry<K, V>) -> Boolean): Int {
      if (`$this$count`.isEmpty()) {
         return 0;
      } else {
         var count: Int = 0;

         for (java.util.Map.Entry element : $this$count.entrySet()) {
            if (predicate.invoke(element) as java.lang.Boolean) {
               count++;
            }
         }

         return count;
      }
   }

   @HidesMembers
   @JvmStatic
   public inline fun <K, V> Map<out K, V>.forEach(action: (Entry<K, V>) -> Unit) {
      for (java.util.Map.Entry element : $this$forEach.entrySet()) {
         action.invoke(element);
      }
   }

   @SinceKotlin(version = "1.7")
   @JvmName(name = "maxByOrThrow")
   @InlineOnly
   @JvmStatic
   public inline fun <K, V, R : Comparable<R>> Map<out K, V>.maxBy(selector: (Entry<K, V>) -> R): Entry<K, V> {
      val `iterator$iv`: java.util.Iterator = `$this$maxBy`.entrySet().iterator();
      if (!`iterator$iv`.hasNext()) {
         throw new NoSuchElementException();
      } else {
         var `maxElem$iv`: Any = `iterator$iv`.next();
         val var10000: Any;
         if (!`iterator$iv`.hasNext()) {
            var10000 = `maxElem$iv`;
         } else {
            var `maxValue$iv`: java.lang.Comparable = selector.invoke(`maxElem$iv`) as java.lang.Comparable;

            do {
               val `e$iv`: Any = `iterator$iv`.next();
               val `v$iv`: java.lang.Comparable = selector.invoke(`e$iv`) as java.lang.Comparable;
               if (`maxValue$iv`.compareTo(`v$iv`) < 0) {
                  `maxElem$iv` = `e$iv`;
                  `maxValue$iv` = `v$iv`;
               }
            } while (iterator$iv.hasNext());

            var10000 = `maxElem$iv`;
         }

         return var10000 as MutableMap.MutableEntry<K, V>;
      }
   }

   @SinceKotlin(version = "1.4")
   @InlineOnly
   @JvmStatic
   public inline fun <K, V, R : Comparable<R>> Map<out K, V>.maxByOrNull(selector: (Entry<K, V>) -> R): Entry<K, V>? {
      val `iterator$iv`: java.util.Iterator = `$this$maxByOrNull`.entrySet().iterator();
      val var10000: Any;
      if (!`iterator$iv`.hasNext()) {
         var10000 = null;
      } else {
         var `maxElem$iv`: Any = `iterator$iv`.next();
         if (!`iterator$iv`.hasNext()) {
            var10000 = `maxElem$iv`;
         } else {
            var `maxValue$iv`: java.lang.Comparable = selector.invoke(`maxElem$iv`) as java.lang.Comparable;

            do {
               val `e$iv`: Any = `iterator$iv`.next();
               val `v$iv`: java.lang.Comparable = selector.invoke(`e$iv`) as java.lang.Comparable;
               if (`maxValue$iv`.compareTo(`v$iv`) < 0) {
                  `maxElem$iv` = `e$iv`;
                  `maxValue$iv` = `v$iv`;
               }
            } while (iterator$iv.hasNext());

            var10000 = `maxElem$iv`;
         }
      }

      return var10000 as MutableMap.MutableEntry<K, V>;
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @InlineOnly
   @JvmStatic
   public inline fun <K, V> Map<out K, V>.maxOf(selector: (Entry<K, V>) -> Double): Double {
      val var2: java.util.Iterator = `$this$maxOf`.entrySet().iterator();
      if (!var2.hasNext()) {
         throw new NoSuchElementException();
      } else {
         var var3: Double = (selector.invoke(var2.next()) as java.lang.Number).doubleValue();

         while (var2.hasNext()) {
            var3 = Math.max(var3, (selector.invoke(var2.next()) as java.lang.Number).doubleValue());
         }

         return var3;
      }
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @InlineOnly
   @JvmStatic
   public inline fun <K, V> Map<out K, V>.maxOf(selector: (Entry<K, V>) -> Float): Float {
      val var2: java.util.Iterator = `$this$maxOf`.entrySet().iterator();
      if (!var2.hasNext()) {
         throw new NoSuchElementException();
      } else {
         var var3: Float = (selector.invoke(var2.next()) as java.lang.Number).floatValue();

         while (var2.hasNext()) {
            var3 = Math.max(var3, (selector.invoke(var2.next()) as java.lang.Number).floatValue());
         }

         return var3;
      }
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @InlineOnly
   @JvmStatic
   public inline fun <K, V, R : Comparable<R>> Map<out K, V>.maxOf(selector: (Entry<K, V>) -> R): R {
      val var2: java.util.Iterator = `$this$maxOf`.entrySet().iterator();
      if (!var2.hasNext()) {
         throw new NoSuchElementException();
      } else {
         var var3: java.lang.Comparable = selector.invoke(var2.next()) as java.lang.Comparable;

         while (var2.hasNext()) {
            val var4: java.lang.Comparable = selector.invoke(var2.next()) as java.lang.Comparable;
            if (var3.compareTo(var4) < 0) {
               var3 = var4;
            }
         }

         return (R)var3;
      }
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @InlineOnly
   @JvmStatic
   public inline fun <K, V> Map<out K, V>.maxOfOrNull(selector: (Entry<K, V>) -> Double): Double? {
      val var2: java.util.Iterator = `$this$maxOfOrNull`.entrySet().iterator();
      val var10000: java.lang.Double;
      if (!var2.hasNext()) {
         var10000 = null;
      } else {
         var var3: Double = (selector.invoke(var2.next()) as java.lang.Number).doubleValue();

         while (var2.hasNext()) {
            var3 = Math.max(var3, (selector.invoke(var2.next()) as java.lang.Number).doubleValue());
         }

         var10000 = var3;
      }

      return var10000;
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @InlineOnly
   @JvmStatic
   public inline fun <K, V> Map<out K, V>.maxOfOrNull(selector: (Entry<K, V>) -> Float): Float? {
      val var2: java.util.Iterator = `$this$maxOfOrNull`.entrySet().iterator();
      val var10000: java.lang.Float;
      if (!var2.hasNext()) {
         var10000 = null;
      } else {
         var var3: Float = (selector.invoke(var2.next()) as java.lang.Number).floatValue();

         while (var2.hasNext()) {
            var3 = Math.max(var3, (selector.invoke(var2.next()) as java.lang.Number).floatValue());
         }

         var10000 = var3;
      }

      return var10000;
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @InlineOnly
   @JvmStatic
   public inline fun <K, V, R : Comparable<R>> Map<out K, V>.maxOfOrNull(selector: (Entry<K, V>) -> R): R? {
      val var2: java.util.Iterator = `$this$maxOfOrNull`.entrySet().iterator();
      val var10000: java.lang.Comparable;
      if (!var2.hasNext()) {
         var10000 = null;
      } else {
         var var3: java.lang.Comparable = selector.invoke(var2.next()) as java.lang.Comparable;

         while (var2.hasNext()) {
            val var4: java.lang.Comparable = selector.invoke(var2.next()) as java.lang.Comparable;
            if (var3.compareTo(var4) < 0) {
               var3 = var4;
            }
         }

         var10000 = var3;
      }

      return (R)var10000;
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @InlineOnly
   @JvmStatic
   public inline fun <K, V, R> Map<out K, V>.maxOfWith(comparator: Comparator<in R>, selector: (Entry<K, V>) -> R): R {
      val var3: java.util.Iterator = `$this$maxOfWith`.entrySet().iterator();
      if (!var3.hasNext()) {
         throw new NoSuchElementException();
      } else {
         var var4: Any = selector.invoke(var3.next());

         while (var3.hasNext()) {
            val var5: Any = selector.invoke(var3.next());
            if (comparator.compare(var4, var5) < 0) {
               var4 = var5;
            }
         }

         return (R)var4;
      }
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @InlineOnly
   @JvmStatic
   public inline fun <K, V, R> Map<out K, V>.maxOfWithOrNull(comparator: Comparator<in R>, selector: (Entry<K, V>) -> R): R? {
      val var3: java.util.Iterator = `$this$maxOfWithOrNull`.entrySet().iterator();
      val var10000: Any;
      if (!var3.hasNext()) {
         var10000 = null;
      } else {
         var var4: Any = selector.invoke(var3.next());

         while (var3.hasNext()) {
            val var5: Any = selector.invoke(var3.next());
            if (comparator.compare(var4, var5) < 0) {
               var4 = var5;
            }
         }

         var10000 = var4;
      }

      return (R)var10000;
   }

   @SinceKotlin(version = "1.7")
   @JvmName(name = "maxWithOrThrow")
   @InlineOnly
   @JvmStatic
   public inline fun <K, V> Map<out K, V>.maxWith(comparator: Comparator<in Entry<K, V>>): Entry<K, V> {
      return CollectionsKt.maxWithOrThrow(`$this$maxWith`.entrySet(), comparator) as MutableMap.MutableEntry<K, V>;
   }

   @SinceKotlin(version = "1.4")
   @InlineOnly
   @JvmStatic
   public inline fun <K, V> Map<out K, V>.maxWithOrNull(comparator: Comparator<in Entry<K, V>>): Entry<K, V>? {
      return CollectionsKt.maxWithOrNull(`$this$maxWithOrNull`.entrySet(), comparator) as MutableMap.MutableEntry<K, V>;
   }

   @SinceKotlin(version = "1.7")
   @JvmName(name = "minByOrThrow")
   @InlineOnly
   @JvmStatic
   public inline fun <K, V, R : Comparable<R>> Map<out K, V>.minBy(selector: (Entry<K, V>) -> R): Entry<K, V> {
      val `iterator$iv`: java.util.Iterator = `$this$minBy`.entrySet().iterator();
      if (!`iterator$iv`.hasNext()) {
         throw new NoSuchElementException();
      } else {
         var `minElem$iv`: Any = `iterator$iv`.next();
         val var10000: Any;
         if (!`iterator$iv`.hasNext()) {
            var10000 = `minElem$iv`;
         } else {
            var `minValue$iv`: java.lang.Comparable = selector.invoke(`minElem$iv`) as java.lang.Comparable;

            do {
               val `e$iv`: Any = `iterator$iv`.next();
               val `v$iv`: java.lang.Comparable = selector.invoke(`e$iv`) as java.lang.Comparable;
               if (`minValue$iv`.compareTo(`v$iv`) > 0) {
                  `minElem$iv` = `e$iv`;
                  `minValue$iv` = `v$iv`;
               }
            } while (iterator$iv.hasNext());

            var10000 = `minElem$iv`;
         }

         return var10000 as MutableMap.MutableEntry<K, V>;
      }
   }

   @SinceKotlin(version = "1.4")
   @InlineOnly
   @JvmStatic
   public inline fun <K, V, R : Comparable<R>> Map<out K, V>.minByOrNull(selector: (Entry<K, V>) -> R): Entry<K, V>? {
      val `iterator$iv`: java.util.Iterator = `$this$minByOrNull`.entrySet().iterator();
      val var10000: Any;
      if (!`iterator$iv`.hasNext()) {
         var10000 = null;
      } else {
         var `minElem$iv`: Any = `iterator$iv`.next();
         if (!`iterator$iv`.hasNext()) {
            var10000 = `minElem$iv`;
         } else {
            var `minValue$iv`: java.lang.Comparable = selector.invoke(`minElem$iv`) as java.lang.Comparable;

            do {
               val `e$iv`: Any = `iterator$iv`.next();
               val `v$iv`: java.lang.Comparable = selector.invoke(`e$iv`) as java.lang.Comparable;
               if (`minValue$iv`.compareTo(`v$iv`) > 0) {
                  `minElem$iv` = `e$iv`;
                  `minValue$iv` = `v$iv`;
               }
            } while (iterator$iv.hasNext());

            var10000 = `minElem$iv`;
         }
      }

      return var10000 as MutableMap.MutableEntry<K, V>;
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @InlineOnly
   @JvmStatic
   public inline fun <K, V> Map<out K, V>.minOf(selector: (Entry<K, V>) -> Double): Double {
      val var2: java.util.Iterator = `$this$minOf`.entrySet().iterator();
      if (!var2.hasNext()) {
         throw new NoSuchElementException();
      } else {
         var var3: Double = (selector.invoke(var2.next()) as java.lang.Number).doubleValue();

         while (var2.hasNext()) {
            var3 = Math.min(var3, (selector.invoke(var2.next()) as java.lang.Number).doubleValue());
         }

         return var3;
      }
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @InlineOnly
   @JvmStatic
   public inline fun <K, V> Map<out K, V>.minOf(selector: (Entry<K, V>) -> Float): Float {
      val var2: java.util.Iterator = `$this$minOf`.entrySet().iterator();
      if (!var2.hasNext()) {
         throw new NoSuchElementException();
      } else {
         var var3: Float = (selector.invoke(var2.next()) as java.lang.Number).floatValue();

         while (var2.hasNext()) {
            var3 = Math.min(var3, (selector.invoke(var2.next()) as java.lang.Number).floatValue());
         }

         return var3;
      }
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @InlineOnly
   @JvmStatic
   public inline fun <K, V, R : Comparable<R>> Map<out K, V>.minOf(selector: (Entry<K, V>) -> R): R {
      val var2: java.util.Iterator = `$this$minOf`.entrySet().iterator();
      if (!var2.hasNext()) {
         throw new NoSuchElementException();
      } else {
         var var3: java.lang.Comparable = selector.invoke(var2.next()) as java.lang.Comparable;

         while (var2.hasNext()) {
            val var4: java.lang.Comparable = selector.invoke(var2.next()) as java.lang.Comparable;
            if (var3.compareTo(var4) > 0) {
               var3 = var4;
            }
         }

         return (R)var3;
      }
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @InlineOnly
   @JvmStatic
   public inline fun <K, V> Map<out K, V>.minOfOrNull(selector: (Entry<K, V>) -> Double): Double? {
      val var2: java.util.Iterator = `$this$minOfOrNull`.entrySet().iterator();
      val var10000: java.lang.Double;
      if (!var2.hasNext()) {
         var10000 = null;
      } else {
         var var3: Double = (selector.invoke(var2.next()) as java.lang.Number).doubleValue();

         while (var2.hasNext()) {
            var3 = Math.min(var3, (selector.invoke(var2.next()) as java.lang.Number).doubleValue());
         }

         var10000 = var3;
      }

      return var10000;
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @InlineOnly
   @JvmStatic
   public inline fun <K, V> Map<out K, V>.minOfOrNull(selector: (Entry<K, V>) -> Float): Float? {
      val var2: java.util.Iterator = `$this$minOfOrNull`.entrySet().iterator();
      val var10000: java.lang.Float;
      if (!var2.hasNext()) {
         var10000 = null;
      } else {
         var var3: Float = (selector.invoke(var2.next()) as java.lang.Number).floatValue();

         while (var2.hasNext()) {
            var3 = Math.min(var3, (selector.invoke(var2.next()) as java.lang.Number).floatValue());
         }

         var10000 = var3;
      }

      return var10000;
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @InlineOnly
   @JvmStatic
   public inline fun <K, V, R : Comparable<R>> Map<out K, V>.minOfOrNull(selector: (Entry<K, V>) -> R): R? {
      val var2: java.util.Iterator = `$this$minOfOrNull`.entrySet().iterator();
      val var10000: java.lang.Comparable;
      if (!var2.hasNext()) {
         var10000 = null;
      } else {
         var var3: java.lang.Comparable = selector.invoke(var2.next()) as java.lang.Comparable;

         while (var2.hasNext()) {
            val var4: java.lang.Comparable = selector.invoke(var2.next()) as java.lang.Comparable;
            if (var3.compareTo(var4) > 0) {
               var3 = var4;
            }
         }

         var10000 = var3;
      }

      return (R)var10000;
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @InlineOnly
   @JvmStatic
   public inline fun <K, V, R> Map<out K, V>.minOfWith(comparator: Comparator<in R>, selector: (Entry<K, V>) -> R): R {
      val var3: java.util.Iterator = `$this$minOfWith`.entrySet().iterator();
      if (!var3.hasNext()) {
         throw new NoSuchElementException();
      } else {
         var var4: Any = selector.invoke(var3.next());

         while (var3.hasNext()) {
            val var5: Any = selector.invoke(var3.next());
            if (comparator.compare(var4, var5) > 0) {
               var4 = var5;
            }
         }

         return (R)var4;
      }
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @InlineOnly
   @JvmStatic
   public inline fun <K, V, R> Map<out K, V>.minOfWithOrNull(comparator: Comparator<in R>, selector: (Entry<K, V>) -> R): R? {
      val var3: java.util.Iterator = `$this$minOfWithOrNull`.entrySet().iterator();
      val var10000: Any;
      if (!var3.hasNext()) {
         var10000 = null;
      } else {
         var var4: Any = selector.invoke(var3.next());

         while (var3.hasNext()) {
            val var5: Any = selector.invoke(var3.next());
            if (comparator.compare(var4, var5) > 0) {
               var4 = var5;
            }
         }

         var10000 = var4;
      }

      return (R)var10000;
   }

   @SinceKotlin(version = "1.7")
   @JvmName(name = "minWithOrThrow")
   @InlineOnly
   @JvmStatic
   public inline fun <K, V> Map<out K, V>.minWith(comparator: Comparator<in Entry<K, V>>): Entry<K, V> {
      return CollectionsKt.minWithOrThrow(`$this$minWith`.entrySet(), comparator) as MutableMap.MutableEntry<K, V>;
   }

   @SinceKotlin(version = "1.4")
   @InlineOnly
   @JvmStatic
   public inline fun <K, V> Map<out K, V>.minWithOrNull(comparator: Comparator<in Entry<K, V>>): Entry<K, V>? {
      return CollectionsKt.minWithOrNull(`$this$minWithOrNull`.entrySet(), comparator) as MutableMap.MutableEntry<K, V>;
   }

   @JvmStatic
   public fun <K, V> Map<out K, V>.none(): Boolean {
      return `$this$none`.isEmpty();
   }

   @JvmStatic
   public inline fun <K, V> Map<out K, V>.none(predicate: (Entry<K, V>) -> Boolean): Boolean {
      if (`$this$none`.isEmpty()) {
         return true;
      } else {
         for (java.util.Map.Entry element : $this$none.entrySet()) {
            if (predicate.invoke(element) as java.lang.Boolean) {
               return false;
            }
         }

         return true;
      }
   }

   @SinceKotlin(version = "1.1")
   @JvmStatic
   public inline fun <K, V, M : Map<out K, V>> M.onEach(action: (Entry<K, V>) -> Unit): M {
      for (java.util.Map.Entry element : $this$onEach.entrySet()) {
         action.invoke(element);
      }

      return (M)`$this$onEach`;
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public inline fun <K, V, M : Map<out K, V>> M.onEachIndexed(action: (Int, Entry<K, V>) -> Unit): M {
      val `$this$forEachIndexed$iv`: java.lang.Iterable = `$this$onEachIndexed`.entrySet();
      var `index$iv`: Int = 0;

      for (Object item$iv : $this$forEachIndexed$iv) {
         val var11: Int = `index$iv`++;
         if (var11 < 0) {
            CollectionsKt.throwIndexOverflow();
         }

         action.invoke(var11, `item$iv`);
      }

      return (M)`$this$onEachIndexed`;
   }

   @InlineOnly
   @JvmStatic
   public inline fun <K, V> Map<out K, V>.asIterable(): Iterable<Entry<K, V>> {
      return `$this$asIterable`.entrySet();
   }

   @JvmStatic
   public fun <K, V> Map<out K, V>.asSequence(): Sequence<Entry<K, V>> {
      return CollectionsKt.asSequence(`$this$asSequence`.entrySet());
   }

   open fun MapsKt___MapsKt() {
   }
}

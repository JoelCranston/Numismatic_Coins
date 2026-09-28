package kotlin.sequences

import java.util.ArrayList
import java.util.Comparator
import java.util.HashSet
import java.util.LinkedHashMap
import java.util.LinkedHashSet
import java.util.NoSuchElementException
import kotlin.comparisons.ComparisonsKt__ComparisonsKt.compareBy.2
import kotlin.contracts.InvocationKind
import kotlin.internal.InlineOnly
import kotlin.jvm.functions.Function1
import kotlin.jvm.functions.Function2
import kotlin.jvm.internal.SourceDebugExtension
import kotlin.sequences.SequencesKt___SequencesKt.minus.3
import kotlin.sequences.SequencesKt___SequencesKt.minus.4
import kotlin.sequences.SequencesKt___SequencesKt.sorted.1

@SourceDebugExtension(["SMAP\n_Sequences.kt\nKotlin\n*S Kotlin\n*F\n+ 1 _Sequences.kt\nkotlin/sequences/SequencesKt___SequencesKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n*L\n1#1,3218:1\n183#1,2:3219\n320#1,7:3221\n1332#1,3:3229\n747#1,4:3232\n712#1,4:3236\n730#1,4:3240\n783#1,4:3244\n1025#1,3:3248\n1028#1,3:3258\n1045#1,3:3261\n1048#1,3:3271\n1332#1,3:3288\n1321#1,2:3291\n1#2:3228\n382#3,7:3251\n382#3,7:3264\n382#3,7:3274\n382#3,7:3281\n*S KotlinDebug\n*F\n+ 1 _Sequences.kt\nkotlin/sequences/SequencesKt___SequencesKt\n*L\n91#1:3219,2\n103#1:3221,7\n462#1:3229,3\n666#1:3232,4\n682#1:3236,4\n697#1:3240,4\n768#1:3244,4\n996#1:3248,3\n996#1:3258,3\n1011#1:3261,3\n1011#1:3271,3\n1114#1:3288,3\n1152#1:3291,2\n996#1:3251,7\n1011#1:3264,7\n1027#1:3274,7\n1047#1:3281,7\n*E\n"])
internal class SequencesKt___SequencesKt : SequencesKt___SequencesJvmKt {
   @JvmStatic
   public operator fun <T> Sequence<T>.contains(element: T): Boolean {
      return SequencesKt.indexOf(`$this$contains`, element) >= 0;
   }

   @JvmStatic
   public fun <T> Sequence<T>.elementAt(index: Int): T {
      return (T)SequencesKt.elementAtOrElse(`$this$elementAt`, index, SequencesKt___SequencesKt::elementAt$lambda$0$SequencesKt___SequencesKt);
   }

   @JvmStatic
   public fun <T> Sequence<T>.elementAtOrElse(index: Int, defaultValue: (Int) -> T): T {
      contract {
         callsInPlace(defaultValue, InvocationKind.AT_MOST_ONCE)
      }

      if (index < 0) {
         return (T)defaultValue.invoke(index);
      } else {
         val iterator: java.util.Iterator = `$this$elementAtOrElse`.iterator();
         val count: Int = 0;

         while (iterator.hasNext()) {
            val element: Any = iterator.next();
            if (index == count++) {
               return (T)element;
            }
         }

         return (T)defaultValue.invoke(index);
      }
   }

   @JvmStatic
   public fun <T> Sequence<T>.elementAtOrNull(index: Int): T? {
      if (index < 0) {
         return null;
      } else {
         val iterator: java.util.Iterator = `$this$elementAtOrNull`.iterator();
         val count: Int = 0;

         while (iterator.hasNext()) {
            val element: Any = iterator.next();
            if (index == count++) {
               return (T)element;
            }
         }

         return null;
      }
   }

   @InlineOnly
   @JvmStatic
   public inline fun <T> Sequence<T>.find(predicate: (T) -> Boolean): T? {
      val var4: java.util.Iterator = `$this$find`.iterator();

      var var10000: Any;
      while (true) {
         if (var4.hasNext()) {
            val `element$iv`: Any = var4.next();
            if (!predicate.invoke(`element$iv`) as java.lang.Boolean) {
               continue;
            }

            var10000 = `element$iv`;
            break;
         }

         var10000 = null;
         break;
      }

      return (T)var10000;
   }

   @InlineOnly
   @JvmStatic
   public inline fun <T> Sequence<T>.findLast(predicate: (T) -> Boolean): T? {
      var `last$iv`: Any = null;

      for (Object element$iv : $this$findLast) {
         if (predicate.invoke(`element$iv`) as java.lang.Boolean) {
            `last$iv` = `element$iv`;
         }
      }

      return (T)`last$iv`;
   }

   @JvmStatic
   public fun <T> Sequence<T>.first(): T {
      val iterator: java.util.Iterator = `$this$first`.iterator();
      if (!iterator.hasNext()) {
         throw new NoSuchElementException("Sequence is empty.");
      } else {
         return (T)iterator.next();
      }
   }

   @JvmStatic
   public inline fun <T> Sequence<T>.first(predicate: (T) -> Boolean): T {
      for (Object element : $this$first) {
         if (predicate.invoke(element) as java.lang.Boolean) {
            return (T)element;
         }
      }

      throw new NoSuchElementException("Sequence contains no element matching the predicate.");
   }

   @SinceKotlin(version = "1.5")
   @InlineOnly
   @JvmStatic
   public inline fun <T, R : Any> Sequence<T>.firstNotNullOf(transform: (T) -> R?): R {
      val var2: java.util.Iterator = `$this$firstNotNullOf`.iterator();

      var var10000: Any;
      do {
         if (!var2.hasNext()) {
            var10000 = null;
            break;
         }

         var10000 = transform.invoke(var2.next());
      } while (var10000 == null);

      if (var10000 == null) {
         throw new NoSuchElementException("No element of the sequence was transformed to a non-null value.");
      } else {
         return (R)var10000;
      }
   }

   @SinceKotlin(version = "1.5")
   @InlineOnly
   @JvmStatic
   public inline fun <T, R : Any> Sequence<T>.firstNotNullOfOrNull(transform: (T) -> R?): R? {
      for (Object element : $this$firstNotNullOfOrNull) {
         val result: Any = transform.invoke(element);
         if (result != null) {
            return (R)result;
         }
      }

      return null;
   }

   @JvmStatic
   public fun <T> Sequence<T>.firstOrNull(): T? {
      val iterator: java.util.Iterator = `$this$firstOrNull`.iterator();
      return (T)(if (!iterator.hasNext()) null else iterator.next());
   }

   @JvmStatic
   public inline fun <T> Sequence<T>.firstOrNull(predicate: (T) -> Boolean): T? {
      for (Object element : $this$firstOrNull) {
         if (predicate.invoke(element) as java.lang.Boolean) {
            return (T)element;
         }
      }

      return null;
   }

   @JvmStatic
   public fun <T> Sequence<T>.indexOf(element: T): Int {
      var index: Int = 0;

      for (Object item : $this$indexOf) {
         if (index < 0) {
            CollectionsKt.throwIndexOverflow();
         }

         if (element == item) {
            return index;
         }

         index++;
      }

      return -1;
   }

   @JvmStatic
   public inline fun <T> Sequence<T>.indexOfFirst(predicate: (T) -> Boolean): Int {
      var index: Int = 0;

      for (Object item : $this$indexOfFirst) {
         if (index < 0) {
            CollectionsKt.throwIndexOverflow();
         }

         if (predicate.invoke(item) as java.lang.Boolean) {
            return index;
         }

         index++;
      }

      return -1;
   }

   @JvmStatic
   public inline fun <T> Sequence<T>.indexOfLast(predicate: (T) -> Boolean): Int {
      var lastIndex: Int = -1;
      var index: Int = 0;

      for (Object item : $this$indexOfLast) {
         if (index < 0) {
            CollectionsKt.throwIndexOverflow();
         }

         if (predicate.invoke(item) as java.lang.Boolean) {
            lastIndex = index;
         }

         index++;
      }

      return lastIndex;
   }

   @JvmStatic
   public fun <T> Sequence<T>.last(): T {
      val iterator: java.util.Iterator = `$this$last`.iterator();
      if (!iterator.hasNext()) {
         throw new NoSuchElementException("Sequence is empty.");
      } else {
         var last: Any = iterator.next();

         while (iterator.hasNext()) {
            last = iterator.next();
         }

         return (T)last;
      }
   }

   @JvmStatic
   public inline fun <T> Sequence<T>.last(predicate: (T) -> Boolean): T {
      var last: Any = null;
      var found: Boolean = false;

      for (Object element : $this$last) {
         if (predicate.invoke(element) as java.lang.Boolean) {
            last = element;
            found = true;
         }
      }

      if (!found) {
         throw new NoSuchElementException("Sequence contains no element matching the predicate.");
      } else {
         return (T)last;
      }
   }

   @JvmStatic
   public fun <T> Sequence<T>.lastIndexOf(element: T): Int {
      var lastIndex: Int = -1;
      var index: Int = 0;

      for (Object item : $this$lastIndexOf) {
         if (index < 0) {
            CollectionsKt.throwIndexOverflow();
         }

         if (element == item) {
            lastIndex = index;
         }

         index++;
      }

      return lastIndex;
   }

   @JvmStatic
   public fun <T> Sequence<T>.lastOrNull(): T? {
      val iterator: java.util.Iterator = `$this$lastOrNull`.iterator();
      if (!iterator.hasNext()) {
         return null;
      } else {
         var last: Any = iterator.next();

         while (iterator.hasNext()) {
            last = iterator.next();
         }

         return (T)last;
      }
   }

   @JvmStatic
   public inline fun <T> Sequence<T>.lastOrNull(predicate: (T) -> Boolean): T? {
      var last: Any = null;

      for (Object element : $this$lastOrNull) {
         if (predicate.invoke(element) as java.lang.Boolean) {
            last = element;
         }
      }

      return (T)last;
   }

   @JvmStatic
   public fun <T> Sequence<T>.single(): T {
      val iterator: java.util.Iterator = `$this$single`.iterator();
      if (!iterator.hasNext()) {
         throw new NoSuchElementException("Sequence is empty.");
      } else {
         val single: Any = iterator.next();
         if (iterator.hasNext()) {
            throw new IllegalArgumentException("Sequence has more than one element.");
         } else {
            return (T)single;
         }
      }
   }

   @JvmStatic
   public inline fun <T> Sequence<T>.single(predicate: (T) -> Boolean): T {
      var single: Any = null;
      var found: Boolean = false;

      for (Object element : $this$single) {
         if (predicate.invoke(element) as java.lang.Boolean) {
            if (found) {
               throw new IllegalArgumentException("Sequence contains more than one matching element.");
            }

            single = element;
            found = true;
         }
      }

      if (!found) {
         throw new NoSuchElementException("Sequence contains no element matching the predicate.");
      } else {
         return (T)single;
      }
   }

   @JvmStatic
   public fun <T> Sequence<T>.singleOrNull(): T? {
      val iterator: java.util.Iterator = `$this$singleOrNull`.iterator();
      if (!iterator.hasNext()) {
         return null;
      } else {
         return (T)(if (iterator.hasNext()) null else iterator.next());
      }
   }

   @JvmStatic
   public inline fun <T> Sequence<T>.singleOrNull(predicate: (T) -> Boolean): T? {
      var single: Any = null;
      var found: Boolean = false;

      for (Object element : $this$singleOrNull) {
         if (predicate.invoke(element) as java.lang.Boolean) {
            if (found) {
               return null;
            }

            single = element;
            found = true;
         }
      }

      return (T)(if (!found) null else single);
   }

   @JvmStatic
   public fun <T> Sequence<T>.drop(n: Int): Sequence<T> {
      if (n < 0) {
         throw new IllegalArgumentException(("Requested element count $n is less than zero.").toString());
      } else {
         return (Sequence<T>)(if (n == 0)
            `$this$drop`
            else
            (if (`$this$drop` is DropTakeSequence) (`$this$drop` as DropTakeSequence).drop(n) else new DropSequence(`$this$drop`, n)));
      }
   }

   @JvmStatic
   public fun <T> Sequence<T>.dropWhile(predicate: (T) -> Boolean): Sequence<T> {
      return new DropWhileSequence(`$this$dropWhile`, predicate);
   }

   @JvmStatic
   public fun <T> Sequence<T>.filter(predicate: (T) -> Boolean): Sequence<T> {
      return new FilteringSequence(`$this$filter`, true, predicate);
   }

   @JvmStatic
   public fun <T> Sequence<T>.filterIndexed(predicate: (Int, T) -> Boolean): Sequence<T> {
      return new TransformingSequence<>(
         new FilteringSequence<>(new IndexingSequence(`$this$filterIndexed`), true, SequencesKt___SequencesKt::filterIndexed$lambda$0$SequencesKt___SequencesKt),
         SequencesKt___SequencesKt::filterIndexed$lambda$1$SequencesKt___SequencesKt
      );
   }

   @JvmStatic
   public inline fun <T, C : MutableCollection<in T>> Sequence<T>.filterIndexedTo(destination: C, predicate: (Int, T) -> Boolean): C {
      var `index$iv`: Int = 0;

      for (Object item$iv : $this$filterIndexedTo) {
         val var9: Int = `index$iv`++;
         if (var9 < 0) {
            CollectionsKt.throwIndexOverflow();
         }

         if (predicate.invoke(var9, `item$iv`) as java.lang.Boolean) {
            destination.add(`item$iv`);
         }
      }

      return (C)destination;
   }

   @JvmStatic
   public fun <T> Sequence<T>.filterNot(predicate: (T) -> Boolean): Sequence<T> {
      return new FilteringSequence(`$this$filterNot`, false, predicate);
   }

   @JvmStatic
   public fun <T : Any> Sequence<T?>.filterNotNull(): Sequence<T> {
      val var10000: Sequence = SequencesKt.filterNot(`$this$filterNotNull`, SequencesKt___SequencesKt::filterNotNull$lambda$0$SequencesKt___SequencesKt);
      return var10000;
   }

   @JvmStatic
   public fun <C : MutableCollection<in T>, T : Any> Sequence<T?>.filterNotNullTo(destination: C): C {
      for (Object element : $this$filterNotNullTo) {
         if (element != null) {
            destination.add(element);
         }
      }

      return (C)destination;
   }

   @JvmStatic
   public inline fun <T, C : MutableCollection<in T>> Sequence<T>.filterNotTo(destination: C, predicate: (T) -> Boolean): C {
      for (Object element : $this$filterNotTo) {
         if (!predicate.invoke(element) as java.lang.Boolean) {
            destination.add(element);
         }
      }

      return (C)destination;
   }

   @JvmStatic
   public inline fun <T, C : MutableCollection<in T>> Sequence<T>.filterTo(destination: C, predicate: (T) -> Boolean): C {
      for (Object element : $this$filterTo) {
         if (predicate.invoke(element) as java.lang.Boolean) {
            destination.add(element);
         }
      }

      return (C)destination;
   }

   @JvmStatic
   public fun <T> Sequence<T>.take(n: Int): Sequence<T> {
      if (n < 0) {
         throw new IllegalArgumentException(("Requested element count $n is less than zero.").toString());
      } else {
         return (Sequence<T>)(if (n == 0)
            SequencesKt.emptySequence()
            else
            (if (`$this$take` is DropTakeSequence) (`$this$take` as DropTakeSequence).take(n) else new TakeSequence(`$this$take`, n)));
      }
   }

   @JvmStatic
   public fun <T> Sequence<T>.takeWhile(predicate: (T) -> Boolean): Sequence<T> {
      return new TakeWhileSequence(`$this$takeWhile`, predicate);
   }

   @JvmStatic
   public fun <T : Comparable<T>> Sequence<T>.sorted(): Sequence<T> {
      return new 1(`$this$sorted`);
   }

   @JvmStatic
   public inline fun <T, R : Comparable<R>> Sequence<T>.sortedBy(crossinline selector: (T) -> R?): Sequence<T> {
      return SequencesKt.sortedWith(`$this$sortedBy`, new 2(selector));
   }

   @JvmStatic
   public inline fun <T, R : Comparable<R>> Sequence<T>.sortedByDescending(crossinline selector: (T) -> R?): Sequence<T> {
      return SequencesKt.sortedWith(`$this$sortedByDescending`, new kotlin.comparisons.ComparisonsKt__ComparisonsKt.compareByDescending.1(selector));
   }

   @JvmStatic
   public fun <T : Comparable<T>> Sequence<T>.sortedDescending(): Sequence<T> {
      return SequencesKt.sortedWith(`$this$sortedDescending`, ComparisonsKt.reverseOrder());
   }

   @JvmStatic
   public fun <T> Sequence<T>.sortedWith(comparator: Comparator<in T>): Sequence<T> {
      return new kotlin.sequences.SequencesKt___SequencesKt.sortedWith.1(`$this$sortedWith`, comparator);
   }

   @JvmStatic
   public inline fun <T, K, V> Sequence<T>.associate(transform: (T) -> Pair<K, V>): Map<K, V> {
      val `destination$iv`: java.util.Map = new LinkedHashMap();

      for (Object element$iv : $this$associate) {
         val var9: Pair = transform.invoke(`element$iv`) as Pair;
         `destination$iv`.put(var9.getFirst(), var9.getSecond());
      }

      return `destination$iv`;
   }

   @JvmStatic
   public inline fun <T, K> Sequence<T>.associateBy(keySelector: (T) -> K): Map<K, T> {
      val `destination$iv`: java.util.Map = new LinkedHashMap();

      for (Object element$iv : $this$associateBy) {
         `destination$iv`.put(keySelector.invoke(`element$iv`), `element$iv`);
      }

      return `destination$iv`;
   }

   @JvmStatic
   public inline fun <T, K, V> Sequence<T>.associateBy(keySelector: (T) -> K, valueTransform: (T) -> V): Map<K, V> {
      val `destination$iv`: java.util.Map = new LinkedHashMap();

      for (Object element$iv : $this$associateBy) {
         `destination$iv`.put(keySelector.invoke(`element$iv`), valueTransform.invoke(`element$iv`));
      }

      return `destination$iv`;
   }

   @JvmStatic
   public inline fun <T, K, M : MutableMap<in K, in T>> Sequence<T>.associateByTo(destination: M, keySelector: (T) -> K): M {
      for (Object element : $this$associateByTo) {
         destination.put(keySelector.invoke(element), element);
      }

      return (M)destination;
   }

   @JvmStatic
   public inline fun <T, K, V, M : MutableMap<in K, in V>> Sequence<T>.associateByTo(destination: M, keySelector: (T) -> K, valueTransform: (T) -> V): M {
      for (Object element : $this$associateByTo) {
         destination.put(keySelector.invoke(element), valueTransform.invoke(element));
      }

      return (M)destination;
   }

   @JvmStatic
   public inline fun <T, K, V, M : MutableMap<in K, in V>> Sequence<T>.associateTo(destination: M, transform: (T) -> Pair<K, V>): M {
      for (Object element : $this$associateTo) {
         val var7: Pair = transform.invoke(element) as Pair;
         destination.put(var7.getFirst(), var7.getSecond());
      }

      return (M)destination;
   }

   @SinceKotlin(version = "1.3")
   @JvmStatic
   public inline fun <K, V> Sequence<K>.associateWith(valueSelector: (K) -> V): Map<K, V> {
      val `destination$iv`: java.util.Map = new LinkedHashMap();

      for (Object element$iv : $this$associateWith) {
         `destination$iv`.put(`element$iv`, valueSelector.invoke(`element$iv`));
      }

      return `destination$iv`;
   }

   @SinceKotlin(version = "1.3")
   @JvmStatic
   public inline fun <K, V, M : MutableMap<in K, in V>> Sequence<K>.associateWithTo(destination: M, valueSelector: (K) -> V): M {
      for (Object element : $this$associateWithTo) {
         destination.put(element, valueSelector.invoke(element));
      }

      return (M)destination;
   }

   @JvmStatic
   public fun <T, C : MutableCollection<in T>> Sequence<T>.toCollection(destination: C): C {
      for (Object item : $this$toCollection) {
         destination.add(item);
      }

      return (C)destination;
   }

   @JvmStatic
   public fun <T> Sequence<T>.toHashSet(): HashSet<T> {
      return SequencesKt.toCollection(`$this$toHashSet`, new HashSet()) as HashSet<T>;
   }

   @JvmStatic
   public fun <T> Sequence<T>.toList(): List<T> {
      val it: java.util.Iterator = `$this$toList`.iterator();
      if (!it.hasNext()) {
         return CollectionsKt.emptyList();
      } else {
         val element: Any = it.next();
         if (!it.hasNext()) {
            return (java.util.List<T>)CollectionsKt.listOf(element);
         } else {
            val dst: ArrayList = new ArrayList();
            dst.add(element);

            while (it.hasNext()) {
               dst.add(it.next());
            }

            return dst;
         }
      }
   }

   @JvmStatic
   public fun <T> Sequence<T>.toMutableList(): MutableList<T> {
      return SequencesKt.toCollection(`$this$toMutableList`, new ArrayList()) as MutableList<T>;
   }

   @JvmStatic
   public fun <T> Sequence<T>.toSet(): Set<T> {
      val it: java.util.Iterator = `$this$toSet`.iterator();
      if (!it.hasNext()) {
         return SetsKt.emptySet();
      } else {
         val element: Any = it.next();
         if (!it.hasNext()) {
            return (java.util.Set<T>)SetsKt.setOf(element);
         } else {
            val dst: LinkedHashSet = new LinkedHashSet();
            dst.add(element);

            while (it.hasNext()) {
               dst.add(it.next());
            }

            return dst;
         }
      }
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @JvmName(name = "flatMapIterable")
   @JvmStatic
   public fun <T, R> Sequence<T>.flatMap(transform: (T) -> Iterable<R>): Sequence<R> {
      return new FlatteningSequence<>(`$this$flatMap`, transform, kotlin.sequences.SequencesKt___SequencesKt.flatMap.1.INSTANCE);
   }

   @JvmStatic
   public fun <T, R> Sequence<T>.flatMap(transform: (T) -> Sequence<R>): Sequence<R> {
      return new FlatteningSequence<>(`$this$flatMap`, transform, kotlin.sequences.SequencesKt___SequencesKt.flatMap.2.INSTANCE);
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @JvmName(name = "flatMapIndexedIterable")
   @JvmStatic
   public fun <T, R> Sequence<T>.flatMapIndexed(transform: (Int, T) -> Iterable<R>): Sequence<R> {
      return SequencesKt.flatMapIndexed(`$this$flatMapIndexed`, transform, kotlin.sequences.SequencesKt___SequencesKt.flatMapIndexed.1.INSTANCE);
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @JvmName(name = "flatMapIndexedSequence")
   @JvmStatic
   public fun <T, R> Sequence<T>.flatMapIndexed(transform: (Int, T) -> Sequence<R>): Sequence<R> {
      return SequencesKt.flatMapIndexed(`$this$flatMapIndexed`, transform, kotlin.sequences.SequencesKt___SequencesKt.flatMapIndexed.2.INSTANCE);
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @JvmName(name = "flatMapIndexedIterableTo")
   @InlineOnly
   @JvmStatic
   public inline fun <T, R, C : MutableCollection<in R>> Sequence<T>.flatMapIndexedTo(destination: C, transform: (Int, T) -> Iterable<R>): C {
      var index: Int = 0;

      for (Object element : $this$flatMapIndexedTo) {
         val var7: Int = index++;
         if (var7 < 0) {
            CollectionsKt.throwIndexOverflow();
         }

         CollectionsKt.addAll(destination, transform.invoke(var7, element) as java.lang.Iterable);
      }

      return (C)destination;
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @JvmName(name = "flatMapIndexedSequenceTo")
   @InlineOnly
   @JvmStatic
   public inline fun <T, R, C : MutableCollection<in R>> Sequence<T>.flatMapIndexedTo(destination: C, transform: (Int, T) -> Sequence<R>): C {
      var index: Int = 0;

      for (Object element : $this$flatMapIndexedTo) {
         val var7: Int = index++;
         if (var7 < 0) {
            CollectionsKt.throwIndexOverflow();
         }

         CollectionsKt.addAll(destination, transform.invoke(var7, element) as Sequence);
      }

      return (C)destination;
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @JvmName(name = "flatMapIterableTo")
   @JvmStatic
   public inline fun <T, R, C : MutableCollection<in R>> Sequence<T>.flatMapTo(destination: C, transform: (T) -> Iterable<R>): C {
      for (Object element : $this$flatMapTo) {
         CollectionsKt.addAll(destination, transform.invoke(element) as java.lang.Iterable);
      }

      return (C)destination;
   }

   @JvmStatic
   public inline fun <T, R, C : MutableCollection<in R>> Sequence<T>.flatMapTo(destination: C, transform: (T) -> Sequence<R>): C {
      for (Object element : $this$flatMapTo) {
         CollectionsKt.addAll(destination, transform.invoke(element) as Sequence);
      }

      return (C)destination;
   }

   @JvmStatic
   public inline fun <T, K> Sequence<T>.groupBy(keySelector: (T) -> K): Map<K, List<T>> {
      val `destination$iv`: java.util.Map = new LinkedHashMap();

      for (Object element$iv : $this$groupBy) {
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
   public inline fun <T, K, V> Sequence<T>.groupBy(keySelector: (T) -> K, valueTransform: (T) -> V): Map<K, List<V>> {
      val `destination$iv`: java.util.Map = new LinkedHashMap();

      for (Object element$iv : $this$groupBy) {
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
   public inline fun <T, K, M : MutableMap<in K, MutableList<T>>> Sequence<T>.groupByTo(destination: M, keySelector: (T) -> K): M {
      for (Object element : $this$groupByTo) {
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
   public inline fun <T, K, V, M : MutableMap<in K, MutableList<V>>> Sequence<T>.groupByTo(destination: M, keySelector: (T) -> K, valueTransform: (T) -> V): M {
      for (Object element : $this$groupByTo) {
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
   public inline fun <T, K> Sequence<T>.groupingBy(crossinline keySelector: (T) -> K): Grouping<T, K> {
      return new kotlin.sequences.SequencesKt___SequencesKt.groupingBy.1(`$this$groupingBy`, keySelector);
   }

   @JvmStatic
   public fun <T, R> Sequence<T>.map(transform: (T) -> R): Sequence<R> {
      return new TransformingSequence(`$this$map`, transform);
   }

   @JvmStatic
   public fun <T, R> Sequence<T>.mapIndexed(transform: (Int, T) -> R): Sequence<R> {
      return new TransformingIndexedSequence(`$this$mapIndexed`, transform);
   }

   @JvmStatic
   public fun <T, R : Any> Sequence<T>.mapIndexedNotNull(transform: (Int, T) -> R?): Sequence<R> {
      return SequencesKt.filterNotNull(new TransformingIndexedSequence(`$this$mapIndexedNotNull`, transform));
   }

   @JvmStatic
   public inline fun <T, R : Any, C : MutableCollection<in R>> Sequence<T>.mapIndexedNotNullTo(destination: C, transform: (Int, T) -> R?): C {
      var `index$iv`: Int = 0;

      for (Object item$iv : $this$mapIndexedNotNullTo) {
         val var9: Int = `index$iv`++;
         if (var9 < 0) {
            CollectionsKt.throwIndexOverflow();
         }

         val var15: Any = transform.invoke(var9, `item$iv`);
         if (var15 != null) {
            destination.add(var15);
         }
      }

      return (C)destination;
   }

   @JvmStatic
   public inline fun <T, R, C : MutableCollection<in R>> Sequence<T>.mapIndexedTo(destination: C, transform: (Int, T) -> R): C {
      var index: Int = 0;

      for (Object item : $this$mapIndexedTo) {
         val var7: Int = index++;
         if (var7 < 0) {
            CollectionsKt.throwIndexOverflow();
         }

         destination.add(transform.invoke(var7, item));
      }

      return (C)destination;
   }

   @JvmStatic
   public fun <T, R : Any> Sequence<T>.mapNotNull(transform: (T) -> R?): Sequence<R> {
      return SequencesKt.filterNotNull(new TransformingSequence(`$this$mapNotNull`, transform));
   }

   @JvmStatic
   public inline fun <T, R : Any, C : MutableCollection<in R>> Sequence<T>.mapNotNullTo(destination: C, transform: (T) -> R?): C {
      for (Object element$iv : $this$mapNotNullTo) {
         val var10000: Any = transform.invoke(`element$iv`);
         if (var10000 != null) {
            destination.add(var10000);
         }
      }

      return (C)destination;
   }

   @JvmStatic
   public inline fun <T, R, C : MutableCollection<in R>> Sequence<T>.mapTo(destination: C, transform: (T) -> R): C {
      for (Object item : $this$mapTo) {
         destination.add(transform.invoke(item));
      }

      return (C)destination;
   }

   @JvmStatic
   public fun <T> Sequence<T>.withIndex(): Sequence<IndexedValue<T>> {
      return new IndexingSequence(`$this$withIndex`);
   }

   @JvmStatic
   public fun <T> Sequence<T>.distinct(): Sequence<T> {
      return (Sequence<T>)SequencesKt.distinctBy(`$this$distinct`, SequencesKt___SequencesKt::distinct$lambda$0$SequencesKt___SequencesKt);
   }

   @JvmStatic
   public fun <T, K> Sequence<T>.distinctBy(selector: (T) -> K): Sequence<T> {
      return new DistinctSequence(`$this$distinctBy`, selector);
   }

   @JvmStatic
   public fun <T> Sequence<T>.toMutableSet(): MutableSet<T> {
      val set: LinkedHashSet = new LinkedHashSet();

      for (Object item : $this$toMutableSet) {
         set.add(item);
      }

      return set;
   }

   @JvmStatic
   public inline fun <T> Sequence<T>.all(predicate: (T) -> Boolean): Boolean {
      for (Object element : $this$all) {
         if (!predicate.invoke(element) as java.lang.Boolean) {
            return false;
         }
      }

      return true;
   }

   @JvmStatic
   public fun <T> Sequence<T>.any(): Boolean {
      return `$this$any`.iterator().hasNext();
   }

   @JvmStatic
   public inline fun <T> Sequence<T>.any(predicate: (T) -> Boolean): Boolean {
      for (Object element : $this$any) {
         if (predicate.invoke(element) as java.lang.Boolean) {
            return true;
         }
      }

      return false;
   }

   @JvmStatic
   public fun <T> Sequence<T>.count(): Int {
      val count: Int = 0;

      for (Object element : $this$count) {
         if (++count < 0) {
            CollectionsKt.throwCountOverflow();
         }
      }

      return count;
   }

   @JvmStatic
   public inline fun <T> Sequence<T>.count(predicate: (T) -> Boolean): Int {
      val count: Int = 0;

      for (Object element : $this$count) {
         if (predicate.invoke(element) as java.lang.Boolean) {
            if (++count < 0) {
               CollectionsKt.throwCountOverflow();
            }
         }
      }

      return count;
   }

   @JvmStatic
   public inline fun <T, R> Sequence<T>.fold(initial: R, operation: (R, T) -> R): R {
      var accumulator: Any = initial;

      for (Object element : $this$fold) {
         accumulator = operation.invoke(accumulator, element);
      }

      return (R)accumulator;
   }

   @JvmStatic
   public inline fun <T, R> Sequence<T>.foldIndexed(initial: R, operation: (Int, R, T) -> R): R {
      var index: Int = 0;
      var accumulator: Any = initial;

      for (Object element : $this$foldIndexed) {
         val var8: Int = index++;
         if (var8 < 0) {
            CollectionsKt.throwIndexOverflow();
         }

         accumulator = operation.invoke(var8, accumulator, element);
      }

      return (R)accumulator;
   }

   @JvmStatic
   public inline fun <T> Sequence<T>.forEach(action: (T) -> Unit) {
      for (Object element : $this$forEach) {
         action.invoke(element);
      }
   }

   @JvmStatic
   public inline fun <T> Sequence<T>.forEachIndexed(action: (Int, T) -> Unit) {
      var index: Int = 0;

      for (Object item : $this$forEachIndexed) {
         val var6: Int = index++;
         if (var6 < 0) {
            CollectionsKt.throwIndexOverflow();
         }

         action.invoke(var6, item);
      }
   }

   @SinceKotlin(version = "1.7")
   @JvmName(name = "maxOrThrow")
   @JvmStatic
   public fun Sequence<Double>.max(): Double {
      val iterator: java.util.Iterator = `$this$max`.iterator();
      if (!iterator.hasNext()) {
         throw new NoSuchElementException();
      } else {
         var max: Double = (iterator.next() as java.lang.Number).doubleValue();

         while (iterator.hasNext()) {
            max = Math.max(max, (iterator.next() as java.lang.Number).doubleValue());
         }

         return max;
      }
   }

   @SinceKotlin(version = "1.7")
   @JvmName(name = "maxOrThrow")
   @JvmStatic
   public fun Sequence<Float>.max(): Float {
      val iterator: java.util.Iterator = `$this$max`.iterator();
      if (!iterator.hasNext()) {
         throw new NoSuchElementException();
      } else {
         var max: Float = (iterator.next() as java.lang.Number).floatValue();

         while (iterator.hasNext()) {
            max = Math.max(max, (iterator.next() as java.lang.Number).floatValue());
         }

         return max;
      }
   }

   @SinceKotlin(version = "1.7")
   @JvmName(name = "maxOrThrow")
   @JvmStatic
   public fun <T : Comparable<T>> Sequence<T>.max(): T {
      val iterator: java.util.Iterator = `$this$max`.iterator();
      if (!iterator.hasNext()) {
         throw new NoSuchElementException();
      } else {
         var max: java.lang.Comparable = iterator.next() as java.lang.Comparable;

         while (iterator.hasNext()) {
            val e: java.lang.Comparable = iterator.next() as java.lang.Comparable;
            if (max.compareTo(e) < 0) {
               max = e;
            }
         }

         return (T)max;
      }
   }

   @SinceKotlin(version = "1.7")
   @JvmName(name = "maxByOrThrow")
   @JvmStatic
   public inline fun <T, R : Comparable<R>> Sequence<T>.maxBy(selector: (T) -> R): T {
      val iterator: java.util.Iterator = `$this$maxBy`.iterator();
      if (!iterator.hasNext()) {
         throw new NoSuchElementException();
      } else {
         var maxElem: Any = iterator.next();
         if (!iterator.hasNext()) {
            return (T)maxElem;
         } else {
            var maxValue: java.lang.Comparable = selector.invoke(maxElem) as java.lang.Comparable;

            do {
               val e: Any = iterator.next();
               val v: java.lang.Comparable = selector.invoke(e) as java.lang.Comparable;
               if (maxValue.compareTo(v) < 0) {
                  maxElem = e;
                  maxValue = v;
               }
            } while (iterator.hasNext());

            return (T)maxElem;
         }
      }
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public inline fun <T, R : Comparable<R>> Sequence<T>.maxByOrNull(selector: (T) -> R): T? {
      val iterator: java.util.Iterator = `$this$maxByOrNull`.iterator();
      if (!iterator.hasNext()) {
         return null;
      } else {
         var maxElem: Any = iterator.next();
         if (!iterator.hasNext()) {
            return (T)maxElem;
         } else {
            var maxValue: java.lang.Comparable = selector.invoke(maxElem) as java.lang.Comparable;

            do {
               val e: Any = iterator.next();
               val v: java.lang.Comparable = selector.invoke(e) as java.lang.Comparable;
               if (maxValue.compareTo(v) < 0) {
                  maxElem = e;
                  maxValue = v;
               }
            } while (iterator.hasNext());

            return (T)maxElem;
         }
      }
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @InlineOnly
   @JvmStatic
   public inline fun <T> Sequence<T>.maxOf(selector: (T) -> Double): Double {
      val iterator: java.util.Iterator = `$this$maxOf`.iterator();
      if (!iterator.hasNext()) {
         throw new NoSuchElementException();
      } else {
         var maxValue: Double = (selector.invoke(iterator.next()) as java.lang.Number).doubleValue();

         while (iterator.hasNext()) {
            maxValue = Math.max(maxValue, (selector.invoke(iterator.next()) as java.lang.Number).doubleValue());
         }

         return maxValue;
      }
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @InlineOnly
   @JvmStatic
   public inline fun <T> Sequence<T>.maxOf(selector: (T) -> Float): Float {
      val iterator: java.util.Iterator = `$this$maxOf`.iterator();
      if (!iterator.hasNext()) {
         throw new NoSuchElementException();
      } else {
         var maxValue: Float = (selector.invoke(iterator.next()) as java.lang.Number).floatValue();

         while (iterator.hasNext()) {
            maxValue = Math.max(maxValue, (selector.invoke(iterator.next()) as java.lang.Number).floatValue());
         }

         return maxValue;
      }
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @InlineOnly
   @JvmStatic
   public inline fun <T, R : Comparable<R>> Sequence<T>.maxOf(selector: (T) -> R): R {
      val iterator: java.util.Iterator = `$this$maxOf`.iterator();
      if (!iterator.hasNext()) {
         throw new NoSuchElementException();
      } else {
         var maxValue: java.lang.Comparable = selector.invoke(iterator.next()) as java.lang.Comparable;

         while (iterator.hasNext()) {
            val v: java.lang.Comparable = selector.invoke(iterator.next()) as java.lang.Comparable;
            if (maxValue.compareTo(v) < 0) {
               maxValue = v;
            }
         }

         return (R)maxValue;
      }
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @InlineOnly
   @JvmStatic
   public inline fun <T> Sequence<T>.maxOfOrNull(selector: (T) -> Double): Double? {
      val iterator: java.util.Iterator = `$this$maxOfOrNull`.iterator();
      if (!iterator.hasNext()) {
         return null;
      } else {
         var maxValue: Double = (selector.invoke(iterator.next()) as java.lang.Number).doubleValue();

         while (iterator.hasNext()) {
            maxValue = Math.max(maxValue, (selector.invoke(iterator.next()) as java.lang.Number).doubleValue());
         }

         return maxValue;
      }
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @InlineOnly
   @JvmStatic
   public inline fun <T> Sequence<T>.maxOfOrNull(selector: (T) -> Float): Float? {
      val iterator: java.util.Iterator = `$this$maxOfOrNull`.iterator();
      if (!iterator.hasNext()) {
         return null;
      } else {
         var maxValue: Float = (selector.invoke(iterator.next()) as java.lang.Number).floatValue();

         while (iterator.hasNext()) {
            maxValue = Math.max(maxValue, (selector.invoke(iterator.next()) as java.lang.Number).floatValue());
         }

         return maxValue;
      }
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @InlineOnly
   @JvmStatic
   public inline fun <T, R : Comparable<R>> Sequence<T>.maxOfOrNull(selector: (T) -> R): R? {
      val iterator: java.util.Iterator = `$this$maxOfOrNull`.iterator();
      if (!iterator.hasNext()) {
         return null;
      } else {
         var maxValue: java.lang.Comparable = selector.invoke(iterator.next()) as java.lang.Comparable;

         while (iterator.hasNext()) {
            val v: java.lang.Comparable = selector.invoke(iterator.next()) as java.lang.Comparable;
            if (maxValue.compareTo(v) < 0) {
               maxValue = v;
            }
         }

         return (R)maxValue;
      }
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @InlineOnly
   @JvmStatic
   public inline fun <T, R> Sequence<T>.maxOfWith(comparator: Comparator<in R>, selector: (T) -> R): R {
      val iterator: java.util.Iterator = `$this$maxOfWith`.iterator();
      if (!iterator.hasNext()) {
         throw new NoSuchElementException();
      } else {
         var maxValue: Any = selector.invoke(iterator.next());

         while (iterator.hasNext()) {
            val v: Any = selector.invoke(iterator.next());
            if (comparator.compare(maxValue, v) < 0) {
               maxValue = v;
            }
         }

         return (R)maxValue;
      }
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @InlineOnly
   @JvmStatic
   public inline fun <T, R> Sequence<T>.maxOfWithOrNull(comparator: Comparator<in R>, selector: (T) -> R): R? {
      val iterator: java.util.Iterator = `$this$maxOfWithOrNull`.iterator();
      if (!iterator.hasNext()) {
         return null;
      } else {
         var maxValue: Any = selector.invoke(iterator.next());

         while (iterator.hasNext()) {
            val v: Any = selector.invoke(iterator.next());
            if (comparator.compare(maxValue, v) < 0) {
               maxValue = v;
            }
         }

         return (R)maxValue;
      }
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public fun Sequence<Double>.maxOrNull(): Double? {
      val iterator: java.util.Iterator = `$this$maxOrNull`.iterator();
      if (!iterator.hasNext()) {
         return null;
      } else {
         var max: Double = (iterator.next() as java.lang.Number).doubleValue();

         while (iterator.hasNext()) {
            max = Math.max(max, (iterator.next() as java.lang.Number).doubleValue());
         }

         return max;
      }
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public fun Sequence<Float>.maxOrNull(): Float? {
      val iterator: java.util.Iterator = `$this$maxOrNull`.iterator();
      if (!iterator.hasNext()) {
         return null;
      } else {
         var max: Float = (iterator.next() as java.lang.Number).floatValue();

         while (iterator.hasNext()) {
            max = Math.max(max, (iterator.next() as java.lang.Number).floatValue());
         }

         return max;
      }
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public fun <T : Comparable<T>> Sequence<T>.maxOrNull(): T? {
      val iterator: java.util.Iterator = `$this$maxOrNull`.iterator();
      if (!iterator.hasNext()) {
         return null;
      } else {
         var max: java.lang.Comparable = iterator.next() as java.lang.Comparable;

         while (iterator.hasNext()) {
            val e: java.lang.Comparable = iterator.next() as java.lang.Comparable;
            if (max.compareTo(e) < 0) {
               max = e;
            }
         }

         return (T)max;
      }
   }

   @SinceKotlin(version = "1.7")
   @JvmName(name = "maxWithOrThrow")
   @JvmStatic
   public fun <T> Sequence<T>.maxWith(comparator: Comparator<in T>): T {
      val iterator: java.util.Iterator = `$this$maxWith`.iterator();
      if (!iterator.hasNext()) {
         throw new NoSuchElementException();
      } else {
         var max: Any = iterator.next();

         while (iterator.hasNext()) {
            val e: Any = iterator.next();
            if (comparator.compare(max, e) < 0) {
               max = e;
            }
         }

         return (T)max;
      }
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public fun <T> Sequence<T>.maxWithOrNull(comparator: Comparator<in T>): T? {
      val iterator: java.util.Iterator = `$this$maxWithOrNull`.iterator();
      if (!iterator.hasNext()) {
         return null;
      } else {
         var max: Any = iterator.next();

         while (iterator.hasNext()) {
            val e: Any = iterator.next();
            if (comparator.compare(max, e) < 0) {
               max = e;
            }
         }

         return (T)max;
      }
   }

   @SinceKotlin(version = "1.7")
   @JvmName(name = "minOrThrow")
   @JvmStatic
   public fun Sequence<Double>.min(): Double {
      val iterator: java.util.Iterator = `$this$min`.iterator();
      if (!iterator.hasNext()) {
         throw new NoSuchElementException();
      } else {
         var min: Double = (iterator.next() as java.lang.Number).doubleValue();

         while (iterator.hasNext()) {
            min = Math.min(min, (iterator.next() as java.lang.Number).doubleValue());
         }

         return min;
      }
   }

   @SinceKotlin(version = "1.7")
   @JvmName(name = "minOrThrow")
   @JvmStatic
   public fun Sequence<Float>.min(): Float {
      val iterator: java.util.Iterator = `$this$min`.iterator();
      if (!iterator.hasNext()) {
         throw new NoSuchElementException();
      } else {
         var min: Float = (iterator.next() as java.lang.Number).floatValue();

         while (iterator.hasNext()) {
            min = Math.min(min, (iterator.next() as java.lang.Number).floatValue());
         }

         return min;
      }
   }

   @SinceKotlin(version = "1.7")
   @JvmName(name = "minOrThrow")
   @JvmStatic
   public fun <T : Comparable<T>> Sequence<T>.min(): T {
      val iterator: java.util.Iterator = `$this$min`.iterator();
      if (!iterator.hasNext()) {
         throw new NoSuchElementException();
      } else {
         var min: java.lang.Comparable = iterator.next() as java.lang.Comparable;

         while (iterator.hasNext()) {
            val e: java.lang.Comparable = iterator.next() as java.lang.Comparable;
            if (min.compareTo(e) > 0) {
               min = e;
            }
         }

         return (T)min;
      }
   }

   @SinceKotlin(version = "1.7")
   @JvmName(name = "minByOrThrow")
   @JvmStatic
   public inline fun <T, R : Comparable<R>> Sequence<T>.minBy(selector: (T) -> R): T {
      val iterator: java.util.Iterator = `$this$minBy`.iterator();
      if (!iterator.hasNext()) {
         throw new NoSuchElementException();
      } else {
         var minElem: Any = iterator.next();
         if (!iterator.hasNext()) {
            return (T)minElem;
         } else {
            var minValue: java.lang.Comparable = selector.invoke(minElem) as java.lang.Comparable;

            do {
               val e: Any = iterator.next();
               val v: java.lang.Comparable = selector.invoke(e) as java.lang.Comparable;
               if (minValue.compareTo(v) > 0) {
                  minElem = e;
                  minValue = v;
               }
            } while (iterator.hasNext());

            return (T)minElem;
         }
      }
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public inline fun <T, R : Comparable<R>> Sequence<T>.minByOrNull(selector: (T) -> R): T? {
      val iterator: java.util.Iterator = `$this$minByOrNull`.iterator();
      if (!iterator.hasNext()) {
         return null;
      } else {
         var minElem: Any = iterator.next();
         if (!iterator.hasNext()) {
            return (T)minElem;
         } else {
            var minValue: java.lang.Comparable = selector.invoke(minElem) as java.lang.Comparable;

            do {
               val e: Any = iterator.next();
               val v: java.lang.Comparable = selector.invoke(e) as java.lang.Comparable;
               if (minValue.compareTo(v) > 0) {
                  minElem = e;
                  minValue = v;
               }
            } while (iterator.hasNext());

            return (T)minElem;
         }
      }
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @InlineOnly
   @JvmStatic
   public inline fun <T> Sequence<T>.minOf(selector: (T) -> Double): Double {
      val iterator: java.util.Iterator = `$this$minOf`.iterator();
      if (!iterator.hasNext()) {
         throw new NoSuchElementException();
      } else {
         var minValue: Double = (selector.invoke(iterator.next()) as java.lang.Number).doubleValue();

         while (iterator.hasNext()) {
            minValue = Math.min(minValue, (selector.invoke(iterator.next()) as java.lang.Number).doubleValue());
         }

         return minValue;
      }
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @InlineOnly
   @JvmStatic
   public inline fun <T> Sequence<T>.minOf(selector: (T) -> Float): Float {
      val iterator: java.util.Iterator = `$this$minOf`.iterator();
      if (!iterator.hasNext()) {
         throw new NoSuchElementException();
      } else {
         var minValue: Float = (selector.invoke(iterator.next()) as java.lang.Number).floatValue();

         while (iterator.hasNext()) {
            minValue = Math.min(minValue, (selector.invoke(iterator.next()) as java.lang.Number).floatValue());
         }

         return minValue;
      }
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @InlineOnly
   @JvmStatic
   public inline fun <T, R : Comparable<R>> Sequence<T>.minOf(selector: (T) -> R): R {
      val iterator: java.util.Iterator = `$this$minOf`.iterator();
      if (!iterator.hasNext()) {
         throw new NoSuchElementException();
      } else {
         var minValue: java.lang.Comparable = selector.invoke(iterator.next()) as java.lang.Comparable;

         while (iterator.hasNext()) {
            val v: java.lang.Comparable = selector.invoke(iterator.next()) as java.lang.Comparable;
            if (minValue.compareTo(v) > 0) {
               minValue = v;
            }
         }

         return (R)minValue;
      }
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @InlineOnly
   @JvmStatic
   public inline fun <T> Sequence<T>.minOfOrNull(selector: (T) -> Double): Double? {
      val iterator: java.util.Iterator = `$this$minOfOrNull`.iterator();
      if (!iterator.hasNext()) {
         return null;
      } else {
         var minValue: Double = (selector.invoke(iterator.next()) as java.lang.Number).doubleValue();

         while (iterator.hasNext()) {
            minValue = Math.min(minValue, (selector.invoke(iterator.next()) as java.lang.Number).doubleValue());
         }

         return minValue;
      }
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @InlineOnly
   @JvmStatic
   public inline fun <T> Sequence<T>.minOfOrNull(selector: (T) -> Float): Float? {
      val iterator: java.util.Iterator = `$this$minOfOrNull`.iterator();
      if (!iterator.hasNext()) {
         return null;
      } else {
         var minValue: Float = (selector.invoke(iterator.next()) as java.lang.Number).floatValue();

         while (iterator.hasNext()) {
            minValue = Math.min(minValue, (selector.invoke(iterator.next()) as java.lang.Number).floatValue());
         }

         return minValue;
      }
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @InlineOnly
   @JvmStatic
   public inline fun <T, R : Comparable<R>> Sequence<T>.minOfOrNull(selector: (T) -> R): R? {
      val iterator: java.util.Iterator = `$this$minOfOrNull`.iterator();
      if (!iterator.hasNext()) {
         return null;
      } else {
         var minValue: java.lang.Comparable = selector.invoke(iterator.next()) as java.lang.Comparable;

         while (iterator.hasNext()) {
            val v: java.lang.Comparable = selector.invoke(iterator.next()) as java.lang.Comparable;
            if (minValue.compareTo(v) > 0) {
               minValue = v;
            }
         }

         return (R)minValue;
      }
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @InlineOnly
   @JvmStatic
   public inline fun <T, R> Sequence<T>.minOfWith(comparator: Comparator<in R>, selector: (T) -> R): R {
      val iterator: java.util.Iterator = `$this$minOfWith`.iterator();
      if (!iterator.hasNext()) {
         throw new NoSuchElementException();
      } else {
         var minValue: Any = selector.invoke(iterator.next());

         while (iterator.hasNext()) {
            val v: Any = selector.invoke(iterator.next());
            if (comparator.compare(minValue, v) > 0) {
               minValue = v;
            }
         }

         return (R)minValue;
      }
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @InlineOnly
   @JvmStatic
   public inline fun <T, R> Sequence<T>.minOfWithOrNull(comparator: Comparator<in R>, selector: (T) -> R): R? {
      val iterator: java.util.Iterator = `$this$minOfWithOrNull`.iterator();
      if (!iterator.hasNext()) {
         return null;
      } else {
         var minValue: Any = selector.invoke(iterator.next());

         while (iterator.hasNext()) {
            val v: Any = selector.invoke(iterator.next());
            if (comparator.compare(minValue, v) > 0) {
               minValue = v;
            }
         }

         return (R)minValue;
      }
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public fun Sequence<Double>.minOrNull(): Double? {
      val iterator: java.util.Iterator = `$this$minOrNull`.iterator();
      if (!iterator.hasNext()) {
         return null;
      } else {
         var min: Double = (iterator.next() as java.lang.Number).doubleValue();

         while (iterator.hasNext()) {
            min = Math.min(min, (iterator.next() as java.lang.Number).doubleValue());
         }

         return min;
      }
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public fun Sequence<Float>.minOrNull(): Float? {
      val iterator: java.util.Iterator = `$this$minOrNull`.iterator();
      if (!iterator.hasNext()) {
         return null;
      } else {
         var min: Float = (iterator.next() as java.lang.Number).floatValue();

         while (iterator.hasNext()) {
            min = Math.min(min, (iterator.next() as java.lang.Number).floatValue());
         }

         return min;
      }
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public fun <T : Comparable<T>> Sequence<T>.minOrNull(): T? {
      val iterator: java.util.Iterator = `$this$minOrNull`.iterator();
      if (!iterator.hasNext()) {
         return null;
      } else {
         var min: java.lang.Comparable = iterator.next() as java.lang.Comparable;

         while (iterator.hasNext()) {
            val e: java.lang.Comparable = iterator.next() as java.lang.Comparable;
            if (min.compareTo(e) > 0) {
               min = e;
            }
         }

         return (T)min;
      }
   }

   @SinceKotlin(version = "1.7")
   @JvmName(name = "minWithOrThrow")
   @JvmStatic
   public fun <T> Sequence<T>.minWith(comparator: Comparator<in T>): T {
      val iterator: java.util.Iterator = `$this$minWith`.iterator();
      if (!iterator.hasNext()) {
         throw new NoSuchElementException();
      } else {
         var min: Any = iterator.next();

         while (iterator.hasNext()) {
            val e: Any = iterator.next();
            if (comparator.compare(min, e) > 0) {
               min = e;
            }
         }

         return (T)min;
      }
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public fun <T> Sequence<T>.minWithOrNull(comparator: Comparator<in T>): T? {
      val iterator: java.util.Iterator = `$this$minWithOrNull`.iterator();
      if (!iterator.hasNext()) {
         return null;
      } else {
         var min: Any = iterator.next();

         while (iterator.hasNext()) {
            val e: Any = iterator.next();
            if (comparator.compare(min, e) > 0) {
               min = e;
            }
         }

         return (T)min;
      }
   }

   @JvmStatic
   public fun <T> Sequence<T>.none(): Boolean {
      return !`$this$none`.iterator().hasNext();
   }

   @JvmStatic
   public inline fun <T> Sequence<T>.none(predicate: (T) -> Boolean): Boolean {
      for (Object element : $this$none) {
         if (predicate.invoke(element) as java.lang.Boolean) {
            return false;
         }
      }

      return true;
   }

   @SinceKotlin(version = "1.1")
   @JvmStatic
   public fun <T> Sequence<T>.onEach(action: (T) -> Unit): Sequence<T> {
      return SequencesKt.map(`$this$onEach`, SequencesKt___SequencesKt::onEach$lambda$0$SequencesKt___SequencesKt);
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public fun <T> Sequence<T>.onEachIndexed(action: (Int, T) -> Unit): Sequence<T> {
      return SequencesKt.mapIndexed(`$this$onEachIndexed`, SequencesKt___SequencesKt::onEachIndexed$lambda$0$SequencesKt___SequencesKt);
   }

   @JvmStatic
   public inline fun <S, T : S> Sequence<T>.reduce(operation: (S, T) -> S): S {
      val iterator: java.util.Iterator = `$this$reduce`.iterator();
      if (!iterator.hasNext()) {
         throw new UnsupportedOperationException("Empty sequence can't be reduced.");
      } else {
         var accumulator: Any = iterator.next();

         while (iterator.hasNext()) {
            accumulator = operation.invoke(accumulator, iterator.next());
         }

         return (S)accumulator;
      }
   }

   @JvmStatic
   public inline fun <S, T : S> Sequence<T>.reduceIndexed(operation: (Int, S, T) -> S): S {
      val iterator: java.util.Iterator = `$this$reduceIndexed`.iterator();
      if (!iterator.hasNext()) {
         throw new UnsupportedOperationException("Empty sequence can't be reduced.");
      } else {
         var index: Int = 1;
         var accumulator: Any = iterator.next();

         while (iterator.hasNext()) {
            val var6: Int = index++;
            if (var6 < 0) {
               CollectionsKt.throwIndexOverflow();
            }

            accumulator = operation.invoke(var6, accumulator, iterator.next());
         }

         return (S)accumulator;
      }
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public inline fun <S, T : S> Sequence<T>.reduceIndexedOrNull(operation: (Int, S, T) -> S): S? {
      val iterator: java.util.Iterator = `$this$reduceIndexedOrNull`.iterator();
      if (!iterator.hasNext()) {
         return null;
      } else {
         var index: Int = 1;
         var accumulator: Any = iterator.next();

         while (iterator.hasNext()) {
            val var6: Int = index++;
            if (var6 < 0) {
               CollectionsKt.throwIndexOverflow();
            }

            accumulator = operation.invoke(var6, accumulator, iterator.next());
         }

         return (S)accumulator;
      }
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public inline fun <S, T : S> Sequence<T>.reduceOrNull(operation: (S, T) -> S): S? {
      val iterator: java.util.Iterator = `$this$reduceOrNull`.iterator();
      if (!iterator.hasNext()) {
         return null;
      } else {
         var accumulator: Any = iterator.next();

         while (iterator.hasNext()) {
            accumulator = operation.invoke(accumulator, iterator.next());
         }

         return (S)accumulator;
      }
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public fun <T, R> Sequence<T>.runningFold(initial: R, operation: (R, T) -> R): Sequence<R> {
      return SequencesKt.sequence(new kotlin.sequences.SequencesKt___SequencesKt.runningFold.1(initial, `$this$runningFold`, operation, null));
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public fun <T, R> Sequence<T>.runningFoldIndexed(initial: R, operation: (Int, R, T) -> R): Sequence<R> {
      return SequencesKt.sequence(new kotlin.sequences.SequencesKt___SequencesKt.runningFoldIndexed.1(initial, `$this$runningFoldIndexed`, operation, null));
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public fun <S, T : S> Sequence<T>.runningReduce(operation: (S, T) -> S): Sequence<S> {
      return SequencesKt.sequence(new kotlin.sequences.SequencesKt___SequencesKt.runningReduce.1(`$this$runningReduce`, operation, null));
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public fun <S, T : S> Sequence<T>.runningReduceIndexed(operation: (Int, S, T) -> S): Sequence<S> {
      return SequencesKt.sequence(new kotlin.sequences.SequencesKt___SequencesKt.runningReduceIndexed.1(`$this$runningReduceIndexed`, operation, null));
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public fun <T, R> Sequence<T>.scan(initial: R, operation: (R, T) -> R): Sequence<R> {
      return (Sequence<R>)SequencesKt.runningFold(`$this$scan`, initial, operation);
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public fun <T, R> Sequence<T>.scanIndexed(initial: R, operation: (Int, R, T) -> R): Sequence<R> {
      return (Sequence<R>)SequencesKt.runningFoldIndexed(`$this$scanIndexed`, initial, operation);
   }

   @Deprecated(message = "Use sumOf instead.", replaceWith = @ReplaceWith(expression = "this.sumOf(selector)", imports = []))
   @DeprecatedSinceKotlin(warningSince = "1.5")
   @JvmStatic
   public inline fun <T> Sequence<T>.sumBy(selector: (T) -> Int): Int {
      var sum: Int = 0;

      for (Object element : $this$sumBy) {
         sum += (selector.invoke(element) as java.lang.Number).intValue();
      }

      return sum;
   }

   @Deprecated(message = "Use sumOf instead.", replaceWith = @ReplaceWith(expression = "this.sumOf(selector)", imports = []))
   @DeprecatedSinceKotlin(warningSince = "1.5")
   @JvmStatic
   public inline fun <T> Sequence<T>.sumByDouble(selector: (T) -> Double): Double {
      var sum: Double = 0.0;

      for (Object element : $this$sumByDouble) {
         sum += (selector.invoke(element) as java.lang.Number).doubleValue();
      }

      return sum;
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @JvmName(name = "sumOfDouble")
   @InlineOnly
   @JvmStatic
   public inline fun <T> Sequence<T>.sumOf(selector: (T) -> Double): Double {
      var sum: Double = 0.0;

      for (Object element : $this$sumOf) {
         sum += (selector.invoke(element) as java.lang.Number).doubleValue();
      }

      return sum;
   }

   @SinceKotlin(version = "1.4")
   @JvmName(name = "sumOfInt")
   @InlineOnly
   @JvmStatic
   public inline fun <T> Sequence<T>.sumOf(selector: (T) -> Int): Int {
      var sum: Int = 0;

      for (Object element : $this$sumOf) {
         sum += (selector.invoke(element) as java.lang.Number).intValue();
      }

      return sum;
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @JvmName(name = "sumOfLong")
   @InlineOnly
   @JvmStatic
   public inline fun <T> Sequence<T>.sumOf(selector: (T) -> Long): Long {
      var sum: Long = 0L;

      for (Object element : $this$sumOf) {
         sum += (selector.invoke(element) as java.lang.Number).longValue();
      }

      return sum;
   }

   @SinceKotlin(version = "1.5")
   @JvmName(name = "sumOfUInt")
   @InlineOnly
   @JvmStatic
   public inline fun <T> Sequence<T>.sumOf(selector: (T) -> UInt): UInt {
      var sum: Int = UInt.constructor-impl(0);

      for (Object element : $this$sumOf) {
         sum = UInt.constructor-impl(sum + (selector.invoke(element) as UInt).unbox-impl());
      }

      return sum;
   }

   @SinceKotlin(version = "1.5")
   @OverloadResolutionByLambdaReturnType
   @JvmName(name = "sumOfULong")
   @InlineOnly
   @JvmStatic
   public inline fun <T> Sequence<T>.sumOf(selector: (T) -> ULong): ULong {
      var sum: Long = ULong.constructor-impl(0L);

      for (Object element : $this$sumOf) {
         sum = ULong.constructor-impl(sum + (selector.invoke(element) as ULong).unbox-impl());
      }

      return sum;
   }

   @JvmStatic
   public fun <T : Any> Sequence<T?>.requireNoNulls(): Sequence<T> {
      return SequencesKt.map(`$this$requireNoNulls`, SequencesKt___SequencesKt::requireNoNulls$lambda$0$SequencesKt___SequencesKt);
   }

   @SinceKotlin(version = "1.2")
   @JvmStatic
   public fun <T> Sequence<T>.chunked(size: Int): Sequence<List<T>> {
      return SequencesKt.windowed(`$this$chunked`, size, size, true);
   }

   @SinceKotlin(version = "1.2")
   @JvmStatic
   public fun <T, R> Sequence<T>.chunked(size: Int, transform: (List<T>) -> R): Sequence<R> {
      return SequencesKt.windowed(`$this$chunked`, size, size, true, transform);
   }

   @JvmStatic
   public operator fun <T> Sequence<T>.minus(element: T): Sequence<T> {
      return new kotlin.sequences.SequencesKt___SequencesKt.minus.1(`$this$minus`, element);
   }

   @JvmStatic
   public operator fun <T> Sequence<T>.minus(elements: Array<out T>): Sequence<T> {
      return (Sequence<T>)(if (elements.length == 0) `$this$minus` else new kotlin.sequences.SequencesKt___SequencesKt.minus.2(`$this$minus`, elements));
   }

   @JvmStatic
   public operator fun <T> Sequence<T>.minus(elements: Iterable<T>): Sequence<T> {
      return new 3(elements, `$this$minus`);
   }

   @JvmStatic
   public operator fun <T> Sequence<T>.minus(elements: Sequence<T>): Sequence<T> {
      return new 4(elements, `$this$minus`);
   }

   @InlineOnly
   @JvmStatic
   public inline fun <T> Sequence<T>.minusElement(element: T): Sequence<T> {
      return (Sequence<T>)SequencesKt.minus(`$this$minusElement`, element);
   }

   @JvmStatic
   public inline fun <T> Sequence<T>.partition(predicate: (T) -> Boolean): Pair<List<T>, List<T>> {
      val first: ArrayList = new ArrayList();
      val second: ArrayList = new ArrayList();

      for (Object element : $this$partition) {
         if (predicate.invoke(element) as java.lang.Boolean) {
            first.add(element);
         } else {
            second.add(element);
         }
      }

      return new Pair<>(first, second);
   }

   @JvmStatic
   public operator fun <T> Sequence<T>.plus(element: T): Sequence<T> {
      return SequencesKt.flatten(SequencesKt.sequenceOf(new Sequence[]{`$this$plus`, SequencesKt.sequenceOf(element)}));
   }

   @JvmStatic
   public operator fun <T> Sequence<T>.plus(elements: Array<out T>): Sequence<T> {
      return (Sequence<T>)SequencesKt.plus(`$this$plus`, ArraysKt.asList(elements));
   }

   @JvmStatic
   public operator fun <T> Sequence<T>.plus(elements: Iterable<T>): Sequence<T> {
      return SequencesKt.flatten(SequencesKt.sequenceOf(new Sequence[]{`$this$plus`, CollectionsKt.asSequence(elements)}));
   }

   @JvmStatic
   public operator fun <T> Sequence<T>.plus(elements: Sequence<T>): Sequence<T> {
      return SequencesKt.flatten(SequencesKt.sequenceOf(new Sequence[]{`$this$plus`, elements}));
   }

   @InlineOnly
   @JvmStatic
   public inline fun <T> Sequence<T>.plusElement(element: T): Sequence<T> {
      return (Sequence<T>)SequencesKt.plus(`$this$plusElement`, element);
   }

   @SinceKotlin(version = "1.2")
   @JvmStatic
   public fun <T> Sequence<T>.windowed(size: Int, step: Int = 1, partialWindows: Boolean = false): Sequence<List<T>> {
      return SlidingWindowKt.windowedSequence(`$this$windowed`, size, step, partialWindows, false);
   }

   @SinceKotlin(version = "1.2")
   @JvmStatic
   public fun <T, R> Sequence<T>.windowed(size: Int, step: Int = 1, partialWindows: Boolean = false, transform: (List<T>) -> R): Sequence<R> {
      return SequencesKt.map(SlidingWindowKt.windowedSequence(`$this$windowed`, size, step, partialWindows, true), transform);
   }

   @JvmStatic
   public infix fun <T, R> Sequence<T>.zip(other: Sequence<R>): Sequence<Pair<T, R>> {
      return new MergingSequence<>(`$this$zip`, other, SequencesKt___SequencesKt::zip$lambda$0$SequencesKt___SequencesKt);
   }

   @JvmStatic
   public fun <T, R, V> Sequence<T>.zip(other: Sequence<R>, transform: (T, R) -> V): Sequence<V> {
      return new MergingSequence(`$this$zip`, other, transform);
   }

   @SinceKotlin(version = "1.2")
   @JvmStatic
   public fun <T> Sequence<T>.zipWithNext(): Sequence<Pair<T, T>> {
      return SequencesKt.zipWithNext(`$this$zipWithNext`, SequencesKt___SequencesKt::zipWithNext$lambda$0$SequencesKt___SequencesKt);
   }

   @SinceKotlin(version = "1.2")
   @JvmStatic
   public fun <T, R> Sequence<T>.zipWithNext(transform: (T, T) -> R): Sequence<R> {
      return SequencesKt.sequence(new kotlin.sequences.SequencesKt___SequencesKt.zipWithNext.2(`$this$zipWithNext`, transform, null));
   }

   @JvmStatic
   public fun <T, A : Appendable> Sequence<T>.joinTo(
      buffer: A,
      separator: CharSequence = ...,
      prefix: CharSequence = ...,
      postfix: CharSequence = ...,
      limit: Int = ...,
      truncated: CharSequence = ...,
      transform: ((T) -> CharSequence)? = ...
   ): A {
      buffer.append(prefix);
      val count: Int = 0;

      for (Object element : $this$joinTo) {
         if (++count > 1) {
            buffer.append(separator);
         }

         if (limit >= 0 && count > limit) {
            break;
         }

         StringsKt.appendElement(buffer, element, transform);
      }

      if (limit >= 0 && count > limit) {
         buffer.append(truncated);
      }

      buffer.append(postfix);
      return (A)buffer;
   }

   @JvmStatic
   public fun <T> Sequence<T>.joinToString(
      separator: CharSequence = ", " as java.lang.CharSequence,
      prefix: CharSequence = "" as java.lang.CharSequence,
      postfix: CharSequence = "" as java.lang.CharSequence,
      limit: Int = -1,
      truncated: CharSequence = "..." as java.lang.CharSequence,
      transform: ((T) -> CharSequence)? = null
   ): String {
      return SequencesKt.joinTo(`$this$joinToString`, new StringBuilder(), separator, prefix, postfix, limit, truncated, transform).toString();
   }

   @JvmStatic
   public fun <T> Sequence<T>.asIterable(): Iterable<T> {
      return new kotlin.sequences.SequencesKt___SequencesKt.asIterable..inlined.Iterable.1(`$this$asIterable`);
   }

   @InlineOnly
   @JvmStatic
   public inline fun <T> Sequence<T>.asSequence(): Sequence<T> {
      return `$this$asSequence`;
   }

   @JvmName(name = "averageOfByte")
   @JvmStatic
   public fun Sequence<Byte>.average(): Double {
      var sum: Double = 0.0;
      val count: Int = 0;
      val var4: java.util.Iterator = `$this$average`.iterator();

      while (var4.hasNext()) {
         sum += (var4.next() as java.lang.Number).byteValue();
         if (++count < 0) {
            CollectionsKt.throwCountOverflow();
         }
      }

      return if (count == 0) java.lang.Double.NaN else sum / count;
   }

   @JvmName(name = "averageOfShort")
   @JvmStatic
   public fun Sequence<Short>.average(): Double {
      var sum: Double = 0.0;
      val count: Int = 0;
      val var4: java.util.Iterator = `$this$average`.iterator();

      while (var4.hasNext()) {
         sum += (var4.next() as java.lang.Number).shortValue();
         if (++count < 0) {
            CollectionsKt.throwCountOverflow();
         }
      }

      return if (count == 0) java.lang.Double.NaN else sum / count;
   }

   @JvmName(name = "averageOfInt")
   @JvmStatic
   public fun Sequence<Int>.average(): Double {
      var sum: Double = 0.0;
      val count: Int = 0;
      val var4: java.util.Iterator = `$this$average`.iterator();

      while (var4.hasNext()) {
         sum += (var4.next() as java.lang.Number).intValue();
         if (++count < 0) {
            CollectionsKt.throwCountOverflow();
         }
      }

      return if (count == 0) java.lang.Double.NaN else sum / count;
   }

   @JvmName(name = "averageOfLong")
   @JvmStatic
   public fun Sequence<Long>.average(): Double {
      var sum: Double = 0.0;
      val count: Int = 0;
      val var4: java.util.Iterator = `$this$average`.iterator();

      while (var4.hasNext()) {
         sum += (var4.next() as java.lang.Number).longValue();
         if (++count < 0) {
            CollectionsKt.throwCountOverflow();
         }
      }

      return if (count == 0) java.lang.Double.NaN else sum / count;
   }

   @JvmName(name = "averageOfFloat")
   @JvmStatic
   public fun Sequence<Float>.average(): Double {
      var sum: Double = 0.0;
      val count: Int = 0;
      val var4: java.util.Iterator = `$this$average`.iterator();

      while (var4.hasNext()) {
         sum += (var4.next() as java.lang.Number).floatValue();
         if (++count < 0) {
            CollectionsKt.throwCountOverflow();
         }
      }

      return if (count == 0) java.lang.Double.NaN else sum / count;
   }

   @JvmName(name = "averageOfDouble")
   @JvmStatic
   public fun Sequence<Double>.average(): Double {
      var sum: Double = 0.0;
      val count: Int = 0;
      val var4: java.util.Iterator = `$this$average`.iterator();

      while (var4.hasNext()) {
         sum += (var4.next() as java.lang.Number).doubleValue();
         if (++count < 0) {
            CollectionsKt.throwCountOverflow();
         }
      }

      return if (count == 0) java.lang.Double.NaN else sum / count;
   }

   @JvmName(name = "sumOfByte")
   @JvmStatic
   public fun Sequence<Byte>.sum(): Int {
      var sum: Int = 0;
      val var2: java.util.Iterator = `$this$sum`.iterator();

      while (var2.hasNext()) {
         sum += (var2.next() as java.lang.Number).byteValue();
      }

      return sum;
   }

   @JvmName(name = "sumOfShort")
   @JvmStatic
   public fun Sequence<Short>.sum(): Int {
      var sum: Int = 0;
      val var2: java.util.Iterator = `$this$sum`.iterator();

      while (var2.hasNext()) {
         sum += (var2.next() as java.lang.Number).shortValue();
      }

      return sum;
   }

   @JvmName(name = "sumOfInt")
   @JvmStatic
   public fun Sequence<Int>.sum(): Int {
      var sum: Int = 0;
      val var2: java.util.Iterator = `$this$sum`.iterator();

      while (var2.hasNext()) {
         sum += (var2.next() as java.lang.Number).intValue();
      }

      return sum;
   }

   @JvmName(name = "sumOfLong")
   @JvmStatic
   public fun Sequence<Long>.sum(): Long {
      var sum: Long = 0L;
      val var3: java.util.Iterator = `$this$sum`.iterator();

      while (var3.hasNext()) {
         sum += (var3.next() as java.lang.Number).longValue();
      }

      return sum;
   }

   @JvmName(name = "sumOfFloat")
   @JvmStatic
   public fun Sequence<Float>.sum(): Float {
      var sum: Float = 0.0F;
      val var2: java.util.Iterator = `$this$sum`.iterator();

      while (var2.hasNext()) {
         sum += (var2.next() as java.lang.Number).floatValue();
      }

      return sum;
   }

   @JvmName(name = "sumOfDouble")
   @JvmStatic
   public fun Sequence<Double>.sum(): Double {
      var sum: Double = 0.0;
      val var3: java.util.Iterator = `$this$sum`.iterator();

      while (var3.hasNext()) {
         sum += (var3.next() as java.lang.Number).doubleValue();
      }

      return sum;
   }

   @JvmStatic
   fun `elementAt$lambda$0$SequencesKt___SequencesKt`(`$index`: Int, it: Int): Any {
      throw new IndexOutOfBoundsException("Sequence doesn't contain element at index $`$index`.");
   }

   @JvmStatic
   fun `filterIndexed$lambda$0$SequencesKt___SequencesKt`(`$predicate`: Function2, it: IndexedValue): Boolean {
      return `$predicate`.invoke(it.getIndex(), it.getValue()) as java.lang.Boolean;
   }

   @JvmStatic
   fun `filterIndexed$lambda$1$SequencesKt___SequencesKt`(it: IndexedValue): Any {
      return it.getValue();
   }

   @JvmStatic
   fun `filterNotNull$lambda$0$SequencesKt___SequencesKt`(it: Any): Boolean {
      return it == null;
   }

   @JvmStatic
   fun `distinct$lambda$0$SequencesKt___SequencesKt`(it: Any): Any {
      return it;
   }

   @JvmStatic
   fun `onEach$lambda$0$SequencesKt___SequencesKt`(`$action`: Function1, it: Any): Any {
      `$action`.invoke(it);
      return it;
   }

   @JvmStatic
   fun `onEachIndexed$lambda$0$SequencesKt___SequencesKt`(`$action`: Function2, index: Int, element: Any): Any {
      `$action`.invoke(index, element);
      return element;
   }

   @JvmStatic
   fun `requireNoNulls$lambda$0$SequencesKt___SequencesKt`(`$this_requireNoNulls`: Sequence, it: Any): Any {
      if (it == null) {
         throw new IllegalArgumentException("null element found in $`$this_requireNoNulls`.");
      } else {
         return it;
      }
   }

   @JvmStatic
   fun `zip$lambda$0$SequencesKt___SequencesKt`(t1: Any, t2: Any): Pair {
      return TuplesKt.to(t1, t2);
   }

   @JvmStatic
   fun `zipWithNext$lambda$0$SequencesKt___SequencesKt`(a: Any, b: Any): Pair {
      return TuplesKt.to(a, b);
   }

   open fun SequencesKt___SequencesKt() {
   }
}

package kotlin.collections

import java.util.LinkedHashSet
import kotlin.internal.InlineOnly
import kotlin.jvm.internal.SourceDebugExtension

@SourceDebugExtension(["SMAP\n_Sets.kt\nKotlin\n*S Kotlin\n*F\n+ 1 _Sets.kt\nkotlin/collections/SetsKt___SetsKt\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,140:1\n865#2,2:141\n855#2,2:143\n1#3:145\n*S KotlinDebug\n*F\n+ 1 _Sets.kt\nkotlin/collections/SetsKt___SetsKt\n*L\n29#1:141,2\n53#1:143,2\n*E\n"])
internal class SetsKt___SetsKt : SetsKt__SetsKt {
   @JvmStatic
   public operator fun <T> Set<T>.minus(element: T): Set<T> {
      val result: LinkedHashSet = new LinkedHashSet(MapsKt.mapCapacity(`$this$minus`.size()));
      var removed: Boolean = false;
      val `$this$filterTo$iv`: java.lang.Iterable = `$this$minus`;
      val `destination$iv`: java.util.Collection = result;

      for (Object element$iv : $this$filterTo$iv) {
         val var10000: Boolean;
         if (!removed && `element$iv` == element) {
            removed = true;
            var10000 = false;
         } else {
            var10000 = true;
         }

         if (var10000) {
            `destination$iv`.add(`element$iv`);
         }
      }

      return `destination$iv` as MutableSet<T>;
   }

   @JvmStatic
   public operator fun <T> Set<T>.minus(elements: Array<out T>): Set<T> {
      val result: LinkedHashSet = new LinkedHashSet(`$this$minus`);
      CollectionsKt.removeAll(result, elements);
      return result;
   }

   @JvmStatic
   public operator fun <T> Set<T>.minus(elements: Iterable<T>): Set<T> {
      val other: java.util.Collection = CollectionsKt.convertToListIfNotCollection(elements);
      if (other.isEmpty()) {
         return CollectionsKt.toSet(`$this$minus`);
      } else if (other is java.util.Set) {
         val var10: java.lang.Iterable = `$this$minus`;
         val `destination$iv`: java.util.Collection = new LinkedHashSet();

         for (Object element$iv : var10) {
            if (!(other as java.util.Set).contains(`element$iv`)) {
               `destination$iv`.add(`element$iv`);
            }
         }

         return `destination$iv` as MutableSet<T>;
      } else {
         val result: LinkedHashSet = new LinkedHashSet(`$this$minus`);
         result.removeAll(other);
         return result;
      }
   }

   @JvmStatic
   public operator fun <T> Set<T>.minus(elements: Sequence<T>): Set<T> {
      val result: LinkedHashSet = new LinkedHashSet(`$this$minus`);
      CollectionsKt.removeAll(result, elements);
      return result;
   }

   @InlineOnly
   @JvmStatic
   public inline fun <T> Set<T>.minusElement(element: T): Set<T> {
      return (java.util.Set<T>)SetsKt.minus(`$this$minusElement`, element);
   }

   @JvmStatic
   public operator fun <T> Set<T>.plus(element: T): Set<T> {
      val result: LinkedHashSet = new LinkedHashSet(MapsKt.mapCapacity(`$this$plus`.size() + 1));
      result.addAll(`$this$plus`);
      result.add(element);
      return result;
   }

   @JvmStatic
   public operator fun <T> Set<T>.plus(elements: Array<out T>): Set<T> {
      val result: LinkedHashSet = new LinkedHashSet(MapsKt.mapCapacity(`$this$plus`.size() + elements.length));
      result.addAll(`$this$plus`);
      CollectionsKt.addAll(result, elements);
      return result;
   }

   @JvmStatic
   public operator fun <T> Set<T>.plus(elements: Iterable<T>): Set<T> {
      val var10000: Int = CollectionsKt.collectionSizeOrNull(elements);
      val result: LinkedHashSet = new LinkedHashSet(
         MapsKt.mapCapacity(if (var10000 != null) `$this$plus`.size() + var10000.intValue() else `$this$plus`.size() * 2)
      );
      result.addAll(`$this$plus`);
      CollectionsKt.addAll(result, elements);
      return result;
   }

   @JvmStatic
   public operator fun <T> Set<T>.plus(elements: Sequence<T>): Set<T> {
      val result: LinkedHashSet = new LinkedHashSet(MapsKt.mapCapacity(`$this$plus`.size() * 2));
      result.addAll(`$this$plus`);
      CollectionsKt.addAll(result, elements);
      return result;
   }

   @InlineOnly
   @JvmStatic
   public inline fun <T> Set<T>.plusElement(element: T): Set<T> {
      return (java.util.Set<T>)SetsKt.plus(`$this$plusElement`, element);
   }

   open fun SetsKt___SetsKt() {
   }
}

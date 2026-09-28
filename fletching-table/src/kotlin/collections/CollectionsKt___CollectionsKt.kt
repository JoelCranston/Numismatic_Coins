package kotlin.collections

import java.util.ArrayList
import java.util.Comparator
import java.util.HashSet
import java.util.LinkedHashMap
import java.util.LinkedHashSet
import java.util.NoSuchElementException
import java.util.RandomAccess
import kotlin.comparisons.ComparisonsKt__ComparisonsKt.compareBy.2
import kotlin.comparisons.ComparisonsKt__ComparisonsKt.compareByDescending.1
import kotlin.contracts.InvocationKind
import kotlin.internal.HidesMembers
import kotlin.internal.InlineOnly
import kotlin.jvm.internal.SourceDebugExtension
import kotlin.random.Random

@SourceDebugExtension(["SMAP\n_Collections.kt\nKotlin\n*S Kotlin\n*F\n+ 1 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 ArraysJVM.kt\nkotlin/collections/ArraysKt__ArraysJVMKt\n+ 4 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n+ 5 Iterators.kt\nkotlin/collections/CollectionsKt__IteratorsKt\n*L\n1#1,3794:1\n295#1,2:3795\n528#1,7:3797\n543#1,6:3804\n865#1,2:3811\n796#1:3813\n1878#1,2:3814\n797#1,2:3816\n1880#1:3818\n799#1:3819\n1878#1,3:3820\n817#1,2:3823\n855#1,2:3825\n1267#1,4:3831\n1236#1,4:3835\n1252#1,4:3839\n1299#1,4:3843\n1460#1,5:3847\n1475#1,5:3852\n1516#1,3:3857\n1519#1,3:3867\n1534#1,3:3870\n1537#1,3:3880\n1634#1,3:3897\n1604#1,4:3900\n1593#1:3904\n1878#1,2:3905\n1880#1:3908\n1594#1:3909\n1878#1,3:3910\n1625#1:3913\n1869#1:3914\n1870#1:3916\n1626#1:3917\n1869#1,2:3918\n1878#1,3:3920\n2967#1,3:3923\n2970#1,6:3927\n2992#1,3:3933\n2995#1,7:3937\n865#1,2:3944\n827#1:3946\n855#1,2:3947\n827#1:3949\n855#1,2:3950\n827#1:3952\n855#1,2:3953\n3516#1,8:3959\n3544#1,7:3967\n3575#1,10:3974\n1#2:3810\n1#2:3907\n1#2:3915\n1#2:3926\n1#2:3936\n37#3,2:3827\n37#3,2:3829\n382#4,7:3860\n382#4,7:3873\n382#4,7:3883\n382#4,7:3890\n32#5,2:3955\n32#5,2:3957\n*S KotlinDebug\n*F\n+ 1 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n174#1:3795,2\n184#1:3797,7\n194#1:3804,6\n774#1:3811,2\n785#1:3813\n785#1:3814,2\n785#1:3816,2\n785#1:3818\n785#1:3819\n796#1:3820,3\n808#1:3823,2\n827#1:3825,2\n1194#1:3831,4\n1209#1:3835,4\n1223#1:3839,4\n1286#1:3843,4\n1374#1:3847,5\n1387#1:3852,5\n1491#1:3857,3\n1491#1:3867,3\n1504#1:3870,3\n1504#1:3880,3\n1563#1:3897,3\n1573#1:3900,4\n1583#1:3904\n1583#1:3905,2\n1583#1:3908\n1583#1:3909\n1593#1:3910,3\n1617#1:3913\n1617#1:3914\n1617#1:3916\n1617#1:3917\n1625#1:3918,2\n2767#1:3920,3\n3067#1:3923,3\n3067#1:3927,6\n3084#1:3933,3\n3084#1:3937,7\n3254#1:3944,2\n3262#1:3946\n3262#1:3947,2\n3272#1:3949\n3272#1:3950,2\n3282#1:3952\n3282#1:3953,2\n3505#1:3959,8\n3533#1:3967,7\n3562#1:3974,10\n1583#1:3907\n1617#1:3915\n3067#1:3926\n3084#1:3936\n1042#1:3827,2\n1089#1:3829,2\n1491#1:3860,7\n1504#1:3873,7\n1518#1:3883,7\n1536#1:3890,7\n3450#1:3955,2\n3492#1:3957,2\n*E\n"])
internal class CollectionsKt___CollectionsKt : CollectionsKt___CollectionsJvmKt {
   @InlineOnly
   @JvmStatic
   public inline operator fun <T> List<T>.component1(): T {
      return (T)`$this$component1`.get(0);
   }

   @InlineOnly
   @JvmStatic
   public inline operator fun <T> List<T>.component2(): T {
      return (T)`$this$component2`.get(1);
   }

   @InlineOnly
   @JvmStatic
   public inline operator fun <T> List<T>.component3(): T {
      return (T)`$this$component3`.get(2);
   }

   @InlineOnly
   @JvmStatic
   public inline operator fun <T> List<T>.component4(): T {
      return (T)`$this$component4`.get(3);
   }

   @InlineOnly
   @JvmStatic
   public inline operator fun <T> List<T>.component5(): T {
      return (T)`$this$component5`.get(4);
   }

   @JvmStatic
   public operator fun <T> Iterable<T>.contains(element: T): Boolean {
      if (`$this$contains` is java.util.Collection) {
         return (`$this$contains` as java.util.Collection).contains(element);
      } else {
         return CollectionsKt.indexOf(`$this$contains`, element) >= 0;
      }
   }

   @JvmStatic
   public fun <T> Iterable<T>.elementAt(index: Int): T {
      return (T)(if (`$this$elementAt` is java.util.List)
         (`$this$elementAt` as java.util.List).get(index)
         else
         CollectionsKt.elementAtOrElse(`$this$elementAt`, index, CollectionsKt___CollectionsKt::elementAt$lambda$0$CollectionsKt___CollectionsKt));
   }

   @InlineOnly
   @JvmStatic
   public inline fun <T> List<T>.elementAt(index: Int): T {
      return (T)`$this$elementAt`.get(index);
   }

   @JvmStatic
   public fun <T> Iterable<T>.elementAtOrElse(index: Int, defaultValue: (Int) -> T): T {
      contract {
         callsInPlace(defaultValue, InvocationKind.AT_MOST_ONCE)
      }

      if (`$this$elementAtOrElse` is java.util.List) {
         val var6: java.util.List = `$this$elementAtOrElse` as java.util.List;
         return (T)(if (0 <= index && index < (`$this$elementAtOrElse` as java.util.List).size())
            (`$this$elementAtOrElse` as java.util.List).get(index)
            else
            defaultValue.invoke(index));
      } else if (index < 0) {
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

   @InlineOnly
   @JvmStatic
   public inline fun <T> List<T>.elementAtOrElse(index: Int, defaultValue: (Int) -> T): T {
      contract {
         callsInPlace(defaultValue, InvocationKind.AT_MOST_ONCE)
      }

      return (T)(if (0 <= index && index < `$this$elementAtOrElse`.size()) `$this$elementAtOrElse`.get(index) else defaultValue.invoke(index));
   }

   @JvmStatic
   public fun <T> Iterable<T>.elementAtOrNull(index: Int): T? {
      if (`$this$elementAtOrNull` is java.util.List) {
         return (T)CollectionsKt.getOrNull(`$this$elementAtOrNull` as java.util.List, index);
      } else if (index < 0) {
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
   public inline fun <T> List<T>.elementAtOrNull(index: Int): T? {
      return (T)CollectionsKt.getOrNull(`$this$elementAtOrNull`, index);
   }

   @InlineOnly
   @JvmStatic
   public inline fun <T> Iterable<T>.find(predicate: (T) -> Boolean): T? {
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
   public inline fun <T> Iterable<T>.findLast(predicate: (T) -> Boolean): T? {
      var `last$iv`: Any = null;

      for (Object element$iv : $this$findLast) {
         if (predicate.invoke(`element$iv`) as java.lang.Boolean) {
            `last$iv` = `element$iv`;
         }
      }

      return (T)`last$iv`;
   }

   @InlineOnly
   @JvmStatic
   public inline fun <T> List<T>.findLast(predicate: (T) -> Boolean): T? {
      val `iterator$iv`: java.util.ListIterator = `$this$findLast`.listIterator(`$this$findLast`.size());

      var var10000: Any;
      while (true) {
         if (`iterator$iv`.hasPrevious()) {
            val `element$iv`: Any = `iterator$iv`.previous();
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

   @JvmStatic
   public fun <T> Iterable<T>.first(): T {
      if (`$this$first` is java.util.List) {
         return (T)CollectionsKt.first(`$this$first` as java.util.List);
      } else {
         val iterator: java.util.Iterator = `$this$first`.iterator();
         if (!iterator.hasNext()) {
            throw new NoSuchElementException("Collection is empty.");
         } else {
            return (T)iterator.next();
         }
      }
   }

   @JvmStatic
   public fun <T> List<T>.first(): T {
      if (`$this$first`.isEmpty()) {
         throw new NoSuchElementException("List is empty.");
      } else {
         return (T)`$this$first`.get(0);
      }
   }

   @JvmStatic
   public inline fun <T> Iterable<T>.first(predicate: (T) -> Boolean): T {
      for (Object element : $this$first) {
         if (predicate.invoke(element) as java.lang.Boolean) {
            return (T)element;
         }
      }

      throw new NoSuchElementException("Collection contains no element matching the predicate.");
   }

   @SinceKotlin(version = "1.5")
   @InlineOnly
   @JvmStatic
   public inline fun <T, R : Any> Iterable<T>.firstNotNullOf(transform: (T) -> R?): R {
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
         throw new NoSuchElementException("No element of the collection was transformed to a non-null value.");
      } else {
         return (R)var10000;
      }
   }

   @SinceKotlin(version = "1.5")
   @InlineOnly
   @JvmStatic
   public inline fun <T, R : Any> Iterable<T>.firstNotNullOfOrNull(transform: (T) -> R?): R? {
      for (Object element : $this$firstNotNullOfOrNull) {
         val result: Any = transform.invoke(element);
         if (result != null) {
            return (R)result;
         }
      }

      return null;
   }

   @JvmStatic
   public fun <T> Iterable<T>.firstOrNull(): T? {
      if (`$this$firstOrNull` is java.util.List) {
         return (T)(if ((`$this$firstOrNull` as java.util.List).isEmpty()) null else (`$this$firstOrNull` as java.util.List).get(0));
      } else {
         val iterator: java.util.Iterator = `$this$firstOrNull`.iterator();
         return (T)(if (!iterator.hasNext()) null else iterator.next());
      }
   }

   @JvmStatic
   public fun <T> List<T>.firstOrNull(): T? {
      return (T)(if (`$this$firstOrNull`.isEmpty()) null else `$this$firstOrNull`.get(0));
   }

   @JvmStatic
   public inline fun <T> Iterable<T>.firstOrNull(predicate: (T) -> Boolean): T? {
      for (Object element : $this$firstOrNull) {
         if (predicate.invoke(element) as java.lang.Boolean) {
            return (T)element;
         }
      }

      return null;
   }

   @InlineOnly
   @JvmStatic
   public inline fun <T> List<T>.getOrElse(index: Int, defaultValue: (Int) -> T): T {
      contract {
         callsInPlace(defaultValue, InvocationKind.AT_MOST_ONCE)
      }

      return (T)(if (0 <= index && index < `$this$getOrElse`.size()) `$this$getOrElse`.get(index) else defaultValue.invoke(index));
   }

   @JvmStatic
   public fun <T> List<T>.getOrNull(index: Int): T? {
      return (T)(if (0 <= index && index < `$this$getOrNull`.size()) `$this$getOrNull`.get(index) else null);
   }

   @JvmStatic
   public fun <T> Iterable<T>.indexOf(element: T): Int {
      if (`$this$indexOf` is java.util.List) {
         return (`$this$indexOf` as java.util.List).indexOf(element);
      } else {
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
   }

   @JvmStatic
   public fun <T> List<T>.indexOf(element: T): Int {
      return `$this$indexOf`.indexOf(element);
   }

   @JvmStatic
   public inline fun <T> Iterable<T>.indexOfFirst(predicate: (T) -> Boolean): Int {
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
   public inline fun <T> List<T>.indexOfFirst(predicate: (T) -> Boolean): Int {
      var index: Int = 0;

      for (Object item : $this$indexOfFirst) {
         if (predicate.invoke(item) as java.lang.Boolean) {
            return index;
         }

         index++;
      }

      return -1;
   }

   @JvmStatic
   public inline fun <T> Iterable<T>.indexOfLast(predicate: (T) -> Boolean): Int {
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
   public inline fun <T> List<T>.indexOfLast(predicate: (T) -> Boolean): Int {
      val iterator: java.util.ListIterator = `$this$indexOfLast`.listIterator(`$this$indexOfLast`.size());

      while (iterator.hasPrevious()) {
         if (predicate.invoke(iterator.previous()) as java.lang.Boolean) {
            return iterator.nextIndex();
         }
      }

      return -1;
   }

   @JvmStatic
   public fun <T> Iterable<T>.last(): T {
      if (`$this$last` is java.util.List) {
         return (T)CollectionsKt.last(`$this$last` as java.util.List);
      } else {
         val iterator: java.util.Iterator = `$this$last`.iterator();
         if (!iterator.hasNext()) {
            throw new NoSuchElementException("Collection is empty.");
         } else {
            var last: Any = iterator.next();

            while (iterator.hasNext()) {
               last = iterator.next();
            }

            return (T)last;
         }
      }
   }

   @JvmStatic
   public fun <T> List<T>.last(): T {
      if (`$this$last`.isEmpty()) {
         throw new NoSuchElementException("List is empty.");
      } else {
         return (T)`$this$last`.get(CollectionsKt.getLastIndex(`$this$last`));
      }
   }

   @JvmStatic
   public inline fun <T> Iterable<T>.last(predicate: (T) -> Boolean): T {
      var last: Any = null;
      var found: Boolean = false;

      for (Object element : $this$last) {
         if (predicate.invoke(element) as java.lang.Boolean) {
            last = element;
            found = true;
         }
      }

      if (!found) {
         throw new NoSuchElementException("Collection contains no element matching the predicate.");
      } else {
         return (T)last;
      }
   }

   @JvmStatic
   public inline fun <T> List<T>.last(predicate: (T) -> Boolean): T {
      val iterator: java.util.ListIterator = `$this$last`.listIterator(`$this$last`.size());

      while (iterator.hasPrevious()) {
         val element: Any = iterator.previous();
         if (predicate.invoke(element) as java.lang.Boolean) {
            return (T)element;
         }
      }

      throw new NoSuchElementException("List contains no element matching the predicate.");
   }

   @JvmStatic
   public fun <T> Iterable<T>.lastIndexOf(element: T): Int {
      if (`$this$lastIndexOf` is java.util.List) {
         return (`$this$lastIndexOf` as java.util.List).lastIndexOf(element);
      } else {
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
   }

   @JvmStatic
   public fun <T> List<T>.lastIndexOf(element: T): Int {
      return `$this$lastIndexOf`.lastIndexOf(element);
   }

   @JvmStatic
   public fun <T> Iterable<T>.lastOrNull(): T? {
      if (`$this$lastOrNull` is java.util.List) {
         return (T)(if ((`$this$lastOrNull` as java.util.List).isEmpty())
            null
            else
            (`$this$lastOrNull` as java.util.List).get((`$this$lastOrNull` as java.util.List).size() - 1));
      } else {
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
   }

   @JvmStatic
   public fun <T> List<T>.lastOrNull(): T? {
      return (T)(if (`$this$lastOrNull`.isEmpty()) null else `$this$lastOrNull`.get(`$this$lastOrNull`.size() - 1));
   }

   @JvmStatic
   public inline fun <T> Iterable<T>.lastOrNull(predicate: (T) -> Boolean): T? {
      var last: Any = null;

      for (Object element : $this$lastOrNull) {
         if (predicate.invoke(element) as java.lang.Boolean) {
            last = element;
         }
      }

      return (T)last;
   }

   @JvmStatic
   public inline fun <T> List<T>.lastOrNull(predicate: (T) -> Boolean): T? {
      val iterator: java.util.ListIterator = `$this$lastOrNull`.listIterator(`$this$lastOrNull`.size());

      while (iterator.hasPrevious()) {
         val element: Any = iterator.previous();
         if (predicate.invoke(element) as java.lang.Boolean) {
            return (T)element;
         }
      }

      return null;
   }

   @SinceKotlin(version = "1.3")
   @InlineOnly
   @JvmStatic
   public inline fun <T> Collection<T>.random(): T {
      return (T)CollectionsKt.random(`$this$random`, Random.Default);
   }

   @SinceKotlin(version = "1.3")
   @JvmStatic
   public fun <T> Collection<T>.random(random: Random): T {
      if (`$this$random`.isEmpty()) {
         throw new NoSuchElementException("Collection is empty.");
      } else {
         return (T)CollectionsKt.elementAt(`$this$random`, random.nextInt(`$this$random`.size()));
      }
   }

   @SinceKotlin(version = "1.4")
   @InlineOnly
   @JvmStatic
   public inline fun <T> Collection<T>.randomOrNull(): T? {
      return (T)CollectionsKt.randomOrNull(`$this$randomOrNull`, Random.Default);
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public fun <T> Collection<T>.randomOrNull(random: Random): T? {
      return (T)(if (`$this$randomOrNull`.isEmpty()) null else CollectionsKt.elementAt(`$this$randomOrNull`, random.nextInt(`$this$randomOrNull`.size())));
   }

   @JvmStatic
   public fun <T> Iterable<T>.single(): T {
      if (`$this$single` is java.util.List) {
         return (T)CollectionsKt.single(`$this$single` as java.util.List);
      } else {
         val iterator: java.util.Iterator = `$this$single`.iterator();
         if (!iterator.hasNext()) {
            throw new NoSuchElementException("Collection is empty.");
         } else {
            val single: Any = iterator.next();
            if (iterator.hasNext()) {
               throw new IllegalArgumentException("Collection has more than one element.");
            } else {
               return (T)single;
            }
         }
      }
   }

   @JvmStatic
   public fun <T> List<T>.single(): T {
      switch ($this$single.size()) {
         case 0:
            throw new NoSuchElementException("List is empty.");
         case 1:
            return (T)`$this$single`.get(0);
         default:
            throw new IllegalArgumentException("List has more than one element.");
      }
   }

   @JvmStatic
   public inline fun <T> Iterable<T>.single(predicate: (T) -> Boolean): T {
      var single: Any = null;
      var found: Boolean = false;

      for (Object element : $this$single) {
         if (predicate.invoke(element) as java.lang.Boolean) {
            if (found) {
               throw new IllegalArgumentException("Collection contains more than one matching element.");
            }

            single = element;
            found = true;
         }
      }

      if (!found) {
         throw new NoSuchElementException("Collection contains no element matching the predicate.");
      } else {
         return (T)single;
      }
   }

   @JvmStatic
   public fun <T> Iterable<T>.singleOrNull(): T? {
      if (`$this$singleOrNull` is java.util.List) {
         return (T)(if ((`$this$singleOrNull` as java.util.List).size() == 1) (`$this$singleOrNull` as java.util.List).get(0) else null);
      } else {
         val iterator: java.util.Iterator = `$this$singleOrNull`.iterator();
         if (!iterator.hasNext()) {
            return null;
         } else {
            return (T)(if (iterator.hasNext()) null else iterator.next());
         }
      }
   }

   @JvmStatic
   public fun <T> List<T>.singleOrNull(): T? {
      return (T)(if (`$this$singleOrNull`.size() == 1) `$this$singleOrNull`.get(0) else null);
   }

   @JvmStatic
   public inline fun <T> Iterable<T>.singleOrNull(predicate: (T) -> Boolean): T? {
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
   public fun <T> Iterable<T>.drop(n: Int): List<T> {
      if (n < 0) {
         throw new IllegalArgumentException(("Requested element count $n is less than zero.").toString());
      } else if (n == 0) {
         return CollectionsKt.toList(`$this$drop`);
      } else {
         val var6: ArrayList;
         if (`$this$drop` is java.util.Collection) {
            val count: Int = (`$this$drop` as java.util.Collection).size() - n;
            if (count <= 0) {
               return CollectionsKt.emptyList();
            }

            if (count == 1) {
               return (java.util.List<T>)CollectionsKt.listOf(CollectionsKt.last(`$this$drop`));
            }

            var6 = new ArrayList(count);
            if (`$this$drop` is java.util.List) {
               if (`$this$drop` is RandomAccess) {
                  var index: Int = n;

                  for (int item = ((java.util.List)$this$drop).size(); index < item; index++) {
                     var6.add((`$this$drop` as java.util.List).get(index));
                  }
               } else {
                  val var10: java.util.Iterator = (`$this$drop` as java.util.List).listIterator(n);

                  while (var10.hasNext()) {
                     var6.add(var10.next());
                  }
               }

               return var6;
            }
         } else {
            var6 = new ArrayList();
         }

         var var7: Int = 0;

         for (Object item : $this$drop) {
            if (var7 >= n) {
               val var10000: java.lang.Comparable = var6.add(var13);
            } else {
               val var14: java.lang.Comparable = ++var7;
            }
         }

         return CollectionsKt.optimizeReadOnlyList(var6);
      }
   }

   @JvmStatic
   public fun <T> List<T>.dropLast(n: Int): List<T> {
      if (n < 0) {
         throw new IllegalArgumentException(("Requested element count $n is less than zero.").toString());
      } else {
         return CollectionsKt.take(`$this$dropLast`, RangesKt.coerceAtLeast(`$this$dropLast`.size() - n, 0));
      }
   }

   @JvmStatic
   public inline fun <T> List<T>.dropLastWhile(predicate: (T) -> Boolean): List<T> {
      if (!`$this$dropLastWhile`.isEmpty()) {
         val iterator: java.util.ListIterator = `$this$dropLastWhile`.listIterator(`$this$dropLastWhile`.size());

         while (iterator.hasPrevious()) {
            if (!predicate.invoke(iterator.previous()) as java.lang.Boolean) {
               return CollectionsKt.take(`$this$dropLastWhile`, iterator.nextIndex() + 1);
            }
         }
      }

      return CollectionsKt.emptyList();
   }

   @JvmStatic
   public inline fun <T> Iterable<T>.dropWhile(predicate: (T) -> Boolean): List<T> {
      var yielding: Boolean = false;
      val list: ArrayList = new ArrayList();

      for (Object item : $this$dropWhile) {
         if (yielding) {
            list.add(item);
         } else if (!predicate.invoke(item) as java.lang.Boolean) {
            list.add(item);
            yielding = true;
         }
      }

      return list;
   }

   @JvmStatic
   public inline fun <T> Iterable<T>.filter(predicate: (T) -> Boolean): List<T> {
      val `destination$iv`: java.util.Collection = new ArrayList();

      for (Object element$iv : $this$filter) {
         if (predicate.invoke(`element$iv`) as java.lang.Boolean) {
            `destination$iv`.add(`element$iv`);
         }
      }

      return `destination$iv` as MutableList<T>;
   }

   @JvmStatic
   public inline fun <T> Iterable<T>.filterIndexed(predicate: (Int, T) -> Boolean): List<T> {
      val `destination$iv`: java.util.Collection = new ArrayList();
      var `index$iv$iv`: Int = 0;

      for (Object item$iv$iv : $this$filterIndexed) {
         val var11: Int = `index$iv$iv`++;
         if (var11 < 0) {
            CollectionsKt.throwIndexOverflow();
         }

         if (predicate.invoke(var11, `item$iv$iv`) as java.lang.Boolean) {
            `destination$iv`.add(`item$iv$iv`);
         }
      }

      return `destination$iv` as MutableList<T>;
   }

   @JvmStatic
   public inline fun <T, C : MutableCollection<in T>> Iterable<T>.filterIndexedTo(destination: C, predicate: (Int, T) -> Boolean): C {
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
   public inline fun <T> Iterable<T>.filterNot(predicate: (T) -> Boolean): List<T> {
      val `destination$iv`: java.util.Collection = new ArrayList();

      for (Object element$iv : $this$filterNot) {
         if (!predicate.invoke(`element$iv`) as java.lang.Boolean) {
            `destination$iv`.add(`element$iv`);
         }
      }

      return `destination$iv` as MutableList<T>;
   }

   @JvmStatic
   public fun <T : Any> Iterable<T?>.filterNotNull(): List<T> {
      return CollectionsKt.filterNotNullTo(`$this$filterNotNull`, new ArrayList()) as MutableList<T>;
   }

   @JvmStatic
   public fun <C : MutableCollection<in T>, T : Any> Iterable<T?>.filterNotNullTo(destination: C): C {
      for (Object element : $this$filterNotNullTo) {
         if (element != null) {
            destination.add(element);
         }
      }

      return (C)destination;
   }

   @JvmStatic
   public inline fun <T, C : MutableCollection<in T>> Iterable<T>.filterNotTo(destination: C, predicate: (T) -> Boolean): C {
      for (Object element : $this$filterNotTo) {
         if (!predicate.invoke(element) as java.lang.Boolean) {
            destination.add(element);
         }
      }

      return (C)destination;
   }

   @JvmStatic
   public inline fun <T, C : MutableCollection<in T>> Iterable<T>.filterTo(destination: C, predicate: (T) -> Boolean): C {
      for (Object element : $this$filterTo) {
         if (predicate.invoke(element) as java.lang.Boolean) {
            destination.add(element);
         }
      }

      return (C)destination;
   }

   @JvmStatic
   public fun <T> List<T>.slice(indices: IntRange): List<T> {
      return if (indices.isEmpty())
         CollectionsKt.emptyList()
         else
         CollectionsKt.toList(`$this$slice`.subList(indices.getStart(), indices.getEndInclusive() + 1));
   }

   @JvmStatic
   public fun <T> List<T>.slice(indices: Iterable<Int>): List<T> {
      val size: Int = CollectionsKt.collectionSizeOrDefault(indices, 10);
      if (size == 0) {
         return CollectionsKt.emptyList();
      } else {
         val list: ArrayList = new ArrayList(size);
         val var4: java.util.Iterator = indices.iterator();

         while (var4.hasNext()) {
            list.add(`$this$slice`.get((var4.next() as java.lang.Number).intValue()));
         }

         return list;
      }
   }

   @JvmStatic
   public fun <T> Iterable<T>.take(n: Int): List<T> {
      if (n < 0) {
         throw new IllegalArgumentException(("Requested element count $n is less than zero.").toString());
      } else if (n == 0) {
         return CollectionsKt.emptyList();
      } else {
         if (`$this$take` is java.util.Collection) {
            if (n >= (`$this$take` as java.util.Collection).size()) {
               return CollectionsKt.toList(`$this$take`);
            }

            if (n == 1) {
               return (java.util.List<T>)CollectionsKt.listOf(CollectionsKt.first(`$this$take`));
            }
         }

         val count: Int = 0;
         val list: ArrayList = new ArrayList(n);

         for (Object item : $this$take) {
            list.add(item);
            if (++count == n) {
               break;
            }
         }

         return CollectionsKt.optimizeReadOnlyList(list);
      }
   }

   @JvmStatic
   public fun <T> List<T>.takeLast(n: Int): List<T> {
      if (n < 0) {
         throw new IllegalArgumentException(("Requested element count $n is less than zero.").toString());
      } else if (n == 0) {
         return CollectionsKt.emptyList();
      } else {
         val size: Int = `$this$takeLast`.size();
         if (n >= size) {
            return CollectionsKt.toList(`$this$takeLast`);
         } else if (n == 1) {
            return (java.util.List<T>)CollectionsKt.listOf(CollectionsKt.last(`$this$takeLast`));
         } else {
            val list: ArrayList = new ArrayList(n);
            if (`$this$takeLast` is RandomAccess) {
               for (int index = size - n; index < size; index++) {
                  list.add(`$this$takeLast`.get(index));
               }
            } else {
               val var8: java.util.Iterator = `$this$takeLast`.listIterator(size - n);

               while (var8.hasNext()) {
                  list.add(var8.next());
               }
            }

            return list;
         }
      }
   }

   @JvmStatic
   public inline fun <T> List<T>.takeLastWhile(predicate: (T) -> Boolean): List<T> {
      if (`$this$takeLastWhile`.isEmpty()) {
         return CollectionsKt.emptyList();
      } else {
         val iterator: java.util.ListIterator = `$this$takeLastWhile`.listIterator(`$this$takeLastWhile`.size());

         while (iterator.hasPrevious()) {
            if (!predicate.invoke(iterator.previous()) as java.lang.Boolean) {
               iterator.next();
               val expectedSize: Int = `$this$takeLastWhile`.size() - iterator.nextIndex();
               if (expectedSize == 0) {
                  return CollectionsKt.emptyList();
               }

               val var5: ArrayList = new ArrayList(expectedSize);
               val `$this$takeLastWhile_u24lambda_u240`: ArrayList = var5;

               while (iterator.hasNext()) {
                  `$this$takeLastWhile_u24lambda_u240`.add(iterator.next());
               }

               return var5;
            }
         }

         return CollectionsKt.toList(`$this$takeLastWhile`);
      }
   }

   @JvmStatic
   public inline fun <T> Iterable<T>.takeWhile(predicate: (T) -> Boolean): List<T> {
      val list: ArrayList = new ArrayList();

      for (Object item : $this$takeWhile) {
         if (!predicate.invoke(item) as java.lang.Boolean) {
            break;
         }

         list.add(item);
      }

      return list;
   }

   @JvmStatic
   public fun <T> Iterable<T>.reversed(): List<T> {
      if (`$this$reversed` is java.util.Collection && (`$this$reversed` as java.util.Collection).size() <= 1) {
         return CollectionsKt.toList(`$this$reversed`);
      } else {
         val list: java.util.List = CollectionsKt.toMutableList(`$this$reversed`);
         CollectionsKt.reverse(list);
         return list;
      }
   }

   @SinceKotlin(version = "1.3")
   @JvmStatic
   public fun <T> MutableList<T>.shuffle(random: Random) {
      for (int i = CollectionsKt.getLastIndex($this$shuffle); 0 < i; i--) {
         val j: Int = random.nextInt(i + 1);
         `$this$shuffle`.set(j, `$this$shuffle`.set(i, `$this$shuffle`.get(j)));
      }
   }

   @JvmStatic
   public inline fun <T, R : Comparable<R>> MutableList<T>.sortBy(crossinline selector: (T) -> R?) {
      if (`$this$sortBy`.size() > 1) {
         CollectionsKt.sortWith(`$this$sortBy`, new 2(selector));
      }
   }

   @JvmStatic
   public inline fun <T, R : Comparable<R>> MutableList<T>.sortByDescending(crossinline selector: (T) -> R?) {
      if (`$this$sortByDescending`.size() > 1) {
         CollectionsKt.sortWith(`$this$sortByDescending`, new 1(selector));
      }
   }

   @JvmStatic
   public fun <T : Comparable<T>> MutableList<T>.sortDescending() {
      CollectionsKt.sortWith(`$this$sortDescending`, ComparisonsKt.reverseOrder());
   }

   @JvmStatic
   public fun <T : Comparable<T>> Iterable<T>.sorted(): List<T> {
      if (`$this$sorted` is java.util.Collection) {
         if ((`$this$sorted` as java.util.Collection).size() <= 1) {
            return CollectionsKt.toList(`$this$sorted`);
         } else {
            val var5: Array<Any> = (`$this$sorted` as java.util.Collection).toArray(new java.lang.Comparable[0]);
            ArraysKt.sort(var5 as Array<java.lang.Comparable>);
            return (java.util.List<T>)ArraysKt.asList(var5);
         }
      } else {
         val `$this$toTypedArray$iv`: java.util.List = CollectionsKt.toMutableList(`$this$sorted`);
         CollectionsKt.sort(`$this$toTypedArray$iv`);
         return `$this$toTypedArray$iv`;
      }
   }

   @JvmStatic
   public inline fun <T, R : Comparable<R>> Iterable<T>.sortedBy(crossinline selector: (T) -> R?): List<T> {
      return CollectionsKt.sortedWith(`$this$sortedBy`, new 2(selector));
   }

   @JvmStatic
   public inline fun <T, R : Comparable<R>> Iterable<T>.sortedByDescending(crossinline selector: (T) -> R?): List<T> {
      return CollectionsKt.sortedWith(`$this$sortedByDescending`, new 1(selector));
   }

   @JvmStatic
   public fun <T : Comparable<T>> Iterable<T>.sortedDescending(): List<T> {
      return CollectionsKt.sortedWith(`$this$sortedDescending`, ComparisonsKt.reverseOrder());
   }

   @JvmStatic
   public fun <T> Iterable<T>.sortedWith(comparator: Comparator<in T>): List<T> {
      if (`$this$sortedWith` is java.util.Collection) {
         if ((`$this$sortedWith` as java.util.Collection).size() <= 1) {
            return CollectionsKt.toList(`$this$sortedWith`);
         } else {
            val var6: Array<Any> = (`$this$sortedWith` as java.util.Collection).toArray(new Object[0]);
            ArraysKt.sortWith(var6, comparator);
            return (java.util.List<T>)ArraysKt.asList(var6);
         }
      } else {
         val `$this$toTypedArray$iv`: java.util.List = CollectionsKt.toMutableList(`$this$sortedWith`);
         CollectionsKt.sortWith(`$this$toTypedArray$iv`, comparator);
         return `$this$toTypedArray$iv`;
      }
   }

   @JvmStatic
   public fun Collection<Boolean>.toBooleanArray(): BooleanArray {
      val result: BooleanArray = new boolean[`$this$toBooleanArray`.size()];
      var index: Int = 0;

      for (boolean element : $this$toBooleanArray) {
         result[index++] = element;
      }

      return result;
   }

   @JvmStatic
   public fun Collection<Byte>.toByteArray(): ByteArray {
      val result: ByteArray = new byte[`$this$toByteArray`.size()];
      var index: Int = 0;
      val var3: java.util.Iterator = `$this$toByteArray`.iterator();

      while (var3.hasNext()) {
         result[index++] = (var3.next() as java.lang.Number).byteValue();
      }

      return result;
   }

   @JvmStatic
   public fun Collection<Char>.toCharArray(): CharArray {
      val result: CharArray = new char[`$this$toCharArray`.size()];
      var index: Int = 0;

      for (char element : $this$toCharArray) {
         result[index++] = element;
      }

      return result;
   }

   @JvmStatic
   public fun Collection<Double>.toDoubleArray(): DoubleArray {
      val result: DoubleArray = new double[`$this$toDoubleArray`.size()];
      var index: Int = 0;
      val var3: java.util.Iterator = `$this$toDoubleArray`.iterator();

      while (var3.hasNext()) {
         result[index++] = (var3.next() as java.lang.Number).doubleValue();
      }

      return result;
   }

   @JvmStatic
   public fun Collection<Float>.toFloatArray(): FloatArray {
      val result: FloatArray = new float[`$this$toFloatArray`.size()];
      var index: Int = 0;
      val var3: java.util.Iterator = `$this$toFloatArray`.iterator();

      while (var3.hasNext()) {
         result[index++] = (var3.next() as java.lang.Number).floatValue();
      }

      return result;
   }

   @JvmStatic
   public fun Collection<Int>.toIntArray(): IntArray {
      val result: IntArray = new int[`$this$toIntArray`.size()];
      var index: Int = 0;
      val var3: java.util.Iterator = `$this$toIntArray`.iterator();

      while (var3.hasNext()) {
         result[index++] = (var3.next() as java.lang.Number).intValue();
      }

      return result;
   }

   @JvmStatic
   public fun Collection<Long>.toLongArray(): LongArray {
      val result: LongArray = new long[`$this$toLongArray`.size()];
      var index: Int = 0;
      val var3: java.util.Iterator = `$this$toLongArray`.iterator();

      while (var3.hasNext()) {
         result[index++] = (var3.next() as java.lang.Number).longValue();
      }

      return result;
   }

   @JvmStatic
   public fun Collection<Short>.toShortArray(): ShortArray {
      val result: ShortArray = new short[`$this$toShortArray`.size()];
      var index: Int = 0;
      val var3: java.util.Iterator = `$this$toShortArray`.iterator();

      while (var3.hasNext()) {
         result[index++] = (var3.next() as java.lang.Number).shortValue();
      }

      return result;
   }

   @JvmStatic
   public inline fun <T, K, V> Iterable<T>.associate(transform: (T) -> Pair<K, V>): Map<K, V> {
      val `destination$iv`: java.util.Map = new LinkedHashMap(
         RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(`$this$associate`, 10)), 16)
      );

      for (Object element$iv : $this$associate) {
         val var10: Pair = transform.invoke(`element$iv`) as Pair;
         `destination$iv`.put(var10.getFirst(), var10.getSecond());
      }

      return `destination$iv`;
   }

   @JvmStatic
   public inline fun <T, K> Iterable<T>.associateBy(keySelector: (T) -> K): Map<K, T> {
      val `destination$iv`: java.util.Map = new LinkedHashMap(
         RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(`$this$associateBy`, 10)), 16)
      );

      for (Object element$iv : $this$associateBy) {
         `destination$iv`.put(keySelector.invoke(`element$iv`), `element$iv`);
      }

      return `destination$iv`;
   }

   @JvmStatic
   public inline fun <T, K, V> Iterable<T>.associateBy(keySelector: (T) -> K, valueTransform: (T) -> V): Map<K, V> {
      val `destination$iv`: java.util.Map = new LinkedHashMap(
         RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(`$this$associateBy`, 10)), 16)
      );

      for (Object element$iv : $this$associateBy) {
         `destination$iv`.put(keySelector.invoke(`element$iv`), valueTransform.invoke(`element$iv`));
      }

      return `destination$iv`;
   }

   @JvmStatic
   public inline fun <T, K, M : MutableMap<in K, in T>> Iterable<T>.associateByTo(destination: M, keySelector: (T) -> K): M {
      for (Object element : $this$associateByTo) {
         destination.put(keySelector.invoke(element), element);
      }

      return (M)destination;
   }

   @JvmStatic
   public inline fun <T, K, V, M : MutableMap<in K, in V>> Iterable<T>.associateByTo(destination: M, keySelector: (T) -> K, valueTransform: (T) -> V): M {
      for (Object element : $this$associateByTo) {
         destination.put(keySelector.invoke(element), valueTransform.invoke(element));
      }

      return (M)destination;
   }

   @JvmStatic
   public inline fun <T, K, V, M : MutableMap<in K, in V>> Iterable<T>.associateTo(destination: M, transform: (T) -> Pair<K, V>): M {
      for (Object element : $this$associateTo) {
         val var7: Pair = transform.invoke(element) as Pair;
         destination.put(var7.getFirst(), var7.getSecond());
      }

      return (M)destination;
   }

   @SinceKotlin(version = "1.3")
   @JvmStatic
   public inline fun <K, V> Iterable<K>.associateWith(valueSelector: (K) -> V): Map<K, V> {
      val `destination$iv`: java.util.Map = new LinkedHashMap(
         RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(`$this$associateWith`, 10)), 16)
      );

      for (Object element$iv : $this$associateWith) {
         `destination$iv`.put(`element$iv`, valueSelector.invoke(`element$iv`));
      }

      return `destination$iv`;
   }

   @SinceKotlin(version = "1.3")
   @JvmStatic
   public inline fun <K, V, M : MutableMap<in K, in V>> Iterable<K>.associateWithTo(destination: M, valueSelector: (K) -> V): M {
      for (Object element : $this$associateWithTo) {
         destination.put(element, valueSelector.invoke(element));
      }

      return (M)destination;
   }

   @JvmStatic
   public fun <T, C : MutableCollection<in T>> Iterable<T>.toCollection(destination: C): C {
      for (Object item : $this$toCollection) {
         destination.add(item);
      }

      return (C)destination;
   }

   @JvmStatic
   public fun <T> Iterable<T>.toHashSet(): HashSet<T> {
      return CollectionsKt.toCollection(`$this$toHashSet`, new HashSet(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(`$this$toHashSet`, 12)))) as HashSet<T>;
   }

   @JvmStatic
   public fun <T> Iterable<T>.toList(): List<T> {
      if (`$this$toList` is java.util.Collection) {
         var var10000: java.util.List;
         switch (((java.util.Collection)$this$toList).size()) {
            case 0:
               var10000 = CollectionsKt.emptyList();
               break;
            case 1:
               var10000 = CollectionsKt.listOf(
                  if (`$this$toList` is java.util.List)
                     (`$this$toList` as java.util.List).get(0)
                     else
                     (`$this$toList` as java.util.Collection).iterator().next()
               );
               break;
            default:
               var10000 = CollectionsKt.toMutableList(`$this$toList` as java.util.Collection);
         }

         return var10000;
      } else {
         return CollectionsKt.optimizeReadOnlyList(CollectionsKt.toMutableList(`$this$toList`));
      }
   }

   @JvmStatic
   public fun <T> Iterable<T>.toMutableList(): MutableList<T> {
      return if (`$this$toMutableList` is java.util.Collection)
         CollectionsKt.toMutableList(`$this$toMutableList` as java.util.Collection)
         else
         CollectionsKt.toCollection(`$this$toMutableList`, new ArrayList()) as java.util.List;
   }

   @JvmStatic
   public fun <T> Collection<T>.toMutableList(): MutableList<T> {
      return new ArrayList(`$this$toMutableList`);
   }

   @JvmStatic
   public fun <T> Iterable<T>.toSet(): Set<T> {
      if (`$this$toSet` is java.util.Collection) {
         var var10000: java.util.Set;
         switch (((java.util.Collection)$this$toSet).size()) {
            case 0:
               var10000 = SetsKt.emptySet();
               break;
            case 1:
               var10000 = SetsKt.setOf(
                  if (`$this$toSet` is java.util.List) (`$this$toSet` as java.util.List).get(0) else (`$this$toSet` as java.util.Collection).iterator().next()
               );
               break;
            default:
               var10000 = CollectionsKt.toCollection(`$this$toSet`, new LinkedHashSet(MapsKt.mapCapacity((`$this$toSet` as java.util.Collection).size())));
         }

         return var10000;
      } else {
         return SetsKt.optimizeReadOnlySet(CollectionsKt.toCollection(`$this$toSet`, new LinkedHashSet()));
      }
   }

   @JvmStatic
   public inline fun <T, R> Iterable<T>.flatMap(transform: (T) -> Iterable<R>): List<R> {
      val `destination$iv`: java.util.Collection = new ArrayList();

      for (Object element$iv : $this$flatMap) {
         CollectionsKt.addAll(`destination$iv`, transform.invoke(`element$iv`) as java.lang.Iterable);
      }

      return `destination$iv` as MutableList<R>;
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @JvmName(name = "flatMapSequence")
   @JvmStatic
   public inline fun <T, R> Iterable<T>.flatMap(transform: (T) -> Sequence<R>): List<R> {
      val `destination$iv`: java.util.Collection = new ArrayList();

      for (Object element$iv : $this$flatMap) {
         CollectionsKt.addAll(`destination$iv`, transform.invoke(`element$iv`) as Sequence);
      }

      return `destination$iv` as MutableList<R>;
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @JvmName(name = "flatMapIndexedIterable")
   @InlineOnly
   @JvmStatic
   public inline fun <T, R> Iterable<T>.flatMapIndexed(transform: (Int, T) -> Iterable<R>): List<R> {
      val var3: java.util.Collection = new ArrayList();
      var var4: Int = 0;

      for (Object var6 : $this$flatMapIndexed) {
         val var7: Int = var4++;
         if (var7 < 0) {
            CollectionsKt.throwIndexOverflow();
         }

         CollectionsKt.addAll(var3, transform.invoke(var7, var6) as java.lang.Iterable);
      }

      return var3 as MutableList<R>;
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @JvmName(name = "flatMapIndexedSequence")
   @InlineOnly
   @JvmStatic
   public inline fun <T, R> Iterable<T>.flatMapIndexed(transform: (Int, T) -> Sequence<R>): List<R> {
      val var3: java.util.Collection = new ArrayList();
      var var4: Int = 0;

      for (Object var6 : $this$flatMapIndexed) {
         val var7: Int = var4++;
         if (var7 < 0) {
            CollectionsKt.throwIndexOverflow();
         }

         CollectionsKt.addAll(var3, transform.invoke(var7, var6) as Sequence);
      }

      return var3 as MutableList<R>;
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @JvmName(name = "flatMapIndexedIterableTo")
   @InlineOnly
   @JvmStatic
   public inline fun <T, R, C : MutableCollection<in R>> Iterable<T>.flatMapIndexedTo(destination: C, transform: (Int, T) -> Iterable<R>): C {
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
   public inline fun <T, R, C : MutableCollection<in R>> Iterable<T>.flatMapIndexedTo(destination: C, transform: (Int, T) -> Sequence<R>): C {
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

   @JvmStatic
   public inline fun <T, R, C : MutableCollection<in R>> Iterable<T>.flatMapTo(destination: C, transform: (T) -> Iterable<R>): C {
      for (Object element : $this$flatMapTo) {
         CollectionsKt.addAll(destination, transform.invoke(element) as java.lang.Iterable);
      }

      return (C)destination;
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @JvmName(name = "flatMapSequenceTo")
   @JvmStatic
   public inline fun <T, R, C : MutableCollection<in R>> Iterable<T>.flatMapTo(destination: C, transform: (T) -> Sequence<R>): C {
      for (Object element : $this$flatMapTo) {
         CollectionsKt.addAll(destination, transform.invoke(element) as Sequence);
      }

      return (C)destination;
   }

   @JvmStatic
   public inline fun <T, K> Iterable<T>.groupBy(keySelector: (T) -> K): Map<K, List<T>> {
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
   public inline fun <T, K, V> Iterable<T>.groupBy(keySelector: (T) -> K, valueTransform: (T) -> V): Map<K, List<V>> {
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
   public inline fun <T, K, M : MutableMap<in K, MutableList<T>>> Iterable<T>.groupByTo(destination: M, keySelector: (T) -> K): M {
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
   public inline fun <T, K, V, M : MutableMap<in K, MutableList<V>>> Iterable<T>.groupByTo(destination: M, keySelector: (T) -> K, valueTransform: (T) -> V): M {
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
   public inline fun <T, K> Iterable<T>.groupingBy(crossinline keySelector: (T) -> K): Grouping<T, K> {
      return new kotlin.collections.CollectionsKt___CollectionsKt.groupingBy.1(`$this$groupingBy`, keySelector);
   }

   @JvmStatic
   public inline fun <T, R> Iterable<T>.map(transform: (T) -> R): List<R> {
      val `destination$iv`: java.util.Collection = new ArrayList(CollectionsKt.collectionSizeOrDefault(`$this$map`, 10));

      for (Object item$iv : $this$map) {
         `destination$iv`.add(transform.invoke(`item$iv`));
      }

      return `destination$iv` as MutableList<R>;
   }

   @JvmStatic
   public inline fun <T, R> Iterable<T>.mapIndexed(transform: (Int, T) -> R): List<R> {
      val `destination$iv`: java.util.Collection = new ArrayList(CollectionsKt.collectionSizeOrDefault(`$this$mapIndexed`, 10));
      var `index$iv`: Int = 0;

      for (Object item$iv : $this$mapIndexed) {
         val var9: Int = `index$iv`++;
         if (var9 < 0) {
            CollectionsKt.throwIndexOverflow();
         }

         `destination$iv`.add(transform.invoke(var9, `item$iv`));
      }

      return `destination$iv` as MutableList<R>;
   }

   @JvmStatic
   public inline fun <T, R : Any> Iterable<T>.mapIndexedNotNull(transform: (Int, T) -> R?): List<R> {
      val `destination$iv`: java.util.Collection = new ArrayList();
      var `index$iv$iv`: Int = 0;

      for (Object item$iv$iv : $this$mapIndexedNotNull) {
         val var11: Int = `index$iv$iv`++;
         if (var11 < 0) {
            CollectionsKt.throwIndexOverflow();
         }

         val var17: Any = transform.invoke(var11, `item$iv$iv`);
         if (var17 != null) {
            `destination$iv`.add(var17);
         }
      }

      return `destination$iv` as MutableList<R>;
   }

   @JvmStatic
   public inline fun <T, R : Any, C : MutableCollection<in R>> Iterable<T>.mapIndexedNotNullTo(destination: C, transform: (Int, T) -> R?): C {
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
   public inline fun <T, R, C : MutableCollection<in R>> Iterable<T>.mapIndexedTo(destination: C, transform: (Int, T) -> R): C {
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
   public inline fun <T, R : Any> Iterable<T>.mapNotNull(transform: (T) -> R?): List<R> {
      val `destination$iv`: java.util.Collection = new ArrayList();

      for (Object element$iv$iv : $this$mapNotNull) {
         val var10000: Any = transform.invoke(`element$iv$iv`);
         if (var10000 != null) {
            `destination$iv`.add(var10000);
         }
      }

      return `destination$iv` as MutableList<R>;
   }

   @JvmStatic
   public inline fun <T, R : Any, C : MutableCollection<in R>> Iterable<T>.mapNotNullTo(destination: C, transform: (T) -> R?): C {
      for (Object element$iv : $this$mapNotNullTo) {
         val var10000: Any = transform.invoke(`element$iv`);
         if (var10000 != null) {
            destination.add(var10000);
         }
      }

      return (C)destination;
   }

   @JvmStatic
   public inline fun <T, R, C : MutableCollection<in R>> Iterable<T>.mapTo(destination: C, transform: (T) -> R): C {
      for (Object item : $this$mapTo) {
         destination.add(transform.invoke(item));
      }

      return (C)destination;
   }

   @JvmStatic
   public fun <T> Iterable<T>.withIndex(): Iterable<IndexedValue<T>> {
      return new IndexingIterable(CollectionsKt___CollectionsKt::withIndex$lambda$0$CollectionsKt___CollectionsKt);
   }

   @JvmStatic
   public fun <T> Iterable<T>.distinct(): List<T> {
      return CollectionsKt.toList(CollectionsKt.toMutableSet(`$this$distinct`));
   }

   @JvmStatic
   public inline fun <T, K> Iterable<T>.distinctBy(selector: (T) -> K): List<T> {
      val set: HashSet = new HashSet();
      val list: ArrayList = new ArrayList();

      for (Object e : $this$distinctBy) {
         if (set.add(selector.invoke(e))) {
            list.add(e);
         }
      }

      return list;
   }

   @JvmStatic
   public infix fun <T> Iterable<T>.intersect(other: Iterable<T>): Set<T> {
      val set: java.util.Set = CollectionsKt.toMutableSet(`$this$intersect`);
      CollectionsKt.retainAll(set, other);
      return set;
   }

   @JvmStatic
   public infix fun <T> Iterable<T>.subtract(other: Iterable<T>): Set<T> {
      val set: java.util.Set = CollectionsKt.toMutableSet(`$this$subtract`);
      CollectionsKt.removeAll(set, other);
      return set;
   }

   @JvmStatic
   public fun <T> Iterable<T>.toMutableSet(): MutableSet<T> {
      return (java.util.Set<T>)(if (`$this$toMutableSet` is java.util.Collection)
         new LinkedHashSet(`$this$toMutableSet` as java.util.Collection)
         else
         CollectionsKt.toCollection(`$this$toMutableSet`, new LinkedHashSet()) as java.util.Set);
   }

   @JvmStatic
   public infix fun <T> Iterable<T>.union(other: Iterable<T>): Set<T> {
      val set: java.util.Set = CollectionsKt.toMutableSet(`$this$union`);
      CollectionsKt.addAll(set, other);
      return set;
   }

   @JvmStatic
   public inline fun <T> Iterable<T>.all(predicate: (T) -> Boolean): Boolean {
      if (`$this$all` is java.util.Collection && (`$this$all` as java.util.Collection).isEmpty()) {
         return true;
      } else {
         for (Object element : $this$all) {
            if (!predicate.invoke(element) as java.lang.Boolean) {
               return false;
            }
         }

         return true;
      }
   }

   @JvmStatic
   public fun <T> Iterable<T>.any(): Boolean {
      if (`$this$any` is java.util.Collection) {
         return !(`$this$any` as java.util.Collection).isEmpty();
      } else {
         return `$this$any`.iterator().hasNext();
      }
   }

   @JvmStatic
   public inline fun <T> Iterable<T>.any(predicate: (T) -> Boolean): Boolean {
      if (`$this$any` is java.util.Collection && (`$this$any` as java.util.Collection).isEmpty()) {
         return false;
      } else {
         for (Object element : $this$any) {
            if (predicate.invoke(element) as java.lang.Boolean) {
               return true;
            }
         }

         return false;
      }
   }

   @JvmStatic
   public fun <T> Iterable<T>.count(): Int {
      if (`$this$count` is java.util.Collection) {
         return (`$this$count` as java.util.Collection).size();
      } else {
         val count: Int = 0;

         for (Object element : $this$count) {
            if (++count < 0) {
               CollectionsKt.throwCountOverflow();
            }
         }

         return count;
      }
   }

   @InlineOnly
   @JvmStatic
   public inline fun <T> Collection<T>.count(): Int {
      return `$this$count`.size();
   }

   @JvmStatic
   public inline fun <T> Iterable<T>.count(predicate: (T) -> Boolean): Int {
      if (`$this$count` is java.util.Collection && (`$this$count` as java.util.Collection).isEmpty()) {
         return 0;
      } else {
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
   }

   @JvmStatic
   public inline fun <T, R> Iterable<T>.fold(initial: R, operation: (R, T) -> R): R {
      var accumulator: Any = initial;

      for (Object element : $this$fold) {
         accumulator = operation.invoke(accumulator, element);
      }

      return (R)accumulator;
   }

   @JvmStatic
   public inline fun <T, R> Iterable<T>.foldIndexed(initial: R, operation: (Int, R, T) -> R): R {
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
   public inline fun <T, R> List<T>.foldRight(initial: R, operation: (T, R) -> R): R {
      var accumulator: Any = initial;
      if (!`$this$foldRight`.isEmpty()) {
         val iterator: java.util.ListIterator = `$this$foldRight`.listIterator(`$this$foldRight`.size());

         while (iterator.hasPrevious()) {
            accumulator = operation.invoke(iterator.previous(), accumulator);
         }
      }

      return (R)accumulator;
   }

   @JvmStatic
   public inline fun <T, R> List<T>.foldRightIndexed(initial: R, operation: (Int, T, R) -> R): R {
      var accumulator: Any = initial;
      if (!`$this$foldRightIndexed`.isEmpty()) {
         val iterator: java.util.ListIterator = `$this$foldRightIndexed`.listIterator(`$this$foldRightIndexed`.size());

         while (iterator.hasPrevious()) {
            accumulator = operation.invoke(iterator.previousIndex(), iterator.previous(), accumulator);
         }
      }

      return (R)accumulator;
   }

   @HidesMembers
   @JvmStatic
   public inline fun <T> Iterable<T>.forEach(action: (T) -> Unit) {
      for (Object element : $this$forEach) {
         action.invoke(element);
      }
   }

   @JvmStatic
   public inline fun <T> Iterable<T>.forEachIndexed(action: (Int, T) -> Unit) {
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
   public fun Iterable<Double>.max(): Double {
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
   public fun Iterable<Float>.max(): Float {
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
   public fun <T : Comparable<T>> Iterable<T>.max(): T {
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
   public inline fun <T, R : Comparable<R>> Iterable<T>.maxBy(selector: (T) -> R): T {
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
   public inline fun <T, R : Comparable<R>> Iterable<T>.maxByOrNull(selector: (T) -> R): T? {
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
   public inline fun <T> Iterable<T>.maxOf(selector: (T) -> Double): Double {
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
   public inline fun <T> Iterable<T>.maxOf(selector: (T) -> Float): Float {
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
   public inline fun <T, R : Comparable<R>> Iterable<T>.maxOf(selector: (T) -> R): R {
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
   public inline fun <T> Iterable<T>.maxOfOrNull(selector: (T) -> Double): Double? {
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
   public inline fun <T> Iterable<T>.maxOfOrNull(selector: (T) -> Float): Float? {
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
   public inline fun <T, R : Comparable<R>> Iterable<T>.maxOfOrNull(selector: (T) -> R): R? {
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
   public inline fun <T, R> Iterable<T>.maxOfWith(comparator: Comparator<in R>, selector: (T) -> R): R {
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
   public inline fun <T, R> Iterable<T>.maxOfWithOrNull(comparator: Comparator<in R>, selector: (T) -> R): R? {
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
   public fun Iterable<Double>.maxOrNull(): Double? {
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
   public fun Iterable<Float>.maxOrNull(): Float? {
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
   public fun <T : Comparable<T>> Iterable<T>.maxOrNull(): T? {
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
   public fun <T> Iterable<T>.maxWith(comparator: Comparator<in T>): T {
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
   public fun <T> Iterable<T>.maxWithOrNull(comparator: Comparator<in T>): T? {
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
   public fun Iterable<Double>.min(): Double {
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
   public fun Iterable<Float>.min(): Float {
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
   public fun <T : Comparable<T>> Iterable<T>.min(): T {
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
   public inline fun <T, R : Comparable<R>> Iterable<T>.minBy(selector: (T) -> R): T {
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
   public inline fun <T, R : Comparable<R>> Iterable<T>.minByOrNull(selector: (T) -> R): T? {
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
   public inline fun <T> Iterable<T>.minOf(selector: (T) -> Double): Double {
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
   public inline fun <T> Iterable<T>.minOf(selector: (T) -> Float): Float {
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
   public inline fun <T, R : Comparable<R>> Iterable<T>.minOf(selector: (T) -> R): R {
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
   public inline fun <T> Iterable<T>.minOfOrNull(selector: (T) -> Double): Double? {
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
   public inline fun <T> Iterable<T>.minOfOrNull(selector: (T) -> Float): Float? {
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
   public inline fun <T, R : Comparable<R>> Iterable<T>.minOfOrNull(selector: (T) -> R): R? {
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
   public inline fun <T, R> Iterable<T>.minOfWith(comparator: Comparator<in R>, selector: (T) -> R): R {
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
   public inline fun <T, R> Iterable<T>.minOfWithOrNull(comparator: Comparator<in R>, selector: (T) -> R): R? {
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
   public fun Iterable<Double>.minOrNull(): Double? {
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
   public fun Iterable<Float>.minOrNull(): Float? {
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
   public fun <T : Comparable<T>> Iterable<T>.minOrNull(): T? {
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
   public fun <T> Iterable<T>.minWith(comparator: Comparator<in T>): T {
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
   public fun <T> Iterable<T>.minWithOrNull(comparator: Comparator<in T>): T? {
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
   public fun <T> Iterable<T>.none(): Boolean {
      if (`$this$none` is java.util.Collection) {
         return (`$this$none` as java.util.Collection).isEmpty();
      } else {
         return !`$this$none`.iterator().hasNext();
      }
   }

   @JvmStatic
   public inline fun <T> Iterable<T>.none(predicate: (T) -> Boolean): Boolean {
      if (`$this$none` is java.util.Collection && (`$this$none` as java.util.Collection).isEmpty()) {
         return true;
      } else {
         for (Object element : $this$none) {
            if (predicate.invoke(element) as java.lang.Boolean) {
               return false;
            }
         }

         return true;
      }
   }

   @SinceKotlin(version = "1.1")
   @JvmStatic
   public inline fun <T, C : Iterable<T>> C.onEach(action: (T) -> Unit): C {
      for (Object element : $this$onEach) {
         action.invoke(element);
      }

      return (C)`$this$onEach`;
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public inline fun <T, C : Iterable<T>> C.onEachIndexed(action: (Int, T) -> Unit): C {
      var `index$iv`: Int = 0;

      for (Object item$iv : $this$onEachIndexed) {
         val var11: Int = `index$iv`++;
         if (var11 < 0) {
            CollectionsKt.throwIndexOverflow();
         }

         action.invoke(var11, `item$iv`);
      }

      return (C)`$this$onEachIndexed`;
   }

   @JvmStatic
   public inline fun <S, T : S> Iterable<T>.reduce(operation: (S, T) -> S): S {
      val iterator: java.util.Iterator = `$this$reduce`.iterator();
      if (!iterator.hasNext()) {
         throw new UnsupportedOperationException("Empty collection can't be reduced.");
      } else {
         var accumulator: Any = iterator.next();

         while (iterator.hasNext()) {
            accumulator = operation.invoke(accumulator, iterator.next());
         }

         return (S)accumulator;
      }
   }

   @JvmStatic
   public inline fun <S, T : S> Iterable<T>.reduceIndexed(operation: (Int, S, T) -> S): S {
      val iterator: java.util.Iterator = `$this$reduceIndexed`.iterator();
      if (!iterator.hasNext()) {
         throw new UnsupportedOperationException("Empty collection can't be reduced.");
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
   public inline fun <S, T : S> Iterable<T>.reduceIndexedOrNull(operation: (Int, S, T) -> S): S? {
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
   public inline fun <S, T : S> Iterable<T>.reduceOrNull(operation: (S, T) -> S): S? {
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

   @JvmStatic
   public inline fun <S, T : S> List<T>.reduceRight(operation: (T, S) -> S): S {
      val iterator: java.util.ListIterator = `$this$reduceRight`.listIterator(`$this$reduceRight`.size());
      if (!iterator.hasPrevious()) {
         throw new UnsupportedOperationException("Empty list can't be reduced.");
      } else {
         var accumulator: Any = iterator.previous();

         while (iterator.hasPrevious()) {
            accumulator = operation.invoke(iterator.previous(), accumulator);
         }

         return (S)accumulator;
      }
   }

   @JvmStatic
   public inline fun <S, T : S> List<T>.reduceRightIndexed(operation: (Int, T, S) -> S): S {
      val iterator: java.util.ListIterator = `$this$reduceRightIndexed`.listIterator(`$this$reduceRightIndexed`.size());
      if (!iterator.hasPrevious()) {
         throw new UnsupportedOperationException("Empty list can't be reduced.");
      } else {
         var accumulator: Any = iterator.previous();

         while (iterator.hasPrevious()) {
            accumulator = operation.invoke(iterator.previousIndex(), iterator.previous(), accumulator);
         }

         return (S)accumulator;
      }
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public inline fun <S, T : S> List<T>.reduceRightIndexedOrNull(operation: (Int, T, S) -> S): S? {
      val iterator: java.util.ListIterator = `$this$reduceRightIndexedOrNull`.listIterator(`$this$reduceRightIndexedOrNull`.size());
      if (!iterator.hasPrevious()) {
         return null;
      } else {
         var accumulator: Any = iterator.previous();

         while (iterator.hasPrevious()) {
            accumulator = operation.invoke(iterator.previousIndex(), iterator.previous(), accumulator);
         }

         return (S)accumulator;
      }
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public inline fun <S, T : S> List<T>.reduceRightOrNull(operation: (T, S) -> S): S? {
      val iterator: java.util.ListIterator = `$this$reduceRightOrNull`.listIterator(`$this$reduceRightOrNull`.size());
      if (!iterator.hasPrevious()) {
         return null;
      } else {
         var accumulator: Any = iterator.previous();

         while (iterator.hasPrevious()) {
            accumulator = operation.invoke(iterator.previous(), accumulator);
         }

         return (S)accumulator;
      }
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public inline fun <T, R> Iterable<T>.runningFold(initial: R, operation: (R, T) -> R): List<R> {
      val estimatedSize: Int = CollectionsKt.collectionSizeOrDefault(`$this$runningFold`, 9);
      if (estimatedSize == 0) {
         return (java.util.List<R>)CollectionsKt.listOf(initial);
      } else {
         val accumulator: ArrayList = new ArrayList(estimatedSize + 1);
         accumulator.add(initial);
         val result: ArrayList = accumulator;
         var var9: Any = initial;

         for (Object element : $this$runningFold) {
            var9 = operation.invoke(var9, var10);
            result.add(var9);
         }

         return result;
      }
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public inline fun <T, R> Iterable<T>.runningFoldIndexed(initial: R, operation: (Int, R, T) -> R): List<R> {
      val estimatedSize: Int = CollectionsKt.collectionSizeOrDefault(`$this$runningFoldIndexed`, 9);
      if (estimatedSize == 0) {
         return (java.util.List<R>)CollectionsKt.listOf(initial);
      } else {
         val index: ArrayList = new ArrayList(estimatedSize + 1);
         index.add(initial);
         val result: ArrayList = index;
         var var10: Int = 0;
         var accumulator: Any = initial;

         for (Object element : $this$runningFoldIndexed) {
            accumulator = operation.invoke(var10++, accumulator, element);
            result.add(accumulator);
         }

         return result;
      }
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public inline fun <S, T : S> Iterable<T>.runningReduce(operation: (S, T) -> S): List<S> {
      val iterator: java.util.Iterator = `$this$runningReduce`.iterator();
      if (!iterator.hasNext()) {
         return CollectionsKt.emptyList();
      } else {
         var var9: Any = iterator.next();
         val var6: ArrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(`$this$runningReduce`, 10));
         var6.add(var9);
         val result: ArrayList = var6;

         while (iterator.hasNext()) {
            var9 = operation.invoke(var9, iterator.next());
            result.add(var9);
         }

         return result;
      }
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public inline fun <S, T : S> Iterable<T>.runningReduceIndexed(operation: (Int, S, T) -> S): List<S> {
      val iterator: java.util.Iterator = `$this$runningReduceIndexed`.iterator();
      if (!iterator.hasNext()) {
         return CollectionsKt.emptyList();
      } else {
         var var9: Any = iterator.next();
         val index: ArrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(`$this$runningReduceIndexed`, 10));
         index.add(var9);
         val result: ArrayList = index;
         var var10: Int = 1;

         while (iterator.hasNext()) {
            var9 = operation.invoke(var10++, var9, iterator.next());
            result.add(var9);
         }

         return result;
      }
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public inline fun <T, R> Iterable<T>.scan(initial: R, operation: (R, T) -> R): List<R> {
      val `estimatedSize$iv`: Int = CollectionsKt.collectionSizeOrDefault(`$this$scan`, 9);
      val var10000: java.util.List;
      if (`estimatedSize$iv` == 0) {
         var10000 = CollectionsKt.listOf(initial);
      } else {
         val `accumulator$iv`: ArrayList = new ArrayList(`estimatedSize$iv` + 1);
         `accumulator$iv`.add(initial);
         val `result$iv`: ArrayList = `accumulator$iv`;
         var var12: Any = initial;

         for (Object element$iv : $this$scan) {
            var12 = operation.invoke(var12, var13);
            `result$iv`.add(var12);
         }

         var10000 = `result$iv`;
      }

      return var10000;
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public inline fun <T, R> Iterable<T>.scanIndexed(initial: R, operation: (Int, R, T) -> R): List<R> {
      val `estimatedSize$iv`: Int = CollectionsKt.collectionSizeOrDefault(`$this$scanIndexed`, 9);
      val var10000: java.util.List;
      if (`estimatedSize$iv` == 0) {
         var10000 = CollectionsKt.listOf(initial);
      } else {
         val `index$iv`: ArrayList = new ArrayList(`estimatedSize$iv` + 1);
         `index$iv`.add(initial);
         val `result$iv`: ArrayList = `index$iv`;
         var var13: Int = 0;
         var `accumulator$iv`: Any = initial;

         for (Object element$iv : $this$scanIndexed) {
            `accumulator$iv` = operation.invoke(var13++, `accumulator$iv`, `element$iv`);
            `result$iv`.add(`accumulator$iv`);
         }

         var10000 = `result$iv`;
      }

      return var10000;
   }

   @Deprecated(message = "Use sumOf instead.", replaceWith = @ReplaceWith(expression = "this.sumOf(selector)", imports = []))
   @DeprecatedSinceKotlin(warningSince = "1.5")
   @JvmStatic
   public inline fun <T> Iterable<T>.sumBy(selector: (T) -> Int): Int {
      var sum: Int = 0;

      for (Object element : $this$sumBy) {
         sum += (selector.invoke(element) as java.lang.Number).intValue();
      }

      return sum;
   }

   @Deprecated(message = "Use sumOf instead.", replaceWith = @ReplaceWith(expression = "this.sumOf(selector)", imports = []))
   @DeprecatedSinceKotlin(warningSince = "1.5")
   @JvmStatic
   public inline fun <T> Iterable<T>.sumByDouble(selector: (T) -> Double): Double {
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
   public inline fun <T> Iterable<T>.sumOf(selector: (T) -> Double): Double {
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
   public inline fun <T> Iterable<T>.sumOf(selector: (T) -> Int): Int {
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
   public inline fun <T> Iterable<T>.sumOf(selector: (T) -> Long): Long {
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
   public inline fun <T> Iterable<T>.sumOf(selector: (T) -> UInt): UInt {
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
   public inline fun <T> Iterable<T>.sumOf(selector: (T) -> ULong): ULong {
      var sum: Long = ULong.constructor-impl(0L);

      for (Object element : $this$sumOf) {
         sum = ULong.constructor-impl(sum + (selector.invoke(element) as ULong).unbox-impl());
      }

      return sum;
   }

   @JvmStatic
   public fun <T : Any> Iterable<T?>.requireNoNulls(): Iterable<T> {
      for (Object element : $this$requireNoNulls) {
         if (element == null) {
            throw new IllegalArgumentException("null element found in $`$this$requireNoNulls`.");
         }
      }

      return `$this$requireNoNulls`;
   }

   @JvmStatic
   public fun <T : Any> List<T?>.requireNoNulls(): List<T> {
      for (Object element : $this$requireNoNulls) {
         if (element == null) {
            throw new IllegalArgumentException("null element found in $`$this$requireNoNulls`.");
         }
      }

      return `$this$requireNoNulls`;
   }

   @SinceKotlin(version = "1.2")
   @JvmStatic
   public fun <T> Iterable<T>.chunked(size: Int): List<List<T>> {
      return CollectionsKt.windowed(`$this$chunked`, size, size, true);
   }

   @SinceKotlin(version = "1.2")
   @JvmStatic
   public fun <T, R> Iterable<T>.chunked(size: Int, transform: (List<T>) -> R): List<R> {
      return CollectionsKt.windowed(`$this$chunked`, size, size, true, transform);
   }

   @JvmStatic
   public operator fun <T> Iterable<T>.minus(element: T): List<T> {
      val result: ArrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(`$this$minus`, 10));
      var removed: Boolean = false;
      val `destination$iv`: java.util.Collection = result;

      for (Object element$iv : $this$minus) {
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

      return `destination$iv` as MutableList<T>;
   }

   @JvmStatic
   public operator fun <T> Iterable<T>.minus(elements: Array<out T>): List<T> {
      if (elements.length == 0) {
         return CollectionsKt.toList(`$this$minus`);
      } else {
         val `destination$iv$iv`: java.util.Collection = new ArrayList();

         for (Object element$iv$iv : $this$minus) {
            if (!ArraysKt.contains(elements, `element$iv$iv`)) {
               `destination$iv$iv`.add(`element$iv$iv`);
            }
         }

         return `destination$iv$iv` as MutableList<T>;
      }
   }

   @JvmStatic
   public operator fun <T> Iterable<T>.minus(elements: Iterable<T>): List<T> {
      val other: java.util.Collection = CollectionsKt.convertToListIfNotCollection(elements);
      if (other.isEmpty()) {
         return CollectionsKt.toList(`$this$minus`);
      } else {
         val `destination$iv$iv`: java.util.Collection = new ArrayList();

         for (Object element$iv$iv : $this$minus) {
            if (!other.contains(`element$iv$iv`)) {
               `destination$iv$iv`.add(`element$iv$iv`);
            }
         }

         return `destination$iv$iv` as MutableList<T>;
      }
   }

   @JvmStatic
   public operator fun <T> Iterable<T>.minus(elements: Sequence<T>): List<T> {
      val other: java.util.List = SequencesKt.toList(elements);
      if (other.isEmpty()) {
         return CollectionsKt.toList(`$this$minus`);
      } else {
         val `destination$iv$iv`: java.util.Collection = new ArrayList();

         for (Object element$iv$iv : $this$minus) {
            if (!other.contains(`element$iv$iv`)) {
               `destination$iv$iv`.add(`element$iv$iv`);
            }
         }

         return `destination$iv$iv` as MutableList<T>;
      }
   }

   @InlineOnly
   @JvmStatic
   public inline fun <T> Iterable<T>.minusElement(element: T): List<T> {
      return (java.util.List<T>)CollectionsKt.minus(`$this$minusElement`, element);
   }

   @JvmStatic
   public inline fun <T> Iterable<T>.partition(predicate: (T) -> Boolean): Pair<List<T>, List<T>> {
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
   public operator fun <T> Iterable<T>.plus(element: T): List<T> {
      if (`$this$plus` is java.util.Collection) {
         return (java.util.List<T>)CollectionsKt.plus(`$this$plus` as MutableCollection<Any>, element);
      } else {
         val result: ArrayList = new ArrayList();
         CollectionsKt.addAll(result, `$this$plus`);
         result.add(element);
         return result;
      }
   }

   @JvmStatic
   public operator fun <T> Collection<T>.plus(element: T): List<T> {
      val result: ArrayList = new ArrayList(`$this$plus`.size() + 1);
      result.addAll(`$this$plus`);
      result.add(element);
      return result;
   }

   @JvmStatic
   public operator fun <T> Iterable<T>.plus(elements: Array<out T>): List<T> {
      if (`$this$plus` is java.util.Collection) {
         return (java.util.List<T>)CollectionsKt.plus(`$this$plus` as MutableCollection<Any>, elements);
      } else {
         val result: ArrayList = new ArrayList();
         CollectionsKt.addAll(result, `$this$plus`);
         CollectionsKt.addAll(result, elements);
         return result;
      }
   }

   @JvmStatic
   public operator fun <T> Collection<T>.plus(elements: Array<out T>): List<T> {
      val result: ArrayList = new ArrayList(`$this$plus`.size() + elements.length);
      result.addAll(`$this$plus`);
      CollectionsKt.addAll(result, elements);
      return result;
   }

   @JvmStatic
   public operator fun <T> Iterable<T>.plus(elements: Iterable<T>): List<T> {
      if (`$this$plus` is java.util.Collection) {
         return CollectionsKt.plus(`$this$plus` as java.util.Collection, elements);
      } else {
         val result: ArrayList = new ArrayList();
         CollectionsKt.addAll(result, `$this$plus`);
         CollectionsKt.addAll(result, elements);
         return result;
      }
   }

   @JvmStatic
   public operator fun <T> Collection<T>.plus(elements: Iterable<T>): List<T> {
      if (elements is java.util.Collection) {
         val var3: ArrayList = new ArrayList(`$this$plus`.size() + (elements as java.util.Collection).size());
         var3.addAll(`$this$plus`);
         var3.addAll(elements as java.util.Collection);
         return var3;
      } else {
         val result: ArrayList = new ArrayList(`$this$plus`);
         CollectionsKt.addAll(result, elements);
         return result;
      }
   }

   @JvmStatic
   public operator fun <T> Iterable<T>.plus(elements: Sequence<T>): List<T> {
      val result: ArrayList = new ArrayList();
      CollectionsKt.addAll(result, `$this$plus`);
      CollectionsKt.addAll(result, elements);
      return result;
   }

   @JvmStatic
   public operator fun <T> Collection<T>.plus(elements: Sequence<T>): List<T> {
      val result: ArrayList = new ArrayList(`$this$plus`.size() + 10);
      result.addAll(`$this$plus`);
      CollectionsKt.addAll(result, elements);
      return result;
   }

   @InlineOnly
   @JvmStatic
   public inline fun <T> Iterable<T>.plusElement(element: T): List<T> {
      return (java.util.List<T>)CollectionsKt.plus(`$this$plusElement`, element);
   }

   @InlineOnly
   @JvmStatic
   public inline fun <T> Collection<T>.plusElement(element: T): List<T> {
      return (java.util.List<T>)CollectionsKt.plus(`$this$plusElement`, element);
   }

   @SinceKotlin(version = "1.2")
   @JvmStatic
   public fun <T> Iterable<T>.windowed(size: Int, step: Int = 1, partialWindows: Boolean = false): List<List<T>> {
      SlidingWindowKt.checkWindowSizeStep(size, step);
      if (`$this$windowed` is RandomAccess && `$this$windowed` is java.util.List) {
         val var16: Int = (`$this$windowed` as java.util.List).size();
         val var18: ArrayList = new ArrayList(var16 / step + (if (var16 % step == 0) 0 else 1));

         for (int index = 0; 0 <= index && index < thisSize; index += step) {
            val var20: Int = RangesKt.coerceAtMost(size, var16 - var19);
            if (var20 < size && !partialWindows) {
               break;
            }

            val var21: ArrayList = new ArrayList(var20);

            for (int var22 = 0; var22 < windowSize; var22++) {
               var21.add((`$this$windowed` as java.util.List).get(var22 + var19));
            }

            var18.add(var21);
         }

         return var18;
      } else {
         val result: ArrayList = new ArrayList();
         val index: java.util.Iterator = SlidingWindowKt.windowedIterator(`$this$windowed`.iterator(), size, step, partialWindows, false);

         while (index.hasNext()) {
            result.add(index.next() as java.util.List);
         }

         return result;
      }
   }

   @SinceKotlin(version = "1.2")
   @JvmStatic
   public fun <T, R> Iterable<T>.windowed(size: Int, step: Int = 1, partialWindows: Boolean = false, transform: (List<T>) -> R): List<R> {
      SlidingWindowKt.checkWindowSizeStep(size, step);
      if (`$this$windowed` is RandomAccess && `$this$windowed` is java.util.List) {
         val var12: Int = (`$this$windowed` as java.util.List).size();
         val var14: ArrayList = new ArrayList(var12 / step + (if (var12 % step == 0) 0 else 1));
         val var15: MovingSubList = new MovingSubList(`$this$windowed` as java.util.List);

         for (int index = 0; 0 <= index && index < thisSize; index += step) {
            val var17: Int = RangesKt.coerceAtMost(size, var12 - var16);
            if (!partialWindows && var17 < size) {
               break;
            }

            var15.move(var16, var16 + var17);
            var14.add(transform.invoke(var15));
         }

         return var14;
      } else {
         val result: ArrayList = new ArrayList();
         val window: java.util.Iterator = SlidingWindowKt.windowedIterator(`$this$windowed`.iterator(), size, step, partialWindows, true);

         while (window.hasNext()) {
            result.add(transform.invoke(window.next() as java.util.List));
         }

         return result;
      }
   }

   @JvmStatic
   public infix fun <T, R> Iterable<T>.zip(other: Array<out R>): List<Pair<T, R>> {
      val `other$iv`: Array<Any> = other;
      val `arraySize$iv`: Int = other.length;
      val `list$iv`: ArrayList = new ArrayList(Math.min(CollectionsKt.collectionSizeOrDefault(`$this$zip`, 10), other.length));
      var `i$iv`: Int = 0;

      for (Object element$iv : $this$zip) {
         if (`i$iv` >= `arraySize$iv`) {
            break;
         }

         `list$iv`.add(TuplesKt.to(`element$iv`, `other$iv`[`i$iv`++]));
      }

      return `list$iv`;
   }

   @JvmStatic
   public inline fun <T, R, V> Iterable<T>.zip(other: Array<out R>, transform: (T, R) -> V): List<V> {
      val arraySize: Int = other.length;
      val list: ArrayList = new ArrayList(Math.min(CollectionsKt.collectionSizeOrDefault(`$this$zip`, 10), other.length));
      var i: Int = 0;

      for (Object element : $this$zip) {
         if (i >= arraySize) {
            break;
         }

         list.add(transform.invoke(element, other[i++]));
      }

      return list;
   }

   @JvmStatic
   public infix fun <T, R> Iterable<T>.zip(other: Iterable<R>): List<Pair<T, R>> {
      val `first$iv`: java.util.Iterator = `$this$zip`.iterator();
      val `second$iv`: java.util.Iterator = other.iterator();
      val `list$iv`: ArrayList = new ArrayList(
         Math.min(CollectionsKt.collectionSizeOrDefault(`$this$zip`, 10), CollectionsKt.collectionSizeOrDefault(other, 10))
      );

      while (first$iv.hasNext() && second$iv.hasNext()) {
         `list$iv`.add(TuplesKt.to(`first$iv`.next(), `second$iv`.next()));
      }

      return `list$iv`;
   }

   @JvmStatic
   public inline fun <T, R, V> Iterable<T>.zip(other: Iterable<R>, transform: (T, R) -> V): List<V> {
      val first: java.util.Iterator = `$this$zip`.iterator();
      val second: java.util.Iterator = other.iterator();
      val list: ArrayList = new ArrayList(Math.min(CollectionsKt.collectionSizeOrDefault(`$this$zip`, 10), CollectionsKt.collectionSizeOrDefault(other, 10)));

      while (first.hasNext() && second.hasNext()) {
         list.add(transform.invoke(first.next(), second.next()));
      }

      return list;
   }

   @SinceKotlin(version = "1.2")
   @JvmStatic
   public fun <T> Iterable<T>.zipWithNext(): List<Pair<T, T>> {
      val `iterator$iv`: java.util.Iterator = `$this$zipWithNext`.iterator();
      val var10000: java.util.List;
      if (!`iterator$iv`.hasNext()) {
         var10000 = CollectionsKt.emptyList();
      } else {
         val `result$iv`: java.util.List = new ArrayList();
         var `current$iv`: Any = `iterator$iv`.next();

         while (iterator$iv.hasNext()) {
            val `next$iv`: Any = `iterator$iv`.next();
            `result$iv`.add(TuplesKt.to(`current$iv`, `next$iv`));
            `current$iv` = `next$iv`;
         }

         var10000 = `result$iv`;
      }

      return var10000;
   }

   @SinceKotlin(version = "1.2")
   @JvmStatic
   public inline fun <T, R> Iterable<T>.zipWithNext(transform: (T, T) -> R): List<R> {
      val iterator: java.util.Iterator = `$this$zipWithNext`.iterator();
      if (!iterator.hasNext()) {
         return CollectionsKt.emptyList();
      } else {
         val result: java.util.List = new ArrayList();
         var current: Any = iterator.next();

         while (iterator.hasNext()) {
            val next: Any = iterator.next();
            result.add(transform.invoke(current, next));
            current = next;
         }

         return result;
      }
   }

   @JvmStatic
   public fun <T, A : Appendable> Iterable<T>.joinTo(
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
   public fun <T> Iterable<T>.joinToString(
      separator: CharSequence = ", " as java.lang.CharSequence,
      prefix: CharSequence = "" as java.lang.CharSequence,
      postfix: CharSequence = "" as java.lang.CharSequence,
      limit: Int = -1,
      truncated: CharSequence = "..." as java.lang.CharSequence,
      transform: ((T) -> CharSequence)? = null
   ): String {
      return CollectionsKt.joinTo(`$this$joinToString`, new StringBuilder(), separator, prefix, postfix, limit, truncated, transform).toString();
   }

   @InlineOnly
   @JvmStatic
   public inline fun <T> Iterable<T>.asIterable(): Iterable<T> {
      return `$this$asIterable`;
   }

   @JvmStatic
   public fun <T> Iterable<T>.asSequence(): Sequence<T> {
      return new kotlin.collections.CollectionsKt___CollectionsKt.asSequence..inlined.Sequence.1(`$this$asSequence`);
   }

   @JvmName(name = "averageOfByte")
   @JvmStatic
   public fun Iterable<Byte>.average(): Double {
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
   public fun Iterable<Short>.average(): Double {
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
   public fun Iterable<Int>.average(): Double {
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
   public fun Iterable<Long>.average(): Double {
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
   public fun Iterable<Float>.average(): Double {
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
   public fun Iterable<Double>.average(): Double {
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
   public fun Iterable<Byte>.sum(): Int {
      var sum: Int = 0;
      val var2: java.util.Iterator = `$this$sum`.iterator();

      while (var2.hasNext()) {
         sum += (var2.next() as java.lang.Number).byteValue();
      }

      return sum;
   }

   @JvmName(name = "sumOfShort")
   @JvmStatic
   public fun Iterable<Short>.sum(): Int {
      var sum: Int = 0;
      val var2: java.util.Iterator = `$this$sum`.iterator();

      while (var2.hasNext()) {
         sum += (var2.next() as java.lang.Number).shortValue();
      }

      return sum;
   }

   @JvmName(name = "sumOfInt")
   @JvmStatic
   public fun Iterable<Int>.sum(): Int {
      var sum: Int = 0;
      val var2: java.util.Iterator = `$this$sum`.iterator();

      while (var2.hasNext()) {
         sum += (var2.next() as java.lang.Number).intValue();
      }

      return sum;
   }

   @JvmName(name = "sumOfLong")
   @JvmStatic
   public fun Iterable<Long>.sum(): Long {
      var sum: Long = 0L;
      val var3: java.util.Iterator = `$this$sum`.iterator();

      while (var3.hasNext()) {
         sum += (var3.next() as java.lang.Number).longValue();
      }

      return sum;
   }

   @JvmName(name = "sumOfFloat")
   @JvmStatic
   public fun Iterable<Float>.sum(): Float {
      var sum: Float = 0.0F;
      val var2: java.util.Iterator = `$this$sum`.iterator();

      while (var2.hasNext()) {
         sum += (var2.next() as java.lang.Number).floatValue();
      }

      return sum;
   }

   @JvmName(name = "sumOfDouble")
   @JvmStatic
   public fun Iterable<Double>.sum(): Double {
      var sum: Double = 0.0;
      val var3: java.util.Iterator = `$this$sum`.iterator();

      while (var3.hasNext()) {
         sum += (var3.next() as java.lang.Number).doubleValue();
      }

      return sum;
   }

   @JvmStatic
   fun `elementAt$lambda$0$CollectionsKt___CollectionsKt`(`$index`: Int, it: Int): Any {
      throw new IndexOutOfBoundsException("Collection doesn't contain element at index $`$index`.");
   }

   @JvmStatic
   fun `withIndex$lambda$0$CollectionsKt___CollectionsKt`(`$this_withIndex`: java.lang.Iterable): java.util.Iterator {
      return `$this_withIndex`.iterator();
   }

   open fun CollectionsKt___CollectionsKt() {
   }
}

package kotlin.collections

import java.util.LinkedHashMap
import java.util.Map.Entry
import kotlin.internal.InlineOnly
import kotlin.jvm.internal.Ref
import kotlin.jvm.internal.SourceDebugExtension
import kotlin.jvm.internal.TypeIntrinsics

@SourceDebugExtension(["SMAP\nGroupingJVM.kt\nKotlin\n*S Kotlin\n*F\n+ 1 GroupingJVM.kt\nkotlin/collections/GroupingKt__GroupingJVMKt\n+ 2 Grouping.kt\nkotlin/collections/GroupingKt__GroupingKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 4 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,52:1\n143#2:53\n80#2,4:54\n85#2:59\n1#3:58\n1869#4,2:60\n*S KotlinDebug\n*F\n+ 1 GroupingJVM.kt\nkotlin/collections/GroupingKt__GroupingJVMKt\n*L\n22#1:53\n22#1:54,4\n22#1:59\n48#1:60,2\n*E\n"])
internal class GroupingKt__GroupingJVMKt {
   @SinceKotlin(version = "1.1")
   @JvmStatic
   public fun <T, K> Grouping<T, K>.eachCount(): Map<K, Int> {
      val `destination$iv`: java.util.Map = new LinkedHashMap();
      val `$this$aggregateTo$iv$iv`: Grouping = `$this$eachCount`;
      val it: java.util.Map = `destination$iv`;
      val var7: java.util.Iterator = `$this$eachCount`.sourceIterator();

      while (var7.hasNext()) {
         val `key$iv$iv`: Any = `$this$aggregateTo$iv$iv`.keyOf(var7.next());
         val `accumulator$iv$iv`: Any = it.get(`key$iv$iv`);
         val acc: Ref.IntRef = (if (`accumulator$iv$iv` == null && !it.containsKey(`key$iv$iv`)) new Ref.IntRef() else `accumulator$iv$iv`) as Ref.IntRef;
         acc.element++;
         it.put(`key$iv$iv`, acc);
      }

      for (Entry var28 : destination$iv$iv.entrySet()) {
         TypeIntrinsics.asMutableMapEntry(var28).setValue((var28.getValue() as Ref.IntRef).element);
      }

      return TypeIntrinsics.asMutableMap(it);
   }

   @PublishedApi
   @InlineOnly
   @JvmStatic
   internal inline fun <K, V, R> MutableMap<K, V>.mapValuesInPlace(f: (kotlin.collections.Map.Entry<K, V>) -> R): MutableMap<K, R> {
      val `$this$forEach$iv`: java.lang.Iterable;
      for (Object element$iv : $this$forEach$iv) {
         val it: Entry = `element$iv` as Entry;
         TypeIntrinsics.asMutableMapEntry(it).setValue(f.invoke(it));
      }

      return TypeIntrinsics.asMutableMap(`$this$mapValuesInPlace`);
   }

   open fun GroupingKt__GroupingJVMKt() {
   }
}

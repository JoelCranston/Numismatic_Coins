@file:SourceDebugExtension(["SMAP\nRanges.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Ranges.kt\nio/ktor/http/RangesKt\n+ 2 Text.kt\nio/ktor/util/TextKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,139:1\n41#2,3:140\n41#2,3:146\n1563#3:143\n1634#3,2:144\n1636#3:149\n1563#3:150\n1634#3,3:151\n827#3:154\n855#3,2:155\n1056#3:157\n1803#3,3:158\n*S KotlinDebug\n*F\n+ 1 Ranges.kt\nio/ktor/http/RangesKt\n*L\n81#1:140,3\n86#1:146,3\n82#1:143\n82#1:144,2\n82#1:149\n105#1:150\n105#1:151,3\n111#1:154\n111#1:155,2\n115#1:157\n115#1:158,3\n*E\n"])

package io.ktor.http

import io.ktor.http.RangesKt.mergeRangesKeepOrder..inlined.sortedBy.1
import java.util.ArrayList
import kotlin.jvm.internal.SourceDebugExtension

public fun parseRangesSpecifier(rangeSpec: String): RangesSpecifier? {
   try {
      val spec: Int = StringsKt.indexOf$default(rangeSpec, "=", 0, false, 6, null);
      if (spec == -1) {
         return null;
      } else {
         var var10000: java.lang.String = rangeSpec.substring(0, spec);
         var var10001: java.lang.String = rangeSpec.substring(spec + "=".length());
         val var1: Pair = TuplesKt.to(var10000, var10001);
         val e: java.lang.String = var1.component1() as java.lang.String;
         val var25: java.lang.Iterable = StringsKt.split$default(var1.component2() as java.lang.String, new char[]{','}, false, 0, 6, null);
         val `destination$iv$iv`: java.util.Collection = new ArrayList(CollectionsKt.collectionSizeOrDefault(var25, 10));

         for (Object item$iv$iv : $this$map$iv) {
            val it: java.lang.String = `item$iv$iv` as java.lang.String;
            val var30: ContentRange;
            if (StringsKt.startsWith$default(`item$iv$iv` as java.lang.String, "-", false, 2, null)) {
               var30 = new ContentRange.Suffix(java.lang.Long.parseLong(StringsKt.removePrefix(it, "-")));
            } else {
               val `idx$ivx`: Int = StringsKt.indexOf$default(it, "-", 0, false, 6, null);
               val var31: Pair;
               if (`idx$ivx` == -1) {
                  var31 = TuplesKt.to("", "");
               } else {
                  var10000 = it.substring(0, `idx$ivx`);
                  var10001 = it.substring(`idx$ivx` + "-".length());
                  var31 = TuplesKt.to(var10000, var10001);
               }

               val from: java.lang.String = var31.component1() as java.lang.String;
               val var28: java.lang.String = var31.component2() as java.lang.String;
               var30 = if (var28.length() > 0)
                  new ContentRange.Bounded(java.lang.Long.parseLong(from), java.lang.Long.parseLong(var28))
                  else
                  new ContentRange.TailFrom(java.lang.Long.parseLong(from));
            }

            `destination$iv$iv`.add(var30);
         }

         val var23: java.util.List = `destination$iv$iv` as java.util.List;
         if (!(`destination$iv$iv` as java.util.List).isEmpty() && e.length() != 0) {
            val var26: RangesSpecifier = new RangesSpecifier(e, var23);
            return if (RangesSpecifier.isValid$default(var26, null, 1, null)) var26 else null;
         } else {
            return null;
         }
      }
   } catch (var21: java.lang.Throwable) {
      return null;
   }
}

internal fun List<ContentRange>.toLongRanges(contentLength: Long): List<LongRange> {
   var `$this$filterNot$iv`: java.lang.Iterable = `$this$toLongRanges`;
   var `destination$iv$iv`: java.util.Collection = new ArrayList(CollectionsKt.collectionSizeOrDefault(`$this$toLongRanges`, 10));

   for (Object item$iv$iv : $this$map$iv) {
      val it: ContentRange = `element$iv$iv` as ContentRange;
      val var10000: LongRange;
      if (`element$iv$iv` as ContentRange is ContentRange.Bounded) {
         var10000 = new LongRange(
            (it as ContentRange.Bounded).getFrom(), kotlin.ranges.RangesKt.coerceAtMost((it as ContentRange.Bounded).getTo(), contentLength - 1L)
         );
      } else if (it is ContentRange.TailFrom) {
         var10000 = kotlin.ranges.RangesKt.until((it as ContentRange.TailFrom).getFrom(), contentLength);
      } else {
         if (it !is ContentRange.Suffix) {
            throw new NoWhenBranchMatchedException();
         }

         var10000 = kotlin.ranges.RangesKt.until(
            kotlin.ranges.RangesKt.coerceAtLeast(contentLength - (it as ContentRange.Suffix).getLastCount(), 0L), contentLength
         );
      }

      `destination$iv$iv`.add(var10000);
   }

   `$this$filterNot$iv` = `destination$iv$iv` as java.util.List;
   `destination$iv$iv` = new ArrayList();

   for (Object element$iv$iv : $this$map$iv) {
      if (!(var19 as LongRange).isEmpty()) {
         `destination$iv$iv`.add(var19);
      }
   }

   return `destination$iv$iv` as MutableList<LongRange>;
}

internal fun List<LongRange>.mergeRangesKeepOrder(): List<LongRange> {
   val var12: java.lang.Iterable = CollectionsKt.sortedWith(`$this$mergeRangesKeepOrder`, new 1());
   var i: Any = new ArrayList(`$this$mergeRangesKeepOrder`.size());

   for (Object element$iv : var12) {
      val range: LongRange = `element$iv` as LongRange;
      if (i.isEmpty()) {
         i.add(range);
      } else if (CollectionsKt.<LongRange>last(i as MutableList<LongRange>).getLast() < range.getFirst() - 1L) {
         i.add(range);
      } else {
         val last: LongRange = CollectionsKt.last(i as MutableList<LongRange>);
         i.set(CollectionsKt.getLastIndex(i as java.util.List), new LongRange(last.getFirst(), Math.max(last.getLast(), range.getLast())));
      }

      i = i;
   }

   val var13: Array<LongRange> = new LongRange[`$this$mergeRangesKeepOrder`.size()];
   var var10000: java.util.Iterator = i.iterator();
   val var15: java.util.Iterator = var10000;

   while (var15.hasNext()) {
      var10000 = (java.util.Iterator)var15.next();
      val var16: LongRange = var10000 as LongRange;
      var var17: Int = 0;

      for (int var18 = $this$mergeRangesKeepOrder.size(); i < var18; i++) {
         if (io.ktor.util.RangesKt.contains(var16, `$this$mergeRangesKeepOrder`.get(var17) as LongRange)) {
            var13[var17] = var16;
            break;
         }
      }
   }

   return ArraysKt.filterNotNull(var13);
}

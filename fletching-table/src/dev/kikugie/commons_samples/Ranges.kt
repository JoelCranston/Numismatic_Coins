package dev.kikugie.commons_samples

import dev.kikugie.commons.ranges.CommonKt
import dev.kikugie.commons.ranges.IntRangesKt
import kotlin.jvm.internal.SourceDebugExtension
import kotlin.test.AssertionsKt
import org.junit.jupiter.api.Test

@SourceDebugExtension(["SMAP\nRanges.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Ranges.kt\ndev/kikugie/commons_samples/Ranges\n+ 2 IntRanges.kt\ndev/kikugie/commons/ranges/IntRangesKt\n*L\n1#1,72:1\n9#2:73\n9#2:74\n9#2:75\n15#2:76\n15#2:77\n15#2:78\n12#2:79\n12#2:80\n12#2:81\n*S KotlinDebug\n*F\n+ 1 Ranges.kt\ndev/kikugie/commons_samples/Ranges\n*L\n18#1:73\n21#1:74\n22#1:75\n27#1:76\n30#1:77\n31#1:78\n36#1:79\n39#1:80\n40#1:81\n*E\n"])
private class Ranges {
   @Test
   public fun extend() {
      AssertionsKt.assertEquals$default(new IntRange(2, 5), new IntRange(2, 2 + 3), null, 4, null);
      AssertionsKt.assertEquals$default(new IntRange(2, -1), new IntRange(2, 2 + -3), null, 4, null);
      AssertionsKt.assertEquals$default(new IntRange(Integer.MAX_VALUE, -2147483640), new IntRange(Integer.MAX_VALUE, Integer.MAX_VALUE + 8), null, 4, null);
   }

   @Test
   public fun shiftRight() {
      var var10000: IntRange = new IntRange(4, 6);
      var `$this$shr$iv`: IntRange = new IntRange(1, 3);
      AssertionsKt.assertEquals$default(var10000, new IntRange(`$this$shr$iv`.getFirst() + 3, `$this$shr$iv`.getLast() + 3), null, 4, null);
      var10000 = new IntRange(0, 2);
      `$this$shr$iv` = new IntRange(1, 3);
      AssertionsKt.assertEquals$default(var10000, new IntRange(`$this$shr$iv`.getFirst() + -1, `$this$shr$iv`.getLast() + -1), null, 4, null);
      var10000 = new IntRange(2147483646, 2147483644);
      `$this$shr$iv` = new IntRange(2147483645, Integer.MAX_VALUE);
      AssertionsKt.assertEquals$default(var10000, new IntRange(`$this$shr$iv`.getFirst() + 2, `$this$shr$iv`.getLast() + 2), null, 4, null);
   }

   @Test
   public fun shiftLeft() {
      var var10000: IntRange = new IntRange(1, 3);
      var `$this$shl$iv`: IntRange = new IntRange(4, 6);
      AssertionsKt.assertEquals$default(var10000, new IntRange(`$this$shl$iv`.getFirst() - 3, `$this$shl$iv`.getLast() - 3), null, 4, null);
      var10000 = new IntRange(7, 9);
      `$this$shl$iv` = new IntRange(4, 6);
      AssertionsKt.assertEquals$default(var10000, new IntRange(`$this$shl$iv`.getFirst() - -3, `$this$shl$iv`.getLast() - -3), null, 4, null);
      var10000 = new IntRange(2147483644, 2147483646);
      `$this$shl$iv` = new IntRange(2147483646, Integer.MIN_VALUE);
      AssertionsKt.assertEquals$default(var10000, new IntRange(`$this$shl$iv`.getFirst() - 2, `$this$shl$iv`.getLast() - 2), null, 4, null);
   }

   @Test
   public fun crossRange() {
      AssertionsKt.assertEquals$default(new IntRange(2, 3), IntRangesKt.cross(new IntRange(1, 3), new IntRange(2, 4)), null, 4, null);
      AssertionsKt.assertEquals$default(IntRange.Companion.getEMPTY(), IntRangesKt.cross(new IntRange(1, 3), new IntRange(5, 9)), null, 4, null);
   }

   @Test
   public fun extendRange() {
      AssertionsKt.assertEquals$default(new IntRange(1, 9), IntRangesKt.extend(new IntRange(1, 3), new IntRange(5, 9)), null, 4, null);
   }

   @Test
   public fun mergeRange() {
      AssertionsKt.assertEquals$default(new IntRange(1, 4), IntRangesKt.merge(new IntRange(1, 3), new IntRange(2, 4)), null, 4, null);
      AssertionsKt.assertEquals$default(IntRange.Companion.getEMPTY(), IntRangesKt.merge(new IntRange(1, 3), new IntRange(5, 9)), null, 4, null);
   }

   @Test
   public fun overlaps() {
      AssertionsKt.assertTrue$default(CommonKt.overlaps(new IntRange(1, 3), new IntRange(2, 4)), null, 2, null);
      AssertionsKt.assertFalse$default(CommonKt.overlaps(new IntRange(1, 3), new IntRange(5, 9)), null, 2, null);
   }

   @Test
   public fun contains() {
      AssertionsKt.assertTrue$default(CommonKt.contains(new IntRange(1, 3), new IntRange(1, 3)), null, 2, null);
      AssertionsKt.assertTrue$default(!CommonKt.contains(new IntRange(5, 9), new IntRange(1, 3)), null, 2, null);
      AssertionsKt.assertFalse$default(CommonKt.contains(new IntRange(0, 2), new IntRange(1, 3)), null, 2, null);
   }
}

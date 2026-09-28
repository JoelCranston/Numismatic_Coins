package io.ktor.http

import kotlin.jvm.internal.SourceDebugExtension

@SourceDebugExtension(["SMAP\nRangesSpecifier.kt\nKotlin\n*S Kotlin\n*F\n+ 1 RangesSpecifier.kt\nio/ktor/http/RangesSpecifier\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,86:1\n1#2:87\n2746#3,3:88\n2423#3,14:91\n1999#3,14:105\n*S KotlinDebug\n*F\n+ 1 RangesSpecifier.kt\nio/ktor/http/RangesSpecifier\n*L\n30#1:88,3\n76#1:91,14\n77#1:105,14\n*E\n"])
public data class RangesSpecifier(unit: String = RangeUnits.Bytes.getUnitToken(), ranges: List<ContentRange>) {
   public final val unit: String
   public final val ranges: List<ContentRange>

   init {
      this.unit = unit;
      this.ranges = ranges;
      if (this.ranges.isEmpty()) {
         throw new IllegalArgumentException("It should be at least one range".toString());
      }
   }

   public constructor(unit: RangeUnits, ranges: List<ContentRange>) : this(unit.getUnitToken(), ranges)
   public fun isValid(rangeUnitPredicate: (String) -> Boolean = RangesSpecifier::isValid$lambda$0): Boolean {
      if (rangeUnitPredicate.invoke(this.unit) as java.lang.Boolean) {
         val `$this$none$iv`: java.lang.Iterable = this.ranges;
         var var9: Boolean;
         if (this.ranges is java.util.Collection && this.ranges.isEmpty()) {
            var9 = true;
         } else {
            val var4: java.util.Iterator = `$this$none$iv`.iterator();

            while (true) {
               if (!var4.hasNext()) {
                  var9 = true;
                  break;
               }

               val it: ContentRange = var4.next() as ContentRange;
               if (it is ContentRange.Bounded) {
                  var9 = (it as ContentRange.Bounded).getFrom() < 0L || (it as ContentRange.Bounded).getTo() < (it as ContentRange.Bounded).getFrom();
               } else if (it is ContentRange.TailFrom) {
                  var9 = (it as ContentRange.TailFrom).getFrom() < 0L;
               } else {
                  if (it !is ContentRange.Suffix) {
                     throw new NoWhenBranchMatchedException();
                  }

                  var9 = (it as ContentRange.Suffix).getLastCount() < 0L;
               }

               if (var9) {
                  var9 = false;
                  break;
               }
            }
         }

         if (var9) {
            return true;
         }
      }

      return false;
   }

   public fun merge(length: Long, maxRangeCount: Int = 50): List<LongRange> {
      return if (this.ranges.size() > maxRangeCount) this.toList(this.mergeToSingle(length)) else this.merge(length);
   }

   public fun merge(length: Long): List<LongRange> {
      return RangesKt.mergeRangesKeepOrder(RangesKt.toLongRanges(this.ranges, length));
   }

   public fun mergeToSingle(length: Long): LongRange? {
      val mapped: java.util.List = RangesKt.toLongRanges(this.ranges, length);
      if (mapped.isEmpty()) {
         return null;
      } else {
         val `$this$maxByOrNull$iv`: java.util.Iterator = mapped.iterator();
         var var10000: Any;
         if (!`$this$maxByOrNull$iv`.hasNext()) {
            var10000 = null;
         } else {
            var `$i$f$maxByOrNull`: Any = `$this$maxByOrNull$iv`.next();
            if (!`$this$maxByOrNull$iv`.hasNext()) {
               var10000 = `$i$f$maxByOrNull`;
            } else {
               var var22: Long = (`$i$f$maxByOrNull` as LongRange).getFirst();

               do {
                  val var24: Any = `$this$maxByOrNull$iv`.next();
                  val var26: Long = (var24 as LongRange).getFirst();
                  if (var22 > var26) {
                     `$i$f$maxByOrNull` = var24;
                     var22 = var26;
                  }
               } while (iterator$iv.hasNext());

               var10000 = `$i$f$maxByOrNull`;
            }
         }

         val start: Long = (var10000 as LongRange).getFirst();
         val `iterator$ivx`: java.util.Iterator = mapped.iterator();
         if (!`iterator$ivx`.hasNext()) {
            var10000 = null;
         } else {
            var var25: Any = `iterator$ivx`.next();
            if (!`iterator$ivx`.hasNext()) {
               var10000 = var25;
            } else {
               var var28: Long = (var25 as LongRange).getLast();

               do {
                  val var30: Any = `iterator$ivx`.next();
                  val var31: Long = (var30 as LongRange).getLast();
                  if (var28 < var31) {
                     var25 = var30;
                     var28 = var31;
                  }
               } while (iterator$ivx.hasNext());

               var10000 = var25;
            }
         }

         return new LongRange(start, kotlin.ranges.RangesKt.coerceAtMost((var10000 as LongRange).getLast(), length - 1L));
      }
   }

   public override fun toString(): String {
      return CollectionsKt.joinToString$default(this.ranges, ",", "${this.unit}=", null, 0, null, null, 60, null);
   }

   private fun <T> Any?.toList(): List<Any> {
      return if (`$this$toList` == null) CollectionsKt.emptyList() else CollectionsKt.listOf(`$this$toList`);
   }

   public operator fun component1(): String {
      return this.unit;
   }

   public operator fun component2(): List<ContentRange> {
      return this.ranges;
   }

   public fun copy(unit: String = this.unit, ranges: List<ContentRange> = this.ranges): RangesSpecifier {
      return new RangesSpecifier(unit, ranges);
   }

   public override fun hashCode(): Int {
      return this.unit.hashCode() * 31 + this.ranges.hashCode();
   }

   public override operator fun equals(other: Any?): Boolean {
      if (this === other) {
         return true;
      } else if (other !is RangesSpecifier) {
         return false;
      } else {
         val var2: RangesSpecifier = other as RangesSpecifier;
         if (!(this.unit == (other as RangesSpecifier).unit)) {
            return false;
         } else {
            return this.ranges == var2.ranges;
         }
      }
   }

   @JvmStatic
   fun `isValid$lambda$0`(it: java.lang.String): Boolean {
      return it == RangeUnits.Bytes.getUnitToken();
   }
}

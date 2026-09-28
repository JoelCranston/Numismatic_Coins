package kotlin.time

import kotlin.jvm.internal.SourceDebugExtension

@SinceKotlin(version = "1.9")
@WasExperimental(markerClass = [ExperimentalTime::class])
@SourceDebugExtension(["SMAP\nTimeSources.kt\nKotlin\n*S Kotlin\n*F\n+ 1 TimeSources.kt\nkotlin/time/TestTimeSource\n+ 2 longSaturatedMath.kt\nkotlin/time/LongSaturatedMathKt\n*L\n1#1,210:1\n80#2:211\n80#2:212\n*S KotlinDebug\n*F\n+ 1 TimeSources.kt\nkotlin/time/TestTimeSource\n*L\n184#1:211\n191#1:212\n*E\n"])
public class TestTimeSource : AbstractLongTimeSource(DurationUnit.NANOSECONDS) {
   private final var reading: Long

   protected override fun read(): Long {
      return this.reading;
   }

   public operator fun plusAssign(duration: Duration) {
      val longDelta: Long = Duration.toLong-impl(var1, this.getUnit());
      if ((longDelta - 1L or 1L) != java.lang.Long.MAX_VALUE) {
         val half: Long = this.reading + longDelta;
         if ((this.reading xor longDelta) >= 0L && (this.reading xor this.reading + longDelta) < 0L) {
            this.overflow-LRDsOJo(var1);
         }

         this.reading = half;
      } else {
         val var11: Long = Duration.div-UwyO8pc(var1, 2);
         if ((Duration.toLong-impl(var11, this.getUnit()) - 1L or 1L) != java.lang.Long.MAX_VALUE) {
            try {
               this.plusAssign-LRDsOJo(var11);
               this.plusAssign-LRDsOJo(Duration.minus-LRDsOJo(var1, var11));
            } catch (var10: IllegalStateException) {
               this.reading = this.reading;
               throw var10;
            }
         } else {
            this.overflow-LRDsOJo(var1);
         }
      }
   }

   private fun overflow(duration: Duration) {
      throw new IllegalStateException(
         "TestTimeSource will overflow if its reading ${this.reading}${DurationUnitKt.shortName(this.getUnit())} is advanced by ${Duration.toString-impl(var1)}."
      );
   }
}

package kotlin.time

private class AdjustedTimeMark(mark: TimeMark, adjustment: Duration) : AdjustedTimeMark(mark, adjustment), TimeMark {
   public final val mark: TimeMark
   public final val adjustment: Duration

   fun AdjustedTimeMark(mark: TimeMark, adjustment: Long) {
      this.mark = mark;
      this.adjustment = adjustment;
   }

   public override fun elapsedNow(): Duration {
      return Duration.minus-LRDsOJo(this.mark.elapsedNow-UwyO8pc(), this.adjustment);
   }

   public override operator fun plus(duration: Duration): TimeMark {
      return new AdjustedTimeMark(this.mark, Duration.plus-LRDsOJo(this.adjustment, var1), null);
   }
}

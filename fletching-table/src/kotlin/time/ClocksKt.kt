package kotlin.time

import kotlin.time.ClocksKt.asClock.1

@SinceKotlin(version = "2.2")
@ExperimentalTime
@JvmName(name = "fromTimeSource")
public fun TimeSource.asClock(origin: Instant): Clock {
   return new 1(`$this$asClock`, origin);
}

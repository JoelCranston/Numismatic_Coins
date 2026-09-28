package kotlin.time

import kotlin.internal.PlatformImplementationsKt

@ExperimentalTime
private final val systemClock: Clock = PlatformImplementationsKt.IMPLEMENTATIONS.getSystemClock()

@ExperimentalTime
internal fun systemClockNow(): Instant {
   return systemClock.now();
}

@ExperimentalTime
internal fun serializedInstant(instant: Instant): Any {
   return new InstantSerialized(instant.getEpochSeconds(), instant.getNanosecondsOfSecond());
}

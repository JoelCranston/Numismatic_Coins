@file:JvmName(name = "InstantConversionsJDK8Kt")

package kotlin.time.jdk8

import kotlin.time.ExperimentalTime
import kotlin.time.Instant

@SinceKotlin(version = "2.1")
@ExperimentalTime
public fun Instant.toJavaInstant(): java.time.Instant {
   val var10000: java.time.Instant = java.time.Instant.ofEpochSecond(
      `$this$toJavaInstant`.getEpochSeconds(), (long)`$this$toJavaInstant`.getNanosecondsOfSecond()
   );
   return var10000;
}

@SinceKotlin(version = "2.1")
@ExperimentalTime
public fun java.time.Instant.toKotlinInstant(): Instant {
   return Instant.Companion.fromEpochSeconds(`$this$toKotlinInstant`.getEpochSecond(), `$this$toKotlinInstant`.getNano());
}

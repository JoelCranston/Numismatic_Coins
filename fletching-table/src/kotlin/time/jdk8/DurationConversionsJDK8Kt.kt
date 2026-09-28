@file:JvmName(name = "DurationConversionsJDK8Kt")

@file:SourceDebugExtension(["SMAP\nDurationConversions.kt\nKotlin\n*S Kotlin\n*F\n+ 1 DurationConversions.kt\nkotlin/time/jdk8/DurationConversionsJDK8Kt\n+ 2 Duration.kt\nkotlin/time/Duration\n*L\n1#1,33:1\n548#2:34\n*S KotlinDebug\n*F\n+ 1 DurationConversions.kt\nkotlin/time/jdk8/DurationConversionsJDK8Kt\n*L\n33#1:34\n*E\n"])

package kotlin.time.jdk8

import java.time.Duration
import kotlin.internal.InlineOnly
import kotlin.jvm.internal.SourceDebugExtension
import kotlin.time.DurationKt
import kotlin.time.DurationUnit

@SinceKotlin(version = "1.6")
@InlineOnly
public inline fun Duration.toKotlinDuration(): kotlin.time.Duration {
   return kotlin.time.Duration.plus-LRDsOJo(
      DurationKt.toDuration(`$this$toKotlinDuration`.getSeconds(), DurationUnit.SECONDS),
      DurationKt.toDuration(`$this$toKotlinDuration`.getNano(), DurationUnit.NANOSECONDS)
   );
}

@SinceKotlin(version = "1.6")
@InlineOnly
public inline fun kotlin.time.Duration.toJavaDuration(): Duration {
   val var10000: Duration = Duration.ofSeconds(kotlin.time.Duration.getInWholeSeconds-impl(var0), (long)kotlin.time.Duration.getNanosecondsComponent-impl(var0));
   return var10000;
}

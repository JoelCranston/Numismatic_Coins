package kotlinx.coroutines

import java.util.concurrent.atomic.AtomicLong
import kotlin.internal.InlineOnly

public const val DEBUG_PROPERTY_NAME: String = "kotlinx.coroutines.debug"
internal const val STACKTRACE_RECOVERY_PROPERTY_NAME: String = "kotlinx.coroutines.stacktrace.recovery"
public const val DEBUG_PROPERTY_VALUE_AUTO: String = "auto"
public const val DEBUG_PROPERTY_VALUE_ON: String = "on"
public const val DEBUG_PROPERTY_VALUE_OFF: String = "off"
internal final val ASSERTIONS_ENABLED: Boolean
internal final val DEBUG: Boolean

@PublishedApi
internal final val RECOVER_STACK_TRACES: Boolean

internal final val COROUTINE_ID: AtomicLong

internal fun resetCoroutineId() {
   COROUTINE_ID.set(0L);
}

@InlineOnly
internal inline fun assert(value: () -> Boolean) {
   if (getASSERTIONS_ENABLED() && !value.invoke() as java.lang.Boolean) {
      throw new AssertionError();
   }
}

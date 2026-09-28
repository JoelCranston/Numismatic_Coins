package kotlinx.coroutines.debug.internal

import kotlin.coroutines.Continuation

internal fun probeCoroutineResumed(frame: Continuation<*>) {
   DebugProbesImpl.INSTANCE.probeCoroutineResumed$kotlinx_coroutines_core(frame);
}

internal fun probeCoroutineSuspended(frame: Continuation<*>) {
   DebugProbesImpl.INSTANCE.probeCoroutineSuspended$kotlinx_coroutines_core(frame);
}

internal fun <T> probeCoroutineCreated(completion: Continuation<T>): Continuation<T> {
   return DebugProbesImpl.INSTANCE.probeCoroutineCreated$kotlinx_coroutines_core(completion);
}

package io.ktor.network.util

import io.ktor.network.util.Timeout.initTimeoutJob.1
import kotlin.coroutines.Continuation
import kotlinx.coroutines.BuildersKt
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job

internal class Timeout(name: String, timeoutMs: Long, clock: () -> Long, scope: CoroutineScope, onTimeout: (Continuation<Unit>) -> Any?) {
   private final val name: String
   private final val timeoutMs: Long
   private final val clock: () -> Long
   private final val scope: CoroutineScope
   private final val onTimeout: (Continuation<Unit>) -> Any?
   private final var workerJob: Job?

   init {
      this.name = name;
      this.timeoutMs = timeoutMs;
      this.clock = clock;
      this.scope = scope;
      this.onTimeout = onTimeout;
      this.lastActivityTime = 0L;
      this.isStarted = 0;
      this.workerJob = this.initTimeoutJob();
   }

   public fun start() {
      this.lastActivityTime = this.clock.invoke().longValue();
      this.isStarted = 1;
   }

   public fun stop() {
      this.isStarted = 0;
   }

   public fun finish() {
      if (this.workerJob != null) {
         Job.DefaultImpls.cancel$default(this.workerJob, null, 1, null);
      }
   }

   private fun initTimeoutJob(): Job? {
      return if (this.timeoutMs == java.lang.Long.MAX_VALUE)
         null
         else
         BuildersKt.launch$default(
            this.scope, this.scope.getCoroutineContext().plus(new CoroutineName("Timeout ${this.name}")), null, new 1(this, null), 2, null
         );
   }
}

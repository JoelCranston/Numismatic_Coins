package io.ktor.util.pipeline

import io.ktor.util.pipeline.SuspendFunctionGun.continuation.1
import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.intrinsics.IntrinsicsKt
import kotlin.coroutines.jvm.internal.DebugProbesKt
import kotlin.jvm.functions.Function3

internal class SuspendFunctionGun<TSubject, TContext>(initial: Any, context: Any, blocks: List<(PipelineContext<Any, Any>, Any, Continuation<Unit>) -> Any?>) : PipelineContext(
      (TContext)context
   ) {
   private final val blocks: List<(PipelineContext<Any, Any>, Any, Continuation<Unit>) -> Any?>

   public open val coroutineContext: CoroutineContext
      public open get() {
         return this.continuation.getContext();
      }


   internal final val continuation: Continuation<Unit>
   public open var subject: Any
   private final val suspensions: Array<Continuation<Any>?>
   private final var lastSuspensionIndex: Int
   private final var index: Int

   init {
      this.blocks = blocks;
      this.continuation = new 1(this);
      this.subject = (TSubject)initial;
      this.suspensions = new Continuation[this.blocks.size()];
      this.lastSuspensionIndex = -1;
   }

   public override fun finish() {
      this.index = this.blocks.size();
   }

   public override suspend fun proceed(): Any {
      val var10000: Any;
      if (this.index == this.blocks.size()) {
         var10000 = this.getSubject();
      } else {
         this.addContinuation$ktor_utils(IntrinsicsKt.intercepted(`$completion`));
         if (this.loop(true)) {
            this.discardLastRootContinuation();
            var10000 = this.getSubject();
         } else {
            var10000 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
         }
      }

      if (var10000 === IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
         DebugProbesKt.probeCoroutineSuspended(`$completion`);
      }

      return var10000;
   }

   public override suspend fun proceedWith(subject: Any): Any {
      this.setSubject((TSubject)subject);
      return this.proceed(`$completion`);
   }

   internal override suspend fun execute(initial: Any): Any {
      this.index = 0;
      if (this.index == this.blocks.size()) {
         return initial;
      } else {
         this.setSubject((TSubject)initial);
         if (this.lastSuspensionIndex >= 0) {
            throw new IllegalStateException("Already started");
         } else {
            return this.proceed(`$completion`);
         }
      }
   }

   private fun loop(direct: Boolean): Boolean {
      while (true) {
         val currentIndex: Int = this.index;
         if (this.index == this.blocks.size()) {
            if (!direct) {
               this.resumeRootWith(Result.constructor-impl(this.getSubject()));
               return false;
            }

            return true;
         }

         this.index = currentIndex + 1;
         val next: Function3 = this.blocks.get(currentIndex);

         try {
            if (PipelineJvmKt.pipelineStartCoroutineUninterceptedOrReturn(next, this, this.getSubject(), this.continuation) === IntrinsicsKt.getCOROUTINE_SUSPENDED()
               )
             {
               return false;
            }
         } catch (var5: java.lang.Throwable) {
            this.resumeRootWith(Result.constructor-impl(ResultKt.createFailure(var5)));
            return false;
         }
      }
   }

   private fun resumeRootWith(result: Result<Any>) {
      if (this.lastSuspensionIndex < 0) {
         throw new IllegalStateException("No more continuations to resume".toString());
      } else {
         val var10000: Continuation = this.suspensions[this.lastSuspensionIndex];
         val var5: Array<Continuation> = this.suspensions;
         val exception: Int = this.lastSuspensionIndex;
         this.lastSuspensionIndex += -1;
         var5[exception] = null;
         if (!Result.isFailure-impl(result)) {
            var10000.resumeWith(result);
         } else {
            val var6: java.lang.Throwable = Result.exceptionOrNull-impl(result);
            var10000.resumeWith(Result.constructor-impl(ResultKt.createFailure(StackTraceRecoverKt.recoverStackTraceBridge(var6, var10000))));
         }
      }
   }

   private fun discardLastRootContinuation() {
      if (this.lastSuspensionIndex < 0) {
         throw new IllegalStateException("No more continuations to resume");
      } else {
         val var10000: Array<Continuation> = this.suspensions;
         val var1: Int = this.lastSuspensionIndex;
         this.lastSuspensionIndex += -1;
         var10000[var1] = null;
      }
   }

   internal fun addContinuation(continuation: Continuation<Any>) {
      val var10000: Array<Continuation> = this.suspensions;
      this.lastSuspensionIndex++;
      var10000[this.lastSuspensionIndex] = continuation;
   }
}

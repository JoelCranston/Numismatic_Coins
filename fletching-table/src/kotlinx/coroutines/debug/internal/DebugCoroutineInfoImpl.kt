package kotlinx.coroutines.debug.internal

import java.lang.ref.WeakReference
import java.util.ArrayList
import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.intrinsics.IntrinsicsKt
import kotlin.coroutines.jvm.internal.CoroutineStackFrame
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.coroutines.debug.internal.DebugCoroutineInfoImpl.creationStackTrace.1

@PublishedApi
@SourceDebugExtension(["SMAP\nDebugCoroutineInfoImpl.kt\nKotlin\n*S Kotlin\n*F\n+ 1 DebugCoroutineInfoImpl.kt\nkotlinx/coroutines/debug/internal/DebugCoroutineInfoImpl\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,178:1\n1#2:179\n*E\n"])
internal class DebugCoroutineInfoImpl internal constructor(context: CoroutineContext?, creationStackBottom: StackTraceFrame?, sequenceNumber: Long) {
   internal final val creationStackBottom: StackTraceFrame?
   public final val sequenceNumber: Long
   private final val _context: WeakReference<CoroutineContext?>

   public final val context: CoroutineContext?
      public final get() {
         return this._context.get();
      }


   public final val creationStackTrace: List<StackTraceElement>
      public final get() {
         return this.creationStackTrace();
      }


   internal final val state: String
      internal final get() {
         return this._state;
      }


   public final var _state: String
      private set

   private final var unmatchedResume: Int

   public final var lastObservedThread: Thread?
      private set

   public final var _lastObservedFrame: WeakReference<CoroutineStackFrame>?
      private set

   internal final var lastObservedFrame: CoroutineStackFrame?
      internal final get() {
         return if (this._lastObservedFrame != null) this._lastObservedFrame.get() else null;
      }

      internal final set(value) {
         var var10000: DebugCoroutineInfoImpl = this;
         val var10001: WeakReference;
         if (value != null) {
            var10001 = new WeakReference<>(value);
            var10000 = this;
         } else {
            var10001 = null;
         }

         var10000._lastObservedFrame = var10001;
      }


   init {
      this.creationStackBottom = creationStackBottom;
      this.sequenceNumber = sequenceNumber;
      this._context = new WeakReference<>(context);
      this._state = "CREATED";
   }

   @Synchronized
   internal fun updateState(state: String, frame: Continuation<*>, shouldBeMatched: Boolean) {
      if (this._state == "RUNNING" && state == "RUNNING" && shouldBeMatched) {
         this.unmatchedResume++;
      } else if (this.unmatchedResume > 0 && state == "SUSPENDED") {
         this.unmatchedResume += -1;
         return;
      }

      if (!(this._state == state) || !(state == "SUSPENDED") || this.getLastObservedFrame$kotlinx_coroutines_core() == null) {
         this._state = state;
         this.setLastObservedFrame$kotlinx_coroutines_core(frame as? CoroutineStackFrame);
         this.lastObservedThread = if (state == "RUNNING") Thread.currentThread() else null;
      }
   }

   internal fun lastObservedStackTrace(): List<StackTraceElement> {
      val var10000: CoroutineStackFrame = this.getLastObservedFrame$kotlinx_coroutines_core();
      if (var10000 == null) {
         return CollectionsKt.emptyList();
      } else {
         var frame: CoroutineStackFrame = var10000;

         val result: ArrayList;
         for (result = new ArrayList(); frame != null; frame = frame.getCallerFrame()) {
            val var5: StackTraceElement = frame.getStackTraceElement();
            if (var5 != null) {
               result.add(var5);
            }
         }

         return result;
      }
   }

   private fun creationStackTrace(): List<StackTraceElement> {
      return if (this.creationStackBottom == null)
         CollectionsKt.emptyList()
         else
         SequencesKt.toList(SequencesKt.sequence(new 1(this, this.creationStackBottom, null)));
   }

   private tailrec suspend fun SequenceScope<StackTraceElement>.yieldFrames(frame: CoroutineStackFrame?) {
      var `$continuation`: Continuation;
      label49: {
         if (`$completion` is kotlinx.coroutines.debug.internal.DebugCoroutineInfoImpl.yieldFrames.1) {
            `$continuation` = `$completion` as kotlinx.coroutines.debug.internal.DebugCoroutineInfoImpl.yieldFrames.1;
            if (((`$completion` as kotlinx.coroutines.debug.internal.DebugCoroutineInfoImpl.yieldFrames.1).label and Integer.MIN_VALUE) != 0) {
               `$continuation`.label -= Integer.MIN_VALUE;
               break label49;
            }
         }

         `$continuation` = new kotlinx.coroutines.debug.internal.DebugCoroutineInfoImpl.yieldFrames.1(this, `$completion`);
      }

      val `$result`: Any = `$continuation`.result;
      val var11: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
      var var14: DebugCoroutineInfoImpl;
      switch ($continuation.label) {
         case 0:
            ResultKt.throwOnFailure(`$result`);
            var14 = this;
            break;
         case 1:
            var14 = `$continuation`.L$2 as DebugCoroutineInfoImpl;
            frame = `$continuation`.L$1 as CoroutineStackFrame;
            `$this$yieldFrames` = `$continuation`.L$0 as SequenceScope;
            ResultKt.throwOnFailure(`$result`);
            val caller: CoroutineStackFrame = frame.getCallerFrame();
            if (caller == null) {
               return Unit.INSTANCE;
            }

            var14 = var14;
            `$this$yieldFrames` = `$this$yieldFrames`;
            frame = caller;
            break;
         default:
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
      }

      while (frame != null) {
         val var10000: StackTraceElement = frame.getStackTraceElement();
         if (var10000 != null) {
            `$continuation`.L$0 = `$this$yieldFrames`;
            `$continuation`.L$1 = frame;
            `$continuation`.L$2 = var14;
            `$continuation`.label = 1;
            if (`$this$yieldFrames`.yield(var10000, `$continuation`) === var11) {
               return var11;
            }
         }

         val var15: CoroutineStackFrame = frame.getCallerFrame();
         if (var15 == null) {
            return Unit.INSTANCE;
         }

         var14 = var14;
         `$this$yieldFrames` = `$this$yieldFrames`;
         frame = var15;
      }

      return Unit.INSTANCE;
   }

   public override fun toString(): String {
      return "DebugCoroutineInfo(state=${this.getState$kotlinx_coroutines_core()},context=${this.getContext()})";
   }
}

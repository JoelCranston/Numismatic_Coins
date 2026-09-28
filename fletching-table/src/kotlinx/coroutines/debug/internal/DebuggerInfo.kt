package kotlinx.coroutines.debug.internal

import java.io.Serializable
import java.lang.Thread.State
import kotlin.coroutines.ContinuationInterceptor
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.CoroutineId
import kotlinx.coroutines.CoroutineName

@PublishedApi
internal class DebuggerInfo(source: DebugCoroutineInfoImpl, context: CoroutineContext) : Serializable {
   public final val coroutineId: Long?
   public final val dispatcher: String?
   public final val name: String?
   public final val state: String
   public final val lastObservedThreadState: String?
   public final val lastObservedThreadName: String?
   public final val lastObservedStackTrace: List<StackTraceElement>
   public final val sequenceNumber: Long

   init {
      var var6: java.lang.String;
      label31: {
         super();
         val var10001: CoroutineId = context.get(CoroutineId.Key);
         this.coroutineId = if (var10001 != null) var10001.getId() else null;
         val var3: ContinuationInterceptor = context.get(ContinuationInterceptor.Key);
         this.dispatcher = if (var3 != null) var3.toString() else null;
         val var4: CoroutineName = context.get(CoroutineName.Key);
         this.name = if (var4 != null) var4.getName() else null;
         this.state = source.getState$kotlinx_coroutines_core();
         if (source.lastObservedThread != null) {
            val var5: State = source.lastObservedThread.getState();
            if (var5 != null) {
               var6 = var5.toString();
               break label31;
            }
         }

         var6 = null;
      }

      this.lastObservedThreadState = var6;
      this.lastObservedThreadName = if (source.lastObservedThread != null) source.lastObservedThread.getName() else null;
      this.lastObservedStackTrace = source.lastObservedStackTrace$kotlinx_coroutines_core();
      this.sequenceNumber = source.sequenceNumber;
   }
}

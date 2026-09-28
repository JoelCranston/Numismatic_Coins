package kotlinx.coroutines.slf4j

import kotlin.coroutines.AbstractCoroutineContextElement
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.ThreadContextElement
import org.slf4j.MDC

public class MDCContext(contextMap: Map<String, String>? = MDC.getCopyOfContextMap()) : AbstractCoroutineContextElement(Key),
   ThreadContextElement<java.util.Map<java.lang.String, ? extends java.lang.String>> {
   public final val contextMap: Map<String, String>?

   init {
      this.contextMap = contextMap;
   }

   public open fun updateThreadContext(context: CoroutineContext): Map<String, String>? {
      val oldState: java.util.Map = MDC.getCopyOfContextMap();
      this.setCurrent(this.contextMap);
      return oldState;
   }

   public open fun restoreThreadContext(context: CoroutineContext, oldState: Map<String, String>?) {
      this.setCurrent(oldState);
   }

   private fun setCurrent(contextMap: Map<String, String>?) {
      if (contextMap == null) {
         MDC.clear();
      } else {
         MDC.setContextMap(contextMap);
      }
   }

   fun MDCContext() {
      this(null, 1, null);
   }

   public companion object Key : CoroutineContext.Key<MDCContext>
}

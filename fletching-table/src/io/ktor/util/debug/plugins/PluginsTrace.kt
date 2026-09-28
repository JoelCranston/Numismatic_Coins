package io.ktor.util.debug.plugins

import java.util.ArrayList
import kotlin.coroutines.AbstractCoroutineContextElement
import kotlin.coroutines.CoroutineContext

public data class PluginsTrace(eventOrder: MutableList<PluginTraceElement> = (new ArrayList()) as java.util.List) : AbstractCoroutineContextElement(Key) {
   public final val eventOrder: MutableList<PluginTraceElement>

   init {
      this.eventOrder = eventOrder;
   }

   public override fun toString(): String {
      return "PluginsTrace(${CollectionsKt.joinToString$default(this.eventOrder, null, null, null, 0, null, null, 63, null)})";
   }

   public operator fun component1(): MutableList<PluginTraceElement> {
      return this.eventOrder;
   }

   public fun copy(eventOrder: MutableList<PluginTraceElement> = this.eventOrder): PluginsTrace {
      return new PluginsTrace(eventOrder);
   }

   public override fun hashCode(): Int {
      return this.eventOrder.hashCode();
   }

   public override operator fun equals(other: Any?): Boolean {
      if (this === other) {
         return true;
      } else if (other !is PluginsTrace) {
         return false;
      } else {
         return this.eventOrder == (other as PluginsTrace).eventOrder;
      }
   }

   fun PluginsTrace() {
      this(null, 1, null);
   }

   public companion object Key : CoroutineContext.Key<PluginsTrace>
}

package io.ktor.util.debug.plugins

import kotlin.enums.EnumEntries

public data class PluginTraceElement(pluginName: String, handler: String, event: io.ktor.util.debug.plugins.PluginTraceElement.PluginEvent) {
   public final val pluginName: String
   public final val handler: String
   public final val event: io.ktor.util.debug.plugins.PluginTraceElement.PluginEvent

   init {
      this.pluginName = pluginName;
      this.handler = handler;
      this.event = event;
   }

   public operator fun component1(): String {
      return this.pluginName;
   }

   public operator fun component2(): String {
      return this.handler;
   }

   public operator fun component3(): io.ktor.util.debug.plugins.PluginTraceElement.PluginEvent {
      return this.event;
   }

   public fun copy(
      pluginName: String = this.pluginName,
      handler: String = this.handler,
      event: io.ktor.util.debug.plugins.PluginTraceElement.PluginEvent = this.event
   ): PluginTraceElement {
      return new PluginTraceElement(pluginName, handler, event);
   }

   public override fun toString(): String {
      return "PluginTraceElement(pluginName=${this.pluginName}, handler=${this.handler}, event=${this.event})";
   }

   public override fun hashCode(): Int {
      return (this.pluginName.hashCode() * 31 + this.handler.hashCode()) * 31 + this.event.hashCode();
   }

   public override operator fun equals(other: Any?): Boolean {
      if (this === other) {
         return true;
      } else if (other !is PluginTraceElement) {
         return false;
      } else {
         val var2: PluginTraceElement = other as PluginTraceElement;
         if (!(this.pluginName == (other as PluginTraceElement).pluginName)) {
            return false;
         } else if (!(this.handler == var2.handler)) {
            return false;
         } else {
            return this.event === var2.event;
         }
      }
   }

   public enum class PluginEvent {
      STARTED,
      FINISHED
      @JvmStatic
      fun getEntries(): EnumEntries<PluginTraceElement.PluginEvent> {
         return $ENTRIES;
      }
   }
}

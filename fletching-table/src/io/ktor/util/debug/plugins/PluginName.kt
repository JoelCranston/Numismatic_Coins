package io.ktor.util.debug.plugins

import kotlin.coroutines.AbstractCoroutineContextElement
import kotlin.coroutines.CoroutineContext

public data class PluginName(pluginName: String) : AbstractCoroutineContextElement(Key) {
   public final val pluginName: String

   init {
      this.pluginName = pluginName;
   }

   public override fun toString(): String {
      return "PluginName(${this.pluginName})";
   }

   public operator fun component1(): String {
      return this.pluginName;
   }

   public fun copy(pluginName: String = this.pluginName): PluginName {
      return new PluginName(pluginName);
   }

   public override fun hashCode(): Int {
      return this.pluginName.hashCode();
   }

   public override operator fun equals(other: Any?): Boolean {
      if (this === other) {
         return true;
      } else if (other !is PluginName) {
         return false;
      } else {
         return this.pluginName == (other as PluginName).pluginName;
      }
   }

   public companion object Key : CoroutineContext.Key<PluginName>
}

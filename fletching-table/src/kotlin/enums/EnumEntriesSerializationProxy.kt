package kotlin.enums

import java.io.Serializable

internal class EnumEntriesSerializationProxy<E extends java.lang.Enum<E>>(vararg entries: Any) : Serializable {
   private final val c: Class<Any>

   init {
      val var10001: Class = entries.getClass().getComponentType();
      this.c = var10001;
   }

   private fun readResolve(): Any {
      val var10000: Array<Any> = this.c.getEnumConstants();
      return EnumEntriesKt.enumEntries((E[])var10000);
   }

   private companion object {
      private const val serialVersionUID: Long
   }
}

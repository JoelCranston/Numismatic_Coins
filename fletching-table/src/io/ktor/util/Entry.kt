package io.ktor.util

import kotlin.jvm.internal.markers.KMutableMap

private class Entry<Key, Value>(key: Any, value: Any) : java.util.Map.Entry<Key, Value>, KMutableMap.Entry {
   public open val key: Any
   public open var value: Any

   init {
      this.key = (Key)key;
      this.value = (Value)value;
   }

   public override fun setValue(newValue: Any): Any {
      this.setValue((Value)newValue);
      return this.getValue();
   }

   public override fun hashCode(): Int {
      var var10001: Any = this.getKey();
      val var10000: Int = 527 + var10001.hashCode();
      var10001 = this.getValue();
      return var10000 + var10001.hashCode();
   }

   public override operator fun equals(other: Any?): Boolean {
      if (other != null && other is java.util.Map.Entry) {
         return (other as java.util.Map.Entry).getKey() == this.getKey() && (other as java.util.Map.Entry).getValue() == this.getValue();
      } else {
         return false;
      }
   }

   public override fun toString(): String {
      return "${this.getKey()}=${this.getValue()}";
   }
}

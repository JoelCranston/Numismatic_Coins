package io.ktor.http

import java.util.Locale

public data class HeaderValueParam(name: String, value: String, escapeValue: Boolean) {
   public final val name: String
   public final val value: String
   public final val escapeValue: Boolean

   init {
      this.name = name;
      this.value = value;
      this.escapeValue = escapeValue;
   }

   public constructor(name: String, value: String) : this(name, value, false)
   public override operator fun equals(other: Any?): Boolean {
      return other is HeaderValueParam
         && StringsKt.equals((other as HeaderValueParam).name, this.name, true)
         && StringsKt.equals((other as HeaderValueParam).value, this.value, true);
   }

   public override fun hashCode(): Int {
      val var10000: java.lang.String = this.name.toLowerCase(Locale.ROOT);
      val result: Int = var10000.hashCode();
      val var10001: Int = 31 * result;
      val var10002: java.lang.String = this.value.toLowerCase(Locale.ROOT);
      return result + var10001 + var10002.hashCode();
   }

   public operator fun component1(): String {
      return this.name;
   }

   public operator fun component2(): String {
      return this.value;
   }

   public operator fun component3(): Boolean {
      return this.escapeValue;
   }

   public fun copy(name: String = this.name, value: String = this.value, escapeValue: Boolean = this.escapeValue): HeaderValueParam {
      return new HeaderValueParam(name, value, escapeValue);
   }

   public override fun toString(): String {
      return "HeaderValueParam(name=${this.name}, value=${this.value}, escapeValue=${this.escapeValue})";
   }
}

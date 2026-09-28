package io.ktor.http

import io.ktor.util.StringValuesImpl

public class HeadersImpl(values: Map<String, List<String>> = MapsKt.emptyMap()) : StringValuesImpl(true, values), Headers {
   public override fun toString(): String {
      return "Headers ${this.entries()}";
   }

   fun HeadersImpl() {
      this(null, 1, null);
   }
}

package io.ktor.http

import io.ktor.util.StringValuesImpl

public class ParametersImpl(values: Map<String, List<String>> = MapsKt.emptyMap()) : StringValuesImpl(true, values), Parameters {
   public override fun toString(): String {
      return "Parameters ${this.entries()}";
   }

   fun ParametersImpl() {
      this(null, 1, null);
   }
}

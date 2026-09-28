package io.ktor.http

import io.ktor.util.StringValuesSingleImpl

public class ParametersSingleImpl(name: String, values: List<String>) : StringValuesSingleImpl(true, name, values), Parameters {
   public override fun toString(): String {
      return "Parameters ${this.entries()}";
   }
}

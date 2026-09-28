package io.ktor.http

import io.ktor.util.StringValuesSingleImpl

public class HeadersSingleImpl(name: String, values: List<String>) : StringValuesSingleImpl(true, name, values), Headers {
   public override fun toString(): String {
      return "Headers ${this.entries()}";
   }
}

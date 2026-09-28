package io.ktor.http.parsing

internal interface Parser {
   public abstract fun parse(input: String): ParseResult? {
   }

   public abstract fun match(input: String): Boolean {
   }
}

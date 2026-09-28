package io.ktor.http.parsing

internal class AnyOfGrammar(value: String) : Grammar() {
   public final val value: String

   init {
      this.value = value;
   }
}

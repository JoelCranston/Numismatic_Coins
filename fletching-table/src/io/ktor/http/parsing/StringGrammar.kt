package io.ktor.http.parsing

internal class StringGrammar(value: String) : Grammar() {
   public final val value: String

   init {
      this.value = value;
   }
}

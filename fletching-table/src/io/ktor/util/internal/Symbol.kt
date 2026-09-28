package io.ktor.util.internal

private class Symbol(symbol: String) {
   public final val symbol: String

   init {
      this.symbol = symbol;
   }

   public override fun toString(): String {
      return this.symbol;
   }
}

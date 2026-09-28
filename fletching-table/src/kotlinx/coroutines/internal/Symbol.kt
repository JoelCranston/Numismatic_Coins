package kotlinx.coroutines.internal

internal class Symbol(symbol: String) {
   public final val symbol: String

   init {
      this.symbol = symbol;
   }

   public override fun toString(): String {
      return "<${this.symbol}>";
   }

   public inline fun <T> unbox(value: Any?): T {
      return (T)(if (value === this) null else value);
   }
}

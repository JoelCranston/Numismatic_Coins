package io.ktor.http.cio.internals

public class MutableRange(start: Int, end: Int) {
   public final var start: Int
   public final var end: Int

   init {
      this.start = start;
      this.end = end;
   }

   public override fun toString(): String {
      return "MutableRange(start=${this.start}, end=${this.end})";
   }
}

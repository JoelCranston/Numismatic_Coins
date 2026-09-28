package kotlin.io

import java.io.BufferedReader
import kotlin.io.LinesSequence.iterator.1

private class LinesSequence(reader: BufferedReader) : Sequence<java.lang.String> {
   private final val reader: BufferedReader

   init {
      this.reader = reader;
   }

   public override operator fun iterator(): Iterator<String> {
      return new 1(this);
   }
}

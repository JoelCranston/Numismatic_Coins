package kotlinx.serialization.json.internal

internal class ArrayAsSequence(buffer: CharArray) : java.lang.CharSequence {
   internal final val buffer: CharArray

   public open var length: Int
      internal final set

   init {
      this.buffer = buffer;
      this.length = this.buffer.length;
   }

   public open operator fun get(index: Int): Char {
      return this.buffer[index];
   }

   public override fun subSequence(startIndex: Int, endIndex: Int): CharSequence {
      return StringsKt.concatToString(this.buffer, startIndex, Math.min(endIndex, this.length()));
   }

   public fun substring(startIndex: Int, endIndex: Int): String {
      return StringsKt.concatToString(this.buffer, startIndex, Math.min(endIndex, this.length()));
   }

   public fun trim(newSize: Int) {
      this.setLength(Math.min(this.buffer.length, newSize));
   }

   public override fun toString(): String {
      return this.substring(0, this.length());
   }
}

package kotlinx.serialization.json.internal

@JsonFriendModuleApi
public abstract class InternalJsonReaderCodePointImpl : InternalJsonReader {
   private final var bufferedChar: Char?

   public abstract fun exhausted(): Boolean {
   }

   public abstract fun nextCodePoint(): Int {
   }

   public override fun read(buffer: CharArray, bufferOffset: Int, count: Int): Int {
      var i: Int = 0;
      if (this.bufferedChar != null) {
         val var10001: Int = bufferOffset + 0;
         val var10002: Character = this.bufferedChar;
         buffer[var10001] = var10002;
         0++;
         this.bufferedChar = null;
      }

      while (i < count && !this.exhausted()) {
         val codePoint: Int = this.nextCodePoint();
         if (codePoint <= 65535) {
            buffer[bufferOffset + i] = (char)codePoint;
            i++;
         } else {
            val upChar: Char = (char)((codePoint ushr 10) + 55232);
            val lowChar: Char = (char)((codePoint and 1023) + 56320);
            buffer[bufferOffset + i] = upChar;
            if (++i < count) {
               buffer[bufferOffset + i] = lowChar;
               i++;
            } else {
               this.bufferedChar = lowChar;
            }
         }
      }

      return if (i > 0) i else -1;
   }
}

package kotlinx.serialization.json.internal

@JsonFriendModuleApi
public interface InternalJsonWriter {
   public abstract fun writeLong(value: Long) {
   }

   public abstract fun writeChar(char: Char) {
   }

   public abstract fun write(text: String) {
   }

   public abstract fun writeQuoted(text: String) {
   }

   public abstract fun release() {
   }

   public companion object {
      public inline fun doWriteEscaping(text: String, writeImpl: (String, Int, Int) -> Unit) {
         var lastPos: Int = 0;
         var i: Int = 0;

         for (int var6 = text.length(); i < var6; i++) {
            val c: Int = text.charAt(i);
            if (c < StringOpsKt.getESCAPE_STRINGS().length && StringOpsKt.getESCAPE_STRINGS()[c] != null) {
               writeImpl.invoke(text, lastPos, i);
               val var10000: java.lang.String = StringOpsKt.getESCAPE_STRINGS()[c];
               writeImpl.invoke(var10000, 0, var10000.length());
               lastPos = i + 1;
            }
         }

         writeImpl.invoke(text, lastPos, text.length());
      }
   }
}

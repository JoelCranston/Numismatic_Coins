package kotlinx.serialization.json.internal

internal object CharMappings {
   public final val ESCAPE_2_CHAR: CharArray = new char[117]
   public final val CHAR_TO_TOKEN: ByteArray = new byte[126]

   private fun initEscape() {
      for (int i = 0; i < 32; i++) {
         this.initC2ESC(i, 'u');
      }

      this.initC2ESC(8, 'b');
      this.initC2ESC(9, 't');
      this.initC2ESC(10, 'n');
      this.initC2ESC(12, 'f');
      this.initC2ESC(13, 'r');
      this.initC2ESC('/', '/');
      this.initC2ESC('"', '"');
      this.initC2ESC('\\', '\\');
   }

   private fun initCharToToken() {
      for (int i = 0; i < 33; i++) {
         this.initC2TC(i, (byte)127);
      }

      this.initC2TC(9, (byte)3);
      this.initC2TC(10, (byte)3);
      this.initC2TC(13, (byte)3);
      this.initC2TC(32, (byte)3);
      this.initC2TC(',', (byte)4);
      this.initC2TC(':', (byte)5);
      this.initC2TC('{', (byte)6);
      this.initC2TC('}', (byte)7);
      this.initC2TC('[', (byte)8);
      this.initC2TC(']', (byte)9);
      this.initC2TC('"', (byte)1);
      this.initC2TC('\\', (byte)2);
   }

   private fun initC2ESC(c: Int, esc: Char) {
      if (esc != 'u') {
         ESCAPE_2_CHAR[esc] = (char)c;
      }
   }

   private fun initC2ESC(c: Char, esc: Char) {
      this.initC2ESC((int)c, esc);
   }

   private fun initC2TC(c: Int, cl: Byte) {
      CHAR_TO_TOKEN[c] = cl;
   }

   private fun initC2TC(c: Char, cl: Byte) {
      this.initC2TC((int)c, cl);
   }

   @JvmStatic
   fun {
      INSTANCE.initEscape();
      INSTANCE.initCharToToken();
   }
}

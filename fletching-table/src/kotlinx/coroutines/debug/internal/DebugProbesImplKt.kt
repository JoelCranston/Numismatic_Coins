package kotlinx.coroutines.debug.internal

private fun String.repr(): String {
   val var1: StringBuilder = new StringBuilder();
   val `$this$repr_u24lambda_u240`: StringBuilder = var1;
   var1.append('"');
   var var4: Int = 0;

   for (int var5 = $this$repr.length(); var4 < var5; var4++) {
      val c: Char = `$this$repr`.charAt(var4);
      switch (c) {
         case '\b':
            `$this$repr_u24lambda_u240`.append("\\b");
            break;
         case '\t':
            `$this$repr_u24lambda_u240`.append("\\t");
            break;
         case '\n':
            `$this$repr_u24lambda_u240`.append("\\n");
            break;
         case '\r':
            `$this$repr_u24lambda_u240`.append("\\r");
            break;
         case '"':
            `$this$repr_u24lambda_u240`.append("\\\"");
            break;
         case '\\':
            `$this$repr_u24lambda_u240`.append("\\\\");
            break;
         default:
            `$this$repr_u24lambda_u240`.append(c);
      }
   }

   `$this$repr_u24lambda_u240`.append('"');
   return var1.toString();
}

@JvmSynthetic
fun `access$repr`(`$receiver`: java.lang.String): java.lang.String {
   return repr(`$receiver`);
}

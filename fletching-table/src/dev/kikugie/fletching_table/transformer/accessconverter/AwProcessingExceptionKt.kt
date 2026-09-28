package dev.kikugie.fletching_table.transformer.accessconverter

import java.nio.file.Path

internal inline fun reportThrowing(lexer: AwLexer, message: String, exception: Throwable? = null): Nothing {
   report(lexer, message, exception);
   throw new AwProcessingException();
}

@JvmSynthetic
fun `reportThrowing$default`(lexer: AwLexer, message: java.lang.String, exception: java.lang.Throwable, `$i$f$reportThrowing`: Int, var4: Any): Void {
   if ((`$i$f$reportThrowing` and 4) != 0) {
      exception = null;
   }

   report(lexer, message, exception);
   throw new AwProcessingException();
}

internal inline fun reportThrowing(file: Path, message: String, line: Int = -1, column: Int = -1, exception: Throwable? = null): Nothing {
   report(file, message, line, column, exception);
   throw new AwProcessingException();
}

@JvmSynthetic
fun `reportThrowing$default`(
   file: Path, message: java.lang.String, line: Int, column: Int, exception: java.lang.Throwable, `$i$f$reportThrowing`: Int, var6: Any
): Void {
   if ((`$i$f$reportThrowing` and 4) != 0) {
      line = -1;
   }

   if ((`$i$f$reportThrowing` and 8) != 0) {
      column = -1;
   }

   if ((`$i$f$reportThrowing` and 16) != 0) {
      exception = null;
   }

   report(file, message, line, column, exception);
   throw new AwProcessingException();
}

internal fun report(lexer: AwLexer, message: String, exception: Throwable? = null) {
   report(lexer.getFile(), message, lexer.getTokenLine(), lexer.getTokenColumn(), exception);
}

@JvmSynthetic
fun `report$default`(var0: AwLexer, var1: java.lang.String, var2: java.lang.Throwable, var3: Int, var4: Any) {
   if ((var3 and 4) != 0) {
      var2 = null;
   }

   report(var0, var1, var2);
}

internal fun report(file: Path, message: String, line: Int = -1, column: Int = -1, exception: Throwable? = null) {
   System.err.println(format(file, message, line, column, exception));
}

@JvmSynthetic
fun `report$default`(var0: Path, var1: java.lang.String, var2: Int, var3: Int, var4: java.lang.Throwable, var5: Int, var6: Any) {
   if ((var5 and 4) != 0) {
      var2 = -1;
   }

   if ((var5 and 8) != 0) {
      var3 = -1;
   }

   if ((var5 and 16) != 0) {
      var4 = null;
   }

   report(var0, var1, var2, var3, var4);
}

private fun format(file: Path, message: String, line: Int, column: Int, exception: Throwable?): String {
   val var5: StringBuilder = new StringBuilder();
   var5.append("e: file://${file.toAbsolutePath().toString()}");
   if (line >= 1) {
      var5.append(":$line");
      if (column >= 1) {
         var5.append(":$column");
      }
   }

   var5.append(" $message");
   if (exception != null) {
      var5.append('\n');
      val var10001: java.lang.String = (exception.getClass()::class).getQualifiedName();
      var var10002: java.lang.String = exception.getMessage();
      if (var10002 == null) {
         var10002 = "";
      }

      var5.append("    Caused by $var10001: $var10002").append('\n');
      var5.append(StringsKt.prependIndent(ExceptionsKt.stackTraceToString(exception), "        "));
   }

   return var5.toString();
}

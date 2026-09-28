package kotlinx.serialization.json.internal

import kotlin.jvm.internal.SourceDebugExtension

@SourceDebugExtension(["SMAP\nCommentLexers.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CommentLexers.kt\nkotlinx/serialization/json/internal/StringJsonLexerWithComments\n+ 2 AbstractJsonLexer.kt\nkotlinx/serialization/json/internal/AbstractJsonLexer\n*L\n1#1,219:1\n158#2:220\n*S KotlinDebug\n*F\n+ 1 CommentLexers.kt\nkotlinx/serialization/json/internal/StringJsonLexerWithComments\n*L\n66#1:220\n*E\n"])
internal class StringJsonLexerWithComments(source: String) : StringJsonLexer(source) {
   public override fun consumeNextToken(): Byte {
      val source: java.lang.String = this.getSource();
      val cpos: Int = this.skipWhitespaces();
      if (cpos < source.length() && cpos != -1) {
         this.currentPosition = cpos + 1;
         return AbstractJsonLexerKt.charToTokenClass(source.charAt(cpos));
      } else {
         return 10;
      }
   }

   public override fun canConsumeValue(): Boolean {
      val current: Int = this.skipWhitespaces();
      return current < this.getSource().length() && current != -1 && this.isValidValueStart(this.getSource().charAt(current));
   }

   public override fun consumeNextToken(expected: Char) {
      val source: java.lang.String = this.getSource();
      val current: Int = this.skipWhitespaces();
      if (current >= source.length() || current == -1) {
         this.currentPosition = -1;
         this.unexpectedToken(expected);
      }

      val c: Char = source.charAt(current);
      this.currentPosition = current + 1;
      if (c != expected) {
         this.unexpectedToken(expected);
      }
   }

   public override fun peekNextToken(): Byte {
      val source: java.lang.String = this.getSource();
      val cpos: Int = this.skipWhitespaces();
      if (cpos < source.length() && cpos != -1) {
         this.currentPosition = cpos;
         return AbstractJsonLexerKt.charToTokenClass(source.charAt(cpos));
      } else {
         return 10;
      }
   }

   public override fun skipWhitespaces(): Int {
      var var7: Int = this.currentPosition;
      if (this.currentPosition == -1) {
         return this.currentPosition;
      } else {
         val source: java.lang.String = this.getSource();

         while (current < source.length()) {
            val c: Char = source.charAt(var7);
            val `this_$iv`: AbstractJsonLexer = this;
            if (c == ' ' || c == '\n' || c == '\r' || c == '\t') {
               var7++;
            } else {
               if (c == '/' && var7 + 1 < source.length()) {
                  switch (source.charAt(current + 1)) {
                     case '*':
                        var7 = StringsKt.indexOf$default(source, "*/", var7 + 2, false, 4, null);
                        if (var7 == -1) {
                           this.currentPosition = source.length();
                           AbstractJsonLexer.fail$default(this, "Expected end of the block comment: \"*/\", but had EOF instead", 0, null, 6, null);
                           throw new KotlinNothingValueException();
                        }

                        var7 = var7 + 2;
                        continue;
                     case '/':
                        var7 = StringsKt.indexOf$default(source, '\n', var7 + 2, false, 4, null);
                        if (var7 == -1) {
                           var7 = source.length();
                        } else {
                           var7++;
                        }
                        continue;
                     default:
                  }
               }
               break;
            }
         }

         this.currentPosition = var7;
         return var7;
      }
   }
}

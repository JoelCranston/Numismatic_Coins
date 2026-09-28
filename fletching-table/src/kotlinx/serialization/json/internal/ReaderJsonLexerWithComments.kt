package kotlinx.serialization.json.internal

import kotlin.jvm.internal.SourceDebugExtension

@SourceDebugExtension(["SMAP\nCommentLexers.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CommentLexers.kt\nkotlinx/serialization/json/internal/ReaderJsonLexerWithComments\n+ 2 AbstractJsonLexer.kt\nkotlinx/serialization/json/internal/AbstractJsonLexer\n*L\n1#1,219:1\n158#2:220\n*S KotlinDebug\n*F\n+ 1 CommentLexers.kt\nkotlinx/serialization/json/internal/ReaderJsonLexerWithComments\n*L\n204#1:220\n*E\n"])
internal class ReaderJsonLexerWithComments(reader: InternalJsonReader, buffer: CharArray) : ReaderJsonLexer(reader, buffer) {
   public override fun consumeNextToken(expected: Char) {
      this.ensureHaveChars();
      val source: ArrayAsSequence = this.getSource();
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

   public override fun canConsumeValue(): Boolean {
      this.ensureHaveChars();
      val current: Int = this.skipWhitespaces();
      return current < this.getSource().length() && current != -1 && this.isValidValueStart(this.getSource().charAt(current));
   }

   public override fun consumeNextToken(): Byte {
      this.ensureHaveChars();
      val source: ArrayAsSequence = this.getSource();
      val cpos: Int = this.skipWhitespaces();
      if (cpos < source.length() && cpos != -1) {
         this.currentPosition = cpos + 1;
         return AbstractJsonLexerKt.charToTokenClass(source.charAt(cpos));
      } else {
         return 10;
      }
   }

   public override fun peekNextToken(): Byte {
      this.ensureHaveChars();
      val source: ArrayAsSequence = this.getSource();
      val cpos: Int = this.skipWhitespaces();
      if (cpos < source.length() && cpos != -1) {
         this.currentPosition = cpos;
         return AbstractJsonLexerKt.charToTokenClass(source.charAt(cpos));
      } else {
         return 10;
      }
   }

   private fun handleComment(position: Int): Pair<Int, Boolean> {
      var current: Int = position;
      var startIndex: Int = position + 2;
      switch (this.getSource().charAt(position + 1)) {
         case '*':
            var rareCaseHit: Boolean = false;

            while (current != -1) {
               current = StringsKt.indexOf$default(this.getSource(), "*/", startIndex, false, 4, null);
               if (current != -1) {
                  return TuplesKt.to(current + 2, true);
               }

               if (this.getSource().charAt(this.getSource().length() - 1) != '*') {
                  current = this.prefetchOrEof(this.getSource().length());
                  startIndex = current;
               } else {
                  current = this.prefetchWithinThreshold(this.getSource().length() - 1);
                  if (rareCaseHit) {
                     break;
                  }

                  rareCaseHit = true;
                  startIndex = current;
               }
            }

            this.currentPosition = this.getSource().length();
            AbstractJsonLexer.fail$default(this, "Expected end of the block comment: \"*/\", but had EOF instead", 0, null, 6, null);
            throw new KotlinNothingValueException();
         case '/':
            while (current != -1) {
               current = StringsKt.indexOf$default(this.getSource(), '\n', startIndex, false, 4, null);
               if (current != -1) {
                  return TuplesKt.to(current + 1, true);
               }

               current = this.prefetchOrEof(this.getSource().length());
               startIndex = current;
            }

            return TuplesKt.to(-1, true);
         default:
            return TuplesKt.to(position, false);
      }
   }

   private fun prefetchWithinThreshold(position: Int): Int {
      if (this.getSource().length() - position > this.threshold) {
         return position;
      } else {
         this.currentPosition = position;
         this.ensureHaveChars();
         return if (this.currentPosition == 0 && this.getSource().length() != 0) 0 else -1;
      }
   }

   public override fun skipWhitespaces(): Int {
      var current: Int = this.currentPosition;

      while (true) {
         current = this.prefetchOrEof(current);
         if (current == -1) {
            break;
         }

         val c: Char = this.getSource().charAt(current);
         val `this_$iv`: AbstractJsonLexer = this;
         if (c == ' ' || c == '\n' || c == '\r' || c == '\t') {
            current++;
         } else {
            if (c == '/' && current + 1 < this.getSource().length()) {
               val var6: Pair = this.handleComment(current);
               val var4: Int = (var6.component1() as java.lang.Number).intValue();
               val var7: Boolean = var6.component2() as java.lang.Boolean;
               current = var4;
               if (var7) {
                  continue;
               }
            }
            break;
         }
      }

      this.currentPosition = current;
      return current;
   }
}

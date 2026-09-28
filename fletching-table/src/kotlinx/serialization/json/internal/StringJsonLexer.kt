package kotlinx.serialization.json.internal

import kotlin.jvm.internal.SourceDebugExtension

@SourceDebugExtension(["SMAP\nStringJsonLexer.kt\nKotlin\n*S Kotlin\n*F\n+ 1 StringJsonLexer.kt\nkotlinx/serialization/json/internal/StringJsonLexer\n+ 2 AbstractJsonLexer.kt\nkotlinx/serialization/json/internal/AbstractJsonLexer\n+ 3 AbstractJsonLexer.kt\nkotlinx/serialization/json/internal/AbstractJsonLexer$fail$1\n+ 4 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,129:1\n158#2:130\n158#2:131\n158#2:132\n158#2:133\n226#2,10:134\n229#3:144\n1869#4,2:145\n*S KotlinDebug\n*F\n+ 1 StringJsonLexer.kt\nkotlinx/serialization/json/internal/StringJsonLexer\n*L\n23#1:130\n38#1:131\n57#1:132\n73#1:133\n95#1:134,10\n95#1:144\n109#1:145,2\n*E\n"])
internal open class StringJsonLexer(source: String) : AbstractJsonLexer {
   protected open val source: String

   init {
      this.source = source;
   }

   public override fun prefetchOrEof(position: Int): Int {
      return if (position < this.getSource().length()) position else -1;
   }

   public override fun consumeNextToken(): Byte {
      val source: java.lang.String = this.getSource();
      var cpos: Int = this.currentPosition;

      while (cpos != -1 && cpos < source.length()) {
         val c: Char = source.charAt(cpos++);
         val `this_$iv`: AbstractJsonLexer = this;
         if (c != ' ' && c != '\n' && c != '\r' && c != '\t') {
            this.currentPosition = cpos;
            return AbstractJsonLexerKt.charToTokenClass(c);
         }
      }

      this.currentPosition = source.length();
      return 10;
   }

   public override fun canConsumeValue(): Boolean {
      var current: Int = this.currentPosition;
      if (this.currentPosition == -1) {
         return false;
      } else {
         for (java.lang.String source = this.getSource(); current < source.length(); current++) {
            val c: Char = source.charAt(current);
            val `this_$iv`: AbstractJsonLexer = this;
            if (c != ' ' && c != '\n' && c != '\r' && c != '\t') {
               this.currentPosition = current;
               return this.isValidValueStart(c);
            }
         }

         this.currentPosition = current;
         return false;
      }
   }

   public override fun skipWhitespaces(): Int {
      var current: Int = this.currentPosition;
      if (this.currentPosition == -1) {
         return this.currentPosition;
      } else {
         for (java.lang.String source = this.getSource(); current < source.length(); current++) {
            val c: Char = source.charAt(current);
            val `this_$iv`: AbstractJsonLexer = this;
            if (c != ' ' && c != '\n' && c != '\r' && c != '\t') {
               break;
            }
         }

         this.currentPosition = current;
         return current;
      }
   }

   public override fun consumeNextToken(expected: Char) {
      if (this.currentPosition == -1) {
         this.unexpectedToken(expected);
      }

      val source: java.lang.String = this.getSource();
      var cpos: Int = this.currentPosition;

      while (cpos < source.length()) {
         val c: Char = source.charAt(cpos++);
         val `this_$iv`: AbstractJsonLexer = this;
         if (c != ' ' && c != '\n' && c != '\r' && c != '\t') {
            this.currentPosition = cpos;
            if (c == expected) {
               return;
            }

            this.unexpectedToken(expected);
         }
      }

      this.currentPosition = -1;
      this.unexpectedToken(expected);
   }

   public override fun consumeKeyString(): String {
      this.consumeNextToken('"');
      val current: Int = this.currentPosition;
      val closingQuote: Int = StringsKt.indexOf$default(this.getSource(), '"', current, false, 4, null);
      if (closingQuote != -1) {
         for (int i = current; i < closingQuote; i++) {
            if (this.getSource().charAt(var14) == '\\') {
               return this.consumeString(this.getSource(), this.currentPosition, var14);
            }
         }

         this.currentPosition = closingQuote + 1;
         val var10000: java.lang.String = this.getSource().substring(current, closingQuote);
         return var10000;
      } else {
         this.consumeStringLenient();
         val ix: AbstractJsonLexer = this;
         val `expected$iv`: java.lang.String = AbstractJsonLexerKt.tokenDescription((byte)1);
         val `position$iv`: Int = ix.currentPosition;
         AbstractJsonLexer.fail$default(
            ix,
            "Expected $`expected$iv`, but had '${if (ix.currentPosition != AbstractJsonLexer.access$getSource(ix).length() && `position$iv` >= 0)
               java.lang.String.valueOf(AbstractJsonLexer.access$getSource(ix).charAt(`position$iv`))
               else
               "EOF"}' instead",
            `position$iv`,
            null,
            4,
            null
         );
         throw new KotlinNothingValueException();
      }
   }

   public override fun consumeStringChunked(isLenient: Boolean, consumeChunk: (String) -> Unit) {
      val `$this$forEach$iv`: java.lang.Iterable;
      for (Object element$iv : $this$forEach$iv) {
         consumeChunk.invoke(`element$iv`);
      }
   }

   public override fun peekLeadingMatchingValue(keyToMatch: String, isLenient: Boolean): String? {
      label41: {
         val positionSnapshot: Int = this.currentPosition;

         label38: {
            label37: {
               label36: {
                  try {
                     if (this.consumeNextToken() != 6) {
                        break label38;
                     }

                     if (!(this.peekString(isLenient) == keyToMatch)) {
                        break label37;
                     }

                     this.discardPeeked();
                     if (this.consumeNextToken() != 5) {
                        break label36;
                     }

                     val var5: java.lang.String = this.peekString(isLenient);
                  } catch (var6: java.lang.Throwable) {
                     this.currentPosition = this.currentPosition;
                     this.discardPeeked();
                  }

                  this.currentPosition = positionSnapshot;
                  this.discardPeeked();
               }

               this.currentPosition = positionSnapshot;
               this.discardPeeked();
            }

            this.currentPosition = positionSnapshot;
            this.discardPeeked();
         }

         this.currentPosition = positionSnapshot;
         this.discardPeeked();
      }
   }
}

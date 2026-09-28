package kotlinx.serialization.json.internal

import kotlin.jvm.internal.SourceDebugExtension

@SourceDebugExtension(["SMAP\nReaderJsonLexer.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ReaderJsonLexer.kt\nkotlinx/serialization/json/internal/ReaderJsonLexer\n+ 2 AbstractJsonLexer.kt\nkotlinx/serialization/json/internal/AbstractJsonLexer\n+ 3 AbstractJsonLexer.kt\nkotlinx/serialization/json/internal/AbstractJsonLexer$fail$1\n*L\n1#1,221:1\n158#2:222\n158#2:223\n158#2:224\n226#2,10:225\n229#3:235\n*S KotlinDebug\n*F\n+ 1 ReaderJsonLexer.kt\nkotlinx/serialization/json/internal/ReaderJsonLexer\n*L\n66#1:222\n133#1:223\n150#1:224\n181#1:225,10\n181#1:235\n*E\n"])
internal open class ReaderJsonLexer(reader: InternalJsonReader, buffer: CharArray = CharArrayPoolBatchSize.INSTANCE.take()) : AbstractJsonLexer {
   public final val reader: InternalJsonReader
   public final val buffer: CharArray

   protected final var threshold: Int
      private set

   protected open val source: ArrayAsSequence

   init {
      this.reader = reader;
      this.buffer = buffer;
      this.threshold = 128;
      this.source = new ArrayAsSequence(this.buffer);
      this.preload(0);
   }

   public override fun canConsumeValue(): Boolean {
      this.ensureHaveChars();
      var var6: Int = this.currentPosition;

      while (true) {
         var6 = this.prefetchOrEof(var6);
         if (var6 == -1) {
            this.currentPosition = var6;
            return false;
         }

         val c: Char = this.getSource().charAt(var6);
         val `this_$iv`: AbstractJsonLexer = this;
         if (c != ' ' && c != '\n' && c != '\r' && c != '\t') {
            this.currentPosition = var6;
            return this.isValidValueStart(c);
         }

         var6++;
      }
   }

   private fun preload(unprocessedCount: Int) {
      val buffer: CharArray = this.getSource().getBuffer$kotlinx_serialization_json();
      if (unprocessedCount != 0) {
         ArraysKt.copyInto(buffer, buffer, 0, this.currentPosition, this.currentPosition + unprocessedCount);
      }

      var filledCount: Int = unprocessedCount;
      val sizeTotal: Int = this.getSource().length();

      while (filledCount != sizeTotal) {
         val actual: Int = this.reader.read(buffer, filledCount, sizeTotal - filledCount);
         if (actual == -1) {
            this.getSource().trim(filledCount);
            this.threshold = -1;
            break;
         }

         filledCount += actual;
      }

      this.currentPosition = 0;
   }

   public override fun prefetchOrEof(position: Int): Int {
      if (position < this.getSource().length()) {
         return position;
      } else {
         this.currentPosition = position;
         this.ensureHaveChars();
         return if (this.currentPosition == 0 && this.getSource().length() != 0) 0 else -1;
      }
   }

   public override fun consumeNextToken(): Byte {
      this.ensureHaveChars();
      val source: ArrayAsSequence = this.getSource();
      var var5: Int = this.currentPosition;

      val tc: Byte;
      do {
         var5 = this.prefetchOrEof(var5);
         if (var5 == -1) {
            this.currentPosition = var5;
            return 10;
         }

         tc = AbstractJsonLexerKt.charToTokenClass(source.charAt(var5++));
      } while (tc == 3);

      this.currentPosition = var5;
      return tc;
   }

   public override fun consumeNextToken(expected: Char) {
      this.ensureHaveChars();
      val source: ArrayAsSequence = this.getSource();
      var var8: Int = this.currentPosition;

      while (true) {
         var8 = this.prefetchOrEof(var8);
         if (var8 == -1) {
            this.currentPosition = var8;
            this.unexpectedToken(expected);
            return;
         }

         val c: Char = source.charAt(var8++);
         val `this_$iv`: AbstractJsonLexer = this;
         if (c != ' ' && c != '\n' && c != '\r' && c != '\t') {
            this.currentPosition = var8;
            if (c == expected) {
               return;
            }

            this.unexpectedToken(expected);
         }
      }
   }

   public override fun skipWhitespaces(): Int {
      var var6: Int = this.currentPosition;

      while (true) {
         var6 = this.prefetchOrEof(var6);
         if (var6 == -1) {
            break;
         }

         val c: Char = this.getSource().charAt(var6);
         val `this_$iv`: AbstractJsonLexer = this;
         if (c != ' ' && c != '\n' && c != '\r' && c != '\t') {
            break;
         }

         var6++;
      }

      this.currentPosition = var6;
      return var6;
   }

   public override fun ensureHaveChars() {
      val cur: Int = this.currentPosition;
      val spaceLeft: Int = this.getSource().length() - cur;
      if (spaceLeft <= this.threshold) {
         this.preload(spaceLeft);
      }
   }

   public override fun consumeKeyString(): String {
      this.consumeNextToken('"');
      var current: Int = this.currentPosition;
      val closingQuote: Int = this.indexOf('"', this.currentPosition);
      if (closingQuote == -1) {
         current = this.prefetchOrEof(current);
         if (current != -1) {
            return this.consumeString(this.getSource(), this.currentPosition, current);
         } else {
            val var15: AbstractJsonLexer = this;
            val `expected$iv`: java.lang.String = AbstractJsonLexerKt.tokenDescription((byte)1);
            val `position$iv`: Int = var15.currentPosition - 1;
            AbstractJsonLexer.fail$default(
               var15,
               "Expected $`expected$iv`, but had '${if (var15.currentPosition != AbstractJsonLexer.access$getSource(var15).length() && `position$iv` >= 0)
                  java.lang.String.valueOf(AbstractJsonLexer.access$getSource(var15).charAt(`position$iv`))
                  else
                  "EOF"}' instead",
               `position$iv`,
               null,
               4,
               null
            );
            throw new KotlinNothingValueException();
         }
      } else {
         for (int i = current; i < closingQuote; i++) {
            if (this.getSource().charAt(i) == '\\') {
               return this.consumeString(this.getSource(), this.currentPosition, i);
            }
         }

         this.currentPosition = closingQuote + 1;
         return this.substring(current, closingQuote);
      }
   }

   public override fun indexOf(char: Char, startPos: Int): Int {
      val src: ArrayAsSequence = this.getSource();
      var i: Int = startPos;

      for (int var5 = src.length(); i < var5; i++) {
         if (src.charAt(i) == var1) {
            return i;
         }
      }

      return -1;
   }

   public override fun substring(startPos: Int, endPos: Int): String {
      return this.getSource().substring(startPos, endPos);
   }

   protected override fun appendRange(fromIndex: Int, toIndex: Int) {
   }

   public override fun peekLeadingMatchingValue(keyToMatch: String, isLenient: Boolean): String? {
      return null;
   }

   public fun release() {
      CharArrayPoolBatchSize.INSTANCE.release(this.buffer);
   }
}

package kotlinx.serialization.json.internal

import kotlin.jvm.internal.SourceDebugExtension
import kotlin.jvm.internal.markers.KMappedMarker
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.json.Json

@SourceDebugExtension(["SMAP\nJsonIterator.kt\nKotlin\n*S Kotlin\n*F\n+ 1 JsonIterator.kt\nkotlinx/serialization/json/internal/JsonIteratorArrayWrapped\n+ 2 AbstractJsonLexer.kt\nkotlinx/serialization/json/internal/AbstractJsonLexer\n+ 3 AbstractJsonLexer.kt\nkotlinx/serialization/json/internal/AbstractJsonLexer$fail$1\n*L\n1#1,103:1\n226#2,10:104\n229#3:114\n*S KotlinDebug\n*F\n+ 1 JsonIterator.kt\nkotlinx/serialization/json/internal/JsonIteratorArrayWrapped\n*L\n99#1:104,10\n99#1:114\n*E\n"])
private class JsonIteratorArrayWrapped<T>(json: Json, lexer: ReaderJsonLexer, deserializer: DeserializationStrategy<Any>) :
   java.util.Iterator<T>,
   KMappedMarker {
   private final val json: Json
   private final val lexer: ReaderJsonLexer
   private final val deserializer: DeserializationStrategy<Any>
   private final var first: Boolean
   private final var finished: Boolean

   init {
      this.json = json;
      this.lexer = lexer;
      this.deserializer = deserializer;
      this.first = true;
   }

   public override operator fun next(): Any {
      if (this.first) {
         this.first = false;
      } else {
         this.lexer.consumeNextToken(',');
      }

      return new StreamingJsonDecoder(this.json, WriteMode.OBJ, this.lexer, this.deserializer.getDescriptor(), null).decodeSerializableValue(this.deserializer);
   }

   public override operator fun hasNext(): Boolean {
      if (this.finished) {
         return false;
      } else if (this.lexer.peekNextToken() == 9) {
         this.finished = true;
         this.lexer.consumeNextToken((byte)9);
         if (this.lexer.isNotEof()) {
            if (this.lexer.peekNextToken() == 8) {
               AbstractJsonLexer.fail$default(
                  this.lexer,
                  "There is a start of the new array after the one parsed to sequence. ARRAY_WRAPPED mode doesn't merge consecutive arrays.\nIf you need to parse a stream of arrays, please use WHITESPACE_SEPARATED mode instead.",
                  0,
                  null,
                  6,
                  null
               );
               throw new KotlinNothingValueException();
            }

            this.lexer.expectEof();
         }

         return false;
      } else if (!this.lexer.isNotEof() && !this.finished) {
         val `$this$iv`: AbstractJsonLexer = this.lexer;
         val `expected$iv`: java.lang.String = AbstractJsonLexerKt.tokenDescription((byte)9);
         val `position$iv`: Int = `$this$iv`.currentPosition - 1;
         AbstractJsonLexer.fail$default(
            `$this$iv`,
            "Expected $`expected$iv`, but had '${if (`$this$iv`.currentPosition != AbstractJsonLexer.access$getSource(`$this$iv`).length()
                  && `position$iv` >= 0)
               java.lang.String.valueOf(AbstractJsonLexer.access$getSource(`$this$iv`).charAt(`position$iv`))
               else
               "EOF"}' instead",
            `position$iv`,
            null,
            4,
            null
         );
         throw new KotlinNothingValueException();
      } else {
         return true;
      }
   }

   override fun remove() {
      throw new UnsupportedOperationException("Operation is not supported for read-only collection");
   }
}
